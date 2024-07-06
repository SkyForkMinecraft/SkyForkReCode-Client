package net.skyfork.ui.gui;

import net.minecraft.client.gui.*;
import net.skyfork.Client;
import net.skyfork.font.FontManager;
import net.skyfork.i18n.LanguageType;

/**
 * @author LangYa
 * @since 2024/07/06/下午8:58
 */
public class GuiLanguage extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground(0);

        FontManager.S50.drawString(Client.name, this.width / 2F - FontManager.S50.getStringWidth(Client.name) / 2F, this.height / 5F, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        int defaultHeight = this.height / 4 + 30;
        int defaultWidth = this.width / 2 - 60;
        int buttonWidth = 120;
        int buttonHeight = 20;
        this.buttonList.add(new GuiButton(0, defaultWidth, defaultHeight, buttonWidth, buttonHeight, "中文 (简体)"));
        this.buttonList.add(new GuiButton(1, defaultWidth, defaultHeight + 25, buttonWidth, buttonHeight, "English (American)"));
        this.buttonList.add(new GuiButton(2, defaultWidth, defaultHeight + 50, buttonWidth, buttonHeight, "Русский (язык)"));
        this.buttonList.add(new GuiButton(3, defaultWidth, defaultHeight + 75, buttonWidth, buttonHeight, "日本語"));
        super.initGui();
    }

    @Override
    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                Client.getInstance().getI18nManager().setLanguageType(LanguageType.Chinese);
                break;
            case 1:
                Client.getInstance().getI18nManager().setLanguageType(LanguageType.English);
                break;
            case 2:
                Client.getInstance().getI18nManager().setLanguageType(LanguageType.Russian);
                break;
            case 3:
                Client.getInstance().getI18nManager().setLanguageType(LanguageType.Japanese);
                break;
        }
    }

}
