package com.zhiwei.wjgg.util;

/*
 * 创建日期 2006-1-12
 *
 * 时间运算函数
 * 
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhengqinyong <br>
 *         常用时间函数， 统一格式:日期 yyyy-MM-dd, 时间 yyyy-MM-dd HH:mm:ss
 */
public class TimeUtil
{
    static final SimpleDateFormat DATE_FORMATE = new SimpleDateFormat(
            "yyyy-MM-dd");

    static final SimpleDateFormat TIME_FORMATE = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    static final SimpleDateFormat HOUR_FORMATE = new SimpleDateFormat(
            "yyyy-MM-dd HH");

    static final SimpleDateFormat TIME_PART = new SimpleDateFormat(
            "HH:mm:ss SSS");

    static final Calendar G_CALENDAR = new java.util.GregorianCalendar();

    /**
     * 
     * @Title: getTimePointToNowOrEnd
     * @Description: TODO(获取开始到结束时间的时间点，按小时)
     * @param @return 设定文件
     * @return List<String> 返回类型
     * @throws ParseException 
     */
    public static List<String> getTimePointToNowOrEnd(long start,long end) throws ParseException
    {
        Calendar car = Calendar.getInstance();
        car.setTime(new Date(start));
        boolean flag = true;

        // 为完善表现层，填充无数据时间点
        // 创建完整时间点
        List<String> times = new ArrayList<String>();
        while (flag)
        {
            times.add(HOUR_FORMATE.format(car.getTime()));

            car.add(Calendar.HOUR, 1);
            if (end <= car.getTimeInMillis())
            {
                flag = false;
            }
            
        }

        return times;
    }

    /**
     * 判断是否是日期格式(yyyy-mm-dd)
     * 
     * @param input
     *            日期字符串
     * @return
     */
    public static boolean isDate(String input)
    {
        if (input != null && input.matches("\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d"))
        {
            int year = CommonUtils.strToInt(input.substring(0, 4));
            if (1900 > year || year > 3000)
                return false;

            int month = CommonUtils.strToInt(input.substring(5, 7));
            if (1 > month || month > 12)
                return false;

            int day = CommonUtils.strToInt(input.substring(8, 10));
            if (1 > day || day > 31)
                return false;

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 判断是否是时间格式(yy-MM-dd HH:mm:ss)
     * 
     * @param input
     *            时间字符串
     * @return
     */
    public static boolean isDatetime(String input)
    {
        if (input != null
                && input.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d"))
        {
            int year = CommonUtils.strToInt(input.substring(0, 4));
            if (1900 > year || year > 3000)
                return false;

            int month = CommonUtils.strToInt(input.substring(5, 7));
            if (1 > month || month > 12)
                return false;

            int day = CommonUtils.strToInt(input.substring(8, 10));
            if (1 > day || day > 31)
                return false;

            int hour = CommonUtils.strToInt(input.substring(11, 13));
            if (0 > hour || hour > 23)
                return false;

            int minute = CommonUtils.strToInt(input.substring(14, 16));
            if (0 > minute || minute > 59)
                return false;

            int second = CommonUtils.strToInt(input.substring(17, 19));
            if (0 > second && second > 59)
                return false;

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @return 当前时间
     */
    public static Date getCurrentTime()
    {
        return new Date();
    }

    /**
     * @return 获取当前时间字符串
     */
    public static String getCurrentTimeStr()
    {
        return formatTime(getCurrentTime());
    }

    /**
     * @return 获取当前日期字符串
     */
    public static String getCurrentDateStr()
    {
        return formatDate(getCurrentTime());
    }

    /**
     * 格式化时间
     * 
     * @param Date
     * @return String
     */
    public static synchronized String formatTime(Date date)
    {
        if (null == date)
        {
            return null;
        }

        return TIME_FORMATE.format(date);
    }

    /**
     * 格式化时间
     * 
     * @param DateString
     * @return String
     */
    public static String formatTime(String timeStr)
    {
        if (null == timeStr)
        {
            return null;
        }

        Date date = parseTime(timeStr);

        return formatTime(date);
    }

    /**
     * 格式化日期
     * 
     * @param dateString
     * @return String
     */
    public static String formatDate(String dateStr)
    {
        if (null == dateStr)
        {
            return null;
        }

        Date date = parseDate(dateStr);

        return formatDate(date);
    }

    /**
     * 格式化日期
     * 
     * @param Date
     * @return String
     */
    public static synchronized String formatDate(Date date)
    {
        if (null == date)
        {
            return null;
        }

        return DATE_FORMATE.format(date);
    }

    /**
     * 根据格式化字符串，格式化日期
     * 
     * @param Date
     *            时间
     * @param partern
     *            格式
     * @return
     */
    public static String formatDate(Date date, String pattern)
    {
        if (null == date || pattern == null)
        {
            return null;
        }

        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 格式化日期
     * 
     * @param Date
     * @return String
     */
    public static synchronized String formatDateToHour(Date date)
    {
        if (null == date)
        {
            return null;
        }

        return HOUR_FORMATE.format(date);
    }

    /**
     * 解析日期字符串
     * 
     * @param dateString
     * @return Date
     */
    public static synchronized Date parseDate(String dateStr)
    {
        if (null == dateStr)
        {
            return null;
        }

        Date date = null;
        try
        {
            date = DATE_FORMATE.parse(dateStr);
        }
        catch (ParseException e)
        {
        }

        return date;
    }

    /**
     * 解析日期字符串
     * 
     * @param dateString
     * @return Date
     */
    public static synchronized Date parseHour(String dateStr)
    {
        if (null == dateStr)
        {
            return null;
        }

        Date date = null;
        try
        {
            date = HOUR_FORMATE.parse(dateStr);
        }
        catch (ParseException e)
        {
        }

        return date;
    }

    /**
     * 解析时间字符串
     * 
     * @param timeString
     * @return Date
     */
    public static synchronized Date parseTime(String timeStr)
    {
        if (null == timeStr)
        {
            return null;
        }

        Date date = null;
        try
        {
            date = TIME_FORMATE.parse(timeStr);
        }
        catch (ParseException e)
        {
        }

        return date;
    }

    /**
     * 解析时间字符串
     * 
     * @param timeString
     * @param 时间格式
     * @return Date
     */
    public static synchronized Date parseTime(String timeStr, String pattern)
    {
        if (null == timeStr || pattern == null)
        {
            return null;
        }

        try
        {
            return new SimpleDateFormat(pattern).parse(timeStr);
        }
        catch (ParseException e)
        {
        }

        return null;
    }

    /**
     * 当前日期偏移运算(增、减几日）
     * 
     * @param skipDate
     *            年偏移量
     * @return Date 偏移日期
     */
    public static Date getSkipYear(Date date, int skipYear)
    {
        G_CALENDAR.setTime(date);
        G_CALENDAR.add(Calendar.YEAR, skipYear);

        return G_CALENDAR.getTime();
    }

    /**
     * 当前日期偏移运算(增、减几日）
     * 
     * @param skipDate
     *            日偏移量
     * @return Date 偏移日期
     */
    public static Date getSkipTime(int skipDay)
    {
        return getSkipTime(getCurrentTime(), skipDay);
    }

    /**
     * 某一时间的偏移运算(增、减几日）
     * 
     * @param Date
     * @param skipDate
     *            日偏移量
     * @return Date 偏移日期
     */
    public static synchronized Date getSkipTime(Date date, int skipDay)
    {
        G_CALENDAR.setTime(date);
        G_CALENDAR.add(Calendar.DAY_OF_MONTH, skipDay);

        return G_CALENDAR.getTime();
    }

    /**
     * 当前时间的偏移运算(增、减几日、几小时、几分、几秒）
     * 
     * @param skipDate
     *            日偏移量
     * @param skipHour
     *            偏移时
     * @param skipMinute
     *            偏移分
     * @param skipSecond
     *            偏移秒
     * @return Date 偏移日期
     */
    public static Date getSkipTime(int skipDay, int skipHour, int skipMinute,
            int skipSecond)
    {
        return getSkipTime(getCurrentTime(), skipDay, skipHour, skipMinute,
                skipSecond);
    }

    /**
     * 某一时间的偏移运算(增、减几日、几小时、几分）
     * 
     * @param date
     *            原时间
     * @param skipDate
     *            日偏移量
     * @param skipHour
     *            偏移时
     * @param skipMinute
     *            偏移分
     * @param skipSecond
     *            偏移秒
     * @return Date 偏移时间
     */
    public static Date getSkipTime(Date date, int skipDay, int skipHour,
            int skipMinute, int skipSecond)
    {
        if (null == date)
        {
            return null;
        }

        return getSkipTime(date, 0, 0, skipDay, skipHour, skipMinute,
                skipSecond);
    }

    /**
     * 某一时间的偏移运算
     * 
     * @param date
     *            原时间
     * @param skipYear
     *            年偏移量
     * @param skipMonth
     *            月偏移量
     * @param skipDay
     *            日偏移量
     * @param skipHour
     *            小时偏移量
     * @param skipMinute
     *            分偏移量
     * @return 偏移时间
     */
    public static synchronized Date getSkipTime(Date date, int skipYear,
            int skipMonth, int skipDay, int skipHour, int skipMinute,
            int skipSecond)
    {
        if (null == date)
        {
            return null;
        }

        G_CALENDAR.setTime(date);
        G_CALENDAR.add(Calendar.YEAR, skipYear);
        G_CALENDAR.add(Calendar.MONTH, skipMonth);
        G_CALENDAR.add(Calendar.DAY_OF_MONTH, skipDay);
        G_CALENDAR.add(Calendar.HOUR_OF_DAY, skipHour);
        G_CALENDAR.add(Calendar.MINUTE, skipMinute);
        G_CALENDAR.add(Calendar.SECOND, skipSecond);

        return G_CALENDAR.getTime();
    }

    private static final int MSEL_OF_HOUR = 1000 * 3600;

    private static final int MSEL_OF_DAY = MSEL_OF_HOUR * 24;

    /**
     * 计算日期相差小时数
     * 
     * @param base
     *            比较基准时间
     * @param compare
     *            比较时间
     * @return long 时偏差
     */
    public static long getSubhour(Date base, Date compare)
    {// subtrahend

        long daterange = base.getTime() - compare.getTime();
        long time = MSEL_OF_HOUR;

        return (daterange % time == 0) ? (daterange / time)
                : (daterange / time + 1);
    }

    /**
     * 计算日期相差几天
     * 
     * @param base
     *            比较基准时间
     * @param compare
     *            比较时间
     * @return long 日偏差
     */
    public static long getSubday(Date base, Date compare)
    {// subtrahend

        long daterange = base.getTime() - compare.getTime();
        long time = MSEL_OF_DAY;

        return (daterange % time == 0) ? (daterange / time)
                : (daterange / time + 1);
    }

    /**
     * @param date
     * @return 所属星期
     */
    public static int dayOfWeek(Date date)
    {
        return getFieldOfDate(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * 返回日期属性字段值，字段定义见 com.util.Calendar，
     * 
     * @param date
     *            当前日期
     * @param field
     *            @see com.util.Calendar
     * @return
     */
    public static synchronized int getFieldOfDate(Date date, int field)
    {
        G_CALENDAR.setTime(date);
        return G_CALENDAR.get(field);
    }

    /**
     * @return 取日期部分，截去时间
     */
    public static synchronized Date getDatePart(Date date)
    {
        String d = formatDate(date);
        return parseDate(d);
    }

    /**
     * @return 取日期到小时部分，截去分钟和秒
     */
    public static synchronized Date getDatePartToHour(Date date)
    {
        String d = formatDateToHour(date);

        return parseHour(d);
    }

    /**
     * @return 取时间部分，截去日期
     */
    public static synchronized Date getTimePart(Date date)
    {
        if (null == date)
            return null;

        String d = TIME_PART.format(date);
        try
        {
            return TIME_PART.parse(d);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 爬虫专用处理（xxxx年xx月xx日 xx:xx:xx 、xx月xx日 xx:xx:xx、xx小时前、xx分钟前、xx秒前等不规则时间）
     * 
     * @param String
     *            date
     * @return Date
     * */
    public static String conversionTime(String time)
    {

        if (time.contains("小时"))
        {
            time = TimeUtil.formatTime(TimeUtil.getSkipTime(0,
                    -Integer.valueOf(time.split("小时")[0]), 0, 0));
        }
        else if (time.contains("分"))
        {
            time = TimeUtil.formatTime(TimeUtil.getSkipTime(0, 0,
                    -Integer.valueOf(time.split("分")[0]), 0));
        }
        else if (time.contains("秒"))
        {
            time = TimeUtil.formatTime(TimeUtil.getSkipTime(0, 0, 0,
                    -Integer.valueOf(time.split("秒")[0])));
        }
        else if (time.contains("今天"))
        {
            time = TimeUtil.getCurrentDateStr() + " " + time.split(" ")[1]
                    + ":00";
            return time;
        }
        else if (time.contains("昨天"))
        {
            time = TimeUtil.formatDate(TimeUtil.getSkipTime(-1)) + " "
                    + time.split(" ")[1] + ":00";
            return time;
        }
        else
        {
            time = conversionTimeYMD(time);
        }

        return time;
    }

    // 处理带有年月日字符串的
    private static String conversionTimeYMD(String time)
    {
        String year = "";
        String month = "";
        String day = "";
        String second = "";
        String[] timeArray = null;
        if (time.contains("年"))
        {
            timeArray = time.split("年");
            year = timeArray[0];
            timeArray = timeArray[1].split("月");
        }
        else
        {
            year = TimeUtil.getCurrentDateStr().split("-")[0];
            timeArray = time.split("月");
        }

        if (time.contains("月"))
        {
            month = Integer.valueOf(timeArray[0]) < 10 ? "0"
                    + Integer.valueOf(timeArray[0]) : timeArray[0];
            timeArray = timeArray[1].split("日");
        }

        if (time.contains("日"))
        {
            day = Integer.valueOf(timeArray[0]) < 10 ? "0"
                    + Integer.valueOf(timeArray[0]) : timeArray[0];
        }
        if (time.split(" ").length < 2)
        {
            second = "00:00:00";
        }
        else
        {
            second = time.split(" ")[1].replace("：", ":");

            switch (second.split(":|：").length)
            {
            case 1:
                second += ":00:00";
                break;
            case 2:
                second += ":00";
                break;
            }
        }
        return year + "-" + month + "-" + day + " " + second;
    }
}
