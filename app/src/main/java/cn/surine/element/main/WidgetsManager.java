package cn.surine.element.main;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.surine.element.bean.WidgetInfo;
import cn.surine.element.core.OnDispatchListener;
import cn.surine.element.core.Widget;

/**
 * Intro：Widget组管理器
 * @author sunliwei
 * @date 2019-08-10 17:37
 */
public class WidgetsManager {
    private Context context;

    static class Instance{
        public static final WidgetsManager instance = new WidgetsManager();
    }

    /**
     * singleton
     * */
    public static WidgetsManager getInstance(){
        return Instance.instance;
    }


    /**
     * get widget
     * */
    public Widget get(int appId){
        List<WidgetInfo> list = LitePal.where("appWidgetId = ?", String.valueOf(appId)).find(WidgetInfo.class);
        if(list.size() > 0 && list.get(0) != null){
            try {
                Class<Widget> widgetClass = (Class<Widget>) Class.forName(list.get(0).getCustomWidgetId());
                Widget widget = widgetClass.newInstance();
                widget.setWidgetJson(list.get(0).getJson());
                widget.setAppWidgetId(list.get(0).getAppWidgetId());
                return widget;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 管理器事件分发
     * @param widgetId 目标widgetId
     * */
    public OnDispatchListener getDispatchListener(final int widgetId){
        return new OnDispatchListener(){
            @Override
            public void onDispatch(Context context, Intent intent) {
                if(Action.IMAGE_VIEW_CLICK.equals(intent.getAction())){
                    String realAction = intent.getStringExtra(Action.IMAGE_VIEW_CLICK);
                    intent.setAction(realAction);
                    //TODO:优化点-添加监听器又重新构建widget
                    Widget widget = get(widgetId);
                    widget.build(context);
                    if(widget != null){
                        widget.getOnElementClickListener().onClick(context,intent);
                    }
                }
            }
        };
    }


    public void setContext(Context context){
        this.context = context;
    }

    public Context getContext(){
        return context;
    }
}
