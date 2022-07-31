package cf.warriorcrystal.evo.module.modules.misc;

import de.Hero.settings.Setting;

import java.text.DecimalFormat;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.TpsUtils;

public class Timer extends Module {
    public static Timer INSTANCE;
    public Timer() {
        super("Timer", Category.MISC);
        INSTANCE = this;
    }

    Setting tpsSync;
    Setting multiplier;

    public void setup(){
        tpsSync = new Setting("timerTpsSync", this, true);
        Evo.getInstance().settingsManager.rSetting(tpsSync);
        multiplier = new Setting("timerMultiplier", this, 5.0, 0.1, 20.0, false);
        Evo.getInstance().settingsManager.rSetting(multiplier);
    }

    public void onUpdate(){
        mc.timer.tickLength = 50f / getMultiplier();
    }

    public void onDisable(){
        mc.timer.tickLength = 50f;
    }

    public float getMultiplier() {
        if (this.isEnabled()) {
            if (tpsSync.getValBoolean()) {
                float f = TpsUtils.getTickRate() / 20 * (float)multiplier.getValDouble();
                if(f < 0.1f) f = 0.1f;
                return f;
            } else {
                return (float)multiplier.getValDouble();
            }
        } else {
            return 1.0f;
        }
    }

    public String getHudInfo(){
        return new DecimalFormat("#0.##").format(getMultiplier());
    }

}
