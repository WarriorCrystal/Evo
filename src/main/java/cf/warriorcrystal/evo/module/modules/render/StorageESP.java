package cf.warriorcrystal.evo.module.modules.render;

import de.Hero.settings.Setting;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.RenderEvent;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.GeometryMasks;
import cf.warriorcrystal.evo.util.EvoTessellator;

import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;

public class StorageESP extends Module {
    public StorageESP() {
        super("StorageESP", Category.RENDER);
    }

    Setting a;
    Setting w;

    ConcurrentHashMap<TileEntity, String> chests = new ConcurrentHashMap<>();

    public void setup(){
        a = new Setting("stAlpha", this, 150, 0, 255, true);
        Evo.getInstance().settingsManager.rSetting(a);
        w = new Setting("stWidth", this, 1, 1, 10, true);
        Evo.getInstance().settingsManager.rSetting(w);
    }

    public void onUpdate(){
        mc.world.loadedTileEntityList.forEach(e->{
                chests.put(e, "");
        });
    }

    public void onWorldRender(RenderEvent event){
        Color c1 = new Color(200, 100, 0, (int)a.getValDouble());
        Color c2 = new Color(200, 0, 200, (int)a.getValDouble());
        Color c3 = new Color(150, 150, 150, (int)a.getValDouble());
        if(chests != null && chests.size() > 0){
            EvoTessellator.prepareGL();
            chests.forEach((c, t)->{
                if(mc.world.loadedTileEntityList.contains(c)) {
                    if(c instanceof TileEntityChest
                            || c instanceof TileEntityShulkerBox)
                        //OsirisTessellator.drawBox(c.getPos(), c1.getRGB(), GeometryMasks.Quad.ALL);
                        EvoTessellator.drawBoundingBox(mc.world.getBlockState(c.getPos()).getSelectedBoundingBox(mc.world, c.getPos()), (float)w.getValDouble(), c1.getRGB());
                    if(c instanceof TileEntityEnderChest)
                        //OsirisTessellator.drawBox(c.getPos(), c2.getRGB(), GeometryMasks.Quad.ALL);
                        EvoTessellator.drawBoundingBox(mc.world.getBlockState(c.getPos()).getSelectedBoundingBox(mc.world, c.getPos()), (float)w.getValDouble(), c2.getRGB());
                    if(c instanceof TileEntityDispenser
                            || c instanceof TileEntityFurnace
                            || c instanceof TileEntityHopper)
                    //OsirisTessellator.drawBox(c.getPos(), c3.getRGB(), GeometryMasks.Quad.ALL);
                        EvoTessellator.drawBoundingBox(mc.world.getBlockState(c.getPos()).getSelectedBoundingBox(mc.world, c.getPos()), (float)w.getValDouble(), c3.getRGB());
                }
            });
            EvoTessellator.releaseGL();
        }
    }
}
