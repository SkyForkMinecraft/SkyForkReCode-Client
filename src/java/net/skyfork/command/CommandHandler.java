package net.skyfork.command;

import net.skyfork.event.EventTarget;
import net.skyfork.Client;
import net.skyfork.event.impl.misc.EventChat;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author LangYa
 * @since 2024/6/9 下午8:18
 */

public class CommandHandler {

    @EventTarget
    private void onChat(EventChat event) {
        String command = event.getMessage();
        if (!command.startsWith(".")) return;

        // 末尾是空格的需要特判(当最后一项为全的时候判断返回下一项的所有预设值，反之不返回任何值)
        boolean skipped = command.endsWith(" ");
        String[] args = command.toLowerCase().replaceFirst(".", "").split(" ");

        CommandManager commandManager =Client.commandManager;
        Command tryCommand = commandManager.getCommand(args[0]);

        if (args.length == 1) {
            if (tryCommand == null) {
                if (!skipped) {
                    commandManager.tabComplete = commandManager.getCommandMap().keySet().stream().filter(name -> name.startsWith(args[0])).map(name -> "." + name).collect(Collectors.toList());
                } else {
                    commandManager.tabComplete = new ArrayList<>();
                }
            } else if (skipped) {
                commandManager.tabComplete = tryCommand.tabComplete(args, true);
            } else {
                commandManager.tabComplete = new ArrayList<>();
            }
        } else {
            if (tryCommand != null) {
                commandManager.tabComplete = tryCommand.tabComplete(args, skipped);
            } else {
                commandManager.tabComplete = new ArrayList<>();
            }
        }
    }

}
