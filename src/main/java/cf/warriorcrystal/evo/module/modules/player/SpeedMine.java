package cf.warriorcrystal.evo.module.modules.player;

import cf.warriorcrystal.evo.module.Module;

public class SpeedMine extends Module {
    public SpeedMine() {
        super("SpeedMine", Category.PLAYER);
    }

    public void onUpdate(){
        if(mc.playerController.curBlockDamageMP >= 0)
            mc.playerController.curBlockDamageMP = 1;
    }
}
