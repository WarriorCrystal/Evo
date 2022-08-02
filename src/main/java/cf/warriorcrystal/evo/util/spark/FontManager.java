package cf.warriorcrystal.evo.util.spark;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.InputStream;
import java.util.Locale;

import cf.warriorcrystal.evo.event.EventProcessor;
import cf.warriorcrystal.evo.module.ModuleManager;
import cf.warriorcrystal.evo.module.modules.gui.CustomFontModule;

public class FontManager implements MC {
    private final String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.ENGLISH);
    public String fontName = "Tahoma";
    public int fontSize = 17;
    private GameFontRenderer font = new GameFontRenderer(new Font(fontName, Font.PLAIN, fontSize), true, false);
    private GameFontRenderer largeFont = new GameFontRenderer(new Font(fontName, Font.PLAIN, 27), true, false);
    private GameFontRenderer badaboom = new GameFontRenderer(getClientFont("badaboom.ttf", 17), true, false);
    public static GameFontRenderer newfont = new GameFontRenderer(getClientFont("Yaahowu.ttf", 21), true, false);
    public static GameFontRenderer newfontbold = new GameFontRenderer(getClientFont("Yaahowu Bold.ttf", 21), true, false);
    public static GameFontRenderer newfontitalic = new GameFontRenderer(getClientFont("Yaahowu Italic.ttf", 21), true, false);
    public static GameFontRenderer newfontbolditalic = new GameFontRenderer(getClientFont("Yaahowu Bold Italic.ttf", 21), true, false);

    public void setFont() {
        this.font = new GameFontRenderer(new Font(fontName, Font.PLAIN, fontSize), true, false);
        this.largeFont = new GameFontRenderer(new Font(fontName, Font.PLAIN, 27), true, false);
    }

    public GameFontRenderer getBadaboom() {
        return this.badaboom;
    }

    public GameFontRenderer getCFont() {
       return EventProcessor.getCustomFont();
    }

    public GameFontRenderer getLargeFont() {
        return this.largeFont;
    }

    public void reset() {
        this.setFont("Tahoma");
        this.setFontSize(17);
        this.setFont();
    }

    public boolean setFont(String fontName) {
        for (String font : fonts) {
            if (fontName.equalsIgnoreCase(font)) {
                this.fontName = font;
                this.setFont();
                return true;
            }
        }
        return false;
    }

    public int drawString(String text, int x, int y, int color) {
        if (getCustomFontEnabled())
            font.drawString(text, x, y, color, getFontShadow());
        else
            Minecraft.getMinecraft().fontRenderer.drawString(text, x, y, color, getFontShadow());
        return x + getTextWidth(text);
    }

    public int getTextWidth(String text) {
        if (getCustomFontEnabled())
            return font.getStringWidth(text);
        else
            return Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
    }

    public int getTextHeight() {
        if (getCustomFontEnabled())
            return font.getStringHeight();
        else
            return Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
    }

    public void setFontSize(int size) {
        this.fontSize = size;
        this.setFont();
    }

    private static Font getClientFont(final String fontName, final float size) {
        try {
            final InputStream inputStream = FontManager.class.getResourceAsStream("/assets/evo/textures/fonts/" + fontName);
            Font awtClientFont = Font.createFont(0, inputStream);
            awtClientFont = awtClientFont.deriveFont(0, size);
            inputStream.close();
            return awtClientFont;
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("default", 0, (int) size);
        }
    }

    public static boolean getCustomFontEnabled(){
        return false;
    }
    public static boolean getFontShadow(){
        return false;
    }
}