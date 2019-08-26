package cn.surine.element.lib_event;

import android.content.Context;
import android.content.Intent;

/**
 * Intro：事件标识表
 * @author sunliwei
 * @date 2019-08-10 15:23
 */
public class Action {

    /**自动update*/
    public static final String UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";
    public static final String UPDATE_OPTIONS = "android.appwidget.action.APPWIDGET_UPDATE_OPTIONS";
    /**手动update*/
    public static final String MY_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE_MY";
    /**主动唤醒*/
    public static final String WAKE_UP = "wake_up";


    /**默认时候的事件*/
    public static final String ELEMENT_CLICK = "ELEMENT_CLICK";
    /**点击Image的事件*/
    public static final String IMAGE_CLICK = "IMAGE_CLICK";



    /**
     * 发送广播式更新
     * @param context
     * */
    public static void sendUpdate(Context context){
        Intent intent = new Intent(MY_UPDATE);
        context.sendBroadcast(intent);
    }

    /**
     * 发送唤醒式更新
     * */
    public static void sendWakeUp(Context context){
        Intent intent = new Intent(WAKE_UP);
        context.sendBroadcast(intent);
    }


    /**发送方法*/
    public static void send(Context context,Intent intent){
        context.sendBroadcast(intent);
    }


    /**设置组*/
    public static final String SETTING = "setting";

    /**setting wifi*/
    public static final String WIFI_OPEN = "wifi_open";
    public static final String WIFI_IS_OPEN = "wifi_is_open";
    public static final String WIFI_IS_CLOSE = "wifi_is_close";
    public static final String WIFI_CLOSE = "wifi_close";

    /**setting bluetooth*/
    public static final String BLUETOOTH_OPEN = "bluetooth_open";
    public static final String BLUETOOTH_CLOSE = "bluetooth_close";
    public static final String BLUETOOTH_IS_OPEN = "bluetooth_is_open";
    public static final String BLUETOOTH_IS_CLOSE = "bluetooth_is_close";

    /**media vol*/
    public static final String MEDIA_VOL_VALUE = "media_vol_value";
    public static final String MEDIA_VOL_UP = "media_vol_up";
    public static final String MEDIA_VOL_DONW = "media_vol_down";

    /**表达式组*/
    public static final String PATTERN = "pattern";


    /**终端命令组*/
    public static final String TERMINAL = "terminal";

}
