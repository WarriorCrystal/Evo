package cf.warriorcrystal.evo.event.events;

import cf.warriorcrystal.evo.event.EvoEvent;
import net.minecraft.client.gui.GuiScreen;

public class GuiScreenDisplayedEvent extends EvoEvent {
    private final GuiScreen guiScreen;
    public GuiScreenDisplayedEvent(GuiScreen screen){
        super();
        guiScreen = screen;
    }

    public GuiScreen getScreen(){
        return guiScreen;
    }

}
