package cf.warriorcrystal.evo.module.modules.chat;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
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
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
