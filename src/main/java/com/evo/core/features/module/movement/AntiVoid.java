package com.evo.core.features.module.movement;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;

public class AntiVoid extends Module {
    public AntiVoid() {
        super("AntiVoid", Category.MOVEMENT, "Stops you from falling into the void");
    }

    @Override
    public void onUpdate() {
        if (mc.player.posY <= 0.0) {
            mc.player.motionY = 0.0;
        }
    }
}
