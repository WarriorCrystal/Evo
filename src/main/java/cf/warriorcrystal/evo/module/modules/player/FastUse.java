package cf.warriorcrystal.evo.module.modules.player;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;
import net.minecraft.init.Items;

public class FastUse extends Module {
    public FastUse() {
        super("FastUse", Category.PLAYER);
    }

    Setting xp;
    Setting crystals;
    Setting all;
    Setting breakS;

    public void setup(){
        xp = new Setting( "fuEXP", this, true);
        Evo.getInstance().settingsManager.rSetting(xp);
        crystals = new Setting("fuCrystals", this, true);
        Evo.getInstance().settingsManager.rSetting(crystals);
        all = new Setting("fuEverything", this, false);
        Evo.getInstance().settingsManager.rSetting(all);
        breakS = new Setting("fuFastBreak", this, true);
        Evo.getInstance().settingsManager.rSetting(breakS);
    }

    public void onUpdate() {
        if(xp.getValBoolean()) {
            if (mc.player != null && (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE || mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE)) {
                mc.rightClickDelayTimer = 0;
            }
        }

        if(crystals.getValBoolean()) {
            if (mc.player != null && (mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL)) {
                mc.rightClickDelayTimer = 0;
            }
        }

        if(all.getValBoolean()) {
            mc.rightClickDelayTimer = 0;
        }

        if(breakS.getValBoolean()){
            mc.playerController.blockHitDelay = 0;
        }
    }
}
