package com.coinbase.android.phone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.phoneNumber.Data;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumbers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.functions.Func0;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class PhoneNumberPresenter implements VerifyPhoneCaller {
    private static final String FIRST_LAUNCH_CALLED = "first_launch_called";
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private List<Data> mPhoneNumberList = new ArrayList();
    private final PhoneNumberScreen mPhoneNumberScreen;
    private final PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final SuccessRouter mSuccessRouter;
    private final Func0<VerifyPhoneHandler> mVerifyPhoneHandlerFunc;

    @Inject
    public PhoneNumberPresenter(LoginManager loginManager, PhoneNumberScreen phoneNumberScreen, SnackBarWrapper snackBarWrapper, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, SuccessRouter successRouter, @MainScheduler Scheduler mainScheduler) {
        this.mLoginManager = loginManager;
        this.mPhoneNumberScreen = phoneNumberScreen;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mPhoneNumbersUpdatedConnector = phoneNumbersUpdatedConnector;
        this.mSuccessRouter = successRouter;
        this.mVerifyPhoneHandlerFunc = PhoneNumberPresenter$$Lambda$1.lambdaFactory$(this);
        this.mMainScheduler = mainScheduler;
    }

    public PhoneNumberPresenter(LoginManager loginManager, PhoneNumberScreen phoneNumberScreen, SnackBarWrapper snackBarWrapper, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, SuccessRouter successRouter, Func0<VerifyPhoneHandler> verifyPhoneHandlerFunc, @MainScheduler Scheduler mainScheduler) {
        this.mLoginManager = loginManager;
        this.mPhoneNumberScreen = phoneNumberScreen;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mPhoneNumbersUpdatedConnector = phoneNumbersUpdatedConnector;
        this.mSuccessRouter = successRouter;
        this.mVerifyPhoneHandlerFunc = verifyPhoneHandlerFunc;
        this.mMainScheduler = mainScheduler;
    }

    void onResume(Bundle args) {
        this.mSubscription.add(this.mPhoneNumbersUpdatedConnector.get().observeOn(this.mMainScheduler).subscribe(PhoneNumberPresenter$$Lambda$2.lambdaFactory$(this)));
        updatePhoneNumbers(args);
    }

    void onDestroy() {
        this.mSubscription.clear();
    }

    List<Data> getPhoneNumberList() {
        return this.mPhoneNumberList;
    }

    void addNewPhoneNumber() {
        ((VerifyPhoneHandler) this.mVerifyPhoneHandlerFunc.call()).addPhone();
    }

    void onVerifyPhoneNumberClicked(Data phoneNumber) {
        if (phoneNumber != null) {
            this.mPhoneNumberScreen.showVerifyPhoneNumberDialog(phoneNumber.getId());
        }
    }

    void onRemovePhoneNumberClicked(Data phoneNumber) {
        if (phoneNumber != null) {
            this.mPhoneNumberScreen.deletePhoneNumber(phoneNumber.getId(), phoneNumber.getVerified().booleanValue());
        }
    }

    private void updatePhoneNumbers(Bundle args) {
        this.mPhoneNumberScreen.showProgressBar();
        this.mSubscription.add(this.mLoginManager.getClient().getPhoneNumbersRx().observeOn(this.mMainScheduler).subscribe(PhoneNumberPresenter$$Lambda$3.lambdaFactory$(this, args), PhoneNumberPresenter$$Lambda$4.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$updatePhoneNumbers$2(PhoneNumberPresenter this_, Bundle args, Pair pair) {
        this_.mPhoneNumberScreen.hideProgressBar();
        Response<PhoneNumbers> response = pair.first;
        if (response.isSuccessful()) {
            this_.mPhoneNumberList.clear();
            this_.mPhoneNumberList.addAll(((PhoneNumbers) response.body()).getData());
            this_.mPhoneNumberScreen.notifyDataSetChanged();
            if (this_.mSuccessRouter.shouldRouteSuccess() && this_.hasVerifiedPhoneNumber(this_.mPhoneNumberList)) {
                this_.mSuccessRouter.routeSuccess();
                return;
            } else if (this_.isFirstLaunch(args)) {
                this_.setFirstLaunchCalled(args);
                if (this_.mPhoneNumberList.isEmpty()) {
                    this_.addNewPhoneNumber();
                    return;
                }
                return;
            } else {
                return;
            }
        }
        this_.mSnackBarWrapper.showError(response, (Retrofit) pair.second);
    }

    static /* synthetic */ void lambda$updatePhoneNumbers$3(PhoneNumberPresenter this_, Throwable t) {
        this_.mPhoneNumberScreen.hideProgressBar();
        this_.handleErrorResponse(t);
    }

    private void handleErrorResponse(Throwable t) {
        this.mSnackBarWrapper.showFailure(t);
    }

    private void onPhoneNumbersUpdated(Object event) {
        updatePhoneNumbers(null);
        showPhoneNumberUpdatedMessage(event);
    }

    void showPhoneNumberUpdatedMessage(Object event) {
        if (event != null) {
            if (event instanceof String) {
                this.mSnackBarWrapper.show((String) event);
            } else if (event instanceof Integer) {
                this.mSnackBarWrapper.show(((Integer) event).intValue());
            }
        }
    }

    private boolean hasVerifiedPhoneNumber(List<Data> phoneNumberList) {
        if (phoneNumberList == null || phoneNumberList.isEmpty()) {
            return false;
        }
        for (Data data : phoneNumberList) {
            if (data.getVerified() != null && data.getVerified().booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isFirstLaunch(Bundle args) {
        if (args == null || args.containsKey(FIRST_LAUNCH_CALLED)) {
            return false;
        }
        return true;
    }

    void setFirstLaunchCalled(Bundle args) {
        if (args != null) {
            args.putBoolean(FIRST_LAUNCH_CALLED, true);
        }
    }

    public Activity getActivity() {
        return this.mPhoneNumberScreen.getActivity();
    }

    public FragmentManager getCallerFragmentManager() {
        return ((AppCompatActivity) this.mPhoneNumberScreen.getActivity()).getSupportFragmentManager();
    }

    public ActionBarController getCallingController() {
        return this.mPhoneNumberScreen.getController();
    }

    public Context getContext() {
        return getActivity();
    }

    public boolean isForeground() {
        return this.mPhoneNumberScreen.isShown();
    }
}
