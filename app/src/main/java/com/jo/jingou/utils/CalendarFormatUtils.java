package com.jo.jingou.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class CalendarFormatUtils {

    private static String week;
    public static final String[] zodiacArr = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇",
            "马", "羊"};

    public static final String[] constellationArr = {"魔羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座",
            "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    private final static int[] constellationEdgeDay = new int[]{20, 19, 21, 20, 21, 22, 23, 23,
            23, 24, 23, 22};

    /**
     * 得到传入的时间当前所在  是周几
     *
     * @param date
     * @return
     */
    public static String date2Week(String date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 定义日期格式
        Date strDate = null;
        try {
            strDate = format.parse(date.trim());// 将字符串转换为日期
        } catch (ParseException e) {
//                System.out.println("输入的日期格式不合理！");
//                UiUtils.showToast("输入的日期格式不合理！");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        if (strDate != null) {
            week = sdf.format(strDate);
        }

        String newWeek = weekFormat(week);  //把星期几转换为周几 日他娘的

        return newWeek;
    }

    private static String weekFormat(String week) {
        String newWeek = "";
        if (("星期一".equals(week))) {
            newWeek = "周 一";
        } else if (("星期二".equals(week))) {
            newWeek = "周 二";
        } else if (("星期三".equals(week))) {
            newWeek = "周 三";
        } else if (("星期四".equals(week))) {
            newWeek = "周 四";
        } else if (("星期五".equals(week))) {
            newWeek = "周 五";
        } else if (("星期六".equals(week))) {
            newWeek = "周 六";
        } else if (("星期日".equals(week))) {
            newWeek = "周 日";
        }
        return newWeek;
    }


    /**
     * @param dateStr        传入的时间字符串(例如服务器返回的)
     * @param formatinputStr 传入时间字符串的格式 例如"yyyy-MM-dd hh:mm:ss" 为 1992-01-02 11:03:23
     * @param formatOutStr   传入 输出时间字符串的格式  例如"hh:mm" 为 11:03
     * @return
     */
    public static String getDate(String dateStr, String formatinputStr, String formatOutStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(formatinputStr);
        SimpleDateFormat outFormat = new SimpleDateFormat(formatOutStr);
        Date inputParse = null;
        String format = "";
        try {
            inputParse = inputFormat.parse(dateStr);
            format = outFormat.format(inputParse);
            return format;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 将时间戳传入  输出一个自己需要的时间字符串
     *
     * @param millis    传入的时间戳
     * @param formatStr 输出时间字符串的格式  例如: hh:mm  输出为 12：00
     * @return
     */
    public static String getMillisDate(String millis, String formatStr) {
        String myMillis = millis + "000";
        DateFormat format = new SimpleDateFormat(formatStr);

        String date = "";
        try {
            long longDate = Long.parseLong(myMillis);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(longDate);
            date = format.format(calendar.getTime());
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static boolean isToday(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = null;
        try {
            date = df.parse(dateStr);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date);
            int year1 = c1.get(Calendar.YEAR);
            int month1 = c1.get(Calendar.MONTH) + 1;
            int day1 = c1.get(Calendar.DAY_OF_MONTH);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date());
            int year2 = c2.get(Calendar.YEAR);
            int month2 = c2.get(Calendar.MONTH) + 1;
            int day2 = c2.get(Calendar.DAY_OF_MONTH);
            if (year1 == year2 && month1 == month2 && day1 == day2) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断传入的日期是否是当前月
     *
     * @param date
     * @return
     */
    public static boolean dateIsCurrentMonth(String date) {

        String myMonth = "";
        try {
            myMonth = date.substring(0, date.indexOf("月"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.getTime().getMonth() + 1;

        if (("" + currentMonth).equals(myMonth)) {
            return true;
        }
        return false;
    }

    /**
     * 判断 传入的日期是否是昨天  注意格式 其实这个没什么太大用了 用底下那个吧
     *
     * @param substring
     */
    public static boolean dateIsYesterday(String substring) {
        //转换传入的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = null;
        try {
            date = df.parse(substring);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //得到昨天的日期
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        int yeaterday = -2;
        try {
            yeaterday = isYeaterday(date, time);
        } catch (ParseException e) {
        }
        if (yeaterday == 0) {
            return true;
        }
        return false;
    }


    /**
     * @param oldTime 较小的时间
     * @param newTime 较大的时间 (如果为空   默认当前时间 ,表示和当前时间相比)
     * @return -1 ：同一天.    0：昨天 .   1 ：至少是前天.
     * @throws ParseException 转换异常
     * @author LuoB.
     */
    private static int isYeaterday(Date oldTime, Date newTime) throws ParseException {
        if (newTime == null) {
            newTime = new Date();
        }
        //将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = format.format(newTime);
        Date today = format.parse(todayStr);
        //昨天 86400000=24*60*60*1000 一天
        if ((today.getTime() - oldTime.getTime()) > 0 && (today.getTime() - oldTime.getTime()) <=
                86400000) {
            return 0;
        } else if ((today.getTime() - oldTime.getTime()) <= 0) { //至少是今天
            return -1;
        } else { //至少是前天
            return 1;
        }
    }

    /**
     * 判断当前日期 是否为未来日期
     *
     * @param time2
     */
    public static boolean dateThanToday(String time2) {
        //转换传入的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date parse = null;
        try {
            parse = df.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();

        return parse.after(time);
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static String getZodica(String myDate) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = null;

        try {
            date = df.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return zodiacArr[cal.get(Calendar.YEAR) % 12];
    }

    /**
     * 根据日期获取星座
     *
     * @return
     */
    public static String getConstellation(String myDate) {
        if (("".equals(myDate)) || myDate == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = null;
        try {
            date = df.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        if (date == null) {
            return "";
        }
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day < constellationEdgeDay[month - 1] ? constellationArr[month - 1] :
                constellationArr[month];
    }

    /**
     * 根据传入的月份和天得到对应的星座
     *
     * @param month
     * @param day
     * @return
     */
    public static String getConstellation(int month, int day) {
        return day < constellationEdgeDay[month - 1] ? constellationArr[month - 1] :
                constellationArr[month];
    }

    public static Map<Integer, Long> date2CountDown(String deadLine) {
        //先把字符串转成Date类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //此处会抛异常
        Date date = null;
        Date dt = new Date();
        Long nowTime = dt.getTime();//这就是距离1970年1月1日0点0分0秒的毫秒数
        long longDate = 0;
        long timeDifference = 0;
        try {
            date = sdf.parse(deadLine);
            longDate = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (longDate != 0) {
            timeDifference = longDate - nowTime;
        }
        Map<Integer, Long> map = new HashMap<>();
        if (timeDifference < 1800000) {  //半个小时以内
            map.put(0, timeDifference / 1000);
            return map;
        } else if (timeDifference < 86400000) {
            //一天以内
            map.put(1, timeDifference / 360000);
            return map;
        } else {
            //一天之外
            map.put(2, timeDifference / 86400000);
            return map;
        }
    }
}
