package cf.warriorcrystal.evo.command.commands;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;

public class LoadAnnouncerCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{
                "loadannouncer"
        };
    }

    @Override
    public String getSyntax() {
        return "loadannouncer";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        Evo.getInstance().configUtils.loadAnnouncer();
        sendClientMessage("Loaded Announcer file");
    }
}
