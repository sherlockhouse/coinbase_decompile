package com.coinbase.android.idology;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;
import com.coinbase.android.signin.IdologyOptionSelectedConnector;
import com.coinbase.android.ui.DatePickerConnector;
import com.coinbase.android.ui.DatePickerConnector.DatePickerButton;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.idology.Address;
import com.coinbase.api.internal.models.idology.Data;
import com.coinbase.api.internal.models.idology.Idology;
import com.coinbase.api.internal.models.idology.Job;
import com.coinbase.api.internal.models.idology.options.IdologyOptions;
import com.coinbase.api.internal.models.idology.options.IdologyOptions.OptionType;
import com.coinbase.v2.models.user.DateOfBirth;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class IdologyFormPresenter {
    private static final String DOB_DATE_FORMAT = "MM/dd/yyyy";
    private static final int LEGAL_AGE = 18;
    private static final String SUPPORT_URL = "https://support.coinbase.com/customer/portal/articles/1955104-why-does-coinbase-ask-for-my-personal-information-when-making-certain-transactions-";
    private final Scheduler mBackgroundScheduler;
    String mCoinbaseUses;
    private final Context mContext;
    private final DatePickerConnector mDatePickerConnector;
    private Data mIdologyData;
    private final IdologyFormValidConnector mIdologyFormValidConnector;
    private final IdologyOptionSelectedConnector mIdologyOptionSelectedConnector;
    private Map<OptionType, IdologyOptions> mIdologyOptions = new HashMap();
    private final IdologyRetryConnector mIdologyRetryConnector;
    private final String mIdologyTrackingContext;
    private final IdologyVerificationConnector mIdologyVerificationConnector;
    String mJobTitle;
    private final Logger mLogger = LoggerFactory.getLogger(IdologyFormPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private Map<Integer, String> mMixPanelEventMap;
    private final MixpanelTracking mMixpanelTracking;
    private final IdologyFormScreen mScreen;
    private final SharedPreferences mSharedPrefs;
    private final SnackBarWrapper mSnackBarWrapper;
    String mSourceOfFunds;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public IdologyFormPresenter(Application application, LoginManager loginManager, IdologyFormScreen screen, SharedPreferences sharedPrefs, IdologyVerificationConnector idologyVerificationConnector, IdologyOptionSelectedConnector idologyOptionSelectedConnector, IdologyFormValidConnector idologyFormValidConnector, IdologyRetryConnector idologyRetryConnector, DatePickerConnector datePickerConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, @IdologyTrackingContext String idologyTrackingContext, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mContext = application;
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mSharedPrefs = sharedPrefs;
        this.mIdologyVerificationConnector = idologyVerificationConnector;
        this.mIdologyOptionSelectedConnector = idologyOptionSelectedConnector;
        this.mIdologyFormValidConnector = idologyFormValidConnector;
        this.mIdologyRetryConnector = idologyRetryConnector;
        this.mDatePickerConnector = datePickerConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMixpanelTracking = mixpanelTracking;
        this.mIdologyTrackingContext = idologyTrackingContext;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
        initMixPanelEventMap();
    }

    void init() {
        fetchIdologyOptions();
        getUserName();
    }

    void onShow() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        this.mSubscription.add(this.mIdologyOptionSelectedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(IdologyFormPresenter$$Lambda$1.lambdaFactory$(this), IdologyFormPresenter$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mDatePickerConnector.get().onBackpressureLatest().filter(IdologyFormPresenter$$Lambda$3.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologyFormPresenter$$Lambda$4.lambdaFactory$(this), IdologyFormPresenter$$Lambda$5.lambdaFactory$(this)));
        this.mSubscription.add(this.mDatePickerConnector.get().onBackpressureLatest().filter(IdologyFormPresenter$$Lambda$6.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologyFormPresenter$$Lambda$7.lambdaFactory$(this), IdologyFormPresenter$$Lambda$8.lambdaFactory$(this)));
        if (this.mLoginManager.getUserIsInEU()) {
            this.mScreen.hideSsn();
            this.mScreen.showPostalCode();
            this.mScreen.hideState();
        }
    }

    static /* synthetic */ Boolean lambda$onShow$2(Pair pair) {
        boolean z = pair != null && pair.first == DatePickerButton.OK;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ void lambda$onShow$3(IdologyFormPresenter this_, Pair pair) {
        if (pair.second != null) {
            this_.mScreen.setDobText(this_.getFormattedDate((Date) pair.second), false);
        }
        this_.mScreen.hideDatePickerLayout();
    }

    static /* synthetic */ Boolean lambda$onShow$5(Pair pair) {
        boolean z = pair != null && pair.first == DatePickerButton.CANCEL;
        return Boolean.valueOf(z);
    }

    void onHide() {
        this.mSubscription.clear();
    }

    void setData(Data idology) {
        this.mIdologyData = idology;
        prefillFormData();
    }

    void onDobClicked() {
        Calendar calendar = getInitialCalendar();
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        this.mScreen.showDatePickerLayout(calendar.get(1), calendar.get(2), calendar.get(5));
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

    void onSupportHeaderClicked() {
        Uri uri = Uri.parse(SUPPORT_URL);
        if (uri == null) {
            this.mSnackBarWrapper.showGenericError();
        } else {
            this.mScreen.displaySupportUrl(uri);
        }
    }

    boolean hideVisibleLayout() {
        if (!this.mScreen.isDatePickerLayoutVisible()) {
            return false;
        }
        this.mScreen.hideDatePickerLayout();
        return true;
    }

    void submitForm() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_SUBMIT, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        if (isValidAge()) {
            this.mSubscription.add(this.mLoginManager.getClient().createIdentificationVerificationRx(getIdologyParams()).first().observeOn(this.mMainScheduler).subscribe(IdologyFormPresenter$$Lambda$9.lambdaFactory$(this), IdologyFormPresenter$$Lambda$10.lambdaFactory$(this)));
            return;
        }
        showAgeWarning();
    }

    static /* synthetic */ void lambda$submitForm$8(IdologyFormPresenter this_, Pair pair) {
        Response<Idology> response = pair.first;
        if (response.isSuccessful()) {
            this_.mIdologyVerificationConnector.get().onNext(((Idology) response.body()).getData());
            return;
        }
        this_.mSnackBarWrapper.show(Utils.getErrorMessage(response, (Retrofit) pair.second));
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, "error", errorMessage, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this_.mIdologyTrackingContext);
        this_.mIdologyRetryConnector.get().onNext(Boolean.valueOf(this_.isFormValid()));
    }

    static /* synthetic */ void lambda$submitForm$9(IdologyFormPresenter this_, Throwable t) {
        this_.mSnackBarWrapper.show(Utils.getMessage(this_.mContext, t));
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, "error", errorMessage, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this_.mIdologyTrackingContext);
        this_.mIdologyRetryConnector.get().onNext(Boolean.valueOf(this_.isFormValid()));
    }

    void showAgeWarning() {
        String errorMessage = this.mContext.getString(R.string.idology_macd_warning, new Object[]{Integer.valueOf(18)});
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, "error", errorMessage, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        this.mSnackBarWrapper.show(errorMessage);
    }

    void onFormUpdated() {
        this.mIdologyFormValidConnector.get().onNext(Boolean.valueOf(isFormValid()));
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
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etFirstName), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_FIRST_NAME);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etLastName), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_LAST_NAME);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etDob), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_DOB);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etAddressLine1), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_ADDRESS_LINE1);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etAddressLine2), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_ADDRESS_LINE2);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etCity), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_CITY_TOWN);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etState), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_STATE);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etZip), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_ZIP);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etSSN), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_SSN);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etCoinbaseUses), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_USE_FOR_COINBASE);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etSourceOfFunds), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_SOURCE_OF_FUNDS);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etJobTitle), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_JOB_TITLE);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etEmployer), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_EMPLOYER);
    }

    private void prefillFormData() {
        if (this.mIdologyData != null && this.mIdologyOptions.size() != 0) {
            this.mScreen.prefillName(this.mIdologyData.getFirstName(), this.mIdologyData.getLastName());
            Address address = this.mIdologyData.getAddress();
            if (address != null) {
                this.mScreen.prefillAddress(address.getLine1(), address.getLine2(), address.getCity(), address.getState(), address.getPostalCode());
            }
            this.mScreen.prefillSsnLast4(this.mIdologyData.getSsnLast4());
            DateOfBirth dateOfBirth = this.mIdologyData.getDateOfBirth();
            if (!(dateOfBirth == null || dateOfBirth.getYear() == null || dateOfBirth.getMonth() == null || dateOfBirth.getDay() == null)) {
                Calendar calendar = getCalendar(dateOfBirth.getYear().intValue(), dateOfBirth.getMonth().intValue() - 1, dateOfBirth.getDay().intValue());
                if (calendar != null) {
                    this.mScreen.setDobText(getFormattedDate(calendar.getTime()), true);
                }
            }
            List<String> usesCoinbaseFor = this.mIdologyData.getUsesCoinbaseFor();
            IdologyOptions coinbaseUsesOption = (IdologyOptions) this.mIdologyOptions.get(OptionType.COINBASE_USES);
            if (usesCoinbaseFor != null && coinbaseUsesOption != null && coinbaseUsesOption.getData() != null) {
                String coinbaseUses = (String) usesCoinbaseFor.get(0);
                for (com.coinbase.api.internal.models.idology.options.Data option : coinbaseUsesOption.getData()) {
                    if (option.getValue().equals(coinbaseUses)) {
                        setSelectedOption(option, OptionType.COINBASE_USES, true);
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
                            setSelectedOption(option2, OptionType.JOB_TITLES, true);
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
                        setSelectedOption(option22, OptionType.SOURCE_OF_FUNDS, true);
                        return;
                    }
                }
            }
        }
    }

    private void getUserName() {
        String fullName = this.mSharedPrefs.getString(Constants.KEY_ACCOUNT_FULL_NAME, null);
        if (!TextUtils.isEmpty(fullName)) {
            String[] fullNameSplit = fullName.split(" ");
            if (fullNameSplit != null && fullNameSplit.length <= 2) {
                String lastName = "";
                String firstName = fullNameSplit[0];
                if (fullNameSplit.length > 1) {
                    lastName = fullNameSplit[1];
                }
                this.mScreen.prefillName(firstName, lastName);
            }
        }
    }

    private void fetchIdologyOptions() {
        this.mSubscription.add(Observable.combineLatest(this.mLoginManager.getClient().getCoinbaseUsesRx(), this.mLoginManager.getClient().getSourceOfFundsRx(), this.mLoginManager.getClient().getJobTitlesRx(), IdologyFormPresenter$$Lambda$11.lambdaFactory$()).map(IdologyFormPresenter$$Lambda$12.lambdaFactory$()).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(IdologyFormPresenter$$Lambda$13.lambdaFactory$(this), IdologyFormPresenter$$Lambda$14.lambdaFactory$(this)));
    }

    static /* synthetic */ Map lambda$fetchIdologyOptions$11(List response) {
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

    static /* synthetic */ void lambda$fetchIdologyOptions$12(IdologyFormPresenter this_, Map idologyOptionsMap) {
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
            setSelectedOption((com.coinbase.api.internal.models.idology.options.Data) pair.first, (OptionType) pair.second, false);
        }
    }

    HashMap<String, Object> getIdologyParams() {
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.FIRST_NAME, this.mScreen.getFirstNameText());
        params.put(ApiConstants.LAST_NAME, this.mScreen.getLastNameText());
        params.put(ApiConstants.ADDRESS1, this.mScreen.getAddressLine1Text());
        params.put(ApiConstants.ADDRESS2, this.mScreen.getAddressLine2Text());
        params.put(ApiConstants.CITY, this.mScreen.getCityText());
        if (!this.mLoginManager.getUserIsInEU()) {
            params.put("state", this.mScreen.getStateText().toUpperCase());
        }
        params.put(ApiConstants.ZIP, this.mScreen.getZipText());
        String dobText = this.mScreen.getDobText();
        if (dobText != null) {
            String[] dobSplit = dobText.split("/");
            if (dobSplit != null) {
                if (dobSplit.length > 0) {
                    params.put(ApiConstants.DOB_MONTH, Integer.valueOf(Integer.parseInt(dobSplit[0])));
                }
                if (dobSplit.length > 1) {
                    params.put(ApiConstants.DOB_DAY, Integer.valueOf(Integer.parseInt(dobSplit[1])));
                }
                if (dobSplit.length > 2) {
                    params.put(ApiConstants.DOB_YEAR, Integer.valueOf(Integer.parseInt(dobSplit[2])));
                }
            }
        }
        if (!this.mLoginManager.getUserIsInEU()) {
            params.put(ApiConstants.SSN_LAST4, this.mScreen.getSsnText());
        }
        params.put(ApiConstants.USES_COINBASE_FOR, Arrays.asList(new String[]{this.mCoinbaseUses}));
        params.put(ApiConstants.PRIMARY_SOURCE_OF_FUNDS, this.mSourceOfFunds);
        params.put(ApiConstants.CURRENT_JOB_TITLE, this.mJobTitle);
        params.put(ApiConstants.CURRENT_EMPLOYER, this.mScreen.getCurrentEmployerText());
        params.put(ApiConstants.ALLOW_QUESTIONS, "true");
        return params;
    }

    boolean isFormValid() {
        if (TextUtils.isEmpty(this.mScreen.getFirstNameText()) || TextUtils.isEmpty(this.mScreen.getLastNameText()) || TextUtils.isEmpty(this.mScreen.getDobText()) || TextUtils.isEmpty(this.mScreen.getAddressLine1Text()) || TextUtils.isEmpty(this.mScreen.getCityText()) || TextUtils.isEmpty(this.mScreen.getStateText()) || TextUtils.isEmpty(this.mScreen.getZipText()) || TextUtils.isEmpty(this.mScreen.getSsnText()) || TextUtils.isEmpty(this.mCoinbaseUses) || TextUtils.isEmpty(this.mSourceOfFunds) || TextUtils.isEmpty(this.mJobTitle) || TextUtils.isEmpty(this.mScreen.getCurrentEmployerText())) {
            return false;
        }
        return true;
    }

    Map<OptionType, IdologyOptions> getIdologyOptions() {
        return this.mIdologyOptions;
    }

    boolean isValidAge() {
        boolean isValid = false;
        String dobText = this.mScreen.getDobText();
        if (TextUtils.isEmpty(dobText)) {
            return 0;
        }
        try {
            Calendar dobCalendar = Calendar.getInstance();
            dobCalendar.setTime(getDobDateFormatter().parse(dobText));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(1, -18);
            isValid = dobCalendar.getTimeInMillis() <= calendar.getTimeInMillis();
        } catch (ParseException e) {
        }
        return isValid;
    }

    SimpleDateFormat getDobDateFormatter() {
        return new SimpleDateFormat(DOB_DATE_FORMAT, Locale.US);
    }

    private Calendar getInitialCalendar() {
        return getCalendar(1990, 0, 1);
    }

    Calendar getCalendar(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month);
        calendar.set(5, dayOfMonth);
        return calendar;
    }

    String getFormattedDate(Date date) {
        if (date == null) {
            return null;
        }
        return getDobDateFormatter().format(date);
    }

    private void setSelectedOption(com.coinbase.api.internal.models.idology.options.Data selectedOption, OptionType optionType, boolean preFill) {
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
                    this.mScreen.setJobTitleText(selectedOption.getTitle(), preFill);
                    return;
                default:
                    return;
            }
        }
    }
}
