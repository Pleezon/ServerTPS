package de.techgamez.pleezon.modules;

import de.techgamez.pleezon.events.custom.TimeUpdate.TimeUpdateType;
import de.techgamez.pleezon.events.custom.spawnPositionUpdate.SpawnPositionUpdateType;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

import java.util.ArrayList;

public class TPSModule extends SimpleModule {
    public static String value = "n.a.";
    @Override
    public String getDisplayName() {
        return "Server-TPS";
    }

    @Override
    public String getDisplayValue() {
        return value;
    }

    @Override
    public String getDefaultValue() {
        return "n.a.";
    }

    @Override
    public String getControlName(){
        return "Server-TPS";
    }


    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.WATCH);
    }

    @Override
    public void loadSettings() {


    }

    @Override
    public String getSettingName() {
        return "Server-TPS";
    }

    @Override
    public String getDescription() {
        return "zeigt eine (relativ genaue) Schätzung der aktuellen server-TPS an.";
    }

    @Override
    public int getSortingId() {
        return 0;
    }









}
