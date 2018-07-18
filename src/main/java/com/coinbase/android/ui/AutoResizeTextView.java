package com.coinbase.android.ui;

import android.content.Context;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class AutoResizeTextView extends TextView {
    public static final float MIN_TEXT_SIZE = 20.0f;
    private static final String mEllipsis = "...";
    private boolean mAddEllipsis;
    private float mMaxTextSize;
    private float mMinTextSize;
    private boolean mNeedsResize;
    private float mSpacingAdd;
    private float mSpacingMult;
    private OnTextResizeListener mTextResizeListener;
    private float mTextSize;

    public interface OnTextResizeListener {
        void onTextResize(TextView textView, float f, float f2);
    }

    public AutoResizeTextView(Context context) {
        this(context, null);
    }

    public AutoResizeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mNeedsResize = false;
        this.mMaxTextSize = 0.0f;
        this.mMinTextSize = MIN_TEXT_SIZE;
        this.mSpacingMult = 1.0f;
        this.mSpacingAdd = 0.0f;
        this.mAddEllipsis = true;
        this.mTextSize = getTextSize();
    }

    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        this.mNeedsResize = true;
        resetTextSize();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            this.mNeedsResize = true;
        }
    }

    public void setOnResizeListener(OnTextResizeListener listener) {
        this.mTextResizeListener = listener;
    }

    public void setTextSize(float size) {
        super.setTextSize(size);
        this.mTextSize = getTextSize();
    }

    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
        this.mTextSize = getTextSize();
    }

    public void setLineSpacing(float add, float mult) {
        super.setLineSpacing(add, mult);
        this.mSpacingMult = mult;
        this.mSpacingAdd = add;
    }

    public float getMaxTextSize() {
        return this.mMaxTextSize;
    }

    public void setMaxTextSize(float maxTextSize) {
        this.mMaxTextSize = maxTextSize;
        requestLayout();
        invalidate();
    }

    public float getMinTextSize() {
        return this.mMinTextSize;
    }

    public void setMinTextSize(float minTextSize) {
        this.mMinTextSize = minTextSize;
        requestLayout();
        invalidate();
    }

    public boolean getAddEllipsis() {
        return this.mAddEllipsis;
    }

    public void setAddEllipsis(boolean addEllipsis) {
        this.mAddEllipsis = addEllipsis;
    }

    public void resetTextSize() {
        if (this.mTextSize > 0.0f) {
            super.setTextSize(0, this.mTextSize);
            this.mMaxTextSize = this.mTextSize;
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed || this.mNeedsResize) {
            resizeText(((right - left) - getCompoundPaddingLeft()) - getCompoundPaddingRight(), ((bottom - top) - getCompoundPaddingBottom()) - getCompoundPaddingTop());
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    public void resizeText() {
        resizeText((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingBottom()) - getPaddingTop());
    }

    public void resizeText(int width, int height) {
        CharSequence text = getText();
        if (text != null && text.length() != 0 && height > 0 && width > 0 && this.mTextSize != 0.0f) {
            float targetTextSize;
            if (getTransformationMethod() != null) {
                text = getTransformationMethod().getTransformation(text, this);
            }
            Paint textPaint = getPaint();
            float oldTextSize = textPaint.getTextSize();
            if (this.mMaxTextSize > 0.0f) {
                targetTextSize = Math.min(this.mTextSize, this.mMaxTextSize);
            } else {
                targetTextSize = this.mTextSize;
            }
            int textHeight = getTextHeight(text, textPaint, width, targetTextSize);
            while (textHeight > height && targetTextSize > this.mMinTextSize) {
                targetTextSize = Math.max(targetTextSize - 2.0f, this.mMinTextSize);
                textHeight = getTextHeight(text, textPaint, width, targetTextSize);
            }
            if (this.mAddEllipsis && targetTextSize == this.mMinTextSize && textHeight > height) {
                StaticLayout layout = new StaticLayout(text, new TextPaint(textPaint), width, Alignment.ALIGN_NORMAL, this.mSpacingMult, this.mSpacingAdd, false);
                if (layout.getLineCount() > 0) {
                    int lastLine = layout.getLineForVertical(height) - 1;
                    if (lastLine < 0) {
                        setText("");
                    } else {
                        int start = layout.getLineStart(lastLine);
                        int end = layout.getLineEnd(lastLine);
                        float lineWidth = layout.getLineWidth(lastLine);
                        float ellipseWidth = textPaint.measureText(mEllipsis);
                        while (((float) width) < lineWidth + ellipseWidth) {
                            end--;
                            Paint paint = textPaint;
                            lineWidth = paint.measureText(text.subSequence(start, end + 1).toString());
                        }
                        setText(text.subSequence(0, end) + mEllipsis);
                    }
                }
            }
            setTextSize(0, targetTextSize);
            setLineSpacing(this.mSpacingAdd, this.mSpacingMult);
            if (this.mTextResizeListener != null) {
                this.mTextResizeListener.onTextResize(this, oldTextSize, targetTextSize);
            }
            this.mNeedsResize = false;
        }
    }

    private int getTextHeight(CharSequence source, TextPaint paint, int width, float textSize) {
        TextPaint paintCopy = new TextPaint(paint);
        paintCopy.setTextSize(textSize);
        return new StaticLayout(source, paintCopy, width, Alignment.ALIGN_NORMAL, this.mSpacingMult, this.mSpacingAdd, true).getHeight();
    }
}
