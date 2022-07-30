package com.evo.core.gui.click.components.buttons;

import static com.evo.core.gui.click.components.buttons.ModuleButton.BACKGROUND;

import com.evo.core.gui.base.AbstractButton;
import com.evo.core.setting.Setting;
import com.evo.util.render.RenderUtil;
import com.evo.util.render.ScaleUtil;

public class BooleanButton extends AbstractButton {
    protected final Setting<Boolean> setting;

    public BooleanButton(Setting<Boolean> setting) {
        super(setting.getName());

        this.setting = setting;
    }

    @Override
    public void render(int mouseX, int mouseY) {
        RenderUtil.drawRectangle(x, y, width, height, setting.getValue() ? BACKGROUND.darker().darker().getRGB() : BACKGROUND.getRGB());
        getEvo().getFontManager().drawNormalizedString(name, (float) (x + 3.0), (float) ScaleUtil.alignH(y, height), -1);
    }

    @Override
    public void onClick(int button) {
        if (button == 0) {
            setting.setValue(!setting.getValue());
        }
    }
}
