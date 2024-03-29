package cf.warriorcrystal.evo.hud.components;

import de.Hero.clickgui.ClickGUI;
import de.Hero.clickgui.Panel;
import de.Hero.clickgui.util.ColorUtil;
import de.Hero.clickgui.util.FontUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.math.MathHelper;

import java.awt.*;
import java.text.DecimalFormat;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.ModuleManager;
import cf.warriorcrystal.evo.module.modules.gui.Bps;
import cf.warriorcrystal.evo.util.Rainbow;

public class BpsComponent extends Panel {
    public BpsComponent(double ix, double iy, ClickGUI parent) {
        super("BPS", ix, iy, 10, 10, false, parent);
        this.isHudComponent = true;

    }



    Bps mod = ((Bps) ModuleManager.getModuleByName("BPS"));

    Color c;
    boolean font;
    Color text;
    Color color;
    final DecimalFormat df = new DecimalFormat("0.0");

    public void drawHud(){
        doStuff();
        final double deltaX = mc.player.posX - mc.player.prevPosX;
        final double deltaZ = mc.player.posZ - mc.player.prevPosZ;
        final float tickRate = (mc.timer.tickLength / 1000.0f);
        final String bps = df.format((MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ) / tickRate)) + " BPS";
        if(font) Evo.fontManager.getCFont().drawText(bps, (float)x, (float)y, text.getRGB());
        else mc.fontRenderer.drawStringWithShadow(bps, (float)x, (float)y, text.getRGB());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        doStuff();
        final double deltaX = mc.player.posX - mc.player.prevPosX;
        final double deltaZ = mc.player.posZ - mc.player.prevPosZ;
        final float tickRate = (mc.timer.tickLength / 1000.0f);
        final String bps = df.format((MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ) / tickRate)) + " BPS";
        double w = mc.fontRenderer.getStringWidth(bps) + 2;
        c = new Color(50, 50, 50, 100);
        if(isHudComponentPinned) c = new Color(ColorUtil.getClickGUIColor().darker().getRed(), ColorUtil.getClickGUIColor().darker().getGreen(), ColorUtil.getClickGUIColor().darker().getBlue(), 100);
        if (this.dragging) {
            x = x2 + mouseX;
            y = y2 + mouseY;
        }
        this.width = w;
        this.height = FontUtil.getFontHeight() + 2;
        Gui.drawRect((int)x, (int)y, (int)x + (int)width, (int)y + (int)height, c.getRGB());
        Evo.fontManager.getCFont().drawText(title, x, y + height / 2 - FontUtil.getFontHeight()/2f, 0xffffffff);

        if(extended) {
            double startY = y + height;
            Gui.drawRect((int) x, (int) startY, (int) x + (int) width, (int) startY + (int) height, c.getRGB());
            if (font) Evo.fontManager.getCFont().drawText(bps, (float) x, (float) startY, text.getRGB());
            else mc.fontRenderer.drawStringWithShadow(bps, (float) x, (float) startY, text.getRGB());
        }
    }

    private void doStuff() {
        color = new Color(mod.red.getValInt(), mod.green.getValInt(), mod.blue.getValInt());
        text = mod.rainbow.getValBoolean() ? Rainbow.getColor() : color;
        font = mod.customFont.getValBoolean();
    }
}
