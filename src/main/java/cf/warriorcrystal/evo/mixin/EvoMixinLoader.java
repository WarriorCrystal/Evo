package cf.warriorcrystal.evo.mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import cf.warriorcrystal.evo.Evo;

import javax.annotation.Nullable;
import java.util.Map;

public class EvoMixinLoader implements IFMLLoadingPlugin {
    private static boolean isObfuscatedEnvironment = false;

    public EvoMixinLoader() {
        Evo.log.info("Evo mixins initialized");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.evo.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        Evo.log.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        isObfuscatedEnvironment = (boolean) (Boolean) data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
