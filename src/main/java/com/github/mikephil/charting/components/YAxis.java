package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.formatter.DefaultYAxisValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;

public class YAxis extends AxisBase {
    private AxisDependency mAxisDependency;
    public float mAxisMaximum;
    public float mAxisMinimum;
    public float mAxisRange;
    protected float mCustomAxisMax;
    protected float mCustomAxisMin;
    public int mDecimals;
    private boolean mDrawTopYLabelEntry;
    public float[] mEntries;
    public int mEntryCount;
    protected boolean mForceLabels;
    protected boolean mInverted;
    private int mLabelCount;
    private YAxisLabelPosition mPosition;
    protected boolean mShowOnlyMinMax;
    protected float mSpacePercentBottom;
    protected float mSpacePercentTop;
    protected boolean mStartAtZero;
    protected YAxisValueFormatter mYAxisValueFormatter;

    public enum AxisDependency {
        LEFT,
        RIGHT
    }

    public enum YAxisLabelPosition {
        OUTSIDE_CHART,
        INSIDE_CHART
    }

    public YAxis() {
        this.mEntries = new float[0];
        this.mLabelCount = 6;
        this.mDrawTopYLabelEntry = true;
        this.mShowOnlyMinMax = false;
        this.mInverted = false;
        this.mStartAtZero = true;
        this.mForceLabels = false;
        this.mCustomAxisMin = Float.NaN;
        this.mCustomAxisMax = Float.NaN;
        this.mSpacePercentTop = 10.0f;
        this.mSpacePercentBottom = 10.0f;
        this.mAxisMaximum = 0.0f;
        this.mAxisMinimum = 0.0f;
        this.mAxisRange = 0.0f;
        this.mPosition = YAxisLabelPosition.OUTSIDE_CHART;
        this.mAxisDependency = AxisDependency.LEFT;
        this.mYOffset = 0.0f;
    }

    public YAxis(AxisDependency position) {
        this.mEntries = new float[0];
        this.mLabelCount = 6;
        this.mDrawTopYLabelEntry = true;
        this.mShowOnlyMinMax = false;
        this.mInverted = false;
        this.mStartAtZero = true;
        this.mForceLabels = false;
        this.mCustomAxisMin = Float.NaN;
        this.mCustomAxisMax = Float.NaN;
        this.mSpacePercentTop = 10.0f;
        this.mSpacePercentBottom = 10.0f;
        this.mAxisMaximum = 0.0f;
        this.mAxisMinimum = 0.0f;
        this.mAxisRange = 0.0f;
        this.mPosition = YAxisLabelPosition.OUTSIDE_CHART;
        this.mAxisDependency = position;
        this.mYOffset = 0.0f;
    }

    public AxisDependency getAxisDependency() {
        return this.mAxisDependency;
    }

    public YAxisLabelPosition getLabelPosition() {
        return this.mPosition;
    }

    public void setPosition(YAxisLabelPosition pos) {
        this.mPosition = pos;
    }

    public boolean isDrawTopYLabelEntryEnabled() {
        return this.mDrawTopYLabelEntry;
    }

    public void setLabelCount(int count, boolean force) {
        if (count > 25) {
            count = 25;
        }
        if (count < 2) {
            count = 2;
        }
        this.mLabelCount = count;
        this.mForceLabels = force;
    }

    public int getLabelCount() {
        return this.mLabelCount;
    }

    public boolean isForceLabelsEnabled() {
        return this.mForceLabels;
    }

    public boolean isShowOnlyMinMaxEnabled() {
        return this.mShowOnlyMinMax;
    }

    public boolean isInverted() {
        return this.mInverted;
    }

    public void setStartAtZero(boolean enabled) {
        this.mStartAtZero = enabled;
    }

    public boolean isStartAtZeroEnabled() {
        return this.mStartAtZero;
    }

    public float getAxisMinValue() {
        return this.mCustomAxisMin;
    }

    public float getAxisMaxValue() {
        return this.mCustomAxisMax;
    }

    public void setSpaceTop(float percent) {
        this.mSpacePercentTop = percent;
    }

    public float getSpaceTop() {
        return this.mSpacePercentTop;
    }

    public void setSpaceBottom(float percent) {
        this.mSpacePercentBottom = percent;
    }

    public float getSpaceBottom() {
        return this.mSpacePercentBottom;
    }

    public float getRequiredWidthSpace(Paint p) {
        p.setTextSize(this.mTextSize);
        return ((float) Utils.calcTextWidth(p, getLongestLabel())) + (getXOffset() * 2.0f);
    }

    public float getRequiredHeightSpace(Paint p) {
        p.setTextSize(this.mTextSize);
        return (((float) Utils.calcTextHeight(p, getLongestLabel())) + (Utils.convertDpToPixel(2.5f) * 2.0f)) + getYOffset();
    }

    public String getLongestLabel() {
        String longest = "";
        for (int i = 0; i < this.mEntries.length; i++) {
            String text = getFormattedLabel(i);
            if (longest.length() < text.length()) {
                longest = text;
            }
        }
        return longest;
    }

    public String getFormattedLabel(int index) {
        if (index < 0 || index >= this.mEntries.length) {
            return "";
        }
        return getValueFormatter().getFormattedValue(this.mEntries[index], this);
    }

    public void setValueFormatter(YAxisValueFormatter f) {
        if (f == null) {
            this.mYAxisValueFormatter = new DefaultYAxisValueFormatter(this.mDecimals);
        } else {
            this.mYAxisValueFormatter = f;
        }
    }

    public YAxisValueFormatter getValueFormatter() {
        if (this.mYAxisValueFormatter == null) {
            this.mYAxisValueFormatter = new DefaultYAxisValueFormatter(this.mDecimals);
        }
        return this.mYAxisValueFormatter;
    }

    public boolean needsOffset() {
        if (isEnabled() && isDrawLabelsEnabled() && getLabelPosition() == YAxisLabelPosition.OUTSIDE_CHART) {
            return true;
        }
        return false;
    }
}
