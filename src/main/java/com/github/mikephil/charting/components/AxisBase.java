package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public abstract class AxisBase extends ComponentBase {
    private int mAxisLineColor;
    private float mAxisLineWidth;
    protected boolean mDrawAxisLine;
    protected boolean mDrawGridLines;
    protected boolean mDrawLabels;
    protected boolean mDrawLimitLineBehindData;
    private int mGridColor;
    private DashPathEffect mGridDashPathEffect;
    private float mGridLineWidth;
    protected List<LimitLine> mLimitLines;

    public AxisBase() {
        this.mGridColor = -7829368;
        this.mGridLineWidth = 1.0f;
        this.mAxisLineColor = -7829368;
        this.mAxisLineWidth = 1.0f;
        this.mDrawGridLines = true;
        this.mDrawAxisLine = true;
        this.mDrawLabels = true;
        this.mGridDashPathEffect = null;
        this.mDrawLimitLineBehindData = false;
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(5.0f);
        this.mLimitLines = new ArrayList();
    }

    public void setDrawGridLines(boolean enabled) {
        this.mDrawGridLines = enabled;
    }

    public boolean isDrawGridLinesEnabled() {
        return this.mDrawGridLines;
    }

    public void setDrawAxisLine(boolean enabled) {
        this.mDrawAxisLine = enabled;
    }

    public boolean isDrawAxisLineEnabled() {
        return this.mDrawAxisLine;
    }

    public void setGridColor(int color) {
        this.mGridColor = color;
    }

    public int getGridColor() {
        return this.mGridColor;
    }

    public void setAxisLineWidth(float width) {
        this.mAxisLineWidth = Utils.convertDpToPixel(width);
    }

    public float getAxisLineWidth() {
        return this.mAxisLineWidth;
    }

    public void setGridLineWidth(float width) {
        this.mGridLineWidth = Utils.convertDpToPixel(width);
    }

    public float getGridLineWidth() {
        return this.mGridLineWidth;
    }

    public void setAxisLineColor(int color) {
        this.mAxisLineColor = color;
    }

    public int getAxisLineColor() {
        return this.mAxisLineColor;
    }

    public void setDrawLabels(boolean enabled) {
        this.mDrawLabels = enabled;
    }

    public boolean isDrawLabelsEnabled() {
        return this.mDrawLabels;
    }

    public void addLimitLine(LimitLine l) {
        this.mLimitLines.add(l);
        if (this.mLimitLines.size() > 6) {
            Log.e("MPAndroiChart", "Warning! You have more than 6 LimitLines on your axis, do you really want that?");
        }
    }

    public void removeAllLimitLines() {
        this.mLimitLines.clear();
    }

    public List<LimitLine> getLimitLines() {
        return this.mLimitLines;
    }

    public boolean isDrawLimitLinesBehindDataEnabled() {
        return this.mDrawLimitLineBehindData;
    }

    public void enableGridDashedLine(float lineLength, float spaceLength, float phase) {
        this.mGridDashPathEffect = new DashPathEffect(new float[]{lineLength, spaceLength}, phase);
    }

    public DashPathEffect getGridDashPathEffect() {
        return this.mGridDashPathEffect;
    }
}
