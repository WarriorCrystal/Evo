package de.Hero.clickgui.util;

import java.awt.Color;

import de.Hero.settings.SettingsManager;
import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.util.Rainbow;

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class ColorUtil {
	
	public static Color getClickGUIColor(){
		if(Evo.getInstance().settingsManager.getSettingByName("GuiRainbow").getValBoolean()) {
			return Rainbow.getColor();
		}else
			return new Color((int) Evo.getInstance().settingsManager.getSettingByName("GuiRed").getValDouble(), (int) Evo.getInstance().settingsManager.getSettingByName("GuiGreen").getValDouble(), (int)Evo.getInstance().settingsManager.getSettingByName("GuiBlue").getValDouble());
	}
}
