package cf.warriorcrystal.evo.command;

import com.mojang.realmsclient.gui.ChatFormatting;

import cf.warriorcrystal.evo.module.modules.gui.NotificationsHud;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

import java.awt.*;

public abstract class Command {
    static Minecraft mc = Minecraft.getMinecraft();
    public static String prefix = "-";
    public abstract String[] getAlias();
    public abstract String getSyntax();
    public abstract void onCommand(String command, String[] args) throws Exception;

    public static boolean MsgWaterMark = true;
    public static ChatFormatting cf = ChatFormatting.RESET;

    public static void sendClientMessage(String message){
            NotificationsHud.addMessage(new TextComponentString(cf + message));
        if(MsgWaterMark)
            mc.player.sendMessage(new TextComponentString(ChatFormatting.AQUA + "" + ChatFormatting.BOLD + " Evo " + ChatFormatting.GRAY + ">> " + ChatFormatting.RESET + message));
        else
            mc.player.sendMessage(new TextComponentString(cf + message));
    }

    public static Color getColorFromChatFormatting(ChatFormatting cf){
        if(cf == ChatFormatting.BLACK) return Color.BLACK;
        if(cf == ChatFormatting.GRAY) return  Color.GRAY;
        if(cf == ChatFormatting.AQUA) return Color.CYAN;
        if(cf == ChatFormatting.BLUE || cf == ChatFormatting.DARK_BLUE || cf == ChatFormatting.DARK_AQUA) return Color.BLUE;
        if(cf == ChatFormatting.DARK_GRAY) return Color.DARK_GRAY;
        if(cf == ChatFormatting.DARK_GREEN || cf == ChatFormatting.GREEN) return Color.GREEN;
        if(cf == ChatFormatting.DARK_PURPLE) return Color.MAGENTA;
        if(cf == ChatFormatting.RED || cf == ChatFormatting.DARK_RED) return Color.RED;
        if(cf == ChatFormatting.LIGHT_PURPLE) return Color.PINK;
        if(cf == ChatFormatting.YELLOW) return Color.YELLOW;
        if(cf == ChatFormatting.GOLD) return Color.ORANGE;
        return Color.WHITE;
    }

    public static void sendRawMessage(String message){
        mc.player.sendMessage(new TextComponentString(message));
    }

    public static String getPrefix(){
        return prefix;
    }

    public static void setPrefix(String p){
        prefix = p;
    }

}
