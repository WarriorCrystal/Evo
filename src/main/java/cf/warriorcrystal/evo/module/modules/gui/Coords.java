package cf.warriorcrystal.evo.module.modules.gui;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class Coords extends Module {
    public Coords() {
        super("Coordinates", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("CoordsRed", this, 255, 0, 255, true);
        green = new Setting("CoordsGreen", this, 255, 0, 255, true);
        blue = new Setting("CoordsBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(red);
        Evo.getInstance().settingsManager.rSetting(green);
        Evo.getInstance().settingsManager.rSetting(blue);
        Evo.getInstance().settingsManager.rSetting(rainbow = new Setting("coordsRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("coordsCFont", this, false));
    }

    public void onEnable(){
        disable();
    }
}
