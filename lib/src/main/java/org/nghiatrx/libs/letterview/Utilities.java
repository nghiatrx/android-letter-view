package org.nghiatrx.libs.letterview;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

/**
 * Created by nghia on 3/6/17.
 */

public class Utilities {
    public static Bitmap getMirrorBitmap(Bitmap bitmap) {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
        bitmap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return bitmap;
    }
}
