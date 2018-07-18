package com.coinbase.android.pricechart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutPriceChartBinding;
import com.coinbase.android.pricechart.PriceChartData.HighlightedPrice;
import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import com.coinbase.android.pricechart.PriceChartPresenter.Period;
import com.coinbase.android.ui.AutoResizeTextView;
import com.coinbase.android.utils.Utils;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.LineDataProvider;
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import rx.subjects.PublishSubject;

@ControllerScope
public class PriceChartLayout extends RelativeLayout implements PriceChartScreen, OnChartGestureListener, OnChartValueSelectedListener {
    private static float CHART_BOTTOM_PADDING_PERCENTAGE = 0.0f;
    private static final int CHART_COLOR = -1;
    private static final int CHART_LINE_COLOR = 0;
    private static final float CHART_LINE_WIDTH = 1.0f;
    private static final int CHART_TEXT_COLOR = Color.argb(130, 255, 255, 255);
    private static final float CHART_TEXT_SIZE = 12.0f;
    private static float CHART_TOP_PADDING_PERCENTAGE = AutoResizeTextView.MIN_TEXT_SIZE;
    private static final Typeface CHART_TYPEFACE = Typeface.create("Roboto", 0);
    private static final float CUBIC_INTENSITY = 0.05f;
    private static final int LIMIT_LINE_COLOR = 2131689652;
    private static final float LIMIT_LINE_DASHED_LINE_LENGTH = 5.0f;
    private static final float LIMIT_LINE_DASHED_SPACE_LENGTH = 5.0f;
    private static final float LIMIT_LINE_WIDTH = 0.6f;
    private static final float LINE_WIDTH = 1.0f;
    private static final int MARKER_OFFSET_PADDING = 5;
    private static final int NUM_STEP_LINES = 3;
    private static final int STEP_LINE_COLOR_ALPHA = 50;
    private static final float X_OFFSET = 15.0f;
    private static final float Y_OFFSET = 15.0f;
    private int mAxisLineColor;
    private LayoutPriceChartBinding mBinding;
    private int mChartColor;
    private int mChartFillAlpha;
    private Context mContext;
    private boolean mCubicEnabled;
    private PublishSubject<CurrencyUnit> mCurrencyUpdatedSubject;
    private PublishSubject<HighlightedPrice> mHighlightedPriceSubject;
    private Highlight[] mHighlightedValues;
    private boolean mIsTouchEnabled;
    private int mLimitLineColor;
    private boolean mLimitLineVisible;
    private int mMinMaxMarkerColor;
    private boolean mMinMaxMarkersVisible;
    private PublishSubject<Boolean> mMotionSubject;
    @Inject
    PriceChartPresenter mPresenter;
    private PublishSubject<Boolean> mScrollConnectorSubject;
    private PublishSubject<SpotPrice> mSpotPriceUpdatedSubject;
    private boolean mStepLinesVisible;
    private WindowManager mWindowManager;
    private DateTime[] mXAxisDates;
    private int mXAxisLabelColor;
    private boolean mXAxisLabelVisible;

    public class CustomMarkerView extends MarkerView {
        private LinearLayout llLinearLayout = ((LinearLayout) findViewById(R.id.llContainer));
        private TextView tvContent = ((TextView) findViewById(R.id.tvText));

        public CustomMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
        }

        public void refreshContent(Entry e, Highlight highlight) {
            Utils.updateBackgroundColor(this.llLinearLayout, PriceChartLayout.this.mMinMaxMarkerColor);
            this.tvContent.setText(PriceChartLayout.this.mPresenter.getFormattedPriceMarkerText(e.getVal()));
        }

        public int getXOffset(float xpos) {
            int markerViewWidth = getWidth();
            int xPosInt = (int) xpos;
            int desiredOffset = -(markerViewWidth / 2);
            int desiredXStart = xPosInt + desiredOffset;
            int desiredXEnd = desiredXStart + markerViewWidth;
            int maxXOffset = PriceChartLayout.this.mBinding.cvPriceChart.getWidth() - 5;
            if (desiredXStart < 5) {
                return 5 - xPosInt;
            }
            if (desiredXEnd > maxXOffset) {
                return (desiredOffset + maxXOffset) - desiredXEnd;
            }
            return desiredOffset;
        }

        public int getYOffset(float ypos) {
            return -(getHeight() / 2);
        }
    }

    private static class PriceValueFormatter implements YAxisValueFormatter {
        private PriceValueFormatter() {
        }

        public String getFormattedValue(float value, YAxis yAxis) {
            return "";
        }
    }

    public PriceChartLayout(Context context) {
        this(context, null);
    }

    public PriceChartLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PriceChartLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mChartColor = -1;
        this.mChartFillAlpha = 0;
        this.mAxisLineColor = 0;
        this.mXAxisLabelColor = CHART_TEXT_COLOR;
        this.mLimitLineColor = R.color.price_chart_limit_line;
        this.mLimitLineVisible = false;
        this.mStepLinesVisible = true;
        this.mXAxisLabelVisible = false;
        this.mMinMaxMarkersVisible = false;
        this.mCubicEnabled = false;
        this.mIsTouchEnabled = true;
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        this.mBinding = LayoutPriceChartBinding.inflate(LayoutInflater.from(this.mContext), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addPriceChartLayoutSubcomponent(new PriceChartPresenterModule(this)).inject(this);
        initChartProperties();
    }

    public void setChartColor(int chartColor) {
        this.mChartColor = chartColor;
    }

    public void setChartFillAlpha(int alpha) {
        this.mChartFillAlpha = alpha;
    }

    public void setAxisLineColor(int axisLineColor) {
        this.mAxisLineColor = axisLineColor;
    }

    public void setXAxisLabelColor(int axisLabelColor) {
        this.mXAxisLabelColor = axisLabelColor;
    }

    public void showXAxisLabel(boolean isVisible) {
        this.mXAxisLabelVisible = isVisible;
    }

    public void setLimitLineColor(int color) {
        this.mLimitLineColor = color;
    }

    public void showLimitLines(boolean isVisible) {
        this.mLimitLineVisible = isVisible;
    }

    public void showStepLines(boolean isVisible) {
        this.mStepLinesVisible = isVisible;
    }

    public void showMinMaxMarkersVisible(boolean isVisible) {
        this.mMinMaxMarkersVisible = isVisible;
    }

    public void setDrawCubicEnabled(boolean isEnabled) {
        this.mCubicEnabled = isEnabled;
    }

    public void setTouchEnabled(boolean isEnabled) {
        this.mBinding.cvPriceChart.setTouchEnabled(isEnabled);
        this.mIsTouchEnabled = isEnabled;
    }

    public void setCurrencyCode(String baseCurrencyCode) {
        setCurrencyCode(baseCurrencyCode, null);
    }

    public void setCurrencyCode(String baseCurrencyCode, Period period) {
        this.mPresenter.setCurrencyCode(baseCurrencyCode, period);
    }

    public void setSpotPriceSubject(PublishSubject<SpotPrice> spotPriceUpdatedSubject) {
        this.mSpotPriceUpdatedSubject = spotPriceUpdatedSubject;
    }

    public void setHighlightedPriceSubject(PublishSubject<HighlightedPrice> highlightedPriceSubject) {
        this.mHighlightedPriceSubject = highlightedPriceSubject;
    }

    public void setCurrencyUpdatedSubject(PublishSubject<CurrencyUnit> currencyUpdatedSubject) {
        this.mCurrencyUpdatedSubject = currencyUpdatedSubject;
    }

    public void setMotionSubject(PublishSubject<Boolean> motionSubject) {
        this.mMotionSubject = motionSubject;
    }

    public void setScrollConnectorSubject(PublishSubject<Boolean> scrollConnectorSubject) {
        this.mScrollConnectorSubject = scrollConnectorSubject;
    }

    public void setMinMaxMarkerColor(int minMaxMarkerColor) {
        this.mMinMaxMarkerColor = minMaxMarkerColor;
    }

    public void listenForPeriodUpdate() {
        this.mPresenter.listenForPeriodUpdate();
    }

    public void onDestroy() {
        this.mPresenter.onDestroy();
    }

    public void setChartVisibility() {
        this.mBinding.tvPriceChartFailure.setVisibility(8);
        this.mBinding.cvPriceChart.setVisibility(4);
        this.mBinding.ivPriceChart.setVisibility(4);
        this.mBinding.progressPriceChart.setVisibility(0);
    }

    public void hidePriceChartProgress() {
        this.mBinding.progressPriceChart.setVisibility(8);
    }

    public void handleFailureError() {
        this.mBinding.tvPriceChartFailure.setVisibility(0);
        if (Utils.isConnectedOrConnecting(getContext())) {
            this.mBinding.tvPriceChartFailure.setText(R.string.pricechart_could_not_load);
        } else {
            this.mBinding.tvPriceChartFailure.setText(R.string.pricechart_coult_not_load_no_internet);
        }
    }

    public void loadChartData(PriceChartData priceData) {
        if (priceData != null && priceData.getDates() != null && priceData.getPriceList() != null) {
            int i;
            prepareXAxis(this.mBinding.cvPriceChart, priceData.getDates());
            prepareYAxis(this.mBinding.cvPriceChart);
            this.mXAxisDates = priceData.getDates();
            ArrayList<String> xVals = new ArrayList();
            for (i = 0; i < this.mXAxisDates.length; i++) {
                xVals.add("lol");
            }
            List<Float> priceList = priceData.getPriceList();
            ArrayList<Entry> yVals = new ArrayList();
            for (i = 0; i < priceList.size(); i++) {
                yVals.add(new Entry(((Float) priceList.get(i)).floatValue(), i));
            }
            LineDataSet set1 = new LineDataSet(yVals, null);
            set1.setColor(this.mChartColor);
            set1.setCircleColor(this.mChartColor);
            set1.setLineWidth(1.0f);
            set1.setDrawValues(false);
            set1.setCircleSize(0.5f);
            set1.setDrawCircleHole(false);
            set1.setFillAlpha(this.mChartFillAlpha);
            set1.setFillColor(this.mChartColor);
            set1.setDrawFilled(true);
            set1.setHighLightColor(this.mChartColor);
            set1.setDrawCubic(this.mCubicEnabled);
            set1.setDrawHorizontalHighlightIndicator(false);
            if (this.mCubicEnabled) {
                set1.setCubicIntensity(CUBIC_INTENSITY);
            }
            set1.setFillFormatter(new FillFormatter() {
                public float getFillLinePosition(LineDataSet dataSet, LineDataProvider dataProvider) {
                    return dataSet.getYMin() - ((dataSet.getYMax() - dataSet.getYMin()) * 0.2f);
                }
            });
            ArrayList<LineDataSet> dataSets = new ArrayList();
            dataSets.add(set1);
            this.mBinding.cvPriceChart.setData(new LineData(xVals, dataSets));
            Legend l = this.mBinding.cvPriceChart.getLegend();
            l.setForm(LegendForm.LINE);
            l.setEnabled(false);
            updateSpotPrice(priceData.getSpotPrice());
            setLimitLines();
            setMinMaxMarkers(priceData);
            showMinMaxPriceMarkers();
            this.mBinding.cvPriceChart.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    PriceChartLayout.this.mBinding.cvPriceChart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if (PriceChartLayout.this.mIsTouchEnabled) {
                        PriceChartLayout.this.mBinding.cvPriceChart.setVisibility(0);
                        PriceChartLayout.this.mBinding.ivPriceChart.setVisibility(4);
                        return;
                    }
                    Bitmap chartBimap = PriceChartLayout.this.mBinding.cvPriceChart.getChartBitmap();
                    if (chartBimap == null) {
                        PriceChartLayout.this.mBinding.cvPriceChart.setVisibility(0);
                        PriceChartLayout.this.mBinding.ivPriceChart.setVisibility(4);
                        return;
                    }
                    PriceChartLayout.this.mBinding.cvPriceChart.setVisibility(4);
                    PriceChartLayout.this.mBinding.ivPriceChart.setVisibility(0);
                    PriceChartLayout.this.mBinding.ivPriceChart.setImageBitmap(chartBimap);
                }
            });
            this.mBinding.cvPriceChart.invalidate();
        }
    }

    public void updateSpotPrice(SpotPrice spotPrice) {
        if (this.mSpotPriceUpdatedSubject != null) {
            this.mSpotPriceUpdatedSubject.onNext(spotPrice);
        }
    }

    public void setCurrency(CurrencyUnit unit) {
        if (this.mCurrencyUpdatedSubject != null) {
            this.mCurrencyUpdatedSubject.onNext(unit);
        }
    }

    private void initChartProperties() {
        this.mBinding.cvPriceChart.setDrawGridBackground(false);
        this.mBinding.cvPriceChart.setDescription("");
        this.mBinding.cvPriceChart.setTouchEnabled(true);
        this.mBinding.cvPriceChart.setHighlightPerTapEnabled(false);
        this.mBinding.cvPriceChart.setHighlightPerDragEnabled(true);
        this.mBinding.cvPriceChart.setOnChartValueSelectedListener(this);
        this.mBinding.cvPriceChart.setOnChartGestureListener(this);
        this.mBinding.cvPriceChart.setDragEnabled(true);
        this.mBinding.cvPriceChart.setScaleEnabled(false);
        this.mBinding.cvPriceChart.setPinchZoom(false);
        this.mBinding.cvPriceChart.setDoubleTapToZoomEnabled(false);
        this.mBinding.cvPriceChart.getAxisRight().setEnabled(false);
        this.mMinMaxMarkerColor = ContextCompat.getColor(this.mContext, R.color.price_chart_marker);
        this.mBinding.cvPriceChart.setMarkerView(new CustomMarkerView(getContext(), R.layout.layout_price_chart_marker_view));
        this.mBinding.cvPriceChart.setVisibility(4);
    }

    private void prepareYAxis(ZeroMarginLineChart chart) {
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setStartAtZero(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setPosition(YAxisLabelPosition.INSIDE_CHART);
        leftAxis.setXOffset(15.0f);
        leftAxis.setValueFormatter(new PriceValueFormatter());
        leftAxis.setAxisLineColor(this.mAxisLineColor);
        leftAxis.setAxisLineWidth(1.0f);
        leftAxis.setGridColor(this.mAxisLineColor);
        leftAxis.setGridLineWidth(1.0f);
        leftAxis.setSpaceTop(CHART_TOP_PADDING_PERCENTAGE);
        leftAxis.setSpaceBottom(CHART_BOTTOM_PADDING_PERCENTAGE);
        leftAxis.setTextSize(CHART_TEXT_SIZE);
        leftAxis.setLabelCount(5, true);
        leftAxis.setTypeface(CHART_TYPEFACE);
        int gapSize = Utils.getPixels(getContext(), 80);
        int lineLength = Utils.getPixels(getContext(), 5000);
        leftAxis.enableGridDashedLine((float) lineLength, (float) gapSize, (float) lineLength);
    }

    private void prepareXAxis(ZeroMarginLineChart chart, DateTime[] dates) {
        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setDrawLabels(this.mXAxisLabelVisible);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setYOffset(15.0f);
        xAxis.setAxisLineColor(this.mAxisLineColor);
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setTextColor(this.mXAxisLabelColor);
        xAxis.setTextSize(CHART_TEXT_SIZE);
        xAxis.setTypeface(CHART_TYPEFACE);
        xAxis.setValueFormatter(this.mPresenter.getDateFormatter(dates));
        Paint paint = new Paint();
        Rect bounds = new Rect();
        paint.setTypeface(CHART_TYPEFACE);
        paint.setTextSize(CHART_TEXT_SIZE);
        String text = "EEE";
        paint.getTextBounds(text, 0, text.length(), bounds);
        int weekdayStringWidthTotal = Utils.getPixels(getContext(), bounds.width() * 7);
        String singleChar = "E";
        Rect singleBounds = new Rect();
        paint.getTextBounds(singleChar, 0, singleChar.length(), singleBounds);
        int singleCharWidth = Utils.getPixels(getContext(), singleBounds.width());
        if (singleCharWidth <= 0) {
            singleCharWidth = 1;
        }
        if (this.mContext == null) {
            xAxis.setSpaceBetweenLabels(5);
            return;
        }
        Display display = this.mWindowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        xAxis.setSpaceBetweenLabels(((size.x - weekdayStringWidthTotal) / singleCharWidth) / 6);
    }

    private void setLimitLines() {
        if (this.mBinding.cvPriceChart != null && this.mBinding.cvPriceChart.getData() != null && this.mLimitLineVisible) {
            YAxis leftAxis = this.mBinding.cvPriceChart.getAxisLeft();
            leftAxis.removeAllLimitLines();
            float yMin = this.mBinding.cvPriceChart.getYMin();
            float yMax = this.mBinding.cvPriceChart.getYMax();
            if (this.mStepLinesVisible) {
                float step = (yMax - yMin) / 4.0f;
                for (int i = 1; i <= 3; i++) {
                    LimitLine stepLine = new LimitLine(yMin + (((float) i) * step));
                    styleStepLine(stepLine, 50);
                    leftAxis.addLimitLine(stepLine);
                }
            }
            LimitLine yMinLine = new LimitLine(yMin);
            styleLimitLine(yMinLine, this.mLimitLineColor);
            leftAxis.addLimitLine(yMinLine);
            LimitLine yMaxLine = new LimitLine(yMax);
            styleLimitLine(yMaxLine, this.mLimitLineColor);
            leftAxis.addLimitLine(yMaxLine);
        }
    }

    private void styleLimitLine(LimitLine limitLine, int lineColor) {
        limitLine.setLineColor(lineColor);
        limitLine.setLineWidth(LIMIT_LINE_WIDTH);
        limitLine.enableDashedLine(5.0f, 5.0f, 0.0f);
    }

    private void styleStepLine(LimitLine limitLine, int alpha) {
        styleLimitLine(limitLine, ColorUtils.setAlphaComponent(this.mLimitLineColor, alpha));
    }

    private void updatePriceSections(Entry e) {
        Money highlightedPrice = this.mPresenter.getMoney(e.getVal());
        Date date = this.mXAxisDates[(this.mXAxisDates.length - 1) - e.getXIndex()].toDate();
        if (this.mHighlightedPriceSubject != null) {
            this.mHighlightedPriceSubject.onNext(new HighlightedPrice(highlightedPrice, date));
        }
    }

    private void handleChartMotionEvent(MotionEvent event) {
        boolean z = true;
        if (event.getAction() == 0 || event.getAction() == 1 || event.getAction() == 3) {
            boolean isTouchDown = event.getAction() == 0;
            YAxis axisLeft = this.mBinding.cvPriceChart.getAxisLeft();
            if (isTouchDown) {
                z = false;
            }
            axisLeft.setEnabled(z);
            if (this.mMotionSubject != null) {
                this.mMotionSubject.onNext(Boolean.valueOf(isTouchDown));
            }
        }
    }

    private void setMinMaxMarkers(PriceChartData priceChartData) {
        if (priceChartData != null && this.mMinMaxMarkersVisible) {
            this.mHighlightedValues = new Highlight[2];
            this.mHighlightedValues[0] = new Highlight(priceChartData.getMinPriceIndex(), 0);
            this.mHighlightedValues[1] = new Highlight(priceChartData.getMaxPriceIndex(), 0);
        }
    }

    private void showMinMaxPriceMarkers() {
        if (this.mMinMaxMarkersVisible && this.mHighlightedValues != null) {
            this.mBinding.cvPriceChart.highlightValues(this.mHighlightedValues);
            this.mBinding.cvPriceChart.setDrawMarkerViews(true);
        }
        ((LineDataSet) this.mBinding.cvPriceChart.getLineData().getDataSetByIndex(0)).setHighLightColor(0);
    }

    private void hideMinMaxPriceMarkers() {
        this.mBinding.cvPriceChart.setDrawMarkerViews(false);
        ((LineDataSet) this.mBinding.cvPriceChart.getLineData().getDataSetByIndex(0)).setHighLightColor(this.mChartColor);
    }

    private void setScrolling(boolean isEnabled) {
        if (this.mScrollConnectorSubject != null) {
            this.mScrollConnectorSubject.onNext(Boolean.valueOf(isEnabled));
        }
    }

    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        setScrolling(false);
        hideMinMaxPriceMarkers();
        updatePriceSections(e);
    }

    public void onNothingSelected() {
    }

    public void onChartGestureStart(MotionEvent me, ChartGesture lastPerformedGesture) {
        hideMinMaxPriceMarkers();
        Highlight h = this.mBinding.cvPriceChart.getHighlightByTouchPoint(me.getX(), me.getY());
        this.mBinding.cvPriceChart.highlightValue(h);
        if (h != null) {
            updatePriceSections(((LineData) this.mBinding.cvPriceChart.getData()).getEntryForHighlight(h));
        }
        handleChartMotionEvent(me);
    }

    public void onChartGestureEnd(MotionEvent me, ChartGesture lastPerformedGesture) {
        setScrolling(true);
        showMinMaxPriceMarkers();
        handleChartMotionEvent(me);
    }

    public void onChartLongPressed(MotionEvent me) {
    }

    public void onChartDoubleTapped(MotionEvent me) {
    }

    public void onChartSingleTapped(MotionEvent me) {
    }

    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
    }

    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
    }

    public void onChartTranslate(MotionEvent me, float dX, float dY) {
    }
}
