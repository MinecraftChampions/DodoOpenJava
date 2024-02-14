package io.github.minecraftchampions.dodoopenjava.message.text;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文本消息组件
 *
 * @author qscbm187531
 */
@Getter
@Slf4j
public class TextMessageComponent {
    private TextMessageComponent() {
        styles.add(TextMessageStyle.normal);
    }

    public static TextMessageComponent newComponent() {
        return new TextMessageComponent();
    }

    public static LinkComponent newLinkComponent() {
        return LinkComponent.newComponent();
    }

    protected String content;

    protected List<TextMessageStyle> styles = new ArrayList<>();

    public synchronized TextMessageComponent content(String str) {
        this.content = str;
        return this;
    }

    public TextMessageComponent style(TextMessageStyle... styles) {
        this.styles = new ArrayList<>(Arrays.asList(styles));
        return this;
    }

    @Override
    public String toString() {
        String str = this.content;
        for (TextMessageStyle style : styles) {
            str = String.format(style.getRegex(), str);
        }
        return str;
    }

    @Getter
    public static class LinkComponent extends TextMessageComponent {
        protected String link;

        public static LinkComponent newComponent() {
            return new LinkComponent();
        }

        public synchronized LinkComponent link(String link) {
            this.link = link;
            return this;
        }

        @Override
        public synchronized LinkComponent content(String str) {
            this.content = str;
            return this;
        }

        @Override
        public synchronized LinkComponent style(TextMessageStyle... styles) {
            this.styles = new ArrayList<>();
            List<TextMessageStyle> list = Arrays.asList(styles);
            if (list.contains(TextMessageStyle.code) ||
                list.contains(TextMessageStyle.cite)) {
                log.error("LinkComponent不能传入code与cite style");
                return this;
            }

            this.styles.addAll(list);
            return this;
        }

        @Override
        public String toString() {
            String str = this.content;
            for (TextMessageStyle style : styles) {
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
