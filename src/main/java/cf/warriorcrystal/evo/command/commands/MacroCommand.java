package cf.warriorcrystal.evo.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.macro.Macro;

import org.lwjgl.input.Keyboard;

public class MacroCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"macro", "macros"};
    }

    @Override
    public String getSyntax() {
        return "macro <add | del> <key> <value>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        if(args[0].equalsIgnoreCase("add")){
            Evo.getInstance().macroManager.delMacro(Evo.getInstance().macroManager.getMacroByKey(Keyboard.getKeyIndex(args[1])));
            Evo.getInstance().macroManager.addMacro(new Macro(Keyboard.getKeyIndex(args[1].toUpperCase()), args[2].replace("_", " ")));
            Command.sendClientMessage(ChatFormatting.GREEN + "Added" + ChatFormatting.GRAY + " macro for key \"" + args[1].toUpperCase() + "\" with value \"" + args[2].replace("_", " ") + "\".");
        }
        if(args[0].equalsIgnoreCase("del")){
            if(Evo.getInstance().macroManager.getMacros().contains(Evo.getInstance().macroManager.getMacroByKey(Keyboard.getKeyIndex(args[1].toUpperCase())))) {
                Evo.getInstance().macroManager.delMacro(Evo.getInstance().macroManager.getMacroByKey(Keyboard.getKeyIndex(args[1].toUpperCase())));
                Command.sendClientMessage(ChatFormatting.RED + "Removed " + ChatFormatting.GRAY + "macro for key \"" + args[1].toUpperCase() + "\".");
            }else {
                Command.sendClientMessage(ChatFormatting.RED + "That macro doesn't exist!");
            }
        }
    }
}
