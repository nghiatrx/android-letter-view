package org.nghiatrx.libs.letterview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by nghia on 3/5/17.
 */

public abstract class BaseLetterVIew extends View {

    public final static int ROTATE_ANIMATION = 1;

    public final static int NONE_ANIMATION = 2;

    protected int mDefaultSize = 60;

    protected String mLetter;

    protected Context mContext;

    protected @ColorInt int mPaintColor, mSelectedColor, mTextColor;

    protected boolean isSetSize = false;

    protected int xText, yText;

    public int getColor() {
        return mPaintColor;
    }

    private int mAnimationType = ROTATE_ANIMATION;

    public BaseLetterVIew setAnimationType(int type) {
        mAnimationType = type;
        return this;
    }

    public int getAnimationType() {
        return mAnimationType;
    }


    public BaseLetterVIew setColor(@ColorInt int color) {
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        mPaint.setColor(color);
        invalidate();
        return this;
    }

    public int getSelectedColor() {
        return mSelectedColor;
    }

    public BaseLetterVIew setSelectedColor(@ColorInt int selectedColor) {
        if (mPaintSelected == null) {
            mPaintSelected = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        this.mSelectedColor = selectedColor;
        mPaintSelected.setColor(selectedColor);
        invalidate();
        return this;
    }

    public int getTextColor() {
        return mTextColor;

    }

    public BaseLetterVIew setTextColor(@ColorInt int textColor) {
        this.mTextColor = textColor;
        mTextPaint.setColor(mTextColor);
        invalidate();
        return this;
    }

    public Bitmap getSelectedIndicatorBitmap() {
        return mSelectedIndicatorBitmap;
    }

    public BaseLetterVIew setSelectedIndicatorBitmap(Bitmap selectedIndicatorBitmap) {
        this.mSelectedIndicatorBitmap = selectedIndicatorBitmap;
        invalidate();
        return this;
    }

    protected Bitmap mSelectedIndicatorBitmap, mSelectedIndicatorMirrorBitmap;

    protected Paint mPaint, mPaintSelected;

    protected Paint mTextPaint;

    protected boolean mIsSelected = false;

    protected final int animateDuration = 340; //ms

    public BaseLetterVIew(Context context) {
        super(context);
        init(context);
    }

    public BaseLetterVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseLetterVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        mContext = context;
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mLetter = "";

        mPaintSelected = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSelected.setColor(ContextCompat.getColor(mContext, R.color.selected));

    }

    public BaseLetterVIew setLetter(char letter) {
        mLetter = String.valueOf(letter);
        mLetter = mLetter.toUpperCase();
        initPaintColor(mLetter);
        invalidate();
        return this;

    }

    public char getLetter() {
        return mLetter.charAt(0);
    }

    private void initPaintColor(String letter) {
        char c = letter.charAt(0);
        if (mPaint == null) {
            int [] defaultColors = getResources().getIntArray(R.array.colors);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaintColor = defaultColors[((int) c) % defaultColors.length];
            mPaint.setColor(mPaintColor);
        }
    }


    //http://stackoverflow.com/questions/13273838/onmeasure-wrap-content-how-do-i-know-the-size-to-wrap
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (!isSetSize) {
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            int width;
            int height;

            //Measure Width
            if (widthMode == MeasureSpec.EXACTLY) {
                //Must be this size
                width = widthSize;
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                width = Math.min(mDefaultSize, widthSize);
            } else {
                //Be whatever you want
                width = mDefaultSize;
            }

            //Measure Height
            if (heightMode == MeasureSpec.EXACTLY) {
                //Must be this size
                height = heightSize;
            } else if (heightMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                height = Math.min(mDefaultSize, heightSize);
            } else {
                //Be whatever you want
                height = mDefaultSize;
            }

            mDefaultSize = Math.min(width, height); // it must be square
        } else {
            isSetSize = false;
        }

        setMeasuredDimension(mDefaultSize, mDefaultSize);
    }

    private float xBitmap, yBitmap;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTextPaint.setTextSize(h * 0.48f);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        Rect bounds = new Rect();
        if (mLetter != null && mLetter.length() == 1) {
            mTextPaint.getTextBounds(mLetter, 0, 1, bounds);
            xText = w / 2;
            yText = h / 2 + (bounds.height() / 2);
        }

        if (mSelectedIndicatorBitmap == null) {
            mSelectedIndicatorBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_check_white_48dp);
        }

        mSelectedIndicatorBitmap = Bitmap.createScaledBitmap(mSelectedIndicatorBitmap, (int)(w / 1.4f), (int)(w / 1.4f), true);

        xBitmap = (w - mSelectedIndicatorBitmap.getWidth()) >> 1; // same as (...) / 2
        yBitmap = (h - mSelectedIndicatorBitmap.getWidth()) >> 1; // same as (...) / 2

    }

    public void setSize(int size) {
        mDefaultSize = size;
        isSetSize = true;
        invalidate();
        requestLayout();
    }

    public int getSize() {
        return mDefaultSize;
    }

    public void runSelectedAnimation() {

        switch (mAnimationType){
            case ROTATE_ANIMATION:
                rotate();
                break;
            case NONE_ANIMATION:
                noneAnim();
                break;
        }


    }

    protected void noneAnim() {
        mIsSelected = ! mIsSelected;
        invalidate();
    }

    protected void rotate() {
        if (mSelectedIndicatorMirrorBitmap == null) {
            if (mSelectedIndicatorBitmap != null) {
                mSelectedIndicatorMirrorBitmap = Utilities.getMirrorBitmap(mSelectedIndicatorBitmap);
            }
        }
        float from, to;

        if (!mIsSelected) {
            from = 0;
            to = 180;
        } else {
            from = 180;
            to = 0;
        }

        ObjectAnimator animation = ObjectAnimator.ofFloat(this, "rotationY", from, to);
        animation.setDuration(animateDuration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());

        animation.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIsSelected = ! mIsSelected;
                invalidate();
            }
        }, animateDuration/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaintSelected == null) {
            mPaintSelected = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        draw(canvas, mIsSelected);
        if (mIsSelected) {
            if (mAnimationType == NONE_ANIMATION) canvas.drawBitmap(mSelectedIndicatorBitmap, xBitmap, yBitmap, null);
            else if (mAnimationType == ROTATE_ANIMATION) canvas.drawBitmap(mSelectedIndicatorMirrorBitmap, xBitmap, yBitmap, null);
        } else {
            canvas.drawText(mLetter, xText, yText, mTextPaint);
        }
    }

    protected abstract void draw(Canvas canvas, boolean selected);

}
