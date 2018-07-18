package com.coinbase.zxing.client.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ScanTabsWidget extends LinearLayout {
    Button mLeftButton;
    OnClickListener mOnClickListener;
    Button mRightButton;
    boolean mRightTabSelected = false;

    public ScanTabsWidget(Context context) {
        super(context);
        inflateView(context);
    }

    public ScanTabsWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
    }

    public ScanTabsWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        inflateView(context);
    }

    public ScanTabsWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs);
        inflateView(context);
    }

    private void inflateView(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.two_button_widget, this, true);
        this.mLeftButton = (Button) findViewById(R.id.left_button);
        if (this.mLeftButton != null) {
            this.mLeftButton.setBackgroundResource(R.drawable.widget_left_drawable_button_selected);
            this.mLeftButton.setTextColor(-16777216);
            this.mLeftButton.setTransformationMethod(null);
            this.mRightButton = (Button) findViewById(R.id.right_button);
            this.mRightButton.setBackgroundResource(R.drawable.widget_right_drawable_button_deselected);
            this.mRightButton.setTextColor(-1);
            this.mRightButton.setTransformationMethod(null);
            this.mLeftButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (ScanTabsWidget.this.mRightTabSelected) {
                        ScanTabsWidget.this.mRightTabSelected = false;
                        ScanTabsWidget.this.setTabSelected(ScanTabsWidget.this.mRightTabSelected);
                        if (ScanTabsWidget.this.mOnClickListener != null) {
                            ScanTabsWidget.this.mOnClickListener.onClick(ScanTabsWidget.this);
                        }
                    }
                }
            });
            this.mRightButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (!ScanTabsWidget.this.mRightTabSelected) {
                        ScanTabsWidget.this.mRightTabSelected = true;
                        ScanTabsWidget.this.setTabSelected(ScanTabsWidget.this.mRightTabSelected);
                        if (ScanTabsWidget.this.mOnClickListener != null) {
                            ScanTabsWidget.this.mOnClickListener.onClick(ScanTabsWidget.this);
                        }
                    }
                }
            });
            this.mRightTabSelected = false;
        }
    }

    public void setTabSelected(boolean rightTabSelected) {
        this.mRightTabSelected = rightTabSelected;
        if (this.mRightTabSelected) {
            this.mLeftButton.setTextColor(-1);
            this.mLeftButton.setBackgroundResource(R.drawable.widget_left_drawable_button_deselected);
            this.mRightButton.setTextColor(-16777216);
            this.mRightButton.setBackgroundResource(R.drawable.widget_right_drawable_button_selected);
            return;
        }
        this.mLeftButton.setTextColor(-16777216);
        this.mLeftButton.setBackgroundResource(R.drawable.widget_left_drawable_button_selected);
        this.mRightButton.setTextColor(-1);
        this.mRightButton.setBackgroundResource(R.drawable.widget_right_drawable_button_deselected);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public boolean getTabSelected() {
        return this.mRightTabSelected;
    }
}
