package com.coinbase.android.ui;

import android.view.View;
import android.widget.RelativeLayout;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.BackgroundDimmer.BlockingOverlay;
import com.coinbase.android.ui.CoinbaseResources.GenericErrorMessage;
import com.coinbase.android.ui.CoinbaseResources.GenericErrorTryAgainMessage;
import com.coinbase.android.ui.SnackBarWrapper.SnackBarRoot;
import rx.functions.Func0;

public class BaseViewModule {
    private final BaseViewProvider mBaseViewProvider;

    public BaseViewModule(BaseViewProvider baseViewProvider) {
        this.mBaseViewProvider = baseViewProvider;
    }

    @BlockingOverlay
    @ActivityScope
    public Func0<RelativeLayout> providesBlockingOverlayViewFunc() {
        return BaseViewModule$$Lambda$1.lambdaFactory$(this);
    }

    @BlockingOverlay
    @ActivityScope
    public RelativeLayout providesBlockingOverlayView(@BlockingOverlay Func0<RelativeLayout> viewFunc) {
        return (RelativeLayout) viewFunc.call();
    }

    @SnackBarRoot
    @ActivityScope
    public Func0<View> providesSnackBarRootViewFunc() {
        return BaseViewModule$$Lambda$2.lambdaFactory$(this);
    }

    @GenericErrorMessage
    @ActivityScope
    int providesGenericErrorRes() {
        return R.string.an_error_occurred;
    }

    @GenericErrorTryAgainMessage
    @ActivityScope
    int providesGenericErrorTryAgainRes() {
        return R.string.error_occurred_try_again;
    }
}
