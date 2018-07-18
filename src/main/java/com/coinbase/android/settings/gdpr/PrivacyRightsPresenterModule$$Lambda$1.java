package com.coinbase.android.settings.gdpr;

import android.app.Application;
import com.coinbase.android.settings.gdpr.PrivacyRightsSettingsPreferences.ExercisePrivacyRightsHeader;
import rx.functions.Func0;

final /* synthetic */ class PrivacyRightsPresenterModule$$Lambda$1 implements Func0 {
    private final Application arg$1;

    private PrivacyRightsPresenterModule$$Lambda$1(Application application) {
        this.arg$1 = application;
    }

    public static Func0 lambdaFactory$(Application application) {
        return new PrivacyRightsPresenterModule$$Lambda$1(application);
    }

    public Object call() {
        return new ExercisePrivacyRightsHeader(this.arg$1);
    }
}
