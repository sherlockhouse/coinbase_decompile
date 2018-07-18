package com.coinbase.android.ui;

import android.app.Application;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.coinbase.android.utils.Utils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SnackBarWrapper {
    private final Context mContext;
    private final int mGenericErrorRes;
    private final int mGenericErrorTryAgainRes;
    private final RootViewWrapper mRootViewWrapper;

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SnackBarRoot {
    }

    public SnackBarWrapper(Application app, int genericErrorRes, int genericErrorTryAgainRes, RootViewWrapper rootViewWrapper) {
        this.mContext = app;
        this.mGenericErrorRes = genericErrorRes;
        this.mGenericErrorTryAgainRes = genericErrorTryAgainRes;
        this.mRootViewWrapper = rootViewWrapper;
    }

    public void show(int id) {
        show(this.mContext.getString(id));
    }

    public void show(String str) {
        View rootView = this.mRootViewWrapper.getRoot();
        ((InputMethodManager) this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(rootView.getWindowToken(), 0);
        Snackbar.make(rootView, str, 0).show();
    }

    public void showFailure(Throwable t) {
        show(Utils.getMessage(this.mContext, t));
    }

    @Deprecated
    public void showError(Response<?> response, Retrofit retrofit) {
        showError(response);
    }

    public void showError(Response<?> response) {
        String errorMessage = Utils.getErrorMessage((Response) response);
        if (TextUtils.isEmpty(errorMessage)) {
            showGenericError();
        } else {
            show(errorMessage);
        }
    }

    public void showGenericError() {
        show(this.mGenericErrorRes);
    }

    public void showGenericErrorTryAgain() {
        show(this.mGenericErrorTryAgainRes);
    }
}
