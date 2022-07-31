package cf.warriorcrystal.evo.module.modules.movement;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraftforge.client.event.InputUpdateEvent;

public class NoSlow extends Module {
    public NoSlow() {
        super("NoSlow", Category.MOVEMENT);
    }

    @EventHandler
    private Listener<InputUpdateEvent> eventListener = new Listener<>(event -> {
        if (mc.player.isHandActive() && !mc.player.isRiding()) {
            event.getMovementInput().moveStrafe *= 5;
            event.getMovementInput().moveForward *= 5;
        }
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
