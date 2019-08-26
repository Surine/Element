package cn.surine.element.lib_event;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiManager;

import cn.surine.element.base.controller.App;
import cn.surine.element.base.utils.Logs;
import cn.surine.element.base.utils.RootUtil;
import cn.surine.element.base.utils.Toasts;

/**
 * Intro：事件库,所有事件将被反射注册为js可执行事件
 * @author sunliwei
 * @date 2019-08-24 18:19
 */
public class Common {

    /**
     * 弹出一个toast
     * @param arg 接受一个参数
     * */
    public static void toast(String arg){
        Toasts.toto(arg);
    }


    /**
     * 打印日志
     * */
    public static void log(String arg){
        Logs.d(arg);
    }

    /**
     * 打开某应用
     * @param pkg 包名
     * */
    public static void openApp(String pkg) {
        try {
            App.getContext().startActivity(App.getContext().getPackageManager().getLaunchIntentForPackage(pkg));
        }catch (Exception e){
            e.printStackTrace();
            Toasts.toto("未找到应用:"+e.getMessage());
        }
    }



    /**
     * 打开url
     * */
    public static void openUrl(String url){
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.getContext().startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            Toasts.toto(e.getMessage());
        }
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


    /**
     * 判断wifi是否打开
     * @return true 打开 false关闭
     * */
    public static boolean isWifiOpen() {
        int wifiStatus;
        WifiManager wm = (WifiManager) App.getContext().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if(wm != null){
            wifiStatus = wm.getWifiState();
            if(wifiStatus == WifiManager.WIFI_STATE_ENABLED || wifiStatus == WifiManager.WIFI_STATE_ENABLING){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }


    public static final int STREAM_NOTIFICATION = AudioManager.STREAM_NOTIFICATION;//通知
    public static final int STREAM_ALARM = AudioManager.STREAM_ALARM;//闹钟
    public static final int STREAM_MUSIC = AudioManager.STREAM_MUSIC;//媒体
    public static final int STREAM_RING = AudioManager.STREAM_RING;//铃声
    public static final int STREAM_SYSTEM = AudioManager.STREAM_SYSTEM;//系统
    public static final int STREAM_VOICE_CALL = AudioManager.STREAM_VOICE_CALL;//通话

    /**
     * 以0-100为范围，获取当前的音量值
     * @return  获取当前的音量值
     */
    public static int curVol(int type) {
        AudioManager am = (AudioManager) App.getContext().getSystemService(Context.AUDIO_SERVICE);
        return 100 * am.getStreamVolume(type) / am.getStreamMaxVolume(type);
    }

    /**
     * 获取当前媒体音量
     * */
    public static int curVol(){
       return curVol(STREAM_MUSIC);
    }


    /**
     * 增加音量
     * */
    public static void volUp(int type){
        AudioManager am = (AudioManager) App.getContext().getSystemService(Context.AUDIO_SERVICE);
        am.adjustStreamVolume(type,AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
    }

    /**
     * 增加媒体音量
     * */
    public static void volUp(){
        volUp(STREAM_MUSIC);
    }


    /**
     * 降低音量
     * @param type 类型，详见静态常量值
     * */
    public static void volDown(int type){
        AudioManager am = (AudioManager) App.getContext().getSystemService(Context.AUDIO_SERVICE);
        am.adjustStreamVolume(type,AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);
    }

    /**
     * 降低媒体音量
     * */
    public static void volDown(){
        volDown(STREAM_MUSIC);
    }


    /**
     * root下执行某命令
     * @param cmd
     * */
    public static void exec(String cmd){
        RootUtil.exec(cmd);
    }


    /**
     * intent启动uri
     * @param s
     * */
    public static void intent(String s){
        Uri uri = Uri.parse(s);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        App.getContext().startActivity(intent);
    }
}
