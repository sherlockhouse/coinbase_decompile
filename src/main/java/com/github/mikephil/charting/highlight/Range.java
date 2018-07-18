package com.github.mikephil.charting.highlight;

public final class Range {
    public float from;
    public float to;

    public Range(float from, float to) {
        this.from = from;
        this.to = to;
    }

    public boolean contains(float value) {
        if (value <= this.from || value > this.to) {
            return false;
        }
        return true;
    }
}
