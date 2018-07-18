package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.interfaces.ScatterDataProvider;
import com.github.mikephil.charting.renderer.ScatterChartRenderer;

public class ScatterChart extends BarLineChartBase<ScatterData> implements ScatterDataProvider {

    public enum ScatterShape {
        CROSS,
        TRIANGLE,
        CIRCLE,
        SQUARE
    }

    public ScatterChart(Context context) {
        super(context);
    }

    public ScatterChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScatterChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void init() {
        super.init();
        this.mRenderer = new ScatterChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        this.mXChartMin = -0.5f;
    }

    protected void calcMinMax() {
        super.calcMinMax();
        if (this.mDeltaX == 0.0f && ((ScatterData) this.mData).getYValCount() > 0) {
            this.mDeltaX = 1.0f;
        }
        this.mXChartMax += 0.5f;
        this.mDeltaX = Math.abs(this.mXChartMax - this.mXChartMin);
    }

    public static ScatterShape[] getAllPossibleShapes() {
        return new ScatterShape[]{ScatterShape.SQUARE, ScatterShape.CIRCLE, ScatterShape.TRIANGLE, ScatterShape.CROSS};
    }

    public ScatterData getScatterData() {
        return (ScatterData) this.mData;
    }
}
