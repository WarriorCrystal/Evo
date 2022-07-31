package cf.warriorcrystal.evo.module.modules.gui;

import de.Hero.settings.Setting;

import java.util.ArrayList;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;

public class Crystals extends Module {
    public Crystals() {
        super("Crystals", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;
    public Setting mode;

    public void setup(){
        ArrayList<String> modes = new ArrayList<>();
        modes.add("Short");
        modes.add("Full");
        red = new Setting("CrystalsRed", this, 255, 0, 255, true);
        green = new Setting("CrystalsGreen", this, 255, 0, 255, true);
        blue = new Setting("CrystalsBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(red);
        Evo.getInstance().settingsManager.rSetting(green);
        Evo.getInstance().settingsManager.rSetting(blue);
        Evo.getInstance().settingsManager.rSetting(rainbow = new Setting("cryRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("cryCFont", this, false));
        Evo.getInstance().settingsManager.rSetting(mode = new Setting("cryText", this, "Short", modes));
    }

    public void onEnable(){
        disable();
    }
}
