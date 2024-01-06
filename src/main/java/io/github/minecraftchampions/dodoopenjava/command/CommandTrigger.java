package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.MessageEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.personal.PersonalMessageEvent;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

/**
 * 命令触发
 */
@AllArgsConstructor
public class CommandTrigger implements Listener {
    /**
     * 监听消息事件
     *
     * @param e 事件
     */
    @EventHandler
    public void event(MessageEvent e) {
        if (!Objects.equals(e.getMessageIntType(), 1)) return;
        if (e.getMessageBody().getString("content").indexOf("/") != 0) return;
        call(e.getJsonObject(), false);
    }

    /**
     * 监听私聊消息事件
     *
     * @param e 事件
     */
    @EventHandler
    public void event(PersonalMessageEvent e) {
        if (!Objects.equals(e.getMessageIntType(), 1)) return;
        if (e.getMessageBody().getString("content").indexOf("/") != 0) return;
        call(e.getJsonObject(), true);
    }

    public void call(JSONObject jsonObject, boolean isPersonalCommand) {
        String command = jsonObject.getJSONObject("data").getJSONObject("eventBody")
                .getJSONObject("messageBody").getString("content")
                .replaceFirst("/", "");
        CommandSender sender = new CommandSender(jsonObject, commandManager.getBot(), isPersonalCommand);
        List<String> Command = new java.util.ArrayList<>(List.of(command.split(" ")));
        String mainCommand = Command.get(0);
        Command.remove(0);
        String[] args = Command.toArray(new String[]{});
        commandManager.trigger(sender, mainCommand, isPersonalCommand, args);
    }

    @NonNull
    CommandManager commandManager;
}
