package net.skyfork.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.skyfork.font.FontManager;

import java.awt.*;

/**
 * @author LangYa
 * @since 2024/07/06/下午9:17
 */
public class ClientButton extends GuiButton {

    protected int buttonColor = new Color(0,0,0,120).getRGB();
    protected int buttonHoverColor = new Color(0,0,0,180).getRGB();

    public ClientButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
        this.width = 120;
        this.height = 20;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        Gui.drawRect2(this.xPosition,this.yPosition,this.width,this.height,hovered ? buttonHoverColor : buttonColor);
        FontManager.S18.drawStringWithShadow(this.displayString,this.xPosition + this.width / 4F, this.yPosition + (this.height - 8) / 2F,-1);
    }
}
