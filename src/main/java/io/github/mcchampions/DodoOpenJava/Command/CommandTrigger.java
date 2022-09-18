package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Event.EventHandler;
import io.github.mcchampions.DodoOpenJava.Event.Listener;
import io.github.mcchampions.DodoOpenJava.Event.events.MessageEvent;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * 命令触发
 */
public class CommandTrigger implements Listener {
    /**
     * 监听消息事件
     * @param e 事件
     */
    @EventHandler
    public void event(MessageEvent e) {
        JSONObject jsontext = new JSONObject(e.jsonString);
        if (!Objects.equals(jsontext.getJSONObject("data").getJSONObject("eventBody").getInt("messageType"), 1)) return;
        if (jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("messageBody").getString("content").indexOf("/") != 0) return;
        String command = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("messageBody").getString("content").replaceFirst("/","");
        CommandSender sender = new CommandSender();
        sender.InitSender(jsontext);
        List<String> Command = new java.util.ArrayList<>(List.of(command.split(" ")));
        String MainCommand = Command.get(0);
        Command.remove(0);
        String[] args = Command.toArray(new String[Command.size()]);
        io.github.mcchampions.DodoOpenJava.Command.Command.Trigger(sender, MainCommand, args);
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
            io.github.mcchampions.DodoOpenJava.Command.Command.Trigger(new ConsoleSender(),MainCommand,args);
        });
        cs.listenInNewThread();
    }
}
