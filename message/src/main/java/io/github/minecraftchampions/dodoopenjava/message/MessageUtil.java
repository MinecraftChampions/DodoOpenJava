package io.github.minecraftchampions.dodoopenjava.message;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtil {
    private static final String START = "start";
    private static final String TOKEN = "token";
    private static final String INNER = "inner";
    private static final String END = "end";
    private static final Pattern pattern = Pattern.compile("((?<start><)(?<token>[^<>]+(:(?<inner>['\"]?([^'\"](\\\\['\"])?)+['\"]?))*)(?<end>>))+?");

    public static String mdToMiniMessage(String input) {
        for (TextComponent textComponent : TextComponentGroup.getTextComponentList()) {
        }
        return null;
    }

    public static String escapeTokens(String str) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = pattern.matcher(str);
        int lastEnd = 0;
        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            if (startIndex > lastEnd) {
                sb.append(str, lastEnd, startIndex);
            }
            lastEnd = endIndex;

            String start = matcher.group(START);
            String token = matcher.group(TOKEN);
            String inner = matcher.group(INNER);
            String end = matcher.group(END);

            // also escape inner
            if (inner != null) {
                token = token.replace(inner, escapeTokens(inner));
            }

            sb.append("\\").append(start).append(token).append("\\").append(end);
        }

        if (str.length() > lastEnd) {
            sb.append(str.substring(lastEnd));
        }

        return sb.toString();
    }

    public static String stripTokens(String richMessage) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = pattern.matcher(richMessage);
        int lastEnd = 0;
        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            if (startIndex > lastEnd) {
                sb.append(richMessage, lastEnd, startIndex);
            }
            lastEnd = endIndex;
        }

        if (richMessage.length() > lastEnd) {
            sb.append(richMessage.substring(lastEnd));
        }

        return sb.toString();
    }

    public static String replaceXmlSpecialCharacters(String text) {
        return text.replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("'","&apos;").replaceAll("\"","&quot;");
    }

    public static String toXml(Entry<TextComponent,Entry<String,String>> entry) {
        TextComponent textComponent = entry.getKey();
        Entry<String,String> strE = entry.getValue();
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
