package net.skyfork.command;

import net.skyfork.event.EventManager;
import lombok.Getter;
import lombok.Setter;
import net.skyfork.command.impl.ToggleCommand;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author LvZiQiao，LangYa
 */

@Getter
@Setter
public class CommandManager {
    private final HashMap<String, Command> commandMap;

    public CommandManager() {
        commandMap = new HashMap<>();
        CommandHandler.commandManager = this;
        CommandHandler.tabComplete = new ArrayList<>();
        registerCommands(
                new ToggleCommand("toggle")
        );
        EventManager.register(new CommandHandler());
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
