package cf.warriorcrystal.evo.module.modules.chat;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

public class ChatSuffix extends Module {
    public ChatSuffix() {
        super("ChatSuffix", Category.CHAT);
    }

    Setting blue;

    public void setup(){
        Evo.getInstance().settingsManager.rSetting(blue = new Setting("csBlue", this, false));
    }

    @EventHandler
    private Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if(event.getPacket() instanceof CPacketChatMessage){
            if(((CPacketChatMessage) event.getPacket()).getMessage().startsWith("/") || ((CPacketChatMessage) event.getPacket()).getMessage().startsWith(Command.getPrefix())) return;
            String old = ((CPacketChatMessage) event.getPacket()).getMessage();
            String suffix = " \u300b\u1d0f\ua731\u026a\u0280\u026a\ua731";
            String s = old + suffix;
            if(blue.getValBoolean()) s = old + "`" + suffix;
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
