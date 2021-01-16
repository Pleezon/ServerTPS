package de.techgamez.pleezon;
import de.techgamez.pleezon.events.custom.TimeUpdate.TimeUpdateType;
import de.techgamez.pleezon.events.custom.spawnPositionUpdate.SpawnPositionUpdateType;
import de.techgamez.pleezon.modules.PercentualTPSModule;
import de.techgamez.pleezon.modules.TPSModule;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

import java.util.ArrayList;
import java.util.List;

public class Main extends LabyModAddon {
    @Override
    public void onEnable() {
        this.getApi().registerModule(new TPSModule());
        this.getApi().registerModule(new PercentualTPSModule());
        TimeUpdateType.Instance.callbacks.add((o)->{
            S03PacketTimeUpdate p = (S03PacketTimeUpdate) o;
            calctps(p);
            return null;
        });
        SpawnPositionUpdateType.Instance.callbacks.add((c)->{
            reset();
            return null;
        });
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }
    private void reset(){
        TPSModule.value = "n.a.";
        PercentualTPSModule.value = "n.a.";
        lastWorldTime = null;
        totalTPS.clear();
    }

    private Long lastWorldTime;
    private Long lastMillis;
    private int lasttriptime;
    private ArrayList<Double> totalTPS = new ArrayList<>();

    private void calctps(S03PacketTimeUpdate packet){
        if(Minecraft.getMinecraft().thePlayer==null||Minecraft.getMinecraft().getNetHandler()==null){return;}
        try{
            int triptime =  Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getGameProfile().getId()).getResponseTime()/2;
            long currentWorldTime = packet.getTotalWorldTime();
            long currentMillis = System.currentTimeMillis();
            if(lastWorldTime==null){
                lastWorldTime=currentWorldTime;
                lastMillis=currentMillis;
                lasttriptime = triptime;
                return;
            }
            int triptimeDiff = triptime-lasttriptime;
            long ageDiff = currentWorldTime-lastWorldTime;
            long timeDiff = currentMillis - (lastMillis + triptimeDiff);
            double timeDiffInSeconds = timeDiff/1000.0;
            double tpt = ageDiff/timeDiffInSeconds;
            if(tpt>1){
                totalTPS.add(Math.min(tpt,20));
                if(totalTPS.size()>=30){
                    TPSModule.value=String.valueOf(Math.round(getAverage(totalTPS) * 100) / 100.0);
                    PercentualTPSModule.value = (Math.round(getAverage(totalTPS)/20* 10000) / 100.0)+ "%";
                    totalTPS.remove(0);
                }
            }
            lastWorldTime = currentWorldTime;
            lasttriptime = triptime;
            lastMillis = currentMillis;
        }catch (Exception ignored){};
    }

    private double getAverage(ArrayList<Double> doubles){
        double total = 0;
        for (double d : doubles) {
            total+=d;
        }
        return total/doubles.size();
    }











}
