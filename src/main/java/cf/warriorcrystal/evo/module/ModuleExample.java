package cf.warriorcrystal.evo.module;

import java.util.ArrayList;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import de.Hero.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class ModuleExample extends Module {
    public ModuleExample() {
        super("ModuleExample", Category.COMBAT);
    }

    Setting exampleMode;
    Setting exampleBoolean;
    Setting exampleNumber;

    public void setup(){
        ArrayList<String> options = new ArrayList<>();
        options.add("One");
        options.add("Two");
        options.add("Three");
        options.add("Four");
        exampleMode = new Setting("emExampleMode", this, "Two", options);
        exampleBoolean = new Setting("emExampleBoolean", this, false);
        exampleNumber = new Setting("exmExampleNumber", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(exampleMode);
        Evo.getInstance().settingsManager.rSetting(exampleBoolean);
        Evo.getInstance().settingsManager.rSetting(exampleNumber);
    }

    
    public void onUpdate() {

    }

    public void onEnable() {
        Evo.EVENT_BUS.subscribe(this);
    }
    
    public void onDisable() {
        Evo.EVENT_BUS.unsubscribe(this);
    }

    @EventHandler
    private Listener<PacketEvent.Send> packetSendListener = new Listener<>(event -> {

    });

    @EventHandler
    private Listener<PacketEvent.Receive> packetReceiveListener = new Listener<>(event -> {

    });

}
