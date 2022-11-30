package cf.warriorcrystal.evo.module.modules.misc;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.event.events.RenderEvent;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.util.EnumHand;

import java.util.ArrayList;

public class AutoFrameDupe extends Module {
    public AutoFrameDupe() {
        super("AutoFrameDupe", Category.MISC);
    }

    private int timeout_ticks;

    Setting ticks;
    Setting shulkersOnly;
    Setting turns;
    Setting range;

    public void setup(){
        Evo.getInstance().settingsManager.rSetting(ticks = new Setting("Ticks", this, 10, 1, 20, true));
        Evo.getInstance().settingsManager.rSetting(shulkersOnly = new Setting("ShulkersOnly", this, true));
        Evo.getInstance().settingsManager.rSetting(turns = new Setting("Turns", this, 1, 0, 3, true));
        Evo.getInstance().settingsManager.rSetting(range = new Setting("Range", this, 5, 0, 6, true));
        timeout_ticks = 0;
    }


    private int getShulkerSlot() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (AutoFrameDupe.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemShulkerBox) {
                n = i;
            }
        }
        return n;
    }

    public void onUpdate() {
        if (AutoFrameDupe.mc.player != null && AutoFrameDupe.mc.world != null) {
            if (this.shulkersOnly.getValBoolean()) {
                final int shulkerSlot = this.getShulkerSlot();
                if (shulkerSlot != -1) {
                    AutoFrameDupe.mc.player.inventory.currentItem = shulkerSlot;
                }
            }
            for (final Entity entity : AutoFrameDupe.mc.world.loadedEntityList) {
                if (entity instanceof EntityItemFrame && AutoFrameDupe.mc.player.getDistance(entity) <= (int)range.getValInt()) {
                    if (this.timeout_ticks >= (int)this.ticks.getValInt()) {
                        if (((EntityItemFrame)entity).getDisplayedItem().getItem() == Items.AIR && !AutoFrameDupe.mc.player.getHeldItemMainhand().isEmpty) {
                            AutoFrameDupe.mc.playerController.interactWithEntity((EntityPlayer)AutoFrameDupe.mc.player, entity, EnumHand.MAIN_HAND);
                        }
                        if (((EntityItemFrame)entity).getDisplayedItem().getItem() != Items.AIR) {
                            for (int i = 0; i < (int)this.turns.getValInt(); ++i) {
                                AutoFrameDupe.mc.playerController.interactWithEntity((EntityPlayer)AutoFrameDupe.mc.player, entity, EnumHand.MAIN_HAND);
                            }
                            AutoFrameDupe.mc.playerController.attackEntity((EntityPlayer)AutoFrameDupe.mc.player, entity);
                            this.timeout_ticks = 0;
                        }
                    }
                    ++this.timeout_ticks;
                }
            }
        }
    }

}
