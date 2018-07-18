package com.coinbase.android.utils;

import com.coinbase.android.R;
import com.coinbase.android.ui.NumericKeypadConnector;

public class KeyboardUtils {
    public static char getKeyStroke(int viewId) {
        switch (viewId) {
            case R.id.llAmountKeyboard1:
            case R.id.tvAmountKeyboard1:
                return '1';
            case R.id.llAmountKeyboard2:
            case R.id.tvAmountKeyboard2:
                return '2';
            case R.id.llAmountKeyboard3:
            case R.id.tvAmountKeyboard3:
                return '3';
            case R.id.llAmountKeyboard4:
            case R.id.tvAmountKeyboard4:
                return '4';
            case R.id.llAmountKeyboard5:
            case R.id.tvAmountKeyboard5:
                return '5';
            case R.id.llAmountKeyboard6:
            case R.id.tvAmountKeyboard6:
                return '6';
            case R.id.llAmountKeyboard7:
            case R.id.tvAmountKeyboard7:
                return '7';
            case R.id.llAmountKeyboard8:
            case R.id.tvAmountKeyboard8:
                return '8';
            case R.id.llAmountKeyboard9:
            case R.id.tvAmountKeyboard9:
                return '9';
            case R.id.llAmountKeyboardCancel:
                return '-';
            case R.id.llAmountKeyboard0:
            case R.id.tvAmountKeyboard0:
                return NumericKeypadConnector.ZERO;
            case R.id.llAmountKeyboardBack:
            case R.id.ivAmountKeyboardBack:
                return '<';
            default:
                return NumericKeypadConnector.DOT;
        }
    }
}
