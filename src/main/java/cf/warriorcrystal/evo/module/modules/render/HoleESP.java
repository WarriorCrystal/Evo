package cf.warriorcrystal.evo.module.modules.render;

import cf.warriorcrystal.evo.util.*;
import de.Hero.settings.Setting;
import net.minecraft.util.math.BlockPos;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.RenderEvent;
import cf.warriorcrystal.evo.module.Module;

import java.util.List;

public class HoleESP extends Module {

    static Setting doubleHole;
    static Setting radius;
    static Setting rObi;
    static Setting gObi;
    static Setting bObi;
    static Setting rRock;
    static Setting gRock;
    static Setting bRock;
    static Setting alpha;
    static Setting outlineAlpha;
    static Setting width;
    static Setting fruitRender;


    public HoleESP() {
        super("HoleESP", Category.RENDER);
        Evo.getInstance().settingsManager.rSetting(fruitRender = new Setting("FruitRender", this, false));
        Evo.getInstance().settingsManager.rSetting(doubleHole = new Setting("DoubleHole", this, true));
        Evo.getInstance().settingsManager.rSetting(radius = new Setting("Radius", this, 5, 1, 20, false));
        Evo.getInstance().settingsManager.rSetting(rObi = new Setting("RedObi", this, 255, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(gObi = new Setting("GreenObi", this, 26, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(bObi = new Setting("BlueObi", this, 255, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(rRock = new Setting("RedRock", this, 0, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(gRock = new Setting("GreenRock", this, 255, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(bRock = new Setting("BlueRock", this, 0, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(alpha = new Setting("Alpha", this, 60, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(outlineAlpha = new Setting("OutlineAlpha", this, 200, 0, 255, true));
        Evo.getInstance().settingsManager.rSetting(width = new Setting("Width", this, 2, 0, 10, true));
    }




    @Override
    public void onWorldRender(RenderEvent event) {
        if (mc.world == null || mc.player == null) return;
        double x = mc.player.posX;
        double y = mc.player.posY;
        double z = mc.player.posZ;
        BlockPos playerPos = new BlockPos(x, y, z);
        List<BlockPos> blocks = (BlockInteractionHelper.getSphere(playerPos, (float) radius.getValDouble(), (int) radius.getValDouble(), false, true, 0));
        for (BlockPos block : blocks) {
            if (block == null)
                return;
            if (HoleUtil.isBedrockHole(block)) {
                ////EvoTessellator.prepare(7);
                EvoTessellator.drawBoxBottom(block, rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), alpha.getValInt());
                ////EvoTessellator.release();
                EvoTessellator.drawBoundingBoxBottomBlockPos(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                if (fruitRender.getValBoolean()) {
                    EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());

                }
            }
            if (HoleUtil.isObiHole(block)) {
                ////EvoTessellator.prepare(7);
                EvoTessellator.drawBoxBottom(block, rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), alpha.getValInt());
                ////EvoTessellator.release();
                EvoTessellator.drawBoundingBoxBottomBlockPos(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());

            }
            if (doubleHole.getValBoolean()) {
                if (HoleUtil.isBedrockEastHole(block) && HoleUtil.isBedrockWestHole(block.west())) {
                    //EvoTessellator.prepare(7);
                    EvoTessellator.drawBoxBottom(block.west(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), alpha.getValInt());
                    EvoTessellator.drawBoxBottom(block, rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), alpha.getValInt());
                    //EvoTessellator.release();
                    EvoTessellator.drawBoundingBoxBottomBlockPosEast(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    EvoTessellator.drawBoundingBoxBottomBlockPosWest(block.west(), width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block.west(), width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block.west(), width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());

                }
                if (HoleUtil.isBedrockNorthHole(block) && HoleUtil.isBedrockSouthHole(block.south())) {
                    //EvoTessellator.prepare(7);
                    EvoTessellator.drawBoxBottom(block.south(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), alpha.getValInt());
                    EvoTessellator.drawBoxBottom(block, rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), alpha.getValInt());
                    //EvoTessellator.release();
                    EvoTessellator.drawBoundingBoxBottomBlockPosNorth(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    EvoTessellator.drawBoundingBoxBottomBlockPosSouth(block.south(), width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block.south(), width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block.south(), width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());


                }
                if (HoleUtil.isObiEastHole(block) && HoleUtil.isObiWestHole(block.west())) {
                    //EvoTessellator.prepare(7);
                    EvoTessellator.drawBoxBottom(block.west(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), alpha.getValInt());
                    EvoTessellator.drawBoxBottom(block, rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), alpha.getValInt());
                    //EvoTessellator.release();
                    EvoTessellator.drawBoundingBoxBottomBlockPosEast(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    EvoTessellator.drawBoundingBoxBottomBlockPosWest(block.west(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());if (fruitRender.getValBoolean()) if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block.west(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block.west(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                }

                if (HoleUtil.isObiNorthHole(block) && HoleUtil.isObiSouthHole(block.south())) {
                    //EvoTessellator.prepare(7);
                    EvoTessellator.drawBoxBottom(block.south(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), alpha.getValInt());
                    EvoTessellator.drawBoxBottom(block, rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), alpha.getValInt());
                    //EvoTessellator.release();
                    EvoTessellator.drawBoundingBoxBottomBlockPosNorth(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    EvoTessellator.drawBoundingBoxBottomBlockPosSouth(block.south(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block.south(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block.south(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                }
                if (!(HoleUtil.isObiNorthHole(block) && HoleUtil.isObiSouthHole(block.south())) && !(HoleUtil.isObiEastHole(block) && HoleUtil.isObiWestHole(block.west())) && !(HoleUtil.isBedrockNorthHole(block) && HoleUtil.isBedrockSouthHole(block.south())) && !(HoleUtil.isBedrockEastHole(block) && HoleUtil.isBedrockWestHole(block.west()))) {

                    if ((HoleUtil.isObiNorthHole(block) || HoleUtil.isBedrockNorthHole(block)) && (HoleUtil.isObiSouthHole(block.south()) || HoleUtil.isBedrockSouthHole(block.south()))) {
                        //EvoTessellator.prepare(7);
                        EvoTessellator.drawBoxBottom(block.south(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), alpha.getValInt());
                        EvoTessellator.drawBoxBottom(block, rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), alpha.getValInt());
                        //EvoTessellator.release();
                        EvoTessellator.drawBoundingBoxBottomBlockPosNorth(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                        EvoTessellator.drawBoundingBoxBottomBlockPosSouth(block.south(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                        if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                        if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block.south(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                        if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                        if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block.south(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    }

                    if ((HoleUtil.isObiEastHole(block) || HoleUtil.isBedrockEastHole(block)) && (HoleUtil.isObiWestHole(block.west()) || HoleUtil.isBedrockWestHole(block.west()))) {
                        //EvoTessellator.prepare(7);
                        EvoTessellator.drawBoxBottom(block.west(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), alpha.getValInt());
                        EvoTessellator.drawBoxBottom(block, rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), alpha.getValInt());
                        //EvoTessellator.release();
                        EvoTessellator.drawBoundingBoxBottomBlockPosEast(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                        EvoTessellator.drawBoundingBoxBottomBlockPosWest(block.west(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                        if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block, width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                        if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block.west(), width.getValInt(), rRock.getValInt(), gRock.getValInt(), bRock.getValInt(), outlineAlpha.getValInt());
                        if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle(block.west(), width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                        if (fruitRender.getValBoolean()) EvoTessellator.drawBoundingBoxBottomBlockPosXInMiddle2(block, width.getValInt(), rObi.getValInt(), gObi.getValInt(), bObi.getValInt(), outlineAlpha.getValInt());
                    }
                }
            }
        }
    }








    public void onEnable() {
    }

    public void onDisable() {
    }
}