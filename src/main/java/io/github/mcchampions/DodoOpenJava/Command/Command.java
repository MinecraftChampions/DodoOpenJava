package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 命令系统的相关方法
 */
public class Command {
    List<CommandExecutor> commands = new ArrayList<>();

    EventManage e = new EventManage();

    public String SenderAuthorization;

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public void initCommand(CommandExecutor Class, String clientId, String token) {
        this.commands.add(Class);
        e.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token));
        this.SenderAuthorization = BaseUtil.Authorization(clientId,token);
        CommandManage.addCommand(SenderAuthorization,this);
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public void initCommand(String clientId, String token, CommandExecutor... Class) {
        this.commands.addAll(List.of(Class));
        e.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId,token));
        this.SenderAuthorization = BaseUtil.Authorization(clientId,token);
        CommandManage.addCommand(SenderAuthorization,this);
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public void initCommand(CommandExecutor Class, String Authorization) {
        this.commands.add(Class);
        e.registerEvents(new CommandTrigger(), Authorization);
        this.SenderAuthorization = Authorization;
        CommandManage.addCommand(SenderAuthorization,this);
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public void initCommand(String Authorization, CommandExecutor... Class) {
        this.commands.addAll(List.of(Class));
        e.registerEvents(new CommandTrigger(), Authorization);
        this.SenderAuthorization = Authorization;
        CommandManage.addCommand(SenderAuthorization,this);
    }

    /**
     * 触发命令
     * @param sender 发送者
     * @param MainCommand 命令名
     * @param args 命令参数
     */
    public Boolean Trigger(CommandSender sender, String MainCommand, String[] args) {
        boolean hasCommand = false;
        for (CommandExecutor command :commands) {
            if (Objects.equals(command.MainCommand(), MainCommand)) {
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
