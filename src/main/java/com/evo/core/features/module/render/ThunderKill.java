package com.evo.core.features.module.render;

import java.util.ArrayList;

import com.evo.core.features.module.Category;
import com.evo.core.features.module.Module;
import com.evo.core.setting.Setting;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;

public class ThunderKill extends Module {

    public ThunderKill() {
        super("ThunderKill", Category.RENDER, "Spawns a thunder when someone dies like in HCF");
    }

    public static final Setting<Boolean> thunder = new Setting<>("Thunder", true);
    public static final Setting<Integer> thunderAmount = new Setting<>("Thunders", 1, 1, 10);
    public static final Setting<Boolean> sound = new Setting<>("Sound", true);
    public static final Setting<Integer> soundAmount = new Setting<>("Sounds", 1, 1, 10);

    ArrayList<EntityPlayer> playersDead = new ArrayList<>();

    final Object sync = new Object();

    @Override
    protected void onEnable() {
        playersDead.clear();
    }

    @Override
    public void onUpdate() {
        if (mc.world == null) {
            playersDead.clear();
            return;
        }
        mc.world.playerEntities.forEach(entity -> {
            if (playersDead.contains(entity)) {
                if (entity.getHealth() > 0)
                    playersDead.remove(entity);
            } else {
                if (entity.getHealth() == 0) {
                    if (thunder.getValue())
                        for(int i = 0; i < thunderAmount.getValue(); i++)
                            mc.world.spawnEntity(new EntityLightningBolt(mc.world, entity.posX, entity.posY, entity.posZ, true));
                    if (sound.getValue())
                        for(int i = 0; i < soundAmount.getValue(); i++)
                            mc.player.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 0.5f, 1.f);
                    playersDead.add(entity);
                }
            }
        });

    }
}
