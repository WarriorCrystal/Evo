package com.evo.asm.impl.world.block;

import net.minecraft.block.BlockSlime;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.evo.core.features.module.movement.NoSlow;
import com.evo.util.internal.Wrapper;

@Mixin(BlockSlime.class)
public class MixinBlockSlime {
    @Inject(method = "onEntityWalk", at = @At("HEAD"), cancellable = true)
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn, CallbackInfo info) {
        if (NoSlow.INSTANCE.isToggled() && NoSlow.slime.getValue() && entityIn.equals(Wrapper.mc.player)) {
            info.cancel();
        }
    }
}
