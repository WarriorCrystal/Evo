package com.evo.core.features.module.other;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;

// im trying bro
public class FullScreenshot extends Module {
    public static FullScreenshot INSTANCE;

    public FullScreenshot() {
        super("FullScreenshot", Category.OTHER, "Takes a screenshot of the entire screen rather than just the game window");
        INSTANCE = this;
    }
}
