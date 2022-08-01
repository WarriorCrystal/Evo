package cf.warriorcrystal.evo.mixin.mixins;

import cf.warriorcrystal.evo.mixin.accessor.IRenderManager;
import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RenderManager.class)
public abstract class MixinRenderManager implements IRenderManager {

    @Accessor
    @Override
    public abstract double getRenderPosX();

    @Accessor
    @Override
    public abstract double getRenderPosY();

    @Accessor
    @Override
    public abstract double getRenderPosZ();
}