package com.github.mikephil.charting.data;

import android.graphics.Color;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public abstract class LineRadarDataSet<T extends Entry> extends LineScatterCandleRadarDataSet<T> {
    private boolean mDrawFilled = false;
    private int mFillAlpha = 85;
    private int mFillColor = Color.rgb(140, 234, 255);
    private float mLineWidth = 2.5f;

    public LineRadarDataSet(List<T> yVals, String label) {
        super(yVals, label);
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    public void setFillColor(int color) {
        this.mFillColor = color;
    }

    public int getFillAlpha() {
        return this.mFillAlpha;
    }

    public void setFillAlpha(int alpha) {
        this.mFillAlpha = alpha;
    }

    public void setLineWidth(float width) {
        if (width < 0.2f) {
            width = 0.2f;
        }
        if (width > 10.0f) {
            width = 10.0f;
        }
        this.mLineWidth = Utils.convertDpToPixel(width);
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public void setDrawFilled(boolean filled) {
        this.mDrawFilled = filled;
    }

    public boolean isDrawFilledEnabled() {
        return this.mDrawFilled;
    }
}
