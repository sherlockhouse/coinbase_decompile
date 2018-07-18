package com.coinbase.android.pricechart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.github.mikephil.charting.charts.LineChart;

public class ZeroMarginLineChart extends LineChart {
    public ZeroMarginLineChart(Context context) {
        super(context);
        this.mMinOffset = 0.0f;
    }

    public ZeroMarginLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mMinOffset = 0.0f;
    }

    public ZeroMarginLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMinOffset = 0.0f;
    }

    public Bitmap getChartBitmap() {
        Bitmap chartBitmap = null;
        if (getWidth() == 0 || getHeight() == 0) {
            return null;
        }
        try {
            chartBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(chartBitmap);
            Drawable bgDrawable = getBackground();
            if (bgDrawable != null) {
                bgDrawable.draw(canvas);
            } else {
                canvas.drawColor(-1);
            }
            draw(canvas);
        } catch (Exception e) {
        }
        return chartBitmap;
    }
}
