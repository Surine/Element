package cn.surine.element.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.bean.StatusInfo;
import cn.surine.element.core.OnElementClickListener;
import cn.surine.element.core.Widget;
import cn.surine.element.lib_widget.Rectangle;
import cn.surine.element.lib_widget.base.Padding;

import static cn.surine.element.core.WidgetProvider.updateRemoteUi;
import static cn.surine.element.core.WidgetUi.addResource;
import static cn.surine.element.core.WidgetUi.addToRoot;
import static cn.surine.element.core.WidgetUi.addToRow;
import static cn.surine.element.core.WidgetUi.addToStack;
import static cn.surine.element.core.WidgetUi.getImage;
import static cn.surine.element.core.WidgetUi.getRoot;
import static cn.surine.element.core.WidgetUi.getRow;
import static cn.surine.element.core.WidgetUi.getStack;
import static cn.surine.element.lib_event.Setting.toggleWiFi;

/**
 * Intro：a setting bar Widget
 *
 * layout tree:
 *
 * Root{
 *   Stack{
 *      ImageView,
 *      Row{
 *          ImageView,
 *          ImageView,
 *          ImageView,
 *      }
 *   }
 * }
 *
 * @author sunliwei
 * @date 2019-08-10 15:57
 */
@SuppressLint("ParcelCreator")
public class SettingWidget extends Widget {

    /**
     * 修改或者添加变量，需要修改序列化部分
     * */

    private RemoteViews root;
    private RemoteViews stack;
    private RemoteViews row;
    private RemoteViews cornerBackImage;

    private RemoteViews wifi;
    private RemoteViews bluetooth;
    private RemoteViews data;

    private String json;


    public SettingWidget(){
        super();
    }


    @Override
    public List<StatusInfo> getStatusInfo() {
        return null;
    }


    @Override
    public RemoteViews build(final Context context){
        root = getRoot();
        stack = getStack();
        row = getRow();

        //背景ImageView：白色，圆角
        cornerBackImage = getImage(context,Rectangle.getInstance().show(620,180,Color.WHITE,180),null,appWidgetId);

        Padding padding = new Padding(70,65,60,0);
        //功能图标
        wifi = getImage(context,R.drawable.ic_action_wifi,padding, Action.Setting.CLICK_WIFI,appWidgetId);
        bluetooth = getImage(context,R.drawable.ic_action_bluetooth,padding, Action.Setting.CLICK_BLUE_TOOTH,appWidgetId);
        data = getImage(context,R.drawable.ic_action_data,padding, Action.Setting.CLICK_DATA,appWidgetId);

        addToRow(row,wifi);
        addToRow(row,bluetooth);
        addToRow(row,data);

        addToStack(stack,cornerBackImage);
        addToStack(stack,row);

        addToRoot(root,stack);
        return root;
    }

    @Override
    public void fromJson(String json) {
        this.json = json;
    }


    @Override
    public String toJson() {
        return null;
    }



    @Override
    public OnElementClickListener getOnElementClickListener() {
        return new OnElementClickListener() {
            @Override
            public void onClick(Context context,Intent intent) {
                switch (intent.getAction()){
                    case Action.Setting.CLICK_WIFI:
                        Toast.makeText(context, "wifi controller", Toast.LENGTH_SHORT).show();
                        if(toggleWiFi(context)){
                            addResource(wifi,R.drawable.ic_action_wifi_open);
                        }else{
                            addResource(wifi,R.drawable.ic_action_wifi);
                        }
//                        updateRemoteUi(context,intent.getIntExtra(BaseConfig.APP_WIDGET_ID,-1),root);
                        updateRemoteUi(context,appWidgetId,root);
                        break;
                    case Action.Setting.CLICK_BLUE_TOOTH:
                        Toast.makeText(context, "btl", Toast.LENGTH_SHORT).show();
                        break;
                    case Action.Setting.CLICK_DATA:
                        Toast.makeText(context, "data", Toast.LENGTH_SHORT).show();
                        break;
                    default:break;
                }
            }
        };
    }

}
