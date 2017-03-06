package org.nghiatrx.libs.letterview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by nghia on 3/5/17.
 */

public class SquareLetterView extends BaseLetterVIew {

    private float x1, y1, x2, y2;
    private float xBitmap;
    private float yBitmap;

    public SquareLetterView(Context context) {
        super(context);
    }

    public SquareLetterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareLetterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x1 = 0;
        y1 = 0;
        x2 = w;
        y2 = h;

    }

    @Override
    protected void draw(Canvas canvas, boolean selected) {
        if (selected) {
            canvas.drawRect(x1, y1, x2, y2, mPaintSelected);
        } else {
            canvas.drawRect(x1, y1, x2, y2, mPaint);
        }
    }
}
