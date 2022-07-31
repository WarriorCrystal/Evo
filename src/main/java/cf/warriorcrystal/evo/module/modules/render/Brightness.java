package cf.warriorcrystal.evo.module.modules.render;

import cf.warriorcrystal.evo.module.Module;

public class Brightness extends Module {
    public Brightness() {
        super("Brightness", Category.RENDER);
    }

    float old;

    public void onEnable(){
        old = mc.gameSettings.gammaSetting;
    }

    public void onUpdate(){
        mc.gameSettings.gammaSetting = 666f;
    }

    public void onDisable(){
        mc.gameSettings.gammaSetting = old;
    }
}
