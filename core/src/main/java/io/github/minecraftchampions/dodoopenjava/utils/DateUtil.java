package io.github.minecraftchampions.dodoopenjava.utils;

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
 * ���� ʱ�� ��һЩʵ�÷���
 * @author qscbm187531
 */
public class DateUtil {
    /**
     * �򻯸�ʽ1
     */
    public final static String Format_One = "yyyy-MM-dd";
    /**
     * �򻯸�ʽ2
     */
    public final static String Format_Two = "yyyy��MM��dd��";
    /**
     * �򻯸�ʽ3
     */
    public final static String Format_Three = "yyyy-MM-dd hh:mm:ss";
    /**
     * �򻯸�ʽ4
     */
    public final static String Format_Four = "yyyy��MM��dd�� hhʱmm��ss��";

    /**
     * �ַ���תdate
     *
     * @param string    �ַ���
     * @param format ��ʽ
     * @return date
     */
    public static Date parse(String string, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Dateת�ַ���
     *
     * @param date   ʱ��
     * @param format ��ʽ
     * @return �ַ���
     */
    public static String format(Date date, String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

    /**
     * ��ȡʱ�����Ӻ������
     *
     * @param day ��������
     * @return ʱ��
     */
    public static Date getDate(Integer day) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.add(Calendar.DATE, day);
        return gregorianCalendar.getTime();
    }

    /**
     * ��ȡ����ʱ��(Ĭ�ϼ򻯸�ʽ1)
     *
     * @return ����ʱ��
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
     * ��ȡ����ʱ��
     *
     * @return ����ʱ��
     */
    public static Date getNow(String format) {
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
     * ��ȡ��һ����
     *
     * @return ��һ����
     */
    public static Date getMonday() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(getNow().getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    /**
     * ��ȡ��������
     *
     * @return ��������
     */
    public static Date getSunday() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(getNow().getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * ���ָ�����������ڼ�
     *
     * @param date ����
     * @return ��Ӧ������
     */
    public static Integer dayOfWeekEnum(Date date) {
        Integer[] weekDays = new Integer[]{7, 1, 2, 3, 4, 5, 6};
        Calendar calendar = Calendar.getInstance();
        return weekDays[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * LocalDateTime ת date
     *
     * @param localDateTime localDateTime
     * @return Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date ת LocalDateTime
     *
     * @param date date
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime ת����ʱ���
     *
     * @param localDateTime localDateTime
     * @return ����ʱ���
     */
    public static long toEpochSecond(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * �������Сʱ
     *
     * @param date_One ��һ��ʱ��
     * @param date_Two �ڶ���ʱ��
     * @return ���Сʱ
     */
    public static long between(Date date_One, Date date_Two) {
        return between(date_One, date_Two, ChronoUnit.HOURS);
    }

    /**
     * ����ʱ�����
     *
     * @param date_One ��һ��ʱ��
     * @param date_Two �ڶ���ʱ��
     * @param unit unit
     * @return �����
     */
    public static long between(Date date_One, Date date_Two, ChronoUnit unit) {
        return unit.between(toLocalDateTime(date_One), toLocalDateTime(date_Two));
    }

    /**
     * �������ڻ�ȡ���ܼ�
     *
     * @param date ʱ��
     * @param week �ܼ�1-7
     * @return ��Ӧ������
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
     * ʱ������
     * ����������,������ǰ�ƶ�
     *
     * @param date ����
     * @param day  ��������
     * @return ʱ��
     */
    public static Date addDate(Date date, Integer day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * ��ȡ�³�
     *
     * @param date ����
     * @return �³�
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    /**
     * ��ȡ��ĩ
     *
     * @param date ����
     * @return ��ĩ
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    /**
     * �������ڻ�ȡ���¼���
     *
     * @param date  ʱ��
     * @param month ����
     * @return ��Ӧ������
     */
    public static Date getMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, month);
        return calendar.getTime();
    }

    /**
     * �������ں�ʱ��
     *
     * @param date   ʱ��
     * @param field  ���� �ο�Calendar
     * @param offset ƫ����������Ϊ���ƫ�ƣ�����Ϊ��ǰƫ��
     * @return ��ʱ�����
     */
    public static Date offset(Date date, int field, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, offset);
        return cal.getTime();
    }
}
