package cf.warriorcrystal.evo.module.modules.chat;

import de.Hero.settings.Setting;

import java.util.ArrayList;
import java.util.List;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.module.Module;

public class Spammer extends Module {
    public Spammer() {
        super("Spammer", Category.CHAT);
        text = new ArrayList<>();
    }

    public static List<String> text;
    int waitCounter;
    Setting delay;
    int i = -1;

    public void setup(){
        Evo.getInstance().settingsManager.rSetting(delay = new Setting("SpammerDelay", this, 5, 1, 100, true));
    }

    public void onUpdate(){
        if(text.size() <= 0 || text.isEmpty()){
            Command.sendClientMessage("Spammer list empty, disabling");
            disable();
        }
        if (waitCounter < delay.getValDouble() * 100) {
            waitCounter++;
            return;
        } else {
            waitCounter = 0;
        }
        i++;
        if(!(i + 1 > text.size()))
            mc.player.sendChatMessage(text.get(i));
        else
            i = -1;

    }
}
