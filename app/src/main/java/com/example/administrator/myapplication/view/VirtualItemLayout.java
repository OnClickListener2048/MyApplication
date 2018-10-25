package com.example.administrator.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.example.administrator.myapplication.R;

/**
 * this file was created by wangxin in 2018/9/30 11:41
 */
public class VirtualItemLayout extends LinearLayout {
    private static final String TAG = "VirtualItemLayout";
    private Paint mArchPaint;
    private int width;
    private int height;
    private Drawable topImage;
    private int mRadius;
    private Paint mRectPaint;
    private RectF mOval;
    private int rectLeft = SizeUtils.dp2px(35);
    private int rectTop = SizeUtils.dp2px(45);
    private int rectRight = SizeUtils.dp2px(35);
    private int rectBottom = SizeUtils.dp2px(45);
    private Paint mBitmapPaint;
    private int mTempHeight;
    private RectF mRectF;

    public VirtualItemLayout(Context context) {
        super(context);
        init(context, null);
    }

    public VirtualItemLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public VirtualItemLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        mArchPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArchPaint.setColor(Color.WHITE);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setColor(Color.WHITE);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(SizeUtils.dp2px(1));

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.VirtualItemLayout);
        topImage = typedArray.getDrawable(typedArray.getIndex(R.styleable.VirtualItemLayout_topImage));

        if (topImage != null) {
            int intrinsicHeight = topImage.getIntrinsicHeight();
            int intrinsicWidth = topImage.getIntrinsicWidth();
            LogUtils.d("init: intrinsicHeight" + intrinsicHeight);
            LogUtils.d("init: intrinsicWidth" + intrinsicWidth);
            mRadius = intrinsicHeight > intrinsicWidth ? intrinsicHeight : intrinsicWidth;
            mRadius = mRadius + SizeUtils.dp2px(5);

        }

        typedArray.recycle();

        mOval = new RectF();
        mOval.left = ScreenUtils.getScreenWidth() / 2 - mRadius;
        mOval.right = ScreenUtils.getScreenWidth() / 2 + mRadius;
        mOval.top = rectTop - mRadius;
        mOval.bottom = rectTop + mRadius;

        mRectF = new RectF();
        mRectF.left = rectLeft;
        mRectF.top = rectTop;
        mRectF.right = ScreenUtils.getScreenWidth() - rectRight;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        LogUtils.d(w);
        LogUtils.d(h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawArc(mOval, 0, -180, false, mArchPaint);

//        canvas.drawRect(rectLeft,rectTop, ScreenUtils.getScreenWidth()-rectRight,height-rectBottom,mRectPaint);

//        canvas.drawRoundRect(rectLeft,rectTop, ScreenUtils.getScreenWidth()-rectRight,height-rectBottom,3,3,mRectPaint);
        mRectF.bottom = height - rectBottom;
        canvas.drawRoundRect(mRectF, SizeUtils.dp2px(1), SizeUtils.dp2px(1), mRectPaint);

        canvas.drawBitmap(ImageUtils.drawable2Bitmap(topImage), ScreenUtils.getScreenWidth() / 2 - topImage.getIntrinsicWidth() / 2
                , rectTop - mRadius + SizeUtils.dp2px(5), mBitmapPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        LogUtils.d(MeasureSpec.getMode(widthMeasureSpec));
        LogUtils.d(MeasureSpec.getSize(widthMeasureSpec));
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int childWidth = view.getMeasuredWidth();//主要getMeasuredWidth函数的值必须调用了measureChild才存在值
            int childHeight = view.getMeasuredHeight();
            if (view instanceof RelativeLayout) {
                view.layout(rectLeft
                        , rectTop
                        , ScreenUtils.getScreenWidth() - rectRight
                        , rectTop + childHeight);
                mTempHeight = rectTop + childHeight;
            }

            if (view instanceof RecyclerView) {
                view.layout(rectLeft
                        , mTempHeight
                        , ScreenUtils.getScreenWidth() - rectRight
                        , height - rectBottom);
            }
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(), attrs);
    }


    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MyLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    public static class MyLayoutParams extends LinearLayout.LayoutParams {
        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public MyLayoutParams(int width, int height) {
            super(width, height);
        }

        public MyLayoutParams(LayoutParams lp) {
            super(lp);
        }
    }


}
