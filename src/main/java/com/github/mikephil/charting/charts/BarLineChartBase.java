package com.github.mikephil.charting.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.filter.Approximator;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.jobs.MoveViewJob;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.PointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;

@SuppressLint({"RtlHardcoded"})
public abstract class BarLineChartBase<T extends BarLineScatterCandleBubbleData<? extends BarLineScatterCandleBubbleDataSet<? extends Entry>>> extends Chart<T> implements BarLineScatterCandleBubbleDataProvider {
    private long drawCycles = 0;
    private Integer mAutoScaleLastHighestVisibleXIndex = null;
    private Integer mAutoScaleLastLowestVisibleXIndex = null;
    private boolean mAutoScaleMinMaxEnabled = false;
    protected YAxis mAxisLeft;
    protected YAxisRenderer mAxisRendererLeft;
    protected YAxisRenderer mAxisRendererRight;
    protected YAxis mAxisRight;
    protected Paint mBorderPaint;
    private boolean mCustomViewPortEnabled = false;
    protected boolean mDoubleTapToZoomEnabled = true;
    private boolean mDragEnabled = true;
    protected boolean mDrawBorders = false;
    protected boolean mDrawGridBackground = true;
    protected OnDrawListener mDrawListener;
    protected boolean mFilterData = false;
    protected Paint mGridBackgroundPaint;
    protected boolean mHighlightPerDragEnabled = true;
    protected Transformer mLeftAxisTransformer;
    protected int mMaxVisibleCount = 100;
    protected float mMinOffset = 10.0f;
    protected boolean mPinchZoomEnabled = false;
    protected Transformer mRightAxisTransformer;
    private boolean mScaleXEnabled = true;
    private boolean mScaleYEnabled = true;
    protected XAxis mXAxis;
    protected XAxisRenderer mXAxisRenderer;
    private long totalTime = 0;

    public /* bridge */ /* synthetic */ BarLineScatterCandleBubbleData getData() {
        return (BarLineScatterCandleBubbleData) super.getData();
    }

    public BarLineChartBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BarLineChartBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarLineChartBase(Context context) {
        super(context);
    }

    protected void init() {
        super.init();
        this.mAxisLeft = new YAxis(AxisDependency.LEFT);
        this.mAxisRight = new YAxis(AxisDependency.RIGHT);
        this.mXAxis = new XAxis();
        this.mLeftAxisTransformer = new Transformer(this.mViewPortHandler);
        this.mRightAxisTransformer = new Transformer(this.mViewPortHandler);
        this.mAxisRendererLeft = new YAxisRenderer(this.mViewPortHandler, this.mAxisLeft, this.mLeftAxisTransformer);
        this.mAxisRendererRight = new YAxisRenderer(this.mViewPortHandler, this.mAxisRight, this.mRightAxisTransformer);
        this.mXAxisRenderer = new XAxisRenderer(this.mViewPortHandler, this.mXAxis, this.mLeftAxisTransformer);
        this.mHighlighter = new ChartHighlighter(this);
        this.mChartTouchListener = new BarLineChartTouchListener(this, this.mViewPortHandler.getMatrixTouch());
        this.mGridBackgroundPaint = new Paint();
        this.mGridBackgroundPaint.setStyle(Style.FILL);
        this.mGridBackgroundPaint.setColor(Color.rgb(240, 240, 240));
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setStyle(Style.STROKE);
        this.mBorderPaint.setColor(-16777216);
        this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(1.0f));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mDataNotSet) {
            long starttime = System.currentTimeMillis();
            calcModulus();
            this.mXAxisRenderer.calcXBounds(this, this.mXAxis.mAxisLabelModulus);
            this.mRenderer.calcXBounds(this, this.mXAxis.mAxisLabelModulus);
            drawGridBackground(canvas);
            if (this.mAxisLeft.isEnabled()) {
                this.mAxisRendererLeft.computeAxis(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisMaximum);
            }
            if (this.mAxisRight.isEnabled()) {
                this.mAxisRendererRight.computeAxis(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisMaximum);
            }
            this.mXAxisRenderer.renderAxisLine(canvas);
            this.mAxisRendererLeft.renderAxisLine(canvas);
            this.mAxisRendererRight.renderAxisLine(canvas);
            if (this.mAutoScaleMinMaxEnabled) {
                int lowestVisibleXIndex = getLowestVisibleXIndex();
                int highestVisibleXIndex = getHighestVisibleXIndex();
                if (this.mAutoScaleLastLowestVisibleXIndex == null || this.mAutoScaleLastLowestVisibleXIndex.intValue() != lowestVisibleXIndex || this.mAutoScaleLastHighestVisibleXIndex == null || this.mAutoScaleLastHighestVisibleXIndex.intValue() != highestVisibleXIndex) {
                    calcMinMax();
                    calculateOffsets();
                    this.mAutoScaleLastLowestVisibleXIndex = Integer.valueOf(lowestVisibleXIndex);
                    this.mAutoScaleLastHighestVisibleXIndex = Integer.valueOf(highestVisibleXIndex);
                }
            }
            int clipRestoreCount = canvas.save();
            canvas.clipRect(this.mViewPortHandler.getContentRect());
            this.mXAxisRenderer.renderGridLines(canvas);
            this.mAxisRendererLeft.renderGridLines(canvas);
            this.mAxisRendererRight.renderGridLines(canvas);
            if (this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderLimitLines(canvas);
            }
            if (this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderLimitLines(canvas);
            }
            if (this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderLimitLines(canvas);
            }
            this.mRenderer.drawData(canvas);
            if (!this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderLimitLines(canvas);
            }
            if (!this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderLimitLines(canvas);
            }
            if (!this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderLimitLines(canvas);
            }
            if (valuesToHighlight()) {
                this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
            }
            canvas.restoreToCount(clipRestoreCount);
            this.mRenderer.drawExtras(canvas);
            this.mXAxisRenderer.renderAxisLabels(canvas);
            this.mAxisRendererLeft.renderAxisLabels(canvas);
            this.mAxisRendererRight.renderAxisLabels(canvas);
            this.mRenderer.drawValues(canvas);
            this.mLegendRenderer.renderLegend(canvas);
            drawMarkers(canvas);
            drawDescription(canvas);
            if (this.mLogEnabled) {
                long drawtime = System.currentTimeMillis() - starttime;
                this.totalTime += drawtime;
                this.drawCycles++;
                Log.i(Chart.LOG_TAG, "Drawtime: " + drawtime + " ms, average: " + (this.totalTime / this.drawCycles) + " ms, cycles: " + this.drawCycles);
            }
        }
    }

    public void resetTracking() {
        this.totalTime = 0;
        this.drawCycles = 0;
    }

    protected void prepareValuePxMatrix() {
        if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "Preparing Value-Px Matrix, xmin: " + this.mXChartMin + ", xmax: " + this.mXChartMax + ", xdelta: " + this.mDeltaX);
        }
        this.mRightAxisTransformer.prepareMatrixValuePx(this.mXChartMin, this.mDeltaX, this.mAxisRight.mAxisRange, this.mAxisRight.mAxisMinimum);
        this.mLeftAxisTransformer.prepareMatrixValuePx(this.mXChartMin, this.mDeltaX, this.mAxisLeft.mAxisRange, this.mAxisLeft.mAxisMinimum);
    }

    protected void prepareOffsetMatrix() {
        this.mRightAxisTransformer.prepareMatrixOffset(this.mAxisRight.isInverted());
        this.mLeftAxisTransformer.prepareMatrixOffset(this.mAxisLeft.isInverted());
    }

    public void notifyDataSetChanged() {
        if (!this.mDataNotSet) {
            if (this.mLogEnabled) {
                Log.i(Chart.LOG_TAG, "Preparing...");
            }
            if (this.mRenderer != null) {
                this.mRenderer.initBuffers();
            }
            calcMinMax();
            this.mAxisRendererLeft.computeAxis(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisMaximum);
            this.mAxisRendererRight.computeAxis(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisMaximum);
            this.mXAxisRenderer.computeAxis(((BarLineScatterCandleBubbleData) this.mData).getXValAverageLength(), ((BarLineScatterCandleBubbleData) this.mData).getXVals());
            if (this.mLegend != null) {
                this.mLegendRenderer.computeLegend(this.mData);
            }
            calculateOffsets();
        } else if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "Preparing... DATA NOT SET.");
        }
    }

    protected void calcMinMax() {
        float f;
        if (this.mAutoScaleMinMaxEnabled) {
            ((BarLineScatterCandleBubbleData) this.mData).calcMinMax(getLowestVisibleXIndex(), getHighestVisibleXIndex());
        }
        float minLeft = ((BarLineScatterCandleBubbleData) this.mData).getYMin(AxisDependency.LEFT);
        float maxLeft = ((BarLineScatterCandleBubbleData) this.mData).getYMax(AxisDependency.LEFT);
        float minRight = ((BarLineScatterCandleBubbleData) this.mData).getYMin(AxisDependency.RIGHT);
        float maxRight = ((BarLineScatterCandleBubbleData) this.mData).getYMax(AxisDependency.RIGHT);
        if (this.mAxisLeft.isStartAtZeroEnabled()) {
            f = 0.0f;
        } else {
            f = minLeft;
        }
        float leftRange = Math.abs(maxLeft - f);
        if (this.mAxisRight.isStartAtZeroEnabled()) {
            f = 0.0f;
        } else {
            f = minRight;
        }
        float rightRange = Math.abs(maxRight - f);
        if (leftRange == 0.0f) {
            maxLeft += 1.0f;
            if (!this.mAxisLeft.isStartAtZeroEnabled()) {
                minLeft -= 1.0f;
            }
        }
        if (rightRange == 0.0f) {
            maxRight += 1.0f;
            if (!this.mAxisRight.isStartAtZeroEnabled()) {
                minRight -= 1.0f;
            }
        }
        float topSpaceLeft = (leftRange / 100.0f) * this.mAxisLeft.getSpaceTop();
        float topSpaceRight = (rightRange / 100.0f) * this.mAxisRight.getSpaceTop();
        float bottomSpaceLeft = (leftRange / 100.0f) * this.mAxisLeft.getSpaceBottom();
        float bottomSpaceRight = (rightRange / 100.0f) * this.mAxisRight.getSpaceBottom();
        this.mXChartMax = (float) (((BarLineScatterCandleBubbleData) this.mData).getXVals().size() - 1);
        this.mDeltaX = Math.abs(this.mXChartMax - this.mXChartMin);
        if (!this.mAxisLeft.isStartAtZeroEnabled()) {
            this.mAxisLeft.mAxisMinimum = !Float.isNaN(this.mAxisLeft.getAxisMinValue()) ? this.mAxisLeft.getAxisMinValue() : minLeft - bottomSpaceLeft;
            this.mAxisLeft.mAxisMaximum = !Float.isNaN(this.mAxisLeft.getAxisMaxValue()) ? this.mAxisLeft.getAxisMaxValue() : maxLeft + topSpaceLeft;
        } else if (minLeft < 0.0f && maxLeft < 0.0f) {
            this.mAxisLeft.mAxisMinimum = Math.min(0.0f, !Float.isNaN(this.mAxisLeft.getAxisMinValue()) ? this.mAxisLeft.getAxisMinValue() : minLeft - bottomSpaceLeft);
            this.mAxisLeft.mAxisMaximum = 0.0f;
        } else if (((double) minLeft) >= 0.0d) {
            this.mAxisLeft.mAxisMinimum = 0.0f;
            this.mAxisLeft.mAxisMaximum = Math.max(0.0f, !Float.isNaN(this.mAxisLeft.getAxisMaxValue()) ? this.mAxisLeft.getAxisMaxValue() : maxLeft + topSpaceLeft);
        } else {
            this.mAxisLeft.mAxisMinimum = Math.min(0.0f, !Float.isNaN(this.mAxisLeft.getAxisMinValue()) ? this.mAxisLeft.getAxisMinValue() : minLeft - bottomSpaceLeft);
            this.mAxisLeft.mAxisMaximum = Math.max(0.0f, !Float.isNaN(this.mAxisLeft.getAxisMaxValue()) ? this.mAxisLeft.getAxisMaxValue() : maxLeft + topSpaceLeft);
        }
        if (!this.mAxisRight.isStartAtZeroEnabled()) {
            this.mAxisRight.mAxisMinimum = !Float.isNaN(this.mAxisRight.getAxisMinValue()) ? this.mAxisRight.getAxisMinValue() : minRight - bottomSpaceRight;
            this.mAxisRight.mAxisMaximum = !Float.isNaN(this.mAxisRight.getAxisMaxValue()) ? this.mAxisRight.getAxisMaxValue() : maxRight + topSpaceRight;
        } else if (minRight < 0.0f && maxRight < 0.0f) {
            this.mAxisRight.mAxisMinimum = Math.min(0.0f, !Float.isNaN(this.mAxisRight.getAxisMinValue()) ? this.mAxisRight.getAxisMinValue() : minRight - bottomSpaceRight);
            this.mAxisRight.mAxisMaximum = 0.0f;
        } else if (minRight >= 0.0f) {
            this.mAxisRight.mAxisMinimum = 0.0f;
            this.mAxisRight.mAxisMaximum = Math.max(0.0f, !Float.isNaN(this.mAxisRight.getAxisMaxValue()) ? this.mAxisRight.getAxisMaxValue() : maxRight + topSpaceRight);
        } else {
            this.mAxisRight.mAxisMinimum = Math.min(0.0f, !Float.isNaN(this.mAxisRight.getAxisMinValue()) ? this.mAxisRight.getAxisMinValue() : minRight - bottomSpaceRight);
            this.mAxisRight.mAxisMaximum = Math.max(0.0f, !Float.isNaN(this.mAxisRight.getAxisMaxValue()) ? this.mAxisRight.getAxisMaxValue() : maxRight + topSpaceRight);
        }
        this.mAxisLeft.mAxisRange = Math.abs(this.mAxisLeft.mAxisMaximum - this.mAxisLeft.mAxisMinimum);
        this.mAxisRight.mAxisRange = Math.abs(this.mAxisRight.mAxisMaximum - this.mAxisRight.mAxisMinimum);
    }

    public void calculateOffsets() {
        if (!this.mCustomViewPortEnabled) {
            float offsetLeft = 0.0f;
            float offsetRight = 0.0f;
            float offsetTop = 0.0f;
            float offsetBottom = 0.0f;
            if (this.mLegend != null && this.mLegend.isEnabled()) {
                if (this.mLegend.getPosition() == LegendPosition.RIGHT_OF_CHART || this.mLegend.getPosition() == LegendPosition.RIGHT_OF_CHART_CENTER) {
                    offsetRight = 0.0f + (Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + (this.mLegend.getXOffset() * 2.0f));
                } else if (this.mLegend.getPosition() == LegendPosition.LEFT_OF_CHART || this.mLegend.getPosition() == LegendPosition.LEFT_OF_CHART_CENTER) {
                    offsetLeft = 0.0f + (Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + (this.mLegend.getXOffset() * 2.0f));
                } else if (this.mLegend.getPosition() == LegendPosition.BELOW_CHART_LEFT || this.mLegend.getPosition() == LegendPosition.BELOW_CHART_RIGHT || this.mLegend.getPosition() == LegendPosition.BELOW_CHART_CENTER) {
                    offsetBottom = 0.0f + Math.min(this.mLegend.mNeededHeight + this.mLegend.mTextHeightMax, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent());
                } else if (this.mLegend.getPosition() == LegendPosition.ABOVE_CHART_LEFT || this.mLegend.getPosition() == LegendPosition.ABOVE_CHART_RIGHT || this.mLegend.getPosition() == LegendPosition.ABOVE_CHART_CENTER) {
                    offsetTop = 0.0f + Math.min(this.mLegend.mNeededHeight + this.mLegend.mTextHeightMax, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent());
                }
            }
            if (this.mAxisLeft.needsOffset()) {
                offsetLeft += this.mAxisLeft.getRequiredWidthSpace(this.mAxisRendererLeft.getPaintAxisLabels());
            }
            if (this.mAxisRight.needsOffset()) {
                offsetRight += this.mAxisRight.getRequiredWidthSpace(this.mAxisRendererRight.getPaintAxisLabels());
            }
            if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
                float xlabelheight = ((float) this.mXAxis.mLabelRotatedHeight) + this.mXAxis.getYOffset();
                if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM) {
                    offsetBottom += xlabelheight;
                } else if (this.mXAxis.getPosition() == XAxisPosition.TOP) {
                    offsetTop += xlabelheight;
                } else if (this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                    offsetBottom += xlabelheight;
                    offsetTop += xlabelheight;
                }
            }
            offsetTop += getExtraTopOffset();
            offsetRight += getExtraRightOffset();
            offsetBottom += getExtraBottomOffset();
            offsetLeft += getExtraLeftOffset();
            float minOffset = Utils.convertDpToPixel(this.mMinOffset);
            this.mViewPortHandler.restrainViewPort(Math.max(minOffset, offsetLeft), Math.max(minOffset, offsetTop), Math.max(minOffset, offsetRight), Math.max(minOffset, offsetBottom));
            if (this.mLogEnabled) {
                Log.i(Chart.LOG_TAG, "offsetLeft: " + offsetLeft + ", offsetTop: " + offsetTop + ", offsetRight: " + offsetRight + ", offsetBottom: " + offsetBottom);
                Log.i(Chart.LOG_TAG, "Content: " + this.mViewPortHandler.getContentRect().toString());
            }
        }
        prepareOffsetMatrix();
        prepareValuePxMatrix();
    }

    protected void calcModulus() {
        if (this.mXAxis != null && this.mXAxis.isEnabled()) {
            if (!this.mXAxis.isAxisModulusCustom()) {
                float[] values = new float[9];
                this.mViewPortHandler.getMatrixTouch().getValues(values);
                this.mXAxis.mAxisLabelModulus = (int) Math.ceil((double) (((float) (((BarLineScatterCandleBubbleData) this.mData).getXValCount() * this.mXAxis.mLabelRotatedWidth)) / (this.mViewPortHandler.contentWidth() * values[0])));
            }
            if (this.mLogEnabled) {
                Log.i(Chart.LOG_TAG, "X-Axis modulus: " + this.mXAxis.mAxisLabelModulus + ", x-axis label width: " + this.mXAxis.mLabelWidth + ", x-axis label rotated width: " + this.mXAxis.mLabelRotatedWidth + ", content width: " + this.mViewPortHandler.contentWidth());
            }
            if (this.mXAxis.mAxisLabelModulus < 1) {
                this.mXAxis.mAxisLabelModulus = 1;
            }
        }
    }

    protected float[] getMarkerPosition(Entry e, Highlight highlight) {
        int dataSetIndex = highlight.getDataSetIndex();
        float xPos = (float) e.getXIndex();
        float yPos = e.getVal();
        if (this instanceof BarChart) {
            float space = this.mData.getGroupSpace();
            int setCount = ((BarLineScatterCandleBubbleData) this.mData).getDataSetCount();
            int i = e.getXIndex();
            if (this instanceof HorizontalBarChart) {
                yPos = (((float) ((((setCount - 1) * i) + i) + dataSetIndex)) + (((float) i) * space)) + (space / 2.0f);
                if (((BarEntry) e).getVals() != null) {
                    xPos = highlight.getRange().to;
                } else {
                    xPos = e.getVal();
                }
                xPos *= this.mAnimator.getPhaseY();
            } else {
                xPos = (((float) ((((setCount - 1) * i) + i) + dataSetIndex)) + (((float) i) * space)) + (space / 2.0f);
                if (((BarEntry) e).getVals() != null) {
                    yPos = highlight.getRange().to;
                } else {
                    yPos = e.getVal();
                }
                yPos *= this.mAnimator.getPhaseY();
            }
        } else {
            yPos *= this.mAnimator.getPhaseY();
        }
        float[] pts = new float[]{xPos, yPos};
        getTransformer(((BarLineScatterCandleBubbleDataSet) ((BarLineScatterCandleBubbleData) this.mData).getDataSetByIndex(dataSetIndex)).getAxisDependency()).pointValuesToPixel(pts);
        return pts;
    }

    protected void drawGridBackground(Canvas c) {
        if (this.mDrawGridBackground) {
            c.drawRect(this.mViewPortHandler.getContentRect(), this.mGridBackgroundPaint);
        }
        if (this.mDrawBorders) {
            c.drawRect(this.mViewPortHandler.getContentRect(), this.mBorderPaint);
        }
    }

    public Transformer getTransformer(AxisDependency which) {
        if (which == AxisDependency.LEFT) {
            return this.mLeftAxisTransformer;
        }
        return this.mRightAxisTransformer;
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (this.mChartTouchListener == null || this.mDataNotSet || !this.mTouchEnabled) {
            return false;
        }
        return this.mChartTouchListener.onTouch(this, event);
    }

    public void computeScroll() {
        if (this.mChartTouchListener instanceof BarLineChartTouchListener) {
            ((BarLineChartTouchListener) this.mChartTouchListener).computeScroll();
        }
    }

    public void zoomIn() {
        this.mViewPortHandler.refresh(this.mViewPortHandler.zoomIn(((float) getWidth()) / 2.0f, -(((float) getHeight()) / 2.0f)), this, true);
        calculateOffsets();
        postInvalidate();
    }

    public void zoomOut() {
        this.mViewPortHandler.refresh(this.mViewPortHandler.zoomOut(((float) getWidth()) / 2.0f, -(((float) getHeight()) / 2.0f)), this, true);
        calculateOffsets();
        postInvalidate();
    }

    public void zoom(float scaleX, float scaleY, float x, float y) {
        this.mViewPortHandler.refresh(this.mViewPortHandler.zoom(scaleX, scaleY, x, -y), this, true);
        calculateOffsets();
        postInvalidate();
    }

    public void fitScreen() {
        this.mViewPortHandler.refresh(this.mViewPortHandler.fitScreen(), this, true);
        calculateOffsets();
        postInvalidate();
    }

    public void setScaleMinima(float scaleX, float scaleY) {
        this.mViewPortHandler.setMinimumScaleX(scaleX);
        this.mViewPortHandler.setMinimumScaleY(scaleY);
    }

    public void setVisibleXRangeMaximum(float maxXRange) {
        this.mViewPortHandler.setMinimumScaleX(this.mDeltaX / maxXRange);
    }

    public void setVisibleXRangeMinimum(float minXRange) {
        this.mViewPortHandler.setMaximumScaleX(this.mDeltaX / minXRange);
    }

    public void setVisibleXRange(float minXRange, float maxXRange) {
        float minScale = this.mDeltaX / maxXRange;
        this.mViewPortHandler.setMinMaxScaleX(minScale, this.mDeltaX / minXRange);
    }

    public void setVisibleYRangeMaximum(float maxYRange, AxisDependency axis) {
        this.mViewPortHandler.setMinimumScaleY(getDeltaY(axis) / maxYRange);
    }

    public void moveViewToX(float xIndex) {
        Runnable job = new MoveViewJob(this.mViewPortHandler, xIndex, 0.0f, getTransformer(AxisDependency.LEFT), this);
        if (this.mViewPortHandler.hasChartDimens()) {
            post(job);
        } else {
            this.mJobs.add(job);
        }
    }

    public void moveViewToY(float yValue, AxisDependency axis) {
        Runnable job = new MoveViewJob(this.mViewPortHandler, 0.0f, ((getDeltaY(axis) / this.mViewPortHandler.getScaleY()) / 2.0f) + yValue, getTransformer(axis), this);
        if (this.mViewPortHandler.hasChartDimens()) {
            post(job);
        } else {
            this.mJobs.add(job);
        }
    }

    public void moveViewTo(float xIndex, float yValue, AxisDependency axis) {
        float f = xIndex;
        Runnable job = new MoveViewJob(this.mViewPortHandler, f, yValue + ((getDeltaY(axis) / this.mViewPortHandler.getScaleY()) / 2.0f), getTransformer(axis), this);
        if (this.mViewPortHandler.hasChartDimens()) {
            post(job);
        } else {
            this.mJobs.add(job);
        }
    }

    public void centerViewTo(int xIndex, float yValue, AxisDependency axis) {
        float xsInView = ((float) getXAxis().getValues().size()) / this.mViewPortHandler.getScaleX();
        Runnable job = new MoveViewJob(this.mViewPortHandler, ((float) xIndex) - (xsInView / 2.0f), ((getDeltaY(axis) / this.mViewPortHandler.getScaleY()) / 2.0f) + yValue, getTransformer(axis), this);
        if (this.mViewPortHandler.hasChartDimens()) {
            post(job);
        } else {
            this.mJobs.add(job);
        }
    }

    public void setViewPortOffsets(float left, float top, float right, float bottom) {
        this.mCustomViewPortEnabled = true;
        final float f = left;
        final float f2 = top;
        final float f3 = right;
        final float f4 = bottom;
        post(new Runnable() {
            public void run() {
                BarLineChartBase.this.mViewPortHandler.restrainViewPort(f, f2, f3, f4);
                BarLineChartBase.this.prepareOffsetMatrix();
                BarLineChartBase.this.prepareValuePxMatrix();
            }
        });
    }

    public void resetViewPortOffsets() {
        this.mCustomViewPortEnabled = false;
        calculateOffsets();
    }

    public float getDeltaY(AxisDependency axis) {
        if (axis == AxisDependency.LEFT) {
            return this.mAxisLeft.mAxisRange;
        }
        return this.mAxisRight.mAxisRange;
    }

    public void setOnDrawListener(OnDrawListener drawListener) {
        this.mDrawListener = drawListener;
    }

    public OnDrawListener getDrawListener() {
        return this.mDrawListener;
    }

    public PointF getPosition(Entry e, AxisDependency axis) {
        if (e == null) {
            return null;
        }
        float[] vals = new float[]{(float) e.getXIndex(), e.getVal()};
        getTransformer(axis).pointValuesToPixel(vals);
        return new PointF(vals[0], vals[1]);
    }

    public void setMaxVisibleValueCount(int count) {
        this.mMaxVisibleCount = count;
    }

    public int getMaxVisibleCount() {
        return this.mMaxVisibleCount;
    }

    public void setHighlightPerDragEnabled(boolean enabled) {
        this.mHighlightPerDragEnabled = enabled;
    }

    public boolean isHighlightPerDragEnabled() {
        return this.mHighlightPerDragEnabled;
    }

    public void setGridBackgroundColor(int color) {
        this.mGridBackgroundPaint.setColor(color);
    }

    public void setDragEnabled(boolean enabled) {
        this.mDragEnabled = enabled;
    }

    public boolean isDragEnabled() {
        return this.mDragEnabled;
    }

    public void setScaleEnabled(boolean enabled) {
        this.mScaleXEnabled = enabled;
        this.mScaleYEnabled = enabled;
    }

    public void setScaleXEnabled(boolean enabled) {
        this.mScaleXEnabled = enabled;
    }

    public void setScaleYEnabled(boolean enabled) {
        this.mScaleYEnabled = enabled;
    }

    public boolean isScaleXEnabled() {
        return this.mScaleXEnabled;
    }

    public boolean isScaleYEnabled() {
        return this.mScaleYEnabled;
    }

    public void setDoubleTapToZoomEnabled(boolean enabled) {
        this.mDoubleTapToZoomEnabled = enabled;
    }

    public boolean isDoubleTapToZoomEnabled() {
        return this.mDoubleTapToZoomEnabled;
    }

    public void setDrawGridBackground(boolean enabled) {
        this.mDrawGridBackground = enabled;
    }

    public void setDrawBorders(boolean enabled) {
        this.mDrawBorders = enabled;
    }

    public void setBorderWidth(float width) {
        this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(width));
    }

    public void setBorderColor(int color) {
        this.mBorderPaint.setColor(color);
    }

    public float getMinOffset() {
        return this.mMinOffset;
    }

    public void setMinOffset(float minOffset) {
        this.mMinOffset = minOffset;
    }

    public Highlight getHighlightByTouchPoint(float x, float y) {
        if (!this.mDataNotSet && this.mData != null) {
            return this.mHighlighter.getHighlight(x, y);
        }
        Log.e(Chart.LOG_TAG, "Can't select by touch. No data set.");
        return null;
    }

    public PointD getValuesByTouchPoint(float x, float y, AxisDependency axis) {
        float[] pts = new float[]{x, y};
        getTransformer(axis).pixelsToValue(pts);
        return new PointD((double) pts[0], (double) pts[1]);
    }

    public PointD getPixelsForValues(float x, float y, AxisDependency axis) {
        float[] pts = new float[]{x, y};
        getTransformer(axis).pointValuesToPixel(pts);
        return new PointD((double) pts[0], (double) pts[1]);
    }

    public float getYValueByTouchPoint(float x, float y, AxisDependency axis) {
        return (float) getValuesByTouchPoint(x, y, axis).y;
    }

    public Entry getEntryByTouchPoint(float x, float y) {
        Highlight h = getHighlightByTouchPoint(x, y);
        if (h != null) {
            return ((BarLineScatterCandleBubbleData) this.mData).getEntryForHighlight(h);
        }
        return null;
    }

    public BarLineScatterCandleBubbleDataSet<? extends Entry> getDataSetByTouchPoint(float x, float y) {
        Highlight h = getHighlightByTouchPoint(x, y);
        if (h != null) {
            return (BarLineScatterCandleBubbleDataSet) ((BarLineScatterCandleBubbleData) this.mData).getDataSetByIndex(h.getDataSetIndex());
        }
        return null;
    }

    public int getLowestVisibleXIndex() {
        float[] pts = new float[]{this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom()};
        getTransformer(AxisDependency.LEFT).pixelsToValue(pts);
        if (pts[0] <= 0.0f) {
            return 0;
        }
        return (int) (pts[0] + 1.0f);
    }

    public int getHighestVisibleXIndex() {
        float[] pts = new float[]{this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom()};
        getTransformer(AxisDependency.LEFT).pixelsToValue(pts);
        return pts[0] >= ((float) ((BarLineScatterCandleBubbleData) this.mData).getXValCount()) ? ((BarLineScatterCandleBubbleData) this.mData).getXValCount() - 1 : (int) pts[0];
    }

    public float getScaleX() {
        if (this.mViewPortHandler == null) {
            return 1.0f;
        }
        return this.mViewPortHandler.getScaleX();
    }

    public float getScaleY() {
        if (this.mViewPortHandler == null) {
            return 1.0f;
        }
        return this.mViewPortHandler.getScaleY();
    }

    public boolean isFullyZoomedOut() {
        return this.mViewPortHandler.isFullyZoomedOut();
    }

    public YAxis getAxisLeft() {
        return this.mAxisLeft;
    }

    public YAxis getAxisRight() {
        return this.mAxisRight;
    }

    public YAxis getAxis(AxisDependency axis) {
        if (axis == AxisDependency.LEFT) {
            return this.mAxisLeft;
        }
        return this.mAxisRight;
    }

    public boolean isInverted(AxisDependency axis) {
        return getAxis(axis).isInverted();
    }

    public XAxis getXAxis() {
        return this.mXAxis;
    }

    public void enableFiltering(Approximator a) {
        this.mFilterData = true;
    }

    public void disableFiltering() {
        this.mFilterData = false;
    }

    public boolean isFilteringEnabled() {
        return this.mFilterData;
    }

    public void setPinchZoom(boolean enabled) {
        this.mPinchZoomEnabled = enabled;
    }

    public boolean isPinchZoomEnabled() {
        return this.mPinchZoomEnabled;
    }

    public void setDragOffsetX(float offset) {
        this.mViewPortHandler.setDragOffsetX(offset);
    }

    public void setDragOffsetY(float offset) {
        this.mViewPortHandler.setDragOffsetY(offset);
    }

    public boolean hasNoDragOffset() {
        return this.mViewPortHandler.hasNoDragOffset();
    }

    public XAxisRenderer getRendererXAxis() {
        return this.mXAxisRenderer;
    }

    public void setXAxisRenderer(XAxisRenderer xAxisRenderer) {
        this.mXAxisRenderer = xAxisRenderer;
    }

    public YAxisRenderer getRendererLeftYAxis() {
        return this.mAxisRendererLeft;
    }

    public void setRendererLeftYAxis(YAxisRenderer rendererLeftYAxis) {
        this.mAxisRendererLeft = rendererLeftYAxis;
    }

    public YAxisRenderer getRendererRightYAxis() {
        return this.mAxisRendererRight;
    }

    public void setRendererRightYAxis(YAxisRenderer rendererRightYAxis) {
        this.mAxisRendererRight = rendererRightYAxis;
    }

    public float getYChartMax() {
        return Math.max(this.mAxisLeft.mAxisMaximum, this.mAxisRight.mAxisMaximum);
    }

    public float getYChartMin() {
        return Math.min(this.mAxisLeft.mAxisMinimum, this.mAxisRight.mAxisMinimum);
    }

    public boolean isAnyAxisInverted() {
        if (this.mAxisLeft.isInverted() || this.mAxisRight.isInverted()) {
            return true;
        }
        return false;
    }

    public void setAutoScaleMinMaxEnabled(boolean enabled) {
        this.mAutoScaleMinMaxEnabled = enabled;
    }

    public boolean isAutoScaleMinMaxEnabled() {
        return this.mAutoScaleMinMaxEnabled;
    }

    public void setPaint(Paint p, int which) {
        super.setPaint(p, which);
        switch (which) {
            case 4:
                this.mGridBackgroundPaint = p;
                return;
            default:
                return;
        }
    }

    public Paint getPaint(int which) {
        Paint p = super.getPaint(which);
        if (p != null) {
            return p;
        }
        switch (which) {
            case 4:
                return this.mGridBackgroundPaint;
            default:
                return null;
        }
    }
}
