package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.api.CommandSender;

import java.util.HashSet;
import java.util.Set;

/**
 * 命令的接口
 *
 * @author qscbm187531
 * @author zimzaza4
 */
public interface CommandExecutor {
    /**
     * 命令名（不带斜杆）
     *
     * @return 主命令
     */
    String getMainCommand();

    /**
     * 获取命令别名
     *
     * @return 别名列表
     */
    default Set<String> getCommandAliases() {
        return new HashSet<>();
    }

    /**
     * 是否允许私聊命令
     *
     * @return boolean
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
