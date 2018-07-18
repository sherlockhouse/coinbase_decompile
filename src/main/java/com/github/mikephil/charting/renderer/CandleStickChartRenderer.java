package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.CandleBodyBuffer;
import com.github.mikephil.charting.buffer.CandleShadowBuffer;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.CandleDataProvider;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class CandleStickChartRenderer extends LineScatterCandleRadarRenderer {
    private CandleBodyBuffer[] mBodyBuffers;
    protected CandleDataProvider mChart;
    private CandleShadowBuffer[] mShadowBuffers;

    public CandleStickChartRenderer(CandleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
    }

    public void initBuffers() {
        CandleData candleData = this.mChart.getCandleData();
        this.mShadowBuffers = new CandleShadowBuffer[candleData.getDataSetCount()];
        this.mBodyBuffers = new CandleBodyBuffer[candleData.getDataSetCount()];
        for (int i = 0; i < this.mShadowBuffers.length; i++) {
            CandleDataSet set = (CandleDataSet) candleData.getDataSetByIndex(i);
            this.mShadowBuffers[i] = new CandleShadowBuffer(set.getValueCount() * 4);
            this.mBodyBuffers[i] = new CandleBodyBuffer(set.getValueCount() * 4);
        }
    }

    public void drawData(Canvas c) {
        for (CandleDataSet set : this.mChart.getCandleData().getDataSets()) {
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set);
            }
        }
    }

    protected void drawDataSet(Canvas c, CandleDataSet dataSet) {
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        int dataSetIndex = this.mChart.getCandleData().getIndexOfDataSet(dataSet);
        List<CandleEntry> entries = dataSet.getYVals();
        int minx = Math.max(this.mMinX, 0);
        int maxx = Math.min(this.mMaxX + 1, entries.size());
        int range = (maxx - minx) * 4;
        int to = (int) Math.ceil((double) ((((float) (maxx - minx)) * phaseX) + ((float) minx)));
        CandleBodyBuffer bodyBuffer = this.mBodyBuffers[dataSetIndex];
        bodyBuffer.setBodySpace(dataSet.getBodySpace());
        bodyBuffer.setPhases(phaseX, phaseY);
        bodyBuffer.limitFrom(minx);
        bodyBuffer.limitTo(maxx);
        bodyBuffer.feed(entries);
        trans.pointValuesToPixel(bodyBuffer.buffer);
        CandleShadowBuffer shadowBuffer = this.mShadowBuffers[dataSetIndex];
        shadowBuffer.setPhases(phaseX, phaseY);
        shadowBuffer.limitFrom(minx);
        shadowBuffer.limitTo(maxx);
        shadowBuffer.feed(entries);
        trans.pointValuesToPixel(shadowBuffer.buffer);
        this.mRenderPaint.setStrokeWidth(dataSet.getShadowWidth());
        int j = 0;
        while (j < range) {
            CandleEntry e = (CandleEntry) entries.get((j / 4) + minx);
            if (fitsBounds((float) e.getXIndex(), (float) this.mMinX, (float) to)) {
                if (!dataSet.getShadowColorSameAsCandle()) {
                    this.mRenderPaint.setColor(dataSet.getShadowColor() == -1 ? dataSet.getColor(j) : dataSet.getShadowColor());
                } else if (e.getOpen() > e.getClose()) {
                    int color;
                    Paint paint = this.mRenderPaint;
                    if (dataSet.getDecreasingColor() == -1) {
                        color = dataSet.getColor(j);
                    } else {
                        color = dataSet.getDecreasingColor();
                    }
                    paint.setColor(color);
                } else if (e.getOpen() < e.getClose()) {
                    this.mRenderPaint.setColor(dataSet.getIncreasingColor() == -1 ? dataSet.getColor(j) : dataSet.getIncreasingColor());
                } else {
                    this.mRenderPaint.setColor(dataSet.getShadowColor() == -1 ? dataSet.getColor(j) : dataSet.getShadowColor());
                }
                this.mRenderPaint.setStyle(Style.STROKE);
                c.drawLine(shadowBuffer.buffer[j], shadowBuffer.buffer[j + 1], shadowBuffer.buffer[j + 2], shadowBuffer.buffer[j + 3], this.mRenderPaint);
                float leftBody = bodyBuffer.buffer[j];
                float open = bodyBuffer.buffer[j + 1];
                float rightBody = bodyBuffer.buffer[j + 2];
                float close = bodyBuffer.buffer[j + 3];
                if (open > close) {
                    if (dataSet.getDecreasingColor() == -1) {
                        this.mRenderPaint.setColor(dataSet.getColor((j / 4) + minx));
                    } else {
                        this.mRenderPaint.setColor(dataSet.getDecreasingColor());
                    }
                    this.mRenderPaint.setStyle(dataSet.getDecreasingPaintStyle());
                    c.drawRect(leftBody, close, rightBody, open, this.mRenderPaint);
                } else if (open < close) {
                    if (dataSet.getIncreasingColor() == -1) {
                        this.mRenderPaint.setColor(dataSet.getColor((j / 4) + minx));
                    } else {
                        this.mRenderPaint.setColor(dataSet.getIncreasingColor());
                    }
                    this.mRenderPaint.setStyle(dataSet.getIncreasingPaintStyle());
                    c.drawRect(leftBody, open, rightBody, close, this.mRenderPaint);
                } else {
                    this.mRenderPaint.setColor(dataSet.getShadowColor());
                    c.drawLine(leftBody, open, rightBody, close, this.mRenderPaint);
                }
            }
            j += 4;
        }
    }

    public void drawValues(Canvas c) {
        if (((float) this.mChart.getCandleData().getYValCount()) < ((float) this.mChart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleX()) {
            List<CandleDataSet> dataSets = this.mChart.getCandleData().getDataSets();
            for (int i = 0; i < dataSets.size(); i++) {
                CandleDataSet dataSet = (CandleDataSet) dataSets.get(i);
                if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                    applyValueTextStyle(dataSet);
                    Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                    List<CandleEntry> entries = dataSet.getYVals();
                    int minx = Math.max(this.mMinX, 0);
                    float[] positions = trans.generateTransformedValuesCandle(entries, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), minx, Math.min(this.mMaxX + 1, entries.size()));
                    float yOffset = Utils.convertDpToPixel(5.0f);
                    for (int j = 0; j < positions.length; j += 2) {
                        float x = positions[j];
                        float y = positions[j + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(x)) {
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsLeft(x) && this.mViewPortHandler.isInBoundsY(y)) {
                            CandleEntry entry = (CandleEntry) entries.get((j / 2) + minx);
                            drawValue(c, dataSet.getValueFormatter(), entry.getHigh(), entry, i, x, y - yOffset);
                        }
                    }
                }
            }
        }
    }

    public void drawExtras(Canvas c) {
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        for (int i = 0; i < indices.length; i++) {
            int xIndex = indices[i].getXIndex();
            CandleDataSet set = (CandleDataSet) this.mChart.getCandleData().getDataSetByIndex(indices[i].getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                CandleEntry e = (CandleEntry) set.getEntryForXIndex(xIndex);
                if (e != null && e.getXIndex() == xIndex) {
                    float y = ((e.getLow() * this.mAnimator.getPhaseY()) + (e.getHigh() * this.mAnimator.getPhaseY())) / 2.0f;
                    float min = this.mChart.getYChartMin();
                    float max = this.mChart.getYChartMax();
                    float[] pts = new float[]{(float) xIndex, y};
                    this.mChart.getTransformer(set.getAxisDependency()).pointValuesToPixel(pts);
                    drawHighlightLines(c, pts, set);
                }
            }
        }
    }
}
