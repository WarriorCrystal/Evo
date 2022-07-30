package com.evo.core.features.module.other;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.core.setting.Setting;

public class Chat extends Module {
    public static Chat INSTANCE;

    public Chat() {
        super("Chat", Category.OTHER, "Modifies the chat");
        INSTANCE = this;
    }

    public static final Setting<Boolean> infinite = new Setting<>("Infinite", false);
    public static final Setting<Boolean> clear = new Setting<>("Clear", true);
}
