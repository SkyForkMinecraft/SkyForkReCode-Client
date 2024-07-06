package net.skyfork.ui.clickgui.component.components.sub;

import net.minecraft.client.gui.Gui;
import net.skyfork.font.FontManager;
import net.skyfork.ui.clickgui.ClickGuiScreen;
import net.skyfork.value.impl.BooleanValue;
import org.lwjgl.opengl.GL11;
import net.skyfork.ui.clickgui.component.components.Button;
import net.skyfork.ui.clickgui.component.Component;

import java.awt.*;

public class Checkbox extends Component {

    private boolean hovered;
    private final BooleanValue op;
    private final Button parent;
    private int offset;
    private int x;
    private int y;

    public Checkbox(BooleanValue option, Button button, int offset) {
        this.op = option;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void renderComponent() {
        Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth()), parent.parent.getY() + offset + 12, this.hovered ? ClickGuiScreen.color : 0xFF111111);
        Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, 0xFF111111);
        GL11.glPushMatrix();

        FontManager.S18.drawStringWithShadow(this.op.getName(), (parent.parent.getX() + 10 + 4)  + 5, (parent.parent.getY() + offset + 2)  , -1);
        GL11.glPopMatrix();
        Gui.drawRect(parent.parent.getX() + 3 + 4, parent.parent.getY() + offset + 3, parent.parent.getX() + 9 + 4, parent.parent.getY() + offset + 9, new Color(0xFF999999).getRGB());
        if (this.op.getValue())
            Gui.drawRect(parent.parent.getX() + 4 + 4, parent.parent.getY() + offset + 4, parent.parent.getX() + 8 + 4, parent.parent.getY() + offset + 8, ClickGuiScreen.color);
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = isMouseOnButton(mouseX, mouseY);
        this.y = parent.parent.getY() + offset;
        this.x = parent.parent.getX();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.op.setValue(!this.op.getValue());
        }
    }

    public boolean isMouseOnButton(int x, int y) {
		return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
	}
}
