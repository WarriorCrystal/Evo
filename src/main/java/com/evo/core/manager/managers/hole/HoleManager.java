package com.evo.core.manager.managers.hole;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

import com.evo.core.manager.managers.hole.impl.DoubleHole;
import com.evo.core.manager.managers.hole.impl.Hole;
import com.evo.core.manager.managers.hole.impl.Type;
import com.evo.util.entity.player.LocalPlayerUtil;
import com.evo.util.internal.Wrapper;
import com.evo.util.internal.timing.Format;
import com.evo.util.internal.timing.Timer;
import com.evo.util.world.block.BlockUtil;

public class HoleManager implements Wrapper {
    private final ArrayList<Hole> holes = new ArrayList<>();
    private final Timer timer = new Timer();

    public void onUpdate() {
        if (timer.passed(200L, Format.MS)) {
            timer.reset();
            holes.clear();

            lookup:
            for (BlockPos pos : BlockUtil.getSphere(LocalPlayerUtil.getLocalPosition(), 8)) {
                if (!mc.world.isAirBlock(pos) || !BlockUtil.isHeadspaceOpen(pos, 2)) {
                    return;
                }

                if (pos.getY() == 0) {
                    holes.add(new Hole(Type.VOID, pos, false));
                } else {
                    Type type = Type.SINGLE;
                    int safety = 0;

                    for (EnumFacing facing : EnumFacing.values()) {
                        if (facing.equals(EnumFacing.UP)) {
                            continue;
                        }

                        BlockPos expansion = pos.offset(facing);
                        Block block = BlockUtil.getBlockFromPos(expansion);

                        if (BlockUtil.EXPLOSION_RESISTANT_BLOCKS.contains(block)) {
                            if (block.equals(Blocks.BEDROCK)) {
                                ++safety;
                            }
                        } else if (block.equals(Blocks.AIR)) {

                        } else {
                            continue lookup;
                        }
                    }

                    holes.add(new Hole(type, pos, safety == 5));
                }
            }
        }
    }

    public boolean isInHole() {
        return holes.stream().anyMatch((hole) -> {
            AxisAlignedBB box = new AxisAlignedBB(hole.getPos());
            if (hole instanceof DoubleHole) {
                box = ((DoubleHole) hole).getBox();
            }

            return box.intersects(mc.player.getEntityBoundingBox());
        });
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }
}
