package com.coinbase.android.event;

import android.app.Application;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.accounts.AccountCryptoAddressButtonConnector;
import com.coinbase.android.accounts.AccountCryptoAddressUpdatedConnector;
import com.coinbase.android.accounts.AccountItemClickedConnector;
import com.coinbase.android.accounts.AccountListConnector;
import com.coinbase.android.accounts.AccountUpdatedConnector;
import com.coinbase.android.billing.BillingAddressDeletedConnector;
import com.coinbase.android.buysell.Buy3dsVerificationConnector;
import com.coinbase.android.buysell.BuySellMadeConnector;
import com.coinbase.android.buysell.QuickBuyConnector;
import com.coinbase.android.dashboard.DashboardAlertsConnector;
import com.coinbase.android.dashboard.DashboardBalanceUpdatedConnector;
import com.coinbase.android.dashboard.DashboardPriceChartItemClickedConnector;
import com.coinbase.android.dashboard.DashboardRefreshConnector;
import com.coinbase.android.dashboard.DashboardTabPeriodSelectionConnector;
import com.coinbase.android.dashboard.DashboardVerificationConnector;
import com.coinbase.android.gdpr.OnboardingUpdatedConnector;
import com.coinbase.android.identityverification.IdentityVerificationBitmapConnector;
import com.coinbase.android.identityverification.PhotoTakenConnector;
import com.coinbase.android.identityverification.RetakeAndContinueConnector;
import com.coinbase.android.idology.IdologyAnswerListValidConnector;
import com.coinbase.android.idology.IdologyAnswerSelectedConnector;
import com.coinbase.android.idology.IdologyAutoCompleteShownConnector;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyRetryConnector;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.idology.ProgressConnector;
import com.coinbase.android.notifications.priceAlerts.PriceAlertsConnector;
import com.coinbase.android.paymentmethods.AddPaymentMethodConnector;
import com.coinbase.android.paymentmethods.BankAccountsUpdatedConnector;
import com.coinbase.android.paymentmethods.PaymentMethodsFetchedConnector;
import com.coinbase.android.paymentmethods.PaymentMethodsUpdatedConnector;
import com.coinbase.android.paymentmethods.PlaidOnExitConnector;
import com.coinbase.android.paymentmethods.VerifyPaymentMethodConnector;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.pricechart.PriceChartDataUpdatedConnector;
import com.coinbase.android.pricechart.PriceChartPeriodUpdatedConnector;
import com.coinbase.android.settings.AccountDeleteRequestConnector;
import com.coinbase.android.settings.AccountsUpdatedConnector;
import com.coinbase.android.settings.GoToSettingsConnector;
import com.coinbase.android.settings.LocalUserDataUpdatedConnector;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.android.signin.DeviceVerifyConnector;
import com.coinbase.android.signin.EmailVerifiedConnector;
import com.coinbase.android.signin.IdologyOptionSelectedConnector;
import com.coinbase.android.signin.LoginSignUpFinishedConnector;
import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.android.signin.StateListSelectorConnector;
import com.coinbase.android.transactions.TransactionDetailButtonConnector;
import com.coinbase.android.transfers.TransferMadeConnector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.CurrencySelectorConnector;
import com.coinbase.android.ui.CurrencyTabSelectorConnector;
import com.coinbase.android.ui.DatePickerConnector;
import com.coinbase.android.ui.MystiqueListButtonConnector;
import com.coinbase.android.ui.MystiqueListSelectorConnector;
import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.ui.SignOutConnector;
import com.coinbase.api.internal.models.currency.Data;
import java.util.List;

public class CoinbaseEventsModule {
    @ApplicationScope
    BuySellMadeConnector providesBuySellMadeConnector() {
        return new BuySellMadeConnector();
    }

    @ApplicationScope
    BankAccountsUpdatedConnector providesBankAccountsUpdatedConnector() {
        return new BankAccountsUpdatedConnector();
    }

    @ApplicationScope
    PaymentMethodsUpdatedConnector providesPaymentMethodsUpdatedConnector() {
        return new PaymentMethodsUpdatedConnector();
    }

    @ApplicationScope
    PhoneNumbersUpdatedConnector providesPhoneNumbersUpdatedConnector() {
        return new PhoneNumbersUpdatedConnector();
    }

    @ApplicationScope
    AccountDeleteRequestConnector providesAccountDeleteRequestConnector() {
        return new AccountDeleteRequestConnector();
    }

    @ApplicationScope
    AccountsUpdatedConnector providesAccountsUpdatedConnector() {
        return new AccountsUpdatedConnector();
    }

    @ApplicationScope
    LocalUserDataUpdatedConnector providesUserDataUpdatedConnector() {
        return new LocalUserDataUpdatedConnector();
    }

    @ApplicationScope
    UserUpdatedConnector providesUserUpdatedConnector() {
        return new UserUpdatedConnector();
    }

    @ApplicationScope
    EmailVerifiedConnector providesEmailVerifiedConnector() {
        return new EmailVerifiedConnector();
    }

    @ApplicationScope
    StateListSelectorConnector providesStateListSelectorConnector() {
        return new StateListSelectorConnector();
    }

    @ApplicationScope
    TransferMadeConnector providesTransferMadeConnector() {
        return new TransferMadeConnector();
    }

    @ApplicationScope
    CurrencySelectorConnector providesCurrencySelectorConnector() {
        return new CurrencySelectorConnector();
    }

    @ApplicationScope
    RefreshRequestedConnector providesRefreshRequestedConnector() {
        return new RefreshRequestedConnector();
    }

    @ApplicationScope
    BillingAddressDeletedConnector providesBillingAddressDeletedConnector() {
        return new BillingAddressDeletedConnector();
    }

    @ApplicationScope
    PriceAlertsConnector providesPriceAlertsConnector() {
        return new PriceAlertsConnector();
    }

    @ApplicationScope
    DeviceVerifyConnector providesDeviceVerifyConnector() {
        return new DeviceVerifyConnector();
    }

    @ApplicationScope
    IdologyOptionSelectedConnector providesIdologyOptionSelectedConnector() {
        return new IdologyOptionSelectedConnector();
    }

    @ApplicationScope
    IdologyVerificationConnector providesIdologyVerificationConnector() {
        return new IdologyVerificationConnector();
    }

    @ApplicationScope
    IdologyFormValidConnector providesIdologyFormValidConnector() {
        return new IdologyFormValidConnector();
    }

    @ApplicationScope
    IdologyRetryConnector providesIdologyRetryConnector() {
        return new IdologyRetryConnector();
    }

    @ApplicationScope
    IdologyAnswerSelectedConnector providesIdologyAnswerSelectedConnector() {
        return new IdologyAnswerSelectedConnector();
    }

    @ApplicationScope
    IdologyAnswerListValidConnector providesIdologyAnswerListValidConnector() {
        return new IdologyAnswerListValidConnector();
    }

    @ApplicationScope
    StateDisclosureFinishedConnector providesStateDisclosureFinishedConnector() {
        return new StateDisclosureFinishedConnector();
    }

    @ApplicationScope
    LoginSignUpFinishedConnector providesTwoFactorConnector() {
        return new LoginSignUpFinishedConnector();
    }

    @ApplicationScope
    MystiqueListButtonConnector providesMystiqueListButtonConnector() {
        return new MystiqueListButtonConnector();
    }

    @ApplicationScope
    MystiqueListSelectorConnector providesMystiqueListSelectorConnector() {
        return new MystiqueListSelectorConnector();
    }

    @ApplicationScope
    RetakeAndContinueConnector providesRetakeAndContinueConnector() {
        return new RetakeAndContinueConnector();
    }

    @ApplicationScope
    PhotoTakenConnector providesPhotoTakenConnector() {
        return new PhotoTakenConnector();
    }

    @ApplicationScope
    TransactionDetailButtonConnector providesTransactionDetailButtonConnector() {
        return new TransactionDetailButtonConnector();
    }

    @ApplicationScope
    BottomNavigationConnector providesBottomNavigationConnector() {
        return new BottomNavigationConnector();
    }

    @ApplicationScope
    BackNavigationConnector providesBackNavigationConnector() {
        return new BackNavigationConnector();
    }

    @ApplicationScope
    AccountCryptoAddressButtonConnector providesAccountAddressButtonConnector() {
        return new AccountCryptoAddressButtonConnector();
    }

    @ApplicationScope
    AccountCryptoAddressUpdatedConnector providesAccountCryptoAddressUpdatedConnector() {
        return new AccountCryptoAddressUpdatedConnector();
    }

    @ApplicationScope
    SignOutConnector providesSignOutConnector() {
        return new SignOutConnector();
    }

    @ApplicationScope
    DashboardPriceChartItemClickedConnector providesDashboardPriceChartItemClickedConnector() {
        return new DashboardPriceChartItemClickedConnector();
    }

    @ApplicationScope
    DashboardTabPeriodSelectionConnector providesDashboardTabPeriodSelectionConnector() {
        return new DashboardTabPeriodSelectionConnector();
    }

    @ApplicationScope
    AccountItemClickedConnector providesAccountItemClickedConnector() {
        return new AccountItemClickedConnector();
    }

    @ApplicationScope
    PriceChartDataUpdatedConnector providesPriceChartDataUpdatedConnector() {
        return new PriceChartDataUpdatedConnector();
    }

    @ApplicationScope
    PriceChartPeriodUpdatedConnector providesPriceChartPeriodUpdatedConnector() {
        return new PriceChartPeriodUpdatedConnector();
    }

    @ApplicationScope
    CurrencyTabSelectorConnector providesCurrencyTabSelectorConnector(CurrenciesUpdatedConnector currenciesUpdatedConnector) {
        List<Data> currencies = (List) currenciesUpdatedConnector.get().getValue();
        Data initialValue = (currencies == null || currencies.isEmpty()) ? null : (Data) currencies.get(0);
        return new CurrencyTabSelectorConnector(initialValue);
    }

    @ApplicationScope
    AccountUpdatedConnector providesAccountUpdatedConnector() {
        return new AccountUpdatedConnector();
    }

    @ApplicationScope
    DashboardBalanceUpdatedConnector providesDashboardBalanceUpdatedConnector() {
        return new DashboardBalanceUpdatedConnector();
    }

    @ApplicationScope
    DashboardVerificationConnector providesDashboardDataConnector() {
        return new DashboardVerificationConnector();
    }

    @ApplicationScope
    DashboardAlertsConnector providesDashboardAlertsConnector() {
        return new DashboardAlertsConnector();
    }

    @ApplicationScope
    CurrenciesUpdatedConnector providesCurrenciesUpdatedConnector(Application application) {
        return new CurrenciesUpdatedConnector(application);
    }

    @ApplicationScope
    DashboardRefreshConnector providesDashboardRefreshConnector() {
        return new DashboardRefreshConnector();
    }

    @ApplicationScope
    IdentityVerificationBitmapConnector providesIdentityVerificationBitmapConnector() {
        return new IdentityVerificationBitmapConnector();
    }

    @ApplicationScope
    AccountListConnector providesAccountListConnector() {
        return new AccountListConnector();
    }

    @ApplicationScope
    DatePickerConnector providesDatePickerButtonConnector() {
        return new DatePickerConnector();
    }

    @ApplicationScope
    ProgressConnector providesProgressConnector() {
        return new ProgressConnector();
    }

    @ApplicationScope
    GoToSettingsConnector providesBuySellSettingsConnector() {
        return new GoToSettingsConnector();
    }

    @ApplicationScope
    AddPaymentMethodConnector providesAddPaymentMethodConnector() {
        return new AddPaymentMethodConnector();
    }

    @ApplicationScope
    IdologyAutoCompleteShownConnector providesIdologyAutoCompleteShownConnector() {
        return new IdologyAutoCompleteShownConnector();
    }

    @ApplicationScope
    VerifyPaymentMethodConnector providesVerifyPaymentMethodConnector() {
        return new VerifyPaymentMethodConnector();
    }

    @ApplicationScope
    PaymentMethodsFetchedConnector providesPaymentMethodsRefreshedConnector() {
        return new PaymentMethodsFetchedConnector();
    }

    @ApplicationScope
    NumericKeypadConnector providesNumericKeypadConnector() {
        return new NumericKeypadConnector();
    }

    @ApplicationScope
    PlaidOnExitConnector providesPlaidOnExitConnector() {
        return new PlaidOnExitConnector();
    }

    @ApplicationScope
    LinkedAccountConnector providesLinkedAccountConnector() {
        return new LinkedAccountConnector();
    }

    @ApplicationScope
    Buy3dsVerificationConnector providesBuy3dsVerificationConnector() {
        return new Buy3dsVerificationConnector();
    }

    @ApplicationScope
    QuickBuyConnector providesQuickBuyConnector() {
        return new QuickBuyConnector();
    }

    @ApplicationScope
    OnboardingUpdatedConnector providesOnboardingUpdatedConnector() {
        return new OnboardingUpdatedConnector();
    }

    @ApplicationScope
    SettingsPreferenceItemClickedConnector providesPrivacyRightsSettingsClickedConnector() {
        return new SettingsPreferenceItemClickedConnector();
    }
}
