package io.github.minecraftchampions.dodoopenjava.message;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;

/**
 * 消息组件
 */
public class MessageComponent {
    private final String text;

    private MessageComponent(String text) {
        this.text = text;
    }

    /**
     * 将Markdown转换为MessageComponent
     *
     * @param text Markdown
     * @return MessageComponent
     */
    public static MessageComponent parseMarkdown(String text) {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder()
                .build();
        Node document = parser.parse(text);
        String html = renderer.render(document);
        html = html.substring(0,html.length()-1);
        html = html.replaceAll("\\n<([^>]*)>\\n","\n<$1>");
        Matcher matcher = MessageUtil.pattern.matcher(html);
        while (matcher.find()) {
            String tempToken = matcher.group("token");
            int start = matcher.start("token");
            int end = matcher.end("token");
            Matcher m = MessageUtil.tokenPattern.matcher(tempToken);
            if (m.find()) {
                String token = m.group("token");
                String attrKey = m.group("attrkey");
                boolean isEnd = false;
                if (token.indexOf("/") == 0) {
                    token = token.split("/")[1];
                    isEnd = true;
                }
                Map<String, TextComponent> map = TextComponentGroup.getHtmlReplaceMap();
                if (map.containsKey(token)) {
                    TextComponent component = map.get(token);
                    String replacement = component.getKey();
                    token = token.replaceFirst(token, replacement);
                    String str = token;
                    if (!isEnd && attrKey != null) {
                        attrKey = component.getAttribute();
                        str = str + " " + attrKey + "=\"" + m.group("attrvalue") + "\"";
                    }
                    if (isEnd) {
                        str = "/" + str;
                    }
                    String tempStr = html.substring(start);
                    tempStr = tempStr.replaceFirst(tempToken, str);
                    html = html.substring(0, start) + tempStr;
                }
            }
        }
        html = html.replaceAll("</?content>", "").replaceAll("([^|])\\|([^|])", "$1&#124$2").replaceAll("\\|\\|([^|]*)\\|\\|", "<antispoiler>$1</antispoiler>").replaceAll("([^_])_([^_])", "$1&#95$2").replaceAll("__([^_]*)__", "<underline>$1</underline>").replaceAll("([^~])~([^~])", "$1&#126$2").replaceAll("~~([^~]*)~~", "<strikethrough>$1</strikethrough>");
        return new MessageComponent(html);
    }

    public String toString() {
        return text;
    }

    static MessageComponent parse(String miniMessage) {
        return new MessageComponent(miniMessage);
    }

    /**
     * 获取构造器
     *
     * @return 构造器
     */
    public static MessageComponentBuilder builder() {
        return new MessageComponentBuilder();
    }

    /**
     * 构造器
     */
    public static class MessageComponentBuilder {
        MessageComponentBuilder() {
        }

        private final LinkedHashMap<TextComponent, Entry<String, String>> parts = new LinkedHashMap<>();

        /**
         * 增加组件
         *
         * @param component 指定组件类型
         * @param str       字符
         * @return 构造器
         */
        public MessageComponentBuilder append(TextComponent component, String str) {
            this.parts.put(component, new SimpleEntry<>(str, null));
            return this;
        }

        /**
         * 增加文字组件
         *
         * @param str 字符
         * @return 构造器
         */
        public MessageComponentBuilder append(String str) {
            this.parts.put(TextComponentGroup.contentComponent, new SimpleEntry<>(str, null));
            return this;
        }

        /**
         * 增加组件
         *
         * @param component 指定组件类型
         * @param str       字符
         * @param attr      属性值,目前为链接
         * @return 构造器
         */
        public MessageComponentBuilder append(TextComponent component, String str, String attr) {
            this.parts.put(component, new SimpleEntry<>(str, attr));
            return this;
        }

        /**
         * 构造
         *
         * @return messageComponent
         */
        public MessageComponent build() {
            StringBuilder sb = new StringBuilder();
            for (Entry<TextComponent, Entry<String, String>> entry : parts.entrySet()) {
                sb.append(MessageUtil.toMiniMessage(entry));
            }
            return MiniMessageParser.parse(sb.toString());
        }
    }
}
