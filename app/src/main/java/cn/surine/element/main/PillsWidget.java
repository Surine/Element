package cn.surine.element.main;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.bean.StatusInfo;
import cn.surine.element.core.OnElementClickListener;
import cn.surine.element.core.Widget;
import cn.surine.element.lib_widget.Circle;
import cn.surine.element.lib_widget.Image;
import cn.surine.element.lib_widget.base.Padding;

import static cn.surine.element.core.WidgetUi.addToRoot;
import static cn.surine.element.core.WidgetUi.addToStack;
import static cn.surine.element.core.WidgetUi.getImage;
import static cn.surine.element.core.WidgetUi.getRoot;
import static cn.surine.element.core.WidgetUi.getStack;

/**
 * Intro：小药丸
 * @author sunliwei
 * @date 2019-08-11 22:57
 */
@SuppressLint("ParcelCreator")
public class PillsWidget extends Widget {
    private RemoteViews root;
    private RemoteViews stack;
    private RemoteViews background;
    private RemoteViews icon;
    private String json;


    public PillsWidget() {
        super();
    }


    @Override
    public List<StatusInfo> getStatusInfo() {
        return null;
    }

    @Override
    public OnElementClickListener getOnElementClickListener() {
        return new OnElementClickListener() {
            @Override
            public void onClick(Context context, Intent i) {
                if (i.getAction().contains("we_chat")) {
                    Toast.makeText(context, "打开微信", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    context.startActivity(intent);
                }
            }
        };
    }

    @Override
    public RemoteViews build(Context context) {
        root = getRoot();
        stack = getStack();

        background = getImage(context, Circle.getInstance().show(100, Color.WHITE), null, appWidgetId);
        icon = getImage(context, Image.getInstance().show(BitmapFactory.decodeResource(context.getResources(), R.drawable.weixin), 120, 100), new Padding(40, 0, 50, 0), Action.App.OPEN_APP + "we_chat", appWidgetId);

        addToStack(stack, background);
        addToStack(stack, icon);
        addToRoot(root, stack);
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
}
