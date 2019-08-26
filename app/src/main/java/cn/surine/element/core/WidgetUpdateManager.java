package cn.surine.element.core;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Intro：widget更新管理器
 * @author sunliwei
 * @date 2019-08-14 11:48
 */
public class WidgetUpdateManager {

    /**
     * 更新方法
     * */
    public static void updateRemoteUi(RemoteViews root, Context context){
        ComponentName componentName = new ComponentName(context, WidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(componentName, root);
    }


    /**
     * 优先使用此方法更新，节省内存消耗
     * */
    public static void updateRemoteUi(Context context,int appWidgetId,RemoteViews remoteViews){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
    }


    /**
     * 局部更新
     * */
    public static void partiallyUpdate(Context context,int appWidgetId,RemoteViews remoteViews){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.partiallyUpdateAppWidget(appWidgetId,remoteViews);
    }
}
