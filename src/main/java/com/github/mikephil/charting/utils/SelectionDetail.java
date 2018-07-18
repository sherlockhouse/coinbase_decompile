package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.data.DataSet;

public class SelectionDetail {
    public DataSet<?> dataSet;
    public int dataSetIndex;
    public float val;

    public SelectionDetail(float val, int dataSetIndex, DataSet<?> set) {
        this.val = val;
        this.dataSetIndex = dataSetIndex;
        this.dataSet = set;
    }
}
