package cn.surine.element.core;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.RemoteViews;

import org.litepal.LitePal;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.base.utils.CurdManager;
import cn.surine.element.base.utils.GsonHelper;
import cn.surine.element.base.utils.Logs;
import cn.surine.element.base.utils.Toasts;
import cn.surine.element.bean.WidgetInfo;
import cn.surine.element.bean.product.Product;
import cn.surine.element.lib_event.Action;
import cn.surine.element.ui.MainActivity;

import static cn.surine.element.base.BaseConfig.ACTION_SUB_EVENT;
import static cn.surine.element.base.BaseConfig.PKG;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-14 10:32
 */
public class WidgetProvider extends AppWidgetProvider {
    private static int CURRENT = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        Logs.d("onreceive");
        Logs.d("收到信息"+intent.getAction());

        Toasts.toto("收到消息"+intent.getAction()+intent.getStringExtra(ACTION_SUB_EVENT));
        //接受广播式更新
        if(Action.UPDATE.equals(intent.getAction())
                || Action.MY_UPDATE.equals(intent.getAction())
        ||Action.UPDATE_OPTIONS.equals(intent.getAction())){
        AppWidgetManager am = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = am.getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
            for (int i = 0; i < appWidgetIds.length; i++) {
                updateInit(context, am, appWidgetIds[i]);
            }
        }

        if(Action.IMAGE_CLICK.equals(intent.getAction())){
            EventManager.getInstance().getOnDispatchListener().onDispatch(context,intent);
        }
    }

    @Override
    public void onEnabled(Context context) {
        Logs.d("onenable");
        super.onEnabled(context);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Logs.d("onAppWidgetOptionsChanged");

        int a = newOptions.getInt("appWidgetMinWidth");
        int b = newOptions.getInt("appWidgetMaxHeight");
        int c = newOptions.getInt("appWidgetMaxWidth");
        int d = newOptions.getInt("appWidgetMinHeight");
        Logs.d(a+"/"+b+"/"+c+"/"+d);

        List<WidgetInfo> list = LitePal.where("appWidgetId = ?", String.valueOf(appWidgetId)).find(WidgetInfo.class);
        WidgetInfo currentInfo;
        if(list.size() > 0){
            currentInfo = list.get(0);
            currentInfo.setWidth(a);
            currentInfo.setHeight(b);
            currentInfo.save();
        }

      //  updateInit(context, appWidgetManager, appWidgetId);

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Logs.d("onDeleted");
        for (int appId:appWidgetIds) {
            if(LitePal.delete(WidgetInfo.class,appId) >= 1){
                Toasts.toto("删除成功");
            }else {
                Toasts.toto("删除失败，请尝试清除app数据");
            }
        }
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Logs.d("onupdate");
        for (int i = 0; i < appWidgetIds.length; i++) {
            updateInit(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    public static void updateInit(Context context, AppWidgetManager appWidgetManager, int appWidgetId){

        //注册初始化时的事件
        RemoteViews remoteViews = new RemoteViews(PKG,R.layout.widget_init);
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra(BaseConfig.APP_WIDGET_ID,appWidgetId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,CURRENT++,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_no_view,pendingIntent);


        //保存小部件
        WidgetInfo widgetInfo = new WidgetInfo(appWidgetId,"",null,0,0);
        if(CurdManager.getWidgetById(appWidgetId).size() == 0){
            widgetInfo.save();
        }


        List<WidgetInfo> list = CurdManager.getWidgetById(appWidgetId);
        if(list != null && list.size() > 0){
            if(list.get(0).getWidth() == 0 || list.get(0).getHeight() == 0){
                return;
            }
            if(list.get(0).getJson() != null && !list.get(0).getJson().isEmpty()){
                remoteViews = RemoteViewDrawer.getInstance(context,list.get(0).getWidth(),list.get(0).getHeight(),GsonHelper.abt.getInstance().parseData(list.get(0).getJson(), Product.class).getRootView(),list.get(0).getAppWidgetId()).getRemote();
            }
        }

        try {
            appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
        }catch (Exception e){
            Toasts.toto("内存异常");
            e.printStackTrace();
        }
    }
}
