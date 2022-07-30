package com.evo.util.render;

import com.evo.core.Evo;
import com.evo.util.internal.Wrapper;

public class ScaleUtil implements Wrapper {
    public static double alignV(String text, double x, double width) {
        return x + (width / 2.0) - (Evo.INSTANCE.getFontManager().getWidth(text) / 2.0);
    }

    public static double alignH(double y, double height) {
        return (y + (height / 2.0)) - Evo.INSTANCE.getFontManager().getHeight() / 2.0;
    }
}
