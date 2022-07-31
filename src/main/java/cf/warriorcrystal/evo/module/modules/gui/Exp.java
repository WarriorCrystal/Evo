package cf.warriorcrystal.evo.module.modules.gui;

import de.Hero.settings.Setting;

import java.util.ArrayList;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;

public class Exp extends Module {
    public Exp() {
        super("Exp", Category.GUI);
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
        red = new Setting("ExpRed", this, 255, 0, 255, true);
        green = new Setting("ExpGreen", this, 255, 0, 255, true);
        blue = new Setting("ExpBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(red);
        Evo.getInstance().settingsManager.rSetting(green);
        Evo.getInstance().settingsManager.rSetting(blue);
        Evo.getInstance().settingsManager.rSetting(rainbow = new Setting("expRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("expCFont", this, false));
        Evo.getInstance().settingsManager.rSetting(mode = new Setting("expText", this, "Short", modes));
    }

    public void onEnable(){
        disable();
    }
}
