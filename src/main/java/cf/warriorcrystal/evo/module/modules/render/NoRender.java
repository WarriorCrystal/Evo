package cf.warriorcrystal.evo.module.modules.render;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.MobEffects;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;

public class NoRender extends Module {
    public NoRender() {
        super("NoRender", Category.RENDER);
    }

    public Setting armor;
    Setting fire;
    Setting blind;
    Setting nausea;
    public Setting hurtCam;

    public void setup(){
        Evo.getInstance().settingsManager.rSetting(armor = new Setting("Armor", this, false));
        Evo.getInstance().settingsManager.rSetting(fire = new Setting("Fire", this, false));
        Evo.getInstance().settingsManager.rSetting(blind = new Setting("Blindness", this, false));
        Evo.getInstance().settingsManager.rSetting(nausea = new Setting("Nausea", this, false));
        Evo.getInstance().settingsManager.rSetting(hurtCam = new Setting("HurtCam", this, false));
    }

    public void onUpdate(){
        if(blind.getValBoolean() && mc.player.isPotionActive(MobEffects.BLINDNESS)) mc.player.removePotionEffect(MobEffects.BLINDNESS);
        if(nausea.getValBoolean() && mc.player.isPotionActive(MobEffects.NAUSEA)) mc.player.removePotionEffect(MobEffects.NAUSEA);
    }

    @EventHandler
    public Listener<RenderBlockOverlayEvent> blockOverlayEventListener = new Listener<>(event -> {
        if(fire.getValBoolean() && event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE) event.setCanceled(true);
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
