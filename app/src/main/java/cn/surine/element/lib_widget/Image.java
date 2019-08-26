package cn.surine.element.lib_widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.surine.element.R;
import cn.surine.element.base.controller.App;
import cn.surine.element.base.utils.ScreenUtil;
import cn.surine.element.bean.product.ProductAttrs;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.bean.product.ProductProperties;

import static cn.surine.element.bean.product.ProductAttrs.Image.SRC;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-14 20:16
 */
public class Image{
    private Map<String,String> map = new HashMap<>();
    private Context context;
    private Bitmap bitmap;
    private ProductElement pe;
    private Canvas canvas;
    private float width;
    private float height;
    private float x_offset;
    private float y_offset;
    private List<ProductProperties> properties;


    public Image(Map<String, String> map) {
        this.map = map;
        this.context = App.getContext();
    }

    public Image(Bitmap bitmap, ProductElement pe) {
        this.bitmap = bitmap;
        this.pe = pe;
        this.context = App.getContext();
        init();
    }

    //初始化画板内容
    private void init() {
        canvas = new Canvas(bitmap);
        width = ScreenUtil.dp2px(pe.getWidth());
        height = ScreenUtil.dp2px(pe.getHeight());
        x_offset = ScreenUtil.dp2px(pe.getX_offset());
        y_offset = ScreenUtil.dp2px(pe.getY_offset());
        properties = pe.getProperties();
        if(properties != null){
            for (ProductProperties p:properties) {
                if(p == null){
                    continue;
                }
                map.put(p.getPropertyName(),p.getPropertyValue());
            }
        }
    }


    public Bitmap draw(){
        Bitmap pic = map.containsKey(SRC) ? BitmapFactory.decodeResource(context.getResources(),R.drawable.chilun) : BitmapFactory.decodeResource(context.getResources(),R.drawable.tutu);
        int corner = map.containsKey(ProductAttrs.Image.CORNERS) ? Integer.parseInt(map.get(ProductAttrs.Image.CORNERS)) : 0;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Rect rect = new Rect((int)x_offset,(int)y_offset,(int)width,(int)height);

        //针对绘制bitmap添加抗锯齿
        PaintFlagsDrawFilter pfd= new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
        //对Bitmap进行滤波处理
        paint.setFilterBitmap(true);
        //设置抗锯齿
        paint.setAntiAlias(true);
        canvas.setDrawFilter(pfd);

        //绘制圆角
        RectF rectF = new RectF(x_offset,y_offset,x_offset+ width,y_offset+height);
        canvas.drawRoundRect(rectF,corner,corner,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(pic, null, rect, paint);
        return bitmap;
    }

}
