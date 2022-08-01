package cf.warriorcrystal.evo.mixin.accessor;

import net.minecraft.util.Timer;
import net.minecraft.client.Minecraft;

public interface IMinecraft {

    Timer getTimer();

    void setRightClickDelayTimer(int delay);

}