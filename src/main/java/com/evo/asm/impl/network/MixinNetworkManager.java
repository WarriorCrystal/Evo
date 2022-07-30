package com.evo.asm.impl.network;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.evo.core.events.PacketEvent;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {
    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void channelRead0(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo info) {
        PacketEvent.Receive event = new PacketEvent.Receive(packet);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.isCanceled()) {
            info.cancel();
        }
    }

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    public void sendPacket(Packet<?> packet, CallbackInfo info) {
        PacketEvent.Send event = new PacketEvent.Send(packet);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.isCanceled()) {
            info.cancel();
        }
    }
}
