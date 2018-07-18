package com.coinbase.android;

import android.app.Application;
import android.view.View;
import android.widget.RelativeLayout;
import com.coinbase.android.ui.BackgroundDimmer.BlockingOverlay;
import com.coinbase.android.ui.CoinbaseResources.GenericErrorMessage;
import com.coinbase.android.ui.CoinbaseResources.GenericErrorTryAgainMessage;
import com.coinbase.android.ui.RootViewWrapper;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SnackBarWrapper.SnackBarRoot;
import rx.functions.Func0;

public class NonBottomNavActivityModule {
    @ActivityScope
    public RootViewWrapper providesRootViewWrapper(@SnackBarRoot Func0<View> rootView, @BlockingOverlay Func0<RelativeLayout> blockingOverlayView) {
        return new RootViewWrapper(rootView, NonBottomNavActivityModule$$Lambda$1.lambdaFactory$(blockingOverlayView));
    }

    @ActivityScope
    public SnackBarWrapper providesSnackBarWrapper(Application app, @GenericErrorMessage int genericeErrorRes, @GenericErrorTryAgainMessage int genericErrorTryAgainRes, RootViewWrapper rootViewWrapper) {
        return new SnackBarWrapper(app, genericeErrorRes, genericErrorTryAgainRes, rootViewWrapper);
    }
}
