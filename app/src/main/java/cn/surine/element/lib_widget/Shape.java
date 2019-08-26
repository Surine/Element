package cn.surine.element.lib_widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.surine.element.base.controller.App;
import cn.surine.element.base.utils.ScreenUtil;
import cn.surine.element.bean.product.ProductAttrs;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.bean.product.ProductProperties;

import static cn.surine.element.bean.product.ProductAttrs.Shape.BACKGROUND_COLOR;

/**
 * Intro：绘制形状
 * @author sunliwei
 * @date 2019-08-14 20:16
 */
public class Shape{

    //矩形
    private final int RECTANGLE = 1;
    //圆形
    private final int CIRCLE = 2;


    private ProductElement pe;

    private Map<String,String> map = new HashMap<>();
    private Context context;
    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;
    private float width;
    private float height;
    private float x_offset;
    private float y_offset;
    private List<ProductProperties> properties;

    public Shape(Map<String, String> map) {
        this.map = map;
        this.context = App.getContext();
    }

    public Shape(Bitmap bitmap, ProductElement pe) {
        this.bitmap = bitmap;
        this.pe = pe;
        this.context = App.getContext();
        init();
    }


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
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int shapeType = map.containsKey(ProductAttrs.Shape.SHAPE_TYPE) ? Integer.parseInt(map.get(ProductAttrs.Shape.SHAPE_TYPE)) : 1;
        int corner = map.containsKey(ProductAttrs.Shape.CORNERS) ? Integer.parseInt(map.get(ProductAttrs.Shape.CORNERS)) : 0;
        int color = map.containsKey(BACKGROUND_COLOR) ? Color.parseColor(map.get(BACKGROUND_COLOR)) : Color.TRANSPARENT;
        if(shapeType == RECTANGLE){
            paint.setColor(color);
//            bitmap = Bitmap.createBitmap((int)width,(int)height, Bitmap.Config.ARGB_8888);
            canvas.drawRoundRect(new RectF(x_offset,y_offset,width + x_offset,height + y_offset),corner,corner,paint);
            return bitmap;
        }


        return null;
    }
}
