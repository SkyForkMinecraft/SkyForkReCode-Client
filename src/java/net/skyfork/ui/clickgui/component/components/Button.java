package net.skyfork.ui.clickgui.component.components;

import net.minecraft.client.gui.Gui;
import net.skyfork.font.FontManager;
import net.skyfork.module.Module;
import net.skyfork.ui.clickgui.ClickGuiScreen;
import net.skyfork.ui.clickgui.component.Component;
import net.skyfork.ui.clickgui.component.Frame;
import net.skyfork.ui.clickgui.component.components.sub.*;
import net.skyfork.ui.clickgui.component.components.sub.Checkbox;
import net.skyfork.util.render.RoundedUtil;
import net.skyfork.value.AbstractValue;
import net.skyfork.value.impl.*;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class Button extends Component {

    public Module module;
    public Frame parent;
    public int offset;
    private boolean isHovered;
    private final ArrayList<Component> subcomponents;
    public boolean open;
    private final int height;
    private boolean end;

    public Button(Module module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;
        this.end = end;
        this.subcomponents = new ArrayList<>();
        this.open = false;
        height = 12;
        int opY = offset + 12;
        if (!module.getValues().isEmpty()) {
            for (AbstractValue<?> value : module.getValues()) {
                if (value instanceof StringValue) {
                    this.subcomponents.add(new ModeButton(this, (StringValue) value, opY));
                    opY += 12;
                }
                if (value instanceof NumberValue) {
                    Slider slider = new Slider((NumberValue) value, this, opY);
                    this.subcomponents.add(slider);
                    opY += 12;
                }
                if (value instanceof BooleanValue) {
                    Checkbox check = new Checkbox((BooleanValue) value, this, opY);
                    this.subcomponents.add(check);
                    opY += 12;
                }
            }
        }
        this.subcomponents.add(new Keybind(this, opY));
        this.subcomponents.add(new VisibleButton(this, module, opY));
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
        int opY = offset + 12;
        for (Component comp : this.subcomponents) {
            comp.setOff(opY);
            opY += 12;
        }
    }

    @Override
    public void renderComponent() {

        GL11.glPushMatrix();

        if (module.isState()) {
            RoundedUtil.drawRound(parent.getX() + 1, end ? this.parent.getY() + this.offset - 2.2F : this.parent.getY() + this.offset + 1F, 86,end ? 14 : 11,
                    end ? 3 : 0, new Color(0,0,0,60));
            RoundedUtil.drawRound(parent.getX() + 1, end ? this.parent.getY() + this.offset - 2.2F : this.parent.getY() + this.offset + 1F, 86,end ? 14 : 11,
                    end ? 3 : 0, new Color(100, 105, 255));

        } else {
            Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset,
                    new Color(0,0,0,60).getRGB());
        }

        GL11.glTranslated(0,end ? -2 : 0,0);
        FontManager.S18.drawStringWithShadow(module.getName(), (parent.getX() + 2) , (parent.getY() + offset + 2), -1);

        if (this.subcomponents.size() > 2) FontManager.S18.drawStringWithShadow(this.open ? "-" : "+", (parent.getX() + parent.getWidth() - 10) , (parent.getY() + offset + 2)  , -1);
        GL11.glPopMatrix();

        if (this.open) {
            if (!this.subcomponents.isEmpty()) {
                for (Component comp : this.subcomponents) {
                    comp.renderComponent();
                }
                Gui.drawRect(parent.getX() + 2, parent.getY() + this.offset + 12, parent.getX() + 3, parent.getY() + this.offset + ((this.subcomponents.size() + 1) * 12), ClickGuiScreen.color);
            }
        }
    }

    @Override
    public int getHeight() {
        if (this.open) {
            return (12 * (this.subcomponents.size() + 1));
        }
        return 12;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.isHovered = isMouseOnButton(mouseX, mouseY);
        if (!this.subcomponents.isEmpty()) {
            for (Component comp : this.subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isMouseOnButton(mouseX, mouseY) && button == 0) {
            module.toggle();
        }
        if (isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        for (Component comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        for (Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void keyTyped(char typedChar, int key) {
        for (Component comp : this.subcomponents) {
            comp.keyTyped(typedChar, key);
        }
    }

    public boolean isMouseOnButton(int x, int y) {
		return x > parent.getX() && x < parent.getX() + parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
	}
}
