package com.coinbase.android.idology;

import android.app.Application;
import android.content.Context;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.idology.IdologyFormErrorMatcher.IdologyKnownFields;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.GoogleApiClientWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.idology.Address;
import com.coinbase.api.internal.models.idology.Data;
import com.coinbase.v2.models.errors.ErrorBody;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.functions.Func0;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class IdologyAddressFormPresenter {
    private static final String SWITCHED_TO_MANUAL_ENTRY = "switched_to_manual_entry";
    private Bundle mArgs;
    private final Context mContext;
    private GoogleApiClientWrapper mGoogleApiClient;
    private final Func0<GoogleApiClientWrapper> mGoogleApiClientFunc;
    private final IdologyAutoCompleteShownConnector mIdologyAutoCompleteShownConnector;
    private Data mIdologyData;
    private final IdologyForm mIdologyForm;
    private final IdologyFormErrorMatcher mIdologyFormErrorMatcher;
    private final IdologyFormValidConnector mIdologyFormValidConnector;
    private final String mIdologyTrackingContext;
    private final Logger mLogger = LoggerFactory.getLogger(IdologyAddressFormPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private Map<Integer, String> mMixPanelEventMap;
    private final MixpanelTracking mMixpanelTracking;
    private final IdologyAddressFormScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public IdologyAddressFormPresenter(Application app, LoginManager loginManager, IdologyAddressFormScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyForm idologyForm, IdologyFormErrorMatcher idologyFormErrorMatcher, IdologyAutoCompleteShownConnector idologyAutoCompleteShownConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, @IdologyTrackingContext String idologyTrackingContext, Func0<GoogleApiClientWrapper> googleApiClientFunc, @MainScheduler Scheduler mainScheduler) {
        this.mContext = app;
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mIdologyFormValidConnector = idologyFormValidConnector;
        this.mIdologyForm = idologyForm;
        this.mIdologyFormErrorMatcher = idologyFormErrorMatcher;
        this.mIdologyAutoCompleteShownConnector = idologyAutoCompleteShownConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMixpanelTracking = mixpanelTracking;
        this.mIdologyTrackingContext = idologyTrackingContext;
        this.mGoogleApiClientFunc = googleApiClientFunc;
        this.mMainScheduler = mainScheduler;
        initMixPanelEventMap();
    }

    void onShow(Bundle controllerArgs) {
        this.mArgs = controllerArgs;
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_ADDRESS_VIEWED, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        if (this.mArgs.getBoolean(SWITCHED_TO_MANUAL_ENTRY)) {
            this.mScreen.showManualEntryView();
        } else {
            this.mScreen.showProgressView();
        }
        this.mIdologyAutoCompleteShownConnector.get().onNext(Boolean.valueOf(false));
        this.mScreen.setContinueButtonText(this.mContext.getString(R.string.btn_continue));
        if (this.mGoogleApiClient == null) {
            Api[] placesOptions = new Api[]{Places.GEO_DATA_API, Places.PLACE_DETECTION_API};
            this.mGoogleApiClient = (GoogleApiClientWrapper) this.mGoogleApiClientFunc.call();
            this.mGoogleApiClient.init(placesOptions, new ConnectionCallbacks() {
                public void onConnected(Bundle bundle) {
                    if (!IdologyAddressFormPresenter.this.mArgs.getBoolean(IdologyAddressFormPresenter.SWITCHED_TO_MANUAL_ENTRY)) {
                        IdologyAddressFormPresenter.this.mScreen.showAutoCompleteView();
                        IdologyAddressFormPresenter.this.mIdologyAutoCompleteShownConnector.get().onNext(Boolean.valueOf(true));
                    }
                }

                public void onConnectionSuspended(int i) {
                }
            }, IdologyAddressFormPresenter$$Lambda$1.lambdaFactory$(this));
        }
        this.mSubscription.add(this.mIdologyFormErrorMatcher.getFormErrors().onBackpressureLatest().filter(IdologyAddressFormPresenter$$Lambda$2.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologyAddressFormPresenter$$Lambda$3.lambdaFactory$(this), IdologyAddressFormPresenter$$Lambda$4.lambdaFactory$(this)));
        if (!this.mArgs.getBoolean(SWITCHED_TO_MANUAL_ENTRY)) {
            this.mGoogleApiClient.connect();
        }
        this.mIdologyFormValidConnector.get().onNext(Boolean.valueOf(isFormValid()));
        if (this.mLoginManager.getUserIsInEU()) {
            this.mScreen.hideState();
            this.mScreen.showPostalCode();
        }
    }

    static /* synthetic */ void lambda$onShow$0(IdologyAddressFormPresenter this_, ConnectionResult r) {
        this_.mScreen.setContinueButtonText(this_.mContext.getString(R.string.btn_continue));
        this_.mScreen.showManualEntryView();
        this_.mIdologyAutoCompleteShownConnector.get().onNext(Boolean.valueOf(false));
    }

    static /* synthetic */ Boolean lambda$onShow$1(List errors) {
        boolean z = (errors == null || errors.isEmpty()) ? false : true;
        return Boolean.valueOf(z);
    }

    void onHide() {
        this.mSubscription.clear();
        this.mGoogleApiClient.disconnect();
    }

    void onContinueClicked() {
        if (isFormValid()) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_SUBMIT_ADDRESS, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
            this.mSubscription.add(this.mIdologyForm.submitForm(Data.builder().setAddress(Address.builder().setLine1(this.mScreen.getAddressLine1Text()).setLine2(this.mScreen.getAddressLine2Text()).setCity(this.mScreen.getCityText()).setState(this.mScreen.getStateText()).setPostalCode(this.mScreen.getZipText()).build()).build()));
        }
    }

    boolean onBackPressed() {
        if (!this.mArgs.getBoolean(SWITCHED_TO_MANUAL_ENTRY)) {
            return false;
        }
        this.mArgs.putBoolean(SWITCHED_TO_MANUAL_ENTRY, false);
        this.mScreen.showAutoCompleteView();
        this.mIdologyAutoCompleteShownConnector.get().onNext(Boolean.valueOf(true));
        return true;
    }

    void onAutoCompleteStarted() {
        this.mScreen.showAddressAutoComplete();
    }

    void onAutoCompleteSelected(Place place) {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_ADDRESS_SEARCH_RESULT, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        this.mScreen.showManualEntryView();
        this.mIdologyAutoCompleteShownConnector.get().onNext(Boolean.valueOf(false));
        this.mScreen.setContinueButtonText(this.mContext.getString(R.string.btn_confirm_address));
        fillFormData(place);
        this.mArgs.putBoolean(SWITCHED_TO_MANUAL_ENTRY, true);
    }

    void onAutoCompleteError() {
        switchToManualView();
    }

    void onEnterManuallyClicked() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_IDENTITY_TAPPED_MANUAL_ENTRY, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        switchToManualView();
    }

    void setData(Data idology) {
        this.mIdologyData = idology;
        prefillFormData();
    }

    void onFormUpdated() {
        this.mIdologyFormValidConnector.get().onNext(Boolean.valueOf(isFormValid()));
    }

    void onFocusRequested(int viewId) {
        String eventTrackingName = (String) this.mMixPanelEventMap.get(Integer.valueOf(viewId));
        if (eventTrackingName != null) {
            this.mMixpanelTracking.trackEvent(eventTrackingName, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        }
    }

    private void switchToManualView() {
        this.mScreen.showManualEntryView();
        this.mIdologyAutoCompleteShownConnector.get().onNext(Boolean.valueOf(false));
        this.mScreen.setContinueButtonText(this.mContext.getString(R.string.btn_continue));
        this.mArgs.putBoolean(SWITCHED_TO_MANUAL_ENTRY, true);
    }

    private void initMixPanelEventMap() {
        this.mMixPanelEventMap = new HashMap();
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etAddressLine1), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_ADDRESS_LINE1);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etAddressLine2), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_ADDRESS_LINE2);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etCity), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_CITY_TOWN);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etState), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_STATE);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etZip), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_ZIP);
    }

    private void prefillFormData() {
        if (this.mIdologyData != null) {
            Address address = this.mIdologyData.getAddress();
            if (address != null) {
                this.mScreen.prefillAddress(address.getLine1(), address.getLine2(), address.getCity(), address.getState(), address.getPostalCode());
            }
        }
    }

    private void fillFormData(Place place) {
        try {
            List<android.location.Address> addressList = new Geocoder(this.mContext, Locale.getDefault()).getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);
            if (addressList != null && !addressList.isEmpty()) {
                android.location.Address address = (android.location.Address) addressList.get(0);
                if (address.getMaxAddressLineIndex() >= 0) {
                    String addressLine1 = place.getName() == null ? "" : place.getName().toString();
                    String state = StringUtils.upperCase(address.getAdminArea());
                    String city = address.getLocality();
                    String postalCode = address.getPostalCode();
                    this.mScreen.prefillAddress(addressLine1, "", city, state, postalCode);
                    this.mIdologyFormValidConnector.get().onNext(Boolean.valueOf(isFormValid(addressLine1, city, state, postalCode)));
                }
            }
        } catch (IOException e) {
            this.mLogger.error("Exception reading from geocoder.", e);
            this.mSnackBarWrapper.showGenericError();
        }
    }

    boolean isFormValid() {
        return isFormValid(this.mScreen.getAddressLine1Text(), this.mScreen.getCityText(), this.mScreen.getStateText(), this.mScreen.getZipText());
    }

    boolean isFormValid(String addressLine1, String city, String state, String postalCode) {
        return (TextUtils.isEmpty(addressLine1) || TextUtils.isEmpty(city) || ((TextUtils.isEmpty(state) && !this.mLoginManager.getUserIsInEU()) || TextUtils.isEmpty(postalCode))) ? false : true;
    }

    private void handleErrors(List<ErrorBody> errorBodies) {
        if (errorBodies != null && !errorBodies.isEmpty()) {
            for (ErrorBody errorBody : errorBodies) {
                handleError(errorBody);
            }
        }
    }

    private void handleError(ErrorBody errorBody) {
        if (errorBody != null && !TextUtils.isEmpty(errorBody.getField()) && !TextUtils.isEmpty(errorBody.getMessage())) {
            String field = errorBody.getField();
            String message = errorBody.getMessage();
            IdologyKnownFields idologyKnownFields = IdologyKnownFields.from(field);
            if (idologyKnownFields == null) {
                this.mSnackBarWrapper.showGenericError();
                return;
            }
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, "error_type", errorBody.getField(), "error", errorBody.getId(), "error_message", errorBody.getMessage(), MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
            switch (idologyKnownFields) {
                case ADDRESS1:
                    this.mScreen.showAddress1FieldError(message);
                    return;
                case ADDRESS2:
                    this.mScreen.showAddress2FieldError(message);
                    return;
                case CITY:
                    this.mScreen.showCityFieldError(message);
                    return;
                case STATE:
                    this.mScreen.showStateFieldError(message);
                    return;
                case ZIP:
                    this.mScreen.showZipFieldError(message);
                    return;
                default:
                    this.mLogger.error("Unhandled error message in address form presenter [" + message + "] shouldn't happen");
                    return;
            }
        }
    }
}
