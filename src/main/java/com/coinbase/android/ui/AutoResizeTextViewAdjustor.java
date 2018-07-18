package com.coinbase.android.ui;

import android.widget.TextView;
import com.coinbase.android.R;
import java.util.LinkedList;
import java.util.List;

public class AutoResizeTextViewAdjustor {
    private final List<TextView> mAdjustingTextViews = new LinkedList();
    private float mMinTextView = 0.0f;
    private float mReferenceSizePx = 0.0f;
    private final List<AutoResizeTextView> mWatchedAutoResizeTextViews = new LinkedList();

    public void addViews(TextView... textViews) {
        for (TextView textView : textViews) {
            addView(textView);
        }
    }

    public void setReferenceView(TextView textView) {
        if (this.mReferenceSizePx == 0.0f) {
            this.mReferenceSizePx = textView.getTextSize();
        }
    }

    public void addView(TextView textView) {
        if (textView instanceof AutoResizeTextView) {
            AutoResizeTextView autoResizeTextView = (AutoResizeTextView) textView;
            autoResizeTextView.setOnResizeListener(AutoResizeTextViewAdjustor$$Lambda$1.lambdaFactory$(this));
            this.mWatchedAutoResizeTextViews.add(autoResizeTextView);
            adjustViewIfNeccessary(autoResizeTextView);
            return;
        }
        this.mAdjustingTextViews.add(textView);
        adjustViewIfNeccessary(textView);
    }

    static /* synthetic */ void lambda$addView$0(AutoResizeTextViewAdjustor this_, TextView resizedTextView, float oldSize, float newSize) {
        if (this_.mMinTextView == 0.0f || newSize < this_.mMinTextView) {
            this_.mMinTextView = newSize;
        }
        this_.addTagToViewIfNeeded(resizedTextView, oldSize);
        this_.updateViews(resizedTextView);
    }

    private void updateViews(TextView resizedTextView) {
        if (this.mMinTextView != 0.0f) {
            for (TextView textView : this.mAdjustingTextViews) {
                adjustViewIfNeccessary(textView);
            }
            for (TextView textView2 : this.mWatchedAutoResizeTextViews) {
                if (resizedTextView != textView2) {
                    adjustViewIfNeccessary(textView2);
                }
            }
        }
    }

    private void adjustViewIfNeccessary(TextView textView) {
        if (this.mMinTextView != 0.0f && !this.mWatchedAutoResizeTextViews.isEmpty()) {
            float currentSize = textView.getTextSize();
            addTagToViewIfNeeded(textView, currentSize);
            float adjustedSize = this.mMinTextView;
            if (this.mReferenceSizePx > 0.0f) {
                adjustedSize = (((Float) textView.getTag(R.string.resized_by_reference)).floatValue() / this.mReferenceSizePx) * this.mMinTextView;
            }
            if (currentSize != adjustedSize) {
                textView.setTextSize(0, adjustedSize);
            }
        }
    }

    private void addTagToViewIfNeeded(TextView textView, float size) {
        if (textView.getTag(R.string.resized_by_reference) == null) {
            textView.setTag(R.string.resized_by_reference, Float.valueOf(size));
        }
    }
}
