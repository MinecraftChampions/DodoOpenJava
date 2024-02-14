package io.github.minecraftchampions.dodoopenjava.utils;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 关于 时间 的一些实用方法
 *
 * @author qscbm187531
 */
@Slf4j
public class DateUtil {
    /**
     * 简化格式1
     */
    public final static String FORMAT_ONE = "yyyy-MM-dd";
    /**
     * 简化格式2
     */
    public final static String FORMAT_TWO = "yyyy年MM月dd日";
    /**
     * 简化格式3
     */
    public final static String FORMAT_THREE = "yyyy-MM-dd HH:mm:ss";
    /**
     * 简化格式4
     */
    public final static String FORMAT_FOUR = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 字符串转date
     *
     * @param string 字符串
     * @param format 格式
     * @return date
     */
    public static Date parse(@NonNull String string, @NonNull String format) {
        try {
            return (new SimpleDateFormat(format)).parse(string);
        } catch (ParseException e) {
            log.error("解析事件错误", e);
            return null;
        }
    }

    /**
     * Date转字符串
     *
     * @param date   时间
     * @param format 格式
     * @return 字符串
     */
    public static String format(@NonNull Date date, @NonNull String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

    /**
     * 时间戳转Date
     *
     * @param timestamp 时间戳
     * @return Date
     */
    public static Date timestampToDate(@NonNull long timestamp) {
        return new Date(timestamp);
    }
}
