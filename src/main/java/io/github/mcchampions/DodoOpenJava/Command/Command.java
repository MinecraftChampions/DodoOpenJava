package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Api.Version;
import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
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
    public static void regsiterCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId, token), Version.V2);
        Authorization = BaseUtil.Authorization(clientId, token);
    }

    /**
     * ��ʼ������ϵͳ(Ĭ��V2,������ʹ��V1)
     *
     * @param Class    ��������ڵ���
     * @param clientId ������Ψһ��ʾ
     * @param token    ������Token
     */
    public static void regsiterCommand(String clientId, String token, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId, token), Version.V2);
        Authorization = BaseUtil.Authorization(clientId, token);
    }

    /**
     * ��ʼ������ϵͳ(Ĭ��V2,������ʹ��V1)
     *
     * @param Class         ��������ڵ���
     * @param authorization authorization
     */
    public static void regsiterCommand(CommandExecutor Class, String authorization) {
        commands.add(Class);
        EventManage.registerEvents(new CommandTrigger(), authorization, Version.V2);
        Authorization = authorization;
    }

    /**
     * ��ʼ������ϵͳ(Ĭ��V2,������ʹ��V1)
     *
     * @param Class         ��������ڵ���
     * @param authorization authorization
     */
    public static void regsiterCommand(String authorization, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        EventManage.registerEvents(new CommandTrigger(), authorization, Version.V2);
        Authorization = authorization;
    }

    /**
     * ��������
     *
     * @param sender ������
     * @param mainCommandName ������
     * @param args �������
     */
    public static Boolean trigger(CommandSender sender, String mainCommandName, String[] args) {
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