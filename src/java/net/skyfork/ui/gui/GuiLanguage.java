package net.skyfork.ui.gui;

import net.minecraft.client.gui.*;
import net.skyfork.Client;
import net.skyfork.font.FontManager;
import net.skyfork.i18n.LanguageType;
import net.skyfork.ui.ClientButton;

/**
 * @author LangYa
 * @since 2024/07/06/下午8:58
 */
public class GuiLanguage extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground(0);

        FontManager.S50.drawString(Client.displayName, this.width / 2F - FontManager.S50.getStringWidth(Client.displayName) / 2F, this.height / 5F, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        int defaultHeight = this.height / 4 + 30;
        int defaultWidth = this.width / 2 - 60;
        this.buttonList.add(new ClientButton(0, defaultWidth, defaultHeight, "中文 (简体)"));
        this.buttonList.add(new ClientButton(1, defaultWidth, defaultHeight + 25, "English (American)"));
        this.buttonList.add(new ClientButton(2, defaultWidth, defaultHeight + 50, "Русский (язык)"));
        this.buttonList.add(new ClientButton(3, defaultWidth, defaultHeight + 75, "日本語"));
        this.buttonList.add(new ClientButton(4, defaultWidth, defaultHeight + 100, "العربية (اللغة العربية)"));
        super.initGui();
    }

    @Override
    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                Client.getInstance().getI18nManager().loadProperties(LanguageType.Chinese);
                break;
            case 1:
                Client.getInstance().getI18nManager().loadProperties(LanguageType.English);
                break;
            case 2:
                Client.getInstance().getI18nManager().loadProperties(LanguageType.Russian);
                break;
            case 3:
                Client.getInstance().getI18nManager().loadProperties(LanguageType.Japanese);
                break;
            case 4:
                Client.getInstance().getI18nManager().loadProperties(LanguageType.Arabian);
                break;
        }
    }

}
