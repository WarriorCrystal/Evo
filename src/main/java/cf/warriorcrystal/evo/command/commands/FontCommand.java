package cf.warriorcrystal.evo.command.commands;

import java.awt.*;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.util.font.CFontRenderer;

public class FontCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{
                "font", "setfont"
        };
    }

    @Override
    public String getSyntax() {
        return "font <Name> <Size>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        String font = args[0].replace("_", " ");
        int size = Integer.parseInt(args[1]);
        Evo.fontRenderer = new CFontRenderer(new Font(font, Font.PLAIN, size), true, false);
        Evo.fontRenderer.setFontName(font);
        Evo.fontRenderer.setFontSize(size);
    }
}
