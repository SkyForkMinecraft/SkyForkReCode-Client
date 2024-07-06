package net.skyfork.ui.clickgui.component;

import lombok.Getter;
import lombok.Setter;
import net.skyfork.Client;
import net.skyfork.font.FontManager;
import net.skyfork.module.Category;
import net.skyfork.module.Module;
import net.skyfork.util.render.RoundedUtil;
import net.skyfork.ui.clickgui.component.components.Button;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


@Getter
@Setter
public class Frame {

    public ArrayList<Component> components;
    public Category category;
    private boolean open;
    private final int width;
    private int y;
    private int x;
    private final int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;

    public Frame(Category cat) {
        this.components = new ArrayList<>();
        this.category = cat;
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = false;
        this.isDragging = false;
        int tY = this.barHeight;
        for (Module module : Client.getInstance().getModuleManager().getModulesByCategory(category)) {
            this.components.add(new Button(module, this, tY));
            tY += 12;
        }
    }


    public void renderFrame() {

        RoundedUtil.drawRound(x + 0.7F, this.y + 3.9F, 86.5F, 11,3, new Color(27, 30, 30));

        FontManager.S18.drawStringWithShadow(this.category.getName(), (this.x + 2)  + 4, (this.y + 4f) , 0xFFFFFFFF);
        FontManager.S18.drawStringWithShadow(this.open ? "-" : "+", (this.x + this.width - 10)  + 3, (this.y + 4.5f) , -1);
        if (this.open) {
            if (!this.components.isEmpty()) {
                for (Component component : components) {
                    component.renderComponent();
                }
            }
        }

    }

    public void refresh() {
        int off = this.barHeight;
        for (Component comp : components) {
            comp.setOff(off);
            off += comp.getHeight();
        }
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - dragX);
            this.setY(mouseY - dragY);
        }
    }

    public boolean isWithinHeader(int x, int y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
	}

}
