package io.github.minecraftchampions.dodoopenjava.message.text;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import lombok.*;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 消息组件
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextMessage implements Message {
    private String text;

    @NonNull
    private TextMessage.Builder messageBuilder;

    /**
     * 获取构造器
     *
     * @return builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 序列化
     *
     * @return 序列化后的文本
     */
    public String serialize() {
        List<TextMessageComponent> list = messageBuilder.components;
        StringBuilder sb = new StringBuilder();
        for (TextMessageComponent component : list) {
            String content = BaseUtil.replaceXmlSpecialCharacters(component.getContent());
            List<TextMessageStyle> styles = component.getStyles();
            StringBuilder stringBuilder = new StringBuilder();
            if (styles.contains(TextMessageStyle.normal) && styles.size() == 1) {
                stringBuilder.append(content);
            } else {
                for (TextMessageStyle style : styles) {
                    stringBuilder.append("<").append(style.toString())
                            .append(">");
                }
                stringBuilder.append(content);
                for (int i = styles.size() - 1; i >= 0; i--) {
                    stringBuilder.append("</")
                            .append(styles.get(i))
                            .append(">");
                }
            }
            if (component instanceof TextMessageComponent.LinkComponent linkComponent) {
                stringBuilder.insert(0, new StringBuilder("<").append("link url=\"")
                        .append(linkComponent.getLink())
                        .append("\">"));
                stringBuilder.append("</link>");
            }
            sb.append(stringBuilder);
        }
        return sb.toString();
    }

    /**
     * 反序列化
     *
     * @param str 需要被反序列化的文本
     * @return Message
     */
    public static TextMessage deserialize(@NonNull String str) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            String input = "<root>" + str + "</root>";
            Document document = documentBuilder.parse(new InputSource(new StringReader(input)));
            List<TextMessageComponent> list = new ArrayList<>();
            List<Node> nodeList = BaseUtil.getAllTextNodes(document.getFirstChild());
            for (Node node : nodeList) {
                String content = node.getTextContent();
                List<TextMessageStyle> styleList = new ArrayList<>();
                boolean isLink = false;
                String link = "";
                if (!node.getParentNode().getNodeName().equals("root")) {
                    while (!node.getParentNode().getNodeName().equals("root")) {
                        if (isLink) {
                            DodoOpenJava.LOGGER.error("link组件外面不能套别的组件");
                            return null;
                        }
                        Node parentNode = node.getParentNode();
                        String nodeName = parentNode.getNodeName();
                        if (nodeName.equals("link")) {
                            isLink = true;
                            link = parentNode.getAttributes().item(0).getNodeValue();
                        } else {
                            if (!TextMessageStyle.getStyles().contains(nodeName)) return null;
                            styleList.add(TextMessageStyle.valueOf(nodeName));
                        }
                        node = parentNode;
                    }
                }
                Collections.reverse(styleList);
                TextMessageComponent textMessageComponent;
                if (isLink) {
                    textMessageComponent = TextMessageComponent.LinkComponent.newComponent()
                            .link(link).content(content).style(styleList.toArray(TextMessageStyle[]::new));
                } else {
                    textMessageComponent = TextMessageComponent.newComponent()
                            .content(content).style(styleList.toArray(TextMessageStyle[]::new));
                }
                list.add(textMessageComponent);
            }
            return TextMessage.builder().append(list).build();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            DodoOpenJava.LOGGER.error("执行TextMessage#deserialize时发生错误", e);
            return null;
        }
    }

    /**
     * 转换为 markdown
     *
     * @return markdown
     */
    @Override
    public String toString() {
        return text;
    }

    @Override
    public JSONObject toMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", text);
        return jsonObject;
    }

    @Override
    public int getType() {
        return 1;
    }

    /**
     * 消息构造器
     */
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Builder {
        private final List<TextMessageComponent> components = new ArrayList<>();

        public Builder append(@NonNull TextMessageComponent component) {
            synchronized (this.components) {
                this.components.add(component);
            }
            return this;
        }

        public Builder append(@NonNull TextMessageComponent... components) {
            synchronized (this.components) {
                this.components.addAll(List.of(components));
            }
            return this;
        }

        public Builder append(@NonNull List<TextMessageComponent> components) {
            synchronized (this.components) {
                this.components.addAll(components);
            }
            return this;
        }

        public Builder prepend(@NonNull TextMessageComponent component) {
            synchronized (this.components) {
                this.components.add(0, component);
            }
            return this;
        }

        public Builder insert(int index, @NonNull TextMessageComponent component) {
            synchronized (this.components) {
                this.components.add(index, component);
            }
            return this;
        }

        public Builder newLine() {
            synchronized (this.components) {
                this.components.add(TextMessageComponent.newComponent().content("\n"));
            }
            return this;
        }

        public Builder link(@NonNull String link, @NonNull String context,
                            @NonNull TextMessageStyle contextStyle) {
            synchronized (this.components) {
                this.components.add(TextMessageComponent.LinkComponent.newComponent()
                        .content(context).style(contextStyle).link(link));
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
}
