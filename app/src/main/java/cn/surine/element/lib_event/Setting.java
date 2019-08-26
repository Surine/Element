package cn.surine.element.lib_event;

import android.content.Context;
import android.net.wifi.WifiManager;

import cn.surine.element.base.controller.App;

/**
 * Intro：控制设置项
 * @author sunliwei
 * @date 2019-08-10 20:18
 */
public class Setting {

    /**
     * 开关wifi
     * */
    public static boolean toggleWiFi() {
        int wifiStatus;
        WifiManager wm = (WifiManager) App.getContext().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if(wm != null){
            wifiStatus = wm.getWifiState();
            if(wifiStatus == WifiManager.WIFI_STATE_ENABLED || wifiStatus == WifiManager.WIFI_STATE_ENABLING){
                wm.setWifiEnabled(false);
                return false;
            }else{
                wm.setWifiEnabled(true);
                return true;
            }
        }
        return false;
    }


    /**打开wifi*/
    public static void openWifi(){
        WifiManager wm = (WifiManager) App.getContext().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if(wm != null){
            wm.setWifiEnabled(true);
        }
    }

    /**关闭wifi*/
    public static void closeWifi(){
        WifiManager wm = (WifiManager) App.getContext().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if(wm != null){
            wm.setWifiEnabled(false);
        }
    }


}
