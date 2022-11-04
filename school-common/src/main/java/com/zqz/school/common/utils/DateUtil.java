package com.zqz.school.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: zqz
 * @Description:
 * @Date: Created in 7:24 PM 2020/6/19
 */
public class DateUtil {
    private static ThreadLocal<SimpleDateFormat> format1 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private static ThreadLocal<SimpleDateFormat> format2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    private static ThreadLocal<SimpleDateFormat> format3 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private static ThreadLocal<SimpleDateFormat> format4 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    public static String parse2yyyyMMddHHmmss(Date date) {
        try {
            return format1.get().format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parse2YYYYMMDDStr(Date date) {
        try {
            return format2.get().format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getTime2MSString(Date date) {
        try {
            return format4.get().format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTime2DateString(Date date) {
        try {
            return format3.get().format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date format1ToDate(String time) {
        try {
            return format1.get().parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date format2ToDate(String time) {
        try {
            return format2.get().parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date format3ToDate(String time) {
        try {
            return format3.get().parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String format1AddMin(String time, int min) {
        try {
            Date date = format1ToDate(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, min);
            return parse2yyyyMMddHHmmss(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format1AddMin(Date date, int min) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, min);
            return parse2yyyyMMddHHmmss(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static int compareFormat1Time(String startTime, String endTime) {
        try {
            Date startDate = format1ToDate(startTime);
            Date endDate = format1ToDate(endTime);
            long diffTime = endDate.getTime() - startDate.getTime();
            return (int) diffTime / 60 / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取指定日期天数的时间 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getTimeByDate(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return format1.get().format(calendar.getTime());
    }

    /**
     * 获取指定日期天数的时间 yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String getTimeByDateFormat3(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return format3.get().format(calendar.getTime());
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @auth zy
     * @since 2022-07-12
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }


        if (nowTime.after(startTime) && nowTime.before(endTime)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串转日期
     *
     * @param str
     * @param format
     * @return
     * @throws Exception
     */
    public static Date str2Date(String str, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(str);
        return date;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(date);
        return str;
    }

    /**
     * 比较两个时间 时分秒 大小
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean compTime(String s1, String s2) {
        try {
            if (s1.indexOf(":") < 0 || s1.indexOf(":") < 0) {
                System.out.println("格式不正确");
            } else {
                String[] array1 = s1.split(":");
                int total1 = Integer.valueOf(array1[0]) * 3600 + Integer.valueOf(array1[1]) * 60 + Integer.valueOf(array1[2]);
                String[] array2 = s2.split(":");
                int total2 = Integer.valueOf(array2[0]) * 3600 + Integer.valueOf(array2[1]) * 60 + Integer.valueOf(array2[2]);
                return total1 - total2 > 0 ? true : false;
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            return true;
        }
        return false;

    }

    public static String calculateTimeStr(String timeStart, String timeEnd) {
        try {
            Date startDate = format1ToDate(timeStart);
            Date endDate = format1ToDate(timeEnd);
            long diff = endDate.getTime() - startDate.getTime();
            //相差天数
            long hour = diff / (60 * 60 * 1000);
            long min = diff / (60 * 1000) - hour * 60;
            return String.format("%s小时%s分钟", hour, min);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        //当前时间   时分秒
//        GregorianCalendar calendar = new GregorianCalendar();
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        int second = calendar.get(Calendar.SECOND);
//
//        String hour_str = String.valueOf(hour);
//        String minute_str = String.valueOf(minute);
//        String second_str = String.valueOf(second);
//
//        String nowTime = hour_str + ":" + minute_str + ":" + second_str;
//        System.out.println("nowTime === " + nowTime);
//
//        String datestr = "08:30-14:30";
//        String startTime = datestr.substring(0, 5) + ":00";
//        String endTime = datestr.substring(6) + ":00";
//        System.out.println("startTime === " + startTime);
//        System.out.println("endTime === " + endTime);
//        boolean startT = compTime(nowTime, startTime);
//        boolean endT = compTime(nowTime, endTime);
//        if (startT && !endT) {
//            System.out.println("在允许时间范围内 == " + nowTime);
////            return true;
//        } else {
//            System.out.println("不在允许时间范围内 == " + nowTime);
//        }

        String s = calculateTimeStr("2022-10-25 09:25:34", "2022-10-26 14:12:38");
        System.out.println(s);

    }
}


