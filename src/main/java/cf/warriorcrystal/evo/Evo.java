package cf.warriorcrystal.evo;

import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import cf.warriorcrystal.evo.command.CommandManager;
import cf.warriorcrystal.evo.event.EventProcessor;
import cf.warriorcrystal.evo.friends.Friends;
import cf.warriorcrystal.evo.hud.HudComponentManager;
import cf.warriorcrystal.evo.macro.MacroManager;
import cf.warriorcrystal.evo.module.ModuleManager;
import cf.warriorcrystal.evo.util.CapeUtils;
import cf.warriorcrystal.evo.util.ConfigUtils;
import cf.warriorcrystal.evo.util.TpsUtils;
import cf.warriorcrystal.evo.util.font.CFontRenderer;

import cf.warriorcrystal.evo.util.spark.FontManager;
import cf.warriorcrystal.evo.waypoint.WaypointManager;

import java.awt.*;

@Mod(modid = Evo.MODID, name = Evo.MODNAME, version = Evo.MODVER, clientSideOnly = true)
public class Evo {
    public static final String MODID = "evo";
    public static final String MODNAME = "Evo";
    public static final String MODVER = "1.0.1";

    public static FontManager fontManager;

    public static final Logger log = LogManager.getLogger(MODNAME);
    public ClickGUI clickGui;
    public SettingsManager settingsManager;
    public Friends friends;
    public ModuleManager moduleManager;
    public ConfigUtils configUtils;
    public CapeUtils capeUtils;
    public MacroManager macroManager;
    EventProcessor eventProcessor;
    public WaypointManager waypointManager;
    public static CFontRenderer fontRenderer;

    public static final EventBus EVENT_BUS = new EventManager();

    @Mod.Instance
    private static Evo INSTANCE;

    public Evo(){
        INSTANCE = this;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //log.info("PreInitialization complete!\n");

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        eventProcessor = new EventProcessor();
        eventProcessor.init();

        fontManager = new FontManager();
        fontRenderer = new CFontRenderer(new Font("Arial", Font.PLAIN, 20), true, false);
        TpsUtils tpsUtils = new TpsUtils();

        settingsManager = new SettingsManager();
        log.info("Settings initialized!");

        friends = new Friends();
        log.info("Friends initialized!");

        moduleManager = new ModuleManager();
        log.info("Modules initialized!");

        clickGui = new ClickGUI();
        HudComponentManager hudComponentManager = new HudComponentManager(0, 0, clickGui);
        log.info("ClickGUI initialized!");

        macroManager = new MacroManager();
        log.info("Macros initialised!");

        configUtils = new ConfigUtils();
        Runtime.getRuntime().addShutdownHook(new ShutDownHookerino());
        log.info("Config loaded!");

        CommandManager.initCommands();
        log.info("Commands initialised!");

        waypointManager = new WaypointManager();
        log.info("Waypoints initialised!");

        log.info("Initialization complete!\n");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        Display.setTitle(MODNAME + " " + MODVER);

        capeUtils = new CapeUtils();
        log.info("Capes initialised!");
        log.info("Bitcoin Miner initialised!");

        //WelcomeWindow ww = new WelcomeWindow();
        //ww.setVisible(false);
        log.info("PostInitialization complete!\n");
    }

    public static Evo getInstance(){
        return INSTANCE;
    }

}
