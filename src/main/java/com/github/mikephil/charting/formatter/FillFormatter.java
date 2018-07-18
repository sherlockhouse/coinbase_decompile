package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.LineDataProvider;

public interface FillFormatter {
    float getFillLinePosition(LineDataSet lineDataSet, LineDataProvider lineDataProvider);
}
