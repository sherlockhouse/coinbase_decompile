package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.BarDataProvider;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class HorizontalBarChartRenderer extends BarChartRenderer {
    public HorizontalBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
        this.mValuePaint.setTextAlign(Align.LEFT);
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            BarDataSet set = (BarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new HorizontalBarBuffer((set.getValueCount() * 4) * set.getStackSize(), barData.getGroupSpace(), barData.getDataSetCount(), set.isStacked());
        }
    }

    protected void drawDataSet(Canvas c, BarDataSet dataSet, int index) {
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mShadowPaint.setColor(dataSet.getBarShadowColor());
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        List<BarEntry> entries = dataSet.getYVals();
        BarBuffer buffer = this.mBarBuffers[index];
        buffer.setPhases(phaseX, phaseY);
        buffer.setBarSpace(dataSet.getBarSpace());
        buffer.setDataSet(index);
        buffer.setInverted(this.mChart.isInverted(dataSet.getAxisDependency()));
        buffer.feed(entries);
        trans.pointValuesToPixel(buffer.buffer);
        int j = 0;
        while (j < buffer.size() && this.mViewPortHandler.isInBoundsTop(buffer.buffer[j + 3])) {
            if (this.mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 1])) {
                if (this.mChart.isDrawBarShadowEnabled()) {
                    c.drawRect(this.mViewPortHandler.contentLeft(), buffer.buffer[j + 1], this.mViewPortHandler.contentRight(), buffer.buffer[j + 3], this.mShadowPaint);
                }
                this.mRenderPaint.setColor(dataSet.getColor(j / 4));
                c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mRenderPaint);
            }
            j += 4;
        }
    }

    public void drawValues(Canvas c) {
        if (passesCheck()) {
            List<BarDataSet> dataSets = this.mChart.getBarData().getDataSets();
            float valueOffsetPlus = Utils.convertDpToPixel(5.0f);
            boolean drawValueAboveBar = this.mChart.isDrawValueAboveBarEnabled();
            for (int i = 0; i < this.mChart.getBarData().getDataSetCount(); i++) {
                BarDataSet dataSet = (BarDataSet) dataSets.get(i);
                if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                    boolean isInverted = this.mChart.isInverted(dataSet.getAxisDependency());
                    applyValueTextStyle(dataSet);
                    float halfTextHeight = ((float) Utils.calcTextHeight(this.mValuePaint, "10")) / 2.0f;
                    ValueFormatter formatter = dataSet.getValueFormatter();
                    Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                    List<BarEntry> entries = dataSet.getYVals();
                    float[] valuePoints = getTransformedValues(trans, entries, i);
                    int j;
                    BarEntry e;
                    String formattedValue;
                    float valueTextWidth;
                    float posOffset;
                    float negOffset;
                    float f;
                    float f2;
                    float val;
                    if (dataSet.isStacked()) {
                        j = 0;
                        while (((float) j) < ((float) (valuePoints.length - 1)) * this.mAnimator.getPhaseX()) {
                            e = (BarEntry) entries.get(j / 2);
                            float[] vals = e.getVals();
                            if (vals == null) {
                                if (!this.mViewPortHandler.isInBoundsTop(valuePoints[j + 1])) {
                                    break;
                                } else if (this.mViewPortHandler.isInBoundsX(valuePoints[j]) && this.mViewPortHandler.isInBoundsBottom(valuePoints[j + 1])) {
                                    formattedValue = formatter.getFormattedValue(e.getVal(), e, i, this.mViewPortHandler);
                                    valueTextWidth = (float) Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                    posOffset = drawValueAboveBar ? valueOffsetPlus : -(valueTextWidth + valueOffsetPlus);
                                    if (drawValueAboveBar) {
                                        negOffset = -(valueTextWidth + valueOffsetPlus);
                                    } else {
                                        negOffset = valueOffsetPlus;
                                    }
                                    if (isInverted) {
                                        posOffset = (-posOffset) - valueTextWidth;
                                        negOffset = (-negOffset) - valueTextWidth;
                                    }
                                    f = valuePoints[j];
                                    if (e.getVal() >= 0.0f) {
                                        f2 = posOffset;
                                    } else {
                                        f2 = negOffset;
                                    }
                                    drawValue(c, formattedValue, f2 + f, valuePoints[j + 1] + halfTextHeight);
                                }
                            } else {
                                float y;
                                float[] transformed = new float[(vals.length * 2)];
                                float posY = 0.0f;
                                float negY = -e.getNegativeSum();
                                int k = 0;
                                int idx = 0;
                                while (k < transformed.length) {
                                    float value = vals[idx];
                                    if (value >= 0.0f) {
                                        posY += value;
                                        y = posY;
                                    } else {
                                        y = negY;
                                        negY -= value;
                                    }
                                    transformed[k] = this.mAnimator.getPhaseY() * y;
                                    k += 2;
                                    idx++;
                                }
                                trans.pointValuesToPixel(transformed);
                                for (k = 0; k < transformed.length; k += 2) {
                                    val = vals[k / 2];
                                    formattedValue = formatter.getFormattedValue(val, e, i, this.mViewPortHandler);
                                    valueTextWidth = (float) Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                    posOffset = drawValueAboveBar ? valueOffsetPlus : -(valueTextWidth + valueOffsetPlus);
                                    if (drawValueAboveBar) {
                                        negOffset = -(valueTextWidth + valueOffsetPlus);
                                    } else {
                                        negOffset = valueOffsetPlus;
                                    }
                                    if (isInverted) {
                                        posOffset = (-posOffset) - valueTextWidth;
                                        negOffset = (-negOffset) - valueTextWidth;
                                    }
                                    f = transformed[k];
                                    if (val >= 0.0f) {
                                        f2 = posOffset;
                                    } else {
                                        f2 = negOffset;
                                    }
                                    float x = f + f2;
                                    y = valuePoints[j + 1];
                                    if (!this.mViewPortHandler.isInBoundsTop(y)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsX(x) && this.mViewPortHandler.isInBoundsBottom(y)) {
                                        drawValue(c, formattedValue, x, y + halfTextHeight);
                                    }
                                }
                            }
                            j += 2;
                        }
                    } else {
                        j = 0;
                        while (((float) j) < ((float) valuePoints.length) * this.mAnimator.getPhaseX() && this.mViewPortHandler.isInBoundsTop(valuePoints[j + 1])) {
                            if (this.mViewPortHandler.isInBoundsX(valuePoints[j]) && this.mViewPortHandler.isInBoundsBottom(valuePoints[j + 1])) {
                                e = (BarEntry) entries.get(j / 2);
                                val = e.getVal();
                                formattedValue = formatter.getFormattedValue(val, e, i, this.mViewPortHandler);
                                valueTextWidth = (float) Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                posOffset = drawValueAboveBar ? valueOffsetPlus : -(valueTextWidth + valueOffsetPlus);
                                if (drawValueAboveBar) {
                                    negOffset = -(valueTextWidth + valueOffsetPlus);
                                } else {
                                    negOffset = valueOffsetPlus;
                                }
                                if (isInverted) {
                                    posOffset = (-posOffset) - valueTextWidth;
                                    negOffset = (-negOffset) - valueTextWidth;
                                }
                                f = valuePoints[j];
                                if (val >= 0.0f) {
                                    f2 = posOffset;
                                } else {
                                    f2 = negOffset;
                                }
                                drawValue(c, formattedValue, f2 + f, valuePoints[j + 1] + halfTextHeight);
                            }
                            j += 2;
                        }
                    }
                }
            }
        }
    }

    protected void drawValue(Canvas c, String valueText, float x, float y) {
        c.drawText(valueText, x, y, this.mValuePaint);
    }

    protected void prepareBarHighlight(float x, float y1, float y2, float barspaceHalf, Transformer trans) {
        float left = y1;
        float right = y2;
        this.mBarRect.set(left, (x - 0.5f) + barspaceHalf, right, (x + 0.5f) - barspaceHalf);
        trans.rectValueToPixelHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    public float[] getTransformedValues(Transformer trans, List<BarEntry> entries, int dataSetIndex) {
        return trans.generateTransformedValuesHorizontalBarChart(entries, dataSetIndex, this.mChart.getBarData(), this.mAnimator.getPhaseY());
    }

    protected boolean passesCheck() {
        return ((float) this.mChart.getBarData().getYValCount()) < ((float) this.mChart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }
}
