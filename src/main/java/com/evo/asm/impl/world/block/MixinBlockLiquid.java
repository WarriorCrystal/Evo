package com.evo.asm.impl.world.block;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.evo.core.features.module.player.Interact;

@Mixin(BlockLiquid.class)
public class MixinBlockLiquid {
    @Inject(method = "canCollideCheck", at = @At("RETURN"), cancellable = true)
    public void canCollideCheck(IBlockState state, boolean hitIfLiquid, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(info.getReturnValue() || (Interact.INSTANCE.isToggled() && Interact.liquidPlace.getValue()));
    }
}
