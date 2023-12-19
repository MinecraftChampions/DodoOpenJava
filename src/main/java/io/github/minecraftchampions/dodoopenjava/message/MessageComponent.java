package io.github.minecraftchampions.dodoopenjava.message;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class MessageComponent {
    private MessageComponent() {
        styles.add(MessageStyle.normal);
    }

    public static MessageComponent newComponent() {
        return new MessageComponent();
    }

    protected String content;

    protected List<MessageStyle> styles = new ArrayList<>();

    public MessageComponent content(String str) {
        this.content = str;
        return this;
    }

    public MessageComponent style(MessageStyle... styles) {
        this.styles = new ArrayList<>();
        this.styles.addAll(Arrays.asList(styles));
        return this;
    }

    @Override
    public String toString() {
        String str = this.content;
        for (MessageStyle style : styles) {
            str = String.format(style.getRegex(), str);
        }
        return str;
    }

    @Getter
    public static class LinkComponent extends MessageComponent {
        protected String link;

        public static LinkComponent newComponent() {
            return new LinkComponent();
        }

        public LinkComponent link(String link) {
            this.link = link;
            return this;
        }

        @Override
        public LinkComponent content(String str) {
            this.content = str;
            return this;
        }

        @Override
        public LinkComponent style(MessageStyle... styles) {
            this.styles = new ArrayList<>();
            List<MessageStyle> list = Arrays.asList(styles);
            if (list.contains(MessageStyle.code) ||
                    list.contains(MessageStyle.cite))
                throw new RuntimeException("LinkComponent不能传入code与cite style");

            this.styles.addAll(list);
            return this;
        }

        @Override
        public String toString() {
            String str = this.content;
            for (MessageStyle style : styles) {
                str = String.format(style.getRegex(), str);
            }
            return "[" +
                    str +
                    "]" +
                    "(" +
                    link +
                    ")";
        }
    }


}
