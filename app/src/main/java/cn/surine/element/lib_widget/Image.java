package cn.surine.element.lib_widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;

/**
 * Intro：图片
 * @author sunliwei
 * @date 2019-08-11 23:33
 */
public class Image {
    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;


    static class Instance{
        public static final Image image = new Image();
    }


    private Image(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.TRANSPARENT);
    }


    /**
     * singleton
     * */
    public static Image getInstance(){
        return Instance.image;
    }


    /**
     * 绘制image
     * @param bitmap 目标资源
     * @param width 绘制宽度
     * @param height 绘制高度
     * */
    public Bitmap show(Bitmap bitmap,int width,int height){
        Rect rect = new Rect(0,0,width,height);
        Bitmap newBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        Bitmap temp = bitmap;

        //针对绘制bitmap添加抗锯齿
        PaintFlagsDrawFilter pfd= new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
        //对Bitmap进行滤波处理
        paint.setFilterBitmap(true);
        //设置抗锯齿
        paint.setAntiAlias(true);
        canvas.setDrawFilter(pfd);
        canvas.drawBitmap(temp, null, rect, paint);
        return newBitmap;
    }
}
