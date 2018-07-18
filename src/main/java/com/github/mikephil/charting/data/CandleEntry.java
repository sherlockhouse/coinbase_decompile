package com.github.mikephil.charting.data;

public class CandleEntry extends Entry {
    private float mClose;
    private float mOpen;
    private float mShadowHigh;
    private float mShadowLow;

    public float getVal() {
        return super.getVal();
    }

    public float getHigh() {
        return this.mShadowHigh;
    }

    public float getLow() {
        return this.mShadowLow;
    }

    public float getClose() {
        return this.mClose;
    }

    public float getOpen() {
        return this.mOpen;
    }
}
