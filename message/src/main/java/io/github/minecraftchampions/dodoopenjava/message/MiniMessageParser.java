package io.github.minecraftchampions.dodoopenjava.message;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MiniMessage解析器
 */
public class MiniMessageParser {
    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static final DocumentBuilder builder;

    static {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成MiniMessage
     *
     * @param text miniMessage
     * @return 组件
     */
    public static MessageComponent parse(String text) {
        return MessageComponent.parseMarkdown(toMarkdown(text));
    }

    public static String toMarkdown(String miniMessage) {
        try {
            String input = "<root>" + miniMessage + "</root>";
            Document document = builder.parse(new InputSource(new StringReader(input)));
            Node rootNode = document.getFirstChild();
            Set<TextComponent> notNestSet = TextComponentGroup.getNotAllowNestTextComponentList();
            Set<String> nameSet = new HashSet<>();
            notNestSet.forEach(textComponent -> nameSet.add(textComponent.getKey()));
            List<Node> nodeList = new ArrayList<>();
            nameSet.forEach(str -> {
                NodeList list = document.getElementsByTagName(str);
                for (int i = 0; i < list.getLength(); i++) {
                    nodeList.add(list.item(i));
                }
            });
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            nodeList.removeIf(node -> isNested(node, nameSet));
            for (Node node : nodeList) {
                StringWriter stringWriter = new StringWriter();

                transformer.transform(new DOMSource(node), new StreamResult(stringWriter));
                String str = stringWriter.toString();
                int length = node.getNodeName().length() + 2;
                if (node instanceof Element element) {
                    NamedNodeMap namedNodeMap = element.getAttributes();
                    int count = namedNodeMap.getLength();
                    for (int i = 0; i < count; i++) {
                        length = 1 + length + namedNodeMap.item(i).getNodeName().length() + 1 + 1 + namedNodeMap.item(i).getNodeValue().length() + 1;
                    }
                }
                node.setTextContent(str.substring(length, str.length() - node.getNodeName().length() - 3));
            }
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(rootNode), new StreamResult(stringWriter));
            String xml = stringWriter.toString();
            xml = xml.substring(6, xml.length() - 7);
            xml = replaceMiniMessage(xml);
            xml = xml.replaceAll("\n\n>","\n>");
            return xml;
        } catch (IOException | SAXException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 替换MiniMessage为markdown消息
     *
     * @param message miniMessage
     * @return 替换后
     * @throws RuntimeException 未知的组件,缺少的属性,多余的属性,错误的属性
     */
    private static String replaceMiniMessage(String message) throws RuntimeException {
        Pattern pattern = Pattern.compile("<(\\S+?)(\\s(?<attr>(?<attrkey>[^=]+)=\"(?<attrvalue>[^>]*)\"))?>(?<content>[\\s\\S]*?)</\\1>");
        Matcher matcher = pattern.matcher(message);
        List<TextComponent> componentList = TextComponentGroup.getTextComponentList();
        Map<String, TextComponent> textComponentMap = new HashMap<>();
        for (TextComponent textComponent : componentList) {
            textComponentMap.put(textComponent.getKey(), textComponent);
        }
        LinkedHashMap<Integer, TextComponent> map = new LinkedHashMap<>();
        while (matcher.find()) {
            String key = matcher.group(1);
            String attr = matcher.group("attr");
            String content = matcher.group("content");
            String attrValue = matcher.group("attrvalue");
            String attrKey = matcher.group("attrkey");
            if (!textComponentMap.containsKey(key)) {
                throw new RuntimeException("错误的标记:" + key);
            }
            if (textComponentMap.get(key).getAttribute() != null) {
                if (attr == null || attr.isEmpty()) {
                    throw new RuntimeException("缺少的属性: " + textComponentMap.get(key).getAttribute());
                }
                if (!Objects.equals(textComponentMap.get(key).getAttribute(), attrKey)) {
                    throw new RuntimeException("错误的属性: " + attrKey);
                }
            } else {
                if (attr != null) {
                    if (!attr.isEmpty()) {
                        throw new RuntimeException("多余的属性: " + attrKey);
                    }
                }
            }
            map.put(matcher.start(), textComponentMap.get(key));
        }
        Set<Entry<Integer, TextComponent>> set = map.entrySet();
        int l = 0;
        for (Entry<Integer, TextComponent> entry : set) {
            int index = entry.getKey()-l;
            TextComponent value = entry.getValue();
            String prefix = message.substring(0, index);
            String content = message.substring(index);
            String tempStr = message;
            message = prefix + content.replaceFirst("<" + value.getKey() + "(\\s(?<attr>(?<attrkey>[^=]+)=\"(?<attrvalue>[^>]*)\"))?>(?<content>[\\s\\S]*?)</" + value.getKey() + ">", value.getMatch());
            l = l + tempStr.length() - message.length();
        }
        pattern = Pattern.compile("<(\\S+?)(\\s(?<attr>(?<attrkey>[^=]+)=\"(?<attrvalue>[^>]*)\"))?>(?<content>[\\s\\S]*?)</\\1>");
        matcher = pattern.matcher(message);
        if (matcher.find()) {
            message = replaceMiniMessage(message);
        }
        return message;
    }

    /**
     * 组件是否被某个名为指定字符(多选包含在Set里)嵌套
     *
     * @param node 组件
     * @param set  指定字符Set
     * @return ture/false
     */
    private static boolean isNested(Node node, Set<String> set) {
        if (node.getParentNode() == null || node.getParentNode().getNodeName().isEmpty()) {
            return false;
        }
        if (set.contains(node.getParentNode().getNodeName())) {
            return true;
        }
        return isNested(node.getParentNode(), set);
    }
}
