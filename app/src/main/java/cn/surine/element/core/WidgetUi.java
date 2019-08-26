package cn.surine.element.core;

import android.widget.RemoteViews;

import cn.surine.element.R;

import static cn.surine.element.base.BaseConfig.PKG;

/**
 * Intro：Widget Ui 管理类
 * @author sunliwei
 * @date 2019-08-10 15:52
 */
public class WidgetUi {

    public static int REQUEST_CODE = 0;

    /**
     * 获取一个remoteView
     */
    public static RemoteViews get(int layout) {
        return new RemoteViews(PKG, layout);
    }

    public static RemoteViews getRoot() {
        return get(R.layout.widget_root);
    }


    public static RemoteViews getTouch(){
        return get(R.layout.widget_touch_helper);
    }

    /**
     * 向根布局添加一个remoteView
     */
    public static void addToRoot(RemoteViews root, RemoteViews remoteViews) {
        root.addView(R.id.widget_root, remoteViews);
    }

    /**
     * 设置imageView的图片
     */
    public static void addResource(RemoteViews remoteViews, int res) {
        remoteViews.setImageViewResource(R.id.widget_imageview, res);
    }

    /**
     * 设置位置
     * */
    public static void setGravity(String type,RemoteViews remoteViews,int value){
        remoteViews.setInt(TypeMap.getIdByType(type),"setGravity", value);
    }

    /**
     * 获取一个imageView组件
     * */
    public static RemoteViews getImage() {
        return get(R.layout.widget_imageview);
    }
}
