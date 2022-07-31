package cf.warriorcrystal.evo.module.modules.gui;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class Bps extends Module {
    public Bps() {
        super("BPS", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("bpsRed", this, 255, 0, 255, true);
        green = new Setting("bpsGreen", this, 255, 0, 255, true);
        blue = new Setting("bpsBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(red);
        Evo.getInstance().settingsManager.rSetting(green);
        Evo.getInstance().settingsManager.rSetting(blue);
        rainbow = new Setting("bpsRainbow", this, false);
        Evo.getInstance().settingsManager.rSetting(rainbow);
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("bpsCFont", this, false));
    }

    public void onEnable(){
        disable();
    }


}
