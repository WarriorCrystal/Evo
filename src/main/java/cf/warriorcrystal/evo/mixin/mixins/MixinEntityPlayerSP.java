package cf.warriorcrystal.evo.mixin.mixins;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.PlayerMoveEvent;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer {
    public MixinEntityPlayerSP() {
        super(null, null);
    }

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {
        PlayerMoveEvent moveEvent = new PlayerMoveEvent(type, x, y, z);
        Evo.EVENT_BUS.post(moveEvent);
        if(moveEvent.isCancelled()) {
        }
        super.move(type, moveEvent.x, moveEvent.y, moveEvent.z);
    }
}
