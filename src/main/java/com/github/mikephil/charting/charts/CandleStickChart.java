package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.interfaces.CandleDataProvider;
import com.github.mikephil.charting.renderer.CandleStickChartRenderer;

public class CandleStickChart extends BarLineChartBase<CandleData> implements CandleDataProvider {
    public CandleStickChart(Context context) {
        super(context);
    }

    public CandleStickChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CandleStickChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void init() {
        super.init();
        this.mRenderer = new CandleStickChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        this.mXChartMin = -0.5f;
    }

    protected void calcMinMax() {
        super.calcMinMax();
        this.mXChartMax += 0.5f;
        this.mDeltaX = Math.abs(this.mXChartMax - this.mXChartMin);
    }

    public CandleData getCandleData() {
        return (CandleData) this.mData;
    }
}
