package io.github.minecraftchampions.dodoopenjava.message;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息构造器
 */
@Getter
public class MessageBuilder {
    protected MessageBuilder() {
    }

    private final List<MessageComponent> components = new ArrayList<>();

    public MessageBuilder append(MessageComponent component) {
        this.components.add(component);
        return this;
    }

    public MessageBuilder append(MessageComponent... components) {
        this.components.addAll(List.of(components));
        return this;
    }

    public MessageBuilder append(List<MessageComponent> components) {
        this.components.addAll(components);
        return this;
    }

    public MessageBuilder prepend(MessageComponent component) {
        this.components.add(0, component);
        return this;
    }

    public MessageBuilder insert(int index, MessageComponent component) {
        this.components.add(0, component);
        return this;
    }

    public MessageBuilder newLine() {
        this.components.add(MessageComponent.newComponent().content("\n"));
        return this;
    }

    public MessageBuilder link(String link, String context, String contextStyle) {
        this.components.add(MessageComponent.LinkComponent.newComponent()
                .content(context).style(MessageStyle.cite).link(link));
        return this;
    }

    public Message build() {
        StringBuilder sb = new StringBuilder();
        for (MessageComponent component : this.components) {
            sb.append(component.toString());
        }
        return new Message(sb.toString(), this);
    }
}
