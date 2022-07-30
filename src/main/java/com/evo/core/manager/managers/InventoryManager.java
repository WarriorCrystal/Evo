package com.evo.core.manager.managers;

import com.evo.core.events.PacketEvent;
import com.evo.util.entity.inventory.InventoryUtil;
import com.evo.util.entity.inventory.Swap;
import com.evo.util.internal.Wrapper;

import net.minecraft.inventory.ClickType;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InventoryManager implements Wrapper {
    private int serverSlot = -1;

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketHeldItemChange) {
            serverSlot = ((CPacketHeldItemChange) event.getPacket()).getSlotId();
        }
    }

    public void swap(int slot, Swap swap) {
        if (swap.equals(Swap.NONE)) {
            return;
        }

        mc.player.connection.sendPacket(new CPacketHeldItemChange(slot));
        if (swap.equals(Swap.CLIENT)) {
            mc.player.inventory.currentItem = slot;
        }

        mc.playerController.updateController();
    }

    public void click(int slot, ClickType type, boolean transform) {
        mc.playerController.windowClick(0, transform ? InventoryUtil.toClickableSlot(slot) : slot, 0, type, mc.player);
    }

    public int getServerSlot() {
        return serverSlot;
    }
}
