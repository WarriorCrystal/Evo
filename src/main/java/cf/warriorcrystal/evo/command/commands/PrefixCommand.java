package cf.warriorcrystal.evo.command.commands;

import cf.warriorcrystal.evo.command.Command;

public class PrefixCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"prefix", "setprefix"};
    }

    @Override
    public String getSyntax() {
        return "prefix <character>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        Command.setPrefix(args[0]);
        Command.sendClientMessage("Command prefix set to " + Command.getPrefix());
    }
}
