package cf.warriorcrystal.evo.command.commands;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.module.modules.chat.Spammer;

public class LoadSpammerCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"loadspammer"};
    }

    @Override
    public String getSyntax() {
        return "loadspammer";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        Spammer.text.clear();
        Evo.getInstance().configUtils.loadSpammer();
        Command.sendClientMessage("Loaded Spammer File");
    }
}
