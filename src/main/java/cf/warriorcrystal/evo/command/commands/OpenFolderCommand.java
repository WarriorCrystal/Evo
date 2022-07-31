package cf.warriorcrystal.evo.command.commands;

import java.awt.*;
import java.io.File;

import cf.warriorcrystal.evo.command.Command;

public class OpenFolderCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"openfolder", "folder"};
    }

    @Override
    public String getSyntax() {
        return "openfolder";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        try {
            Desktop.getDesktop().open(new File("Evo"));
        } catch(Exception e){sendClientMessage("Error: " + e.getMessage());}
    }
}
