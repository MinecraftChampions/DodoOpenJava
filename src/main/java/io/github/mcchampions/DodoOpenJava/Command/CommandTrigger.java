package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Event.EventHandler;
import io.github.mcchampions.DodoOpenJava.Event.Listener;
import io.github.mcchampions.DodoOpenJava.Event.events.V2.MessageEvent;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * 命令触发
 * @author qscbm187531
 */
public class CommandTrigger implements Listener {
    /**
     * 监听消息事件
     * @param e 事件
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
        io.github.mcchampions.DodoOpenJava.Command.Command.trigger(sender, MainCommand, args);
    }

    /**
     * 监听控制台输入命令
     */
    public static void listenerConsole() {
        ConsoleListener cs = new ConsoleListener(new Scanner(System.in), msg -> {
            List<String> Command = new java.util.ArrayList<>(List.of(msg.split(" ")));
            String MainCommand = Command.get(0);
            Command.remove(0);
            String[] args = Command.toArray(new String[Command.size()]);
            io.github.mcchampions.DodoOpenJava.Command.Command.trigger(new ConsoleSender(),MainCommand,args);
        });
        cs.listenInNewThread();
    }
}
