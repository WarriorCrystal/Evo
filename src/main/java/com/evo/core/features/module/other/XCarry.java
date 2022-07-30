package com.evo.core.features.module.other;

import com.evo.core.events.PacketEvent;
import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.core.setting.Setting;
import com.evo.util.network.NetworkUtil;

import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class XCarry extends Module {
    public XCarry() {
        super("XCarry", Category.OTHER, "Allows you to carry items in your crafting slots");
    }

    public static final Setting<Boolean> disableClose = new Setting<>("DisableClose", false);

    @Override
    protected void onDisable() {
        if (disableClose.getValue()) {
            NetworkUtil.sendNoEvent(new CPacketCloseWindow(0));
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketCloseWindow) {
            event.setCanceled(true);
        }
    }
}
