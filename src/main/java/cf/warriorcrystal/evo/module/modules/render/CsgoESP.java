package cf.warriorcrystal.evo.module.modules.render;

import de.Hero.settings.Setting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

import static org.lwjgl.opengl.GL11.*;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.event.events.RenderEvent;
import cf.warriorcrystal.evo.friends.Friends;
import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.module.modules.combat.Surround;
import cf.warriorcrystal.evo.util.EvoTessellator;

public class CsgoESP extends Module {
    public CsgoESP() {
        super("CsgoESP", Category.RENDER);
    }

    Setting players;
    Setting passive;
    Setting monsters;
    Setting items;
    Setting xpBottles;
    Setting crystals;

    public void setup(){
        Evo.getInstance().settingsManager.rSetting(players = new Setting("Players", this, true));
        Evo.getInstance().settingsManager.rSetting(passive = new Setting("Passive", this, false));
        Evo.getInstance().settingsManager.rSetting(monsters = new Setting("Monsters", this, false));
        Evo.getInstance().settingsManager.rSetting(items = new Setting("Items", this, false));
        Evo.getInstance().settingsManager.rSetting(crystals = new Setting("Crystals", this, false));
        Evo.getInstance().settingsManager.rSetting(xpBottles = new Setting("XpBottles", this, false));
    }

    public void onWorldRender(RenderEvent event) {
        if (mc.getRenderManager().options == null) return;
                boolean isThirdPersonFrontal = mc.getRenderManager().options.thirdPersonView == 2;
                float viewerYaw = mc.getRenderManager().playerViewY;

                mc.world.loadedEntityList.stream()
                        .filter(entity -> mc.player != entity)
                        .forEach(e -> {
                            EvoTessellator.prepareGL();
                            GlStateManager.pushMatrix();
                            Vec3d pos = Surround.getInterpolatedPos(e, mc.getRenderPartialTicks());
                            GlStateManager.translate(pos.x-mc.getRenderManager().renderPosX, pos.y-mc.getRenderManager().renderPosY, pos.z-mc.getRenderManager().renderPosZ);
                            GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
                            GlStateManager.rotate(-viewerYaw, 0.0F, 1.0F, 0.0F);
                            GlStateManager.rotate((float)(isThirdPersonFrontal ? -1 : 1), 1.0F, 0.0F, 0.0F);
                            glColor4f(1, 1, 1, 0.5f);

                            glLineWidth(3f);
                            glEnable(GL_LINE_SMOOTH);

                            if(e instanceof EntityPlayer && players.getValBoolean()) {
                                if (Friends.isFriend(e.getName())) {
                                    glColor4f(0, 1, 1, 0.5f);
                                } else {
                                    glColor4f(1f, 0f, 0f, 0.5f);
                                }
                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, 0);
                                    glVertex2d(-e.width, e.height / 3);
                                    glVertex2d(-e.width, 0);
                                    glVertex2d((-e.width / 3) * 2, 0);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d((-e.width / 3) * 2, e.height);
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d(-e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, e.height);
                                    glVertex2d((e.width / 3) * 2, e.height);
                                    glVertex2d(e.width, e.height);
                                    glVertex2d(e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, 0);
                                    glVertex2d((e.width / 3) * 2, 0);
                                    glVertex2d(e.width, 0);
                                    glVertex2d(e.width, e.height / 3);
                                }
                                glEnd();
                            }

                            glColor4f(1, 1, 1, 0.5f);

                            if(GlowESP.isPassive(e) && passive.getValBoolean()) {
                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, 0);
                                    glVertex2d(-e.width, e.height / 3);
                                    glVertex2d(-e.width, 0);
                                    glVertex2d((-e.width / 3) * 2, 0);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d((-e.width / 3) * 2, e.height);
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d(-e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, e.height);
                                    glVertex2d((e.width / 3) * 2, e.height);
                                    glVertex2d(e.width, e.height);
                                    glVertex2d(e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, 0);
                                    glVertex2d((e.width / 3) * 2, 0);
                                    glVertex2d(e.width, 0);
                                    glVertex2d(e.width, e.height / 3);
                                }
                                glEnd();
                            }

                            if(GlowESP.isMonster(e) && monsters.getValBoolean()) {
                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, 0);
                                    glVertex2d(-e.width, e.height / 3);
                                    glVertex2d(-e.width, 0);
                                    glVertex2d((-e.width / 3) * 2, 0);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d((-e.width / 3) * 2, e.height);
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d(-e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, e.height);
                                    glVertex2d((e.width / 3) * 2, e.height);
                                    glVertex2d(e.width, e.height);
                                    glVertex2d(e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, 0);
                                    glVertex2d((e.width / 3) * 2, 0);
                                    glVertex2d(e.width, 0);
                                    glVertex2d(e.width, e.height / 3);
                                }
                                glEnd();
                            }

                            if(e instanceof EntityItem && items.getValBoolean()) {
                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, 0);
                                    glVertex2d(-e.width, e.height / 3);
                                    glVertex2d(-e.width, 0);
                                    glVertex2d((-e.width / 3) * 2, 0);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d((-e.width / 3) * 2, e.height);
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d(-e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, e.height);
                                    glVertex2d((e.width / 3) * 2, e.height);
                                    glVertex2d(e.width, e.height);
                                    glVertex2d(e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, 0);
                                    glVertex2d((e.width / 3) * 2, 0);
                                    glVertex2d(e.width, 0);
                                    glVertex2d(e.width, e.height / 3);
                                }
                                glEnd();
                            }

                            if(e instanceof EntityExpBottle && xpBottles.getValBoolean()) {
                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, 0);
                                    glVertex2d(-e.width, e.height / 3);
                                    glVertex2d(-e.width, 0);
                                    glVertex2d((-e.width / 3) * 2, 0);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d((-e.width / 3) * 2, e.height);
                                    glVertex2d(-e.width, e.height);
                                    glVertex2d(-e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, e.height);
                                    glVertex2d((e.width / 3) * 2, e.height);
                                    glVertex2d(e.width, e.height);
                                    glVertex2d(e.width, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width, 0);
                                    glVertex2d((e.width / 3) * 2, 0);
                                    glVertex2d(e.width, 0);
                                    glVertex2d(e.width, e.height / 3);
                                }
                                glEnd();
                            }


                            if(e instanceof EntityEnderCrystal && crystals.getValBoolean()) {
                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width / 2, 0);
                                    glVertex2d(-e.width / 2, e.height / 3);
                                    glVertex2d(-e.width / 2, 0);
                                    glVertex2d(((-e.width / 3) * 2) / 2, 0);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(-e.width / 2, e.height);
                                    glVertex2d(((-e.width / 3) * 2) / 2, e.height);
                                    glVertex2d(-e.width / 2, e.height);
                                    glVertex2d(-e.width / 2, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width / 2, e.height);
                                    glVertex2d(((e.width / 3) * 2) / 2, e.height);
                                    glVertex2d(e.width / 2, e.height);
                                    glVertex2d(e.width / 2, (e.height / 3) * 2);
                                }
                                glEnd();

                                glBegin(GL_LINE_LOOP);
                                {
                                    glVertex2d(e.width / 2, 0);
                                    glVertex2d(((e.width / 3) * 2) / 2, 0);
                                    glVertex2d(e.width / 2, 0);
                                    glVertex2d(e.width / 2, e.height / 3);
                                }
                                glEnd();
                            }

                            EvoTessellator.releaseGL();
                            GlStateManager.popMatrix();
                        });
                glColor4f(1,1,1, 1);
    }
}
