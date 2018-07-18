package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.CombinedHighlighter;
import com.github.mikephil.charting.interfaces.BarDataProvider;
import com.github.mikephil.charting.interfaces.BubbleDataProvider;
import com.github.mikephil.charting.interfaces.CandleDataProvider;
import com.github.mikephil.charting.interfaces.LineDataProvider;
import com.github.mikephil.charting.interfaces.ScatterDataProvider;
import com.github.mikephil.charting.renderer.CombinedChartRenderer;

public class CombinedChart extends BarLineChartBase<CombinedData> implements BarDataProvider, BubbleDataProvider, CandleDataProvider, LineDataProvider, ScatterDataProvider {
    private boolean mDrawBarShadow;
    private boolean mDrawHighlightArrow;
    protected DrawOrder[] mDrawOrder;
    private boolean mDrawValueAboveBar;

    public enum DrawOrder {
        BAR,
        BUBBLE,
        LINE,
        CANDLE,
        SCATTER
    }

    public CombinedChart(Context context) {
        super(context);
        this.mDrawHighlightArrow = false;
        this.mDrawValueAboveBar = true;
        this.mDrawBarShadow = false;
        this.mDrawOrder = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
    }

    public CombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDrawHighlightArrow = false;
        this.mDrawValueAboveBar = true;
        this.mDrawBarShadow = false;
        this.mDrawOrder = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
    }

    public CombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mDrawHighlightArrow = false;
        this.mDrawValueAboveBar = true;
        this.mDrawBarShadow = false;
        this.mDrawOrder = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
    }

    protected void init() {
        super.init();
        this.mHighlighter = new CombinedHighlighter(this);
    }

    protected void calcMinMax() {
        super.calcMinMax();
        if (!(getBarData() == null && getCandleData() == null && getBubbleData() == null)) {
            this.mXChartMin = -0.5f;
            this.mXChartMax = ((float) ((CombinedData) this.mData).getXVals().size()) - 0.5f;
            if (getBubbleData() != null) {
                for (BubbleDataSet set : getBubbleData().getDataSets()) {
                    float xmin = set.getXMin();
                    float xmax = set.getXMax();
                    if (xmin < this.mXChartMin) {
                        this.mXChartMin = xmin;
                    }
                    if (xmax > this.mXChartMax) {
                        this.mXChartMax = xmax;
                    }
                }
            }
        }
        this.mDeltaX = Math.abs(this.mXChartMax - this.mXChartMin);
        if (this.mDeltaX == 0.0f && getLineData() != null && getLineData().getYValCount() > 0) {
            this.mDeltaX = 1.0f;
        }
    }

    public void setData(CombinedData data) {
        this.mData = null;
        this.mRenderer = null;
        super.setData(data);
        this.mRenderer = new CombinedChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        this.mRenderer.initBuffers();
    }

    public LineData getLineData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getLineData();
    }

    public BarData getBarData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getBarData();
    }

    public ScatterData getScatterData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getScatterData();
    }

    public CandleData getCandleData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getCandleData();
    }

    public BubbleData getBubbleData() {
        if (this.mData == null) {
            return null;
        }
        return ((CombinedData) this.mData).getBubbleData();
    }

    public boolean isDrawBarShadowEnabled() {
        return this.mDrawBarShadow;
    }

    public boolean isDrawValueAboveBarEnabled() {
        return this.mDrawValueAboveBar;
    }

    public boolean isDrawHighlightArrowEnabled() {
        return this.mDrawHighlightArrow;
    }

    public void setDrawHighlightArrow(boolean enabled) {
        this.mDrawHighlightArrow = enabled;
    }

    public void setDrawValueAboveBar(boolean enabled) {
        this.mDrawValueAboveBar = enabled;
    }

    public void setDrawBarShadow(boolean enabled) {
        this.mDrawBarShadow = enabled;
    }

    public DrawOrder[] getDrawOrder() {
        return this.mDrawOrder;
    }

    public void setDrawOrder(DrawOrder[] order) {
        if (order != null && order.length > 0) {
            this.mDrawOrder = order;
        }
    }
}
