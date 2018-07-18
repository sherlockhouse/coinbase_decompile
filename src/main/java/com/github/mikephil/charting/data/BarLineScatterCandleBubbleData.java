package com.github.mikephil.charting.data;

import java.util.List;

public abstract class BarLineScatterCandleBubbleData<T extends BarLineScatterCandleBubbleDataSet<? extends Entry>> extends ChartData<T> {
    public BarLineScatterCandleBubbleData(List<String> xVals, List<T> sets) {
        super(xVals, sets);
    }
}
