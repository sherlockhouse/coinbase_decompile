package com.github.mikephil.charting.data;

import android.graphics.Color;
import java.util.List;

public abstract class BarLineScatterCandleBubbleDataSet<T extends Entry> extends DataSet<T> {
    protected int mHighLightColor = Color.rgb(255, 187, 115);

    public BarLineScatterCandleBubbleDataSet(List<T> yVals, String label) {
        super(yVals, label);
    }

    public void setHighLightColor(int color) {
        this.mHighLightColor = color;
    }

    public int getHighLightColor() {
        return this.mHighLightColor;
    }
}
