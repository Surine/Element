package cn.surine.element.lib_widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Intro：实现矩形组件
 * @author sunliwei
 * @date 2019-08-10 15:01
 */
public class Rectangle {

    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;


    static class Instance{
        public static final Rectangle sizedBox = new Rectangle();
    }


    private Rectangle(){
       paint = new Paint(Paint.ANTI_ALIAS_FLAG);
       paint.setStyle(Paint.Style.FILL);
       paint.setColor(Color.TRANSPARENT);
    }


    /**
     * singleton
     * */
    public static Rectangle getInstance(){
        return Instance.sizedBox;
    }


    /**
     * 绘制矩形,透明款可用作Margin（SizedBox）
     * */
    public Bitmap show(int width,int height,int color,int radius){
        paint.setColor(color);
        bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawRoundRect(new RectF(0,0,width,height),radius,radius,paint);
        return bitmap;
    }
}
