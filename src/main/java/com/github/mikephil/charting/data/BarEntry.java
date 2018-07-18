package com.github.mikephil.charting.data;

public class BarEntry extends Entry {
    private float mNegativeSum;
    private float mPositiveSum;
    private float[] mVals;

    public float[] getVals() {
        return this.mVals;
    }

    public float getVal() {
        return super.getVal();
    }

    public float getPositiveSum() {
        return this.mPositiveSum;
    }

    public float getNegativeSum() {
        return this.mNegativeSum;
    }
}
