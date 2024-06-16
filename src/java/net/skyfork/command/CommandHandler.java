package net.skyfork.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LangYa
 * @since 2024/6/9 下午8:18
 */

public class CommandHandler {
    public static List<String> tabComplete;
    public static CommandManager commandManager;

    public static void runComplete(String command) {
        if (!command.startsWith(".")) return;

        // 末尾是空格的需要特判(当最后一项为全的时候判断返回下一项的所有预设值，反之不返回任何值)
        boolean skipped = command.endsWith(" ");
        String[] args = command.toLowerCase().replaceFirst(".", "").split(" ");

        Command tryCommand = commandManager.getCommand(args[0]);

        if (args.length == 1) {
            if (tryCommand == null) {
                if (!skipped) {
                    tabComplete = commandManager.getCommandMap().keySet().stream().filter(name -> name.startsWith(args[0])).map(name -> "." + name).collect(Collectors.toList());
                } else {
                    tabComplete = new ArrayList<>();
                }
            } else if (skipped) {
                tabComplete = tryCommand.tabComplete(args, true);
            } else {
                tabComplete = new ArrayList<>();
            }
        } else {
            if (tryCommand != null) {
                tabComplete = tryCommand.tabComplete(args, skipped);
            } else {
                tabComplete = new ArrayList<>();
            }
        }
    }

    public static boolean canAutoComplete(String command) {
        runComplete(command);
        return command.startsWith(".") && tabComplete.size() != 0;
    }
}
