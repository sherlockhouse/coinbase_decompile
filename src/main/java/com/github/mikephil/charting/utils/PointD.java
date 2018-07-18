package com.github.mikephil.charting.utils;

public class PointD {
    public double x;
    public double y;

    public PointD(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "PointD, x: " + this.x + ", y: " + this.y;
    }
}
