package cf.warriorcrystal.evo.module.modules.player;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.RenderEvent;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.BlockInteractionHelper;
import cf.warriorcrystal.evo.util.EvoTessellator;
import de.Hero.settings.Setting;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

public class Scaffold extends Module {
    int playerHotbarSlot = -1;
    int lastHotbarSlot = -1;

    Setting placeMode;

    public Scaffold() {
        super("Scaffold", Category.PLAYER);
        ArrayList<String> placeModes = new ArrayList<>();
        placeModes.add("Old");
        placeModes.add("New");
        Evo.getInstance().settingsManager.rSetting(placeMode = new Setting("PlaceModes", this, "New", placeModes));

    }

    @Override
    public void onUpdate() {
        BlockPos below = new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ);
        BlockPos belowSouth = new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ - 1);
        BlockPos belowNorth = new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ + 1);
        BlockPos belowEast = new BlockPos(mc.player.posX - 1, mc.player.posY - 1, mc.player.posZ);
        BlockPos belowWest = new BlockPos(mc.player.posX + 1, mc.player.posY - 1, mc.player.posZ);
        if (placeMode.getValString().equalsIgnoreCase("new")) {
            findDirectionLookingPlace();
            if (mc.player.motionX == 0 && mc.player.motionZ == 0) {
                if (mc.gameSettings.keyBindJump.isKeyDown()) {


                }
            }
        } else {
            // south -z
            // north +z
            //east - x
            // west +x
            int blockSlot = this.findBlockInHotbar();
            if (blockSlot > 1) {
                if (blockSlot == -1) {

                }
                if (this.lastHotbarSlot != blockSlot) {
                    mc.player.inventory.currentItem = blockSlot;
                    this.lastHotbarSlot = blockSlot;
                }

                if (mc.world.getBlockState(below).getBlock().canPlaceBlockAt(mc.world, below)) {
                    BlockInteractionHelper.placeBlockScaffold(below);
                }
            }
        }

    }

    @Override
    public void onWorldRender(RenderEvent event) {
        BlockPos below = new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ);
        BlockPos belowSouth = new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ - 1);
        BlockPos belowNorth = new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ + 1);
        BlockPos belowEast = new BlockPos(mc.player.posX - 1, mc.player.posY - 1, mc.player.posZ);
        BlockPos belowWest = new BlockPos(mc.player.posX + 1, mc.player.posY - 1, mc.player.posZ);

        EvoTessellator.drawBoundingBoxBlockPos(below, 1, 81, 205, 159, 255);
    }

    @Override
    public void onEnable() {
        this.playerHotbarSlot = mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
    }

    @Override
    public void onDisable() {
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            mc.player.inventory.currentItem = this.playerHotbarSlot;
        }
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;


    }


    private int findBlockInHotbar() {

        int slot = -1;
        for (int i = 0; i < 9; i++) {


            ItemStack stack = mc.player.inventory.getStackInSlot(i);

            if (stack == ItemStack.EMPTY || !(stack.getItem() instanceof ItemBlock)) {
                continue;
            }
            slot = i;
            break;

        }

        return slot;

    }

    private void findDirectionLookingPlace() {
        BlockPos below = new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ);
        BlockPos belowSouth = new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ - 1);
        BlockPos belowNorth = new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ + 1);
        BlockPos belowEast = new BlockPos(mc.player.posX - 1, mc.player.posY - 1, mc.player.posZ);
        BlockPos belowWest = new BlockPos(mc.player.posX + 1, mc.player.posY - 1, mc.player.posZ);
        int var24 = MathHelper.floor((double) (mc.player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        //if (var24 == 2) {
        //mc.player.sendChatMessage("Your Looking North");
        //BlockInteractionHelper.placeBlockScaffold(belowNorth);
        if (mc.world.getBlockState(belowSouth).getBlock().canPlaceBlockAt(mc.world, belowSouth)) {
            if (mc.player.motionZ < -.05) {
                BlockInteractionHelper.placeBlockScaffold(belowSouth);
            }
            //}

        }

        //if (var24 == 1) {
        //mc.player.sendChatMessage("Your Looking West");
        if (mc.world.getBlockState(belowEast).getBlock().canPlaceBlockAt(mc.world, belowEast)) {
            if (mc.player.motionX < -.05) {
                BlockInteractionHelper.placeBlockScaffold(belowEast);
                // }
            }
        }
        if (var24 == 3) {
            //mc.player.sendChatMessage("Your Looking East");
            if (mc.world.getBlockState(belowWest).getBlock().canPlaceBlockAt(mc.world, belowWest)) {
                if (mc.player.motionX > .05) {

                    BlockInteractionHelper.placeBlockScaffold(belowWest);
                }
            }
        }
        // if (var24 == 0) {
        //mc.player.sendChatMessage("Your Looking South");
        if (mc.world.getBlockState(belowNorth).getBlock().canPlaceBlockAt(mc.world, belowNorth)) {
            if (mc.player.motionZ > .05) {
                BlockInteractionHelper.placeBlockScaffold(belowNorth);

                // }//
            }
        }
        if (mc.player.motionX == 0 && mc.player.motionZ == 0) {
            if (mc.world.getBlockState(below).getBlock().canPlaceBlockAt(mc.world, below)) {
                if (mc.gameSettings.keyBindJump.isKeyDown()) {
                    mc.player.motionY = .3683;
                    BlockInteractionHelper.placeBlockScaffold(below);
                }
            }
        }
    }
}