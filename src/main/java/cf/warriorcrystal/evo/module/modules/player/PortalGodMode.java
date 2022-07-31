package cf.warriorcrystal.evo.module.modules.player;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketConfirmTeleport;

public class PortalGodMode extends Module {
    public PortalGodMode() {
        super("PortalGodmode", Category.PLAYER);
    }

    public void onEnable() {
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable() {
        Evo.EVENT_BUS.unsubscribe(this);
    }

    @EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if(event.getPacket() instanceof CPacketConfirmTeleport)
            event.cancel();
    });
}
