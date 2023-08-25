package io.github.minecraftchampions.dodoopenjava.message;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class MessageComponent {
    private String text;

    private MessageComponent(String text) {
        this.text = text;
    }

    public static MessageComponent parse(String text) {
        return new MessageComponent(text);
    }


    public static MessageComponentBuilder builder() {
        return new MessageComponentBuilder();
    }

    private static class MessageComponentBuilder {
        MessageComponentBuilder() {}
        private LinkedHashMap<TextComponent, Entry<String, String>> parts = new LinkedHashMap<>();

        public MessageComponentBuilder append(TextComponent component, String str) {
            this.parts.put(component, new SimpleEntry<>(str,null));
            return this;
        }

        public MessageComponentBuilder append(String str) {
            this.parts.put(TextComponentGroup.contentComponent, new SimpleEntry<>(str,null));
            return this;
        }

        public MessageComponentBuilder append(TextComponent component, String str, String attr) {
            this.parts.put(component, new SimpleEntry<>(str,attr));
            return this;
        }

        public MessageComponent build() {
            StringBuilder sb = new StringBuilder();
            for (Entry<TextComponent,Entry<String,String>> entry : parts.entrySet()) {
                sb.append(MessageUtil.toXml(entry));
            }
            return MiniMessageParser.parse(sb.toString());
        }
    }

    public static void main(String... args) {
        ;
    }
}
