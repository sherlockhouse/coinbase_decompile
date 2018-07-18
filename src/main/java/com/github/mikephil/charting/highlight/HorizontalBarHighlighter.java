package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.interfaces.BarDataProvider;

public class HorizontalBarHighlighter extends BarHighlighter {
    public HorizontalBarHighlighter(BarDataProvider chart) {
        super(chart);
    }

    public Highlight getHighlight(float x, float y) {
        Highlight h = super.getHighlight(x, y);
        if (h == null) {
            return h;
        }
        BarDataSet set = (BarDataSet) ((BarDataProvider) this.mChart).getBarData().getDataSetByIndex(h.getDataSetIndex());
        if (!set.isStacked()) {
            return h;
        }
        float[] pts = new float[2];
        pts[0] = y;
        ((BarDataProvider) this.mChart).getTransformer(set.getAxisDependency()).pixelsToValue(pts);
        return getStackedHighlight(h, set, h.getXIndex(), h.getDataSetIndex(), (double) pts[0]);
    }

    protected int getXIndex(float x) {
        if (((BarDataProvider) this.mChart).getBarData().isGrouped()) {
            float baseNoSpace = getBase(x);
            int xIndex = ((int) baseNoSpace) / ((BarDataProvider) this.mChart).getBarData().getDataSetCount();
            int valCount = ((BarDataProvider) this.mChart).getData().getXValCount();
            if (xIndex < 0) {
                return 0;
            }
            if (xIndex >= valCount) {
                return valCount - 1;
            }
            return xIndex;
        }
        float[] pts = new float[2];
        pts[1] = x;
        ((BarDataProvider) this.mChart).getTransformer(AxisDependency.LEFT).pixelsToValue(pts);
        return Math.round(pts[1]);
    }

    protected float getBase(float y) {
        float[] pts = new float[2];
        pts[1] = y;
        ((BarDataProvider) this.mChart).getTransformer(AxisDependency.LEFT).pixelsToValue(pts);
        float yVal = pts[1];
        return yVal - (((BarDataProvider) this.mChart).getBarData().getGroupSpace() * ((float) ((int) (yVal / (((BarDataProvider) this.mChart).getBarData().getGroupSpace() + ((float) ((BarDataProvider) this.mChart).getBarData().getDataSetCount()))))));
    }
}
