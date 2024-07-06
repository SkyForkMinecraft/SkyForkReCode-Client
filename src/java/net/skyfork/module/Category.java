package net.skyfork.module;

import lombok.Getter;

public enum Category {
    Combat("战斗"),
    Move("移动"),
    Client("客户端"),
    Render("视觉"),
    Player("玩家");

    Category(String name) {
        this.name = name;
    }

    @Getter
    private String name;


}
