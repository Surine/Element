package cn.surine.element.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;

import cn.surine.element.R;


/**
 * Intro：一个可以支持设置shape属性的button/textView
 * @author sunliwei
 * @date 2019-08-08 14:34
 */
@SuppressLint("SupportAnnotationUsage")
public class ColorfulButton extends AppCompatButton {

    Context context;

    /**矩形*/
    public static final int RECTANGLE = 0;
    /**椭圆*/
    public static final int OVAL = 1;
    /**线*/
    public static final int LINE = 2;
    /**环*/
    public static final int RING = 3;

    final int NORMAL_COLOR = Color.parseColor("#FC3B40");

    /**shape形状*/
    int shape;

    /**默认，按压，聚焦 color*/
    int color;
    int pressColor;
    int focusColor;
    /**水波纹颜色，仅支持api>23*/
    int rippleColor;


    /**corner radius*/
    /**优先级比cornerArray高，因此cornerRadius会覆盖cornerArray的值*/
    @Px
    float cornerRadius;

    /**corner array*/
    @Px
    float[] cornerArray;

    /**stroke width*/
    @Px
    float strokeWidth;

    /**stroke color*/
    @Px
    int strokeColor;

    /**dash gap*/
    @Px
    float dashGap;

    /**dash width*/
    @Px
    float dashWidth;


    public ColorfulButton(Context context) {
        this(context,null);
    }

    public ColorfulButton(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ColorfulButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ColorfulButton);
        shape = typedArray.getInteger(R.styleable.ColorfulButton_shape,RECTANGLE);
        color = typedArray.getColor(R.styleable.ColorfulButton_color, NORMAL_COLOR);
        pressColor = typedArray.getColor(R.styleable.ColorfulButton_pressColor, NORMAL_COLOR);
        focusColor = typedArray.getColor(R.styleable.ColorfulButton_focusColor, NORMAL_COLOR);
        rippleColor = typedArray.getColor(R.styleable.ColorfulButton_rippleColor, Color.parseColor("#00000000"));
        cornerRadius = typedArray.getDimension(R.styleable.ColorfulButton_cornerRadius, 8);
        typedArray.recycle();
        init();
    }


    private void init() {

        //如果没有设置padding，默认要加一个padding
        @SuppressLint({"NewApi", "LocalSuppress"})
        boolean _17highCondition =
                getPaddingTop() == 0 &&
                        getPaddingBottom() == 0 &&
                        getPaddingStart() == 0 &&
                        getPaddingEnd() == 0;


        boolean _17lowCondition = getPaddingTop() == 0 && getPaddingBottom() == 0
                || getPaddingLeft() == 0 && getPaddingRight() == 0;


        if(Build.VERSION.SDK_INT >= 17){
            if(_17highCondition){
                setPadding(42,32,42,32);
            }
        }else {
            if(_17lowCondition){
                setPadding(42,32,42,32);
            }
        }
        //update
        update();
    }


    /**
     * get the shape
     * 获取当前配置的形状
     * @return shape
     * */
    public int getShape() {
        return shape;
    }


    /**
     * set the shape
     * 设置当前形状
     * @param shape
     * */
    public ColorfulButton setShape(int shape) {
        if(shape < RECTANGLE || shape > RING){
            throw new IllegalArgumentException("shape enum is not in the range");
        }
        this.shape = shape;
        return this;
    }


    /**
     * get the solid color
     * 获取背景颜色
     * */
    public int getColor() {
        return color;
    }

    /**
     * set the solid color
     * 设置背景颜色
     * @param color
     * */
    public ColorfulButton setColor(int color) {
        this.color = color;
        return this;
    }


    /**
     * 获取圆角半径
     * */
    public float getCornerRadius() {
        return cornerRadius;
    }

    /**
     * 设置圆角半径
     * @param cornerRadius 半径 px
     * */
    public ColorfulButton setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        return this;
    }

    /**
     * get the corner array
     * 获取圆角数组
     * */
    public float[] getCornerArray() {
        return cornerArray;
    }

    /**
     * set the corner array
     * 设置圆角数组,用于高级圆角
     * 接受8个值，分别为左上，右上，左下，右下4个端点圆心x,y坐标
     * @see GradientDrawable#setCornerRadii(float[])
     * @param cornerArray
     * */
    public ColorfulButton setCornerArray(float[] cornerArray) {
        this.cornerArray = cornerArray;
        return this;
    }


    public float getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * 设置边框宽度
     * @param strokeWidth px
     * */
    public ColorfulButton setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public float getStrokeColor() {
        return strokeColor;
    }

    /**
     * 设置边框颜色
     * @param strokeColor
     * */
    public ColorfulButton setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public float getDashGap() {
        return dashGap;
    }

    /**
     * 设置虚线间隔
     * @param dashGap px
     * */
    public ColorfulButton setDashGap(float dashGap) {
        this.dashGap = dashGap;
        return this;
    }

    public float getDashWidth() {
        return dashWidth;
    }

    /**
     * 设置虚线主体长度
     * @param dashWidth px
     * */
    public ColorfulButton setDashWidth(float dashWidth) {
        this.dashWidth = dashWidth;
        return this;
    }


    public int getPressColor() {
        return pressColor;
    }

    /**
     * 设置按压时候的颜色
     * @param pressColor
     * */
    public ColorfulButton setPressColor(int pressColor) {
        this.pressColor = pressColor;
        return this;
    }

    public int getFocusColor() {
        return focusColor;
    }


    /**
     * 设置聚焦颜色
     * @param focusColor
     * */
    public ColorfulButton setFocusColor(int focusColor) {
        this.focusColor = focusColor;
        return this;
    }

    /**
     * 获取水波纹颜色
     * */
    public int getRippleColor() {
        return rippleColor;
    }

    /**
     * 设置水波纹颜色 (required api23)
     * @param rippleColor
     * */
    public ColorfulButton setRippleColor(int rippleColor) {
        this.rippleColor = rippleColor;
        return this;
    }

    /**
     * 刷新当前UI
     * 调用set方法后，必须通过update生效
     * */
    public void update(){

        if(Build.VERSION.SDK_INT >= 16){
            setBackground(addStateDrawable());
        }else{
            setBackgroundDrawable(addStateDrawable());
        }

        //仅在23上支持设置前景色，仅在21上支持水波纹
        if(Build.VERSION.SDK_INT >= 23){
            setForeground(initRipple());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private RippleDrawable initRipple() {

        //添加默认状态颜色为水波纹颜色，避免点击闪烁
        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{}
        };

        int[] stateColorList = new int[]{
                rippleColor,
                rippleColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);

        //控制水波纹边界
        float[] outRadius = new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius};
        RoundRectShape roundRectShape = new RoundRectShape(outRadius, null, null);
        ShapeDrawable maskDrawable = new ShapeDrawable();
        maskDrawable.setShape(roundRectShape);

        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, null, maskDrawable);
        return rippleDrawable;
    }



    //构建selector
    private StateListDrawable addStateDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();

        Drawable normal = copy(color);
        Drawable pressed = copy(pressColor);
        Drawable focus = copy(focusColor);

        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focus);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        stateListDrawable.addState(new int[]{android.R.attr.state_focused}, focus);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normal);
        stateListDrawable.addState(new int[]{}, normal);
        return stateListDrawable;
    }


    //解决对象引用copy引起的问题
    private Drawable copy(int copyColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(copyColor);
        drawable.setShape(shape);
        drawable.setCornerRadii(cornerArray);
        drawable.setCornerRadius(cornerRadius);
        drawable.setStroke((int)strokeWidth,strokeColor,dashWidth,dashGap);
        return drawable;
    }

}

