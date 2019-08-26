package cn.surine.element.lib_widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.surine.element.base.controller.App;
import cn.surine.element.base.utils.ScreenUtil;
import cn.surine.element.bean.product.ProductAttrs;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.bean.product.ProductProperties;
import cn.surine.element.core.ElementTextManager;

import static cn.surine.element.base.utils.ScreenUtil.dp2px;
import static cn.surine.element.base.utils.ScreenUtil.px2dp;
import static cn.surine.element.base.utils.ScreenUtil.sp2px;

/**
 * Intro：绘制文字
 * @author sunliwei
 * @date 2019-08-14 20:21
 */
public class Text{

    //align
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final String CENTER = "CENTER";

    private ProductElement pe;
    private Bitmap bitmap;

    private Map<String,String> map = new HashMap<>();
    private Context context;
    private Canvas canvas;
    private float width;
    private float height;
    private float x_offset;
    private float y_offset;
    private List<ProductProperties> properties;

    public Text(Map<String, String> map) {
        this.map = map;
        this.context = App.getContext();
    }

    public Text(Bitmap bitmap, ProductElement pe) {
        this.bitmap = bitmap;
        this.pe = pe;
        this.context = App.getContext();
        init();
    }


    private void init() {
        canvas = new Canvas(bitmap);
        width = dp2px(pe.getWidth());
        height = dp2px(pe.getHeight());
        x_offset = dp2px(pe.getX_offset());
        y_offset = dp2px(pe.getY_offset());
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
        String text = map.containsKey(ProductAttrs.Text.TEXT) ? map.get(ProductAttrs.Text.TEXT) : "";
        String realText = ElementTextManager.abt.getInstance().parseString(text);
        int textSize = map.containsKey(ProductAttrs.Text.TEXT_SIZE) ? Integer.parseInt(map.get(ProductAttrs.Text.TEXT_SIZE)) : 40;
        int textColor = map.containsKey(ProductAttrs.Text.TEXT_COLOR) ? Color.parseColor(map.get(ProductAttrs.Text.TEXT_COLOR)) : Color.BLACK;
        Paint.Style style = map.containsKey(ProductAttrs.Text.PAINT_STYLE) ? ("FILL".equals(map.get(ProductAttrs.Text.PAINT_STYLE)) ? Paint.Style.FILL : Paint.Style.STROKE) : Paint.Style.FILL;
        Paint.Align align = map.containsKey(ProductAttrs.Text.ALIGN) ? getAlign(map.get(ProductAttrs.Text.ALIGN)) : Paint.Align.CENTER;

        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setStrokeWidth (2);
        paint.setAntiAlias(true);
        //设置文字绘制风格
        paint.setStyle(style);
        //设置文字对齐方式
        paint.setTextAlign(align);
        //设置文字大小
        paint.setTextSize(sp2px(textSize));

        Rect bounds = new Rect();
        paint.getTextBounds(text,0,text.length(),bounds);
        //样式设置
        paint.setFakeBoldText(true);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float y = height / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;

        int x = 0;
        if(align.equals(Paint.Align.LEFT)){
            x = 0;
        }else if(align.equals(Paint.Align.CENTER)){
            x = (int) (width / 2);
        }else{
            x = (int) width;
        }

        canvas.translate(x_offset,y_offset);
        canvas.drawText(realText,x,y,paint);
        return bitmap;
    }



    private Paint.Align getAlign(String s) {
        switch (s){
            case LEFT: return Paint.Align.LEFT;
            case RIGHT: return Paint.Align.RIGHT;
            case CENTER:
                default:
                return Paint.Align.CENTER;
        }
    }
}
