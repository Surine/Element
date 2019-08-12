package cn.surine.element.base.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Intro：图片处理类
 * @author sunliwei
 * @date 2019-08-11 23:29
 */
public class ImageUtil {

    /**
     * 图片缩放
     * TODO:暂时有问题
     * */
    public static Bitmap scaleMatrix(Bitmap bitmap, int width, int height){
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float scaleW = width/w;
        float scaleH = height/h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleW, scaleH);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }
}
