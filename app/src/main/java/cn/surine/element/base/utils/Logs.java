package cn.surine.element.base.utils;

import android.util.Log;

import cn.surine.element.BuildConfig;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-13 14:14
 */
public class Logs {
    private static final String TAG = "slw";

    private static boolean DEV = BuildConfig.DEBUG;


    public static void d(String msg){
        if(DEV){
            Log.d(TAG,msg);
        }
    }
}
