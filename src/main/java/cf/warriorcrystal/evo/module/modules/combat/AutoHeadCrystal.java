package cf.warriorcrystal.evo.module.modules.combat;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.friends.Friends;
import cf.warriorcrystal.evo.mixin.accessor.IPlayerControllerMP;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.BlockInteractionHelper;
import cf.warriorcrystal.evo.util.InventoryUtil;
import de.Hero.settings.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class AutoHeadCrystal extends Module {

    private EntityPlayer closestTarget;

    int delay = 0;
    boolean blockAboveHead = false;
    boolean placedCrystal = false;
    boolean minedBlock = false;
    boolean brokeCrystal = false;

    Setting delayS;

    public AutoHeadCrystal() {
        super("AutoHeadCrystal", Category.COMBAT);
        Evo.getInstance().settingsManager.rSetting(delayS = new Setting("Delay", this, 1, 0, 20, true));

    }

    public void onUpdate() {
        delay++;
        if (closestTarget == null) return;
        BlockPos enemyPos = new BlockPos(closestTarget.posX, closestTarget.posY, closestTarget.posZ);
        if (delay > delayS.getValInt() && blockAboveHead && !placedCrystal && !minedBlock && !brokeCrystal) {
            if (InventoryUtil.findItemInHotbar(Items.END_CRYSTAL) == -1) {
                this.disable();
            } else {
                mc.player.inventory.currentItem = InventoryUtil.findItemInHotbar(Items.END_CRYSTAL);
                mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(enemyPos.up(2), EnumFacing.SOUTH, EnumHand.OFF_HAND, 0, 0, 0));
                placedCrystal = true;
            }
            delay = 0;
        }

        if (delay > delayS.getValInt() && blockAboveHead && placedCrystal && !minedBlock && !brokeCrystal) {
            if (InventoryUtil.getItems(Items.DIAMOND_PICKAXE) == -1) {
                this.disable();
            } else {
                mc.player.inventory.currentItem = InventoryUtil.findItemInHotbar(Items.DIAMOND_PICKAXE);
                ((IPlayerControllerMP) mc.playerController).setCurBlockDamageMP(.9f);
                mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, enemyPos.up(2), EnumFacing.SOUTH));
                minedBlock = true;
            }
            delay = 0;
        }
        if (delay > 10 && blockAboveHead && placedCrystal && minedBlock && !brokeCrystal) {
            for (Entity entity : mc.world.loadedEntityList) {
                if (entity instanceof EntityEnderCrystal) {
                    if (mc.player.getDistance(entity) < 5) {
                        mc.player.swingArm(EnumHand.MAIN_HAND);
                        mc.playerController.attackEntity(mc.player, entity);
                        mc.player.swingArm(EnumHand.MAIN_HAND);
                        brokeCrystal = true;
                    }
                }
            }
            delay = 0;
        }
        if (mc.world.getBlockState(enemyPos.up().up()).getBlock().equals(Blocks.OBSIDIAN)) {
            blockAboveHead = true;
        } else {
            if (delay > delayS.getValInt() && !blockAboveHead) {
                if (InventoryUtil.findBlockInHotbar(Blocks.OBSIDIAN) == -1) {
                    this.disable();
                } else {
                    mc.player.inventory.currentItem = InventoryUtil.findBlockInHotbar(Blocks.OBSIDIAN);
                    BlockInteractionHelper.placeBlockScaffold(enemyPos.up(2));
                    blockAboveHead = true;
                }
                delay = 0;
            }

        }

        if (brokeCrystal && blockAboveHead && placedCrystal && minedBlock && delay > delayS.getValInt()) {
            blockAboveHead = false;
            placedCrystal = false;
            minedBlock = false;
            brokeCrystal = false;
            delay = 0;

        }
    }
    //

    public void onEnable() {
        findClosestTarget();
        blockAboveHead = false;
        placedCrystal = false;
        minedBlock = false;
        brokeCrystal = false;
    }

    private void findClosestTarget() {
        final List<EntityPlayer> playerList = mc.world.playerEntities;
        this.closestTarget = null;
        for (final EntityPlayer target : playerList) {
            if (target == mc.player) {
                continue;
            }
            if (Friends.isFriend(target.getName())) {
                continue;
            }
            if (target.getHealth() <= 0.0f) {
                continue;
            }
            if (this.closestTarget == null) {
                this.closestTarget = target;
            }
            else {
                if (mc.player.getDistance(target) >= mc.player.getDistance(this.closestTarget)) {
                    continue;
                }
                this.closestTarget = target;
            }
        }
    }
}