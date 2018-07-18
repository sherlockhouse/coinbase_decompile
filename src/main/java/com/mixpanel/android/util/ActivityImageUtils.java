package com.mixpanel.android.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;

public class ActivityImageUtils {
    public static Bitmap getScaledScreenshot(Activity activity, int scaleWidth, int scaleHeight, boolean relativeScaleIfTrue) {
        View rootView = activity.findViewById(16908290).getRootView();
        boolean originalCacheState = rootView.isDrawingCacheEnabled();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);
        Bitmap original = rootView.getDrawingCache();
        Bitmap scaled = null;
        if (original != null && original.getWidth() > 0 && original.getHeight() > 0) {
            if (relativeScaleIfTrue) {
                scaleWidth = original.getWidth() / scaleWidth;
                scaleHeight = original.getHeight() / scaleHeight;
            }
            if (scaleWidth > 0 && scaleHeight > 0) {
                scaled = Bitmap.createScaledBitmap(original, scaleWidth, scaleHeight, false);
            }
        }
        if (!originalCacheState) {
            rootView.setDrawingCacheEnabled(false);
        }
        return scaled;
    }

    public static int getHighlightColorFromBackground(Activity activity) {
        int incolor = -16777216;
        Bitmap screenshot1px = getScaledScreenshot(activity, 1, 1, false);
        if (screenshot1px != null) {
            incolor = screenshot1px.getPixel(0, 0);
        }
        return getHighlightColor(incolor);
    }

    public static int getHighlightColorFromBitmap(Bitmap bitmap) {
        int incolor = -16777216;
        if (bitmap != null) {
            incolor = Bitmap.createScaledBitmap(bitmap, 1, 1, false).getPixel(0, 0);
        }
        return getHighlightColor(incolor);
    }

    public static int getHighlightColor(int sampleColor) {
        float[] hsvBackground = new float[3];
        Color.colorToHSV(sampleColor, hsvBackground);
        hsvBackground[2] = 0.3f;
        return Color.HSVToColor(242, hsvBackground);
    }
}
