package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Api.Version;
import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令系统的相关方法
 * @author qscbm187531
 */
public class Command {
    public static List<CommandExecutor> commands = new ArrayList<>();

    public static String Authorization;

    /**
     * 初始化命令系统(默认V2,不考虑使用V1)
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public static void regsiterCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token), Version.V2);
        Authorization = BaseUtil.Authorization(clientId,token);
    }

    /**
     * 初始化命令系统(默认V2,不考虑使用V1)
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public static void regsiterCommand(String clientId, String token, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token), Version.V2);
        Authorization = BaseUtil.Authorization(clientId,token);
    }

    /**
     * 初始化命令系统(默认V2,不考虑使用V1)
     * @param Class 命令处理所在的类
     * @param authorization authorization
     */
    public static void regsiterCommand(CommandExecutor Class, String authorization) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), authorization, Version.V2);
        Authorization = authorization;
    }

    /**
     * 初始化命令系统(默认V2,不考虑使用V1)
     * @param Class 命令处理所在的类
     * @param authorization authorization
     */
    public static void regsiterCommand(String authorization, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), authorization, Version.V2);
        Authorization = authorization;
    }
}
