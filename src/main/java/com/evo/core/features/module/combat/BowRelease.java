package com.evo.core.features.module.combat;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.core.setting.Setting;
import com.evo.util.network.NetworkUtil;

import net.minecraft.item.ItemBow;
import net.minecraft.network.play.client.CPacketPlayerDigging;

public class BowRelease extends Module {
    public BowRelease() {
        super("BowRelease", Category.COMBAT, "Automatically releases your bow");
    }

    public static final Setting<Integer> ticks = new Setting<>("Ticks", 3, 3, 20);

    @Override
    public void onUpdate() {
        if (mc.player.isHandActive() &&
                mc.player.getActiveItemStack().getItem() instanceof ItemBow &&
                mc.player.getItemInUseMaxCount() >= ticks.getValue()) {

            mc.player.stopActiveHand();
            NetworkUtil.send(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, mc.player.getPosition(), mc.player.getHorizontalFacing()));
        }
    }
}
