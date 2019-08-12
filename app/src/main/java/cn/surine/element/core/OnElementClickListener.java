package cn.surine.element.core;

import android.content.Context;
import android.content.Intent;

/**
 * Intro：点击监听事件
 * @author sunliwei
 * @date 2019-08-10 17:06
 */
public interface OnElementClickListener {

    /**
     * 接收器点击事件回调
     * @param context
     * @param intent
     * */
    void onClick(Context context, Intent intent);
}
