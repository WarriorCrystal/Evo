package com.evo.asm.impl.render.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.evo.core.features.module.other.Chat;

@Mixin(GuiNewChat.class)
public class MixinGuiNewChat {
    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    public void drawRectHook(int left, int top, int right, int bottom, int color) {
        Gui.drawRect(left, top, right, bottom, Chat.INSTANCE.isToggled() && Chat.clear.getValue() ? 0 : color);
    }
}
