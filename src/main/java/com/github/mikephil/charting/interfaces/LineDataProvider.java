package com.github.mikephil.charting.interfaces;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {
    YAxis getAxis(AxisDependency axisDependency);

    LineData getLineData();
}
