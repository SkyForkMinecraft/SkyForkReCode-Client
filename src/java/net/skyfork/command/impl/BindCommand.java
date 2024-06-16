package net.skyfork.command.impl;

import net.skyfork.Client;
import net.skyfork.command.Command;
import net.skyfork.i18n.I18n;
import net.skyfork.module.Module;
import net.skyfork.util.misc.ClientUtil;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BindCommand extends Command {
    public BindCommand(String name) {
        super(name);
    }

    @Override
    public void execute(String[] parma) {
        if (parma.length > 1) {
            Module module = Client.moduleManager.getModule(parma[1]);

            if (module == null) {
                ClientUtil.chatPrefix(I18n.format("command.bind.error1"));
            } else {
                if (parma.length > 2) {
                    final int keyIndex = Keyboard.getKeyIndex(parma[2].toUpperCase());
                    if (keyIndex != 0) {
                        module.setKey(keyIndex);
                        ClientUtil.chatPrefix(I18n.format("command.bind.success1"));
                    } else {
                        module.setKey(0);
                        ClientUtil.chatPrefix(I18n.format("command.bind.success2"));
                    }
                } else {
                    module.setKey(0);
                    ClientUtil.chatPrefix(I18n.format("command.bind.success2"));
                }
            }
        } else {
            ClientUtil.chatPrefix(I18n.format("command.bind.error2"));
        }
    }

    @Override
    protected List<String> getComplete(int length) {
        if (length == 1) {
            return Client.moduleManager.getModules().stream().map(Module::getName).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
