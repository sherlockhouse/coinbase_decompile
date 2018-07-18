package com.github.mikephil.charting.data;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.highlight.Highlight;
import java.util.ArrayList;
import java.util.List;

public abstract class ChartData<T extends DataSet<? extends Entry>> {
    protected List<T> mDataSets;
    protected int mLastEnd;
    protected int mLastStart;
    protected float mLeftAxisMax;
    protected float mLeftAxisMin;
    protected float mRightAxisMax;
    protected float mRightAxisMin;
    private float mXValAverageLength;
    protected List<String> mXVals;
    protected float mYMax;
    protected float mYMin;
    private int mYValCount;
    private float mYValueSum;

    public ChartData() {
        this.mYMax = 0.0f;
        this.mYMin = 0.0f;
        this.mLeftAxisMax = 0.0f;
        this.mLeftAxisMin = 0.0f;
        this.mRightAxisMax = 0.0f;
        this.mRightAxisMin = 0.0f;
        this.mYValueSum = 0.0f;
        this.mYValCount = 0;
        this.mLastStart = 0;
        this.mLastEnd = 0;
        this.mXValAverageLength = 0.0f;
        this.mXVals = new ArrayList();
        this.mDataSets = new ArrayList();
    }

    public ChartData(List<String> xVals, List<T> sets) {
        this.mYMax = 0.0f;
        this.mYMin = 0.0f;
        this.mLeftAxisMax = 0.0f;
        this.mLeftAxisMin = 0.0f;
        this.mRightAxisMax = 0.0f;
        this.mRightAxisMin = 0.0f;
        this.mYValueSum = 0.0f;
        this.mYValCount = 0;
        this.mLastStart = 0;
        this.mLastEnd = 0;
        this.mXValAverageLength = 0.0f;
        this.mXVals = xVals;
        this.mDataSets = sets;
        init();
    }

    protected void init() {
        checkLegal();
        calcMinMax(this.mLastStart, this.mLastEnd);
        calcYValueSum();
        calcYValueCount();
        calcXValAverageLength();
    }

    private void calcXValAverageLength() {
        if (this.mXVals.size() <= 0) {
            this.mXValAverageLength = 1.0f;
            return;
        }
        float sum = 1.0f;
        for (int i = 0; i < this.mXVals.size(); i++) {
            sum += (float) ((String) this.mXVals.get(i)).length();
        }
        this.mXValAverageLength = sum / ((float) this.mXVals.size());
    }

    private void checkLegal() {
        if (this.mDataSets != null && !(this instanceof ScatterData)) {
            for (int i = 0; i < this.mDataSets.size(); i++) {
                if (((DataSet) this.mDataSets.get(i)).getYVals().size() > this.mXVals.size()) {
                    throw new IllegalArgumentException("One or more of the DataSet Entry arrays are longer than the x-values array of this ChartData object.");
                }
            }
        }
    }

    public void notifyDataChanged() {
        init();
    }

    public void calcMinMax(int start, int end) {
        if (this.mDataSets == null || this.mDataSets.size() < 1) {
            this.mYMax = 0.0f;
            this.mYMin = 0.0f;
            return;
        }
        this.mLastStart = start;
        this.mLastEnd = end;
        this.mYMin = Float.MAX_VALUE;
        this.mYMax = -3.4028235E38f;
        for (int i = 0; i < this.mDataSets.size(); i++) {
            ((DataSet) this.mDataSets.get(i)).calcMinMax(start, end);
            if (((DataSet) this.mDataSets.get(i)).getYMin() < this.mYMin) {
                this.mYMin = ((DataSet) this.mDataSets.get(i)).getYMin();
            }
            if (((DataSet) this.mDataSets.get(i)).getYMax() > this.mYMax) {
                this.mYMax = ((DataSet) this.mDataSets.get(i)).getYMax();
            }
        }
        if (this.mYMin == Float.MAX_VALUE) {
            this.mYMin = 0.0f;
            this.mYMax = 0.0f;
        }
        T firstLeft = getFirstLeft();
        if (firstLeft != null) {
            this.mLeftAxisMax = firstLeft.getYMax();
            this.mLeftAxisMin = firstLeft.getYMin();
            for (DataSet<?> dataSet : this.mDataSets) {
                if (dataSet.getAxisDependency() == AxisDependency.LEFT) {
                    if (dataSet.getYMin() < this.mLeftAxisMin) {
                        this.mLeftAxisMin = dataSet.getYMin();
                    }
                    if (dataSet.getYMax() > this.mLeftAxisMax) {
                        this.mLeftAxisMax = dataSet.getYMax();
                    }
                }
            }
        }
        T firstRight = getFirstRight();
        if (firstRight != null) {
            this.mRightAxisMax = firstRight.getYMax();
            this.mRightAxisMin = firstRight.getYMin();
            for (DataSet<?> dataSet2 : this.mDataSets) {
                if (dataSet2.getAxisDependency() == AxisDependency.RIGHT) {
                    if (dataSet2.getYMin() < this.mRightAxisMin) {
                        this.mRightAxisMin = dataSet2.getYMin();
                    }
                    if (dataSet2.getYMax() > this.mRightAxisMax) {
                        this.mRightAxisMax = dataSet2.getYMax();
                    }
                }
            }
        }
        handleEmptyAxis(firstLeft, firstRight);
    }

    protected void calcYValueSum() {
        this.mYValueSum = 0.0f;
        if (this.mDataSets != null) {
            for (int i = 0; i < this.mDataSets.size(); i++) {
                this.mYValueSum = Math.abs(((DataSet) this.mDataSets.get(i)).getYValueSum()) + this.mYValueSum;
            }
        }
    }

    protected void calcYValueCount() {
        this.mYValCount = 0;
        if (this.mDataSets != null) {
            int count = 0;
            for (int i = 0; i < this.mDataSets.size(); i++) {
                count += ((DataSet) this.mDataSets.get(i)).getEntryCount();
            }
            this.mYValCount = count;
        }
    }

    public int getDataSetCount() {
        if (this.mDataSets == null) {
            return 0;
        }
        return this.mDataSets.size();
    }

    public float getYMin() {
        return this.mYMin;
    }

    public float getYMin(AxisDependency axis) {
        if (axis == AxisDependency.LEFT) {
            return this.mLeftAxisMin;
        }
        return this.mRightAxisMin;
    }

    public float getYMax() {
        return this.mYMax;
    }

    public float getYMax(AxisDependency axis) {
        if (axis == AxisDependency.LEFT) {
            return this.mLeftAxisMax;
        }
        return this.mRightAxisMax;
    }

    public float getXValAverageLength() {
        return this.mXValAverageLength;
    }

    public float getYValueSum() {
        return this.mYValueSum;
    }

    public int getYValCount() {
        return this.mYValCount;
    }

    public List<String> getXVals() {
        return this.mXVals;
    }

    public List<T> getDataSets() {
        return this.mDataSets;
    }

    public int getXValCount() {
        return this.mXVals.size();
    }

    public Entry getEntryForHighlight(Highlight highlight) {
        if (highlight.getDataSetIndex() >= this.mDataSets.size()) {
            return null;
        }
        return ((DataSet) this.mDataSets.get(highlight.getDataSetIndex())).getEntryForXIndex(highlight.getXIndex());
    }

    public T getDataSetByIndex(int index) {
        if (this.mDataSets == null || index < 0 || index >= this.mDataSets.size()) {
            return null;
        }
        return (DataSet) this.mDataSets.get(index);
    }

    private void handleEmptyAxis(T firstLeft, T firstRight) {
        if (firstLeft == null) {
            this.mLeftAxisMax = this.mRightAxisMax;
            this.mLeftAxisMin = this.mRightAxisMin;
        } else if (firstRight == null) {
            this.mRightAxisMax = this.mLeftAxisMax;
            this.mRightAxisMin = this.mLeftAxisMin;
        }
    }

    public int getIndexOfDataSet(T dataSet) {
        for (int i = 0; i < this.mDataSets.size(); i++) {
            if (this.mDataSets.get(i) == dataSet) {
                return i;
            }
        }
        return -1;
    }

    public T getFirstLeft() {
        for (DataSet dataSet : this.mDataSets) {
            if (dataSet.getAxisDependency() == AxisDependency.LEFT) {
                return dataSet;
            }
        }
        return null;
    }

    public T getFirstRight() {
        for (DataSet dataSet : this.mDataSets) {
            if (dataSet.getAxisDependency() == AxisDependency.RIGHT) {
                return dataSet;
            }
        }
        return null;
    }

    public void clearValues() {
        this.mDataSets.clear();
        notifyDataChanged();
    }
}
