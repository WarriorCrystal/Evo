package me.finz0.osiris.module.modules.chat;

import me.finz0.osiris.OsirisMod;
import me.finz0.osiris.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class AutoReply extends Module {
    public AutoReply() {
        super("AutoReply", Category.CHAT);
    }

    private static String reply = "fuck off";

    @EventHandler
    private Listener<ClientChatReceivedEvent> listener = new Listener<>(event ->{
        if( event.getMessage().getUnformattedText().contains("whispers: ") && !event.getMessage().getUnformattedText().startsWith(mc.player.getName())){
            mc.player.sendChatMessage("/r " + reply);
        }
    });

    public static String getReply(){
        return reply;
    }

    public static void setReply(String r){
        reply = r;
    }

    public void onEnable(){
        OsirisMod.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        OsirisMod.EVENT_BUS.unsubscribe(this);
    }
}
