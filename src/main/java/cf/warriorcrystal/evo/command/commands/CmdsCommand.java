package cf.warriorcrystal.evo.command.commands;

import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.command.CommandManager;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

public class CmdsCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"commands", "cmds"};
    }

    @Override
    public String getSyntax() {
        return "commands";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        final int size = CommandManager.getCommands().size();

        final TextComponentString msg = new TextComponentString("\2477Commands: ");

        for (int i = 0; i < size; i++) {
            final Command c = CommandManager.getCommands().get(i);
            if (c != null) {
                msg.appendSibling(new TextComponentString(c.getAlias()[0] + ((i == size - 1) ? "" : ", "))
                        .setStyle(new Style()
                                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(c.getSyntax())))));
            }
        }

        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(msg);
    }
}
