package cf.warriorcrystal.evo.module.modules.misc;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.GuiGameOver;

import java.awt.*;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.GuiScreenDisplayedEvent;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.waypoint.Waypoint;

public class DeathWaypoint extends Module {
    public DeathWaypoint() {
        super("DeathWaypoint", Category.MISC);
    }

    @EventHandler
    private Listener<GuiScreenDisplayedEvent> listener = new Listener<>(event -> {
        if (event.getScreen() instanceof GuiGameOver) {
            Evo.getInstance().waypointManager.delWaypoint( Evo.getInstance().waypointManager.getWaypointByName("Last Death"));
            Evo.getInstance().waypointManager.addWaypoint(new Waypoint("Last Death", mc.player.posX, mc.player.posY, mc.player.posZ, Color.RED.getRGB()));
        }
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }

}
