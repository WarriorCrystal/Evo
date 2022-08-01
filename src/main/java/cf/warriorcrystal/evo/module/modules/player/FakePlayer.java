package cf.warriorcrystal.evo.module.modules.player;

import cf.warriorcrystal.evo.module.Module;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.UUID;

public class FakePlayer extends Module {

    public FakePlayer() {
        super("XiussPlayer", Category.PLAYER);
    }

    EntityOtherPlayerMP entity;

    @Override
    public void onEnable() {
        entity = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("d8d5a923-7b20-43d8-883b-1150148d6955"), "Test"));
        entity.copyLocationAndAnglesFrom(mc.player);
        entity.rotationYaw = mc.player.rotationYaw;
        entity.rotationYawHead = mc.player.rotationYawHead;
        entity.inventory = mc.player.inventory;
        mc.world.addEntityToWorld(69698067, entity);
    }

    @Override
    public void onDisable() {
        if (mc.world.loadedEntityList.contains(entity)) {
            mc.world.removeEntity(entity);
        }
    }
    @SubscribeEvent
    public void onPlayerLeaveEvent(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.disable();
    }
}