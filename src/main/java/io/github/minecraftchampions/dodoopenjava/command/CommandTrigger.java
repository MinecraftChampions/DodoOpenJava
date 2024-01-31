package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.MessageEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.personal.PersonalMessageEvent;
import io.github.minecraftchampions.dodoopenjava.impl.CommandSenderImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

/**
 * 命令触发
 */
@RequiredArgsConstructor
@Getter
public class CommandTrigger implements Listener {
    /**
     * 监听消息事件
     *
     * @param e 事件
     */
    @EventHandler
    public void event(MessageEvent e) {
        if (!Objects.equals(e.getMessageIntType(), 1)) return;
        if (e.getMessageBody().getString("content").indexOf(commandHeader) != 0) return;
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
        if (e.getMessageBody().getString("content").indexOf(commandHeader) != 0) return;
        call(e.getJsonObject(), true);
    }

    public void call(JSONObject jsonObject, boolean isPersonalCommand) {
        String command = jsonObject.getJSONObject("data").getJSONObject("eventBody")
                .getJSONObject("messageBody").getString("content")
                .replaceFirst(commandHeader, "");
        CommandSenderImpl sender = new CommandSenderImpl(jsonObject, commandManager.getBot(), isPersonalCommand);
        List<String> args = new java.util.ArrayList<>(List.of(command.split(" ")));
        String mainCommand = args.get(0);
        args.remove(0);
        String[] argArray = args.toArray(new String[]{});
        commandManager.trigger(sender, mainCommand, isPersonalCommand, argArray);
    }

    private String commandHeader = "/";

    /**
     * 设置命令头
     * 默认:"/"
     *
     * @param commandHeader header
     */
    public void setCommandHeader(@NonNull String commandHeader) {
        this.commandHeader = commandHeader;
    }

    @NonNull
    CommandManager commandManager;
}
