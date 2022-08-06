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
import cf.warriorcrystal.evo.event.events.EventPlayerGetLocationCape;
import cf.warriorcrystal.evo.module.ModuleManager;

import javax.annotation.Nullable;
import java.util.UUID;

@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer {
    @Shadow @Nullable protected abstract NetworkPlayerInfo getPlayerInfo();

    @Inject(method = "getLocationCape", at = @At(value = "RETURN"), cancellable = true)
    public void getCape(CallbackInfoReturnable<ResourceLocation> callbackInfo)
    {
        EventPlayerGetLocationCape l_Event = new EventPlayerGetLocationCape((AbstractClientPlayer)(Object)this);
        Evo.EVENT_BUS.post(l_Event);

        if (l_Event.isCancelled())
        {
            // p_Callback.cancel();
            callbackInfo.setReturnValue(l_Event.GetResourceLocation());
        }
    }
}
