package cf.warriorcrystal.evo.module.modules.render;

import de.Hero.settings.Setting;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.RenderEvent;
import cf.warriorcrystal.evo.friends.Friends;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.GeometryMasks;
import cf.warriorcrystal.evo.util.EvoTessellator;
import cf.warriorcrystal.evo.util.Rainbow;

import java.awt.*;

public class BoxESP extends Module {
    public BoxESP() {
        super("BoxESP", Category.RENDER);
        Evo.getInstance().settingsManager.rSetting(players = new Setting("bePlayers", this, false));
        Evo.getInstance().settingsManager.rSetting(passive = new Setting("bePassive", this, false));
        Evo.getInstance().settingsManager.rSetting(mobs = new Setting("beMobs", this, false));
        Evo.getInstance().settingsManager.rSetting(exp = new Setting("beXpBottles", this, false));
        Evo.getInstance().settingsManager.rSetting(epearls = new Setting("beEpearls", this, false));
        Evo.getInstance().settingsManager.rSetting(crystals = new Setting("beCrystals", this, false));
        Evo.getInstance().settingsManager.rSetting(items = new Setting("beItems", this, false));
        Evo.getInstance().settingsManager.rSetting(rainbow = new Setting("beRainbow", this, false));
        Evo.getInstance().settingsManager.rSetting(r = new Setting("beRed", this, 255, 1, 255, true));
        Evo.getInstance().settingsManager.rSetting(g = new Setting("beGreen", this, 255, 1, 255, true));
        Evo.getInstance().settingsManager.rSetting(b = new Setting("beBlue", this, 255, 1, 255, true));
        Evo.getInstance().settingsManager.rSetting(a = new Setting("beAlpha", this, 50, 1, 255, true));
    }

    Setting players;
    Setting passive;
    Setting mobs;
    Setting exp;
    Setting epearls;
    Setting crystals;
    Setting items;

    Setting rainbow;
    Setting r;
    Setting g;
    Setting b;
    Setting a;

    public void onWorldRender(RenderEvent event){
        Color c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), a.getValInt());
        if(rainbow.getValBoolean()) c = new Color(Rainbow.getColor().getRed(), Rainbow.getColor().getGreen(), Rainbow.getColor().getBlue(), a.getValInt());
        Color enemy = new Color(255, 0, 0, a.getValInt());
        Color friend = new Color(0, 255, 255, a.getValInt());
        Color finalC = c;
        mc.world.loadedEntityList.stream()
                .filter(entity -> entity != mc.player)
                .forEach(e -> {
                    EvoTessellator.prepare(GL11.GL_QUADS);
                    if(players.getValBoolean() && e instanceof EntityPlayer) {
                        if (Friends.isFriend(e.getName())) EvoTessellator.drawBox(e.getRenderBoundingBox(), friend.getRGB(), GeometryMasks.Quad.ALL);
                        else EvoTessellator.drawBox(e.getRenderBoundingBox(), enemy.getRGB(), GeometryMasks.Quad.ALL);
                    }
                    if(mobs.getValBoolean() && GlowESP.isMonster(e)){
                        EvoTessellator.drawBox(e.getRenderBoundingBox(), finalC.getRGB(), GeometryMasks.Quad.ALL);
                    }
                    if(passive.getValBoolean() && GlowESP.isPassive(e)){
                        EvoTessellator.drawBox(e.getRenderBoundingBox(), finalC.getRGB(), GeometryMasks.Quad.ALL);
                    }
                    if(exp.getValBoolean() && e instanceof EntityExpBottle){
                        EvoTessellator.drawBox(e.getRenderBoundingBox(), finalC.getRGB(), GeometryMasks.Quad.ALL);
                    }
                    if(epearls.getValBoolean() && e instanceof EntityEnderPearl){
                        EvoTessellator.drawBox(e.getRenderBoundingBox(), finalC.getRGB(), GeometryMasks.Quad.ALL);
                    }
                    if(crystals.getValBoolean() && e instanceof EntityEnderCrystal){
                        EvoTessellator.drawBox(e.getRenderBoundingBox(), finalC.getRGB(), GeometryMasks.Quad.ALL);
                    }
                    if(items.getValBoolean() && e instanceof EntityItem){
                        EvoTessellator.drawBox(e.getRenderBoundingBox(), finalC.getRGB(), GeometryMasks.Quad.ALL);
                    }
                    EvoTessellator.release();
                });
    }
}
