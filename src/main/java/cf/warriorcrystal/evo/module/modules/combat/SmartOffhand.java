package cf.warriorcrystal.evo.module.modules.combat;

import de.Hero.settings.Setting;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

import java.util.ArrayList;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.salhack.PlayerUtil;

public class SmartOffhand extends Module {
    public SmartOffhand() {
        super("Offhand", Category.COMBAT);
    }

    public int totems;
    int crystals;
    boolean moving = false;
    boolean returnI = false;
    Item item;

    Setting health;
    Setting Mode;
    Setting FallbackMode;
    Setting FallDistance;
    Setting TotemOnElytra;
    Setting OffhandGapOnSword;
    Setting OffhandStrNoStrSword;
    Setting HotbarFirst;
    

    public void setup(){
        ArrayList<String> items = new ArrayList<>();
        items.add("Totem");
        items.add("Gap");
        items.add("Crystal");
        items.add("Pearl");
        items.add("Chorus");
        items.add("Stregth");
        Evo.getInstance().settingsManager.rSetting(health = new Setting("Health", this, 16.0f, 0.0f, 20.0f, false));
        Evo.getInstance().settingsManager.rSetting(Mode = new Setting("Mode", this, "Crystal", items));
        Evo.getInstance().settingsManager.rSetting(FallbackMode = new Setting("FallbackMode", this, "Crystal", items));
        Evo.getInstance().settingsManager.rSetting(FallDistance = new Setting("FallDistance", this, 15.0f, 0.0f, 100.0f, false));
        Evo.getInstance().settingsManager.rSetting(TotemOnElytra = new Setting("TotemOnElytra", this, true));
        Evo.getInstance().settingsManager.rSetting(OffhandGapOnSword = new Setting("SwordGap", this, true));
        Evo.getInstance().settingsManager.rSetting(OffhandStrNoStrSword = new Setting("StrIfNoStr", this, false));
        Evo.getInstance().settingsManager.rSetting(HotbarFirst = new Setting("HotbarFirst", this, false));
    }

    public void onUpdate() {
        if (mc.currentScreen != null && (!(mc.currentScreen instanceof GuiInventory)))
            return;
        
        if (!mc.player.getHeldItemMainhand().isEmpty())
        {
            if ((float)health.getValDouble() <= PlayerUtil.GetHealthWithAbsorption() && mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && OffhandStrNoStrSword.getValBoolean() && !mc.player.isPotionActive(MobEffects.STRENGTH))
            {
                SwitchOffHandIfNeed("Strength");
                return;
            }
            
            /// Sword override
            if ((float)health.getValDouble() <= PlayerUtil.GetHealthWithAbsorption() && mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && OffhandGapOnSword.getValBoolean())
            {
                SwitchOffHandIfNeed("Gap");
                return;
            }
        }
        
        /// First check health, most important as we don't want to die for no reason.
        if ((float)health.getValDouble() > PlayerUtil.GetHealthWithAbsorption() || Mode.getValString() == "Totem" || (TotemOnElytra.getValBoolean() && mc.player.isElytraFlying()) || (mc.player.fallDistance >= (float) FallDistance.getValDouble() && !mc.player.isElytraFlying()))
        {
            SwitchOffHandIfNeed("Totem");
            return;
        }
        
        /// If we meet the required health
        SwitchOffHandIfNeed(Mode.getValString());
    }

    private void SwitchOffHandIfNeed(String p_Val)
    {
        Item l_Item = GetItemFromModeVal(p_Val);
        
        if (mc.player.getHeldItemOffhand().getItem() != l_Item)
        {
            int l_Slot = HotbarFirst.getValBoolean() ? PlayerUtil.GetRecursiveItemSlot(l_Item) : PlayerUtil.GetItemSlot(l_Item);
            
            Item l_Fallback = GetItemFromModeVal(FallbackMode.getValString());
            
            String l_Display = GetItemNameFromModeVal(p_Val);
            
            if (l_Slot == -1 && l_Item != l_Fallback && mc.player.getHeldItemOffhand().getItem() != l_Fallback)
            {
                l_Slot = PlayerUtil.GetRecursiveItemSlot(l_Fallback);
                l_Display = GetItemNameFromModeVal(FallbackMode.getValString());
                
                /// still -1...
                if (l_Slot == -1 && l_Fallback != Items.TOTEM_OF_UNDYING)
                {
                    l_Fallback = Items.TOTEM_OF_UNDYING;
                    
                    if (l_Item != l_Fallback && mc.player.getHeldItemOffhand().getItem() != l_Fallback)
                    {
                        l_Slot = PlayerUtil.GetRecursiveItemSlot(l_Fallback);
                        l_Display = "Emergency Totem";
                    }
                }
            }

            if (l_Slot != -1)
            {
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, l_Slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP,
                        mc.player);
                
                /// @todo: this might cause desyncs, we need a callback for windowclicks for transaction complete packet.
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, l_Slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.updateController();
                
            }
        }
    }

    public Item GetItemFromModeVal(String p_Val)
    {
        switch (p_Val)
        {
            case "Crystal":
                return Items.END_CRYSTAL;
            case "Gap":
                return Items.GOLDEN_APPLE;
            case "Pearl":
                return Items.ENDER_PEARL;
            case "Chorus":
                return Items.CHORUS_FRUIT;
            case "Strength":
                return Items.POTIONITEM;
            default:
                break;
        }
        
        return Items.TOTEM_OF_UNDYING;
    }

    private String GetItemNameFromModeVal(String p_Val)
    {
        switch (p_Val)
        {
            case "Crystal":
                return "End Crystal";
            case "Gap":
                return "Gap";
            case "Pearl":
                return "Pearl";
            case "Chorus":
                return "Chorus";
            case "Strength":
                return "Strength";
            default:
                break;
        }
        
        return "Totem";
    }

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }

}
