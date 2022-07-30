package com.evo.util.internal;

import com.evo.core.Evo;

import net.minecraft.client.Minecraft;

public interface Wrapper {
    Minecraft mc = Minecraft.getMinecraft();

    default boolean nullCheck() {
        return mc.world != null && mc.player != null;
    }

    default Evo getEvo() {
        return Evo.INSTANCE;
    }
}
