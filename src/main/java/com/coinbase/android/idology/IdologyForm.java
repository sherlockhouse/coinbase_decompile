package com.coinbase.android.idology;

import android.app.Application;
import android.content.Context;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.idology.Data;
import com.coinbase.api.internal.models.idology.Idology;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.errors.Errors;
import com.coinbase.v2.models.user.DateOfBirth;
import java.util.HashMap;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;

@ControllerScope
public class IdologyForm {
    private final Context mContext;
    private final IdologyFormErrorMatcher mIdologyFormErrorMatcher;
    private final IdologyRetryConnector mIdologyRetryConnector;
    private final String mIdologyTrackingContext;
    private final IdologyVerificationConnector mIdologyVerificationConnector;
    private final Logger mLogger = LoggerFactory.getLogger(IdologyForm.class);
    private final LoginManager mLoginManager;
    @MainScheduler
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final ProgressConnector mProgressConnector;
    private final SnackBarWrapper mSnackBarWrapper;

    @Inject
    public IdologyForm(Application app, MixpanelTracking mixpanelTracking, LoginManager loginManager, @MainScheduler Scheduler mainScheduler, ProgressConnector progressConnector, SnackBarWrapper snackBarWrapper, IdologyRetryConnector idologyRetryConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyFormErrorMatcher idologyFormErrorMatcher, @IdologyTrackingContext String idologyTrackingContext) {
        this.mContext = app;
        this.mMixpanelTracking = mixpanelTracking;
        this.mLoginManager = loginManager;
        this.mMainScheduler = mainScheduler;
        this.mProgressConnector = progressConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mIdologyRetryConnector = idologyRetryConnector;
        this.mIdologyVerificationConnector = idologyVerificationConnector;
        this.mIdologyFormErrorMatcher = idologyFormErrorMatcher;
        this.mIdologyTrackingContext = idologyTrackingContext;
    }

    Subscription submitForm(Data buildingIdology) {
        Observable<Pair<Response<Idology>, Retrofit>> createIdentificationVerificationObservable = this.mLoginManager.getClient().createIdentificationVerificationRx(getIdologyParams(buildingIdology));
        this.mProgressConnector.get().onNext(Boolean.valueOf(true));
        return createIdentificationVerificationObservable.first().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(IdologyForm$$Lambda$1.lambdaFactory$(this, buildingIdology), IdologyForm$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$submitForm$0(IdologyForm this_, Data buildingIdology, Pair pair) {
        this_.mProgressConnector.get().onNext(Boolean.valueOf(false));
        Response response = pair.first;
        if (response.isSuccessful()) {
            this_.mIdologyVerificationConnector.get().onNext(((Idology) response.body()).getData());
            return;
        }
        try {
            Errors errors = Utils.getErrors(response);
            if (!this_.mIdologyFormErrorMatcher.isIdologyFormError(errors)) {
                if (errors != null) {
                    this_.mSnackBarWrapper.show(Utils.getErrorMessage(errors));
                    String id = (errors.getErrors() == null || errors.getErrors().get(0) == null) ? "generic_id" : ((ErrorBody) errors.getErrors().get(0)).getId();
                    this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, "error", id, "error_message", errorMessage, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this_.mIdologyTrackingContext);
                } else {
                    this_.mSnackBarWrapper.showGenericError();
                    this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, "error", "generic", MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this_.mIdologyTrackingContext);
                }
                this_.mIdologyRetryConnector.get().onNext(Boolean.valueOf(true));
            } else if (!this_.mIdologyFormErrorMatcher.filterErrorMessages(errors.getErrors())) {
                this_.mIdologyVerificationConnector.get().onNext(buildingIdology);
            }
        } catch (Exception e) {
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, "error", "generic", MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this_.mIdologyTrackingContext);
            this_.mSnackBarWrapper.showGenericError();
            this_.mLogger.error("Couldn't parse error body, shouldn't happen", e);
        }
    }

    static /* synthetic */ void lambda$submitForm$1(IdologyForm this_, Throwable t) {
        this_.mProgressConnector.get().onNext(Boolean.valueOf(false));
        this_.mSnackBarWrapper.show(Utils.getMessage(this_.mContext, t));
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, "error", errorMessage, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this_.mIdologyTrackingContext);
        this_.mIdologyRetryConnector.get().onNext(Boolean.valueOf(true));
    }

    HashMap<String, Object> getIdologyParams(Data buildingIdology) {
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.FIRST_NAME, buildingIdology.getFirstName());
        params.put(ApiConstants.LAST_NAME, buildingIdology.getLastName());
        if (buildingIdology.getAddress() != null) {
            params.put(ApiConstants.ADDRESS1, buildingIdology.getAddress().getLine1());
            params.put(ApiConstants.ADDRESS2, buildingIdology.getAddress().getLine2());
            params.put(ApiConstants.CITY, buildingIdology.getAddress().getCity());
            if (!this.mLoginManager.getUserIsInEU()) {
                params.put("state", buildingIdology.getAddress().getState().toUpperCase());
            }
            params.put(ApiConstants.ZIP, buildingIdology.getAddress().getPostalCode());
            if (this.mLoginManager.getUserIsInEU()) {
                params.put("country", this.mLoginManager.getActiveUserCountryCode());
            }
        }
        DateOfBirth dob = buildingIdology.getDateOfBirth();
        if (dob != null) {
            if (dob.getMonth() != null) {
                params.put(ApiConstants.DOB_MONTH, dob.getMonth());
            }
            if (dob.getDay() != null) {
                params.put(ApiConstants.DOB_DAY, dob.getDay());
            }
            if (dob.getYear() != null) {
                params.put(ApiConstants.DOB_YEAR, dob.getYear());
            }
        }
        if (!this.mLoginManager.getUserIsInEU()) {
            params.put(ApiConstants.SSN_LAST4, buildingIdology.getSsnLast4());
        }
        params.put(ApiConstants.USES_COINBASE_FOR, buildingIdology.getUsesCoinbaseFor());
        params.put(ApiConstants.PRIMARY_SOURCE_OF_FUNDS, buildingIdology.getPrimarySourceOfFunds());
        if (buildingIdology.getCurrentJob() != null) {
            params.put(ApiConstants.CURRENT_JOB_TITLE, buildingIdology.getCurrentJob().getTitle());
            params.put(ApiConstants.CURRENT_EMPLOYER, buildingIdology.getCurrentJob().getEmployer());
        }
        params.put(ApiConstants.ALLOW_QUESTIONS, "true");
        return params;
    }
}
