package com.github.mikephil.charting.data;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public abstract class LineScatterCandleRadarDataSet<T extends Entry> extends BarLineScatterCandleBubbleDataSet<T> {
    protected boolean mDrawHorizontalHighlightIndicator;
    protected boolean mDrawVerticalHighlightIndicator;
    protected DashPathEffect mHighlightDashPathEffect;
    protected float mHighlightLineWidth;

    public LineScatterCandleRadarDataSet(List<T> yVals, String label) {
        super(yVals, label);
        this.mDrawVerticalHighlightIndicator = true;
        this.mDrawHorizontalHighlightIndicator = true;
        this.mHighlightLineWidth = 0.5f;
        this.mHighlightDashPathEffect = null;
        this.mHighlightLineWidth = Utils.convertDpToPixel(0.5f);
    }

    public void setDrawHorizontalHighlightIndicator(boolean enabled) {
        this.mDrawHorizontalHighlightIndicator = enabled;
    }

    public boolean isVerticalHighlightIndicatorEnabled() {
        return this.mDrawVerticalHighlightIndicator;
    }

    public boolean isHorizontalHighlightIndicatorEnabled() {
        return this.mDrawHorizontalHighlightIndicator;
    }

    public float getHighlightLineWidth() {
        return this.mHighlightLineWidth;
    }

    public DashPathEffect getDashPathEffectHighlight() {
        return this.mHighlightDashPathEffect;
    }
}
