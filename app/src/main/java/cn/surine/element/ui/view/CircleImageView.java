package cn.surine.element.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Intro：a circle image view 圆形图片控件
 * @author sunliwei
 * @date 2019-08-06 18:18
 */
public class CircleImageView extends AppCompatImageView {

    private Paint mPaint;
    private Matrix mMatrix;
    private int mViewWdth;
    private int mViewHeight;

    private int mCircleRadius;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;

    public CircleImageView(Context context) {
        this(context,null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mMatrix = new Matrix();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWdth = View.MeasureSpec.getSize(widthMeasureSpec);
        mViewHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        mCircleRadius = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(mCircleRadius, mCircleRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(getDrawable() == null){
            super.onDraw(canvas);
            return;
        }
        bitmapSetting();
        setUpShader();
        canvas.drawCircle(mCircleRadius / 2, mCircleRadius / 2, mCircleRadius / 2, mPaint);
    }


    private void bitmapSetting(){
        mBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        int offset;
        if (mBitmap.getWidth() < mBitmap.getHeight()) {
            offset = (mBitmap.getHeight() - mBitmap.getWidth()) / 2;
            mBitmap = Bitmap.createBitmap(mBitmap, 0, offset, mBitmap.getWidth(), mBitmap.getWidth() + offset);
        } else if (mBitmap.getWidth() > mBitmap.getHeight()) {
            offset = (mBitmap.getWidth() - mBitmap.getHeight()) / 2;
            mBitmap = Bitmap.createBitmap(mBitmap, offset, 0, mBitmap.getHeight() + offset, mBitmap.getHeight());
        }
    }


    private void setUpShader() {
        if (getDrawable() == null) {
            return;
        }
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;

        mMatrix.postScale(scale, scale);

        int bSize = Math.min(mBitmap.getWidth(), mBitmap.getHeight());
        scale = mCircleRadius * 1.0f / bSize;
        mMatrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);
    }


    /**
     * set bitmap resource
     * 设置bitmap资源
     * @param mBitmap
     * */
    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        postInvalidate();
    }


}
