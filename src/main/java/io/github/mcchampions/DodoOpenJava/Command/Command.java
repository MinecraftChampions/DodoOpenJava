package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Utils;

import java.util.List;
import java.util.Objects;

/**
 * 命令系统的相关方法
 */
public class Command {
    List<CommandExecutor> commands = null;

    EventManage e = new EventManage();

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public void initCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        e.register(new CommandTrigger(), Utils.Authorization(clientId,token));
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token 机器人Token
     */
    public void initCommand(List<CommandExecutor> Class, String clientId, String token) {
        this.commands = Class;
        e.register(new CommandTrigger(), Utils.Authorization(clientId,token));
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public void initCommand(CommandExecutor Class, String Authorization) {
        commands.add(Class);
        e.register(new CommandTrigger(), Authorization);
    }

    /**
     * 初始化命令系统
     * @param Class 命令处理所在的类
     * @param Authorization Authorization
     */
    public void initCommand(List<CommandExecutor> Class, String Authorization) {
        this.commands = Class;
        e.register(new CommandTrigger(), Authorization);
    }

    /**
     * 触发命令
     * @param DodoId 发送者DodoId
     * @param Command 命令名
     * @param args 命令参数
     */
    public Boolean Trigger(String DodoId, String Command, String[] args) {
        Boolean hasCommand = false;
        for (int i = 0; i < commands.size(); i++) {
            CommandExecutor command = commands.get(i);
            if (Objects.equals(command.MainCommand(), Command)) {
                i = commands.size();
                hasCommand = true;
                command.onCommand(DodoId,args);
            }
        }
        return hasCommand;
    }
}
