package cn.surine.element.lib_widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Intro：圆形
 * @author sunliwei
 * @date 2019-08-11 23:00
 */
public class Circle {
    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;


    static class Instance{
        public static final Circle circle = new Circle();
    }


    private Circle(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.TRANSPARENT);
    }


    /**
     * singleton
     * */
    public static Circle getInstance(){
        return Instance.circle;
    }


    /**
     * 绘制Circle
     * */
    public Bitmap show(int radius,int color){
        paint.setColor(color);
        bitmap = Bitmap.createBitmap(radius * 2,radius * 2, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawCircle(radius,radius,radius,paint);
        return bitmap;
    }
}
