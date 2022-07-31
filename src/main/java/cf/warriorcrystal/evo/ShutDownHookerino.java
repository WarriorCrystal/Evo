package cf.warriorcrystal.evo;

public class ShutDownHookerino extends Thread {
    @Override
    public void run(){
        saveConfig();
    }

    public static void saveConfig(){
        Evo.getInstance().configUtils.saveMods();
        Evo.getInstance().configUtils.saveSettingsList();
        Evo.getInstance().configUtils.saveBinds();
        Evo.getInstance().configUtils.saveDrawn();
        Evo.getInstance().configUtils.saveFriends();
        Evo.getInstance().configUtils.saveGui();
        Evo.getInstance().configUtils.savePrefix();
        Evo.getInstance().configUtils.saveRainbow();
        Evo.getInstance().configUtils.saveMacros();
        Evo.getInstance().configUtils.saveMsgs();
        Evo.getInstance().configUtils.saveAutoGG();
        Evo.getInstance().configUtils.saveSpammer();
        Evo.getInstance().configUtils.saveAutoReply();
        Evo.getInstance().configUtils.saveAnnouncer();
        Evo.getInstance().configUtils.saveWaypoints();
        Evo.getInstance().configUtils.saveHudComponents();
        Evo.getInstance().configUtils.saveFont();
    }
}
