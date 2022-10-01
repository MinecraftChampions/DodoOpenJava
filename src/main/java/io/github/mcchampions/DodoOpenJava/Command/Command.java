package io.github.mcchampions.DodoOpenJava.Command;

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
    static List<CommandExecutor> commands = new ArrayList<>();

    public static String senderAuthorization;

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public static void initCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token));
        senderAuthorization = BaseUtil.Authorization(clientId,token);
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
        senderAuthorization = BaseUtil.Authorization(clientId,token);
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param authorization authorization
     */
    public static void initCommand(CommandExecutor Class, String authorization) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), authorization);
        senderAuthorization = authorization;
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param authorization authorization
     */
    public static void initCommand(String authorization, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), authorization);
        senderAuthorization = authorization;
    }

    /**
     * 触发命令
     * @param sender 发送者
     * @param mainCommandainCommand 命令名
     * @param args 命令参数
     */
    public static Boolean trigger(CommandSender sender, String mainCommandainCommand, String[] args) {
        boolean hasCommand = false;
        for (CommandExecutor command :commands) {
            if (Objects.equals(command.MainCommand().utf8(), ByteString.encodeUtf8((mainCommandainCommand)).utf8())) {
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
