package cn.surine.element.core;

import android.content.Context;
import android.os.Parcelable;
import android.widget.RemoteViews;

import java.io.Serializable;
import java.util.List;

import cn.surine.element.bean.StatusInfo;

/**
 * Intro：widget基础类
 * 所有要实现的widget必须继承自此类
 *
 * Protocol:
 *      所有子类必须实现无参构造方法，无参构造方法优先解析状态，同时可用于反射获取子类对象
 *      所有调用方必须调用setWidgetJson方法设置属性
 *      所有子类必须调用build(Context)方法开始构建,否则视图为空
 *      所有子类必须重写其他必要方法
 *      无法使用不可序列化字段，所有字段默认要实现Parcelable
 *
 * @author sunliwei
 * @date 2019-08-10 17:31
 */
public abstract class Widget{


    public Widget() {
        //必须实现无参方法，用于反射获取子类对象
        getStatusInfo();
    }


    /**
     * 配置json值，此时将json发送给孩子
     * */
    public void setWidgetJson(String widgetJson) {
        if(widgetJson != null){
            fromJson(widgetJson);
        }
    }


    /**
     * 读取事件状态
     * @return StatusInfo列表，传递给build方法，可以用于读取状态
     * */
    public abstract List<StatusInfo> getStatusInfo();



    /**
     * 构建事件监听器
     * @return OnElementClickListener
     * */
    public abstract OnElementClickListener getOnElementClickListener();


    /**
     * 构建视图
     * @param context
     * @return RemoteViews
     * */
    public abstract RemoteViews build(Context context);



    /**
     * 从json串中解析视图
     * */
    public abstract void fromJson(String json);


    /**
     * 将属性转换为json
     * */
    public abstract String toJson();


    protected int appWidgetId;
    public void setAppWidgetId(int appWidgetId) {
        this.appWidgetId = appWidgetId;
    }
}
