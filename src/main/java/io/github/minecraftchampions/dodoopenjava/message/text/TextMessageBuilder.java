package io.github.minecraftchampions.dodoopenjava.message.text;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息构造器
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextMessageBuilder {
    private final List<TextMessageComponent> components = new ArrayList<>();

    public TextMessageBuilder append(TextMessageComponent component) {
        synchronized (this.components) {
            this.components.add(component);
        }
        return this;
    }

    public TextMessageBuilder append(TextMessageComponent... components) {
        synchronized (this.components) {
            this.components.addAll(List.of(components));
        }
        return this;
    }

    public TextMessageBuilder append(List<TextMessageComponent> components) {
        synchronized (this.components) {
            this.components.addAll(components);
        }
        return this;
    }

    public TextMessageBuilder prepend(TextMessageComponent component) {
        synchronized (this.components) {
            this.components.add(0, component);
        }
        return this;
    }

    public TextMessageBuilder insert(int index, TextMessageComponent component) {
        synchronized (this.components) {
            this.components.add(0, component);
        }
        return this;
    }

    public TextMessageBuilder newLine() {
        synchronized (this.components) {
            this.components.add(TextMessageComponent.newComponent().content("\n"));
        }
        return this;
    }

    public TextMessageBuilder link(String link, String context, String contextStyle) {
        synchronized (this.components) {
            this.components.add(TextMessageComponent.LinkComponent.newComponent()
                    .content(context).style(TextMessageStyle.cite).link(link));
        }
        return this;
    }

    public TextMessage build() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.components) {
            for (TextMessageComponent component : this.components) {
                sb.append(component.toString());
            }
        }
        return new TextMessage(sb.toString(), this);
    }
}
