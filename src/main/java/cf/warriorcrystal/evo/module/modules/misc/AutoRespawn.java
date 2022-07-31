package cf.warriorcrystal.evo.module.modules.misc;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.event.events.GuiScreenDisplayedEvent;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.GuiGameOver;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", Category.MISC);
    }

    Setting coords;

    public void setup(){
        Evo.getInstance().settingsManager.rSetting(coords = new Setting("DeathCoords", this, true));
    }

    @EventHandler
    private Listener<GuiScreenDisplayedEvent> listener = new Listener<>(event -> {
        if(event.getScreen() instanceof GuiGameOver) {
            if(coords.getValBoolean())
                Command.sendClientMessage(String.format("You died at x%d y%d z%d", (int)mc.player.posX, (int)mc.player.posY, (int)mc.player.posZ));
            if(mc.player != null)
                mc.player.respawnPlayer();
            mc.displayGuiScreen(null);
        }
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
