package net.skyfork.module.legit.movement;

import net.minecraft.client.settings.KeyBinding;
import net.skyfork.event.EventTarget;
import net.skyfork.event.impl.player.EventUpdate;
import net.skyfork.i18n.I18n;
import net.skyfork.module.Category;
import net.skyfork.module.Module;

public class SprintMod extends Module {
    public SprintMod() {
        super(I18n.format("module.legit.movement.sprint.name"), "Keep running.", Category.Move);
    }

    @Override
    public void onDisable() {
        KeyBinding.updateKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode());
    }

    @EventTarget
    private void onUpdate(EventUpdate event) {
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
    }
}
