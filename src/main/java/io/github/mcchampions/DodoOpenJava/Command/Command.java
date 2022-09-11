package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Permissions.User;

import java.util.List;
import java.util.Objects;

/**
 * 命令系统的相关方法
 */
public class Command {
    List<CommandExecutor> commands = null;

    EventManage e = new EventManage();

    public static String SenderAuthorization;

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public void initCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        e.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token));
        SenderAuthorization = BaseUtil.Authorization(clientId,token);
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public void initCommand(String clientId, String token, CommandExecutor... Class) {
        this.commands = List.of(Class);
        e.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token));
        SenderAuthorization = BaseUtil.Authorization(clientId,token);
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public void initCommand(CommandExecutor Class, String Authorization) {
        commands.add(Class);
        e.registerEvents(new CommandTrigger(), Authorization);
        SenderAuthorization = Authorization;
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public void initCommand(String Authorization, CommandExecutor... Class) {
        this.commands = List.of(Class);
        e.registerEvents(new CommandTrigger(), Authorization);
        SenderAuthorization = Authorization;
    }

    /**
     * 触发命令
     * @param sender 发送者
     * @param Command 命令名
     * @param args 命令参数
     */
    public Boolean Trigger(CommandSender sender, String Command, String[] args) {
        Boolean hasCommand = false;
        for (int i = 0; i < commands.size(); i++) {
            CommandExecutor command = commands.get(i);
            if (Objects.equals(command.MainCommand(), Command)) {
                if (User.hasPerm(sender.getSenderDodoId(), command.Permissions())) {
                    i = commands.size();
                    hasCommand = true;
                    command.onCommand(sender, args);
                }
            }
        }
        return hasCommand;
    }
}
