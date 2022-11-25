package io.github.mcchampions.DodoOpenJava.Utils.Command;

import io.github.mcchampions.DodoOpenJava.Command.Command;
import io.github.mcchampions.DodoOpenJava.Command.CommandExecutor;
import io.github.mcchampions.DodoOpenJava.Command.CommandSender;
import io.github.mcchampions.DodoOpenJava.Utils.MapUtil;
import okio.ByteString;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ����ݵ�����ע��(����û��д��)
 */
public class CommandUtil {
    /**
     * ע��������(���Զ�����������ƥ�䣬����Ҫ��������������)
     * @param clazz �����ķ���������(�������������Ǿ�̬)
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
                    public ByteString MainCommand() {
                        return ByteString.encodeUtf8(command);
                    }

                    @Override
                    public String Permission() {
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

    /** ƥ��������
     *
     * @param mainCommand ������
     * @param sender ������
     * @param args ����
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
     * ע��������
     * @param clazz �����ķ���������(������������뾲̬)
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
