package com.evo.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evo.core.events.ShutdownEvent;
import com.evo.core.manager.managers.*;
import com.evo.core.manager.managers.hole.HoleManager;
import com.evo.core.manager.managers.relationships.RelationshipManager;
import com.evo.util.internal.fs.FileUtil;;

@Mod(modid = Evo.MODID, name = Evo.NAME, version = Evo.VERSION)
public class Evo {
    public static final String MODID = "evo";
    public static final String NAME = "Evo";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static Evo INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    // client related shit
    private ModuleManager moduleManager;
    private TickManager tickManager;
    private FontManager fontManager;
    private InteractionManager interactionManager;
    private InventoryManager inventoryManager;
    private RotationManager rotationManager;
    private HoleManager holeManager;
    private TotemPopManager totemPopManager;
    private RelationshipManager relationshipManager;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Forge found the mod Evo v{}", VERSION);
        FileUtil.mkDir(FileUtil.EVO_FOLDER, false); // create the config folder
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Initializing client managers");

        moduleManager = new ModuleManager();
        moduleManager.init();
        MinecraftForge.EVENT_BUS.register(moduleManager);

        tickManager = new TickManager();

        fontManager = new FontManager();

        interactionManager = new InteractionManager();

        inventoryManager = new InventoryManager();
        MinecraftForge.EVENT_BUS.register(inventoryManager);

        rotationManager = new RotationManager();
        MinecraftForge.EVENT_BUS.register(rotationManager);

        holeManager = new HoleManager();

        totemPopManager = new TotemPopManager();
        MinecraftForge.EVENT_BUS.register(totemPopManager);

        relationshipManager = new RelationshipManager();

        MinecraftForge.EVENT_BUS.register(new EventManager());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("Doing some last minute things...");

        LOGGER.info("Initialized Evo v{}", VERSION);
    }

    @SubscribeEvent
    public void onShutdown(ShutdownEvent event) {
        LOGGER.info("Removing icon from system tray");
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public TickManager getTickManager() {
        return tickManager;
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public InteractionManager getInteractionManager() {
        return interactionManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public RotationManager getRotationManager() {
        return rotationManager;
    }

    public HoleManager getHoleManager() {
        return holeManager;
    }

    public TotemPopManager getTotemPopManager() {
        return totemPopManager;
    }

    public RelationshipManager getRelationshipManager() {
        return relationshipManager;
    }
}
