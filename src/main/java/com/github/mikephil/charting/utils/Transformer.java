package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import java.util.List;

public class Transformer {
    private Matrix mMBuffer1 = new Matrix();
    private Matrix mMBuffer2 = new Matrix();
    protected Matrix mMatrixOffset = new Matrix();
    protected Matrix mMatrixValueToPx = new Matrix();
    protected ViewPortHandler mViewPortHandler;

    public Transformer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
    }

    public void prepareMatrixValuePx(float xChartMin, float deltaX, float deltaY, float yChartMin) {
        float scaleX = this.mViewPortHandler.contentWidth() / deltaX;
        float scaleY = this.mViewPortHandler.contentHeight() / deltaY;
        this.mMatrixValueToPx.reset();
        this.mMatrixValueToPx.postTranslate(-xChartMin, -yChartMin);
        this.mMatrixValueToPx.postScale(scaleX, -scaleY);
    }

    public void prepareMatrixOffset(boolean inverted) {
        this.mMatrixOffset.reset();
        if (inverted) {
            this.mMatrixOffset.setTranslate(this.mViewPortHandler.offsetLeft(), -this.mViewPortHandler.offsetTop());
            this.mMatrixOffset.postScale(1.0f, -1.0f);
            return;
        }
        this.mMatrixOffset.postTranslate(this.mViewPortHandler.offsetLeft(), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
    }

    public float[] generateTransformedValuesScatter(List<? extends Entry> entries, float phaseY) {
        float[] valuePoints = new float[(entries.size() * 2)];
        for (int j = 0; j < valuePoints.length; j += 2) {
            Entry e = (Entry) entries.get(j / 2);
            if (e != null) {
                valuePoints[j] = (float) e.getXIndex();
                valuePoints[j + 1] = e.getVal() * phaseY;
            }
        }
        getValueToPixelMatrix().mapPoints(valuePoints);
        return valuePoints;
    }

    public float[] generateTransformedValuesBubble(List<? extends Entry> entries, float phaseX, float phaseY, int from, int to) {
        int count = ((int) Math.ceil((double) (to - from))) * 2;
        float[] valuePoints = new float[count];
        for (int j = 0; j < count; j += 2) {
            Entry e = (Entry) entries.get((j / 2) + from);
            if (e != null) {
                valuePoints[j] = (((float) (e.getXIndex() - from)) * phaseX) + ((float) from);
                valuePoints[j + 1] = e.getVal() * phaseY;
            }
        }
        getValueToPixelMatrix().mapPoints(valuePoints);
        return valuePoints;
    }

    public float[] generateTransformedValuesLine(List<? extends Entry> entries, float phaseX, float phaseY, int from, int to) {
        int count = ((int) Math.ceil((double) (((float) (to - from)) * phaseX))) * 2;
        float[] valuePoints = new float[count];
        for (int j = 0; j < count; j += 2) {
            Entry e = (Entry) entries.get((j / 2) + from);
            if (e != null) {
                valuePoints[j] = (float) e.getXIndex();
                valuePoints[j + 1] = e.getVal() * phaseY;
            }
        }
        getValueToPixelMatrix().mapPoints(valuePoints);
        return valuePoints;
    }

    public float[] generateTransformedValuesCandle(List<CandleEntry> entries, float phaseX, float phaseY, int from, int to) {
        int count = ((int) Math.ceil((double) (((float) (to - from)) * phaseX))) * 2;
        float[] valuePoints = new float[count];
        for (int j = 0; j < count; j += 2) {
            CandleEntry e = (CandleEntry) entries.get((j / 2) + from);
            if (e != null) {
                valuePoints[j] = (float) e.getXIndex();
                valuePoints[j + 1] = e.getHigh() * phaseY;
            }
        }
        getValueToPixelMatrix().mapPoints(valuePoints);
        return valuePoints;
    }

    public float[] generateTransformedValuesBarChart(List<? extends Entry> entries, int dataSet, BarData bd, float phaseY) {
        float[] valuePoints = new float[(entries.size() * 2)];
        int setCount = bd.getDataSetCount();
        float space = bd.getGroupSpace();
        for (int j = 0; j < valuePoints.length; j += 2) {
            Entry e = (Entry) entries.get(j / 2);
            int i = e.getXIndex();
            float x = (((float) ((e.getXIndex() + ((setCount - 1) * i)) + dataSet)) + (((float) i) * space)) + (space / 2.0f);
            float y = e.getVal();
            valuePoints[j] = x;
            valuePoints[j + 1] = y * phaseY;
        }
        getValueToPixelMatrix().mapPoints(valuePoints);
        return valuePoints;
    }

    public float[] generateTransformedValuesHorizontalBarChart(List<? extends Entry> entries, int dataSet, BarData bd, float phaseY) {
        float[] valuePoints = new float[(entries.size() * 2)];
        int setCount = bd.getDataSetCount();
        float space = bd.getGroupSpace();
        for (int j = 0; j < valuePoints.length; j += 2) {
            Entry e = (Entry) entries.get(j / 2);
            int i = e.getXIndex();
            float x = (((float) ((((setCount - 1) * i) + i) + dataSet)) + (((float) i) * space)) + (space / 2.0f);
            valuePoints[j] = e.getVal() * phaseY;
            valuePoints[j + 1] = x;
        }
        getValueToPixelMatrix().mapPoints(valuePoints);
        return valuePoints;
    }

    public void pathValueToPixel(Path path) {
        path.transform(this.mMatrixValueToPx);
        path.transform(this.mViewPortHandler.getMatrixTouch());
        path.transform(this.mMatrixOffset);
    }

    public void pointValuesToPixel(float[] pts) {
        this.mMatrixValueToPx.mapPoints(pts);
        this.mViewPortHandler.getMatrixTouch().mapPoints(pts);
        this.mMatrixOffset.mapPoints(pts);
    }

    public void rectValueToPixel(RectF r, float phaseY) {
        r.top *= phaseY;
        r.bottom *= phaseY;
        this.mMatrixValueToPx.mapRect(r);
        this.mViewPortHandler.getMatrixTouch().mapRect(r);
        this.mMatrixOffset.mapRect(r);
    }

    public void rectValueToPixelHorizontal(RectF r, float phaseY) {
        r.left *= phaseY;
        r.right *= phaseY;
        this.mMatrixValueToPx.mapRect(r);
        this.mViewPortHandler.getMatrixTouch().mapRect(r);
        this.mMatrixOffset.mapRect(r);
    }

    public void pixelsToValue(float[] pixels) {
        Matrix tmp = new Matrix();
        this.mMatrixOffset.invert(tmp);
        tmp.mapPoints(pixels);
        this.mViewPortHandler.getMatrixTouch().invert(tmp);
        tmp.mapPoints(pixels);
        this.mMatrixValueToPx.invert(tmp);
        tmp.mapPoints(pixels);
    }

    public PointD getValuesByTouchPoint(float x, float y) {
        float[] pts = new float[]{x, y};
        pixelsToValue(pts);
        return new PointD((double) pts[0], (double) pts[1]);
    }

    public Matrix getValueToPixelMatrix() {
        this.mMBuffer1.set(this.mMatrixValueToPx);
        this.mMBuffer1.postConcat(this.mViewPortHandler.mMatrixTouch);
        this.mMBuffer1.postConcat(this.mMatrixOffset);
        return this.mMBuffer1;
    }

    public Matrix getPixelToValueMatrix() {
        getValueToPixelMatrix().invert(this.mMBuffer2);
        return this.mMBuffer2;
    }
}
