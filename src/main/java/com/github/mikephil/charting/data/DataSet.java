package com.github.mikephil.charting.data;

import android.graphics.Color;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class DataSet<T extends Entry> {
    protected AxisDependency mAxisDependency = AxisDependency.LEFT;
    protected List<Integer> mColors = null;
    protected boolean mDrawValues = true;
    protected boolean mHighlightEnabled = true;
    private String mLabel = "DataSet";
    protected int mLastEnd = 0;
    protected int mLastStart = 0;
    private int mValueColor = -16777216;
    protected transient ValueFormatter mValueFormatter;
    private float mValueTextSize = 17.0f;
    private Typeface mValueTypeface;
    private boolean mVisible = true;
    protected float mYMax = 0.0f;
    protected float mYMin = 0.0f;
    protected List<T> mYVals = null;
    private float mYValueSum = 0.0f;

    public DataSet(List<T> yVals, String label) {
        this.mLabel = label;
        this.mYVals = yVals;
        if (this.mYVals == null) {
            this.mYVals = new ArrayList();
        }
        this.mColors = new ArrayList();
        this.mColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
        calcMinMax(this.mLastStart, this.mLastEnd);
        calcYValueSum();
    }

    protected void calcMinMax(int start, int end) {
        int yValCount = this.mYVals.size();
        if (yValCount != 0) {
            int endValue;
            if (end == 0 || end >= yValCount) {
                endValue = yValCount - 1;
            } else {
                endValue = end;
            }
            this.mLastStart = start;
            this.mLastEnd = endValue;
            this.mYMin = Float.MAX_VALUE;
            this.mYMax = -3.4028235E38f;
            for (int i = start; i <= endValue; i++) {
                Entry e = (Entry) this.mYVals.get(i);
                if (!(e == null || Float.isNaN(e.getVal()))) {
                    if (e.getVal() < this.mYMin) {
                        this.mYMin = e.getVal();
                    }
                    if (e.getVal() > this.mYMax) {
                        this.mYMax = e.getVal();
                    }
                }
            }
            if (this.mYMin == Float.MAX_VALUE) {
                this.mYMin = 0.0f;
                this.mYMax = 0.0f;
            }
        }
    }

    private void calcYValueSum() {
        this.mYValueSum = 0.0f;
        for (int i = 0; i < this.mYVals.size(); i++) {
            Entry e = (Entry) this.mYVals.get(i);
            if (e != null) {
                this.mYValueSum += Math.abs(e.getVal());
            }
        }
    }

    public int getEntryCount() {
        return this.mYVals.size();
    }

    public float getYValForXIndex(int xIndex) {
        Entry e = getEntryForXIndex(xIndex);
        if (e == null || e.getXIndex() != xIndex) {
            return Float.NaN;
        }
        return e.getVal();
    }

    public T getEntryForXIndex(int x) {
        int index = getEntryIndex(x);
        if (index > -1) {
            return (Entry) this.mYVals.get(index);
        }
        return null;
    }

    public int getEntryIndex(int x) {
        int low = 0;
        int high = this.mYVals.size() - 1;
        int closest = -1;
        while (low <= high) {
            int m = (high + low) / 2;
            if (x == ((Entry) this.mYVals.get(m)).getXIndex()) {
                while (m > 0 && ((Entry) this.mYVals.get(m - 1)).getXIndex() == x) {
                    m--;
                }
                return m;
            }
            if (x > ((Entry) this.mYVals.get(m)).getXIndex()) {
                low = m + 1;
            } else {
                high = m - 1;
            }
            closest = m;
        }
        return closest;
    }

    public List<T> getYVals() {
        return this.mYVals;
    }

    public float getYValueSum() {
        return this.mYValueSum;
    }

    public float getYMin() {
        return this.mYMin;
    }

    public float getYMax() {
        return this.mYMax;
    }

    public int getValueCount() {
        return this.mYVals.size();
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(toSimpleString());
        for (int i = 0; i < this.mYVals.size(); i++) {
            buffer.append(((Entry) this.mYVals.get(i)).toString() + " ");
        }
        return buffer.toString();
    }

    public String toSimpleString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("DataSet, label: " + (this.mLabel == null ? "" : this.mLabel) + ", entries: " + this.mYVals.size() + "\n");
        return buffer.toString();
    }

    public String getLabel() {
        return this.mLabel;
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    public AxisDependency getAxisDependency() {
        return this.mAxisDependency;
    }

    public void setDrawValues(boolean enabled) {
        this.mDrawValues = enabled;
    }

    public boolean isDrawValuesEnabled() {
        return this.mDrawValues;
    }

    public void setColor(int color) {
        resetColors();
        this.mColors.add(Integer.valueOf(color));
    }

    public List<Integer> getColors() {
        return this.mColors;
    }

    public int getColor(int index) {
        return ((Integer) this.mColors.get(index % this.mColors.size())).intValue();
    }

    public int getColor() {
        return ((Integer) this.mColors.get(0)).intValue();
    }

    public void resetColors() {
        this.mColors = new ArrayList();
    }

    public boolean isHighlightEnabled() {
        return this.mHighlightEnabled;
    }

    public int getEntryPosition(Entry e) {
        for (int i = 0; i < this.mYVals.size(); i++) {
            if (e.equalTo((Entry) this.mYVals.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public void setValueFormatter(ValueFormatter f) {
        if (f != null) {
            this.mValueFormatter = f;
        }
    }

    public ValueFormatter getValueFormatter() {
        if (this.mValueFormatter == null) {
            return new DefaultValueFormatter(1);
        }
        return this.mValueFormatter;
    }

    public boolean needsDefaultFormatter() {
        if (this.mValueFormatter == null || (this.mValueFormatter instanceof DefaultValueFormatter)) {
            return true;
        }
        return false;
    }

    public int getValueTextColor() {
        return this.mValueColor;
    }

    public Typeface getValueTypeface() {
        return this.mValueTypeface;
    }

    public float getValueTextSize() {
        return this.mValueTextSize;
    }
}
