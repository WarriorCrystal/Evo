package cf.warriorcrystal.evo.module.modules.chat;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.event.events.PlayerJoinEvent;
import cf.warriorcrystal.evo.event.events.PlayerLeaveEvent;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class Welcomer extends Module {
    public Welcomer() {
        super("Welcomer", Category.CHAT);
        Evo.getInstance().settingsManager.rSetting(publicS = new Setting("wPublic", this, false));
    }
    Setting publicS;

    @EventHandler
    private Listener<PlayerJoinEvent> listener1 = new Listener<>(event -> {
        if(publicS.getValBoolean()) mc.player.sendChatMessage(event.getName() + " joined the game");
        else Command.sendClientMessage(event.getName() + " joined the game");
    });

    @EventHandler
    private Listener<PlayerLeaveEvent> listener2 = new Listener<>(event -> {
        if(publicS.getValBoolean()) mc.player.sendChatMessage(event.getName() + " left the game");
        else Command.sendClientMessage(event.getName() + " left the game");
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
