package cn.surine.element.core;

import android.content.Context;
import android.content.Intent;

/**
 * Intro：事件分发监听器
 * @author sunliwei
 * @date 2019-08-10 18:00
 */
public interface OnDispatchListener {

    /**
     * 事件分发
     * @param context
     * @param intent
     */
    void onDispatch(Context context, Intent intent);
}
