package io.github.minecraftchampions.dodoopenjava.configuration;

import io.github.minecraftchampions.dodoopenjava.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 保存时保留配置文件注释的Yaml实例
 * 会以KV的形式存储
 * ”'comment   # 注释内容': comment“的格式存储
 */
public class CommentConfiguration extends YamlConfiguration {
    protected static String commentPrefixSymbol = "'comment ";
    protected static String commentSuffixSymbol = "': comment";
    protected static String fromRegex = "( *)(#.*)";
    protected static Pattern fromPattern = Pattern.compile(fromRegex);
    protected static String toRegex = "( *)(- )*" + "(" + commentPrefixSymbol + ")" + "(#.*)" + "(" + commentSuffixSymbol + ")";
    protected static Pattern toPattern = Pattern.compile(toRegex);
    protected static Pattern countSpacePattern = Pattern.compile("( *)(- )*(.*)");
    protected static int commentSplitWidth = 90;

    private static String[] split(String string, int partLength) {
        String[] array = new String[string.length() / partLength + 1];
        for (int i = 0; i < array.length; i++) {
            int beginIndex = i * partLength;
            int endIndex = beginIndex + partLength;
            if (endIndex > string.length()) {
                endIndex = string.length();
            }
            array[i] = string.substring(beginIndex, endIndex);
        }
        return array;
    }

    @Override
    public void loadFromString(String contents) throws InvalidConfigurationException {
        String[] parts = contents.split("\n");
        List<String> lastComments = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (String part : parts) {
            Matcher matcher = fromPattern.matcher(part);
            if (matcher.find()) {
                String originComment = matcher.group(2);
                String[] splitComments = split(originComment, commentSplitWidth);
                for (int i = 0; i < splitComments.length; i++) {
                    String comment = splitComments[i];
                    if (i == 0) {
                        comment = comment.substring(1);
                    }
                    comment = YamlConfiguration.COMMENT_PREFIX + comment;
                    lastComments.add(comment.replaceAll("\\.", "．").replaceAll("'", "＇").replaceAll(":", "："));
                }
            } else {
                matcher = countSpacePattern.matcher(part);
                if (matcher.find() && !lastComments.isEmpty()) {
                    for (String comment : lastComments) {
                        builder.append(matcher.group(1));
                        builder.append(this.checkNull(matcher.group(2)));
                        builder.append(commentPrefixSymbol);
                        builder.append(comment);
                        builder.append(commentSuffixSymbol);
                        builder.append("\n");
                    }
                    lastComments.clear();
                }
                builder.append(part);
                builder.append("\n");
            }
        }
        super.loadFromString(builder.toString());
    }

    @Override
    public String saveToString() {
        String contents = super.saveToString();
        StringBuilder saveContent = new StringBuilder();
        String[] parts = contents.split("\n");
        for (String part : parts) {
            Matcher matcher = toPattern.matcher(part);
            if (matcher.find() && matcher.groupCount() == 5) {
                part = this.checkNull(matcher.group(1)) + matcher.group(4);
            }
            saveContent.append(part.replaceAll("．", ".").replaceAll("＇", "'").replaceAll("：", ":"));
            saveContent.append("\n");
        }
        return saveContent.toString();
    }

    private String checkNull(String string) {
        return string == null ? "" : string;
    }
}
