package cf.warriorcrystal.evo.module.modules.misc;

import cf.warriorcrystal.evo.RPC;
import cf.warriorcrystal.evo.command.Command;
import cf.warriorcrystal.evo.module.Module;

public class RpcModule extends Module {
    public RpcModule() {
        super("DiscordRPC", Category.MISC);
        setDrawn(false);
    }

    public void onEnable(){
        RPC.init();
        if(mc.player != null)
            Command.sendClientMessage("discord rpc started");
    }

    public void onDisable(){
        Command.sendClientMessage("you need to restart your game disable rpc");
    }
}
