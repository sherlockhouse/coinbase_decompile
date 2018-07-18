package com.github.mikephil.charting.data;

import java.util.List;

public class LineData extends BarLineScatterCandleBubbleData<LineDataSet> {
    public LineData(List<String> xVals, List<LineDataSet> dataSets) {
        super(xVals, dataSets);
    }
}
