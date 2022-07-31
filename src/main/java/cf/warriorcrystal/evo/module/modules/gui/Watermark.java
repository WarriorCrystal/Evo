package cf.warriorcrystal.evo.module.modules.gui;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class Watermark extends Module {
    public static Watermark INSTANCE;
    public Watermark() {
        super("Watermark", Category.GUI);
        setDrawn(false);
        INSTANCE = this;
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("MarkRed", this, 255, 0, 255, true);
        green = new Setting("MarkGreen", this, 255, 0, 255, true);
        blue = new Setting("MarkBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(red);
        Evo.getInstance().settingsManager.rSetting(green);
        Evo.getInstance().settingsManager.rSetting(blue);
        rainbow = new Setting("MarkRainbow", this, true);
        Evo.getInstance().settingsManager.rSetting(rainbow);
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("markCustomFont", this, true));
    }

    public void onEnable(){
        disable();
    }
}
