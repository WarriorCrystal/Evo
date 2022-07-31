package cf.warriorcrystal.evo.module.modules.chat;

import com.mojang.realmsclient.gui.ChatFormatting;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.friends.Friends;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BetterChat extends Module {
    public BetterChat() {
        super("BetterChat", Category.CHAT);
    }

    public Setting clearBkg;
    Setting timeStamps;
    Setting nameHighlight;
    Setting friendHighlight;

    public void setup(){
        clearBkg = new Setting("Clear", this, true);
        Evo.getInstance().settingsManager.rSetting(clearBkg);
        timeStamps = new Setting("TimeStamps", this, true);
        Evo.getInstance().settingsManager.rSetting(timeStamps);
        Evo.getInstance().settingsManager.rSetting(nameHighlight = new Setting("NameHighlight", this, false));
        Evo.getInstance().settingsManager.rSetting(friendHighlight = new Setting("FriendHighlight", this, false));
    }

    @EventHandler
    private Listener<ClientChatReceivedEvent> chatReceivedEventListener = new Listener<>(event -> {
        if(mc.player == null) return;
        String name = mc.player.getName().toLowerCase();
        if(friendHighlight.getValBoolean()){
            if(!event.getMessage().getUnformattedText().startsWith("<"+mc.player.getName()+">")){
                Friends.getFriends().forEach(f -> {
                    if(event.getMessage().getUnformattedText().contains(f.getName())){
                        event.getMessage().setStyle(event.getMessage().getStyle().setColor(TextFormatting.AQUA));
                    }
                });
            }
        }
        if(nameHighlight.getValBoolean()){
            String s = ChatFormatting.GOLD + "" + ChatFormatting.BOLD + mc.player.getName() + ChatFormatting.RESET;
            Style style = event.getMessage().getStyle();
            if(!event.getMessage().getUnformattedText().startsWith("<"+mc.player.getName()+">") && event.getMessage().getUnformattedText().toLowerCase().contains(name)) {
                event.getMessage().getStyle().setParentStyle(style.setBold(true).setColor(TextFormatting.GOLD));
            }
        }
        if(timeStamps.getValBoolean()) {
            String date = new SimpleDateFormat("k:mm").format(new Date());
            TextComponentString newMsg = new TextComponentString(ChatFormatting.GRAY + "[" + date + "]" + ChatFormatting.RESET);
            event.setMessage(newMsg.appendSibling(event.getMessage()));
        }
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
