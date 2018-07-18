package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;
import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class PieChartRenderer extends DataRenderer {
    protected Canvas mBitmapCanvas;
    private RectF mCenterTextLastBounds = new RectF();
    private SpannableString mCenterTextLastValue;
    private StaticLayout mCenterTextLayout;
    private TextPaint mCenterTextPaint;
    protected PieChart mChart;
    protected Bitmap mDrawBitmap;
    protected Paint mHolePaint;
    private RectF[] mRectBuffer = new RectF[]{new RectF(), new RectF(), new RectF()};
    protected Paint mTransparentCirclePaint;

    public PieChartRenderer(PieChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
        this.mHolePaint = new Paint(1);
        this.mHolePaint.setColor(-1);
        this.mHolePaint.setStyle(Style.FILL);
        this.mTransparentCirclePaint = new Paint(1);
        this.mTransparentCirclePaint.setColor(-1);
        this.mTransparentCirclePaint.setStyle(Style.FILL);
        this.mTransparentCirclePaint.setAlpha(105);
        this.mCenterTextPaint = new TextPaint(1);
        this.mCenterTextPaint.setColor(-16777216);
        this.mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValuePaint.setColor(-1);
        this.mValuePaint.setTextAlign(Align.CENTER);
    }

    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    public void initBuffers() {
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
        for (PieDataSet set : ((PieData) this.mChart.getData()).getDataSets()) {
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set);
            }
        }
    }

    protected void drawDataSet(Canvas c, PieDataSet dataSet) {
        float angle = this.mChart.getRotationAngle();
        List<Entry> entries = dataSet.getYVals();
        float[] drawAngles = this.mChart.getDrawAngles();
        for (int j = 0; j < entries.size(); j++) {
            float newangle = drawAngles[j];
            float sliceSpace = dataSet.getSliceSpace();
            Entry e = (Entry) entries.get(j);
            if (((double) Math.abs(e.getVal())) > 1.0E-6d && !this.mChart.needsHighlight(e.getXIndex(), ((PieData) this.mChart.getData()).getIndexOfDataSet(dataSet))) {
                this.mRenderPaint.setColor(dataSet.getColor(j));
                this.mBitmapCanvas.drawArc(this.mChart.getCircleBox(), ((sliceSpace / 2.0f) + angle) * this.mAnimator.getPhaseY(), (newangle - (sliceSpace / 2.0f)) * this.mAnimator.getPhaseY(), true, this.mRenderPaint);
            }
            angle += this.mAnimator.getPhaseX() * newangle;
        }
    }

    public void drawValues(Canvas c) {
        PointF center = this.mChart.getCenterCircleBox();
        float r = this.mChart.getRadius();
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles = this.mChart.getDrawAngles();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        float off = (r / 10.0f) * 3.6f;
        if (this.mChart.isDrawHoleEnabled()) {
            off = (r - ((r / 100.0f) * this.mChart.getHoleRadius())) / 2.0f;
        }
        r -= off;
        PieData data = (PieData) this.mChart.getData();
        List<PieDataSet> dataSets = data.getDataSets();
        boolean drawXVals = this.mChart.isDrawSliceTextEnabled();
        int cnt = 0;
        for (int i = 0; i < dataSets.size(); i++) {
            DataSet dataSet = (PieDataSet) dataSets.get(i);
            if (dataSet.isDrawValuesEnabled() || drawXVals) {
                applyValueTextStyle(dataSet);
                float lineHeight = ((float) Utils.calcTextHeight(this.mValuePaint, "Q")) + Utils.convertDpToPixel(4.0f);
                List<Entry> entries = dataSet.getYVals();
                int maxEntry = Math.min((int) Math.ceil((double) (((float) entries.size()) * this.mAnimator.getPhaseX())), entries.size());
                for (int j = 0; j < maxEntry; j++) {
                    Entry entry = (Entry) entries.get(j);
                    float offset = drawAngles[cnt] / 2.0f;
                    float x = (float) ((((double) r) * Math.cos(Math.toRadians((double) (((absoluteAngles[cnt] + rotationAngle) - offset) * this.mAnimator.getPhaseY())))) + ((double) center.x));
                    float y = (float) ((((double) r) * Math.sin(Math.toRadians((double) (((absoluteAngles[cnt] + rotationAngle) - offset) * this.mAnimator.getPhaseY())))) + ((double) center.y));
                    float value = this.mChart.isUsePercentValuesEnabled() ? (entry.getVal() / data.getYValueSum()) * 100.0f : entry.getVal();
                    ValueFormatter formatter = dataSet.getValueFormatter();
                    boolean drawYVals = dataSet.isDrawValuesEnabled();
                    if (drawXVals && drawYVals) {
                        drawValue(c, formatter, value, entry, 0, x, y);
                        if (j < data.getXValCount()) {
                            c.drawText((String) data.getXVals().get(j), x, y + lineHeight, this.mValuePaint);
                        }
                    } else if (!drawXVals || drawYVals) {
                        if (!drawXVals && drawYVals) {
                            drawValue(c, formatter, value, entry, 0, x, y + (lineHeight / 2.0f));
                        }
                    } else if (j < data.getXValCount()) {
                        c.drawText((String) data.getXVals().get(j), x, (lineHeight / 2.0f) + y, this.mValuePaint);
                    }
                    cnt++;
                }
            }
        }
    }

    public void drawExtras(Canvas c) {
        drawHole(c);
        c.drawBitmap(this.mDrawBitmap, 0.0f, 0.0f, this.mRenderPaint);
        drawCenterText(c);
    }

    protected void drawHole(Canvas c) {
        if (this.mChart.isDrawHoleEnabled()) {
            float transparentCircleRadius = this.mChart.getTransparentCircleRadius();
            float holeRadius = this.mChart.getHoleRadius();
            float radius = this.mChart.getRadius();
            PointF center = this.mChart.getCenterCircleBox();
            if (transparentCircleRadius > holeRadius) {
                int alpha = this.mTransparentCirclePaint.getAlpha();
                this.mTransparentCirclePaint.setAlpha((int) ((((float) alpha) * this.mAnimator.getPhaseX()) * this.mAnimator.getPhaseY()));
                this.mBitmapCanvas.drawCircle(center.x, center.y, (radius / 100.0f) * transparentCircleRadius, this.mTransparentCirclePaint);
                this.mTransparentCirclePaint.setAlpha(alpha);
            }
            this.mBitmapCanvas.drawCircle(center.x, center.y, (radius / 100.0f) * holeRadius, this.mHolePaint);
        }
    }

    protected void drawCenterText(Canvas c) {
        SpannableString centerText = this.mChart.getCenterText();
        if (this.mChart.isDrawCenterTextEnabled() && centerText != null) {
            PointF center = this.mChart.getCenterCircleBox();
            float innerRadius = (this.mChart.isDrawHoleEnabled() && this.mChart.isHoleTransparent()) ? this.mChart.getRadius() * (this.mChart.getHoleRadius() / 100.0f) : this.mChart.getRadius();
            RectF holeRect = this.mRectBuffer[0];
            holeRect.left = center.x - innerRadius;
            holeRect.top = center.y - innerRadius;
            holeRect.right = center.x + innerRadius;
            holeRect.bottom = center.y + innerRadius;
            RectF boundingRect = this.mRectBuffer[1];
            boundingRect.set(holeRect);
            float radiusPercent = this.mChart.getCenterTextRadiusPercent();
            if (((double) radiusPercent) > 0.0d) {
                boundingRect.inset((boundingRect.width() - (boundingRect.width() * radiusPercent)) / 2.0f, (boundingRect.height() - (boundingRect.height() * radiusPercent)) / 2.0f);
            }
            if (!(centerText.equals(this.mCenterTextLastValue) && boundingRect.equals(this.mCenterTextLastBounds))) {
                this.mCenterTextLastBounds.set(boundingRect);
                this.mCenterTextLastValue = centerText;
                this.mCenterTextLayout = new StaticLayout(centerText, 0, centerText.length(), this.mCenterTextPaint, (int) Math.max(Math.ceil((double) this.mCenterTextLastBounds.width()), 1.0d), Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            }
            float layoutHeight = (float) this.mCenterTextLayout.getHeight();
            c.save();
            c.translate(boundingRect.left, boundingRect.top + ((boundingRect.height() - layoutHeight) / 2.0f));
            this.mCenterTextLayout.draw(c);
            c.restore();
        }
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles = this.mChart.getDrawAngles();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        for (int i = 0; i < indices.length; i++) {
            int xIndex = indices[i].getXIndex();
            if (xIndex < drawAngles.length) {
                PieDataSet set = ((PieData) this.mChart.getData()).getDataSetByIndex(indices[i].getDataSetIndex());
                if (set != null && set.isHighlightEnabled()) {
                    float angle;
                    if (xIndex == 0) {
                        angle = rotationAngle;
                    } else {
                        angle = rotationAngle + absoluteAngles[xIndex - 1];
                    }
                    angle *= this.mAnimator.getPhaseY();
                    float sliceDegrees = drawAngles[xIndex];
                    float shift = set.getSelectionShift();
                    RectF circleBox = this.mChart.getCircleBox();
                    RectF highlighted = new RectF(circleBox.left - shift, circleBox.top - shift, circleBox.right + shift, circleBox.bottom + shift);
                    this.mRenderPaint.setColor(set.getColor(xIndex));
                    this.mBitmapCanvas.drawArc(highlighted, (set.getSliceSpace() / 2.0f) + angle, (this.mAnimator.getPhaseY() * sliceDegrees) - (set.getSliceSpace() / 2.0f), true, this.mRenderPaint);
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
