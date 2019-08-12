package cn.surine.element.base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Intro：
 * @author sunliwei
 * @date 2019-08-10 15:42
 */
public class Ui {
    /**
     * convert dip to px
     *
     * @param dip The dip
     * @return px
     */
    public static int dip2px(int dip,Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }

    /**
     * @param resources The resources.
     * @param px        The pixel value.
     * @return A DIP value.
     */
    public static int px2dip(Resources resources, int px) {
        final float scale = resources.getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
