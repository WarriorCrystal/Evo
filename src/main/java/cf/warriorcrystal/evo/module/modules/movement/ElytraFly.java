package cf.warriorcrystal.evo.module.modules.movement;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.MathUtil;
import de.Hero.settings.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

//this is from kami blue or somethin idk
public class ElytraFly extends Module {

    Setting speed;
    Setting glide;
    Setting glideSpeed;
    Setting noGlideAFK;
    Setting boost;
    Setting autoTakeoff;
    private final Setting flyMode;
    private int sendPacketDelay = 0;

    public ElytraFly() {
        super("ElytraFly", Category.MOVEMENT);

        Evo.getInstance().settingsManager.rSetting(speed = new Setting("Speed", this, 2, 0, 10, false));
        Evo.getInstance().settingsManager.rSetting(glide = new Setting("Glide", this, true));
        Evo.getInstance().settingsManager.rSetting(glideSpeed = new Setting("GlideSpeed", this, 1, 0, 2.5, false));
        Evo.getInstance().settingsManager.rSetting(noGlideAFK = new Setting("NoGlideAFK", this, false));
        Evo.getInstance().settingsManager.rSetting(boost = new Setting("Boost", this, true));
        Evo.getInstance().settingsManager.rSetting(autoTakeoff = new Setting("AutoTakeOff", this, true));

        ArrayList<String> flyModes = new ArrayList<>();
        flyModes.add("2b");
        flyModes.add("Creative");
        flyModes.add("Plane");
        Evo.getInstance().settingsManager.rSetting(flyMode = new Setting("FlyModes", this, "2b", flyModes));



    }

    @Override
    public void onUpdate() {
        if (flyMode.getValString().equalsIgnoreCase("2b")) return;
        if (mc.player.isElytraFlying() && !mc.gameSettings.keyBindSneak.isKeyDown()) {
            final float yaw = GetRotationYawForCalc();
            if (flyMode.getValString().equalsIgnoreCase("2b")) {
                mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_FALL_FLYING));

            }

            if (flyMode.getValString().equalsIgnoreCase("plane")) {
                final double[] dir = MathUtil.directionSpeed(speed.getValDouble());
                mc.player.motionY *= 0;
                mc.player.motionX = dir[0];
                mc.player.motionZ = dir[1];
            }
            if (flyMode.getValString().equalsIgnoreCase("creative")) {
                final double[] dir = MathUtil.directionSpeed(speed.getValDouble());
                if (!boost.getValBoolean()) {
                    mc.player.motionX = dir[0];
                    mc.player.motionZ = dir[1];
                } else {
                    if (mc.player.rotationPitch > 0) {
                        mc.player.motionX = dir[0];
                        mc.player.motionZ = dir[1];

                    }
                    if (mc.player.rotationPitch < 0) {
                        mc.player.motionX -= MathHelper.sin(yaw) * .08 / 10;
                        mc.player.motionZ += MathHelper.cos(yaw) * .08 / 10;
                    }

                }

            }

            if (boost.getValBoolean()) {
                if (mc.player.rotationPitch < 0) {
                    mc.player.motionX -= MathHelper.sin(yaw) * .08 / 10;
                    mc.player.motionZ += MathHelper.cos(yaw) * .08 / 10;
                }
            }




            if (glide.getValBoolean()) {
                mc.player.motionY = -(glideSpeed.getValDouble() / 10000);
            }

        }
        if (autoTakeoff.getValBoolean()) {
            if (mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA && mc.gameSettings.keyBindJump.isKeyDown() && !mc.player.isElytraFlying()) {
                sendPacketDelay++;
                if (sendPacketDelay > 5) {
                    mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    sendPacketDelay = 0;
                    //
                }
            }
        }

        if (mc.player.isElytraFlying() && mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.player.motionY = -.51;
        }


        if (!flyMode.getValString().equalsIgnoreCase("2b"))  {
            if (mc.player.isElytraFlying() && !mc.gameSettings.keyBindForward.isKeyDown() && !mc.gameSettings.keyBindRight.isKeyDown() && !mc.gameSettings.keyBindLeft.isKeyDown() && !mc.gameSettings.keyBindBack.isKeyDown() && !mc.gameSettings.keyBindSneak.isKeyDown() && !mc.gameSettings.keyBindJump.isKeyDown()) {
                mc.player.motionX = 0;
                mc.player.motionZ = 0;
                if (noGlideAFK.getValBoolean()) {
                    mc.player.motionY = 0;
                }
            }


            //
            //
        }

    }
    /*
        thx ionar
    */
    private float GetRotationYawForCalc() {
        float rotationYaw = mc.player.rotationYaw;
        if (mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }


    @Override
    public String getHudInfo() {
        return "\u00A77[\u00A7f" + flyMode.getValString() + "\u00A77]";
    }
    //
    public void onUpdate(PacketEvent.Receive event) {
        if (flyMode.getValString().equalsIgnoreCase("2b")) {
            if (event.getPacket() instanceof SPacketPlayerPosLook && mc.player.isElytraFlying()) {
                for (Entity entity : mc.world.loadedEntityList) {
                    if (entity instanceof EntityFireworkRocket) {
                        mc.world.removeEntity(entity);
                    }
                }
            }
        }
    }
}
