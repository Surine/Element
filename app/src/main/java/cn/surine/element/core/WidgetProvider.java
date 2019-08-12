package cn.surine.element.core;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.surine.element.BuildConfig;
import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.bean.WidgetInfo;
import cn.surine.element.main.Action;
import cn.surine.element.main.WidgetsManager;

import static cn.surine.element.base.BaseConfig.APP_WIDGET_ID;
import static cn.surine.element.base.BaseConfig.PKG;

/**
 * Intro：基础承载
 * @author sunliwei
 * @date 2019-08-10 15:20
 */
public class WidgetProvider extends AppWidgetProvider {

    private static final String TAG = "WidgetProvider";
    //点击事件唯一标示
    private static int REQUEST_CODE_IN_PROVIDER = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        //pass context
        WidgetsManager.getInstance().setContext(context);

        for (int i:
             appWidgetIds) {
            Log.d(TAG,"onUpdate:"+i);
        }

//        for (int i = 0; i < appWidgetIds.length; i++) {
//            Intent intent = new Intent(context,WidgetService.class);
//            context.startService(intent);
//        }


        RemoteViews remoteViews = new RemoteViews(PKG, R.layout.widget_no_view);

//        for (int i = 0; i < appWidgetIds.length; i++) {
//            root = WidgetsManager.getInstance().get(context,appWidgetIds[i]).build(context);
//        }

        for(int i = 0; i < appWidgetIds.length; i++) {
            int currentId = appWidgetIds[i];


            Intent intent = new Intent(Action.NO_VIEW_CLICK_EVENT);
            intent.putExtra(APP_WIDGET_ID,currentId);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE_IN_PROVIDER++, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_tv_no_view, pendingIntent);

            //根据当前扫描id获取存储的view
            remoteViews.setTextViewText(R.id.widget_tv_no_view,"无视图，点此前往Element加载视图,Id:"+currentId);

            WidgetInfo widgetInfo = new WidgetInfo(currentId,"",null);
            if(LitePal.where("appWidgetId = ?", String.valueOf(currentId)).find(WidgetInfo.class).size() == 0){
                widgetInfo.save();
            }

            appWidgetManager.updateAppWidget(currentId,remoteViews);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        OnDispatchListener listener = null;


        if(Action.UPDATA.equals(intent.getAction())){
            //扫描一遍数据库
            List<WidgetInfo> list = LitePal.findAll(WidgetInfo.class);
            for (WidgetInfo widgetInfo:list) {
                //更新
                Widget widget = WidgetsManager.getInstance().get(widgetInfo.getAppWidgetId());
                if(widget != null){
                    RemoteViews remoteViews = widget.build(context);
//                    RemoteViews remoteViews = widget.build(context, widgetInfo.getAppWidgetId());
                    updateRemoteUi(context,widgetInfo.getAppWidgetId(),remoteViews);
                }
            }

        }

        //如果是点击了空视图，就打开添加View的activity
        if(Action.NO_VIEW_CLICK_EVENT.equals(intent.getAction())){
            ComponentName componentName = new ComponentName(PKG,PKG+".ui.MainActivity");
            Intent openAddView = new Intent();
            openAddView.putExtra(APP_WIDGET_ID,intent.getIntExtra(APP_WIDGET_ID,-1));
            openAddView.setComponent(componentName);
            openAddView.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(openAddView);
            return;
        }


        if(listener == null){
           int widgetId = intent.getIntExtra(APP_WIDGET_ID,-1);
           if(widgetId != -1){
               try {
                   listener =  WidgetsManager.getInstance().getDispatchListener(widgetId);
                   //将接收器接收到的点击事件分发给WidgetsManager里的监听器，由其完成事件解析，交给实际接收监听器
                   listener.onDispatch(context,intent);
               }catch (Exception e){
                   e.printStackTrace();
               }
           }else{
               //Toast.makeText(context, "系统内部错误", Toast.LENGTH_SHORT).show();
           }
        }
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(TAG,"onEnabled: first add");
    }



//    /**
//     * 更新方法
//     * */
//    public static void updateRemoteUi(RemoteViews root, Context context){
//        ComponentName componentName = new ComponentName(context,WidgetProvider.class);
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//        appWidgetManager.updateAppWidget(componentName, root);
//    }


    /**
     * 不推荐
     * */
    public static void updateRemoteUi(Context context,int appWidgetId){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(appWidgetId,WidgetsManager.getInstance().get(appWidgetId).build(WidgetsManager.getInstance().getContext()));
    }


    /**
     * 优先使用此方法更新，节省内存消耗
     * */
    public static void updateRemoteUi(Context context,int appWidgetId,RemoteViews remoteViews){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
    }


    /**
     * 初次add或大小改变
     * */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Log.d(TAG,"onAppWidgetOptionsChanged:"+appWidgetId);
    }


    /**
     * 被删除时
     * */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        for (int i:
             appWidgetIds) {
            Log.d(TAG,"onDeleted:"+i);
            LitePal.deleteAll(WidgetInfo.class,"appWidgetId=?",String.valueOf(i));
        }
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(TAG,"onDisabled:last removed");
    }
}
