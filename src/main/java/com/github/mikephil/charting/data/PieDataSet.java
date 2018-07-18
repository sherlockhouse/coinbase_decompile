package com.github.mikephil.charting.data;

public class PieDataSet extends DataSet<Entry> {
    private float mShift;
    private float mSliceSpace;

    public float getSliceSpace() {
        return this.mSliceSpace;
    }

    public float getSelectionShift() {
        return this.mShift;
    }
}
