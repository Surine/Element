package cn.surine.element.core;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.widget.RemoteViews;

import java.io.Serializable;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.lib_widget.base.Padding;
import cn.surine.element.main.Action;

import static cn.surine.element.base.BaseConfig.PKG;

/**
 * Intro：Widget Ui 管理类
 *
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

    public static RemoteViews getRow() {
        return get(R.layout.widget_row);
    }

    public static RemoteViews getCol() {
        return get(R.layout.widget_column);
    }

    public static RemoteViews getStack() {
        return get(R.layout.widget_stack);
    }

    /**
     * 获取一个ImageView
     */
    public static RemoteViews getImage(Context context, Bitmap bitmap, String action,int widgetId) {
        return getImage(context, bitmap, new Padding(0, 0, 0, 0), action,widgetId);
    }

    /**
     * 获取一个ImageView
     */
    public static RemoteViews getImage(Context context, Bitmap bitmap, Padding padding, String action,int widgetId) {
        RemoteViews imageView = get(R.layout.widget_imageview);

        if (action != null) {
            Intent intent = new Intent(Action.IMAGE_VIEW_CLICK);
            intent.putExtra(Action.IMAGE_VIEW_CLICK, action);
            intent.putExtra(BaseConfig.APP_WIDGET_ID,widgetId);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE++, intent, 0);
            imageView.setOnClickPendingIntent(R.id.widget_imageview, pendingIntent);
        }

        if (bitmap == null) {
            return imageView;
        }
        imageView.setImageViewBitmap(R.id.widget_imageview, bitmap);
        imageView.setViewPadding(R.id.widget_imageview, padding.getLeft(), padding.getTop(), padding.getTop(), padding.getBottom());
        return imageView;
    }

    /**
     * 获取一个ImageView
     */
    public static RemoteViews getImage(Context context, int drawable, String action,int widgetId) {
        return getImage(context, drawable, new Padding(0, 0, 0, 0), action,widgetId);
    }


    /**
     * 获取一个ImageView
     */
    public static RemoteViews getImage(Context context, int drawable, Padding padding, String action,int widgetId) {
        RemoteViews imageView = get(R.layout.widget_imageview);

        if (action != null) {
            Intent intent = new Intent(Action.IMAGE_VIEW_CLICK);
            intent.putExtra(Action.IMAGE_VIEW_CLICK, action);
            intent.putExtra(BaseConfig.APP_WIDGET_ID,widgetId);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE++, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            imageView.setOnClickPendingIntent(R.id.widget_imageview, pendingIntent);
        }

        if (drawable == 0) {
            return imageView;
        }
        imageView.setImageViewResource(R.id.widget_imageview, drawable);
        imageView.setViewPadding(R.id.widget_imageview, padding.getLeft(), padding.getRight(), padding.getTop(), padding.getBottom());
        return imageView;
    }


    public static RemoteViews getImage(int drawable, Padding padding, PendingIntent pendingIntent) {
        RemoteViews imageView = get(R.layout.widget_imageview);
        imageView.setOnClickPendingIntent(R.id.widget_imageview, pendingIntent);
        if (drawable == 0) {
            return imageView;
        }
        imageView.setImageViewResource(R.id.widget_imageview, drawable);
        imageView.setViewPadding(R.id.widget_imageview, padding.getLeft(), padding.getRight(), padding.getTop(), padding.getBottom());
        return imageView;
    }


    /**
     * 向根布局添加一个remoteView
     */
    public static void addToRoot(RemoteViews root, RemoteViews remoteViews) {
        root.addView(R.id.widget_root, remoteViews);
    }


    /**
     * 向row里添加一个remoteView
     */
    public static void addToRow(RemoteViews remoteViews, RemoteViews beAdder) {
        remoteViews.addView(R.id.widget_row, beAdder);
    }


    /**
     * 向col里添加一个remoteView
     */
    public static void addToCol(RemoteViews remoteViews, RemoteViews beAdder) {
        remoteViews.addView(R.id.widget_column, beAdder);
    }


    /**
     * 向stack里添加一个remoteView
     */
    public static void addToStack(RemoteViews remoteViews, RemoteViews beAdder) {
        remoteViews.addView(R.id.widget_stack, beAdder);
    }


    /**
     * 设置imageView的图片
     */
    public static void addResource(RemoteViews remoteViews, int res) {
        remoteViews.setImageViewResource(R.id.widget_imageview, res);
    }

}
