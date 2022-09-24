package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import okio.ByteString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 命令系统的相关方法
 */
public class Command {
    static List<CommandExecutor> commands = new ArrayList<>();

    public static String SenderAuthorization;

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public static void initCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token));
        SenderAuthorization = BaseUtil.Authorization(clientId,token);
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public static void initCommand(String clientId, String token, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token));
        SenderAuthorization = BaseUtil.Authorization(clientId,token);
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public static void initCommand(CommandExecutor Class, String Authorization) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), Authorization);
        SenderAuthorization = Authorization;
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public static void initCommand(String Authorization, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), Authorization);
        SenderAuthorization = Authorization;
    }

    /**
     * 触发命令
     * @param sender 发送者
     * @param MainCommand 命令名
     * @param args 命令参数
     */
    public static Boolean Trigger(CommandSender sender, String MainCommand, String[] args) {
        boolean hasCommand = false;
        for (CommandExecutor command :commands) {
            if (Objects.equals(command.MainCommand().utf8(), ByteString.encodeUtf8((MainCommand)).utf8())) {
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
