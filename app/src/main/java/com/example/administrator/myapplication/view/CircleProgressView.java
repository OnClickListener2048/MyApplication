package com.example.administrator.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myapplication.R;

/**
 * this file was created by wangxin in 2018/9/11 9:35
 */
public class CircleProgressView extends View {

    private String mTitle;
    private String mNum;
    private String mUnit;
    private float mTitleTextSize;
    private float mNumTextSize;
    private float mUnitTextSize;
    private int mTitleTextColor;
    private int mNumTextColor;
    private int mUnitTextColor;
    private float mBackCircleWidth;
    private float mOuterCircleWidth;
    private int mBackCircleColor;
    private int mOuterCircleColor;
    private float mEndCircleWidth;
    private int mEndCircleColor;
    private Paint mBackCirclePaint;
    private Paint mOuterCirclePaint;
    private Paint mEndCirclePaint;
    private Paint mEndCirclePaint2;
    private Paint mTitlePaint;
    private Paint mNumPaint;
    private Paint mUnitPaint;
    private int mWidth;
    private int mHeight;
    private float currentPercent = 0.3f;
    public CircleProgressView(Context context) {
        super(context);
        init(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CircleProgressView);
            mTitle = typedArray.getString(R.styleable.CircleProgressView_progress_title);
            mNum = typedArray.getString(R.styleable.CircleProgressView_progress_num);
            mUnit = typedArray.getString(R.styleable.CircleProgressView_progress_unit);

            mTitleTextSize = typedArray.getDimension(R.styleable.CircleProgressView_titleTextSize, getResources().getDimension(R.dimen.px_to_dip_24));
            mNumTextSize = typedArray.getDimension(R.styleable.CircleProgressView_numTextSize, getResources().getDimension(R.dimen.px_to_dip_48));
            mUnitTextSize = typedArray.getDimension(R.styleable.CircleProgressView_unitTextSize, getResources().getDimension(R.dimen.px_to_dip_24));

            mTitleTextColor = typedArray.getColor(R.styleable.CircleProgressView_titleTextColor, Color.parseColor("#656d78"));
            mNumTextColor = typedArray.getColor(R.styleable.CircleProgressView_numTextColor, Color.parseColor("#4fc1e9"));
            mUnitTextColor = typedArray.getColor(R.styleable.CircleProgressView_unitTextColor, Color.parseColor("#4fc1e9"));

            mBackCircleWidth = typedArray.getDimension(R.styleable.CircleProgressView_backCircleWidth, getResources().getDimension(R.dimen.px_to_dip_12));
            mOuterCircleWidth = typedArray.getDimension(R.styleable.CircleProgressView_outerCircleWidth, getResources().getDimension(R.dimen.px_to_dip_20));

            mBackCircleColor = typedArray.getColor(R.styleable.CircleProgressView_backCircleColor, Color.parseColor("#e6e9ed"));
            mOuterCircleColor = typedArray.getColor(R.styleable.CircleProgressView_outerCircleColor, Color.parseColor("#4fc1e9"));

            mEndCircleWidth = typedArray.getDimension(R.styleable.CircleProgressView_endCircleWidth, getResources().getDimension(R.dimen.px_to_dip_24));
            mEndCircleColor = typedArray.getColor(R.styleable.CircleProgressView_endCircleColor, Color.parseColor("#4fc1e9"));

            typedArray.recycle();

            mBackCirclePaint = new Paint();
            mBackCirclePaint.setAntiAlias(true);
            mBackCirclePaint.setStrokeWidth(mBackCircleWidth);
            mBackCirclePaint.setColor(mBackCircleColor);
            mBackCirclePaint.setStyle(Paint.Style.STROKE);

            mOuterCirclePaint = new Paint();
            mOuterCirclePaint.setAntiAlias(true);
            mOuterCirclePaint.setStrokeWidth(mOuterCircleWidth);
            mOuterCirclePaint.setColor(mOuterCircleColor);
            mOuterCirclePaint.setStyle(Paint.Style.STROKE);
            mOuterCirclePaint.setStrokeCap(Paint.Cap.ROUND);

            mEndCirclePaint = new Paint();
            mEndCirclePaint.setAntiAlias(true);
            mEndCirclePaint.setColor(mEndCircleColor);
            mEndCirclePaint.setStyle(Paint.Style.FILL);


            mEndCirclePaint2 = new Paint();
            mEndCirclePaint2.setAntiAlias(true);
            mEndCirclePaint2.setColor(Color.WHITE);
            mEndCirclePaint2.setStyle(Paint.Style.FILL);

            mTitlePaint = new Paint();
            mTitlePaint.setAntiAlias(true);
            mTitlePaint.setColor(mTitleTextColor);
            mTitlePaint.setTextSize(mTitleTextSize);

            mNumPaint = new Paint();
            mNumPaint.setAntiAlias(true);
            mNumPaint.setColor(mNumTextColor);
            mNumPaint.setTextSize(mNumTextSize);

            mUnitPaint = new Paint();
            mUnitPaint.setAntiAlias(true);
            mUnitPaint.setColor(mUnitTextColor);
            mUnitPaint.setTextSize(mUnitTextSize);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (mWidth>mHeight) {
            mWidth = mHeight;
        }

        if (mHeight>mWidth) {
            mHeight = mWidth;
        }

        setMeasuredDimension(mWidth,mHeight);
    }

    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = mWidth / 2;
        int centerY = mHeight / 2;

        float radius = centerX - mOuterCircleWidth + (mOuterCircleWidth - mBackCircleWidth) / 2;

        canvas.drawCircle(centerX,centerY,radius,mBackCirclePaint);

        RectF rectF = new RectF(mOuterCircleWidth / 2 + mBackCircleWidth / 2, mOuterCircleWidth / 2 + mBackCircleWidth / 2, mWidth - mOuterCircleWidth / 2 - mBackCircleWidth / 2, mHeight - mOuterCircleWidth / 2 - mBackCircleWidth / 2);

        canvas.drawArc(rectF, -90, 360 * currentPercent, false, mOuterCirclePaint);

        Rect textRect = new Rect();

        mTitlePaint.getTextBounds(mTitle, 0, mTitle.length(), textRect);

        canvas.drawText(mTitle, mWidth / 2 - textRect.width() / 2, mHeight / 4 + textRect.height() / 2, mTitlePaint);


        mNumPaint.getTextBounds(mNum, 0, mNum.length(), textRect);
        canvas.drawText(mNum, mWidth / 2 - textRect.width() / 2, mHeight / 2 + textRect.height() / 2, mNumPaint);

        mUnitPaint.getTextBounds(mUnit, 0, mUnit.length(), textRect);
        canvas.drawText(mUnit, mWidth / 2 - textRect.width() / 2, mHeight * 2 / 3 + textRect.height() / 2, mUnitPaint);


        //我这里规定进度在0~100%的时候才会画终点小圆，可以自由改动
        if (currentPercent < 1 && currentPercent > 0) {
            canvas.drawCircle(centerX + rectF.width() / 2 * (float) Math.sin(360 * currentPercent * Math.PI / 180),
                    centerY - rectF.width() / 2 * (float) Math.cos(360 * currentPercent * Math.PI / 180), mEndCircleWidth / 2, mEndCirclePaint);


            canvas.drawCircle(centerX + rectF.width() / 2 * (float) Math.sin(360 * currentPercent * Math.PI / 180),
                    centerY - rectF.width() / 2 * (float) Math.cos(360 * currentPercent * Math.PI / 180), mEndCircleWidth / 4, mEndCirclePaint2);

        }

    }

    public void setValue(String value) {
        this.mNum = value;
        invalidate();
    }

    public void  setCurrentPercent(float currentPercent) {
        this.currentPercent = currentPercent;
        invalidate();
    }
}
