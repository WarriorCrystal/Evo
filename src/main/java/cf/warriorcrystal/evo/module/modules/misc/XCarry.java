package cf.warriorcrystal.evo.module.modules.misc;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.*;

public class XCarry extends Module {
    public XCarry() {
        super("XCarry", Category.MISC);
    }

    @EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if(event.getPacket() instanceof CPacketCloseWindow){
            if(((CPacketCloseWindow)event.getPacket()).windowId == mc.player.inventoryContainer.windowId){
                event.cancel();
            }
        }
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
