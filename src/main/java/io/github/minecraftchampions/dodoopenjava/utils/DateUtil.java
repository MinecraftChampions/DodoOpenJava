package io.github.minecraftchampions.dodoopenjava.utils;

import lombok.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 关于 时间 的一些实用方法
 */
public class DateUtil {
    /**
     * 简化格式1
     */
    public final static String Format_One = "yyyy-MM-dd";
    /**
     * 简化格式2
     */
    public final static String Format_Two = "yyyy年MM月dd日";
    /**
     * 简化格式3
     */
    public final static String Format_Three = "yyyy-MM-dd hh:mm:ss";
    /**
     * 简化格式4
     */
    public final static String Format_Four = "yyyy年MM月dd日 hh时mm分ss秒";

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
            throw new RuntimeException(e);
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
     * 获取时间增加后的日期
     *
     * @param day 增加天数
     * @return 时间
     */
    public static Date getDate(@NonNull Integer day) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.add(Calendar.DATE, day);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取现在时间(默认简化格式1)
     *
     * @return 现在时间
     */
    public static Date getNow() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Format_One);
        try {
            date = dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取现在时间
     *
     * @return 现在时间
     */
    public static Date getNow(@NonNull String format) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            date = dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 获取周一日期
     *
     * @return 周一日期
     */
    public static Date getMonday() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(getNow().getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    /**
     * 获取周日日期
     *
     * @return 周日日期
     */
    public static Date getSunday() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(getNow().getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 获得指定日期是星期几
     *
     * @param date 日期
     * @return 对应的星期
     */
    public static Integer dayOfWeekEnum(@NonNull Date date) {
        Integer[] weekDays = new Integer[]{7, 1, 2, 3, 4, 5, 6};
        Calendar calendar = Calendar.getInstance();
        return weekDays[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * LocalDateTime 转 date
     *
     * @param localDateTime localDateTime
     * @return Date
     */
    public static Date toDate(@NonNull LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date 转 LocalDateTime
     *
     * @param date date
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(@NonNull Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime 转毫秒时间戳
     *
     * @param localDateTime localDateTime
     * @return 毫秒时间戳
     */
    public static long toEpochSecond(@NonNull LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 计算相差小时
     *
     * @param date_One 第一个时间
     * @param date_Two 第二个时间
     * @return 相差小时
     */
    public static long between(@NonNull Date date_One, @NonNull Date date_Two) {
        return between(date_One, date_Two, ChronoUnit.HOURS);
    }

    /**
     * 计算时间相差
     *
     * @param date_One 第一个时间
     * @param date_Two 第二个时间
     * @param unit     unit
     * @return 相差多久
     */
    public static long between(@NonNull Date date_One, @NonNull Date date_Two,
                               @NonNull ChronoUnit unit) {
        return unit.between(toLocalDateTime(date_One), toLocalDateTime(date_Two));
    }

    /**
     * 根据日期获取本周几
     *
     * @param date 时间
     * @param week 周几1-7
     * @return 对应周日期
     */
    public static Date getWeek(@NonNull Date date, int week) {
        week = week + 1;
        if (week > 7) {
            week = 1;
        }
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, week);
        return calendar.getTime();
    }

    /**
     * 时间增加
     * 整数往后推,负数往前移动
     *
     * @param date 日期
     * @param day  增加天数
     * @return 时间
     */
    public static Date addDate(@NonNull Date date, @NonNull Integer day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获取月初
     *
     * @param date 日期
     * @return 月初
     */
    public static Date getFirstDayOfMonth(@NonNull Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    /**
     * 获取月末
     *
     * @param date 日期
     * @return 月末
     */
    public static Date getLastDayOfMonth(@NonNull Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    /**
     * 根据日期获取本月几号
     *
     * @param date  时间
     * @param month 几号
     * @return 对应月日期
     */
    public static Date getMonth(@NonNull Date date, int month) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, month);
        return calendar.getTime();
    }

    /**
     * 调整日期和时间
     *
     * @param date   时间
     * @param field  类型 参考Calendar
     * @param offset 偏移量，正数为向后偏移，负数为向前偏移
     * @return 新时间对象
     */
    public static Date offset(@NonNull Date date, int field, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, offset);
        return cal.getTime();
    }
}
