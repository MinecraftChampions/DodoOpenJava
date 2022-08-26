package io.github.mcchampions.DodoOpenJava.Utils;

import java.util.Objects;

/**
 * 数字转换
 */
public class NumberConversions {
    private NumberConversions() {
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
}
