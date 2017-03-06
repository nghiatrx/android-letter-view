package org.nghiatrx.libs.letterview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by nghia on 3/5/17.
 */

public class CircleLetterView extends BaseLetterVIew {

    private float xCircle, yCircle;

    private float mRadius;

    public CircleLetterView(Context context) {
        super(context);
    }

    private boolean isGetMirrorBitmap = false;

    public CircleLetterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleLetterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        xCircle = w / 2.0f;
        yCircle = h / 2.0f;

        mRadius = w / 2.0f;

    }

    @Override
    protected void draw(Canvas canvas, boolean selected) {
        if (selected) {
            if (mPaintSelected == null) {
                mPaintSelected = new Paint(Paint.ANTI_ALIAS_FLAG);
            }
            canvas.drawCircle(xCircle, yCircle, mRadius, mPaintSelected);
        } else {
            if (mPaint == null) {
                mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            }
            canvas.drawCircle(xCircle, yCircle, mRadius, mPaint);
        }
    }


}
