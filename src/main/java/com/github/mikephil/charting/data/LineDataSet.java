package com.github.mikephil.charting.data;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class LineDataSet extends LineRadarDataSet<Entry> {
    private int mCircleColorHole;
    private List<Integer> mCircleColors;
    private float mCircleSize;
    private float mCubicIntensity;
    private DashPathEffect mDashPathEffect;
    private boolean mDrawCircleHole;
    private boolean mDrawCircles;
    private boolean mDrawCubic;
    private FillFormatter mFillFormatter;

    public LineDataSet(List<Entry> yVals, String label) {
        super(yVals, label);
        this.mCircleColors = null;
        this.mCircleColorHole = -1;
        this.mCircleSize = 8.0f;
        this.mCubicIntensity = 0.2f;
        this.mDashPathEffect = null;
        this.mFillFormatter = new DefaultFillFormatter();
        this.mDrawCircles = true;
        this.mDrawCubic = false;
        this.mDrawCircleHole = true;
        this.mCircleColors = new ArrayList();
        this.mCircleColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
    }

    public void setCubicIntensity(float intensity) {
        if (intensity > 1.0f) {
            intensity = 1.0f;
        }
        if (intensity < 0.05f) {
            intensity = 0.05f;
        }
        this.mCubicIntensity = intensity;
    }

    public float getCubicIntensity() {
        return this.mCubicIntensity;
    }

    public void setCircleSize(float size) {
        this.mCircleSize = Utils.convertDpToPixel(size);
    }

    public float getCircleSize() {
        return this.mCircleSize;
    }

    public boolean isDashedLineEnabled() {
        return this.mDashPathEffect != null;
    }

    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public boolean isDrawCirclesEnabled() {
        return this.mDrawCircles;
    }

    public void setDrawCubic(boolean enabled) {
        this.mDrawCubic = enabled;
    }

    public boolean isDrawCubicEnabled() {
        return this.mDrawCubic;
    }

    public int getCircleColor(int index) {
        return ((Integer) this.mCircleColors.get(index % this.mCircleColors.size())).intValue();
    }

    public void setCircleColor(int color) {
        resetCircleColors();
        this.mCircleColors.add(Integer.valueOf(color));
    }

    public void resetCircleColors() {
        this.mCircleColors = new ArrayList();
    }

    public int getCircleHoleColor() {
        return this.mCircleColorHole;
    }

    public void setDrawCircleHole(boolean enabled) {
        this.mDrawCircleHole = enabled;
    }

    public boolean isDrawCircleHoleEnabled() {
        return this.mDrawCircleHole;
    }

    public void setFillFormatter(FillFormatter formatter) {
        if (formatter == null) {
            this.mFillFormatter = new DefaultFillFormatter();
        } else {
            this.mFillFormatter = formatter;
        }
    }

    public FillFormatter getFillFormatter() {
        return this.mFillFormatter;
    }
}
