package cf.warriorcrystal.evo.mixin.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.GuiScreenDisplayedEvent;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "displayGuiScreen", at = @At("HEAD"), cancellable = true)
    public void displayGuiScreen(GuiScreen guiScreenIn, CallbackInfo info) {
        try {
            GuiScreenDisplayedEvent screenEvent = new GuiScreenDisplayedEvent(guiScreenIn);
            Evo.EVENT_BUS.post(screenEvent);
            guiScreenIn = screenEvent.getScreen();
        } catch (Exception e){}
    }
}
