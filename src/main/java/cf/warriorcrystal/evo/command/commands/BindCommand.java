package cf.warriorcrystal.evo.command.commands;

import org.lwjgl.input.Keyboard;

import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.module.ModuleManager;

public class BindCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"bind", "b"};
    }

    @Override
    public String getSyntax() {
        return "bind <Module> <Key>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        int key = Keyboard.getKeyIndex(args[1].toUpperCase());
        ModuleManager.getModules().forEach(m ->{
            if(args[0].equalsIgnoreCase(m.getName())){
                m.setBind(key);
                Command.sendClientMessage(args[0] + " bound to " + args[1].toUpperCase());
            }
        });
    }
}
