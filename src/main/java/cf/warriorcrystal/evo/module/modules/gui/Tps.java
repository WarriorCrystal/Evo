package cf.warriorcrystal.evo.module.modules.gui;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class Tps extends Module {
    public static Tps INSTANCE;
    public Tps() {
        super("TPS", Category.GUI);
        setDrawn(false);
        INSTANCE = this;
    }



    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("TpsRed", this, 255, 0, 255, true);
        green = new Setting("TpsGreen", this, 255, 0, 255, true);
        blue = new Setting("TpsBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(red);
        Evo.getInstance().settingsManager.rSetting(green);
        Evo.getInstance().settingsManager.rSetting(blue);
        Evo.getInstance().settingsManager.rSetting(rainbow = new Setting("tpsRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("tpsCFont", this, false));
    }

    public void onEnable(){
        disable();
    }
}
