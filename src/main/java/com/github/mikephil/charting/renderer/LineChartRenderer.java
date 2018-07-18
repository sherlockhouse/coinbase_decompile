package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.CircleBuffer;
import com.github.mikephil.charting.buffer.LineBuffer;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.LineDataProvider;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class LineChartRenderer extends LineScatterCandleRadarRenderer {
    protected Path cubicFillPath = new Path();
    protected Path cubicPath = new Path();
    protected Canvas mBitmapCanvas;
    protected LineDataProvider mChart;
    protected CircleBuffer[] mCircleBuffers;
    protected Paint mCirclePaintInner;
    protected Bitmap mDrawBitmap;
    protected LineBuffer[] mLineBuffers;

    public LineChartRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
        this.mCirclePaintInner = new Paint(1);
        this.mCirclePaintInner.setStyle(Style.FILL);
        this.mCirclePaintInner.setColor(-1);
    }

    public void initBuffers() {
        LineData lineData = this.mChart.getLineData();
        this.mLineBuffers = new LineBuffer[lineData.getDataSetCount()];
        this.mCircleBuffers = new CircleBuffer[lineData.getDataSetCount()];
        for (int i = 0; i < this.mLineBuffers.length; i++) {
            LineDataSet set = (LineDataSet) lineData.getDataSetByIndex(i);
            this.mLineBuffers[i] = new LineBuffer((set.getEntryCount() * 4) - 4);
            this.mCircleBuffers[i] = new CircleBuffer(set.getEntryCount() * 2);
        }
    }

    public void drawData(Canvas c) {
        int width = (int) this.mViewPortHandler.getChartWidth();
        int height = (int) this.mViewPortHandler.getChartHeight();
        if (!(this.mDrawBitmap != null && this.mDrawBitmap.getWidth() == width && this.mDrawBitmap.getHeight() == height)) {
            if (width > 0 && height > 0) {
                this.mDrawBitmap = Bitmap.createBitmap(width, height, Config.ARGB_4444);
                this.mBitmapCanvas = new Canvas(this.mDrawBitmap);
            } else {
                return;
            }
        }
        this.mDrawBitmap.eraseColor(0);
        for (LineDataSet set : this.mChart.getLineData().getDataSets()) {
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set);
            }
        }
        c.drawBitmap(this.mDrawBitmap, 0.0f, 0.0f, this.mRenderPaint);
    }

    protected void drawDataSet(Canvas c, LineDataSet dataSet) {
        List<Entry> entries = dataSet.getYVals();
        if (entries.size() >= 1) {
            this.mRenderPaint.setStrokeWidth(dataSet.getLineWidth());
            this.mRenderPaint.setPathEffect(dataSet.getDashPathEffect());
            if (dataSet.isDrawCubicEnabled()) {
                drawCubic(c, dataSet, entries);
            } else {
                drawLinear(c, dataSet, entries);
            }
            this.mRenderPaint.setPathEffect(null);
        }
    }

    protected void drawCubic(Canvas c, LineDataSet dataSet, List<Entry> entries) {
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        Entry entryFrom = dataSet.getEntryForXIndex(this.mMinX);
        Entry entryTo = dataSet.getEntryForXIndex(this.mMaxX);
        int minx = Math.max(dataSet.getEntryPosition(entryFrom) - (entryFrom == entryTo ? 1 : 0), 0);
        int maxx = Math.min(Math.max(minx + 2, dataSet.getEntryPosition(entryTo) + 1), entries.size());
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float intensity = dataSet.getCubicIntensity();
        this.cubicPath.reset();
        int size = (int) Math.ceil((double) ((((float) (maxx - minx)) * phaseX) + ((float) minx)));
        if (size - minx >= 2) {
            Entry prevPrev = (Entry) entries.get(minx);
            Entry prev = (Entry) entries.get(minx);
            Entry cur = (Entry) entries.get(minx);
            Entry next = (Entry) entries.get(minx + 1);
            this.cubicPath.moveTo((float) cur.getXIndex(), cur.getVal() * phaseY);
            this.cubicPath.cubicTo(((float) prev.getXIndex()) + (((float) (cur.getXIndex() - prev.getXIndex())) * intensity), (prev.getVal() + ((cur.getVal() - prev.getVal()) * intensity)) * phaseY, ((float) cur.getXIndex()) - (((float) (next.getXIndex() - cur.getXIndex())) * intensity), (cur.getVal() - ((next.getVal() - cur.getVal()) * intensity)) * phaseY, (float) cur.getXIndex(), cur.getVal() * phaseY);
            int j = minx + 1;
            int count = Math.min(size, entries.size() - 1);
            while (j < count) {
                prevPrev = (Entry) entries.get(j == 1 ? 0 : j - 2);
                prev = (Entry) entries.get(j - 1);
                cur = (Entry) entries.get(j);
                next = (Entry) entries.get(j + 1);
                this.cubicPath.cubicTo(((float) prev.getXIndex()) + (((float) (cur.getXIndex() - prevPrev.getXIndex())) * intensity), (prev.getVal() + ((cur.getVal() - prevPrev.getVal()) * intensity)) * phaseY, ((float) cur.getXIndex()) - (((float) (next.getXIndex() - prev.getXIndex())) * intensity), (cur.getVal() - ((next.getVal() - prev.getVal()) * intensity)) * phaseY, (float) cur.getXIndex(), cur.getVal() * phaseY);
                j++;
            }
            if (size > entries.size() - 1) {
                prevPrev = (Entry) entries.get(entries.size() >= 3 ? entries.size() - 3 : entries.size() - 2);
                prev = (Entry) entries.get(entries.size() - 2);
                cur = (Entry) entries.get(entries.size() - 1);
                next = cur;
                this.cubicPath.cubicTo(((float) prev.getXIndex()) + (((float) (cur.getXIndex() - prevPrev.getXIndex())) * intensity), (prev.getVal() + ((cur.getVal() - prevPrev.getVal()) * intensity)) * phaseY, ((float) cur.getXIndex()) - (((float) (next.getXIndex() - prev.getXIndex())) * intensity), (cur.getVal() - ((next.getVal() - prev.getVal()) * intensity)) * phaseY, (float) cur.getXIndex(), cur.getVal() * phaseY);
            }
        }
        if (dataSet.isDrawFilledEnabled()) {
            this.cubicFillPath.reset();
            this.cubicFillPath.addPath(this.cubicPath);
            drawCubicFill(this.mBitmapCanvas, dataSet, this.cubicFillPath, trans, entryFrom.getXIndex(), entryFrom.getXIndex() + size);
        }
        this.mRenderPaint.setColor(dataSet.getColor());
        this.mRenderPaint.setStyle(Style.STROKE);
        trans.pathValueToPixel(this.cubicPath);
        this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    protected void drawCubicFill(Canvas c, LineDataSet dataSet, Path spline, Transformer trans, int from, int to) {
        if (to - from > 1) {
            float fillMin = dataSet.getFillFormatter().getFillLinePosition(dataSet, this.mChart);
            spline.lineTo((float) (to - 1), fillMin);
            spline.lineTo((float) from, fillMin);
            spline.close();
            trans.pathValueToPixel(spline);
            drawFilledPath(c, spline, dataSet.getFillColor(), dataSet.getFillAlpha());
        }
    }

    protected void drawLinear(Canvas c, LineDataSet dataSet, List<Entry> entries) {
        Canvas canvas;
        int dataSetIndex = this.mChart.getLineData().getIndexOfDataSet(dataSet);
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        this.mRenderPaint.setStyle(Style.STROKE);
        if (dataSet.isDashedLineEnabled()) {
            canvas = this.mBitmapCanvas;
        } else {
            canvas = c;
        }
        Entry entryFrom = dataSet.getEntryForXIndex(this.mMinX);
        Entry entryTo = dataSet.getEntryForXIndex(this.mMaxX);
        int minx = Math.max(dataSet.getEntryPosition(entryFrom) - (entryFrom == entryTo ? 1 : 0), 0);
        int maxx = Math.min(Math.max(minx + 2, dataSet.getEntryPosition(entryTo) + 1), entries.size());
        int range = ((maxx - minx) * 4) - 4;
        LineBuffer buffer = this.mLineBuffers[dataSetIndex];
        buffer.setPhases(phaseX, phaseY);
        buffer.limitFrom(minx);
        buffer.limitTo(maxx);
        buffer.feed(entries);
        trans.pointValuesToPixel(buffer.buffer);
        if (dataSet.getColors().size() > 1) {
            int j = 0;
            while (j < range && this.mViewPortHandler.isInBoundsRight(buffer.buffer[j])) {
                if (this.mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2]) && ((this.mViewPortHandler.isInBoundsTop(buffer.buffer[j + 1]) || this.mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 3])) && (this.mViewPortHandler.isInBoundsTop(buffer.buffer[j + 1]) || this.mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 3])))) {
                    this.mRenderPaint.setColor(dataSet.getColor((j / 4) + minx));
                    canvas.drawLine(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mRenderPaint);
                }
                j += 4;
            }
        } else {
            this.mRenderPaint.setColor(dataSet.getColor());
            canvas.drawLines(buffer.buffer, 0, range, this.mRenderPaint);
        }
        this.mRenderPaint.setPathEffect(null);
        if (dataSet.isDrawFilledEnabled() && entries.size() > 0) {
            drawLinearFill(c, dataSet, entries, minx, maxx, trans);
        }
    }

    protected void drawLinearFill(Canvas c, LineDataSet dataSet, List<Entry> entries, int minx, int maxx, Transformer trans) {
        Path filled = generateFilledPath(entries, dataSet.getFillFormatter().getFillLinePosition(dataSet, this.mChart), minx, maxx);
        trans.pathValueToPixel(filled);
        drawFilledPath(c, filled, dataSet.getFillColor(), dataSet.getFillAlpha());
    }

    protected void drawFilledPath(Canvas c, Path filledPath, int fillColor, int fillAlpha) {
        c.save();
        c.clipPath(filledPath);
        c.drawColor((fillAlpha << 24) | (16777215 & fillColor));
        c.restore();
    }

    private Path generateFilledPath(List<Entry> entries, float fillMin, int from, int to) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        Path filled = new Path();
        filled.moveTo((float) ((Entry) entries.get(from)).getXIndex(), fillMin);
        filled.lineTo((float) ((Entry) entries.get(from)).getXIndex(), ((Entry) entries.get(from)).getVal() * phaseY);
        int count = (int) Math.ceil((double) ((((float) (to - from)) * phaseX) + ((float) from)));
        for (int x = from + 1; x < count; x++) {
            Entry e = (Entry) entries.get(x);
            filled.lineTo((float) e.getXIndex(), e.getVal() * phaseY);
        }
        filled.lineTo((float) ((Entry) entries.get(Math.max(Math.min(((int) Math.ceil((double) ((((float) (to - from)) * phaseX) + ((float) from)))) - 1, entries.size() - 1), 0))).getXIndex(), fillMin);
        filled.close();
        return filled;
    }

    public void drawValues(Canvas c) {
        if (((float) this.mChart.getLineData().getYValCount()) < ((float) this.mChart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleX()) {
            List<LineDataSet> dataSets = this.mChart.getLineData().getDataSets();
            for (int i = 0; i < dataSets.size(); i++) {
                DataSet dataSet = (LineDataSet) dataSets.get(i);
                if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                    applyValueTextStyle(dataSet);
                    Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                    int valOffset = (int) (dataSet.getCircleSize() * 1.75f);
                    if (!dataSet.isDrawCirclesEnabled()) {
                        valOffset /= 2;
                    }
                    List<Entry> entries = dataSet.getYVals();
                    Entry entryFrom = dataSet.getEntryForXIndex(this.mMinX);
                    Entry entryTo = dataSet.getEntryForXIndex(this.mMaxX);
                    int minx = Math.max(dataSet.getEntryPosition(entryFrom) - (entryFrom == entryTo ? 1 : 0), 0);
                    float[] positions = trans.generateTransformedValuesLine(entries, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), minx, Math.min(Math.max(minx + 2, dataSet.getEntryPosition(entryTo) + 1), entries.size()));
                    for (int j = 0; j < positions.length; j += 2) {
                        float x = positions[j];
                        float y = positions[j + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(x)) {
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsLeft(x) && this.mViewPortHandler.isInBoundsY(y)) {
                            Entry entry = (Entry) entries.get((j / 2) + minx);
                            drawValue(c, dataSet.getValueFormatter(), entry.getVal(), entry, i, x, y - ((float) valOffset));
                        }
                    }
                }
            }
        }
    }

    public void drawExtras(Canvas c) {
        drawCircles(c);
    }

    protected void drawCircles(Canvas c) {
        this.mRenderPaint.setStyle(Style.FILL);
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        List<LineDataSet> dataSets = this.mChart.getLineData().getDataSets();
        for (int i = 0; i < dataSets.size(); i++) {
            LineDataSet dataSet = (LineDataSet) dataSets.get(i);
            if (dataSet.isVisible() && dataSet.isDrawCirclesEnabled() && dataSet.getEntryCount() != 0) {
                this.mCirclePaintInner.setColor(dataSet.getCircleHoleColor());
                Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                List<Entry> entries = dataSet.getYVals();
                Entry entryFrom = dataSet.getEntryForXIndex(this.mMinX < 0 ? 0 : this.mMinX);
                Entry entryTo = dataSet.getEntryForXIndex(this.mMaxX);
                int minx = Math.max(dataSet.getEntryPosition(entryFrom) - (entryFrom == entryTo ? 1 : 0), 0);
                int maxx = Math.min(Math.max(minx + 2, dataSet.getEntryPosition(entryTo) + 1), entries.size());
                CircleBuffer buffer = this.mCircleBuffers[i];
                buffer.setPhases(phaseX, phaseY);
                buffer.limitFrom(minx);
                buffer.limitTo(maxx);
                buffer.feed(entries);
                trans.pointValuesToPixel(buffer.buffer);
                float halfsize = dataSet.getCircleSize() / 2.0f;
                int count = ((int) Math.ceil((double) ((((float) (maxx - minx)) * phaseX) + ((float) minx)))) * 2;
                for (int j = 0; j < count; j += 2) {
                    float x = buffer.buffer[j];
                    float y = buffer.buffer[j + 1];
                    if (!this.mViewPortHandler.isInBoundsRight(x)) {
                        break;
                    }
                    if (this.mViewPortHandler.isInBoundsLeft(x) && this.mViewPortHandler.isInBoundsY(y)) {
                        int circleColor = dataSet.getCircleColor((j / 2) + minx);
                        this.mRenderPaint.setColor(circleColor);
                        c.drawCircle(x, y, dataSet.getCircleSize(), this.mRenderPaint);
                        if (dataSet.isDrawCircleHoleEnabled() && circleColor != this.mCirclePaintInner.getColor()) {
                            c.drawCircle(x, y, halfsize, this.mCirclePaintInner);
                        }
                    }
                }
            }
        }
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        for (int i = 0; i < indices.length; i++) {
            LineDataSet set = (LineDataSet) this.mChart.getLineData().getDataSetByIndex(indices[i].getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                int xIndex = indices[i].getXIndex();
                if (((float) xIndex) <= this.mChart.getXChartMax() * this.mAnimator.getPhaseX()) {
                    float yVal = set.getYValForXIndex(xIndex);
                    if (yVal != Float.NaN) {
                        float y = yVal * this.mAnimator.getPhaseY();
                        float[] pts = new float[]{(float) xIndex, y};
                        this.mChart.getTransformer(set.getAxisDependency()).pointValuesToPixel(pts);
                        drawHighlightLines(c, pts, set);
                    }
                }
            }
        }
    }

    public void releaseBitmap() {
        if (this.mDrawBitmap != null) {
            this.mDrawBitmap.recycle();
            this.mDrawBitmap = null;
        }
    }
}
