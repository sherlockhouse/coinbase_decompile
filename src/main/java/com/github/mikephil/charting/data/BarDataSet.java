package com.github.mikephil.charting.data;

public class BarDataSet extends BarLineScatterCandleBubbleDataSet<BarEntry> {
    private int mBarShadowColor;
    private float mBarSpace;
    private int mHighLightAlpha;
    private String[] mStackLabels;
    private int mStackSize;

    protected void calcMinMax(int start, int end) {
        int yValCount = this.mYVals.size();
        if (yValCount != 0) {
            int endValue;
            if (end == 0 || end >= yValCount) {
                endValue = yValCount - 1;
            } else {
                endValue = end;
            }
            this.mLastStart = start;
            this.mLastEnd = endValue;
            this.mYMin = Float.MAX_VALUE;
            this.mYMax = -3.4028235E38f;
            for (int i = start; i <= endValue; i++) {
                BarEntry e = (BarEntry) this.mYVals.get(i);
                if (!(e == null || Float.isNaN(e.getVal()))) {
                    if (e.getVals() == null) {
                        if (e.getVal() < this.mYMin) {
                            this.mYMin = e.getVal();
                        }
                        if (e.getVal() > this.mYMax) {
                            this.mYMax = e.getVal();
                        }
                    } else {
                        if ((-e.getNegativeSum()) < this.mYMin) {
                            this.mYMin = -e.getNegativeSum();
                        }
                        if (e.getPositiveSum() > this.mYMax) {
                            this.mYMax = e.getPositiveSum();
                        }
                    }
                }
            }
            if (this.mYMin == Float.MAX_VALUE) {
                this.mYMin = 0.0f;
                this.mYMax = 0.0f;
            }
        }
    }

    public int getStackSize() {
        return this.mStackSize;
    }

    public boolean isStacked() {
        return this.mStackSize > 1;
    }

    public float getBarSpace() {
        return this.mBarSpace;
    }

    public int getBarShadowColor() {
        return this.mBarShadowColor;
    }

    public int getHighLightAlpha() {
        return this.mHighLightAlpha;
    }

    public String[] getStackLabels() {
        return this.mStackLabels;
    }
}
