package com.github.mikephil.charting.data;

import com.github.mikephil.charting.charts.ScatterChart.ScatterShape;

public class ScatterDataSet extends LineScatterCandleRadarDataSet<Entry> {
    private ScatterShape mScatterShape;
    private float mShapeSize;

    public float getScatterShapeSize() {
        return this.mShapeSize;
    }

    public ScatterShape getScatterShape() {
        return this.mScatterShape;
    }
}
