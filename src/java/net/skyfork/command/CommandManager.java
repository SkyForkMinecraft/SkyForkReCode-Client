package net.skyfork.command;

import net.skyfork.event.EventTarget;
import lombok.Getter;
import lombok.Setter;
import net.skyfork.Client;
import net.skyfork.command.impl.ToggleCommand;
import net.skyfork.event.impl.misc.EventChat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LvZiQiao，LangYa
 */

@Getter
@Setter
public class CommandManager {
    private final HashMap<String, Command> commandMap;
    public List<String> tabComplete;

    public CommandManager() {
        commandMap = new HashMap<>();
        tabComplete = new ArrayList<>();
        registerCommands(
                new ToggleCommand("toggle")
        );
        Client.eventManager.register(new CommandHandler());
    }

    @EventTarget
    private void onChat(EventChat event) {
        String command = event.getMessage();
        if (!command.startsWith(".")) return;

        final boolean skipped = command.endsWith(" "); // 末尾是空格的需要特判(当最后一项为全的时候判断返回下一项的所有预设值，反之不返回任何值)
        final String[] args = command.toLowerCase().replaceFirst(".", "").split(" ");

        final Command tryCommand = getCommand(args[0]);

        if (args.length == 1) {
            if (tryCommand == null) {
                if (!skipped) {
                    tabComplete = commandMap.keySet().stream().filter(name -> name.startsWith(args[0])).map(name -> "." + name).collect(Collectors.toList());
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

    private void registerCommands(Command ... commands) {
        for (Command command : commands) {
            registerCommand(command);
        }
    }

    private void registerCommand(Command command) {
        commandMap.put(command.name, command);
    }

    public Command getCommand(String name) {
        return commandMap.get(name.replaceAll("_", "").toLowerCase());
    }
}
