package com.github.mikephil.charting.listener;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;

public abstract class ChartTouchListener<T extends Chart<?>> extends SimpleOnGestureListener implements OnTouchListener {
    protected T mChart;
    protected GestureDetector mGestureDetector;
    protected ChartGesture mLastGesture = ChartGesture.NONE;
    protected Highlight mLastHighlighted;
    protected int mTouchMode = 0;

    public enum ChartGesture {
        NONE,
        DRAG,
        X_ZOOM,
        Y_ZOOM,
        PINCH_ZOOM,
        ROTATE,
        SINGLE_TAP,
        DOUBLE_TAP,
        LONG_PRESS,
        FLING
    }

    public ChartTouchListener(T chart) {
        this.mChart = chart;
        this.mGestureDetector = new GestureDetector(chart.getContext(), this);
    }

    public void startAction(MotionEvent me) {
        OnChartGestureListener l = this.mChart.getOnChartGestureListener();
        if (l != null) {
            l.onChartGestureStart(me, this.mLastGesture);
        }
    }

    public void endAction(MotionEvent me) {
        OnChartGestureListener l = this.mChart.getOnChartGestureListener();
        if (l != null) {
            l.onChartGestureEnd(me, this.mLastGesture);
        }
    }

    public void setLastHighlighted(Highlight high) {
        this.mLastHighlighted = high;
    }

    protected void performHighlight(Highlight h, MotionEvent e) {
        if (h == null || h.equalTo(this.mLastHighlighted)) {
            this.mChart.highlightTouch(null);
            this.mLastHighlighted = null;
            return;
        }
        this.mLastHighlighted = h;
        this.mChart.highlightTouch(h);
    }

    protected static float distance(float eventX, float startX, float eventY, float startY) {
        float dx = eventX - startX;
        float dy = eventY - startY;
        return (float) Math.sqrt((double) ((dx * dx) + (dy * dy)));
    }
}
