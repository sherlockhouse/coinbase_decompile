package com.github.mikephil.charting.data;

import android.graphics.Paint.Style;
import java.util.List;

public class CandleDataSet extends LineScatterCandleRadarDataSet<CandleEntry> {
    private float mBodySpace;
    protected int mDecreasingColor;
    protected Style mDecreasingPaintStyle;
    protected int mIncreasingColor;
    protected Style mIncreasingPaintStyle;
    protected int mShadowColor;
    private boolean mShadowColorSameAsCandle;
    private float mShadowWidth;

    protected void calcMinMax(int start, int end) {
        if (this.mYVals.size() != 0) {
            int endValue;
            List<CandleEntry> entries = this.mYVals;
            if (end == 0 || end >= this.mYVals.size()) {
                endValue = this.mYVals.size() - 1;
            } else {
                endValue = end;
            }
            this.mLastStart = start;
            this.mLastEnd = endValue;
            this.mYMin = Float.MAX_VALUE;
            this.mYMax = -3.4028235E38f;
            for (int i = start; i <= endValue; i++) {
                CandleEntry e = (CandleEntry) entries.get(i);
                if (e.getLow() < this.mYMin) {
                    this.mYMin = e.getLow();
                }
                if (e.getHigh() > this.mYMax) {
                    this.mYMax = e.getHigh();
                }
            }
        }
    }

    public float getBodySpace() {
        return this.mBodySpace;
    }

    public float getShadowWidth() {
        return this.mShadowWidth;
    }

    public int getDecreasingColor() {
        return this.mDecreasingColor;
    }

    public int getIncreasingColor() {
        return this.mIncreasingColor;
    }

    public Style getDecreasingPaintStyle() {
        return this.mDecreasingPaintStyle;
    }

    public Style getIncreasingPaintStyle() {
        return this.mIncreasingPaintStyle;
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }

    public boolean getShadowColorSameAsCandle() {
        return this.mShadowColorSameAsCandle;
    }
}
