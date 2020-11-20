/**
 *
 */
package com.ozvale.utils.date;


import com.ozvale.utils.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工具类
 * <p>
 * <Strong>Date: </Strong> 2015年4月3日 下午3:58:22
 *
 * @author Spartacus
 */
public class DateUtils {

    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static SimpleDateFormat FORMAT_yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat FORMAT_yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

    /**
     * 获取当前时间
     * <p>
     * <h1>created by Spartacus at 2014年8月12日 下午5:32:29</h1>
     * <p>
     *
     * @return yyyy-MM-dd HH:mm:ss 格式的当前时间
     */
    public static String getCurrentTime() {
        return FORMAT_yyyyMMddHHmmss.format(new Date());
    }

    /**
     * 根据固定的patten格式化时间
     * <p>
     * <h1>created by Spartacus at 2014年8月12日 下午5:31:31</h1>
     * <p>
     *
     * @param date   要格式化的时间
     * @param patten 格式化的patten
     * @return 格式化后的字符串
     */
    public static String format(Date date, String patten) {

        SimpleDateFormat sdf = new SimpleDateFormat(patten);

        return sdf.format(date);
    }

    /**
     * 根据默认 yyyy-MM-dd HH:mm:ss patten格式化时间
     * <p>
     * <h1>created by Spartacus at 2014年8月12日 下午5:31:31</h1>
     * <p>
     *
     * @param date   要格式化的时间
     * @param patten 格式化的patten
     * @return 格式化后的字符串
     */
    public static String format(Date date) {
        return FORMAT_yyyyMMddHHmmss.format(date);
    }

    /**
     * 获取理论0时间(1970-01-01 08:00:00),毫秒数为0
     * <p>
     * <h1>created by Spartacus at 2014年8月12日 下午5:30:14</h1>
     * <p>
     *
     * @return 1970-01-01 08:00:00
     */
    public static Date getEarliestDate() {
        return new Date(0);
    }

    /**
     * 获取当地时间和太平洋标准时差（时区） 单位:小时
     * <p>
     * <h1>created by Spartacus at 2014年8月12日 下午5:29:56</h1>
     * <p>
     *
     * @return 当地时间和太平洋标准时差（时区） 单位:小时
     */
    public static int getUTCOffset() {
        return TimeZone.getDefault().getRawOffset() / (1000 * 60 * 60);
    }

    /**
     * 将字符串转换为时间
     * <p>
     * <h1>created by Spartacus at 2014年8月14日 下午2:48:46</h1>
     * <p>
     *
     * @param time 时间字符串
     *             <p>
     *             时间格式： 2014?08?19?13?00?00?00 (?匹配任意非数字字符串),时分秒毫秒的值可以缺省。
     *             <p>
     *             例如：1991年2月1日11点12分01秒
     * @return 转换后的时间
     * @throws ParseException
     */
    private static Date parseString(String time) throws ParseException {

        String[] array = time.trim().split("\\D+");

        StringBuffer sb = new StringBuffer();

        int i = 0;
        while (i < 7) {
            if (i < array.length) {
                sb.append(array[i]);
            } else {
                sb.append("0");
            }
            sb.append("-");
            i++;
        }

        sb.deleteCharAt(sb.length() - 1);

        return FORMAT_yyyyMMddHHmmssSSS.parse(sb.toString());
    }

    /**
     * 将对象转换为时间
     * <p>
     * <h1>created by Spartacus at 2015年1月18日 上午9:54:45</h1>
     * <p>
     *
     * @param 可转换的时间对象 <p>
     *                 时间字符串 时间格式： 2014?08?19?13?00?00?00 (?匹配任意非数字字符串),时分秒毫秒的值可以缺省。
     *                 例如：1991年2月1日11点12分01秒
     *                 <p>
     *                 毫秒
     * @return
     * @throws ParseException
     */
    public static Date parse(Object obj) throws ParseException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        } else if (obj instanceof String) {
            String s = (String) obj;
            // 毫秒
            if (StringUtils.isNumber(String.valueOf(obj).trim())) {
                return new Date(Long.parseLong(s));
            } else {
                return parseString(s);
            }
        } else if (obj instanceof Number) {
            return new Date((Long) obj);
        } else {
            throw new ParseException("无法转换的时间 : " + obj, 0);
        }
    }

    /**
     * 尝试将对象转换为时间,失败返回null
     * <p>
     * <h1>created by Spartacus at 2015年1月18日 上午9:54:45</h1>
     * <p>
     *
     * @param 可转换的时间对象 <p>
     *                 时间字符串 时间格式： 2014?08?19?13?00?00?00 (?匹配任意非数字字符串),时分秒毫秒的值可以缺省。
     *                 例如：1991年2月1日11点12分01秒
     *                 <p>
     *                 毫秒
     * @return
     * @throws ParseException
     */
    public static Date tryParse(Object obj) {
        Date d = null;
        try {
            d = parse(obj);
        } catch (Exception e) {
        }
        return d;
    }

}
