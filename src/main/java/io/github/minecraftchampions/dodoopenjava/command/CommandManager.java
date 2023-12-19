package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.Bot;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Objects;

/**
 * 命令系统的相关方法
 */
@Getter
@RequiredArgsConstructor
public class CommandManager {
    private final HashSet<CommandExecutor> commands = new HashSet<>();

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
        commands.add(command);
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
        commands.remove(command);
    }

    /**
     * 注销所有命令
     */
    public void unregisterAllCommands() {
        commands.clear();
    }

    /**
     * 触发命令
     *
     * @param sender          发送者
     * @param mainCommandName 命令名
     * @param args            命令参数
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean trigger(@NonNull CommandSender sender, @NonNull String mainCommandName,
                           @NonNull String... args) {
        boolean hasCommand = false;
        for (CommandExecutor command : commands) {
            if (Objects.equals(command.getMainCommand(), mainCommandName)) {
                if (command.getPermission() == null || command.getPermission().isEmpty() || sender.hasPermission(command.getPermission())) {
                    command.onCommand(sender, args);
                    hasCommand = true;
                    break;
                }
            }
        }
        return hasCommand;
    }
}