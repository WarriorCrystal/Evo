package cf.warriorcrystal.evo.module.modules.gui;


import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class Core extends Module {

    public static Setting saturation;
    public static Setting brightness;
    public static Setting speed;
    public static Setting customFont;

    int i = 0;
    int j = 0;
    boolean switchingSlot = false;
    int delaySlot = 0;

    public Core() {
        super("Core", Category.GUI);

        Evo.getInstance().settingsManager.rSetting(saturation = new Setting("Saturation", this, .8, 0, 1, false));
        Evo.getInstance().settingsManager.rSetting(brightness = new Setting("Brightness", this, .8, 0, 1, false));
        Evo.getInstance().settingsManager.rSetting(speed = new Setting("Speed", this, 1, 0, 5, false));
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("CustomFont", this, true));
    }
}