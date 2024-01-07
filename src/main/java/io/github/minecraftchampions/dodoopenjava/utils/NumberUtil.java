package io.github.minecraftchampions.dodoopenjava.utils;

import lombok.NonNull;

/**
 * 关于 数字 的一些实用方法
 */
public class NumberUtil {
    /**
     * 转换数据类型为Int
     *
     * @param object 值
     * @return 转换后的值
     */
    public static int toInt(@NonNull Object object) {
        if (object instanceof Number) {
            return ((Number) object).intValue();
        }
        return Integer.parseInt(object.toString());
    }

    /**
     * 转换数据类型为Float
     *
     * @param object 值
     * @return 转换后的值
     */
    public static float toFloat(@NonNull Object object) {
        if (object instanceof Number) {
            return ((Number) object).floatValue();
        }
        return Float.parseFloat(object.toString());
    }

    /**
     * 转换数据类型为Double
     *
     * @param object 值
     * @return 转换后的值
     */
    public static double toDouble(@NonNull Object object) {
        if (object instanceof Number) {
            return ((Number) object).doubleValue();
        }
        return Double.parseDouble(object.toString());
    }

    /**
     * 转换数据类型为Long
     *
     * @param object 值
     * @return 转换后的值
     */
    public static long toLong(@NonNull Object object) {
        if (object instanceof Number) {
            return ((Number) object).longValue();
        }
        return Long.parseLong(object.toString());
    }

    /**
     * 转换数据类型为Short
     *
     * @param object 值
     * @return 转换后的值
     */
    public static short toShort(@NonNull Object object) {
        if (object instanceof Number) {
            return ((Number) object).shortValue();
        }

        return Short.parseShort(object.toString());
    }
}
