/*package cf.warriorcrystal.evo.mixin.mixins;

import cf.warriorcrystal.evo.module.ModuleManager;
import cf.warriorcrystal.evo.module.modules.render.ViewModelChanger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemRenderer.class, priority = 5000)
public abstract class MixinItemRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();
    public final RenderManager renderManager;

    protected MixinItemRenderer(RenderManager renderManager) {
        this.renderManager = renderManager;
    }
    @Inject(method = "transformEatFirstPerson", at = @At("HEAD"), cancellable = true)
    private void transformEatFirstPerson(float p_187454_1_, EnumHandSide hand, ItemStack stack, CallbackInfo ci) {
        if (ModuleManager.getModuleByName("ViewModelChanger").isEnabled()) {
            float f = (float) this.mc.player.getItemInUseCount() - p_187454_1_ + 1.0F;
            float f1 = f / (float) stack.getMaxItemUseDuration();
            float f3;
            if (f1 < 0.8F) {
                f3 = MathHelper.abs(MathHelper.cos(f / 4.0F * 3.1415927F) * 0.1F);
                GlStateManager.translate(0.0F, f3, 0.0F);
            }

            f3 = 1.0F - (float) Math.pow(f1, 27.0D);
            int i = hand == EnumHandSide.RIGHT ? 1 : -1;
            GlStateManager.translate(f3 * (ViewModelChanger.sizeX.getValDouble() * .6F) * (float) i, f3 * (-ViewModelChanger.sizeYMain.getValDouble() * .5), f3 * ViewModelChanger.sizeZMain.getValDouble());
            GlStateManager.rotate((float) i * f3 * 90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(f3 * 10.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate((float) i * f3 * 30.0F, 0.0F, 0.0F, 1.0F);
        }
    }

    @Inject(method = "transformFirstPerson", at = @At("HEAD"), cancellable = true)
    public void transformFirstPerson(EnumHandSide hand, float p_187453_2_, CallbackInfo info) {
        if (ModuleManager.getModuleByName("ViewModelChanger").isEnabled()) {
            int i = hand == EnumHandSide.RIGHT ? 1 : -1;
            float f = MathHelper.sin(p_187453_2_ * p_187453_2_ * 3.1415927F);
            GlStateManager.rotate((float) i * (-45.0f + f * -20.0F), 0.0F, 100.0F, 0.0F);
            float f1 = MathHelper.sin(MathHelper.sqrt(p_187453_2_) * 3.1415927F);
            GlStateManager.rotate((float) i * f1 * -20.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate((float) i * ViewModelChanger.rotateZ.getValInt(), 0.0F, 1.0F, 0.0F);
            // still gotta find what does pitch
            GlStateManager.scale(ViewModelChanger.sizeXMain.getValDouble(), ViewModelChanger.sizeYMain.getValDouble(), ViewModelChanger.sizeZMain.getValDouble());
        }
    }

    @Inject(method = "transformSideFirstPerson", at = @At("HEAD"), cancellable = true)
    public void transformSideFirstPerson(EnumHandSide hand, float p_187459_2_, CallbackInfo info) {
        if (ModuleManager.getModuleByName("ViewModelChanger").isEnabled()) {
            int i = hand == EnumHandSide.RIGHT ? 1 : -1;
            GlStateManager.translate((float) i * ViewModelChanger.sizeX.getValDouble(), -ViewModelChanger.sizeY.getValDouble() + p_187459_2_ * .6, ViewModelChanger.sizeZ.getValDouble() * -1);

        }
    }
}

 */