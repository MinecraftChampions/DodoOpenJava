package io.github.minecraftchampions.dodoopenjava.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * һЩ �ַ��� ��ʵ�÷���
 * @author qscbm187531
 */
public class StringUtil {
    public final static Pattern DEL_PATTERN = Pattern.compile("%");
    public final static Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    public final static Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    public final static Pattern CHINESE = Pattern.compile("[^u4E00-u9FA5]+", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    public static final Pattern BRACKET_NUMBER = Pattern.compile("\\[(\\d+)\\]");
    /**
     * �Ƿ�Ϊ��
     *
     * @param str �ַ���
     * @return true/��
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0 || str.isEmpty();
    }

    /**
     * �Ƿ�Ϊ��
     *
     * @param str �ַ���
     * @return true/��
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * һ���ַ����Ƿ�Ϊ����
     *
     * @param value �ַ���
     * @return true�����ң�false������
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
     * ��һ���ֽڼ���תΪ�ַ�����UTF-8)
     *
     * @param data �ֽ�
     * @return �ַ���
     */
    public static String toString(byte[] data){
        try{
            return toString(data, "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ��һ���ֽڼ���תΪ�ַ���
     *
     * @param data �ֽ�
     * @param charset ����
     * @return �ַ���
     */
    public static String toString(byte[] data, String charset){
        try{
            return new String(data, charset);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ���ݷָ�����ȡ�Զ�������
     * ���磺[123]����123 ��ȡ�� ����123
     *
     * @param str       �ַ���
     * @param separator �ָ���
     * @return �ַ���
     */
    public static String getSeparatorCustomName(String str, String separator) {
        return str.substring(str.indexOf(separator) + 1);
    }

    /**
     * ��ȡ[]�ڵ�����
     * ���磺[123]����123 ��ȡ�� 123
     *
     * @param str �ַ���
     * @return ����
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
     * ת��Сд
     *
     * @param str �ַ���
     * @return Сд�ַ���
     */
    public static String toLowerCase(String str) {
        return str != null ? str.toLowerCase() : null;
    }

    /**
     * ����ĸ��д
     *
     * @param str �ַ���
     * @return ����ĸ��д�ַ���
     */
    public static String capture(String str) {
        return StringUtils.capitalize(str);
    }

    /**
     * �ظ�ƴ���ַ���
     *
     * @param str �ַ���
     * @param repeatInt �ظ�����
     * @return �ַ���
     */
    public static String repeat(String str, int repeatInt) {
        return StringUtils.repeat(str, repeatInt);
    }

    /**
     * ��#�滻�ɿո�
     *
     * @param str �ַ���
     * @return �滻����ַ���
     */
    public static String replaceSpace(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        return str.replace("#", " ");
    }

    /**
     * �»���ת�շ�
     *
     * @param str �ַ�
     * @return ���
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
     * �շ�ת�»���
     *
     * @param str �ַ�
     * @return ���
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
     * ȥ���ַ��������еĿհ׷�
     *
     * @param str �ַ���
     * @return ���ַ���
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
     * ���������ַ� %
     *
     * @param str ����
     * @return str
     */
    public static String stringFilter(String str) {
        return DEL_PATTERN.matcher(str).replaceAll("").trim();
    }

    /**
     * ת���ַ���ΪUUID
     *
     * @param uuid �ַ���
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
     * ת�� Char Ϊ Unicode �ַ�
     *
     * @param c Char
     * @return Unicode �ַ�
     */
    public static String unicode(char c) {
        return "\\u" + String.format("%04x", (int) c).toUpperCase(Locale.ROOT);
    }
}