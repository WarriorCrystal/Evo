package com.evo.asm.impl;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.evo.core.events.ShutdownEvent;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "shutdown", at = @At("HEAD"))
    public void shutdown(CallbackInfo info) {
        MinecraftForge.EVENT_BUS.post(new ShutdownEvent());
    }
}
