package net.skyfork.module;

import net.skyfork.i18n.I18n_Client;

public enum Category {
    Boost("module.category.boost"),
    Client("module.category.client"),
    Render("module.category.render"),
    Player("module.category.player");

    Category(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return I18n_Client.format(name);
    }

}
