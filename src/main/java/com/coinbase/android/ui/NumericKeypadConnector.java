package com.coinbase.android.ui;

import android.util.Pair;
import rx.subjects.PublishSubject;

public class NumericKeypadConnector {
    public static final char DOT = '.';
    public static final char ZERO = '0';
    private final PublishSubject<Pair<NumericKeypadButton, String>> mButtonClickedSubject;
    private final PublishSubject<Pair<NumericKeypadButton, String>> mButtonLongClickedSubject;
    private final PublishSubject<Character> mNumericSubject;

    public enum KeypadType {
        DIGIT,
        DOT,
        DELETE,
        LONG_DELETE
    }

    public enum NumericKeypadButton {
        LEFT,
        RIGHT
    }

    public NumericKeypadConnector() {
        this(PublishSubject.create(), PublishSubject.create(), PublishSubject.create());
    }

    public NumericKeypadConnector(PublishSubject<Character> numericSubject, PublishSubject<Pair<NumericKeypadButton, String>> buttonClickedSubject, PublishSubject<Pair<NumericKeypadButton, String>> buttonLongClickedSubject) {
        this.mNumericSubject = numericSubject;
        this.mButtonClickedSubject = buttonClickedSubject;
        this.mButtonLongClickedSubject = buttonLongClickedSubject;
    }

    public PublishSubject<Character> getNumericSubject() {
        return this.mNumericSubject;
    }

    public PublishSubject<Pair<NumericKeypadButton, String>> getButtonClickedSubject() {
        return this.mButtonClickedSubject;
    }

    public PublishSubject<Pair<NumericKeypadButton, String>> getButtonLongClickedSubject() {
        return this.mButtonLongClickedSubject;
    }
}
