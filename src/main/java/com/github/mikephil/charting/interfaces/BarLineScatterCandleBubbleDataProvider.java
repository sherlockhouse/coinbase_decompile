package com.github.mikephil.charting.interfaces;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {
    BarLineScatterCandleBubbleData getData();

    int getHighestVisibleXIndex();

    int getLowestVisibleXIndex();

    int getMaxVisibleCount();

    Transformer getTransformer(AxisDependency axisDependency);

    boolean isInverted(AxisDependency axisDependency);
}
