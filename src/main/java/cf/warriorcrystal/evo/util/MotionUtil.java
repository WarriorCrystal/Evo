package cf.warriorcrystal.evo.util;

import net.minecraft.client.Minecraft;

public class MotionUtil {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static boolean isMoving() {
        return mc.player.motionX > .00 || mc.player.motionX < -.00 || mc.player.motionZ > .00 || mc.player.motionZ < -.00;
    }

}