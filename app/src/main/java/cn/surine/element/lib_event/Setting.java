package cn.surine.element.lib_event;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Intro：控制设置项
 * @author sunliwei
 * @date 2019-08-10 20:18
 */
public class Setting {

    /**
     * 开关wifi
     * */
    public static boolean toggleWiFi(Context context) {
        int wifiStatus;
        WifiManager wm = (WifiManager) context
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

}
