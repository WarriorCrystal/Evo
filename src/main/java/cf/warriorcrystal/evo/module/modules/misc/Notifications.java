package cf.warriorcrystal.evo.module.modules.misc;

import java.awt.*;

import cf.warriorcrystal.evo.module.Module;

public class Notifications extends Module {
    public Notifications() {
        super("Notifications", Category.MISC);
    }

    public static void sendNotification(String message, TrayIcon.MessageType messageType){
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon icon = new TrayIcon(image, "Evo");
        icon.setImageAutoSize(true);
        icon.setToolTip("Evo");
        try { tray.add(icon); }
        catch (AWTException e) { e.printStackTrace(); }
        icon.displayMessage("Evo", message, messageType);
    }

}
