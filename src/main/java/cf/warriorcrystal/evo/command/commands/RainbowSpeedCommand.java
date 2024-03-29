package cf.warriorcrystal.evo.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.event.EventProcessor;

public class RainbowSpeedCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"rainbowspeed", "rainbow"};
    }

    @Override
    public String getSyntax() {
        return "rainbowspeed <speed>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        if(args.length == 1){
            int i = Integer.parseInt(args[0]);
            if(i <= 0) {
                EventProcessor.INSTANCE.setRainbowSpeed(0);
            } else {
                EventProcessor.INSTANCE.setRainbowSpeed(i);
            }
            Command.sendClientMessage("Rainbow speed set to " + i);
        } else {
            Command.sendClientMessage(ChatFormatting.RED + this.getSyntax());
        }
    }
}
