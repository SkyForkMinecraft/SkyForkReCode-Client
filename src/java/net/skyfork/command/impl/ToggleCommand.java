package net.skyfork.command.impl;

import net.skyfork.Client;
import net.skyfork.command.Command;
import net.skyfork.i18n.I18n;
import net.skyfork.module.Module;
import net.skyfork.util.misc.ClientUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LvZiQiao，LangYa
 */

public class ToggleCommand extends Command {
    public ToggleCommand(String name) {
        super(name);
    }

    @Override
    public void execute(String[] parma) {
        if (parma.length > 1) {
            Module module = Client.moduleManager.getModule(parma[1]);

            if (module == null) {
                ClientUtil.chatPrefix(I18n.format("command.toggle.error1"));
            } else {
                if (parma.length > 2) {
                    if (parma[2].equalsIgnoreCase("on") || parma[2].equalsIgnoreCase("off")) {
                        final boolean targetState = parma[2].equalsIgnoreCase("on");

                        if (module.isState() != targetState)
                            module.toggle();
                    } else {
                        ClientUtil.chatPrefix(I18n.format("command.toggle.error2"));
                    }
                } else {
                    module.toggle();
                }
            }
        } else {
            ClientUtil.chatPrefix(I18n.format("command.toggle.error2"));
        }
    }

    @Override
    protected List<String> getComplete(int length) {
        if (length == 1) {
            return Client.moduleManager.getModules().stream().map(Module::getName).collect(Collectors.toList());
        } else if (length == 2) {
            return Arrays.asList(
                    "on",
                    "off"
            );
        }

        return new ArrayList<>();
    }
}
