package com.evo.core.features.module.client;

import java.awt.*;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.core.setting.Setting;

public class CustomFont extends Module {
    public static CustomFont INSTANCE;

    public CustomFont() {
        super("CustomFont", Category.CLIENT, "Manages the client's custom font");
        INSTANCE = this;
    }

    public static final Setting<String> font = new Setting<>("Font", "Verdana");
    public static final Setting<Integer> size = new Setting<>("Size", 18, 6, 20);

    public static final Setting<Style> style = new Setting<>("Style", Style.PLAIN);
    public static final Setting<Boolean> shadow = new Setting<>(style, "Shadow", false);

    @Override
    protected void onEnable() {
        getEvo().getFontManager().setFontRenderer(font.getValue(), style.getValue().style, size.getValue());
    }

    @Override
    protected void onDisable() {
        getEvo().getFontManager().resetToDefault();
    }

    public enum Style {
        PLAIN(Font.PLAIN),
        BOLD(Font.BOLD),
        ITALIC(Font.ITALIC),
        BOLDEDITALIC(Font.BOLD + Font.ITALIC);

        private final int style;

        Style(int style) {
            this.style = style;
        }
    }
}
