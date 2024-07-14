package net.skyfork.ui.clickgui.component.components.sub;

import net.minecraft.client.gui.Gui;
import net.skyfork.font.FontManager;
import net.skyfork.i18n.I18n_Client;
import net.skyfork.module.Module;
import org.lwjgl.opengl.GL11;
import net.skyfork.ui.clickgui.component.Component;
import net.skyfork.ui.clickgui.component.components.Button;

public class VisibleButton extends Component {

    private boolean hovered;
    private final Button parent;
    private int offset;
    private int x;
    private int y;
    private final Module module;

    public VisibleButton(Button button, Module module, int offset) {
        this.parent = button;
        this.module = module;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void renderComponent() {
        Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth()), parent.parent.getY() + offset + 12, this.hovered ? 0xFF222222 : 0xFF111111);
        Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, 0xFF111111);
        GL11.glPushMatrix();

        String s = module.isArray() ? I18n_Client.format("module.clickgui.renderText.canArray") : I18n_Client.format("module.clickgui.renderText.notArray");
        FontManager.S18.drawStringWithShadow(I18n_Client.format("module.clickgui.renderText.arrayRender") + ": " + s, (parent.parent.getX() + 7), (parent.parent.getY() + offset + 2) + 1, -1);
        GL11.glPopMatrix();
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
            module.setArray(!module.isArray());
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
    }
}
