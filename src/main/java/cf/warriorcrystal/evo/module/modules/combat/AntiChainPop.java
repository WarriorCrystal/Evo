package cf.warriorcrystal.evo.module.modules.combat;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.*;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

//I have no idea if this actually does anything but whatever
public class AntiChainPop extends Module {
    public AntiChainPop() {
        super("AntiChainPop", Category.COMBAT);
    }

    boolean shouldCancel = false;

    @EventHandler
    private Listener<LivingDamageEvent> listener = new Listener<>(event -> {
        if(mc.player != null || event.getEntity() != null) return;
        float hp = mc.player.getHealth() + mc.player.getAbsorptionAmount();
        if(event.getEntity() == mc.player && event.getAmount() >= hp){
            mc.player.connection.sendPacket(new CPacketKeepAlive());
            shouldCancel = true;
        } else {
            shouldCancel = false;
        }
    });

    @EventHandler
    private Listener<PacketEvent.Send> listener2 = new Listener<>(event -> {
        Packet packet = event.getPacket();
        if (packet instanceof CPacketChatMessage || packet instanceof CPacketConfirmTeleport || packet instanceof CPacketKeepAlive || packet instanceof CPacketTabComplete || packet instanceof CPacketClientStatus || packet instanceof CPacketHeldItemChange)
            return;
        if(shouldCancel){
            event.cancel();
            shouldCancel = false;
        }
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
        shouldCancel = false;
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
        shouldCancel = false;
    }

}
