package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Permissions.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 命令系统的相关方法
 */
public class Command {
    static List<CommandExecutor> commands = new ArrayList<>();

    static EventManage e = new EventManage();

    public static String SenderAuthorization;

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public static void initCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        e.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token));
        SenderAuthorization = BaseUtil.Authorization(clientId,token);
        CommandTrigger.listenerConsole();
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public static void initCommand(String clientId, String token, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        e.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token));
        SenderAuthorization = BaseUtil.Authorization(clientId,token);
        CommandTrigger.listenerConsole();
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public static void initCommand(CommandExecutor Class, String Authorization) {
        commands.add(Class);
        e.registerEvents(new CommandTrigger(), Authorization);
        SenderAuthorization = Authorization;
        CommandTrigger.listenerConsole();
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public static void initCommand(String Authorization, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        e.registerEvents(new CommandTrigger(), Authorization);
        SenderAuthorization = Authorization;
        CommandTrigger.listenerConsole();
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
            if (Objects.equals(command.MainCommand(), MainCommand)) {
                if (User.hasPerm(sender.getSenderDodoId(), command.Permissions())) {
                    command.onCommand(sender, args);
                    hasCommand = true;
                    break;
                }
            }
        }
        return hasCommand;
    }
}
