package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.LineDataProvider;

public class DefaultFillFormatter implements FillFormatter {
    public float getFillLinePosition(LineDataSet dataSet, LineDataProvider dataProvider) {
        float chartMaxY = dataProvider.getYChartMax();
        float chartMinY = dataProvider.getYChartMin();
        LineData data = dataProvider.getLineData();
        if (dataSet.getYMax() > 0.0f && dataSet.getYMin() < 0.0f) {
            return 0.0f;
        }
        if (dataProvider.getAxis(dataSet.getAxisDependency()).isStartAtZeroEnabled()) {
            return 0.0f;
        }
        float max;
        float min;
        float fillMin;
        if (data.getYMax() > 0.0f) {
            max = 0.0f;
        } else {
            max = chartMaxY;
        }
        if (data.getYMin() < 0.0f) {
            min = 0.0f;
        } else {
            min = chartMinY;
        }
        if (dataSet.getYMin() >= 0.0f) {
            fillMin = min;
        } else {
            fillMin = max;
        }
        return fillMin;
    }
}
