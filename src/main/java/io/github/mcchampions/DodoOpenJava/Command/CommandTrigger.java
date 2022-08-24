package io.github.mcchampions.DodoOpenJava.Command;

import com.alibaba.fastjson.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.Listener;

import java.util.List;

/**
 * 命令触发
 */
public class CommandTrigger implements Listener {
    @Override
    public void event(Event e) {
        JSONObject jsontext = JSONObject.parseObject(e.getParam());
        if (jsontext.getJSONObject("eventBody").getIntValue("messageType") != 1) return;
        if (jsontext.getJSONObject("eventBody").getJSONObject("messageBody").getString("content").indexOf("/") != 0) return;
        String command = jsontext.getJSONObject("eventBody").getJSONObject("messageBody").getString("content").replaceFirst("/","");
        String DodoId = jsontext.getJSONObject("eventBody").getJSONObject("messageBody").getString("dodoId");
        List<String> Command = new java.util.ArrayList<>(List.of(command.split(" ")));
        String MainCommand = Command.get(0);
        Command.remove(0);
        String[] args = Command.toArray(new String[0]);
        io.github.mcchampions.DodoOpenJava.Command.Command c = new Command();
        c.Trigger(DodoId, MainCommand, args);
    }
}
