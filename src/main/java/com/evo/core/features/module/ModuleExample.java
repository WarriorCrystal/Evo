package com.evo.core.features.module;

import com.evo.core.setting.Setting;

public class ModuleExample extends Module {

    public ModuleExample() {
        super("ModuleExample", Category.COMBAT, "Example");
    }

    public static final Setting<Mode> exampleMode = new Setting<>("Example", Mode.TWO);
    public static final Setting<Float> exampleFloat = new Setting<>("Example", 16.0f, 1.0f, 20.0f);
    public static final Setting<Boolean> exampleBoolean = new Setting<>("Example", true);
    public static final Setting<Integer> exampleInteger = new Setting<>("Example", 1, 0, 10);
    public static final Setting<Double> exampleDouble = new Setting<>("Example", 3.5, 1.0, 6.0);

    public enum Mode {
        ONE,
        TWO,
        ETC
    }

    @Override
    protected void onEnable() {
        
    }

    @Override
    protected void onDisable() {

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onTick() {

    }

    @Override
    public void onRender2D() {

    }
    
    @Override
    public void onRender3D() {

    }
}
