package cn.surine.element.core;

import android.content.Context;
import android.content.Intent;

import cn.surine.element.base.BaseConfig;
import cn.surine.element.lib_event.Action;
import cn.surine.element.lib_event.Setting;

import static cn.surine.element.base.BaseConfig.ACTION_EVENT_DATA;
import static cn.surine.element.base.BaseConfig.ACTION_SUB_EVENT;
import static cn.surine.element.base.BaseConfig.APP_WIDGET_ID;

/**
 * Intro：事件分发管理器
 * @author sunliwei
 * @date 2019-08-23 16:53
 */
public class EventManager {

    public static class INS{
        public static final EventManager e = new EventManager();
    }

    public static EventManager getInstance(){
        return INS.e;
    }

    private OnDispatchListener onDispatchListener;


    public OnDispatchListener getOnDispatchListener() {
        onDispatchListener = new OnDispatchListener() {
            @Override
            public void onDispatch(Context context, Intent intent) {
                String sub = intent.getStringExtra(ACTION_SUB_EVENT);
                String data = intent.getStringExtra(ACTION_EVENT_DATA);
                if(Action.SETTING.equals(sub)){
                    handlerSetting(data);
                }
            }
        };
        return onDispatchListener;
    }

    /**
     * 处理设置事件
     * */
    private void handlerSetting(String data) {
        switch (data){
            default:break;
            case Action.WIFI_OPEN:
                Setting.openWifi();
                break;
            case Action.WIFI_CLOSE:
                Setting.closeWifi();
                break;
        }
    }
}
