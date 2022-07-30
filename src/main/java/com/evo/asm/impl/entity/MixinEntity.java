package com.evo.asm.impl.entity;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.evo.core.events.EntityVelocityEvent;
import com.evo.util.internal.Wrapper;

@Mixin(Entity.class)
public class MixinEntity {
    @Redirect(method = "applyEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void hookAddVelocity(Entity entity, double x, double y, double z) {
        if (((Entity) (Object) this) == Wrapper.mc.player) {
            EntityVelocityEvent event = new EntityVelocityEvent(EntityVelocityEvent.Material.ENTITY);
            MinecraftForge.EVENT_BUS.post(event);

            if (!event.isCanceled()) {
                entity.motionX += x;
                entity.motionY += y;
                entity.motionZ += z;
            }
        }
    }
}
