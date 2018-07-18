package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

public abstract class MarkerView extends RelativeLayout {
    public abstract int getXOffset(float f);

    public abstract int getYOffset(float f);

    public abstract void refreshContent(Entry entry, Highlight highlight);

    public MarkerView(Context context, int layoutResource) {
        super(context);
        setupLayoutResource(layoutResource);
    }

    private void setupLayoutResource(int layoutResource) {
        View inflated = LayoutInflater.from(getContext()).inflate(layoutResource, this);
        inflated.setLayoutParams(new LayoutParams(-2, -2));
        inflated.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        inflated.layout(0, 0, inflated.getMeasuredWidth(), inflated.getMeasuredHeight());
    }

    public void draw(Canvas canvas, float posx, float posy) {
        posx += (float) getXOffset(posx);
        posy += (float) getYOffset(posy);
        canvas.translate(posx, posy);
        draw(canvas);
        canvas.translate(-posx, -posy);
    }
}
