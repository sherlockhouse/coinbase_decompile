package com.github.mikephil.charting.charts;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.animation.EasingFunction;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.ChartInterface;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public abstract class Chart<T extends ChartData<? extends DataSet<? extends Entry>>> extends ViewGroup implements ChartInterface {
    public static final String LOG_TAG = "MPAndroidChart";
    public static final int PAINT_CENTER_TEXT = 14;
    public static final int PAINT_DESCRIPTION = 11;
    public static final int PAINT_GRID_BACKGROUND = 4;
    public static final int PAINT_HOLE = 13;
    public static final int PAINT_INFO = 7;
    public static final int PAINT_LEGEND_LABEL = 18;
    protected ChartAnimator mAnimator;
    protected ChartTouchListener mChartTouchListener;
    protected T mData = null;
    protected boolean mDataNotSet = true;
    protected ValueFormatter mDefaultFormatter;
    protected float mDeltaX = 1.0f;
    protected Paint mDescPaint;
    protected String mDescription = "Description";
    private PointF mDescriptionPosition;
    private boolean mDragDecelerationEnabled = true;
    private float mDragDecelerationFrictionCoef = 0.9f;
    protected boolean mDrawMarkerViews = true;
    protected Paint mDrawPaint;
    private float mExtraBottomOffset = 0.0f;
    private float mExtraLeftOffset = 0.0f;
    private float mExtraRightOffset = 0.0f;
    private float mExtraTopOffset = 0.0f;
    private OnChartGestureListener mGestureListener;
    protected boolean mHighLightPerTapEnabled = true;
    protected ChartHighlighter mHighlighter;
    protected Highlight[] mIndicesToHighlight;
    protected Paint mInfoPaint;
    protected ArrayList<Runnable> mJobs = new ArrayList();
    protected Legend mLegend;
    protected LegendRenderer mLegendRenderer;
    protected boolean mLogEnabled = false;
    protected MarkerView mMarkerView;
    private String mNoDataText = "No chart data available.";
    private String mNoDataTextDescription;
    private boolean mOffsetsCalculated = false;
    protected DataRenderer mRenderer;
    protected OnChartValueSelectedListener mSelectionListener;
    protected boolean mTouchEnabled = true;
    protected ViewPortHandler mViewPortHandler;
    protected float mXChartMax = 0.0f;
    protected float mXChartMin = 0.0f;

    protected abstract void calcMinMax();

    protected abstract void calculateOffsets();

    protected abstract float[] getMarkerPosition(Entry entry, Highlight highlight);

    public abstract void notifyDataSetChanged();

    public Chart(Context context) {
        super(context);
        init();
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Chart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected void init() {
        setWillNotDraw(false);
        if (VERSION.SDK_INT < 11) {
            this.mAnimator = new ChartAnimator();
        } else {
            this.mAnimator = new ChartAnimator(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Chart.this.postInvalidate();
                }
            });
        }
        Utils.init(getContext());
        this.mDefaultFormatter = new DefaultValueFormatter(1);
        this.mViewPortHandler = new ViewPortHandler();
        this.mLegend = new Legend();
        this.mLegendRenderer = new LegendRenderer(this.mViewPortHandler, this.mLegend);
        this.mDescPaint = new Paint(1);
        this.mDescPaint.setColor(-16777216);
        this.mDescPaint.setTextAlign(Align.RIGHT);
        this.mDescPaint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mInfoPaint = new Paint(1);
        this.mInfoPaint.setColor(Color.rgb(247, 189, 51));
        this.mInfoPaint.setTextAlign(Align.CENTER);
        this.mInfoPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        this.mDrawPaint = new Paint(4);
        if (this.mLogEnabled) {
            Log.i("", "Chart.init()");
        }
    }

    public void setData(T data) {
        if (data == null) {
            Log.e(LOG_TAG, "Cannot set data for chart. Provided data object is null.");
            return;
        }
        this.mDataNotSet = false;
        this.mOffsetsCalculated = false;
        this.mData = data;
        calculateFormatter(data.getYMin(), data.getYMax());
        for (DataSet<?> set : this.mData.getDataSets()) {
            if (set.needsDefaultFormatter()) {
                set.setValueFormatter(this.mDefaultFormatter);
            }
        }
        notifyDataSetChanged();
        if (this.mLogEnabled) {
            Log.i(LOG_TAG, "Data is set.");
        }
    }

    public void clear() {
        this.mData = null;
        this.mDataNotSet = true;
        this.mIndicesToHighlight = null;
        invalidate();
    }

    public void clearValues() {
        this.mData.clearValues();
        invalidate();
    }

    public boolean isEmpty() {
        if (this.mData != null && this.mData.getYValCount() > 0) {
            return false;
        }
        return true;
    }

    protected void calculateFormatter(float min, float max) {
        float reference;
        if (this.mData == null || this.mData.getXValCount() < 2) {
            reference = Math.max(Math.abs(min), Math.abs(max));
        } else {
            reference = Math.abs(max - min);
        }
        this.mDefaultFormatter = new DefaultValueFormatter(Utils.getDecimals(reference));
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDataNotSet || this.mData == null || this.mData.getYValCount() <= 0) {
            canvas.drawText(this.mNoDataText, (float) (getWidth() / 2), (float) (getHeight() / 2), this.mInfoPaint);
            if (!TextUtils.isEmpty(this.mNoDataTextDescription)) {
                canvas.drawText(this.mNoDataTextDescription, (float) (getWidth() / 2), ((float) (getHeight() / 2)) + ((-this.mInfoPaint.ascent()) + this.mInfoPaint.descent()), this.mInfoPaint);
            }
        } else if (!this.mOffsetsCalculated) {
            calculateOffsets();
            this.mOffsetsCalculated = true;
        }
    }

    protected void drawDescription(Canvas c) {
        if (!this.mDescription.equals("")) {
            if (this.mDescriptionPosition == null) {
                c.drawText(this.mDescription, (((float) getWidth()) - this.mViewPortHandler.offsetRight()) - 10.0f, (((float) getHeight()) - this.mViewPortHandler.offsetBottom()) - 10.0f, this.mDescPaint);
            } else {
                c.drawText(this.mDescription, this.mDescriptionPosition.x, this.mDescriptionPosition.y, this.mDescPaint);
            }
        }
    }

    public Highlight[] getHighlighted() {
        return this.mIndicesToHighlight;
    }

    public boolean isHighlightPerTapEnabled() {
        return this.mHighLightPerTapEnabled;
    }

    public void setHighlightPerTapEnabled(boolean enabled) {
        this.mHighLightPerTapEnabled = enabled;
    }

    public boolean valuesToHighlight() {
        return (this.mIndicesToHighlight == null || this.mIndicesToHighlight.length <= 0 || this.mIndicesToHighlight[0] == null) ? false : true;
    }

    public void highlightValues(Highlight[] highs) {
        this.mIndicesToHighlight = highs;
        if (highs == null || highs.length <= 0 || highs[0] == null) {
            this.mChartTouchListener.setLastHighlighted(null);
        } else {
            this.mChartTouchListener.setLastHighlighted(highs[0]);
        }
        invalidate();
    }

    public void highlightValue(int xIndex, int dataSetIndex) {
        if (xIndex < 0 || dataSetIndex < 0 || xIndex >= this.mData.getXValCount() || dataSetIndex >= this.mData.getDataSetCount()) {
            highlightValues(null);
            return;
        }
        highlightValues(new Highlight[]{new Highlight(xIndex, dataSetIndex)});
    }

    public void highlightValue(Highlight highlight) {
        highlightValue(highlight, false);
    }

    public void highlightValue(Highlight high, boolean callListener) {
        Entry e = null;
        if (high == null) {
            this.mIndicesToHighlight = null;
        } else {
            if (this.mLogEnabled) {
                Log.i(LOG_TAG, "Highlighted: " + high.toString());
            }
            e = this.mData.getEntryForHighlight(high);
            if (e == null || e.getXIndex() != high.getXIndex()) {
                this.mIndicesToHighlight = null;
                high = null;
            } else {
                this.mIndicesToHighlight = new Highlight[]{high};
            }
        }
        if (callListener && this.mSelectionListener != null) {
            if (valuesToHighlight()) {
                this.mSelectionListener.onValueSelected(e, high.getDataSetIndex(), high);
            } else {
                this.mSelectionListener.onNothingSelected();
            }
        }
        invalidate();
    }

    @Deprecated
    public void highlightTouch(Highlight high) {
        highlightValue(high, true);
    }

    public void setOnTouchListener(ChartTouchListener l) {
        this.mChartTouchListener = l;
    }

    protected void drawMarkers(Canvas canvas) {
        if (this.mMarkerView != null && this.mDrawMarkerViews && valuesToHighlight()) {
            int i = 0;
            while (i < this.mIndicesToHighlight.length) {
                Highlight highlight = this.mIndicesToHighlight[i];
                int xIndex = highlight.getXIndex();
                int dataSetIndex = highlight.getDataSetIndex();
                if (((float) xIndex) <= this.mDeltaX && ((float) xIndex) <= this.mDeltaX * this.mAnimator.getPhaseX()) {
                    Entry e = this.mData.getEntryForHighlight(this.mIndicesToHighlight[i]);
                    if (e != null && e.getXIndex() == this.mIndicesToHighlight[i].getXIndex()) {
                        float[] pos = getMarkerPosition(e, highlight);
                        if (this.mViewPortHandler.isInBounds(pos[0], pos[1])) {
                            this.mMarkerView.refreshContent(e, highlight);
                            this.mMarkerView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                            this.mMarkerView.layout(0, 0, this.mMarkerView.getMeasuredWidth(), this.mMarkerView.getMeasuredHeight());
                            if (pos[1] - ((float) this.mMarkerView.getHeight()) <= 0.0f) {
                                this.mMarkerView.draw(canvas, pos[0], pos[1] + (((float) this.mMarkerView.getHeight()) - pos[1]));
                            } else {
                                this.mMarkerView.draw(canvas, pos[0], pos[1]);
                            }
                        }
                    }
                }
                i++;
            }
        }
    }

    public ChartAnimator getAnimator() {
        return this.mAnimator;
    }

    public boolean isDragDecelerationEnabled() {
        return this.mDragDecelerationEnabled;
    }

    public void setDragDecelerationEnabled(boolean enabled) {
        this.mDragDecelerationEnabled = enabled;
    }

    public float getDragDecelerationFrictionCoef() {
        return this.mDragDecelerationFrictionCoef;
    }

    public void setDragDecelerationFrictionCoef(float newValue) {
        if (newValue < 0.0f) {
            newValue = 0.0f;
        }
        if (newValue >= 1.0f) {
            newValue = 0.999f;
        }
        this.mDragDecelerationFrictionCoef = newValue;
    }

    public void animateXY(int durationMillisX, int durationMillisY, EasingFunction easingX, EasingFunction easingY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY, easingX, easingY);
    }

    public void animateX(int durationMillis, EasingFunction easing) {
        this.mAnimator.animateX(durationMillis, easing);
    }

    public void animateY(int durationMillis, EasingFunction easing) {
        this.mAnimator.animateY(durationMillis, easing);
    }

    public void animateXY(int durationMillisX, int durationMillisY, EasingOption easingX, EasingOption easingY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY, easingX, easingY);
    }

    public void animateX(int durationMillis, EasingOption easing) {
        this.mAnimator.animateX(durationMillis, easing);
    }

    public void animateY(int durationMillis, EasingOption easing) {
        this.mAnimator.animateY(durationMillis, easing);
    }

    public void animateX(int durationMillis) {
        this.mAnimator.animateX(durationMillis);
    }

    public void animateY(int durationMillis) {
        this.mAnimator.animateY(durationMillis);
    }

    public void animateXY(int durationMillisX, int durationMillisY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY);
    }

    public ValueFormatter getDefaultValueFormatter() {
        return this.mDefaultFormatter;
    }

    public void setOnChartValueSelectedListener(OnChartValueSelectedListener l) {
        this.mSelectionListener = l;
    }

    public void setOnChartGestureListener(OnChartGestureListener l) {
        this.mGestureListener = l;
    }

    public OnChartGestureListener getOnChartGestureListener() {
        return this.mGestureListener;
    }

    public float getYMax() {
        return this.mData.getYMax();
    }

    public float getYMin() {
        return this.mData.getYMin();
    }

    public float getXChartMax() {
        return this.mXChartMax;
    }

    public float getXChartMin() {
        return this.mXChartMin;
    }

    public int getXValCount() {
        return this.mData.getXValCount();
    }

    public int getValueCount() {
        return this.mData.getYValCount();
    }

    public PointF getCenter() {
        return new PointF(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
    }

    public PointF getCenterOffsets() {
        return this.mViewPortHandler.getContentCenter();
    }

    public void setDescription(String desc) {
        if (desc == null) {
            desc = "";
        }
        this.mDescription = desc;
    }

    public void setDescriptionPosition(float x, float y) {
        this.mDescriptionPosition = new PointF(x, y);
    }

    public void setDescriptionTypeface(Typeface t) {
        this.mDescPaint.setTypeface(t);
    }

    public void setDescriptionTextSize(float size) {
        if (size > 16.0f) {
            size = 16.0f;
        }
        if (size < 6.0f) {
            size = 6.0f;
        }
        this.mDescPaint.setTextSize(Utils.convertDpToPixel(size));
    }

    public void setDescriptionColor(int color) {
        this.mDescPaint.setColor(color);
    }

    public void setExtraOffsets(float left, float top, float right, float bottom) {
        setExtraLeftOffset(left);
        setExtraTopOffset(top);
        setExtraRightOffset(right);
        setExtraBottomOffset(bottom);
    }

    public void setExtraTopOffset(float offset) {
        this.mExtraTopOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraTopOffset() {
        return this.mExtraTopOffset;
    }

    public void setExtraRightOffset(float offset) {
        this.mExtraRightOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraRightOffset() {
        return this.mExtraRightOffset;
    }

    public void setExtraBottomOffset(float offset) {
        this.mExtraBottomOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraBottomOffset() {
        return this.mExtraBottomOffset;
    }

    public void setExtraLeftOffset(float offset) {
        this.mExtraLeftOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraLeftOffset() {
        return this.mExtraLeftOffset;
    }

    public void setLogEnabled(boolean enabled) {
        this.mLogEnabled = enabled;
    }

    public boolean isLogEnabled() {
        return this.mLogEnabled;
    }

    public void setNoDataText(String text) {
        this.mNoDataText = text;
    }

    public void setNoDataTextDescription(String text) {
        this.mNoDataTextDescription = text;
    }

    public void setTouchEnabled(boolean enabled) {
        this.mTouchEnabled = enabled;
    }

    public void setMarkerView(MarkerView v) {
        this.mMarkerView = v;
    }

    public MarkerView getMarkerView() {
        return this.mMarkerView;
    }

    public Legend getLegend() {
        return this.mLegend;
    }

    public LegendRenderer getLegendRenderer() {
        return this.mLegendRenderer;
    }

    public RectF getContentRect() {
        return this.mViewPortHandler.getContentRect();
    }

    public void disableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    public void enableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    public void setPaint(Paint p, int which) {
        switch (which) {
            case 7:
                this.mInfoPaint = p;
                return;
            case 11:
                this.mDescPaint = p;
                return;
            default:
                return;
        }
    }

    public Paint getPaint(int which) {
        switch (which) {
            case 7:
                return this.mInfoPaint;
            case 11:
                return this.mDescPaint;
            default:
                return null;
        }
    }

    public boolean isDrawMarkerViewEnabled() {
        return this.mDrawMarkerViews;
    }

    public void setDrawMarkerViews(boolean enabled) {
        this.mDrawMarkerViews = enabled;
    }

    public String getXValue(int index) {
        if (this.mData == null || this.mData.getXValCount() <= index) {
            return null;
        }
        return (String) this.mData.getXVals().get(index);
    }

    public List<Entry> getEntriesAtIndex(int xIndex) {
        List<Entry> vals = new ArrayList();
        for (int i = 0; i < this.mData.getDataSetCount(); i++) {
            Entry e = this.mData.getDataSetByIndex(i).getEntryForXIndex(xIndex);
            if (e != null) {
                vals.add(e);
            }
        }
        return vals;
    }

    public T getData() {
        return this.mData;
    }

    public float getPercentOfTotal(float val) {
        return (val / this.mData.getYValueSum()) * 100.0f;
    }

    public ViewPortHandler getViewPortHandler() {
        return this.mViewPortHandler;
    }

    public DataRenderer getRenderer() {
        return this.mRenderer;
    }

    public void setRenderer(DataRenderer renderer) {
        if (renderer != null) {
            this.mRenderer = renderer;
        }
    }

    public PointF getCenterOfView() {
        return getCenter();
    }

    public Bitmap getChartBitmap() {
        Bitmap returnedBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.RGB_565);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        draw(canvas);
        return returnedBitmap;
    }

    public boolean saveToPath(String title, String pathOnSD) {
        OutputStream outputStream;
        Exception e;
        Bitmap b = getChartBitmap();
        try {
            OutputStream stream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + pathOnSD + "/" + title + ".png");
            try {
                b.compress(CompressFormat.PNG, 40, stream);
                stream.close();
                outputStream = stream;
                return true;
            } catch (Exception e2) {
                e = e2;
                outputStream = stream;
                e.printStackTrace();
                return false;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveToGallery(String fileName, int quality) {
        IOException e;
        if (quality < 0 || quality > 100) {
            quality = 50;
        }
        long currentTime = System.currentTimeMillis();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM");
        if (!file.exists() && !file.mkdirs()) {
            return false;
        }
        String filePath = file.getAbsolutePath() + "/" + fileName;
        try {
            FileOutputStream out = new FileOutputStream(filePath);
            try {
                getChartBitmap().compress(CompressFormat.JPEG, quality, out);
                out.flush();
                out.close();
                long size = new File(filePath).length();
                ContentValues values = new ContentValues(8);
                values.put("title", fileName);
                values.put("_display_name", fileName);
                values.put("date_added", Long.valueOf(currentTime));
                values.put("mime_type", "image/jpeg");
                values.put("description", "MPAndroidChart-Library Save");
                values.put("orientation", Integer.valueOf(0));
                values.put("_data", filePath);
                values.put("_size", Long.valueOf(size));
                if (getContext().getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values) == null) {
                    return false;
                }
                return true;
            } catch (IOException e2) {
                e = e2;
                FileOutputStream fileOutputStream = out;
                e.printStackTrace();
                return false;
            }
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            return false;
        }
    }

    public void addJob(Runnable job) {
        this.mJobs.add(job);
    }

    public void removeJob(Runnable job) {
        this.mJobs.remove(job);
    }

    public void clearAllJobs() {
        this.mJobs.clear();
    }

    public ArrayList<Runnable> getJobs() {
        return this.mJobs;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(left, top, right, bottom);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = (int) Utils.convertDpToPixel(50.0f);
        setMeasuredDimension(Math.max(getSuggestedMinimumWidth(), resolveSize(size, widthMeasureSpec)), Math.max(getSuggestedMinimumHeight(), resolveSize(size, heightMeasureSpec)));
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (this.mLogEnabled) {
            Log.i(LOG_TAG, "OnSizeChanged()");
        }
        if (w > 0 && h > 0 && w < 10000 && h < 10000) {
            this.mViewPortHandler.setChartDimens((float) w, (float) h);
            if (this.mLogEnabled) {
                Log.i(LOG_TAG, "Setting chart dimens, width: " + w + ", height: " + h);
            }
            Iterator i$ = this.mJobs.iterator();
            while (i$.hasNext()) {
                post((Runnable) i$.next());
            }
            this.mJobs.clear();
        }
        notifyDataSetChanged();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setHardwareAccelerationEnabled(boolean enabled) {
        if (VERSION.SDK_INT < 11) {
            Log.e(LOG_TAG, "Cannot enable/disable hardware acceleration for devices below API level 11.");
        } else if (enabled) {
            setLayerType(2, null);
        } else {
            setLayerType(1, null);
        }
    }
}
