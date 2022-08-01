package cf.warriorcrystal.evo.util.salhack;

import me.zero.alpine.type.Cancellable;

public class MinecraftEvent extends Cancellable
{
    private Era era = Era.PRE;
    private final float partialTicks;

    public MinecraftEvent()
    {
        partialTicks = Wrapper.GetMC().getRenderPartialTicks();
    }
    
    public MinecraftEvent(Era p_Era)
    {
        partialTicks = Wrapper.GetMC().getRenderPartialTicks();
        era = p_Era;
    }

    public Era getEra()
    {
        return era;
    }

    public float getPartialTicks()
    {
        return partialTicks;
    }
    
    public enum Era
    {
        PRE,
        PERI,
        POST
    }

}