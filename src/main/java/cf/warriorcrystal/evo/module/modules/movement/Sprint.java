package cf.warriorcrystal.evo.module.modules.movement;

import cf.warriorcrystal.evo.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    public void onUpdate(){
        if(mc.player.moveForward > 0 && !mc.player.isSprinting()){
            mc.player.setSprinting(true);
        }
    }
}
