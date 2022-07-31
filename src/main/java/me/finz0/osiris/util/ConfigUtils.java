package me.finz0.osiris.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.Hero.clickgui.ClickGUI;
import de.Hero.clickgui.Panel;
import me.finz0.osiris.hud.HudComponentManager;
import de.Hero.settings.Setting;
import me.finz0.osiris.OsirisMod;
import me.finz0.osiris.command.Command;
import me.finz0.osiris.event.EventProcessor;
import me.finz0.osiris.macro.Macro;
import me.finz0.osiris.module.Module;
import me.finz0.osiris.friends.Friend;
import me.finz0.osiris.friends.Friends;
import me.finz0.osiris.module.modules.chat.Announcer;
import me.finz0.osiris.module.modules.chat.AutoGG;
import me.finz0.osiris.module.modules.chat.AutoReply;
import me.finz0.osiris.module.modules.chat.Spammer;
import me.finz0.osiris.util.font.CFontRenderer;
import me.finz0.osiris.waypoint.Waypoint;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ConfigUtils {
    Minecraft mc = Minecraft.getMinecraft();
    public File Osiris;
    public File Settings;

    public ConfigUtils() {
        this.Osiris = new File(mc.gameDir + File.separator + "Osiris");
        if (!this.Osiris.exists()) {
            this.Osiris.mkdirs();
        }

        this.Settings = new File(mc.gameDir + File.separator + "Osiris" + File.separator + "Settings");
        if (!this.Settings.exists()) {
            this.Settings.mkdirs();
        }

        loadMods();
        loadDrawn();
        loadSettingsList();
        loadBinds();
        loadFriends();
        loadGui();
        loadPrefix();
        loadRainbow();
        loadMacros();
        loadMsgs();
        loadAutoGG();
        loadSpammer();
        loadAutoReply();
        loadAnnouncer();
        loadWaypoints();
        loadHudComponents();
        loadFont();
    }

    public void saveBinds() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Binds.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = OsirisMod.getInstance().moduleManager.getModules().iterator();

            while(var3.hasNext()) {
                Module module = (Module)var3.next();
                out.write(module.getName() + ":" + Keyboard.getKeyName(module.getBind()));
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadBinds() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Binds.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String bind = curLine.split(":")[1];
                for(Module m : OsirisMod.getInstance().moduleManager.getModules()) {
                    if (m != null && m.getName().equalsIgnoreCase(name)) {
                        m.setBind(Keyboard.getKeyIndex(bind));
                    }
                }
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            saveBinds();
        }

    }

    public void saveMacros() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Macros.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = OsirisMod.getInstance().macroManager.getMacros().iterator();

            while(var3.hasNext()) {
                Macro m = (Macro) var3.next();
                out.write(Keyboard.getKeyName(m.getKey()) + ":" + m.getValue().replace(" ", "_"));
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadMacros() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Macros.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String bind = curLine.split(":")[0];
                String value = curLine.split(":")[1];
                OsirisMod.getInstance().macroManager.addMacro(new Macro(Keyboard.getKeyIndex(bind), value.replace("_", " ")));
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            saveMacros();
        }

    }

    public void saveWaypoints() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Waypoints.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = OsirisMod.getInstance().waypointManager.getWaypoints().iterator();

            while(var3.hasNext()) {
                Waypoint w = (Waypoint) var3.next();
                out.write(w.getName() + ":" + w.getX() + ":" + w.getY() + ":" + w.getZ() + ":" + w.getColor());
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadWaypoints() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Waypoints.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String x = curLine.split(":")[1];
                int xx = Integer.parseInt(x);
                String y = curLine.split(":")[2];
                int yy = Integer.parseInt(y);
                String z = curLine.split(":")[3];
                int zz = Integer.parseInt(z);
                String color = curLine.split(":")[4];
                int c = Integer.parseInt(color);
                OsirisMod.getInstance().waypointManager.addWaypoint(new Waypoint(name, xx, yy, zz, c));
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            saveWaypoints();
        }

    }

    public void saveAnnouncer() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Announcer.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write("Walk:" + Announcer.walkMessage);
                out.write("\r\n");
                out.write("Place:" + Announcer.placeMessage);
                out.write("\r\n");
                out.write("Jump:" + Announcer.jumpMessage);
                out.write("\r\n");
                out.write("Break:" + Announcer.breakMessage);
                out.write("\r\n");
                out.write("Attack:" + Announcer.attackMessage);
                out.write("\r\n");
                out.write("Eat:" + Announcer.eatMessage);
                out.write("\r\n");
                out.write("ClickGUI:" + Announcer.guiMessage);
                out.write("\r\n");

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadAnnouncer() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Announcer.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String message = curLine.split(":")[1];
                if(name.equalsIgnoreCase("Walk")) Announcer.walkMessage = message;
                if(name.equalsIgnoreCase("Place")) Announcer.placeMessage = message;
                if(name.equalsIgnoreCase("Jump")) Announcer.jumpMessage = message;
                if(name.equalsIgnoreCase("Break")) Announcer.breakMessage = message;
                if(name.equalsIgnoreCase("Attack")) Announcer.attackMessage = message;
                if(name.equalsIgnoreCase("Eat")) Announcer.eatMessage = message;
                if(name.equalsIgnoreCase("ClickGUI")) Announcer.guiMessage = message;
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            saveAnnouncer();
        }

    }

    public void saveSpammer() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Spammer.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = Spammer.text.iterator();

            while(var3.hasNext()) {
                String s = (String)var3.next();
                out.write(s);
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadSpammer() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Spammer.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                Spammer.text.add(line);
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            saveSpammer();
        }

    }

    public void saveMods() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "EnabledModules.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = OsirisMod.getInstance().moduleManager.getModules().iterator();

            while(var3.hasNext()) {
                Module module = (Module)var3.next();
                if (module.isEnabled()) {
                    out.write(module.getName());
                    out.write("\r\n");
                }
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void saveFriends() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Friends.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = Friends.getFriends().iterator();

            while(var3.hasNext()) {
                Friend f = (Friend)var3.next();
                out.write(f.getName());
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadFriends() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Friends.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            Friends.friends.clear();
            String line;
            while((line = br.readLine()) != null) {
                OsirisMod.getInstance().friends.addFriend(line);
            }

            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();
            saveMods();
        }

    }

    public void saveGui() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Gui.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = ClickGUI.panels.iterator();

            while(var3.hasNext()) {
                Panel p = (Panel)var3.next();
                out.write(p.title + ":" + p.x + ":" + p.y + ":" + p.extended);
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadGui() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Gui.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String x = curLine.split(":")[1];
                String y = curLine.split(":")[2];
                String e = curLine.split(":")[3];
                double x1 = Double.parseDouble(x);
                double y1 = Double.parseDouble(y);
                boolean ext = Boolean.parseBoolean(e);
                Panel p = ClickGUI.getPanelByName(name);
                if (p != null) {
                    p.x = x1;
                    p.y = y1;
                    p.extended = ext;
                }
            }

            br.close();
        } catch (Exception var17) {
            var17.printStackTrace();
            this.saveGui();
        }

    }

    public void saveHudComponents() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "HudComponents.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = HudComponentManager.hudComponents.iterator();

            while(var3.hasNext()) {
                Panel p = (Panel)var3.next();
                out.write(p.title + ":" + p.x + ":" + p.y + ":" + p.extended + ":" + p.isHudComponentPinned);
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadHudComponents() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "HudComponents.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String x = curLine.split(":")[1];
                String y = curLine.split(":")[2];
                String e = curLine.split(":")[3];
                String pin = curLine.split(":")[4];
                double x1 = Double.parseDouble(x);
                double y1 = Double.parseDouble(y);
                boolean ex = Boolean.parseBoolean(e);
                boolean pinned = Boolean.parseBoolean(pin);
                Panel p = HudComponentManager.getHudComponentByName(name);
                if (p != null) {
                    p.x = x1;
                    p.y = y1;
                    p.extended = ex;
                    p.isHudComponentPinned = pinned;
                }
            }

            br.close();
        } catch (Exception var17) {
            var17.printStackTrace();
            this.saveHudComponents();
        }

    }

    public void savePrefix() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "CommandPrefix.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(Command.getPrefix());
            out.write("\r\n");
            out.close();
        } catch (Exception var3) {
        }

    }

    public void loadPrefix() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "CommandPrefix.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                Command.setPrefix(line);
            }

            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();
            this.savePrefix();
        }

    }

    public void saveFont() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Font.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(OsirisMod.fontRenderer.getFontName()+ ":" + OsirisMod.fontRenderer.getFontSize());
            out.write("\r\n");
            out.close();
        } catch (Exception var3) {
        }

    }

    public void loadFont() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Font.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String name = line.split(":")[0];
                String size = line.split(":")[1];
                int sizeInt = Integer.parseInt(size);
                OsirisMod.fontRenderer = new CFontRenderer(new Font(name, Font.PLAIN, sizeInt), true, false);
                OsirisMod.fontRenderer.setFont(new Font(name, Font.PLAIN, sizeInt));
                OsirisMod.fontRenderer.setAntiAlias(true);
                OsirisMod.fontRenderer.setFractionalMetrics(false);
                OsirisMod.fontRenderer.setFontName(name);
                OsirisMod.fontRenderer.setFontSize(sizeInt);
            }

            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();
            this.saveFont();
        }

    }

    public void saveAutoGG() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "AutoGgMessage.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for(String s : AutoGG.getAutoGgMessages()) {
                out.write(s);
                out.write("\r\n");
            }
            out.close();
        } catch (Exception var3) {
        }

    }

    public void loadAutoGG() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "AutoGgMessage.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                AutoGG.addAutoGgMessage(line);
            }

            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();
            this.saveAutoGG();
        }

    }

    public void saveAutoReply() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "AutoReplyMessage.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(AutoReply.getReply());
            out.close();
        } catch (Exception var3) {
        }

    }

    public void loadAutoReply() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "AutoReplyMessage.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                AutoReply.setReply(line);
            }

            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();
            this.saveAutoReply();
        }

    }

    public void saveRainbow() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "RainbowSpeed.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(EventProcessor.INSTANCE.getRainbowSpeed() + "");
            //out.write("\r\n");
            out.close();
        } catch (Exception var3) {
        }

    }

    public void loadRainbow() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "RainbowSpeed.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = br.readLine()) != null) {
                EventProcessor.INSTANCE.setRainbowSpeed(Integer.parseInt(line));
            }
            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();
            saveRainbow();
        }

    }

    public void saveMsgs() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "ClientMessages.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(Command.MsgWaterMark + "");
            out.write(",");
            out.write(Command.cf.getName());
            out.close();
        } catch (Exception var3) {
        }

    }

    public void loadMsgs() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "ClientMessages.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String watermark = curLine.split(",")[0];
                String color = curLine.split(",")[1];
                boolean w = Boolean.parseBoolean(watermark);
                ChatFormatting c = ChatFormatting.getByName(color);
                Command.cf = c;
                Command.MsgWaterMark = w;

            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            this.saveMsgs();
        }

    }

    public void saveDrawn() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Drawn.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = OsirisMod.getInstance().moduleManager.getModules().iterator();

            while(var3.hasNext()) {
                Module module = (Module)var3.next();
                out.write(module.getName() + ":" + module.isDrawn());
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadDrawn() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "Drawn.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String isOn = curLine.split(":")[1];
                boolean drawn = Boolean.parseBoolean(isOn);
                for(Module m : OsirisMod.getInstance().moduleManager.getModules()) {
                    if (m.getName().equalsIgnoreCase(name)) {
                        m.setDrawn(drawn);
                    }
                }
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            this.saveDrawn();
        }

    }

    public void writeCrash(String alah) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
            Date date = new Date();
            File file = new File(this.Osiris.getAbsolutePath(), "crashlog-".concat(format.format(date)).concat(".bruh"));
            BufferedWriter outWrite = new BufferedWriter(new FileWriter(file));
            outWrite.write(alah);
            outWrite.close();
        } catch (Exception var6) {
        }

    }

    public void loadMods() {
        try {
            File file = new File(this.Osiris.getAbsolutePath(), "EnabledModules.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                Iterator var6 = OsirisMod.getInstance().moduleManager.getModules().iterator();

                while(var6.hasNext()) {
                    Module m = (Module)var6.next();
                    if (m.getName().equals(line)) {
                        m.enable();
                    }
                }
            }

            br.close();
        } catch (Exception var8) {
            var8.printStackTrace();
            this.saveMods();
        }

    }

    public void saveSettingsList() {
        File file;
        BufferedWriter out;
        Iterator var3;
        Setting i;
        try {
            file = new File(Settings.getAbsolutePath(), "Number.txt");
            out = new BufferedWriter(new FileWriter(file));
            var3 = me.finz0.osiris.OsirisMod.getInstance().settingsManager.getSettings().iterator();

            while(var3.hasNext()) {
                i = (Setting)var3.next();
                if (i.isSlider()) {
                    out.write(i.getName() + ":" + i.getValDouble() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }

            out.close();
        } catch (Exception var7) {
        }

        try {
            file = new File(Settings.getAbsolutePath(), "Boolean.txt");
            out = new BufferedWriter(new FileWriter(file));
            var3 = me.finz0.osiris.OsirisMod.getInstance().settingsManager.getSettings().iterator();

            while(var3.hasNext()) {
                i = (Setting)var3.next();
                if (i.isCheck()) {
                    out.write(i.getName() + ":" + i.getValBoolean() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }

            out.close();
        } catch (Exception var6) {
        }

        try {
            file = new File(Settings.getAbsolutePath(), "String.txt");
            out = new BufferedWriter(new FileWriter(file));
            var3 = me.finz0.osiris.OsirisMod.getInstance().settingsManager.getSettings().iterator();

            while(var3.hasNext()) {
                i = (Setting)var3.next();
                if (i.isCombo()) {
                    out.write(i.getName() + ":" + i.getValString() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadSettingsList() {
        File file;
        FileInputStream fstream;
        DataInputStream in;
        BufferedReader br;
        String line;
        String curLine;
        String name;
        String isOn;
        String m;
        Setting mod;
        try {
            file = new File(Settings.getAbsolutePath(), "Number.txt");
            fstream = new FileInputStream(file.getAbsolutePath());
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while((line = br.readLine()) != null) {
                curLine = line.trim();
                name = curLine.split(":")[0];
                isOn = curLine.split(":")[1];
                m = curLine.split(":")[2];
                for(Module mm : OsirisMod.getInstance().moduleManager.getModules()) {
                    if (mm != null && mm.getName().equalsIgnoreCase(m)) {
                        mod = me.finz0.osiris.OsirisMod.getInstance().settingsManager.getSettingByName(name);
                        mod.setValDouble(Double.parseDouble(isOn));
                    }
                }
            }

            br.close();
        } catch (Exception var13) {
            var13.printStackTrace();
            this.saveSettingsList();
        }

        try {
            file = new File(Settings.getAbsolutePath(), "Boolean.txt");
            fstream = new FileInputStream(file.getAbsolutePath());
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while((line = br.readLine()) != null) {
                curLine = line.trim();
                name = curLine.split(":")[0];
                isOn = curLine.split(":")[1];
                m = curLine.split(":")[2];
                for(Module mm : OsirisMod.getInstance().moduleManager.getModules()) {
                    if (mm != null && mm.getName().equalsIgnoreCase(m)) {
                        mod = me.finz0.osiris.OsirisMod.getInstance().settingsManager.getSettingByName(name);
                        mod.setValBoolean(Boolean.parseBoolean(isOn));
                    }
                }
            }

            br.close();
        } catch (Exception var12) {
            var12.printStackTrace();
            this.saveSettingsList();
        }

        try {
            file = new File(Settings.getAbsolutePath(), "String.txt");
            fstream = new FileInputStream(file.getAbsolutePath());
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while((line = br.readLine()) != null) {
                curLine = line.trim();
                name = curLine.split(":")[0];
                isOn = curLine.split(":")[1];
                m = curLine.split(":")[2];
                for(Module mm : OsirisMod.getInstance().moduleManager.getModules()) {
                    if (mm != null && mm.getName().equalsIgnoreCase(m)) {
                        mod = me.finz0.osiris.OsirisMod.getInstance().settingsManager.getSettingByName(name);
                        mod.setValString(isOn);
                    }
                }
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            this.saveSettingsList();
        }

    }
}
