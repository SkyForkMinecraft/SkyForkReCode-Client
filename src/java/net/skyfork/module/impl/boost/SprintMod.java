package net.skyfork.module.impl.boost;

import net.minecraft.client.settings.KeyBinding;
import net.skyfork.event.EventTarget;
import net.skyfork.event.impl.player.EventUpdate;
import net.skyfork.module.Category;
import net.skyfork.module.Module;

public class SprintMod extends Module {
    public SprintMod() {
        super("module.move.sprint.name", "Keep running.", Category.Boost);
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
