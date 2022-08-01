package cf.warriorcrystal.evo.module.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntityStatus;

import java.util.concurrent.ConcurrentHashMap;

public class TotemPopCounter extends Module {
    public TotemPopCounter() {
        super("TotemPopCounter", Category.MISC);
    }

    public ConcurrentHashMap<EntityPlayer, Integer> popMap = new ConcurrentHashMap<>();

    ConcurrentHashMap<Entity, Integer> players;

    @EventHandler
    private Listener<PacketEvent.Receive> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof SPacketEntityStatus) {
            SPacketEntityStatus packet = (SPacketEntityStatus) event.getPacket();
            if (packet.getOpCode() == 35 && packet.getEntity(mc.world) instanceof EntityPlayer) {
                EntityPlayer entity = (EntityPlayer) packet.getEntity(mc.world);
                int pops = popMap.getOrDefault(entity, 0) + 1;
                Command.sendClientMessage(entity.getName() + " popped " + ChatFormatting.RED + pops + ChatFormatting.RESET + " time/s");
                popMap.put(entity, pops);
            }
        }
    });

    public void onUpdate(){
        for (EntityPlayer player : mc.world.playerEntities) {
            if (player.getHealth() == 0.0f  && popMap.containsKey(player)) {
                Command.sendClientMessage(player.getName() + ChatFormatting.RED + " died");
                popMap.remove(player);
            }
        }
    }

    public void onEnable(){
        players = new ConcurrentHashMap<>();
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
