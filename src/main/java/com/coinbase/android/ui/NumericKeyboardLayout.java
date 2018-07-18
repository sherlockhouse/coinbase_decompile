package com.coinbase.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutNumericKeyboardBinding;
import com.coinbase.android.ui.NumericKeypadConnector.NumericKeypadButton;
import java.text.DecimalFormatSymbols;
import javax.inject.Inject;

@ControllerScope
public class NumericKeyboardLayout extends LinearLayout {
    private LayoutNumericKeyboardBinding mBinding;
    @Inject
    NumericKeypadConnector mNumericKeypadConnector;

    public NumericKeyboardLayout(Context context) {
        this(context, null);
    }

    public NumericKeyboardLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumericKeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mBinding = LayoutNumericKeyboardBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().inject(this);
        setAttributes(context, attrs);
        this.mBinding.tvAmountKeyboard0.setOnClickListener(NumericKeyboardLayout$$Lambda$1.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboard1.setOnClickListener(NumericKeyboardLayout$$Lambda$2.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboard2.setOnClickListener(NumericKeyboardLayout$$Lambda$3.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboard3.setOnClickListener(NumericKeyboardLayout$$Lambda$4.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboard4.setOnClickListener(NumericKeyboardLayout$$Lambda$5.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboard5.setOnClickListener(NumericKeyboardLayout$$Lambda$6.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboard6.setOnClickListener(NumericKeyboardLayout$$Lambda$7.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboard7.setOnClickListener(NumericKeyboardLayout$$Lambda$8.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboard8.setOnClickListener(NumericKeyboardLayout$$Lambda$9.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboard9.setOnClickListener(NumericKeyboardLayout$$Lambda$10.lambdaFactory$(this));
        this.mBinding.tvAmountKeyboardLeft.setText(Character.toString(DecimalFormatSymbols.getInstance().getDecimalSeparator()));
        this.mBinding.tvAmountKeyboardLeft.setOnClickListener(NumericKeyboardLayout$$Lambda$11.lambdaFactory$(this));
        this.mBinding.ivAmountKeyboardRight.setOnClickListener(NumericKeyboardLayout$$Lambda$12.lambdaFactory$(this));
        this.mBinding.ivAmountKeyboardRight.setOnLongClickListener(NumericKeyboardLayout$$Lambda$13.lambdaFactory$(this));
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NumericKeyboardLayout, 0, 0);
            try {
                String leftText = a.getString(0);
                if (!TextUtils.isEmpty(leftText)) {
                    this.mBinding.tvAmountKeyboardLeft.setText(leftText);
                }
                Drawable drawable = a.getDrawable(1);
                if (drawable != null) {
                    this.mBinding.ivAmountKeyboardRight.setImageDrawable(drawable);
                }
                a.recycle();
            } catch (Throwable th) {
                a.recycle();
            }
        }
    }

    private void onNumKeyboardClicked(View view) {
        if (view instanceof TextView) {
            performHapticFeedback(view);
            this.mNumericKeypadConnector.getNumericSubject().onNext(Character.valueOf(((TextView) view).getText().toString().charAt(0)));
        }
    }

    private void onLeftKeyboardClicked(View view) {
        performHapticFeedback(view);
        this.mNumericKeypadConnector.getButtonClickedSubject().onNext(new Pair(NumericKeypadButton.LEFT, this.mBinding.tvAmountKeyboardLeft.getText().toString()));
    }

    private void onRightKeyboardClicked(View view) {
        performHapticFeedback(view);
        this.mNumericKeypadConnector.getButtonClickedSubject().onNext(new Pair(NumericKeypadButton.RIGHT, null));
    }

    private boolean onRightKeyboardLongClicked(View view) {
        performHapticFeedback(view);
        this.mNumericKeypadConnector.getButtonLongClickedSubject().onNext(new Pair(NumericKeypadButton.RIGHT, null));
        return true;
    }

    private void performHapticFeedback(View view) {
        view.performHapticFeedback(1);
    }
}
