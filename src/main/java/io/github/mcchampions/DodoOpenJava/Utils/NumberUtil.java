package io.github.mcchampions.DodoOpenJava.Utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 关于 数字 的一些实用方法
 */
public class NumberUtil {
    public final static Pattern NUMERIC = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    private NumberUtil() {
    }

    /**
     * 转换数据类型为Int
     * @param object 值
     * @return 转换后的值
     */
    public static int toInt(Object object) {
        if (object instanceof Number) {
            return ((Number) object).intValue();
        }

        try {
            return Integer.parseInt(Objects.requireNonNull(object).toString());
        } catch (NumberFormatException | NullPointerException ignored) {
        }
        return 0;
    }

    /**
     * 转换数据类型为Float
     * @param object 值
     * @return 转换后的值
     */
    public static float toFloat(Object object) {
        if (object instanceof Number) {
            return ((Number) object).floatValue();
        }

        try {
            return Float.parseFloat(Objects.requireNonNull(object).toString());
        } catch (NumberFormatException | NullPointerException ignored) {
        }
        return 0;
    }

    /**
     * 转换数据类型为Double
     * @param object 值
     * @return 转换后的值
     */
    public static double toDouble(Object object) {
        if (object instanceof Number) {
            return ((Number) object).doubleValue();
        }

        try {
            return Double.parseDouble(Objects.requireNonNull(object).toString());
        } catch (NumberFormatException | NullPointerException ignored) {
        }
        return 0;
    }

    /**
     * 转换数据类型为Long
     * @param object 值
     * @return 转换后的值
     */
    public static long toLong(Object object) {
        if (object instanceof Number) {
            return ((Number) object).longValue();
        }

        try {
            return Long.parseLong(Objects.requireNonNull(object).toString());
        } catch (NumberFormatException | NullPointerException ignored) {
        }
        return 0;
    }

    /**
     * 转换数据类型为Short
     * @param object 值
     * @return 转换后的值
     */
    public static short toShort(Object object) {
        if (object instanceof Number) {
            return ((Number) object).shortValue();
        }

        try {
            return Short.parseShort(Objects.requireNonNull(object).toString());
        } catch (NumberFormatException | NullPointerException ignored) {
        }
        return 0;
    }

    /**
     * 转换数据类型为Byte
     * @param object 值
     * @return 转换后的值
     */
    public static byte toByte(Object object) {
        if (object instanceof Number) {
            return ((Number) object).byteValue();
        }

        try {
            return Byte.parseByte(Objects.requireNonNull(object).toString());
        } catch (NumberFormatException | NullPointerException ignored) {
        }
        return 0;
    }

    /**
     * 测试数值是否为有限数
     * @param d 值
     * @return true或false
     */
    public static boolean isFinite(double d) {
        return Math.abs(d) <= Double.MAX_VALUE;
    }

    /**
     * 测试数值是否为有限数
     * @param f 值
     * @return true或false
     */
    public static boolean isFinite(float f) {
        return Math.abs(f) <= Float.MAX_VALUE;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     *
     * @param str 字符串
     * @return true是数字
     */
    public static Integer isNumericToInt(String str) {
        try {
            Matcher isNum = NUMERIC.matcher(str);
            if (isNum.matches()) {
                return Integer.valueOf(str);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     *
     * @param str 字符串
     * @return true是数字
     */
    public static Long isNumericToLong(String str) {
        try {
            Matcher isNum = NUMERIC.matcher(str);
            if (isNum.matches()) {
                return Long.parseLong(str);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
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
     * 将整数格式转化为十六进制值
     *
     * @param i 数值
     * @return 十六进制数值
     */
    public static String hex(int i)
    {
        return String.format( "0x%02X", i );
    }
}
