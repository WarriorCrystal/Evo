package cf.warriorcrystal.evo.module.modules.chat;

import de.Hero.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

import java.util.ArrayList;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.module.Module;

public class ColorChat extends Module {
    public ColorChat() {
        super("ColorChat", Category.CHAT);
    }

    Setting mode;

    public void setup(){
        ArrayList<String> modes = new ArrayList<>();
        modes.add("Green");
        modes.add("Blue");
        Evo.getInstance().settingsManager.rSetting(mode = new Setting("ccColor", this, "Green", modes));
    }

    @EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if(event.getPacket() instanceof CPacketChatMessage){
            if(((CPacketChatMessage) event.getPacket()).getMessage().startsWith("/") || ((CPacketChatMessage) event.getPacket()).getMessage().startsWith(Command.getPrefix())) return;
            String message = ((CPacketChatMessage) event.getPacket()).getMessage();
            String prefix = "";
            if(mode.getValString().equalsIgnoreCase("Green")) prefix = ">";
            if(mode.getValString().equalsIgnoreCase("Blue")) prefix = "`";
            String s = prefix + message;
            if(s.length() > 255) return;
            ((CPacketChatMessage) event.getPacket()).message = s;
        }
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
