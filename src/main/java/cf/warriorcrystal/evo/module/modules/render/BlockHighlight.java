package cf.warriorcrystal.evo.module.modules.render;

import de.Hero.settings.Setting;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

import java.awt.*;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.RenderEvent;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.EvoTessellator;
import cf.warriorcrystal.evo.util.Rainbow;

public class BlockHighlight extends Module {
    public BlockHighlight() {
        super("BlockHighlight", Category.RENDER);
    }

    Setting r;
    Setting g;
    Setting b;
    Setting a;
    Setting w;
    Setting rainbow;

    public void setup(){
        r = new Setting("bhRed", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(r);
        g = new Setting("bhGreen", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(g);
        b = new Setting("bhBlue", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(b);
        a = new Setting("bhAlpha", this, 255, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(a);
        w = new Setting("bhWidth", this, 1, 1, 10, true);
        Evo.getInstance().settingsManager.rSetting(w);
        Evo.getInstance().settingsManager.rSetting(rainbow = new Setting("bhRainbow", this, false));
    }

    public void onWorldRender(RenderEvent event){
        RayTraceResult ray = mc.objectMouseOver;
        AxisAlignedBB bb;
        BlockPos pos;
        Color c;
        Color color = Rainbow.getColor();
        if(rainbow.getValBoolean())
            c = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)a.getValDouble());
        else
            c = new Color((int)r.getValDouble(), (int)g.getValDouble(), (int)b.getValDouble(), (int)a.getValDouble());
        if(ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK){
            pos = ray.getBlockPos();
            bb = mc.world.getBlockState(pos).getSelectedBoundingBox(mc.world, pos);
            if(bb != null && pos != null && mc.world.getBlockState(pos).getMaterial() != Material.AIR){
                EvoTessellator.prepareGL();
                EvoTessellator.drawBoundingBox(bb, (int)w.getValDouble(), c.getRGB());
                EvoTessellator.releaseGL();
            }
        }
    }
}
