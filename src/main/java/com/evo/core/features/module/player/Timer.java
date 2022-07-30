package com.evo.core.features.module.player;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.core.setting.Setting;

public class Timer extends Module {
    public Timer() {
        super("Timer", Category.PLAYER, "Modifies your game's tick speed");
    }

    public static final Setting<Float> speed = new Setting<>("Speed", 1.5f, 0.1f, 20.0f);

    @Override
    protected void onDisable() {
        getEvo().getTickManager().reset();
    }

    @Override
    public void onUpdate() {
        getEvo().getTickManager().setTicks(50.0f, speed.getValue());
    }
}
