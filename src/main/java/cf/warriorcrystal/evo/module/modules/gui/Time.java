package cf.warriorcrystal.evo.module.modules.gui;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class Time extends Module {
    public Time() {
        super("Time", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("TimeRed", this, 255, 0, 255, true);
        green = new Setting("TimeGreen", this, 255, 0, 255, true);
        blue = new Setting("TimeBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(red);
        Evo.getInstance().settingsManager.rSetting(green);
        Evo.getInstance().settingsManager.rSetting(blue);
        Evo.getInstance().settingsManager.rSetting(rainbow = new Setting("timeRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("timeCFont", this, false));
    }

    public void onEnable(){
        disable();
    }
}
