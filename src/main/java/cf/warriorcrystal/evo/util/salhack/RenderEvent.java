package cf.warriorcrystal.evo.util.salhack;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.Vec3d;

public class RenderEvent extends MinecraftEvent
{
    private float _partialTicks;
    
    public RenderEvent(float partialTicks)
    {
        _partialTicks = partialTicks;
    }
    
    public float getPartialTicks()
    {
        return _partialTicks;
    }
}