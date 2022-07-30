package com.evo.util.entity.inventory.task;

import com.evo.util.entity.inventory.InventoryUtil;
import com.evo.util.internal.Wrapper;

import net.minecraft.inventory.ClickType;

public class Task implements Wrapper {
    private final int slot;
    private final boolean update;

    public Task(int slot, boolean transform) {
        this(slot, transform, true);
    }

    public Task(int slot, boolean transform, boolean update) {
        this.slot = transform ? InventoryUtil.toClickableSlot(slot) : slot;
        this.update = update;
    }

    public void runTask() {
        getEvo().getInventoryManager().click(slot, ClickType.PICKUP, false);

        if (update) {
            mc.playerController.updateController();
        }
    }

    public int getSlot() {
        return slot;
    }

    public boolean isUpdate() {
        return update;
    }
}
