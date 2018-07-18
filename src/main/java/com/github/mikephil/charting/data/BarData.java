package com.github.mikephil.charting.data;

public class BarData extends BarLineScatterCandleBubbleData<BarDataSet> {
    private float mGroupSpace = 0.8f;

    public float getGroupSpace() {
        if (this.mDataSets.size() <= 1) {
            return 0.0f;
        }
        return this.mGroupSpace;
    }

    public boolean isGrouped() {
        return this.mDataSets.size() > 1;
    }
}
