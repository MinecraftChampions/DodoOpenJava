package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.event.EventManage;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import okio.ByteString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ����ϵͳ����ط���
 * @author qscbm187531
 */
public class Command {
    public static List<CommandExecutor> commands = new ArrayList<>();

    public static String Authorization;

    /**
     * ��ʼ������ϵͳ(Ĭ��V2,������ʹ��V1)
     *
     * @param Class    ��������ڵ���
     * @param clientId ������Ψһ��ʾ
     * @param token    ������Token
     */
    public static void registerCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId, token));
        Authorization = BaseUtil.Authorization(clientId, token);
    }

    /**
     * ��ʼ������ϵͳ(Ĭ��V2,������ʹ��V1)
     *
     * @param Class    ��������ڵ���
     * @param clientId ������Ψһ��ʾ
     * @param token    ������Token
     */
    public static void registerCommand(String clientId, String token, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId, token));
        Authorization = BaseUtil.Authorization(clientId, token);
    }

    /**
     * ��ʼ������ϵͳ(Ĭ��V2,������ʹ��V1)
     *
     * @param Class         ��������ڵ���
     * @param authorization authorization
     */
    public static void registerCommand(CommandExecutor Class, String authorization) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), authorization);
        Authorization = authorization;
    }

    /**
     * ��ʼ������ϵͳ(Ĭ��V2,������ʹ��V1)
     *
     * @param Class         ��������ڵ���
     * @param authorization authorization
     */
    public static void registerCommand(String authorization, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), authorization);
        Authorization = authorization;
    }

    /**
     * ��������
     *
     * @param sender ������
     * @param mainCommandName ������
     * @param args �������
     */
    @SuppressWarnings("UnusedReturnValue")
    public static Boolean trigger(CommandSender sender, String mainCommandName, String... args) {
        boolean hasCommand = false;
        for (CommandExecutor command : commands) {
            if (Objects.equals(command.MainCommand().utf8(), ByteString.encodeUtf8((mainCommandName)).utf8())) {
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