package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Utils.MapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CommandManage {
    public static Map<String,Command> CommandSystem = new HashMap<>();

    /**
     * 触发指令
     * @param Authorization Authorization
     * @param sender 发送者
     * @param mainCommand 主命令
     * @param args 参数
     * @return true成功，false代表没有这个
     */
    public static Boolean TriggerAllCommand(String Authorization,CommandSender sender,String mainCommand,String[] args) {
        boolean hasCommand = false;
        for ( List<Object> list: MapUtil.ergodicMaps(CommandSystem)) {
            if (Objects.equals(list.get(0).toString(), Authorization)) {
                if (list.get(1) instanceof Command command) {
                    command.Trigger(sender,mainCommand,args);
                    hasCommand = true;
                }
            }
        }
        return hasCommand;
    }

    /**
     * 初始化控制台命令系统
     */
    public static void initConsoleCommand() {
        CommandTrigger.listenerConsole();
    }

    /**
     * 新增一个指令处理系统
     * @param Authorization Authorization
     * @param command 实例
     */
    public static void addCommand(String Authorization,Command command) {
        CommandSystem.put(Authorization, command);
    }
}
