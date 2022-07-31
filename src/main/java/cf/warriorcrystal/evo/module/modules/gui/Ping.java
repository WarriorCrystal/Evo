package cf.warriorcrystal.evo.module.modules.gui;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

public class Ping extends Module {
    public Ping() {
        super("Ping", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;

    public void setup(){
        red = new Setting("PingRed", this, 255, 0, 255, true);
        green = new Setting("PingGreen", this, 255, 0, 255, true);
        blue = new Setting("PingBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(red);
        Evo.getInstance().settingsManager.rSetting(green);
        Evo.getInstance().settingsManager.rSetting(blue);
        Evo.getInstance().settingsManager.rSetting(rainbow = new Setting("pingRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(customFont = new Setting("pingCFont", this, false));
    }

    public int getPing(){
        int p = -1;
        if(mc.player == null || mc.getConnection() == null || mc.getConnection().getPlayerInfo(mc.player.getName()) == null){
            p = -1;
        } else {
            p = mc.getConnection().getPlayerInfo(mc.player.getName()).getResponseTime();
        }
        return p;
    }

    public void onEnable(){
        disable();
    }
}
