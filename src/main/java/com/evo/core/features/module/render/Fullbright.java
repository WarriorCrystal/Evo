package com.evo.core.features.module.render;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;

// cba to add different modes
public class Fullbright extends Module {
    public static float oldGamma = -1.0f;

    public Fullbright() {
        super("Fullbright", Category.RENDER, "Brightens up the game");
    }

    @Override
    protected void onEnable() {
        oldGamma = mc.gameSettings.gammaSetting;
    }

    @Override
    protected void onDisable() {
        mc.gameSettings.gammaSetting = oldGamma;
        oldGamma = -1.0f;
    }

    @Override
    public void onUpdate() {
        mc.gameSettings.gammaSetting = 100.0f;
    }
}
