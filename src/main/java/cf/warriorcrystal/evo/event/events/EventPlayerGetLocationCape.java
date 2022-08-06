package cf.warriorcrystal.evo.event.events;

import me.zero.alpine.type.Cancellable;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class EventPlayerGetLocationCape extends Cancellable
{
    private ResourceLocation m_Location = null;
    public AbstractClientPlayer Player;
    
    public EventPlayerGetLocationCape(AbstractClientPlayer abstractClientPlayer)
    {
        super();
        
        Player = abstractClientPlayer;
    }
    
    public void SetResourceLocation(ResourceLocation p_Location)
    {
        m_Location = p_Location;
    }

    public ResourceLocation GetResourceLocation()
    {
        return m_Location;
    }
}
