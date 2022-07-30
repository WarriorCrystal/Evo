package com.evo.core.features.module.other;

import com.evo.core.events.PacketEvent;
import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.util.internal.ChatUtil;

import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiLog4j extends Module {

    public AntiLog4j() {
        super("AntiLog4j", Category.OTHER, "Prevents the log4j exploit");
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Receive event) {
        String text;
        if (event.getPacket() instanceof SPacketChat && ((text = ((SPacketChat)event.getPacket()).getChatComponent().getUnformattedText()).contains("${") || text.contains("$<") || text.contains("$:-") || text.contains("jndi:ldap"))) {
            ChatUtil.sendPrefixed("&c[AntiLog4j] &cBlocked message: " + text);
            event.setCanceled(true);
        }
    }

}
