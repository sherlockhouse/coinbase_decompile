package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.LineDataProvider;
import com.github.mikephil.charting.renderer.LineChartRenderer;

public class LineChart extends BarLineChartBase<LineData> implements LineDataProvider {
    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void init() {
        super.init();
        this.mRenderer = new LineChartRenderer(this, this.mAnimator, this.mViewPortHandler);
    }

    protected void calcMinMax() {
        super.calcMinMax();
        if (this.mDeltaX == 0.0f && ((LineData) this.mData).getYValCount() > 0) {
            this.mDeltaX = 1.0f;
        }
    }

    public LineData getLineData() {
        return (LineData) this.mData;
    }

    protected void onDetachedFromWindow() {
        if (this.mRenderer != null && (this.mRenderer instanceof LineChartRenderer)) {
            ((LineChartRenderer) this.mRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }
}
