package com.evo.core.gui.click.components.buttons;

import static com.evo.core.gui.click.components.buttons.ModuleButton.BACKGROUND;

import com.evo.core.gui.base.AbstractButton;
import com.evo.core.setting.Setting;
import com.evo.util.render.RenderUtil;
import com.evo.util.render.ScaleUtil;
import com.evo.util.text.FormatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;

public class EnumButton extends AbstractButton {
    private final Setting<Enum> setting;

    public EnumButton(Setting<Enum> setting) {
        super(setting.getName());

        this.setting = setting;
    }

    @Override
    public void render(int mouseX, int mouseY) {
        RenderUtil.drawRectangle(x, y, width, height, BACKGROUND.getRGB());
        getEvo().getFontManager().drawNormalizedString(
                setting.getName() + ": " + ChatFormatting.GRAY + FormatUtil.formatName(setting.getValue().name()),
                (float) (x + 2.3),
                (float) ScaleUtil.alignH(y, height),
                -1
        );
    }

    @Override
    public void onClick(int button) {
        setting.setValue(Setting.increase(setting.getValue()));
    }
}
