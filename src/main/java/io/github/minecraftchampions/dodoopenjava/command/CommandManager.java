package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.api.CommandSender;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 命令系统的相关方法
 */
@RequiredArgsConstructor
@Slf4j
public class CommandManager {
    private final Map<String, CommandExecutor> commandMap = new ConcurrentHashMap<>();

    private final Map<String, String> commandAliasesMapping = new ConcurrentHashMap<>();

    @Getter
    private final @NonNull Bot bot;

    private final AtomicBoolean isInit = new AtomicBoolean(false);

    @Getter
    private final CommandTrigger commandTrigger = new CommandTrigger(this);

    public boolean isInit() {
        return isInit.get();
    }

    /**
     * 初始化
     */
    private synchronized void init() {
        if (!isInit.compareAndSet(false, true)) {
            return;
        }
        bot.registerListener(commandTrigger);
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
            log.error("注册命令时错误",
                    new CommandRegistrationException("别名包括命令名:" + commandName + ",已取消添加此别名"));
            aliases.remove(commandName);
        }
        if (commandMap.containsKey(commandName)) {
            log.error("注册命令时错误",
                    new CommandRegistrationException("重复的命令名:" + commandName + ";已取消注册"));
            return;
        }
        commandMap.put(commandName, command);
        for (String name : aliases) {
            if (commandMap.containsKey(name)) {
                log.error("注册命令时错误",
                        new CommandRegistrationException("已经注册过命令名为" + name + "的命令，已经取消添加此别名"));
            } else {
                if (commandAliasesMapping.containsKey(name)) {
                    log.error("注册命令时错误",
                            new CommandRegistrationException("已经添加过别名包含" + name + "的命令，已经取消添加别名"));
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
        commandMap.remove(command.getMainCommand());
        for (String str : command.getCommandAliases()) {
            commandAliasesMapping.remove(str);
        }
    }

    /**
     * 注销所有命令
     */
    public void unregisterAllCommands() {
        commandMap.clear();
        commandAliasesMapping.clear();
    }

    /**
     * 触发命令
     *
     * @param sender      发送者
     * @param commandName 命令名
     * @param args        命令参数
     */
    public boolean trigger(@NonNull CommandSender sender, @NonNull String commandName,
                           boolean personalMessage, @NonNull String... args) {
        CommandExecutor command;
        if (commandMap.containsKey(commandName)) {
            command = commandMap.get(commandName);
        } else if (commandAliasesMapping.containsKey(commandName)) {
            command = commandMap.get(commandAliasesMapping.get(commandName));
        } else {
            return false;
        }
        if (!command.allowPersonalChat() && personalMessage) {
            return false;
        }
        command.onCommand(sender, args);
        return true;
    }

    public static class CommandRegistrationException extends RuntimeException {
        public CommandRegistrationException(String message) {
            super(message);
        }
    }
}