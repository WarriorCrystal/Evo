package com.evo.core.features.module.render;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.core.setting.Setting;

public class CameraClip extends Module {
    public static CameraClip INSTANCE;

    public CameraClip() {
        super("CameraClip", Category.RENDER, "Clips your camera through blocks");
        INSTANCE = this;
    }

    public static final Setting<Double> distance = new Setting<>("Distance", 4.0, 1.0, 6.0);
}
