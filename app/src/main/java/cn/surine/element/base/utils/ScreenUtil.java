package cn.surine.element.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import cn.surine.element.base.controller.App;

/**
 * Intro：屏幕尺寸工具类
 * @author sunliwei
 * @date 2019-08-18 12:05
 */
public class ScreenUtil {

    static WindowManager wm = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
    static DisplayMetrics dm = new DisplayMetrics();


    /**
     * 获取屏幕宽度
     * */
    public static int getWidth(){
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        float density = dm.density;
        return  (int) (width / density);
    }

    /**
     * 获取屏幕高度
     * */
    public static int getHeight(){
        wm.getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        float density = dm.density;
        return (int) (height / density);
    }

    /**
     * dp to px
     * */
    public static float dp2px(int dp){
        wm.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        return (dp * density);
    }


    /**
     * px to dp
     * */
    public static float px2dp(int px){
        wm.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        return (px/density);
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue
     * @return
     */
    public static int px2sp(float pxValue) {
        final float fontScale = App.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = App.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * 尺寸映射
     * @param real 当前真实尺寸
     * @param value json文件值的尺寸
     * */
    public static float map(int real,int value){
        return (float) value/(float) real;
    }
}
