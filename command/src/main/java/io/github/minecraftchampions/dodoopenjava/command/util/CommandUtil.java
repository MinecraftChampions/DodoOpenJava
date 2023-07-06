package io.github.minecraftchampions.dodoopenjava.command.util;

import io.github.minecraftchampions.dodoopenjava.command.Command;
import io.github.minecraftchampions.dodoopenjava.command.CommandExecutor;
import io.github.minecraftchampions.dodoopenjava.command.CommandSender;
import io.github.minecraftchampions.dodoopenjava.utils.MapUtil;
import okio.ByteString;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 更快捷的命令注册(闲着没事写的)
 */
public class CommandUtil {
    /**
     * 注册主命令(会自动进行子命令匹配，不需要额外加入子命令触发)
     * @param clazz 命令处理的方法所在类(命令处理器方法须非静态)
     */
    public static void registerMainCommand(Class<?> clazz, String authorization) {
        MainCommand mainCommand = null;
        for (Method method : clazz.getMethods()) {
            for (Annotation tag : method.getAnnotations()) {
                if (tag instanceof MainCommand command) {
                    mainCommand = command;
                    break;
                }
            }
            if (mainCommand != null) {
                String command = mainCommand.command();
                String permission = mainCommand.permission();
                Command.registerCommand(new CommandExecutor() {
                    @Override
                    public String getMainCommand() {
                        return command;
                    }

                    @Override
                    public String getPermission() {
                        return permission;
                    }

                    @Override
                    public void onCommand(CommandSender sender, String[] args) {
                        try {
                            method.invoke(this,sender,args);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                        onSubCommand(command,sender,args);
                    }
                },authorization);
                mainCommand = null;
            }
        }
    }

    public static Map<String[],Method> subCommandMethods = new HashMap<>();

    /** 匹配子命令
     *
     * @param mainCommand 主命令
     * @param sender 发送者
     * @param args 参数
     */
    public static void onSubCommand(String mainCommand,CommandSender sender,String[] args) {
        Method method = null;
        for (List<Object> list : MapUtil.ergodicMaps(subCommandMethods)) {
            String[] strings = (String[]) list.get(0);
            if (Objects.equals(strings[0], mainCommand)) {
                if (Objects.equals(args[0], strings[2])) {
                    if (sender.hasPermission(strings[1])) {
                        method = (Method) list.get(1);
                    }
                    break;
                }
            }
        }

        if (method != null) {
            try {
                method.invoke(null,sender,args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 注册子命令
     * @param clazz 命令处理的方法所在类(命令处理器方法须静态)
     */
    public static void registerSubCommand(Class<?> clazz) {
        SubCommand subCommand = null;
        for (Method method : clazz.getMethods()) {
            for (Annotation tag : method.getAnnotations()) {
                if (tag instanceof SubCommand command) {
                    subCommand = command;
                    break;
                }
            }
            if (subCommand != null) {
                String mainCommand = subCommand.mainCommand();
                String permission = subCommand.permission();
                String command = subCommand.subCommand();
                String[] strings = new String[3];
                strings[0] = mainCommand;
                strings[1] = permission;
                strings[2] = command;
                subCommandMethods.put(strings,method);
                subCommand = null;
            }
        }
    }
}
