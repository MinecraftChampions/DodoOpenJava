package io.github.minecraftchampions.dodoopenjava.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 关于 数字 的一些实用方法
 */
public class NumberUtil {
    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000000, "m");
        map.put(900000, "cm");
        map.put(500000, "d");
        map.put(100000, "c");
        map.put(90000, "xc");
        map.put(50000, "l");
        map.put(10000, "x");
        map.put(9000, "Mx");
        map.put(5000, "v");
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    /**
     * 转换数字为罗马数字
     *
     * @param number 数字
     * @return 罗马数字
     */
    public static String toRoman(int number) {
        if (number > 0) {
            int l = map.floorKey(number);
            if (number == l) {
                return map.get(number);
            }
            return map.get(l) + toRoman(number - l);
        } else {
            return String.valueOf(number);
        }
    }

    /**
     * 数字正则
     */
    public final static Pattern NUMERIC = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    /**
     * 转换数据类型为Int
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param d 值
     * @return true或false
     */
    public static boolean isFinite(double d) {
        return Math.abs(d) <= Double.MAX_VALUE;
    }

    /**
     * 测试数值是否为有限数
     *
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
     * 将整数格式转化为十六进制值
     *
     * @param i 数值
     * @return 十六进制数值
     */
    public static String hex(int i) {
        return String.format("0x%02X", i);
    }

    /**
     * 进行二分查询
     * <p>
     * 请一定要按从小到大的顺序
     *
     * @param srcArray 被匹配的数组
     * @param des      要匹配的值
     * @return 返回匹配的数字的在数组的索引，-1为匹配不到
     */
    public static int binarySearch(Integer[] srcArray, int des) {
        int start = 0;
        int end = srcArray.length - 1;
        while (start <= end) {
            int middle = (end + start) >>> 1;
            if (des == srcArray[middle]) {
                return middle;
            } else if (des < srcArray[middle]) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    private static final int DEF_DIV_SCALE = 4;

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static Double add(Double v1, Double v2) {
        if (v1 == null || v2 == null) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(DEF_DIV_SCALE, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static Double substract(Double v1, Double v2) {
        if (v1 == null || v2 == null) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).setScale(DEF_DIV_SCALE, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static Double multiply(Double v1, Double v2) {
        if (v1 == null || v2 == null) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(DEF_DIV_SCALE, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算,当发生除不尽的情况时,
     * 精确到小数点以后10位,以后的数字四舍五入.
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static Double divide(Double v1, Double v2) {
        return divide(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算.
     * 当发生除不尽的情况时,由scale参数指 定精度,以后的数字四舍五入.
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static Double divide(Double v1, Double v2, int scale) {
        if (v1 == null || v2 == null) {
            return null;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static Double round(Double v, int scale) {
        if (v == null) {
            return null;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
    }
}
