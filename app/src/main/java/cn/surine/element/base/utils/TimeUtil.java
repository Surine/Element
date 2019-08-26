package cn.surine.element.base.utils;

import java.util.Date;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-16 11:33
 */
public class TimeUtil {
    public static String getHour(){
        return String.valueOf(new Date().getHours());
    }

    public static String getMinute(){
        return String.valueOf(new Date().getMinutes());
    }
}
