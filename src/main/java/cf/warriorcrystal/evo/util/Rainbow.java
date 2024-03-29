package cf.warriorcrystal.evo.util;

import java.awt.*;

import cf.warriorcrystal.evo.event.EventProcessor;
import cf.warriorcrystal.evo.module.modules.gui.Core;
import de.Hero.settings.Setting;

public class Rainbow {
    public static int getInt(){
        return EventProcessor.INSTANCE.getRgb();
    }

    public static Color getColor(){
        return EventProcessor.INSTANCE.getC();
    }

    public static Color getColorWithOpacity(int opacity) {
        return new Color(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), opacity);
    }
    public static int getIntWithOpacity(int opacity) {
        return getColorWithOpacity(opacity).getRGB();
    }

    public static Color getAllRainbow() {
        final float[] hue = {(System.currentTimeMillis() % (360 * 32)) / (360f * 32)};
        int rgb = Color.HSBtoRGB(1, 1, 1);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        Color c = new Color(red, green, blue);
        return c; //

    }

    public static int getRainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 0.70f, 1f).getRGB();
    }

    public static void settingRainbow(Setting r, Setting g, Setting b) {

        float[] tick_color = {(float) ((System.currentTimeMillis() % (360 * 32)) / (360f * 32) * Core.speed.getValDouble())};

        int colorRGB = Color.HSBtoRGB(tick_color[0], (float) Core.saturation.getValDouble(), (float) Core.brightness.getValDouble());

        r.setValDouble((colorRGB >> 16) & 0xFF);
        g.setValDouble((colorRGB >> 8) & 0xFF);
        b.setValDouble(colorRGB & 0xFF);

    }

    public static void settingRainbowArrayList(Setting r, Setting g, Setting b, int delay) {

        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360;
        int colorRGB = Color.getHSBColor((float) (rainbowState / 360.0f), (float) Core.saturation.getValDouble(), (float) Core.brightness.getValDouble()).getRGB();
        r.setValDouble((colorRGB >> 16) & 0xFF);
        g.setValDouble((colorRGB >> 8) & 0xFF);
        b.setValDouble(colorRGB & 0xFF);

    }
}
