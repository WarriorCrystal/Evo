package cf.warriorcrystal.evo.event.events;

import cf.warriorcrystal.evo.event.EvoEvent;
import net.minecraft.util.math.BlockPos;

public class DestroyBlockEvent extends EvoEvent {
    BlockPos pos;
    public DestroyBlockEvent(BlockPos blockPos){
        super();
        pos = blockPos;
    }

    public BlockPos getBlockPos(){
        return pos;
    }
}
