package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.interfaces.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.utils.SelectionDetail;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class ChartHighlighter<T extends BarLineScatterCandleBubbleDataProvider> {
    protected T mChart;

    public ChartHighlighter(T chart) {
        this.mChart = chart;
    }

    public Highlight getHighlight(float x, float y) {
        int xIndex = getXIndex(x);
        if (xIndex == -2147483647) {
            return null;
        }
        int dataSetIndex = getDataSetIndex(xIndex, x, y);
        if (dataSetIndex != -2147483647) {
            return new Highlight(xIndex, dataSetIndex);
        }
        return null;
    }

    protected int getXIndex(float x) {
        float[] pts = new float[2];
        pts[0] = x;
        this.mChart.getTransformer(AxisDependency.LEFT).pixelsToValue(pts);
        return Math.round(pts[0]);
    }

    protected int getDataSetIndex(int xIndex, float x, float y) {
        List<SelectionDetail> valsAtIndex = getSelectionDetailsAtIndex(xIndex);
        return Utils.getClosestDataSetIndex(valsAtIndex, y, Utils.getMinimumDistance(valsAtIndex, y, AxisDependency.LEFT) < Utils.getMinimumDistance(valsAtIndex, y, AxisDependency.RIGHT) ? AxisDependency.LEFT : AxisDependency.RIGHT);
    }

    protected List<SelectionDetail> getSelectionDetailsAtIndex(int xIndex) {
        List<SelectionDetail> vals = new ArrayList();
        float[] pts = new float[2];
        for (int i = 0; i < this.mChart.getData().getDataSetCount(); i++) {
            DataSet<?> dataSet = this.mChart.getData().getDataSetByIndex(i);
            if (dataSet.isHighlightEnabled()) {
                float yVal = dataSet.getYValForXIndex(xIndex);
                if (yVal != Float.NaN) {
                    pts[1] = yVal;
                    this.mChart.getTransformer(dataSet.getAxisDependency()).pointValuesToPixel(pts);
                    if (!Float.isNaN(pts[1])) {
                        vals.add(new SelectionDetail(pts[1], i, dataSet));
                    }
                }
            }
        }
        return vals;
    }
}
