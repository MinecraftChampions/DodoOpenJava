package io.github.minecraftchampions.dodoopenjava.command;

import java.util.HashSet;
import java.util.Set;

/**
 * 命令的接口
 */
public interface CommandExecutor {
    /**
     * 命令名（不带斜杆）
     *
     * @return 主命令
     */
    String getMainCommand();

    /**
     * 命令别名
     */
    default Set<String> getCommandAliases() {
        return new HashSet<>();
    }

    /**
     * 需要的权限
     *
     * @return 权限
     */
    String getPermission();

    /**
     * 是否允许私聊命令
     */
    default boolean allowPersonalChat() {
        return false;
    }

    /**
     * 命令处理
     *
     * @param sender 发送者
     * @param args   参数
     */
    void onCommand(CommandSender sender, String[] args);
}
