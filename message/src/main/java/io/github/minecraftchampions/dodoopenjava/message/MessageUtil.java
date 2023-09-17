package io.github.minecraftchampions.dodoopenjava.message;

import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class MessageUtil {
    public static final Pattern tokenPattern = Pattern.compile("(?<token>\\S+)(\\s(?<attr>(?<attrkey>[^=]+)=\"(?<attrvalue>[^>]*)\"))?");
    public static final Pattern pattern = Pattern.compile("((?<start><)(?<token>[^<>]+(:(?<inner>['\"]?([^'\"](\\\\['\"])?)+['\"]?))*)(?<end>>))+?");


    /**
     * 替换特殊字符
     * @param text 要替换的字符
     * @return str
     */
    public static String replaceXmlSpecialCharacters(String text) {
        return text.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
    }

    /**
     * 替换为MiniMessage
     *
     * @param entry 第一个参数为TextComponent即对应的组件,第二个参数为Entry,里面的第一个参数为内容,第二个参数为属性(如link组件的url)
     * @return Str
     */
    public static String toMiniMessage(Entry<TextComponent, Entry<String, String>> entry) {
        TextComponent textComponent = entry.getKey();
        Entry<String, String> strE = entry.getValue();
        String str = strE.getKey();
        String attr = strE.getValue();
        if (textComponent == TextComponentGroup.contentComponent) {
            return str;
        }
        StringBuilder attrSb = new StringBuilder();
        if (textComponent.getAttribute() != null) {
            if (attr == null) {
                attr = "";
            }
            attrSb.append(" ").append(textComponent.getAttribute()).append("=\"").append(attr).append("\"");
        }
        return "<" + textComponent.getKey() + attrSb + ">" + str + "</" + textComponent.getKey() + ">";
    }
}
