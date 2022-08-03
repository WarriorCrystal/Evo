package cf.warriorcrystal.evo.module.modules.render;

import java.util.ArrayList;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.event.events.RenderEvent;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;

public class ThunderKill extends Module {
    public ThunderKill() {
        super("ThunderKill", Category.RENDER);
    }

    Setting thunder;
    Setting thunderAmount;
    Setting sound;
    Setting soundAmount;

    public void setup(){
        thunder = new Setting("Thunder", this, true);
        Evo.getInstance().settingsManager.rSetting(thunder);
        thunderAmount = new Setting("Thunders", this, 1, 1, 10, true);
        Evo.getInstance().settingsManager.rSetting(thunderAmount);
        sound = new Setting("Sound", this, true);
        Evo.getInstance().settingsManager.rSetting(sound);
        soundAmount = new Setting("Sounds", this, 1, 1, 10, true);
        Evo.getInstance().settingsManager.rSetting(soundAmount);
    }

    ArrayList<EntityPlayer> playersDead = new ArrayList<>();

    final Object sync = new Object();
    
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
                    if (thunder.getValBoolean())
                        for(int i = 0; i < thunderAmount.getValInt(); i++)
                            mc.world.spawnEntity(new EntityLightningBolt(mc.world, entity.posX, entity.posY, entity.posZ, true));
                    if (sound.getValBoolean())
                        for(int i = 0; i < soundAmount.getValInt(); i++)
                            mc.player.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 0.5f, 1.f);
                    playersDead.add(entity);
                }
            }
        });

    }

    public void onEnable() {
        Evo.EVENT_BUS.subscribe(this);
        playersDead.clear();
    }
    public void onDisable() {
        Evo.EVENT_BUS.subscribe(this);
    }

}
