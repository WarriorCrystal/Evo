package com.evo.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.transformer.Config;

import com.evo.core.Evo;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.stream.Collectors;

@IFMLLoadingPlugin.Name("MixinLoader")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class MixinLoader implements IFMLLoadingPlugin {
    public MixinLoader() {
        Evo.LOGGER.info("Initializing mixin bootstrap...");

        MixinBootstrap.init();

        MixinEnvironment.getCurrentEnvironment().setObfuscationContext("searge");
        MixinEnvironment.getCurrentEnvironment().setSide(MixinEnvironment.Side.CLIENT);

        Mixins.addConfiguration("mixins.evo.json");

        Evo.LOGGER.info(
                "Added mixin configurations: {}",
                Mixins.getConfigs().stream().map(Config::getName).collect(Collectors.joining(", "))
        );
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

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
