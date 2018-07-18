package com.coinbase.android.idology;

import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.idology.IdologyFormErrorMatcher.IdologyKnownFields;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;
import com.coinbase.android.signin.IdologyOptionSelectedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.idology.Data;
import com.coinbase.api.internal.models.idology.Job;
import com.coinbase.api.internal.models.idology.options.IdologyOptions;
import com.coinbase.api.internal.models.idology.options.IdologyOptions.OptionType;
import com.coinbase.v2.models.errors.ErrorBody;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class IdologySourceOfFundsFormPresenter {
    private final Scheduler mBackgroundScheduler;
    String mCoinbaseUses;
    private Data mIdologyData;
    private final IdologyForm mIdologyForm;
    private final IdologyFormErrorMatcher mIdologyFormErrorMatcher;
    private final IdologyOptionSelectedConnector mIdologyOptionSelectedConnector;
    private Map<OptionType, IdologyOptions> mIdologyOptions = new HashMap();
    private final IdologyFormValidConnector mIdologySourceOfFundsFormValidConnector;
    private final String mIdologyTrackingContext;
    String mJobTitle;
    private final Logger mLogger = LoggerFactory.getLogger(IdologySourceOfFundsFormPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private Map<Integer, String> mMixPanelEventMap;
    private final MixpanelTracking mMixpanelTracking;
    private final ProgressConnector mProgressConnector;
    private final IdologySourceOfFundsFormScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    String mSourceOfFunds;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public IdologySourceOfFundsFormPresenter(LoginManager loginManager, IdologySourceOfFundsFormScreen screen, IdologyForm idologyForm, IdologyFormErrorMatcher idologyFormErrorMatcher, IdologyOptionSelectedConnector idologyOptionSelectedConnector, IdologyFormValidConnector idologyFormValidConnector, ProgressConnector progressConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, @IdologyTrackingContext String idologyTrackingContext, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mIdologyForm = idologyForm;
        this.mIdologyFormErrorMatcher = idologyFormErrorMatcher;
        this.mIdologyOptionSelectedConnector = idologyOptionSelectedConnector;
        this.mIdologySourceOfFundsFormValidConnector = idologyFormValidConnector;
        this.mProgressConnector = progressConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMixpanelTracking = mixpanelTracking;
        this.mIdologyTrackingContext = idologyTrackingContext;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
        initMixPanelEventMap();
    }

    void init() {
        this.mProgressConnector.get().onNext(Boolean.valueOf(false));
        fetchIdologyOptions();
    }

    void onShow() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_SOURCE_OF_FUNDS_VIEWED, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        this.mSubscription.add(this.mIdologyOptionSelectedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(IdologySourceOfFundsFormPresenter$$Lambda$1.lambdaFactory$(this), IdologySourceOfFundsFormPresenter$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyFormErrorMatcher.getFormErrors().onBackpressureLatest().observeOn(this.mMainScheduler).filter(IdologySourceOfFundsFormPresenter$$Lambda$3.lambdaFactory$()).subscribe(IdologySourceOfFundsFormPresenter$$Lambda$4.lambdaFactory$(this), IdologySourceOfFundsFormPresenter$$Lambda$5.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$2(List errorBodies) {
        boolean z = (errorBodies == null || errorBodies.isEmpty()) ? false : true;
        return Boolean.valueOf(z);
    }

    void onHide() {
        this.mSubscription.clear();
    }

    void setData(Data idology) {
        this.mIdologyData = idology;
        prefillFormData();
    }

    void submitForm(Data idology) {
        if (isFormValid()) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_SUBMIT, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
            this.mSubscription.add(this.mIdologyForm.submitForm(Data.builder(idology).setPrimarySourceOfFunds(this.mSourceOfFunds).setCurrentJob(Job.builder().setEmployer(this.mScreen.getCurrentEmployerText()).setTitle(this.mJobTitle).build()).setUsesCoinbaseFor(Arrays.asList(new String[]{this.mCoinbaseUses})).build()));
        }
    }

    void onCoinbaseUsesClicked() {
        IdologyOptions coinbaseUsesOption = (IdologyOptions) this.mIdologyOptions.get(OptionType.COINBASE_USES);
        if (coinbaseUsesOption == null) {
            showGenericError();
        } else {
            this.mScreen.setOptionsDialog(coinbaseUsesOption, OptionType.COINBASE_USES);
        }
    }

    void onSourceOfFundsClicked() {
        IdologyOptions jobTitlesOptions = (IdologyOptions) this.mIdologyOptions.get(OptionType.SOURCE_OF_FUNDS);
        if (jobTitlesOptions == null) {
            showGenericError();
        } else {
            this.mScreen.setOptionsDialog(jobTitlesOptions, OptionType.SOURCE_OF_FUNDS);
        }
    }

    void onJobTitleClicked() {
        IdologyOptions sourceOfFundsOptions = (IdologyOptions) this.mIdologyOptions.get(OptionType.JOB_TITLES);
        if (sourceOfFundsOptions == null) {
            showGenericError();
        } else {
            this.mScreen.setOptionsDialog(sourceOfFundsOptions, OptionType.JOB_TITLES);
        }
    }

    void onFormUpdated() {
        this.mIdologySourceOfFundsFormValidConnector.get().onNext(Boolean.valueOf(isFormValid()));
    }

    void showGenericError() {
        this.mSnackBarWrapper.showGenericError();
    }

    void onFocusRequested(int viewId) {
        String eventTrackingName = (String) this.mMixPanelEventMap.get(Integer.valueOf(viewId));
        if (eventTrackingName != null) {
            this.mMixpanelTracking.trackEvent(eventTrackingName, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        }
    }

    private void initMixPanelEventMap() {
        this.mMixPanelEventMap = new HashMap();
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etCoinbaseUses), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_USE_FOR_COINBASE);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etSourceOfFunds), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_SOURCE_OF_FUNDS);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etJobTitle), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_JOB_TITLE);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etEmployer), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_EMPLOYER);
    }

    private void prefillFormData() {
        if (this.mIdologyData != null && this.mIdologyOptions.size() != 0) {
            List<String> usesCoinbaseFor = this.mIdologyData.getUsesCoinbaseFor();
            IdologyOptions coinbaseUsesOption = (IdologyOptions) this.mIdologyOptions.get(OptionType.COINBASE_USES);
            if (usesCoinbaseFor != null && !usesCoinbaseFor.isEmpty() && coinbaseUsesOption != null && coinbaseUsesOption.getData() != null) {
                String coinbaseUses = (String) usesCoinbaseFor.get(0);
                for (com.coinbase.api.internal.models.idology.options.Data option : coinbaseUsesOption.getData()) {
                    if (option.getValue().equals(coinbaseUses)) {
                        setSelectedOption(option, OptionType.COINBASE_USES);
                        break;
                    }
                }
            }
            Job currentJob = this.mIdologyData.getCurrentJob();
            if (currentJob != null) {
                this.mScreen.prefillCurrentEmployer(currentJob.getEmployer());
                IdologyOptions jobTitleOption = (IdologyOptions) this.mIdologyOptions.get(OptionType.JOB_TITLES);
                if (jobTitleOption != null && jobTitleOption.getData() != null) {
                    String jobTitle = currentJob.getTitle();
                    for (com.coinbase.api.internal.models.idology.options.Data option2 : jobTitleOption.getData()) {
                        if (option2.getValue().equals(jobTitle)) {
                            setSelectedOption(option2, OptionType.JOB_TITLES);
                            break;
                        }
                    }
                }
            }
            String sourceOfFunds = this.mIdologyData.getPrimarySourceOfFunds();
            IdologyOptions sourceOfFundsOptions = (IdologyOptions) this.mIdologyOptions.get(OptionType.SOURCE_OF_FUNDS);
            if (sourceOfFunds != null && sourceOfFundsOptions != null && sourceOfFundsOptions.getData() != null) {
                for (com.coinbase.api.internal.models.idology.options.Data option22 : sourceOfFundsOptions.getData()) {
                    if (option22.getValue().equals(sourceOfFunds)) {
                        setSelectedOption(option22, OptionType.SOURCE_OF_FUNDS);
                        return;
                    }
                }
            }
        }
    }

    private void fetchIdologyOptions() {
        this.mSubscription.add(Observable.combineLatest(this.mLoginManager.getClient().getCoinbaseUsesRx(), this.mLoginManager.getClient().getSourceOfFundsRx(), this.mLoginManager.getClient().getJobTitlesRx(), IdologySourceOfFundsFormPresenter$$Lambda$6.lambdaFactory$()).onBackpressureLatest().map(IdologySourceOfFundsFormPresenter$$Lambda$7.lambdaFactory$()).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(IdologySourceOfFundsFormPresenter$$Lambda$8.lambdaFactory$(this), IdologySourceOfFundsFormPresenter$$Lambda$9.lambdaFactory$(this)));
    }

    static /* synthetic */ Map lambda$fetchIdologyOptions$6(List response) {
        Map<OptionType, IdologyOptions> idologyOptionsMap = new HashMap();
        Response<IdologyOptions> coinbaseUsesResponse = ((Pair) response.get(0)).first;
        if (!coinbaseUsesResponse.isSuccessful()) {
            return null;
        }
        idologyOptionsMap.put(OptionType.COINBASE_USES, coinbaseUsesResponse.body());
        Response<IdologyOptions> sourceOfFundsResponse = ((Pair) response.get(1)).first;
        if (!sourceOfFundsResponse.isSuccessful()) {
            return null;
        }
        idologyOptionsMap.put(OptionType.SOURCE_OF_FUNDS, sourceOfFundsResponse.body());
        Response<IdologyOptions> jobTitleResponse = ((Pair) response.get(2)).first;
        if (!jobTitleResponse.isSuccessful()) {
            return null;
        }
        idologyOptionsMap.put(OptionType.JOB_TITLES, jobTitleResponse.body());
        return idologyOptionsMap;
    }

    static /* synthetic */ void lambda$fetchIdologyOptions$7(IdologySourceOfFundsFormPresenter this_, Map idologyOptionsMap) {
        if (idologyOptionsMap == null) {
            this_.mSnackBarWrapper.showGenericError();
            return;
        }
        this_.mIdologyOptions.clear();
        this_.mIdologyOptions.putAll(idologyOptionsMap);
        this_.prefillFormData();
    }

    void idologyOptionChosen(Pair<com.coinbase.api.internal.models.idology.options.Data, OptionType> pair) {
        if (pair != null) {
            setSelectedOption((com.coinbase.api.internal.models.idology.options.Data) pair.first, (OptionType) pair.second);
        }
    }

    boolean isFormValid() {
        return (TextUtils.isEmpty(this.mCoinbaseUses) || TextUtils.isEmpty(this.mSourceOfFunds) || TextUtils.isEmpty(this.mJobTitle) || TextUtils.isEmpty(this.mScreen.getCurrentEmployerText())) ? false : true;
    }

    Map<OptionType, IdologyOptions> getIdologyOptions() {
        return this.mIdologyOptions;
    }

    private void setSelectedOption(com.coinbase.api.internal.models.idology.options.Data selectedOption, OptionType optionType) {
        if (selectedOption != null && optionType != null) {
            switch (optionType) {
                case COINBASE_USES:
                    this.mCoinbaseUses = selectedOption.getValue();
                    this.mScreen.setCoinbaseUsesText(selectedOption.getTitle());
                    return;
                case SOURCE_OF_FUNDS:
                    this.mSourceOfFunds = selectedOption.getValue();
                    this.mScreen.setSourceOfFundsText(selectedOption.getTitle());
                    return;
                case JOB_TITLES:
                    this.mJobTitle = selectedOption.getValue();
                    this.mScreen.setJobTitleText(selectedOption.getTitle());
                    return;
                default:
                    return;
            }
        }
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
                case CURRENT_EMPLOYER:
                    this.mScreen.showEmployerFieldError(message);
                    return;
                default:
                    this.mLogger.error("Unhandled error message in source of funds form presenter [" + message + "] shouldn't happen");
                    return;
            }
        }
    }
}
