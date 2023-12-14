package io.github.minecraftchampions.dodoopenjava.message;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import lombok.SneakyThrows;
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
public class Message {
    private String text;

    private Message() {}

    protected Message(String text, MessageBuilder builder) {
        this.text = text;
        this.messageBuilder = builder;
    }

    private MessageBuilder messageBuilder;

    /**
     * 获取构造器
     * @return builder
     */
    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    /**
     * 序列化
     * @return 序列化后的文本
     */
    public String serialize() {
        List<MessageComponent> list = messageBuilder.getComponents();
        StringBuilder sb = new StringBuilder();
        for (MessageComponent component : list) {
            String content = BaseUtil.replaceXmlSpecialCharacters(component.getStr());
            List<MessageStyle> styles = component.getStyles();
            if (component instanceof MessageComponent.LinkComponent linkComponent) {
                sb.append("<").append("link url=\"")
                        .append(linkComponent.getLink())
                        .append("\">");
                if (styles.contains(MessageStyle.normal) && styles.size() == 1) {
                    sb.append(content);
                } else {
                    for (MessageStyle style : styles) {
                        sb.append("<").append(style.toString())
                                .append(">");
                    }
                    sb.append(content);
                    for (int i = styles.size()-1;i>=0;i--) {
                        sb.append("</")
                                .append(styles.get(i))
                                .append(">");
                    }
                }
                sb.append("</link>");
            } else {
                if (styles.contains(MessageStyle.normal) && styles.size() == 1) {
                    sb.append(content);
                } else {
                    for (MessageStyle style : styles) {
                        sb.append("<").append(style.toString())
                                .append(">");
                    }
                    sb.append(content);
                    for (int i = styles.size()-1;i>=0;i--) {
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
     * @param str 需要被反序列化的文本
     * @return Message
     */
    @SneakyThrows
    public static Message deserialize(String str) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        String input = "<root>" + str + "</root>";
        Document document = documentBuilder.parse(new InputSource(new StringReader(input)));
        NodeList nodes = document.getFirstChild().getChildNodes();
        List<MessageComponent> list = new ArrayList<>();
        List<Node> nodeList = BaseUtil.getAllTextNodes(document.getFirstChild());
        for (Node node : nodeList) {
            String content = node.getTextContent();
            List<MessageStyle> styleList = new ArrayList<>();
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
                        styleList.add(MessageStyle.valueOf(nodeName));
                    }
                    node = parentNode;
                }
            }
            Collections.reverse(styleList);
            MessageComponent messageComponent;
            if (isLink) {
                messageComponent = MessageComponent.LinkComponent.newComponent()
                        .link(link).content(content).style(styleList.toArray(MessageStyle[]::new));
            } else {
                messageComponent = MessageComponent.newComponent()
                        .content(content).style(styleList.toArray(MessageStyle[]::new));
            }
            list.add(messageComponent);
        }
        return Message.builder().append(list).build();
    }

    /**
     * 转换为 markdown
     * @return markdown
     */
    @Override
    public String toString() {
        return text;
    }
}
