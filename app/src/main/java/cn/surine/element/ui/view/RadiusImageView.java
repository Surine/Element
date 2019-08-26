package cn.surine.element.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import cn.surine.element.R;


/**
 * Intro：a image view with Rounded corners
 * 实现了圆角矩形的ImageView
 * @author sunliwei
 * @date 2019-08-06 16:26
 */
public class RadiusImageView extends AppCompatImageView {

    //normal radius 默认值20
    private final int ROUND_RADIUS  = 20;
    protected int mRoundRectRadius;

    //corner position
    private boolean mLeftTopRound;
    private boolean mLeftBottomRound;
    private boolean mRightTopRound;
    private boolean mRightBottomRound;

    private Paint mPaint;
    private Matrix mMatrix;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private RectF rectF;
    private int mViewWidth;
    private int mViewHeight;


    public RadiusImageView(Context context) {
        this(context,null);
    }

    public RadiusImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadiusImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RadiusImageView);
        mRoundRectRadius = (int) ta.getDimension(R.styleable.RadiusImageView_RoundRectRadius, ROUND_RADIUS);
        mLeftTopRound = ta.getBoolean(R.styleable.RadiusImageView_LeftTopRound, true);
        mLeftBottomRound = ta.getBoolean(R.styleable.RadiusImageView_LeftBottomRound, true);
        mRightTopRound = ta.getBoolean(R.styleable.RadiusImageView_RightTopRound, true);
        mRightBottomRound = ta.getBoolean(R.styleable.RadiusImageView_RightBottomRound, true);
        ta.recycle();
    }

    //init the paint
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mMatrix = new Matrix();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //get the view width and height
        mViewWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        mViewHeight = View.MeasureSpec.getSize(heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //if cant get the image's drawable : do nothing and return
        if (getDrawable() == null) {
            super.onDraw(canvas);
            return;
        }

        mBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        setUpShader();
        canvas.drawRoundRect(rectF, mRoundRectRadius, mRoundRectRadius, mPaint);

        //change the corner rect
        if (!mLeftTopRound) {
            canvas.drawRect(0, 0, mRoundRectRadius, mRoundRectRadius, mPaint);
        }
        if (!mLeftBottomRound) {
            canvas.drawRect(0, mViewHeight - mRoundRectRadius, mRoundRectRadius, mViewHeight, mPaint);
        }
        if (!mRightTopRound) {
            canvas.drawRect(mViewWidth - mRoundRectRadius, 0, mViewWidth, mRoundRectRadius, mPaint);
        }
        if (!mRightBottomRound) {
            canvas.drawRect(mViewWidth - mRoundRectRadius, mViewHeight - mRoundRectRadius, mViewWidth, mViewHeight, mPaint);
        }
    }

    private void setUpShader() {
        if (getDrawable() == null) {
            return;
        }
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //we should scale the drawable so that we can
        // draw the corner on the layout border rather
        // than the drawable border
        float wScale = 1.0f;
        float hScale = 1.0f;
        mMatrix.postScale(wScale, hScale);
        wScale = getWidth() * 1.0F / mBitmap.getWidth();
        hScale = getHeight() * 1.0f / mBitmap.getHeight();
        mMatrix.setScale(wScale, hScale);
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);
        rectF = new RectF(new Rect(0, 0, getWidth(), getHeight()));
    }


    /**
     * update the bitmap resource
     * 设置Bitmap
     * @param mBitmap Resource
     * */
    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        postInvalidate();
    }
}
