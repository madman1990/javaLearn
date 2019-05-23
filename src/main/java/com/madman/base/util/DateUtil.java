package com.madman.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtil {

    //日志管理器
    private final static Logger logger                 = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 日期时间格式 年月日 时 分
     */
    public static final  String YYYYMMDDHHMM           = "yyyyMMddHHmm";
    /**
     * 日期时间格式
     */
    public static final  String YYYYMMDDHHMMSS         = "yyyyMMddHHmmss";
    /**
     * 日期时间格式
     */
    public static final  String YYYYMMDDHHMMSSSSS      = "yyyyMMddHHmmssSSS";
    /**
     * 日期時間格式 2017年3月22日14:41:52 王朋亮
     */
    public static final  String YYYY_MM_DDHH_MM_SS     = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期時間格式 2017/03/22 14:41:52 黄强
     */
    public static final  String YYYY_MM_DDHH_MM_SS_TWO = "yyyy/MM/dd HH:mm:ss";
    /**
     * 日期时间格式
     */
    public static final  String YYMMDDHHMMSS           = "yyMMddHHmmss";

    /**
     * 日期 格式 年月
     */
    public static final String YYYYMM       = "yyyyMM";
    /**
     * 日期 格式
     */
    public static final String YYYY_MM_DD   = "yyyy-MM-dd";
    /**
     * 年 月 日
     */
    public static final String H_YYYY_MM_DD = "yyyy年MM月dd日";
    /**
     * 日期 格式
     */
    public static final String YYYYMMDD     = "yyyyMMdd";

    /**
     * 时间格式
     */
    public static final String HHMMSS = "HHmmss";

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：yyyyMMdd.<br/>
     * @return 返回结果：String <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrDate() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    public static int compareDate(String date) {
        DateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDD);
        try {
            Date dt1 = df.parse(getCurrDate());
            Date dt2 = df.parse(date);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            logger.error("日期处理异常：" + e.getMessage(), e);
            return -1;
        }

    }

    public static void main(String[] args) {
        System.out.println(compareDate("20190511"));
    }

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：自定义格式.<br/>
     * @param format 自定义格式 <br/>
     * @return 返回结果：String <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：自定义格式.<br/>
     * @param l 时间格式 <br/>
     * @param format 自定义格式 <br/>
     * @return 返回结果：String <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrDate(long l, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(l));
    }

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：自定义格式.<br/>
     * @param date 时间 <br/>
     * @param format 自定义格式 <br/>
     * @return 返回结果：String <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：自定义格式.<br/>
     * @param date 时间 <br/>
     * @param format 自定义格式 <br/>
     * @return 返回结果：String <br/>
     * @throws QTException 异常信息
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrDate(String date, String format) throws QTException {
        if (EmptyChecker.isEmpty(date)) {
            return "";
        }
        SimpleDateFormat df    = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Date             dates = null;
        try {
            dates = df.parse(date);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return "";
        }
        DateFormat dfs = new SimpleDateFormat(format);
        return dfs.format(dates);
    }

    /**
     * getCurrDate:(获取当前日期). <br/>
     * 格式为：yyyyMMdd.<br/>
     * @param dateStr 时间字符串 <br/>
     * @return 返回结果：Date <br/>
     * @throws ParseException 转换异常 <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static Date getCurrDateStr(String dateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.parse(dateStr);
    }

    /**
     * getCurrTime:(获取当前日期). <br/>
     * 格式为：hhMMss.<br/>
     * @return 返回结果：String <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrTime() {
        DateFormat df = new SimpleDateFormat("HHmmss");
        return df.format(new Date());
    }

    /**
     * getCurrTime:(获取当前日期). <br/>
     * 格式为：hhMMss.<br/>
     * @param format 格式化 <br/>
     * @return 返回结果：String <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String getCurrTime(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * addDate:(计算时间格式). <br/>
     * @param date 时间 <br/>
     * @param num 基数 <br/>
     * @param calendarType 时间格式【Calendar:DATE 天数, DAY_OF_MONTH, YEAR,】 <br/>
     * @return 返回结果：int 返回天数 <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static Date addDate(Date date, int num, int calendarType) {
        Calendar sc = Calendar.getInstance();
        sc.setTime(date);
        sc.add(calendarType, num);
        return sc.getTime();
    }

    /**
     * addDate:(计算时间格式). <br/>
     * @param date 时间 <br/>
     * @param num 基数 <br/>
     * @param calendarType 时间格式【Calendar:DATE 天数, DAY_OF_MONTH, YEAR,】 <br/>
     * @return 返回结果：int 返回天数 <br/>
     * @throws ParseException 转换失败
     * @author Lance.Wu <br/>
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static Date addDate(String date, int num, int calendarType) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        Calendar         sc = Calendar.getInstance();
        sc.setTime(sd.parse(date));
        sc.add(calendarType, num);
        return sc.getTime();
    }

    /**
     * 【方法名】 : (这里用一句话描述这个方法的作用). <br/>
     * 【注意】: (这里描述这个方法的注意事项 – 可选).<br/>
     * 【作者】: 张朝辉 .<br/>
     * 【时间】： 2017年6月28日 下午12:08:52 .<br/>
     * 【参数】： .<br/>
     * @param date 传入日期格式
     * @param num 加的数目
     * @param calendarType 加时间对应类型
     * @param format 最后输出格式
     * @return String 时间字符串
     * <p>
     * 修改记录.<br/>
     * 修改人: 张朝辉 修改描述：创建新新件 .<br/>
     * <p/>
     */
    public static String addMinute(String date, int num, int calendarType, String format) {
        if (EmptyChecker.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat sd        = new SimpleDateFormat(format);
        Calendar         sc        = Calendar.getInstance();
        String           resultStr = null;
        try {
            sc.setTime(sd.parse(date));
            sc.add(calendarType, num);
            resultStr = sd.format(sc.getTime());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * addDate:(计算时间格式). <br/>
     * @param date 时间 <br/>
     * @param num 基数 <br/>
     * @param calendarType 时间格式【Calendar:DATE 天数, DAY_OF_MONTH, YEAR,】 <br/>
     * @param formart 返回内容格式 <br/>
     * @return 返回结果：int 返回天数 <br/>
     * @author Lance.Wu <br/>
     * 修改者: Jason.H 修改时间 : 2016年11月15日11:12:12
     * @since JDK 1.6 ServerFramework 1.0 <br/>
     */
    public static String addDate(Date date, int num, int calendarType, String formart) {
        Calendar sc = Calendar.getInstance();
        sc.setTime(date);
        sc.add(calendarType, num);
        SimpleDateFormat sd = new SimpleDateFormat(formart);
        return sd.format(sc.getTime());
    }

    /**
     * 将当前 时间加两个小时返回
     * <p>
     * <p>
     * 传入字符串类型的数据
     * @return String
     */

    @SuppressWarnings("static-access")
    public static String timeadd() {
        Date             date     = null;
        String           time     = null;
        SimpleDateFormat format   = new SimpleDateFormat("HH:mm");
        Calendar         calendar = Calendar.getInstance();
        calendar.add(calendar.HOUR_OF_DAY, 2);
        date = calendar.getTime();
        time = format.format(date);

        return time;
    }

    /**
     * 将当前 时间加2分钟返回
     * @return String
     */

    @SuppressWarnings("static-access")
    public static String timeMinueAdd() {
        Date             date     = null;
        String           time     = null;
        SimpleDateFormat format   = new SimpleDateFormat("HH:mm");
        Calendar         calendar = Calendar.getInstance();
        calendar.add(calendar.MINUTE, 2);
        date = calendar.getTime();
        time = format.format(date);

        return time;
    }

    /**
     * 将原格式 的日期字符串，转换成新的格式
     * @param date 日期对象 java.util.Date
     * @param srcFormat 原格式 java.lang.String
     * @param disFormat 目标格式 java.lang.String
     * @return String 转换后的String日期
     *
     * <pre>
     * 案例：
     *    String result = formatDate("20150404",  "yyyyMMdd", "yyyy-MM-dd");
     * 结果：result =  2015-04-04
     * </pre>
     * @Title: formatDateByFormat
     * @Description: 将原格式 的日期字符串，转换成新的格式
     * @Date May 3, 2014 21:23:41 PM
     * @modifyDate May 3, 2014 21:23:41 PM
     * @since PlatForm 1.0
     */
    public static String formatDate(String date, String srcFormat, String disFormat) {
        SimpleDateFormat srcSdf = null;
        if (EmptyChecker.isEmpty(date)) {
            return "";
        }
        try {
            srcSdf = new SimpleDateFormat(srcFormat);
            // 将原日期转换成毫秒
            long srcDateTimes = srcSdf.parse(date).getTime();
            Date d            = new Date();
            d.setTime(srcDateTimes);
            SimpleDateFormat sdf = new SimpleDateFormat(disFormat);
            return sdf.format(d).toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            srcSdf = null;
        }
    }

    /**
     * 计算天数 【方法名】 : (根据传入起始日期，天数，日期模式，计算最后天数). <br/>
     * 【作者】: 张朝辉 .<br/>
     * 【时间】： 2017年12月13日 下午2:44:16 .<br/>
     * 【参数】： .<br/>
     * @param days 天数
     * @param endFormat 日期格式化模式
     * @return String
     * @throws QTException .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: 张朝辉 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @SuppressWarnings("static-access")
    public static String getEndDateStr(int days, String endFormat) throws QTException {
        // H_YYYY_MM_DD
        Date             dates  = null;
        String           time   = null;
        SimpleDateFormat format = new SimpleDateFormat(endFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days - 1);
        dates = calendar.getTime();
        time = format.format(dates);
        return time;
    }

    /**
     * @param dateStr 日期字符串
     * @param type 类型
     * @param addTime 增加时间
     * @param format 模式
     * @return String
     */
    public static String addDate(String dateStr, int type, int addTime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date             date       = null;
        try {
            date = dateFormat.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(type, addTime);
            Date nowDate = calendar.getTime();
            return dateFormat.format(nowDate);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 【方法名】 : 日期描述工具类<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年5月8日 下午4:24:17 .<br/>
     * 【参数】： .<br/>
     * @param dateStr 时间参数 .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     * @return 返回结果
     */
    public static String formatDateRemark(String dateStr) {
        SimpleDateFormat dfs   = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Date             begin = new Date();
        Date             end;
        try {
            end = dfs.parse(dateStr);
            long between = Math.abs((end.getTime() - begin.getTime()) / 1000 / 60);
            if (between / 60 == 0) {
                return between + "分钟之前";
            }
            if (between / 60 > 0 && between / 60 < 24) {
                return between / 60 + "小时之前";
            }
            if (between / (60 * 24) > 0) {
                return between / (60 * 24) + "天之前";
            }
        } catch (ParseException e) {
            logger.error("时间格式化异常:" + e.getMessage(), e);
            return null;
        }
        return "N天前";

    }

    /**
     * 方法名: addDate <br/>
     * 描述: 日期相加<br/>
     * @param date String
     * @param format String
     * @param day int
     * @return .<br />
     */
    public static String addDate(String date, String format, int day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            long             d1  = sdf.parse(date).getTime() + (long) day * 24 * 3600 * 1000;
            return formatDate(new Date(d1), format);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    /**
     * 方法名: formatDate <br/>
     * 描述: 格式化日期<br/>
     * @param date Date
     * @param formatStr String
     * @return .<br />
     */
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat sdf   = new SimpleDateFormat(formatStr);
        String           today = sdf.format(date);
        return today;
    }

    /**
     * fromDateStringToLong:(格式转换). <br/>
     * @param inVal 初始化时间
     * @param format 格式
     * @return 返回结果：long <br/>
     * @author Lance.Wu <br/>
     * @since JDK 1.6 PayCreditServer 1.0 <br/>
     */
    public static long fromDateStringToLong(String inVal, String format) {
        Date             date        = null; // 定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat(format);
        try {
            date = inputFormat.parse(inVal);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return date.getTime();
    }

}
