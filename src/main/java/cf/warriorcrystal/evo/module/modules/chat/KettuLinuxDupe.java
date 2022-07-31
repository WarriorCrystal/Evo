package cf.warriorcrystal.evo.module.modules.chat;

import java.util.Random;

import cf.warriorcrystal.evo.module.Module;

public class KettuLinuxDupe extends Module {
    public KettuLinuxDupe() {
        super("KettuLinuxDupe", Category.CHAT);
    }

    public void onEnable(){
        if(mc.player != null)
            mc.player.sendChatMessage("I just used the Kettu Linux Dupe and got " + new Random().nextInt(15) + " shulkers! Powered by Evo\u2122");
        disable();
    }
}
