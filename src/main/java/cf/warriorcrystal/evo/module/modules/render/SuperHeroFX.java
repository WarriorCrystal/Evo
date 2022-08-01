package cf.warriorcrystal.evo.module.modules.render;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mojang.realmsclient.gui.ChatFormatting;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.PacketEvent;
import cf.warriorcrystal.evo.event.events.RenderEvent;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.spark.*;

import de.Hero.settings.Setting;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SuperHeroFX extends Module {
    public SuperHeroFX() {
        super("ModuleExample", Category.RENDER);
    }

    Setting delay;
    Setting scale;
    Setting extra;

    public void setup(){
        Evo.getInstance().settingsManager.rSetting(delay = new Setting("shfDelay", this, 1.0f, 0.0f, 10.0f, false));
        Evo.getInstance().settingsManager.rSetting(scale = new Setting("shfScale", this, 1.5f, 0.0f, 10.0f, false));
        Evo.getInstance().settingsManager.rSetting(extra = new Setting("shfExtra", this, 1, 0, 50, true));
    }

    public static final FontManager fontManager = new FontManager();

    private List<PopupText> popTexts = new CopyOnWriteArrayList<>();
    private final Random rand = new Random();
    private final Timer timer = new Timer();
    private static final String[] superHeroTextsBlowup = new String[]{"KABOOM", "BOOM", "POW", "KAPOW", "KABLEM"};
    private static final String[] superHeroTextsDamageTaken = new String[]{"OUCH", "ZAP", "BAM", "WOW", "POW", "SLAP"};
    
    public void onUpdate() {
        this.popTexts.removeIf(PopupText::isMarked);
        this.popTexts.forEach(PopupText::Update);
    }

    public void onEnable() {
        Evo.EVENT_BUS.subscribe(this);
    }
    
    public void onDisable() {
        Evo.EVENT_BUS.unsubscribe(this);
    }

    public void onWorldRender(RenderEvent event){
        mc.getRenderManager();
        if (mc.getRenderManager().options != null) {

            this.popTexts.forEach(pop -> {
                GlStateManager.pushMatrix();
                RenderUtil.glBillboardDistanceScaled((float) pop.pos.x, (float) pop.pos.y, (float) pop.pos.z, mc.player, (float) scale.getValDouble());
                GlStateManager.disableDepth();
                GlStateManager.translate(-((double) fontManager.getBadaboom().getStringWidth(pop.getDisplayName()) / 2.0), 0.0, 0.0);
                fontManager.getBadaboom().drawText(pop.getDisplayName(), 0, 0, pop.color);

                //added this line to not fuck up item rendering
                GlStateManager.enableDepth();

                GlStateManager.popMatrix();
            });
        }
    }

    @EventHandler
    private Listener<PacketEvent.Receive> packetReceiveListener = new Listener<>(event -> {
        if (mc.player == null || mc.world == null) return;
        try {
            if (event.getPacket() instanceof SPacketExplosion) {
                SPacketExplosion packet = (SPacketExplosion) event.getPacket();
                if (mc.player.getDistance(packet.getX(), packet.getY(), packet.getZ()) < 20.0 && this.timer.passedMs((long) ((float) this.delay.getValDouble() * 1000.0f))) {
                    this.timer.reset();
                    int len = rand.nextInt(extra.getValInt());
                    for (int i = 0; i <= len; i++) {
                        Vec3d pos = new Vec3d(packet.getX() + rand.nextInt(4) - 2, packet.getY() + rand.nextInt(2), packet.getZ() + rand.nextInt(4) - 2);
                        PopupText popupText = new PopupText(ChatFormatting.ITALIC + SuperHeroFX.superHeroTextsBlowup[this.rand.nextInt(SuperHeroFX.superHeroTextsBlowup.length)], pos);
                        popTexts.add(popupText);
                    }
                }
            } else if (event.getPacket() instanceof SPacketEntityStatus) {
                SPacketEntityStatus packet = (SPacketEntityStatus) event.getPacket();
                if (mc.world != null) {
                    Entity e = packet.getEntity((World) mc.world);
                    if (packet.getOpCode() == 35) {
                        if (mc.player.getDistance(e) < 20.0f) {
                            PopupText popupText = new PopupText(ChatFormatting.ITALIC + "POP", e.getPositionVector().add((double) (this.rand.nextInt(2) / 2), 1.0, (double) (this.rand.nextInt(2) / 2)));
                            popTexts.add(popupText);
                        }
                    } else if (packet.getOpCode() == 2) {
                        if (mc.player.getDistance(e) < 20.0f & e != mc.player) {
                            if (this.timer.passedMs((long) ( (float) this.delay.getValDouble() * 1000.0f))) {
                                this.timer.reset();
                                int len = rand.nextInt((int)Math.ceil(extra.getValInt()/2.0));
                                for (int i = 0; i <= len; i++) {
                                    Vec3d pos = new Vec3d(e.posX + rand.nextInt(2) - 1, e.posY + rand.nextInt(2) - 1, e.posZ + rand.nextInt(2) - 1);
                                    PopupText popupText = new PopupText(ChatFormatting.ITALIC + SuperHeroFX.superHeroTextsDamageTaken[this.rand.nextInt(SuperHeroFX.superHeroTextsBlowup.length)], pos);
                                    popTexts.add(popupText);
                                }
                            }
                        }
                    }
                }
            } else if (event.getPacket() instanceof SPacketDestroyEntities) {
                SPacketDestroyEntities packet = (SPacketDestroyEntities) event.getPacket();
                final int[] array = packet.getEntityIDs();
                for (int i = 0; i < array.length - 1; i++) {
                    int id = array[i];
                    try {
                    	//wtf is this?
                        if (mc.world.getEntityByID(id) == null) continue;
                    } catch (ConcurrentModificationException exception) {
                        return;
                    }
                    Entity e = mc.world.getEntityByID(id);
                    if (e != null && e.isDead) {
                        if ((mc.player.getDistance(e) < 20.0f & e != mc.player) && e instanceof EntityPlayer) {
                            for (int t = 0; t <= rand.nextInt(extra.getValInt()); t++) {
                                Vec3d pos = new Vec3d(e.posX + rand.nextInt(2) - 1, e.posY + rand.nextInt(2) - 1, e.posZ + rand.nextInt(2) - 1);
                                PopupText popupText = new PopupText(ChatFormatting.ITALIC + "" + ChatFormatting.BOLD + "EZ", pos);
                                popTexts.add(popupText);
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException ignoredlel) {
            //rreee empty catch block
        }
    });

}
