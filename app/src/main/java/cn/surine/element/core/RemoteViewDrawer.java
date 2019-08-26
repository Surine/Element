package cn.surine.element.core;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.widget.FrameLayout;
import android.widget.RemoteViews;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.base.utils.Logs;
import cn.surine.element.base.utils.ScreenUtil;
import cn.surine.element.base.utils.Toasts;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.bean.product.ProductView;
import cn.surine.element.core.widget_service.KeepService;

import static cn.surine.element.base.utils.ScreenUtil.dp2px;

/**
 * Intro：plan2 实现方法
 * @author sunliwei
 * @date 2019-08-17 18:47
 */
public class RemoteViewDrawer {
    private static int CURRENT_INDEX = 0;
    private Context context;
    private int width;
    private int height;
    private ProductView view;
    private int appWidgetId;


    public static RemoteViewDrawer getInstance(Context context, int width, int height, ProductView vg, int appWidgetId) {
        return new RemoteViewDrawer(width,height,context, vg, appWidgetId);
    }


    public RemoteViewDrawer(int width, int height, Context context, ProductView view, int appWidgetId) {
        this.width = width;
        this.height = height;
        this.context = context;
        this.view = view;
        this.appWidgetId = appWidgetId;
    }


    /**
     * 创建远程视图
     * */
    public RemoteViews getRemote() {
        //创建
        RemoteViews root = WidgetUi.getRoot();
        root.removeAllViews(R.id.widget_root);

        String color = view.getBackgroundColor();
        if(color != null){
            root.setInt(R.id.widget_root,"setBackgroundColor",Color.parseColor(color));
        }
        RemoteViews content = WidgetUi.getImage();
        content.setImageViewBitmap(R.id.widget_imageview,createBitmap(view.getWidth() == -1 ? width : view.getWidth(),view.getHeight() == -1 ? height : view.getHeight()));
        WidgetUi.addToRoot(root,content);
        setClickListener(root);
        return root;
    }

    /**
     * 绘制主体布局
     * */
    private Bitmap createBitmap(int width,int height) {
        List<ProductElement> elements = view.getViews();
        Rect rect = new Rect(0,0, (int)dp2px(width), (int)dp2px(height));
        Bitmap bitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        for (ProductElement pe :elements) {
            if (TypeMap.IMAGE.equals(pe.getType())){
                Painter.paintImage(bitmap,pe);
            }else if(TypeMap.SHAPE.equals(pe.getType())){
                Painter.paintShape(bitmap,pe);
            }else if(TypeMap.TEXT.equals(pe.getType())){
                Painter.paintText(bitmap,pe);
            }
        }
        return bitmap;
    }


    /**
     * 设置监听器事件
     */
    private void setClickListener(RemoteViews root){
        List<ProductElement> elements = view.getViews();
        for (ProductElement pe :elements) {
            String action = pe.getAction();
            if(action == null){
                continue;
            }
            setPendingIntent(action,root,pe.getWidth(),pe.getHeight(),pe.getX_offset(),pe.getY_offset());
        }
    }





    /**
     * 设置某个组件的点击事件
     */
    public void setPendingIntent(String action, RemoteViews root, int viewWidth, int viewHeight, int x_offset, int y_offset) {

        //解析事件
        String[] strs = action.split("\\|");
        if (strs.length < 3) {
            //TODO:上报
            Toasts.toto("当前组件监听事件配置有误，问题已上报，请等待解决。");
            return;
        }

        //将widget点击事件转移到服务中处理
        Intent intent = new Intent(context, KeepService.class);
        intent.putExtra(BaseConfig.APP_WIDGET_ID, appWidgetId);
        intent.putExtra(BaseConfig.ACTION_NAME, strs[0]);
        intent.putExtra(BaseConfig.ACTION_SUB_EVENT, strs[1]);
        intent.putExtra(BaseConfig.ACTION_EVENT_DATA, strs[2]);
        PendingIntent pendingIntent = PendingIntent.getService(context, CURRENT_INDEX++, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews touch = WidgetUi.getTouch();
        touch.setOnClickPendingIntent(R.id.touchRect, pendingIntent);
        touch.setViewPadding(R.id.touchPadding, (int)dp2px(x_offset), (int) dp2px(y_offset),(int)dp2px(width - x_offset - viewWidth), (int)dp2px(height - y_offset - viewHeight));
        WidgetUi.addToRoot(root,touch);
    }
}
