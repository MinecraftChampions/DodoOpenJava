package io.github.mcchampions.DodoOpenJava.Utils;

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
public class DataUtil {
    /**
     * 简化格式
     */
    public final static String YYYY = "yyyy-MM-dd";

    /**
     * 字符串转date
     *
     * @param str    字符串
     * @param format 格式
     * @return date
     */
    public static Date parse(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * date转字符串
     *
     * @param date   时间
     * @param format 格式
     * @return 字符串
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param dateTime 时间
     * @return 时间间隔
     */
    public static int getDifferDay(Long dateTime) {
        return (int) ((System.currentTimeMillis() - dateTime) / (1000 * 3600 * 24));
    }

    /**
     * 时间增加
     *
     * @param day 增加天数
     * @return 时间
     */
    public static Date getDate(Integer day) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 判断传入的时间是否大于2120-01-01 00:00:00
     *
     * @param date 日期
     * @return true 是
     */
    public static boolean isPerpetual(Date date) {
        return date.getTime() > 4733481600000L;
    }

    /**
     * 获取今日日期
     *
     * @return 今日日期
     */
    public static Date getToday() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY);
        try {
            date = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
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
        calendar.setTimeInMillis(getToday().getTime());
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
        calendar.setTimeInMillis(getToday().getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 获得指定日期是星期几
     *
     * @param date 日期
     * @return 对应的星期
     */
    public static Integer dayOfWeekEnum(Date date) {
        Integer[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar calendar = Calendar.getInstance();
        return weekDays[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * LocalDateTime 转 date
     *
     * @param localDateTime localDateTime
     * @return Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date 转 LocalDateTime
     *
     * @param date date
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime 转毫秒时间戳
     *
     * @param localDateTime localDateTime
     * @return 毫秒时间戳
     */
    public static long toEpochSecond(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 计算相差小时
     *
     * @param dateOne 第一个时间
     * @param dateTwo 第二个时间
     * @return 相差小时
     */
    public static long between(Date dateOne, Date dateTwo) {
        return between(dateOne, dateTwo, ChronoUnit.HOURS);
    }

    /**
     * 计算时间相差
     *
     * @param dateOne 第一个时间
     * @param dateTwo 第二个时间
     * @param unit unit
     * @return 相差多久
     */
    public static long between(Date dateOne, Date dateTwo, ChronoUnit unit) {
        return unit.between(toLocalDateTime(dateOne), toLocalDateTime(dateTwo));
    }

    /**
     * 根据日期获取本周几
     *
     * @param date 时间
     * @param week 周几1-7
     * @return 对应周日期
     */
    public static Date getWeek(Date date, int week) {
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
    public static Date addDate(Date date, Integer day) {
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
    public static Date getFirstDayOfMonth(Date date) {
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
    public static Date getLastDayOfMonth(Date date) {
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
    public static Date getMonth(Date date, int month) {
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
    public static Date offset(Date date, int field, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, offset);
        return cal.getTime();
    }
}
