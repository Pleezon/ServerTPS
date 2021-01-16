package de.techgamez.pleezon.modules;

import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

public class PercentualTPSModule extends SimpleModule {
    public static String value = "n.a.";
    @Override
    public String getDisplayName() {
        return "TPS%";
    }
    @Override
    public String getControlName(){
        return "Server-TPS %";
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
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.REDSTONE);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "ServerLeistung";
    }

    @Override
    public String getDescription() {
        return "Zeigt die TPS in % im vergleich zur optimalen Performance an.";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}
