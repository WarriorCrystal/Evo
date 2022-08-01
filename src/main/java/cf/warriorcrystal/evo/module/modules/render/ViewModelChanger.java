package cf.warriorcrystal.evo.module.modules.render;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class ViewModelChanger extends Module {

    public static Setting sizeX;
    public static Setting sizeY;
    public static Setting sizeZ;
    public static Setting sizeXMain;
    public static Setting sizeYMain;
    public static Setting sizeZMain;
    public static Setting rotateZ;
    public ViewModelChanger() {
        super("ViewModelChanger", Category.RENDER);
        Evo.getInstance().settingsManager.rSetting(sizeX = new Setting("OffSetXMain", this, 1, -3, 3,false));
        Evo.getInstance().settingsManager.rSetting(sizeY = new Setting("OffSetYMain", this, 1, -3, 3,false));
        Evo.getInstance().settingsManager.rSetting(sizeZ = new Setting("SizeMain", this, 1, 0, 4,false));
        Evo.getInstance().settingsManager.rSetting(sizeXMain = new Setting("SizeXMain", this, 1, 0, 4,false));
        Evo.getInstance().settingsManager.rSetting(sizeYMain = new Setting("SizeYMain", this, 1, 0, 4,false));
        Evo.getInstance().settingsManager.rSetting(sizeZMain = new Setting("SizeZMain", this, 1, 0, 4,false));
        Evo.getInstance().settingsManager.rSetting(rotateZ = new Setting("RotateYawMain", this, 1, 0, 360,false));

    }
}