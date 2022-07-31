package cf.warriorcrystal.evo.module.modules.gui;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class PvpInfo extends Module {
    public PvpInfo() {
        super("PvpInfo", Category.GUI);
        setDrawn(false);
    }

    public Setting offRainbow;
    public Setting offR;
    public Setting offG;
    public Setting offB;
    public Setting onRainbow;
    public Setting onR;
    public Setting onG;
    public Setting onB;
    public Setting customFont;

    public void setup(){
        Evo.getInstance().settingsManager.rSetting(offRainbow = new Setting("piOffRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(offR = new Setting("piOffR", this, 255, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(offG = new Setting("piOffG", this, 0, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(offB = new Setting("piOffB", this, 0, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(onRainbow = new Setting("piOnRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(onR = new Setting("piOnR", this, 0, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(onG = new Setting("piOnG", this, 255, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(onB = new Setting("piOnB", this, 0, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("piCFont", this, false));
    }

    public void onEnable(){
        disable();
    }
}
