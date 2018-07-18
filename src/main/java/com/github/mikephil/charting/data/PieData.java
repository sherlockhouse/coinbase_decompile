package com.github.mikephil.charting.data;

public class PieData extends ChartData<PieDataSet> {
    public PieDataSet getDataSet() {
        return (PieDataSet) this.mDataSets.get(0);
    }

    public PieDataSet getDataSetByIndex(int index) {
        return index == 0 ? getDataSet() : null;
    }
}
