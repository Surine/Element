package cn.surine.element.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import cn.surine.element.R;
import cn.surine.element.base.controller.App;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.lib_widget.Image;
import cn.surine.element.lib_widget.Shape;
import cn.surine.element.lib_widget.Text;

import static cn.surine.element.base.utils.ScreenUtil.dp2px;

/**
 * Intro：绘画者
 * @author sunliwei
 * @date 2019-08-17 19:25
 */
public class Painter {

    /**
     * 绘制一个image
     * */
    public static void paintImage(Bitmap bitmap, ProductElement pe){
        Image image = new Image(bitmap,pe);
        image.draw();
    }


    /**
     * 绘制一个shape
     * */
    public static void paintShape(Bitmap bitmap, ProductElement pe) {
        new Shape(bitmap,pe).draw();
    }

    /**
     * 绘制一个text
     * */
    public static void paintText(Bitmap bitmap, ProductElement pe) {
        new Text(bitmap,pe).draw();
    }


    public static void paintClickHelper(Bitmap bitmap,ProductElement pe){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(App.getContext().getResources().getColor(R.color.event_show));
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRoundRect(new RectF(dp2px(pe.getX_offset()),dp2px(pe.getY_offset()),dp2px(pe.getWidth() + pe.getX_offset()),dp2px(pe.getHeight() + pe.getY_offset())),0,0,paint);
    }

}
