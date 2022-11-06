package io.github.mcchampions.DodoOpenJava.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ���� ���� ��һЩʵ�÷���
 * @author qscbm187531
 */
public class NumberUtil {
    /**
     * ��������
     */
    public final static Pattern NUMERIC = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    /**
     * ת����������ΪInt
     *
     * @param object ֵ
     * @return ת�����ֵ
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
     * ת����������ΪFloat
     *
     * @param object ֵ
     * @return ת�����ֵ
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
     * ת����������ΪDouble
     *
     * @param object ֵ
     * @return ת�����ֵ
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
     * ת����������ΪLong
     *
     * @param object ֵ
     * @return ת�����ֵ
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
     * ת����������ΪShort
     *
     * @param object ֵ
     * @return ת�����ֵ
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
     * ת����������ΪByte
     *
     * @param object ֵ
     * @return ת�����ֵ
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
     * ������ֵ�Ƿ�Ϊ������
     *
     * @param d ֵ
     * @return true��false
     */
    public static boolean isFinite(double d) {
        return Math.abs(d) <= Double.MAX_VALUE;
    }

    /**
     * ������ֵ�Ƿ�Ϊ������
     *
     * @param f ֵ
     * @return true��false
     */
    public static boolean isFinite(float f) {
        return Math.abs(f) <= Float.MAX_VALUE;
    }

    /**
     * ����������ʽ�ж��ַ����Ƿ�������
     *
     * @param str �ַ���
     * @return true������
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
     * ����������ʽ�ж��ַ����Ƿ�������
     *
     * @param str �ַ���
     * @return true������
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
     * ��������ʽת��Ϊʮ������ֵ
     *
     * @param i ��ֵ
     * @return ʮ��������ֵ
     */
    public static String hex(int i) {
        return String.format("0x%02X", i);
    }

    /**
     * ���ж��ֲ�ѯ
     * <p>
     * ��һ��Ҫ����С�����˳��
     *
     * @param srcArray ��ƥ�������
     * @param des Ҫƥ���ֵ
     * @return ����ƥ������ֵ��������������-1Ϊƥ�䲻��
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
     * �ṩ��ȷ�ļӷ�����
     *
     * @param v1 ������
     * @param v2 ����
     * @return ���������ĺ�
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
     * �ṩ��ȷ�ļ�������
     *
     * @param v1 ������
     * @param v2 ����
     * @return ���������Ĳ�
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
     * �ṩ��ȷ�ĳ˷�����
     *
     * @param v1 ������
     * @param v2 ����
     * @return ���������Ļ�
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
     * �ṩ����ԣ���ȷ�ĳ�������,�����������������ʱ,
     * ��ȷ��С�����Ժ�10λ,�Ժ��������������.
     *
     * @param v1 ������
     * @param v2 ����
     * @return ������������
     */
    public static Double divide(Double v1, Double v2) {
        return divide(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * �ṩ����ԣ���ȷ�ĳ�������.
     * �����������������ʱ,��scale����ָ ������,�Ժ��������������.
     *
     * @param v1    ������
     * @param v2    ����
     * @param scale ��ʾ��Ҫ��ȷ��С�����Ժ�λ
     * @return ������������
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
     * �ṩ��ȷ��С��λ�������봦��
     *
     * @param v     ��Ҫ�������������
     * @param scale С���������λ
     * @return ���������Ľ��
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
