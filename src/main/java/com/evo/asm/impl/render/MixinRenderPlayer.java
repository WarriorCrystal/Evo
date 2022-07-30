package com.evo.asm.impl.render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.evo.core.Evo;
import com.evo.core.features.module.render.Nametags;
import com.evo.util.internal.Wrapper;

@Mixin(RenderPlayer.class)
public class MixinRenderPlayer {
    private float renderPitch;
    private float renderYaw;
    private float renderHeadYaw;
    private float prevRenderHeadYaw;
    private float lastRenderHeadYaw = 0.0f;
    private float prevRenderPitch;
    private float lastRenderPitch = 0.0f;

    @Inject(method = "doRender", at = @At("HEAD"))
    public void doRenderPre(AbstractClientPlayer player, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo info) {
        if (player == Wrapper.mc.player && Evo.INSTANCE.getRotationManager().isValid()) {
            renderPitch = player.rotationPitch;
            renderYaw = player.rotationYaw;

            renderHeadYaw = player.rotationYawHead;

            prevRenderHeadYaw = player.prevRotationYawHead;
            prevRenderPitch = player.prevRotationPitch;

            player.rotationPitch = Evo.INSTANCE.getRotationManager().getPitch();
            player.rotationYaw = Evo.INSTANCE.getRotationManager().getYaw();

            player.rotationYawHead = Evo.INSTANCE.getRotationManager().getYaw();
            player.renderYawOffset = Evo.INSTANCE.getRotationManager().getYaw();

            player.prevRotationYawHead = lastRenderHeadYaw;
            player.prevRotationPitch = lastRenderPitch;
        }
    }

    @Inject(method = "doRender", at = @At("RETURN"))
    public void doRenderPost(AbstractClientPlayer player, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo info) {
        if (player == Wrapper.mc.player && Evo.INSTANCE.getRotationManager().isValid()) {
            lastRenderHeadYaw = player.rotationYawHead;
            lastRenderPitch = player.rotationPitch;

            player.rotationPitch = renderPitch;
            player.rotationYaw = renderYaw;

            player.prevRotationPitch = prevRenderPitch;

            player.rotationYawHead = renderHeadYaw;
            player.prevRotationYawHead = prevRenderHeadYaw;
        }
    }

    @Inject(method = "renderEntityName", at = @At("HEAD"), cancellable = true)
    public void renderEntityName(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq, CallbackInfo info) {
        if (Nametags.INSTANCE.isToggled()) {
            info.cancel();
        }
    }
}
