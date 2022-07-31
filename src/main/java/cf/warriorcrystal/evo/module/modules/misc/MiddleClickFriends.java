package cf.warriorcrystal.evo.module.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Mouse;

public class MiddleClickFriends extends Module {
    public MiddleClickFriends() {
        super("MCF", Category.MISC);
    }

    @EventHandler
    private Listener<InputEvent.MouseInputEvent> listener = new Listener<>(event -> {
        if (mc.objectMouseOver.typeOfHit.equals(RayTraceResult.Type.ENTITY) && mc.objectMouseOver.entityHit instanceof EntityPlayer && Mouse.getEventButton() == 2) {
            if (Evo.getInstance().friends.isFriend(mc.objectMouseOver.entityHit.getName())) {
                Evo.getInstance().friends.delFriend(mc.objectMouseOver.entityHit.getName());
                Command.sendClientMessage(ChatFormatting.RED + "Removed " + mc.objectMouseOver.entityHit.getName() + " from friends list");
            } else {
                Evo.getInstance().friends.addFriend(mc.objectMouseOver.entityHit.getName());
                Command.sendClientMessage(ChatFormatting.GREEN + "Added " + mc.objectMouseOver.entityHit.getName() + " to friends list");
            }
        }
    });

    public void onEnable(){
        Evo.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        Evo.EVENT_BUS.unsubscribe(this);
    }
}
