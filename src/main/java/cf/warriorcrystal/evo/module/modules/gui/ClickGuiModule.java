package cf.warriorcrystal.evo.module.modules.gui;

import de.Hero.settings.Setting;

import org.lwjgl.input.Keyboard;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.module.ModuleManager;
import cf.warriorcrystal.evo.module.modules.chat.Announcer;

import java.util.ArrayList;

public class ClickGuiModule extends Module {
    public ClickGuiModule INSTANCE;
    public ClickGuiModule() {
        super("ClickGUI", Category.GUI);
        setBind(Keyboard.KEY_Y);
        INSTANCE = this;
    }

    public void setup(){
        ArrayList<String> options = new ArrayList<>();
        options.add("New");
        options.add("JellyLike");
        options.add("f0nzi");
        options.add("Windows");
        Evo.getInstance().settingsManager.rSetting(new Setting("Design", this, "New", options));
        Evo.getInstance().settingsManager.rSetting(new Setting("GuiRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(new Setting("GuiRed", this, 255, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(new Setting("GuiGreen", this, 26, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(new Setting("GuiBlue", this, 42, 0, 255, true));
    }

    public void onEnable(){
        mc.displayGuiScreen(Evo.getInstance().clickGui);
        if(((Announcer)ModuleManager.getModuleByName("Announcer")).clickGui.getValBoolean() && ModuleManager.isModuleEnabled("Announcer") && mc.player != null)
            if(((Announcer)ModuleManager.getModuleByName("Announcer")).clientSide.getValBoolean()){
                Command.sendClientMessage(Announcer.guiMessage);
            } else {
                mc.player.sendChatMessage(Announcer.guiMessage);
            }
        disable();
    }
}
