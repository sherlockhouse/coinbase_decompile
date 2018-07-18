package com.github.mikephil.charting.data;

import java.util.List;

public class BubbleDataSet extends BarLineScatterCandleBubbleDataSet<BubbleEntry> {
    private float mHighlightCircleWidth;
    protected float mMaxSize;
    protected float mXMax;
    protected float mXMin;

    public float getHighlightCircleWidth() {
        return this.mHighlightCircleWidth;
    }

    protected void calcMinMax(int start, int end) {
        if (this.mYVals.size() != 0) {
            int endValue;
            List<BubbleEntry> entries = getYVals();
            if (end == 0) {
                endValue = this.mYVals.size() - 1;
            } else {
                endValue = end;
            }
            this.mLastStart = start;
            this.mLastEnd = endValue;
            this.mYMin = yMin((BubbleEntry) entries.get(start));
            this.mYMax = yMax((BubbleEntry) entries.get(start));
            for (int i = start; i <= endValue; i++) {
                BubbleEntry entry = (BubbleEntry) entries.get(i);
                float ymin = yMin(entry);
                float ymax = yMax(entry);
                if (ymin < this.mYMin) {
                    this.mYMin = ymin;
                }
                if (ymax > this.mYMax) {
                    this.mYMax = ymax;
                }
                float xmin = xMin(entry);
                float xmax = xMax(entry);
                if (xmin < this.mXMin) {
                    this.mXMin = xmin;
                }
                if (xmax > this.mXMax) {
                    this.mXMax = xmax;
                }
                float size = largestSize(entry);
                if (size > this.mMaxSize) {
                    this.mMaxSize = size;
                }
            }
        }
    }

    public float getXMax() {
        return this.mXMax;
    }

    public float getXMin() {
        return this.mXMin;
    }

    public float getMaxSize() {
        return this.mMaxSize;
    }

    private float yMin(BubbleEntry entry) {
        return entry.getVal();
    }

    private float yMax(BubbleEntry entry) {
        return entry.getVal();
    }

    private float xMin(BubbleEntry entry) {
        return (float) entry.getXIndex();
    }

    private float xMax(BubbleEntry entry) {
        return (float) entry.getXIndex();
    }

    private float largestSize(BubbleEntry entry) {
        return entry.getSize();
    }
}
