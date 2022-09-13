package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Event.EventHandler;
import io.github.mcchampions.DodoOpenJava.Event.Listener;
import io.github.mcchampions.DodoOpenJava.Event.events.MessageEvent;
import io.github.mcchampions.DodoOpenJava.Utils.MapUtil;
import org.json.JSONObject;

import java.util.List;
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
        if (jsontext.getJSONObject("eventBody").getInt("messageType") != 1) return;
        if (jsontext.getJSONObject("eventBody").getJSONObject("messageBody").getString("content").indexOf("/") != 0) return;
        String command = jsontext.getJSONObject("eventBody").getJSONObject("messageBody").getString("content").replaceFirst("/","");
        CommandSender sender = new CommandSender();
        sender.InitSender(jsontext,e.getJsonObject().getString("Authorization"));
        List<String> Command = new java.util.ArrayList<>(List.of(command.split(" ")));
        String MainCommand = Command.get(0);
        Command.remove(0);
        String[] args = Command.toArray(new String[Command.size()]);
        CommandManage.TriggerAllCommand(e.getJsonObject().getString("Authorization"),sender, MainCommand, args);
    }

    /**
     * 监听控制台输入
     */
    public static void listenerConsole() {
        ConsoleListener cs = new ConsoleListener(new Scanner(System.in), msg -> {
            List<String> Command = new java.util.ArrayList<>(List.of(msg.split(" ")));
            String MainCommand = Command.get(0);
            Command.remove(0);
            String[] args = Command.toArray(new String[Command.size()]);
            for ( List<Object> list: MapUtil.ergodicMaps(CommandManage.CommandSystem)) {
                if (list.get(1) instanceof Command command) {
                    command.Trigger(new ConsoleSender(),MainCommand,args);
                }
            }
        });
        cs.listenInNewThread();

        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
