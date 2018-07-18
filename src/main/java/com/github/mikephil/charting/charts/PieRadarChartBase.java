package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.PieRadarChartTouchListener;
import com.github.mikephil.charting.utils.SelectionDetail;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public abstract class PieRadarChartBase<T extends ChartData<? extends DataSet<? extends Entry>>> extends Chart<T> {
    protected float mMinOffset = 0.0f;
    private float mRawRotationAngle = 270.0f;
    protected boolean mRotateEnabled = true;
    private float mRotationAngle = 270.0f;

    public abstract int getIndexForAngle(float f);

    public abstract float getRadius();

    protected abstract float getRequiredBaseOffset();

    protected abstract float getRequiredLegendOffset();

    public PieRadarChartBase(Context context) {
        super(context);
    }

    public PieRadarChartBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieRadarChartBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void init() {
        super.init();
        this.mChartTouchListener = new PieRadarChartTouchListener(this);
    }

    protected void calcMinMax() {
        this.mDeltaX = (float) (this.mData.getXVals().size() - 1);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!this.mTouchEnabled || this.mChartTouchListener == null) {
            return super.onTouchEvent(event);
        }
        return this.mChartTouchListener.onTouch(this, event);
    }

    public void computeScroll() {
        if (this.mChartTouchListener instanceof PieRadarChartTouchListener) {
            ((PieRadarChartTouchListener) this.mChartTouchListener).computeScroll();
        }
    }

    public void notifyDataSetChanged() {
        if (!this.mDataNotSet) {
            calcMinMax();
            if (this.mLegend != null) {
                this.mLegendRenderer.computeLegend(this.mData);
            }
            calculateOffsets();
        }
    }

    public void calculateOffsets() {
        float legendLeft = 0.0f;
        float legendRight = 0.0f;
        float legendBottom = 0.0f;
        float legendTop = 0.0f;
        if (this.mLegend != null && this.mLegend.isEnabled()) {
            float fullLegendWidth = (Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + this.mLegend.getFormSize()) + this.mLegend.getFormToTextSpace();
            if (this.mLegend.getPosition() == LegendPosition.RIGHT_OF_CHART_CENTER) {
                legendRight = fullLegendWidth + Utils.convertDpToPixel(13.0f);
            } else {
                float legendWidth;
                float legendHeight;
                PointF c;
                float distLegend;
                PointF reference;
                float distReference;
                float min;
                if (this.mLegend.getPosition() == LegendPosition.RIGHT_OF_CHART) {
                    legendWidth = fullLegendWidth + Utils.convertDpToPixel(8.0f);
                    legendHeight = this.mLegend.mNeededHeight + this.mLegend.mTextHeightMax;
                    c = getCenter();
                    PointF bottomRight = new PointF((((float) getWidth()) - legendWidth) + 15.0f, 15.0f + legendHeight);
                    distLegend = distanceToCenter(bottomRight.x, bottomRight.y);
                    reference = getPosition(c, getRadius(), getAngleForPoint(bottomRight.x, bottomRight.y));
                    distReference = distanceToCenter(reference.x, reference.y);
                    min = Utils.convertDpToPixel(5.0f);
                    if (distLegend < distReference) {
                        legendRight = min + (distReference - distLegend);
                    }
                    if (bottomRight.y >= c.y && ((float) getHeight()) - legendWidth > ((float) getWidth())) {
                        legendRight = legendWidth;
                    }
                } else {
                    if (this.mLegend.getPosition() == LegendPosition.LEFT_OF_CHART_CENTER) {
                        legendLeft = fullLegendWidth + Utils.convertDpToPixel(13.0f);
                    } else {
                        if (this.mLegend.getPosition() == LegendPosition.LEFT_OF_CHART) {
                            legendWidth = fullLegendWidth + Utils.convertDpToPixel(8.0f);
                            legendHeight = this.mLegend.mNeededHeight + this.mLegend.mTextHeightMax;
                            c = getCenter();
                            PointF bottomLeft = new PointF(legendWidth - 15.0f, 15.0f + legendHeight);
                            distLegend = distanceToCenter(bottomLeft.x, bottomLeft.y);
                            reference = getPosition(c, getRadius(), getAngleForPoint(bottomLeft.x, bottomLeft.y));
                            distReference = distanceToCenter(reference.x, reference.y);
                            min = Utils.convertDpToPixel(5.0f);
                            if (distLegend < distReference) {
                                legendLeft = min + (distReference - distLegend);
                            }
                            if (bottomLeft.y >= c.y && ((float) getHeight()) - legendWidth > ((float) getWidth())) {
                                legendLeft = legendWidth;
                            }
                        } else {
                            if (this.mLegend.getPosition() == LegendPosition.BELOW_CHART_LEFT || this.mLegend.getPosition() == LegendPosition.BELOW_CHART_RIGHT || this.mLegend.getPosition() == LegendPosition.BELOW_CHART_CENTER) {
                                legendBottom = Math.min(this.mLegend.mNeededHeight + getRequiredLegendOffset(), this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent());
                            } else {
                                if (this.mLegend.getPosition() == LegendPosition.ABOVE_CHART_LEFT || this.mLegend.getPosition() == LegendPosition.ABOVE_CHART_RIGHT || this.mLegend.getPosition() == LegendPosition.ABOVE_CHART_CENTER) {
                                    legendTop = Math.min(this.mLegend.mNeededHeight + getRequiredLegendOffset(), this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent());
                                }
                            }
                        }
                    }
                }
            }
            legendLeft += getRequiredBaseOffset();
            legendRight += getRequiredBaseOffset();
            legendTop += getRequiredBaseOffset();
        }
        float minOffset = Utils.convertDpToPixel(this.mMinOffset);
        if (this instanceof RadarChart) {
            XAxis x = ((RadarChart) this).getXAxis();
            if (x.isEnabled() && x.isDrawLabelsEnabled()) {
                minOffset = Math.max(minOffset, (float) x.mLabelRotatedWidth);
            }
        }
        legendTop += getExtraTopOffset();
        legendRight += getExtraRightOffset();
        legendBottom += getExtraBottomOffset();
        float offsetLeft = Math.max(minOffset, legendLeft + getExtraLeftOffset());
        float offsetTop = Math.max(minOffset, legendTop);
        float offsetRight = Math.max(minOffset, legendRight);
        float offsetBottom = Math.max(minOffset, Math.max(getRequiredBaseOffset(), legendBottom));
        this.mViewPortHandler.restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
        if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "offsetLeft: " + offsetLeft + ", offsetTop: " + offsetTop + ", offsetRight: " + offsetRight + ", offsetBottom: " + offsetBottom);
        }
    }

    public float getAngleForPoint(float x, float y) {
        PointF c = getCenterOffsets();
        double tx = (double) (x - c.x);
        double ty = (double) (y - c.y);
        float angle = (float) Math.toDegrees(Math.acos(ty / Math.sqrt((tx * tx) + (ty * ty))));
        if (x > c.x) {
            angle = 360.0f - angle;
        }
        angle += 90.0f;
        if (angle > 360.0f) {
            return angle - 360.0f;
        }
        return angle;
    }

    protected PointF getPosition(PointF center, float dist, float angle) {
        return new PointF((float) (((double) center.x) + (((double) dist) * Math.cos(Math.toRadians((double) angle)))), (float) (((double) center.y) + (((double) dist) * Math.sin(Math.toRadians((double) angle)))));
    }

    public float distanceToCenter(float x, float y) {
        float xDist;
        float yDist;
        PointF c = getCenterOffsets();
        if (x > c.x) {
            xDist = x - c.x;
        } else {
            xDist = c.x - x;
        }
        if (y > c.y) {
            yDist = y - c.y;
        } else {
            yDist = c.y - y;
        }
        return (float) Math.sqrt(Math.pow((double) xDist, 2.0d) + Math.pow((double) yDist, 2.0d));
    }

    public void setRotationAngle(float angle) {
        this.mRawRotationAngle = angle;
        this.mRotationAngle = Utils.getNormalizedAngle(this.mRawRotationAngle);
    }

    public float getRawRotationAngle() {
        return this.mRawRotationAngle;
    }

    public float getRotationAngle() {
        return this.mRotationAngle;
    }

    public void setRotationEnabled(boolean enabled) {
        this.mRotateEnabled = enabled;
    }

    public boolean isRotationEnabled() {
        return this.mRotateEnabled;
    }

    public float getMinOffset() {
        return this.mMinOffset;
    }

    public void setMinOffset(float minOffset) {
        this.mMinOffset = minOffset;
    }

    public float getDiameter() {
        RectF content = this.mViewPortHandler.getContentRect();
        return Math.min(content.width(), content.height());
    }

    public float getYChartMax() {
        return 0.0f;
    }

    public float getYChartMin() {
        return 0.0f;
    }

    public List<SelectionDetail> getSelectionDetailsAtIndex(int xIndex) {
        List<SelectionDetail> vals = new ArrayList();
        for (int i = 0; i < this.mData.getDataSetCount(); i++) {
            DataSet<?> dataSet = this.mData.getDataSetByIndex(i);
            float yVal = dataSet.getYValForXIndex(xIndex);
            if (yVal != Float.NaN) {
                vals.add(new SelectionDetail(yVal, i, dataSet));
            }
        }
        return vals;
    }
}
