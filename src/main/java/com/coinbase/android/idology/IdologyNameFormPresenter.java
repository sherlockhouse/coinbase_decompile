package com.coinbase.android.idology;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.idology.IdologyFormErrorMatcher.IdologyKnownFields;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;
import com.coinbase.android.ui.DatePickerConnector;
import com.coinbase.android.ui.DatePickerConnector.DatePickerButton;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.idology.Data;
import com.coinbase.api.internal.models.idology.Data.Builder;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.user.DateOfBirth;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class IdologyNameFormPresenter {
    private static final String DOB_DATE_FORMAT = "MM/dd/yyyy";
    private static final int LEGAL_AGE = 18;
    private final Context mContext;
    private final DatePickerConnector mDatePickerConnector;
    private Data mIdologyData;
    private final IdologyForm mIdologyForm;
    private final IdologyFormErrorMatcher mIdologyFormErrorMatcher;
    private final IdologyFormValidConnector mIdologyFormValidConnector;
    private final String mIdologyTrackingContext;
    private final Logger mLogger = LoggerFactory.getLogger(IdologyNameFormPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private Map<Integer, String> mMixPanelEventMap;
    private final MixpanelTracking mMixpanelTracking;
    private final IdologyNameFormScreen mScreen;
    private final SharedPreferences mSharedPrefs;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public IdologyNameFormPresenter(Application application, LoginManager loginManager, IdologyNameFormScreen screen, SharedPreferences sharedPrefs, IdologyFormValidConnector idologyFormValidConnector, IdologyForm idologyForm, IdologyFormErrorMatcher idologyFormErrorMatcher, DatePickerConnector datePickerConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, @IdologyTrackingContext String idologyTrackingContext, @MainScheduler Scheduler mainScheduler) {
        this.mContext = application;
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mSharedPrefs = sharedPrefs;
        this.mIdologyFormValidConnector = idologyFormValidConnector;
        this.mIdologyForm = idologyForm;
        this.mIdologyFormErrorMatcher = idologyFormErrorMatcher;
        this.mDatePickerConnector = datePickerConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMixpanelTracking = mixpanelTracking;
        this.mIdologyTrackingContext = idologyTrackingContext;
        this.mMainScheduler = mainScheduler;
        initMixPanelEventMap();
    }

    void init() {
        getUserName();
    }

    void onShow() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_NAME_VIEWED, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        this.mSubscription.add(this.mDatePickerConnector.get().onBackpressureLatest().filter(IdologyNameFormPresenter$$Lambda$1.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologyNameFormPresenter$$Lambda$2.lambdaFactory$(this), IdologyNameFormPresenter$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mDatePickerConnector.get().onBackpressureLatest().filter(IdologyNameFormPresenter$$Lambda$4.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologyNameFormPresenter$$Lambda$5.lambdaFactory$(this), IdologyNameFormPresenter$$Lambda$6.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyFormErrorMatcher.getFormErrors().filter(IdologyNameFormPresenter$$Lambda$7.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologyNameFormPresenter$$Lambda$8.lambdaFactory$(this), IdologyNameFormPresenter$$Lambda$9.lambdaFactory$(this)));
        if (this.mLoginManager.getUserIsInEU()) {
            this.mScreen.hideSsnText();
        }
        this.mIdologyFormValidConnector.get().onNext(Boolean.valueOf(isFormValid()));
    }

    static /* synthetic */ Boolean lambda$onShow$0(Pair pair) {
        boolean z = pair != null && pair.first == DatePickerButton.OK;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ void lambda$onShow$1(IdologyNameFormPresenter this_, Pair pair) {
        if (pair.second != null) {
            this_.mScreen.setDobText(this_.getFormattedDate((Date) pair.second));
        }
        this_.mScreen.hideDatePickerLayout();
    }

    static /* synthetic */ Boolean lambda$onShow$3(Pair pair) {
        boolean z = pair != null && pair.first == DatePickerButton.CANCEL;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ Boolean lambda$onShow$6(List errors) {
        boolean z = (errors == null || errors.isEmpty()) ? false : true;
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

    boolean hideVisibleLayout() {
        if (!this.mScreen.isDatePickerLayoutVisible()) {
            return false;
        }
        this.mScreen.hideDatePickerLayout();
        return true;
    }

    void onContinueClicked() {
        if (isFormValid()) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_SUBMIT_PERSONAL_DETAIL, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
            if (isValidAge()) {
                Builder builder = Data.builder().setFirstName(this.mScreen.getFirstNameText()).setLastName(this.mScreen.getLastNameText()).setDateOfBirth(getDateOfBirth());
                if (!this.mLoginManager.getUserIsInEU()) {
                    builder.setSsnLast4(this.mScreen.getSsnText());
                }
                this.mSubscription.add(this.mIdologyForm.submitForm(builder.build()));
                return;
            }
            showAgeWarning();
        }
    }

    void showAgeWarning() {
        String errorMessage = this.mContext.getString(R.string.idology_macd_warning, new Object[]{Integer.valueOf(18)});
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, "error", errorMessage, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext);
        this.mSnackBarWrapper.show(errorMessage);
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

    private void initMixPanelEventMap() {
        this.mMixPanelEventMap = new HashMap();
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etFirstName), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_FIRST_NAME);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etLastName), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_LAST_NAME);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etDob), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_DOB);
        this.mMixPanelEventMap.put(Integer.valueOf(R.id.etSSN), MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_SSN);
    }

    private void prefillFormData() {
        if (this.mIdologyData != null) {
            this.mScreen.prefillName(this.mIdologyData.getFirstName(), this.mIdologyData.getLastName());
            this.mScreen.prefillSsnLast4(this.mIdologyData.getSsnLast4());
            DateOfBirth dateOfBirth = this.mIdologyData.getDateOfBirth();
            if (dateOfBirth != null && dateOfBirth.getYear() != null && dateOfBirth.getMonth() != null && dateOfBirth.getDay() != null) {
                Calendar calendar = getCalendar(dateOfBirth.getYear().intValue(), dateOfBirth.getMonth().intValue() - 1, dateOfBirth.getDay().intValue());
                if (calendar != null) {
                    this.mScreen.setDobText(getFormattedDate(calendar.getTime()));
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

    boolean isFormValid() {
        return (TextUtils.isEmpty(this.mScreen.getFirstNameText()) || TextUtils.isEmpty(this.mScreen.getLastNameText()) || TextUtils.isEmpty(this.mScreen.getDobText()) || getDateOfBirth() == null || (TextUtils.isEmpty(this.mScreen.getSsnText()) && !this.mLoginManager.getUserIsInEU())) ? false : true;
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

    private DateOfBirth getDateOfBirth() {
        String dobText = this.mScreen.getDobText();
        int month = 0;
        int day = 0;
        int year = 0;
        if (!(dobText == null || TextUtils.isEmpty(dobText))) {
            String[] dobSplit = dobText.split("/");
            if (dobSplit == null || dobSplit.length != 3) {
                return null;
            }
            month = Integer.parseInt(dobSplit[0]);
            day = Integer.parseInt(dobSplit[1]);
            year = Integer.parseInt(dobSplit[2]);
        }
        DateOfBirth dateOfBirth = new DateOfBirth();
        dateOfBirth.setMonth(Integer.valueOf(month));
        dateOfBirth.setDay(Integer.valueOf(day));
        dateOfBirth.setYear(Integer.valueOf(year));
        return dateOfBirth;
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
                case FIRST_NAME:
                    this.mScreen.showFirstNameFieldError(message);
                    return;
                case LAST_NAME:
                    this.mScreen.showLastNameFieldError(message);
                    return;
                case DOB_DAY:
                case DOB_MONTH:
                case DOB_YEAR:
                    this.mScreen.showDobFieldError(message);
                    return;
                case SSN_LAST4:
                    this.mScreen.showSsnLast4FieldError(message);
                    return;
                default:
                    this.mLogger.error("Unhandled error message in name form presenter [" + message + "] shouldn't happen");
                    return;
            }
        }
    }
}
