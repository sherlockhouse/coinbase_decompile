package com.github.mikephil.charting.data.filter;

public class Approximator {
    private float mDeltaRatio;
    private float mScaleRatio;
    private double mTolerance;
    private ApproximatorType mType;

    public enum ApproximatorType {
        NONE,
        DOUGLAS_PEUCKER
    }

    public Approximator() {
        this.mType = ApproximatorType.DOUGLAS_PEUCKER;
        this.mTolerance = 0.0d;
        this.mScaleRatio = 1.0f;
        this.mDeltaRatio = 1.0f;
        this.mType = ApproximatorType.NONE;
    }
}
