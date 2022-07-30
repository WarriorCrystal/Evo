package com.evo.core.features.module.client;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.core.gui.click.ClickGUIScreen;
import com.evo.core.setting.Setting;

public class ClickGUI extends Module {
    public static ClickGUI INSTANCE;

    public ClickGUI() {
        super("ClickGUI", Category.CLIENT, "Shows the clients modules and settings", Keyboard.KEY_I);
        INSTANCE = this;
    }

    public static final Setting<Boolean> pause = new Setting<>("Pause", false);

    public static final Setting<Boolean> scrollInvert = new Setting<>("ScrollInvert", true);
    public static final Setting<Double> scrollSpeed = new Setting<>("ScrollSpeed", 10.0, 1.0, 20.0);

    public static final Setting<Background> background = new Setting<>("Background", Background.BLUR);
    public static final Setting<Double> intensity = new Setting<>(background, "Intensity", 7.0, 1.0, 15.0);

    @Override
    protected void onEnable() {
        if (nullCheck()) {
            mc.displayGuiScreen(ClickGUIScreen.getInstance());
        } else {
            toggle();
        }
    }

    @Override
    protected void onDisable() {
        if (nullCheck()) {
            mc.displayGuiScreen(null);
        }
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (!(event.getGui() instanceof ClickGUIScreen)) {
            toggle();
        }
    }

    public enum Background {
        /**
         * No background
         */
        NONE,

        /**
         * The default blackish background minecraft uses
         */
        DEFAULT,

        /**
         * Show nice ass custom blur shader as the background
         */
        BLUR
    }
}
