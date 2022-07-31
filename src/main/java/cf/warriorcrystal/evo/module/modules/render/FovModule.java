package cf.warriorcrystal.evo.module.modules.render;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class FovModule extends Module {
    public FovModule() {
        super("FOV", Category.RENDER);
        Evo.getInstance().settingsManager.rSetting(fov = new Setting("FovAmount", this, 90, 0, 180, true));
        setDrawn(false);
    }

    Setting fov;

    public void onUpdate(){
        mc.gameSettings.fovSetting = (float)fov.getValInt();
    }
}
