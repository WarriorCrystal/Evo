package cf.warriorcrystal.evo.module.modules.gui;

import de.Hero.settings.Setting;

import java.util.ArrayList;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;


public class WelcomerGui extends Module {
    public WelcomerGui() {
        super("Welcome", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting message;
    public Setting customFont;
    ArrayList<String> messages;

    public void setup(){
        messages = new ArrayList<>();
        messages.add("Welcome1");
        messages.add("Welcome2");
        messages.add("Hello1");
        messages.add("Hello2");
        red = new Setting("welRed", this, 255, 0, 255, true);
        green = new Setting("welGreen", this, 255, 0, 255, true);
        blue = new Setting("welBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(red);
        Evo.getInstance().settingsManager.rSetting(green);
        Evo.getInstance().settingsManager.rSetting(blue);
        rainbow = new Setting("welRainbow", this, true);
        Evo.getInstance().settingsManager.rSetting(rainbow);
        Evo.getInstance().settingsManager.rSetting(message = new Setting("welMessage", this, "Welcome1", messages));
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("welCFont", this, false));
    }

    public void onEnable(){
        disable();
    }
}
