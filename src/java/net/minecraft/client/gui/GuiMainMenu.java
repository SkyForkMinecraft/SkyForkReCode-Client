package net.minecraft.client.gui;

import net.skyfork.Client;
import net.skyfork.font.FontManager;
import net.skyfork.ui.gui.GuiLanguage;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {

    @Override
    public void initGui() {
        int defaultHeight = this.height / 4 + 30;
        int defaultWidth = this.width / 2 - 60;
        int buttonWidth = 120;
        int buttonHeight = 20;

        this.buttonList.add(new GuiButton(0, defaultWidth, defaultHeight, buttonWidth, buttonHeight, "Single Player"));
        this.buttonList.add(new GuiButton(1, defaultWidth, defaultHeight + 25, buttonWidth, buttonHeight, "Multi Player"));
        this.buttonList.add(new GuiButton(2, defaultWidth, defaultHeight + 50, buttonWidth, buttonHeight, "Client Settings"));
        this.buttonList.add(new GuiButton(3, defaultWidth, defaultHeight + 75, buttonWidth, buttonHeight, "Game Options"));
        this.buttonList.add(new GuiButton(4, defaultWidth, defaultHeight + 100, buttonWidth, buttonHeight, "Shut Down"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground(0);

        FontManager.S50.drawString(Client.name, this.width / 2F - FontManager.S50.getStringWidth(Client.name) / 2F, this.height / 5F, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 1:
                mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 2:
                mc.displayGuiScreen(new GuiLanguage());
                break;
            case 3:
                mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                break;
            case 4:
                mc.shutdown();
                break;
        }
    }
}
