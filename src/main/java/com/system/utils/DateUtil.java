package com.system.utils;

import com.taobao.api.internal.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: xo
 * @NAME: DateUtil
 * @DATE: 2020/7/23
 * @Description: 日期工具类
 **/
public class DateUtil {
    private static Log LOG = LogFactory.getLog(DateUtil.class);

    public static final String MM_DD = "MM-dd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMM = "yyyyMM";
    public static final String HH = "HH";
    public static final int SECOND = 0;
    public static final int MINUTE = 1;
    public static final int HOUR = 2;
    public static final int DAY = 3;
    public static final int MONTH = 4;
    public static final int YEAR = 5;

    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (StringUtils.isEmpty(pattern)) {
            return date.toString();
        }
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        return formater.format(date);
    }
    /**
     * <将日期格式化成格式为yyyyMMddHHmmss的字符串>
     * <功能详细描述>
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String formatToYYYYMMDDHHMMSS(Date date){
        return format(date, YYYYMMDDHHMMSS);
    }
    /**
     * <将日期格式化成格式为yyyyMMddHHmmss的字符串>
     * <功能详细描述>
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String formatToYYYYMMDDHHMMSS(Long date){
    	return format(new Date(date), YYYYMMDDHHMMSS);
    }
    /**
     * <将日期格式化成格式为yyyyMMdd的数值>
     * <功能详细描述>
     * @param date
     * @return Long
     * @see [类、类#方法、类#成员]
     */
    public static Long formatToYYYYMMDD(Long date){
        return Long.parseLong(format(new Date(date), YYYYMMDD));
    }
    /**
     * <将日期格式化成格式为yyyyMMdd的数值>
     * <功能详细描述>
     * @param date
     * @return Long
     * @see [类、类#方法、类#成员]
     */
    public static Long formatToYYYYMMDD(Date date){
        return Long.parseLong(format(date, YYYYMMDD));
    }
    
    public static Long formatToHH(Date date){
        return Long.parseLong(format(date, HH));
    }

    /**
     * 增加一小时的方法
     * 
     * @param day
     * @param x
     * @return
     */
    public static String addHours(String day, int x) {
        // 24小时制
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS);
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, x);// 24小时制
        date = cal.getTime();
        cal = null;
        return format.format(date);
    }

    /**
     * 增加一小时的方法
     * @param date
     * @param x
     * @return
     */
    public static Date addHours(Date date, int x) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, x);// 24小时制
        return date = cal.getTime();
    }

    /**
     * 增加天的方法
     * @param date
     * @param x
     * @return
     */
    public static Date addDay(Date date, int x) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, x);// 24小时制
        date = cal.getTime();
        cal = null;
        return date;
    }

    /**
     * 获取增加天数的日期
     * @param dateNo 日期yyyyMMdd
     * @param days
     * @return
     */
    public static long getDateNo(long dateNo, int days)
    {
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String dateNoStr = String.valueOf(dateNo);
		int year = Integer.valueOf(dateNoStr.substring(0, 4));
		int month = Integer.valueOf(dateNoStr.substring(4, 6));
		int day = Integer.valueOf(dateNoStr.substring(6, 8));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		cal.add(Calendar.DATE, days);
		Date date = cal.getTime();
		String endDate = sf.format(date);
		return Long.valueOf(endDate);
    }
    /***
     * <获取指定时间的小时>
     * <功能详细描述>
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static int getHour(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }
    /***
     * <获取指定时间的分钟>
     * <功能详细描述>
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static int getMinute(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }
    /**
     * 增加分钟的方法
     * 
     * @param day
     * @param x
     * @return
     */
    public static String addMinute(String day, int x) {
        // 24小时制
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS);
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, x);// 24小时制
        date = cal.getTime();
        cal = null;
        return format.format(date);
    }

    public static Date addMinute(Date date, int x){
        if(date == null){
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, x);
        
        date = cal.getTime();
        return date;
    }
    
    /**
     * 将日期字符串解析成指定格式的Date对象
     * 
     * @param dateTime
     *            日期字符串
     * @param format
     *            指定格式
     * @return （正确格式）日期对象
     * @throws ParseException
     */
    public static Date parse(String dateTime, String format) {
        if (dateTime == null || dateTime.length() <= 0) {
            return null;
        }
        String sDateTime = ((dateTime.indexOf('.') > 0)) ? dateTime.substring(0, dateTime.indexOf('.')) : dateTime;
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(sDateTime);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 将日期字符串解析成"yyyy-MM-dd HH:mm:ss"格式的Date对象
     * 
     * @param dateTime
     *            日期字符串
     * @return （正确格式）日期对象
     * @throws ParseException
     */
    public static Date parseDateTime(String dateTime) throws ParseException {
        return parse(dateTime, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将日期字符串解析成"yyyy-MM-dd"格式的Date对象
     * 
     * @param dateTime
     *            日期字符串
     * @return （正确格式）日期对象
     * @throws ParseException
     */
    public static Date parseDate(String dateTime) {
        return parse(dateTime, YYYY_MM_DD);
    }

    /**
     * 获取n天后的日期
     * 
     * @param date
     * @param days
     */
    public static Date getDateAfter(final Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 获得指定日期的第一秒(yyyy-MM-dd 00:00:00:000)
     * 
     * @param fullDate
     *            日期
     * @return 日期
     */
    public static Date getFirstSecondOfTheDay(Date fullDate) {
        if (null == fullDate) {
            return (Date) null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fullDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定日期的最后一秒 (yyyy-MM-dd 23:59:59:000)
     * 
     * @param fullDate
     *            日期
     * @return 日期
     */
    public static Date getLastSecondOfTheDay(Date fullDate) {
        if (null == fullDate) {
            return (Date) null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fullDate);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Timestamp parseTimestamp(Object date) {
        Timestamp ts = null;
        String dateStr = null;
        if (date instanceof Date) {
            dateStr = format((Date) date, YYYY_MM_DD_HH_MM_SS);
        } else if (date instanceof String) {
            dateStr = (String) date;
            Date tmpDate = DateUtil.parse(dateStr, YYYY_MM_DD_HH_MM_SS);
            ts = parseTimestamp(tmpDate);
        } else {
            return null;
        }

        if (ts == null) {
            ts = Timestamp.valueOf(dateStr);
        }
        return ts;
    }

    public static Date getCurrentDate() {
        return new Date();
    }
    /**
     * <获取当前时间的格式为YYYYMMDDHHMMSS的字符串>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getCurrentDateForYYYYMMDDHHMMSS(){
        return formatToYYYYMMDDHHMMSS(getCurrentDate());
    }
    /**
     * <获取当前时间的格式为YYYYMM的数字>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Long getCurrentDateForYYYYMM(){
        return Long.parseLong(format(getCurrentDate(), YYYYMM));
    }
    /**
     * <获取当前时间的格式为YYYYMMDD的数字>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Long getCurrentDateForYYYYMMDD(){
        return Long.parseLong(format(getCurrentDate(), YYYYMMDD));
    }
    /**
     * <获取时间的格式为YYYYMMDD的数字>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Long getDateForYYYYMMDD(Date date){
    	return Long.parseLong(format(date, YYYYMMDD));
    }

    public static Timestamp getCurrentTime() {
        return parseTimestamp(new Date());
    }
    /**
     * <获取当前时间在24小时制中的小时数,如1点时显示1>
     * <功能详细描述>
     * @return 当前小时
     * @see [类、类#方法、类#成员]
     */
    public static int getCurrentHour(){
        Calendar cal = Calendar.getInstance();
        return  cal.get(Calendar.HOUR_OF_DAY);
    } 
    /**
     * <获取天+时段 如 101表示1号01时段，2100表示21号00时段 >
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static int getDayHour(){
        Calendar cal = Calendar.getInstance();
        Integer dayHour = cal.get(Calendar.DATE)*100 + cal.get(Calendar.HOUR_OF_DAY);
        return dayHour;
    }
    /***
     * <获取前一天23点的整形数字，格式为  天+23 如 12号可以表示为1223>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static int getDayHourFor23(){
        Calendar cal = Calendar.getInstance();
        Integer dayHour = (cal.get(Calendar.DATE)-1)*100 + 23;
        return dayHour;
    }

    public static Long calcDateBalance(Date sDate, Date eDate) {
        Long res = null;
        Long sDateAsLong = sDate.getTime();
        Long eDateAsLong = eDate.getTime();
        res = eDateAsLong - sDateAsLong;
        return res;
    }

    /**
     * 比较时间,如果sDate比eDate早,则返回1,sDate比eDate晚则返回-1,相等返回0
     * 
     * @param sDate
     * @param eDate
     * @return
     */
    public static Integer compareDate(Date sDate, Date eDate) {
        Long resBalance = calcDateBalance(sDate, eDate);
        Integer res = null;
        if (resBalance > 0) {
            res = 1;
        } else if (resBalance < 0) {
            res = -1;
        } else {
            res = 0;
        }
        return res;
    }

    public static Long getTotalMinute(Long milliseconds) {
        return milliseconds / 1000 / 60;
    }

    public static boolean isGreatThenXM(Date sDate, Date eDate, Long minute) {
        return DateUtil.calcDateBalance(sDate, eDate) / 60000 > minute;
    }

    public static boolean isInSameDay(Date sDate, Date eDate) {
        sDate = DateUtil.parseDate(DateUtil.format(sDate, DateUtil.YYYY_MM_DD));
        eDate = DateUtil.parseDate(DateUtil.format(eDate, DateUtil.YYYY_MM_DD));
        return sDate.equals(eDate);
    }

    public static String format(Timestamp t) {
        Date d = new Date(t.getTime());
        return format(d, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 二十四小时制
     * 
     * @param hour
     * @param min
     * @return
     */
    public static Date setHourAndMin(int hour, int min) {
        Calendar curr = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, 0);

        if (curr.getTimeInMillis() > cal.getTimeInMillis()) {
            cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
        }
        return cal.getTime();
    }

    /**
     * 获取两个时间的差值 单位:天数 默认毫秒
     * 
     * @param date1
     * @param date2
     * @param timeUnit
     * <br/>
     *            时间单位：<b>DateUtil.SECOND,DateUtil.MINUTE,DateUtil.HOUR,DateUtil
     *            .DAY,DateUtil.MONTH,DateUtil.YEAR<b>
     * @return
     */
    public static long getDiffDate(Date date1, Date date2, int timeUnit) {
        long diffTime = Math.abs(date1.getTime() - date2.getTime());
        switch (timeUnit) {
        case SECOND:
            diffTime = diffTime / 1000;
            break;
        case MINUTE:
            diffTime = diffTime / 60000;
            break;
        case HOUR:
            diffTime = diffTime / 3600000L;
            break;
        case DAY:
            diffTime = diffTime / 86400000L;
            break;
        case MONTH:
            diffTime = diffTime / 2592000000L;
            break;
        case YEAR:
            diffTime = diffTime / 946080000000L;
            break;
        default:
            break;
        }
        return diffTime;
    }
    
    public static String getDateFromMilliseconds(Long milliseconds){
        String res = null;
        if (milliseconds != null){
            long day=milliseconds/(24*60*60*1000);
            long hour=(milliseconds/(60*60*1000)-day*24);
            long min=((milliseconds/(60*1000))-day*24*60-hour*60);
            long s=(milliseconds/1000-day*24*60*60-hour*60*60-min*60);
            if (day > 0){
                res = day+"天"+hour+"小时"+min+"分"+s+"秒";
            }else{
                res = hour+"小时"+min+"分"+s+"秒";
            }
        }
        return res;
    }
    
    /**
     * 计算订购退款时，用于计算剩余天数（计算订购退款专用）
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDiffDay(Date beginDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(beginDate);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(endDate);

        int y1 = c1.get(Calendar.YEAR);
        int m1 = c1.get(Calendar.MONTH) + 1;
        int da1 = c1.get(Calendar.DAY_OF_MONTH);

        int y2 = (c2.get(Calendar.YEAR));
        int m2 = (c2.get(Calendar.MONTH) + 1);
        int da2 = (c2.get(Calendar.DAY_OF_MONTH));

        int total2 = (y2 - y1) * 12 * 31 + (m2 - m1) * 31 + (da2 - da1);

        return total2;
    }
}
