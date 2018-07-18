package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class RadarChartRenderer extends LineScatterCandleRadarRenderer {
    protected RadarChart mChart;
    protected Paint mWebPaint;

    public RadarChartRenderer(RadarChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(2.0f);
        this.mHighlightPaint.setColor(Color.rgb(255, 187, 115));
        this.mWebPaint = new Paint(1);
        this.mWebPaint.setStyle(Style.STROKE);
    }

    public void initBuffers() {
    }

    public void drawData(Canvas c) {
        for (RadarDataSet set : ((RadarData) this.mChart.getData()).getDataSets()) {
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set);
            }
        }
    }

    protected void drawDataSet(Canvas c, RadarDataSet dataSet) {
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        PointF center = this.mChart.getCenterOffsets();
        List<Entry> entries = dataSet.getYVals();
        Path surface = new Path();
        boolean hasMovedToPoint = false;
        for (int j = 0; j < entries.size(); j++) {
            this.mRenderPaint.setColor(dataSet.getColor(j));
            PointF p = Utils.getPosition(center, (((Entry) entries.get(j)).getVal() - this.mChart.getYChartMin()) * factor, (((float) j) * sliceangle) + this.mChart.getRotationAngle());
            if (!Float.isNaN(p.x)) {
                if (hasMovedToPoint) {
                    surface.lineTo(p.x, p.y);
                } else {
                    surface.moveTo(p.x, p.y);
                    hasMovedToPoint = true;
                }
            }
        }
        surface.close();
        if (dataSet.isDrawFilledEnabled()) {
            this.mRenderPaint.setStyle(Style.FILL);
            this.mRenderPaint.setAlpha(dataSet.getFillAlpha());
            c.drawPath(surface, this.mRenderPaint);
            this.mRenderPaint.setAlpha(255);
        }
        this.mRenderPaint.setStrokeWidth(dataSet.getLineWidth());
        this.mRenderPaint.setStyle(Style.STROKE);
        if (!dataSet.isDrawFilledEnabled() || dataSet.getFillAlpha() < 255) {
            c.drawPath(surface, this.mRenderPaint);
        }
    }

    public void drawValues(Canvas c) {
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        PointF center = this.mChart.getCenterOffsets();
        float yoffset = Utils.convertDpToPixel(5.0f);
        for (int i = 0; i < ((RadarData) this.mChart.getData()).getDataSetCount(); i++) {
            RadarDataSet dataSet = (RadarDataSet) ((RadarData) this.mChart.getData()).getDataSetByIndex(i);
            if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                applyValueTextStyle(dataSet);
                List<Entry> entries = dataSet.getYVals();
                for (int j = 0; j < entries.size(); j++) {
                    Entry entry = (Entry) entries.get(j);
                    PointF p = Utils.getPosition(center, (entry.getVal() - this.mChart.getYChartMin()) * factor, (((float) j) * sliceangle) + this.mChart.getRotationAngle());
                    drawValue(c, dataSet.getValueFormatter(), entry.getVal(), entry, i, p.x, p.y - yoffset);
                }
            }
        }
    }

    public void drawExtras(Canvas c) {
        drawWeb(c);
    }

    protected void drawWeb(Canvas c) {
        int i;
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        float rotationangle = this.mChart.getRotationAngle();
        PointF center = this.mChart.getCenterOffsets();
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidth());
        this.mWebPaint.setColor(this.mChart.getWebColor());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int xIncrements = this.mChart.getSkipWebLineCount() + 1;
        for (i = 0; i < ((RadarData) this.mChart.getData()).getXValCount(); i += xIncrements) {
            PointF p = Utils.getPosition(center, this.mChart.getYRange() * factor, (((float) i) * sliceangle) + rotationangle);
            c.drawLine(center.x, center.y, p.x, p.y, this.mWebPaint);
        }
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidthInner());
        this.mWebPaint.setColor(this.mChart.getWebColorInner());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int labelCount = this.mChart.getYAxis().mEntryCount;
        for (int j = 0; j < labelCount; j++) {
            for (i = 0; i < ((RadarData) this.mChart.getData()).getXValCount(); i++) {
                float r = (this.mChart.getYAxis().mEntries[j] - this.mChart.getYChartMin()) * factor;
                PointF p1 = Utils.getPosition(center, r, (((float) i) * sliceangle) + rotationangle);
                PointF p2 = Utils.getPosition(center, r, (((float) (i + 1)) * sliceangle) + rotationangle);
                c.drawLine(p1.x, p1.y, p2.x, p2.y, this.mWebPaint);
            }
        }
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        PointF center = this.mChart.getCenterOffsets();
        for (int i = 0; i < indices.length; i++) {
            RadarDataSet set = (RadarDataSet) ((RadarData) this.mChart.getData()).getDataSetByIndex(indices[i].getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                int xIndex = indices[i].getXIndex();
                Entry e = set.getEntryForXIndex(xIndex);
                if (e != null && e.getXIndex() == xIndex) {
                    int j = set.getEntryPosition(e);
                    float y = e.getVal() - this.mChart.getYChartMin();
                    if (!Float.isNaN(y)) {
                        PointF p = Utils.getPosition(center, y * factor, (((float) j) * sliceangle) + this.mChart.getRotationAngle());
                        drawHighlightLines(c, new float[]{p.x, p.y}, set);
                    }
                }
            }
        }
    }
}
