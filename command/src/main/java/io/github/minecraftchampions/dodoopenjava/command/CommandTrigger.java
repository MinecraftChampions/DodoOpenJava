package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.MessageEvent;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * �����
 * @author qscbm187531
 */
public class CommandTrigger implements Listener {
    /**
     * ������Ϣ�¼�
     * @param e �¼�
     */
    @EventHandler
    public void event(MessageEvent e) {
        if (!Objects.equals(e.getMessageIntType(), 1)) return;
        if (e.getMessageBody().getString("content").indexOf("/") != 0) return;
        String command = e.getMessageBody().getString("content").replaceFirst("/","");
        CommandSender sender = new CommandSender();
        sender.InitSender(new JSONObject(e.jsonString));
        List<String> Command = new java.util.ArrayList<>(List.of(command.split(" ")));
        String MainCommand = Command.get(0);
        Command.remove(0);
        String[] args = Command.toArray(new String[Command.size()]);
        io.github.minecraftchampions.dodoopenjava.command.Command.trigger(sender, MainCommand, args);
    }

    /**
     * ��������̨��������
     */
    public static void listenerConsole() {
        ConsoleListener cs = new ConsoleListener(new Scanner(System.in), msg -> {
            List<String> Command = new java.util.ArrayList<>(List.of(msg.split(" ")));
            String MainCommand = Command.get(0);
            Command.remove(0);
            String[] args = Command.toArray(new String[Command.size()]);
            io.github.minecraftchampions.dodoopenjava.command.Command.trigger(new ConsoleSender(),MainCommand,args);
        });
        cs.listenInNewThread();
    }
}