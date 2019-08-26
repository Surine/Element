package cn.surine.element.lib_database;

import android.util.TimeUtils;

import cn.surine.element.base.utils.TimeUtil;

/**
 * Intro：时间数据
 * @author sunliwei
 * @date 2019-08-16 11:30
 */
public class Time {

    //当前小时
    public String hour = getHour();
    //当前分钟
    public String minute = getMinute();


    private String getMinute() {
        return TimeUtil.getMinute();
    }


    private String getHour() {
        return TimeUtil.getHour();
    }

}
