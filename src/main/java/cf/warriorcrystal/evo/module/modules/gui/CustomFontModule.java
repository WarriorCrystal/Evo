package cf.warriorcrystal.evo.module.modules.gui;

import java.util.ArrayList;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class CustomFontModule extends Module {
    public CustomFontModule() {
        super("CustomFont", Category.GUI);
    }

    public Setting Mode;

    public void setup(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Normal");
        options.add("Bold");
        options.add("Italic");
        options.add("ItalicBold");
        Evo.getInstance().settingsManager.rSetting(Mode = new Setting("Font", this, "Normal", options));
    }

    public void onEnable() {
        disable();
    }

}
