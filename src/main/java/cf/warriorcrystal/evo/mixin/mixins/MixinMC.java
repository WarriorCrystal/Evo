package cf.warriorcrystal.evo.mixin.mixins;

import cf.warriorcrystal.evo.mixin.accessor.IMinecraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = Minecraft.class)
public abstract class MixinMC implements IMinecraft {

    @Accessor
    @Override public abstract Timer getTimer();
    @Accessor @Override public abstract void setRightClickDelayTimer(int delay);
    @Shadow
    public EntityPlayerSP player;
    @Shadow public PlayerControllerMP playerController;



}