package net.skyfork.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LvZiQiao
 */

public abstract class Command {
    public final String name;
    public String description;

    public Command(String name) { // TODO : 做一指令多触发，增加config指令和reload指令
        this.name = name;
    }

    /**
     * @param parma be like : [".toggle","Sprint","on"]
     */
    public abstract void execute(String[] parma);

    public List<String> tabComplete(String[] args, boolean skipped) {
        if (args.length == 1) return skipped ? getComplete(1) : new ArrayList<>(); // 特判
        final String lastString = args[args.length - 1];
        final List<String> basicFinder = getComplete(args.length - 1).stream().filter(name -> name.toLowerCase().startsWith(lastString.toLowerCase())).collect(Collectors.toList());

        if (skipped) {
            if (basicFinder.size() > 0 && basicFinder.get(0).equalsIgnoreCase(lastString.toLowerCase())) {
                return getComplete(args.length);
            }
        } else {
            return basicFinder;
        }

        return new ArrayList<>();
    }

    /**
     * @param length Example : .toggle Sprint on ---> 1 : Sprint,2 : on/off
     */
    protected abstract List<String> getComplete(int length);
}
