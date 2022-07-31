package cf.warriorcrystal.evo.module.modules.player;

import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.module.Module;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

import net.minecraft.network.play.server.SPacketChat;

public class AntiLog4j extends Module {
    public AntiLog4j() {
        super("AntiLog4j", Category.PLAYER);
    }

    @EventHandler
    private Listener<PacketEvent.Receive> packetReceiveListener = new Listener<>(event -> {
        String text;
        if (event.getPacket() instanceof SPacketChat && ((text = ((SPacketChat)event.getPacket()).getChatComponent().getUnformattedText()).contains("${") || text.contains("$<") || text.contains("$:-") || text.contains("jndi:ldap"))) {
            Command.sendClientMessage("&c[AntiLog4j] &cBlocked message: " + text);
            event.cancel();
        }
    });

}
