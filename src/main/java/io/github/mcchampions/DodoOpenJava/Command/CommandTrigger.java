package io.github.mcchampions.DodoOpenJava.Command;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.EventHandler;
import io.github.mcchampions.DodoOpenJava.Event.Listener;
import io.github.mcchampions.DodoOpenJava.Event.events.MessageEvent;

import java.util.List;

/**
 * 命令触发
 */
public class CommandTrigger implements Listener {
    @EventHandler
    public void event(MessageEvent e) {
        JSONObject jsontext = new JSONObject(e.jsonString);
        if (jsontext.getJSONObject("eventBody").getInt("messageType") != 1) return;
        if (jsontext.getJSONObject("eventBody").getJSONObject("messageBody").getString("content").indexOf("/") != 0) return;
        String command = jsontext.getJSONObject("eventBody").getJSONObject("messageBody").getString("content").replaceFirst("/","");
        CommandSender sender = new CommandSender();
        sender.InitSender(jsontext);
        List<String> Command = new java.util.ArrayList<>(List.of(command.split(" ")));
        String MainCommand = Command.get(0);
        Command.remove(0);
        String[] args = Command.toArray(new String[0]);
        io.github.mcchampions.DodoOpenJava.Command.Command c = new Command();
        c.Trigger(sender, MainCommand, args);
    }
}
