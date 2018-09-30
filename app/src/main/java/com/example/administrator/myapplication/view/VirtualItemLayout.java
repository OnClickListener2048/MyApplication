package com.example.administrator.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
        mArchPaint.setColor(Color.BLACK);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setColor(Color.WHITE);

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.VirtualItemLayout);
        topImage = typedArray.getDrawable(typedArray.getIndex(R.styleable.VirtualItemLayout_topImage));

        if (topImage != null) {
            int intrinsicHeight = topImage.getIntrinsicHeight();
            int intrinsicWidth = topImage.getIntrinsicWidth();
            Log.d(TAG, "init: intrinsicHeight" + intrinsicHeight);
            Log.d(TAG, "init: intrinsicWidth" + intrinsicWidth);
            mRadius = intrinsicHeight > intrinsicWidth ? intrinsicHeight : intrinsicWidth;
            mRadius = mRadius + 20;

        }

        typedArray.recycle();

        RectF oval = new RectF();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return new LayoutParams(lp);
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        Log.d(TAG, "onSizeChanged: " + w);
        Log.d(TAG, "onSizeChanged: " + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        Log.d(TAG, "onDraw: getX()" + getX());
        Log.d(TAG, "onDraw: getTop()" + getTop());



//        canvas.drawArc();

        canvas.drawRect(104,120, ScreenUtils.getScreenWidth()-106,height-120,mRectPaint);

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int size = MeasureSpec.getSize(heightMeasureSpec);
//        int height  = size + mRadius / 2 - getTop() / 2;
//        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),height+300);
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


}
