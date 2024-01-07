package io.github.minecraftchampions.dodoopenjava.message.text;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import lombok.*;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
    private TextMessageBuilder messageBuilder;

    /**
     * 获取构造器
     *
     * @return builder
     */
    public static TextMessageBuilder builder() {
        return new TextMessageBuilder();
    }

    /**
     * 序列化
     *
     * @return 序列化后的文本
     */
    public String serialize() {
        List<TextMessageComponent> list = messageBuilder.getComponents();
        StringBuilder sb = new StringBuilder();
        for (TextMessageComponent component : list) {
            String content = BaseUtil.replaceXmlSpecialCharacters(component.getContent());
            List<TextMessageStyle> styles = component.getStyles();
            if (component instanceof TextMessageComponent.LinkComponent linkComponent) {
                sb.append("<").append("link url=\"")
                        .append(linkComponent.getLink())
                        .append("\">");
                if (styles.contains(TextMessageStyle.normal) && styles.size() == 1) {
                    sb.append(content);
                } else {
                    for (TextMessageStyle style : styles) {
                        sb.append("<").append(style.toString())
                                .append(">");
                    }
                    sb.append(content);
                    for (int i = styles.size() - 1; i >= 0; i--) {
                        sb.append("</")
                                .append(styles.get(i))
                                .append(">");
                    }
                }
                sb.append("</link>");
            } else {
                if (styles.contains(TextMessageStyle.normal) && styles.size() == 1) {
                    sb.append(content);
                } else {
                    for (TextMessageStyle style : styles) {
                        sb.append("<").append(style.toString())
                                .append(">");
                    }
                    sb.append(content);
                    for (int i = styles.size() - 1; i >= 0; i--) {
                        sb.append("</")
                                .append(styles.get(i))
                                .append(">");
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 反序列化
     *
     * @param str 需要被反序列化的文本
     * @return Message
     */
    @SneakyThrows
    public static TextMessage deserialize(String str) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        String input = "<root>" + str + "</root>";
        Document document = documentBuilder.parse(new InputSource(new StringReader(input)));
        NodeList nodes = document.getFirstChild().getChildNodes();
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
                        throw new RuntimeException("link组件外面不能套别的组件");
                    }
                    Node parentNode = node.getParentNode();
                    String nodeName = parentNode.getNodeName();
                    if (nodeName.equals("link")) {
                        isLink = true;
                        link = parentNode.getAttributes().item(0).getNodeValue();
                    } else {
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
}
