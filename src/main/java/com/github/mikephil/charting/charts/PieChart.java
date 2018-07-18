package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.util.AttributeSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public class PieChart extends PieRadarChartBase<PieData> {
    private float[] mAbsoluteAngles;
    private SpannableString mCenterText = new SpannableString("");
    private float mCenterTextRadiusPercent = 1.0f;
    private RectF mCircleBox = new RectF();
    private float[] mDrawAngles;
    private boolean mDrawCenterText = true;
    private boolean mDrawHole = true;
    private boolean mDrawRoundedSlices = false;
    private boolean mDrawXLabels = true;
    private float mHoleRadiusPercent = 50.0f;
    protected float mTransparentCircleRadiusPercent = 55.0f;
    private boolean mUsePercentValues = false;

    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void init() {
        super.init();
        this.mRenderer = new PieChartRenderer(this, this.mAnimator, this.mViewPortHandler);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mDataNotSet) {
            this.mRenderer.drawData(canvas);
            if (valuesToHighlight()) {
                this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
            }
            this.mRenderer.drawExtras(canvas);
            this.mRenderer.drawValues(canvas);
            this.mLegendRenderer.renderLegend(canvas);
            drawDescription(canvas);
            drawMarkers(canvas);
        }
    }

    public void calculateOffsets() {
        super.calculateOffsets();
        if (!this.mDataNotSet) {
            float radius = getDiameter() / 2.0f;
            PointF c = getCenterOffsets();
            float shift = ((PieData) this.mData).getDataSet().getSelectionShift();
            this.mCircleBox.set((c.x - radius) + shift, (c.y - radius) + shift, (c.x + radius) - shift, (c.y + radius) - shift);
        }
    }

    protected void calcMinMax() {
        super.calcMinMax();
        calcAngles();
    }

    protected float[] getMarkerPosition(Entry e, Highlight highlight) {
        PointF center = getCenterCircleBox();
        float r = getRadius();
        float off = (r / 10.0f) * 3.6f;
        if (isDrawHoleEnabled()) {
            off = (r - ((r / 100.0f) * getHoleRadius())) / 2.0f;
        }
        r -= off;
        float rotationAngle = getRotationAngle();
        int i = e.getXIndex();
        float offset = this.mDrawAngles[i] / 2.0f;
        float x = (float) ((((double) r) * Math.cos(Math.toRadians((double) (((this.mAbsoluteAngles[i] + rotationAngle) - offset) * this.mAnimator.getPhaseY())))) + ((double) center.x));
        float y = (float) ((((double) r) * Math.sin(Math.toRadians((double) (((this.mAbsoluteAngles[i] + rotationAngle) - offset) * this.mAnimator.getPhaseY())))) + ((double) center.y));
        return new float[]{x, y};
    }

    private void calcAngles() {
        this.mDrawAngles = new float[((PieData) this.mData).getYValCount()];
        this.mAbsoluteAngles = new float[((PieData) this.mData).getYValCount()];
        List<PieDataSet> dataSets = ((PieData) this.mData).getDataSets();
        int cnt = 0;
        for (int i = 0; i < ((PieData) this.mData).getDataSetCount(); i++) {
            List<Entry> entries = ((PieDataSet) dataSets.get(i)).getYVals();
            for (int j = 0; j < entries.size(); j++) {
                this.mDrawAngles[cnt] = calcAngle(Math.abs(((Entry) entries.get(j)).getVal()));
                if (cnt == 0) {
                    this.mAbsoluteAngles[cnt] = this.mDrawAngles[cnt];
                } else {
                    this.mAbsoluteAngles[cnt] = this.mAbsoluteAngles[cnt - 1] + this.mDrawAngles[cnt];
                }
                cnt++;
            }
        }
    }

    public boolean needsHighlight(int xIndex, int dataSetIndex) {
        if (!valuesToHighlight() || dataSetIndex < 0) {
            return false;
        }
        int i = 0;
        while (i < this.mIndicesToHighlight.length) {
            if (this.mIndicesToHighlight[i].getXIndex() == xIndex && this.mIndicesToHighlight[i].getDataSetIndex() == dataSetIndex) {
                return true;
            }
            i++;
        }
        return false;
    }

    private float calcAngle(float value) {
        return (value / ((PieData) this.mData).getYValueSum()) * 360.0f;
    }

    public int getIndexForAngle(float angle) {
        float a = Utils.getNormalizedAngle(angle - getRotationAngle());
        for (int i = 0; i < this.mAbsoluteAngles.length; i++) {
            if (this.mAbsoluteAngles[i] > a) {
                return i;
            }
        }
        return -1;
    }

    public float[] getDrawAngles() {
        return this.mDrawAngles;
    }

    public float[] getAbsoluteAngles() {
        return this.mAbsoluteAngles;
    }

    public void setHoleColor(int color) {
        ((PieChartRenderer) this.mRenderer).getPaintHole().setXfermode(null);
        ((PieChartRenderer) this.mRenderer).getPaintHole().setColor(color);
    }

    public void setHoleColorTransparent(boolean enable) {
        if (enable) {
            ((PieChartRenderer) this.mRenderer).getPaintHole().setColor(-1);
            ((PieChartRenderer) this.mRenderer).getPaintHole().setXfermode(new PorterDuffXfermode(Mode.CLEAR));
            return;
        }
        ((PieChartRenderer) this.mRenderer).getPaintHole().setXfermode(null);
    }

    public boolean isHoleTransparent() {
        return ((PieChartRenderer) this.mRenderer).getPaintHole().getXfermode() != null;
    }

    public void setDrawHoleEnabled(boolean enabled) {
        this.mDrawHole = enabled;
    }

    public boolean isDrawHoleEnabled() {
        return this.mDrawHole;
    }

    public void setCenterText(SpannableString text) {
        if (text == null) {
            this.mCenterText = new SpannableString("");
        } else {
            this.mCenterText = text;
        }
    }

    public void setCenterText(String text) {
        setCenterText(new SpannableString(text));
    }

    public SpannableString getCenterText() {
        return this.mCenterText;
    }

    public void setDrawCenterText(boolean enabled) {
        this.mDrawCenterText = enabled;
    }

    public boolean isDrawCenterTextEnabled() {
        return this.mDrawCenterText;
    }

    protected float getRequiredLegendOffset() {
        return this.mLegendRenderer.getLabelPaint().getTextSize() * 2.0f;
    }

    protected float getRequiredBaseOffset() {
        return 0.0f;
    }

    public float getRadius() {
        if (this.mCircleBox == null) {
            return 0.0f;
        }
        return Math.min(this.mCircleBox.width() / 2.0f, this.mCircleBox.height() / 2.0f);
    }

    public RectF getCircleBox() {
        return this.mCircleBox;
    }

    public PointF getCenterCircleBox() {
        return new PointF(this.mCircleBox.centerX(), this.mCircleBox.centerY());
    }

    public void setCenterTextTypeface(Typeface t) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTypeface(t);
    }

    public void setCenterTextSize(float sizeDp) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(Utils.convertDpToPixel(sizeDp));
    }

    public void setCenterTextSizePixels(float sizePixels) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(sizePixels);
    }

    public void setCenterTextColor(int color) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setColor(color);
    }

    public void setHoleRadius(float percent) {
        this.mHoleRadiusPercent = percent;
    }

    public float getHoleRadius() {
        return this.mHoleRadiusPercent;
    }

    public void setTransparentCircleColor(int color) {
        Paint p = ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle();
        int alpha = p.getAlpha();
        p.setColor(color);
        p.setAlpha(alpha);
    }

    public void setTransparentCircleRadius(float percent) {
        this.mTransparentCircleRadiusPercent = percent;
    }

    public float getTransparentCircleRadius() {
        return this.mTransparentCircleRadiusPercent;
    }

    public void setTransparentCircleAlpha(int alpha) {
        ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle().setAlpha(alpha);
    }

    public void setDrawSliceText(boolean enabled) {
        this.mDrawXLabels = enabled;
    }

    public boolean isDrawSliceTextEnabled() {
        return this.mDrawXLabels;
    }

    public void setUsePercentValues(boolean enabled) {
        this.mUsePercentValues = enabled;
    }

    public boolean isUsePercentValuesEnabled() {
        return this.mUsePercentValues;
    }

    public void setCenterTextRadiusPercent(float percent) {
        this.mCenterTextRadiusPercent = percent;
    }

    public float getCenterTextRadiusPercent() {
        return this.mCenterTextRadiusPercent;
    }

    protected void onDetachedFromWindow() {
        if (this.mRenderer != null && (this.mRenderer instanceof PieChartRenderer)) {
            ((PieChartRenderer) this.mRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }
}
