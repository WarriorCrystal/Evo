package cf.warriorcrystal.evo.util;

import java.awt.*;

import cf.warriorcrystal.evo.event.EventProcessor;

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

}
