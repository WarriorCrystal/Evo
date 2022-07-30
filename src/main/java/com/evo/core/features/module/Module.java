package com.evo.core.features.module;

import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import com.evo.core.events.ModuleToggleEvent;
import com.evo.core.setting.Bind;
import com.evo.core.setting.Configurable;
import com.evo.core.setting.Setting;

public class Module extends Configurable {
    private final String name;
    private final Category category;
    private final String description;

    protected final Bind bind = new Bind("Bind", Keyboard.KEY_NONE);
    protected final Setting<Boolean> drawn = new Setting<>("Drawn", false);

    protected boolean toggled = false;

    public Module(String name, Category category, String description) {
        this(name, category, description, Keyboard.KEY_NONE);
    }

    public Module(String name, Category category, String description, int code) {
        this.name = name;
        this.category = category;
        this.description = description;

        bind.setValue(code);

        settings.add(bind);
        settings.add(drawn);
    }

    public void onUpdate() {

    }

    public void onTick() {

    }

    public void onRender2D() {

    }

    public void onRender3D() {

    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void toggle() {
        toggled = !toggled;
        MinecraftForge.EVENT_BUS.post(new ModuleToggleEvent(this, toggled));

        if (toggled) {
            MinecraftForge.EVENT_BUS.register(this);
            onEnable();
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            onDisable();
        }
    }

    protected void onEnable() {

    }

    protected void onDisable() {

    }

    public boolean isToggled() {
        return toggled;
    }

    public boolean isDrawn() {
        return drawn.getValue();
    }

    public int getBind() {
        return bind.getValue();
    }

    public void setBind(int in) {
        bind.setValue(in);
    }
}
