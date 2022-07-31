package cf.warriorcrystal.evo.mixin.mixins;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.ModuleManager;

import javax.annotation.Nullable;
import java.util.UUID;

@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer {
    @Shadow @Nullable protected abstract NetworkPlayerInfo getPlayerInfo();

    @Inject(method = "getLocationCape", at = @At("HEAD"), cancellable = true)
    public void getLocationCape(CallbackInfoReturnable<ResourceLocation> cir){
        UUID uuid = getPlayerInfo().getGameProfile().getId();

        if(ModuleManager.isModuleEnabled("Capes") && Evo.getInstance().capeUtils.hasCape(uuid)) {
            cir.setReturnValue(new ResourceLocation("evo:textures/cape.png"));
        }
    }
}
