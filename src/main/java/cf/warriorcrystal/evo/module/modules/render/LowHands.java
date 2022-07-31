package cf.warriorcrystal.evo.module.modules.render;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.EnumHand;

public class LowHands extends Module {
    public LowHands() {
        super("LowOffhand", Category.RENDER);
    }
    Setting off;
    ItemRenderer itemRenderer = mc.entityRenderer.itemRenderer;

    public void setup(){
        off = new Setting("Height", this, 0.5, 0, 1, false);
        Evo.getInstance().settingsManager.rSetting(off);
    }

    public void onUpdate(){
        itemRenderer.equippedProgressOffHand = (float)off.getValDouble();
    }
}
