package com.github.mikephil.charting.renderer;

import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class AxisRenderer extends Renderer {
    protected Paint mAxisLabelPaint = new Paint(1);
    protected Paint mAxisLinePaint;
    protected Paint mGridPaint = new Paint();
    protected Paint mLimitLinePaint;
    protected Transformer mTrans;

    public AxisRenderer(ViewPortHandler viewPortHandler, Transformer trans) {
        super(viewPortHandler);
        this.mTrans = trans;
        this.mGridPaint.setColor(-7829368);
        this.mGridPaint.setStrokeWidth(1.0f);
        this.mGridPaint.setStyle(Style.STROKE);
        this.mGridPaint.setAlpha(90);
        this.mAxisLinePaint = new Paint();
        this.mAxisLinePaint.setColor(-16777216);
        this.mAxisLinePaint.setStrokeWidth(1.0f);
        this.mAxisLinePaint.setStyle(Style.STROKE);
        this.mLimitLinePaint = new Paint(1);
        this.mLimitLinePaint.setStyle(Style.STROKE);
    }

    public Paint getPaintAxisLabels() {
        return this.mAxisLabelPaint;
    }
}
