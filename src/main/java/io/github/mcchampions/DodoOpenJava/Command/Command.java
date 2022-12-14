package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Api.Version;
import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import okio.ByteString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 命令系统的相关方法
 * @author qscbm187531
 */
public class Command {
    public static List<CommandExecutor> commands = new ArrayList<>();

    public static String Authorization;

    /**
     * 初始化命令系统(默认V2,不考虑使用V1)
     *
     * @param Class    命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token    机器人Token
     */
    public static void registerCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId, token), Version.V2);
        Authorization = BaseUtil.Authorization(clientId, token);
    }

    /**
     * 初始化命令系统(默认V2,不考虑使用V1)
     *
     * @param Class    命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token    机器人Token
     */
    public static void registerCommand(String clientId, String token, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId, token), Version.V2);
        Authorization = BaseUtil.Authorization(clientId, token);
    }

    /**
     * 初始化命令系统(默认V2,不考虑使用V1)
     *
     * @param Class         命令处理所在的类
     * @param authorization authorization
     */
    public static void registerCommand(CommandExecutor Class, String authorization) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), authorization, Version.V2);
        Authorization = authorization;
    }

    /**
     * 初始化命令系统(默认V2,不考虑使用V1)
     *
     * @param Class         命令处理所在的类
     * @param authorization authorization
     */
    public static void registerCommand(String authorization, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), authorization, Version.V2);
        Authorization = authorization;
    }

    /**
     * 触发命令
     *
     * @param sender 发送者
     * @param mainCommandName 命令名
     * @param args 命令参数
     */
    @SuppressWarnings("UnusedReturnValue")
    public static Boolean trigger(CommandSender sender, String mainCommandName, String... args) {
        boolean hasCommand = false;
        for (CommandExecutor command : commands) {
            if (Objects.equals(command.MainCommand().utf8(), ByteString.encodeUtf8((mainCommandName)).utf8())) {
                if (sender.hasPermission(command.Permission())) {
                    command.onCommand(sender, args);
                    hasCommand = true;
                    break;
                }
            }
        }
        return hasCommand;
    }
}