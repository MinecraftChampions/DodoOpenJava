package io.github.minecraftchampions.dodoopenjava.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一些 字符串 的实用方法
 */
public class StringUtil {
    public final static Pattern DEL_PATTERN = Pattern.compile("%");
    public final static Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    public final static Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    public final static Pattern CHINESE = Pattern.compile("[^u4E00-u9FA5]+", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    public static final Pattern BRACKET_NUMBER = Pattern.compile("\\[(\\d+)\\]");

    /**
     * 是否为空
     *
     * @param str 字符串
     * @return true/是
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0 || str.isEmpty();
    }

    /**
     * 是否不为空
     *
     * @param str 字符串
     * @return true/是
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 一段字符串是否为中文
     *
     * @param value 字符串
     * @return true代表室，false代表不是
     */
    public static boolean isChinese(String value) {
        boolean result = false;
        Matcher matcher = CHINESE.matcher(value);
        if (matcher.find()) {
            result = true;
        }

        return result;
    }

    /**
     * 将一段字节集合转为字符串（UTF-8)
     *
     * @param data 字节
     * @return 字符串
     */
    public static String toString(byte[] data) {
        try {
            return toString(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将一段字节集合转为字符串
     *
     * @param data    字节
     * @param charset 编码
     * @return 字符串
     */
    public static String toString(byte[] data, String charset) {
        try {
            return new String(data, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据分隔符获取自定义名称
     * 例如：[123]测试123 获取到 测试123
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return 字符串
     */
    public static String getSeparatorCustomName(String str, String separator) {
        return str.substring(str.indexOf(separator) + 1);
    }

    /**
     * 获取[]内的数字
     * 例如：[123]测试123 获取到 123
     *
     * @param str 字符串
     * @return 数字
     * @since 1.1.6
     */
    public static int getSeparatorCustomNameNumber(String str) {
        Matcher matcher = BRACKET_NUMBER.matcher(str);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }


    /**
     * 转换小写
     *
     * @param str 字符串
     * @return 小写字符串
     */
    public static String toLowerCase(String str) {
        return str != null ? str.toLowerCase() : null;
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 首字母大写字符串
     */
    public static String capture(String str) {
        return StringUtils.capitalize(str);
    }

    /**
     * 重复拼接字符串
     *
     * @param str       字符串
     * @param repeatInt 重复次数
     * @return 字符串
     */
    public static String repeat(String str, int repeatInt) {
        return StringUtils.repeat(str, repeatInt);
    }

    /**
     * 将#替换成空格
     *
     * @param str 字符串
     * @return 替换后的字符串
     */
    public static String replaceSpace(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        return str.replace("#", " ");
    }

    /**
     * 下划线转驼峰
     *
     * @param str 字符
     * @return 结果
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str 字符
     * @return 结果
     */
    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 去除字符串中所有的空白符
     *
     * @param str 字符串
     * @return 新字符串
     */
    public static String deleteWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        } else {
            return new String(chs, 0, count);
        }
    }

    /**
     * 过滤特殊字符 %
     *
     * @param str 变量
     * @return str
     */
    public static String stringFilter(String str) {
        return DEL_PATTERN.matcher(str).replaceAll("").trim();
    }

    /**
     * 转换字符串为UUID
     *
     * @param uuid 字符串
     * @return UUID
     */
    public static UUID getUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (final IllegalArgumentException ignored) {
        }
        return null;
    }

    /**
     * 转换 Char 为 Unicode 字符
     *
     * @param c Char
     * @return Unicode 字符
     */
    public static String unicode(char c) {
        return "\\u" + String.format("%04x", (int) c).toUpperCase(Locale.ROOT);
    }
}