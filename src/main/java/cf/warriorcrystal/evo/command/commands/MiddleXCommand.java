package cf.warriorcrystal.evo.command.commands;

import cf.warriorcrystal.evo.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class MiddleXCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{
                "getmiddlex", "middlex", "getmiddle"
        };
    }

    @Override
    public String getSyntax() {
        return getAlias()[0];
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        Command.sendClientMessage(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() / 2 + "");
    }
}
