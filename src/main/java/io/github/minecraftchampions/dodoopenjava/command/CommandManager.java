package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.Bot;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Set;

/**
 * 命令系统的相关方法
 */
@Getter
@RequiredArgsConstructor
public class CommandManager {
    private final HashMap<String, CommandExecutor> commands = new HashMap<>();


    private final HashMap<String, String> commandAliasesMapping = new HashMap<>();

    private final @NonNull Bot bot;

    private boolean isInit = false;

    /**
     * 初始化
     */
    private void init() {
        isInit = true;
        bot.registerListener(new CommandTrigger(this));
    }

    /**
     * 注册命令
     *
     * @param command 命令实例
     */
    public void registerCommand(@NonNull CommandExecutor command) {
        String commandName = command.getMainCommand();
        Set<String> aliases = command.getCommandAliases();
        if (aliases.contains(commandName)) {
            new RuntimeException("别名包括命令名:" + commandName + ",已取消添加此别名").printStackTrace();
            aliases.remove(commandName);
        }
        if (commands.containsKey(commandName)) {
            new RuntimeException("重复的命令名:" + commandName + ";已取消注册").printStackTrace();
            return;
        }
        commands.put(commandName, command);
        for (String name : aliases) {
            if (commands.containsKey(name)) {
                new RuntimeException("已经注册过命令名为" + name + "的命令，已经取消添加此别名").printStackTrace();
            } else {
                if (commandAliasesMapping.containsKey(name)) {
                    new RuntimeException("已经添加过别名包含" + name + "的命令，已经取消添加别名").printStackTrace();
                } else {
                    commandAliasesMapping.put(name, commandName);
                }
            }
        }
        if (!isInit()) {
            init();
        }
    }

    /**
     * 注销命令
     *
     * @param command 命令实例
     */
    public void unregisterCommand(@NonNull CommandExecutor command) {
        commands.remove(command.getMainCommand());
        for (String str : command.getCommandAliases()) {
            commandAliasesMapping.remove(str);
        }
    }

    /**
     * 注销所有命令
     */
    public void unregisterAllCommands() {
        commands.clear();
        commandAliasesMapping.clear();
    }

    /**
     * 触发命令
     *
     * @param sender      发送者
     * @param commandName 命令名
     * @param args        命令参数
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean trigger(@NonNull CommandSender sender, @NonNull String commandName,
                           @NonNull String... args) {
        CommandExecutor command;
        if (commands.containsKey(commandName)) {
            command = commands.get(commandName);
        } else if (commandAliasesMapping.containsKey(commandName)) {
            command = commands.get(commandAliasesMapping.get(commandName));
        } else {
            return false;
        }
        if (command.getPermission() == null || command.getPermission().isEmpty() || sender.hasPermission(command.getPermission())) {
            command.onCommand(sender, args);
        }
        return true;
    }
}