package com.evo.core.features.module.combat;

import com.evo.asm.duck.IEntityPlayer;
import com.evo.core.events.DeathEvent;
import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.core.manager.managers.relationships.impl.Status;
import com.evo.core.setting.Setting;
import com.evo.util.internal.ChatUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ScreenShotHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoClip extends Module {
    public AutoClip() {
        super("AutoClip", Category.COMBAT, "Automatically clips people on 2b2tpvp (cringe)");
    }

    public static final Setting<Mode> mode = new Setting<>("Mode", Mode.ALL);
    public static final Setting<Boolean> self = new Setting<>("Self", false);

    @SubscribeEvent
    public void onDeath(DeathEvent event) {
        // @todo: check for Aura, AutoCrystal, AutoTNT, AutoMinecart targets
        EntityPlayer player = event.getPlayer();

        if (player.equals(mc.player) && !self.getValue()) {
            return;
        }

        if (mode.getValue().equals(Mode.ENEMIES) && !((IEntityPlayer) player).isRelationship(Status.ENEMY)) {
            return;
        }

        if (mc.player.getDistance(player) <= 50.0f) {
            ScreenShotHelper.saveScreenshot(mc.gameDir, mc.displayWidth, mc.displayHeight, mc.framebuffer);
            ChatUtil.sendPrefixed("Clipped " + player.getName() + "!");
        }
    }

    public enum Mode {
        /**
         * Clips everyone
         */
        ALL,

        /**
         * Only clips your enemies
         */
        ENEMIES,
    }
}
