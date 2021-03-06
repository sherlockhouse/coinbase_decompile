package com.coinbase.android;

import com.coinbase.android.accounts.AccountCryptoAddressLayoutSubcomponent;
import com.coinbase.android.accounts.AccountCryptoAddressPresenterModule;
import com.coinbase.android.accounts.AccountFilteredListLayoutSubcomponent;
import com.coinbase.android.accounts.AccountFilteredListPresenterModule;
import com.coinbase.android.accounts.AccountMainControllerSubcomponent;
import com.coinbase.android.accounts.AccountMainPresenterModule;
import com.coinbase.android.accounts.AccountRefreshListLayoutSubcomponent;
import com.coinbase.android.accounts.AccountRefreshListPresenterModule;
import com.coinbase.android.accounts.AccountTransactionsControllerSubcomponent;
import com.coinbase.android.accounts.AccountTransactionsPresenterModule;
import com.coinbase.android.alerts.AlertsListLayoutSubcomponent;
import com.coinbase.android.alerts.AlertsListPresenterModule;
import com.coinbase.android.buysell.BuyConfirmationControllerSubcomponent;
import com.coinbase.android.buysell.BuyConfirmationPresenterModule;
import com.coinbase.android.buysell.BuyControllerSubcomponent;
import com.coinbase.android.buysell.BuyPresenterModule;
import com.coinbase.android.buysell.BuySellSuccessControllerSubcomponent;
import com.coinbase.android.buysell.BuySellSuccessPresenterModule;
import com.coinbase.android.buysell.QuickBuyListLayout;
import com.coinbase.android.buysell.SellConfirmationControllerSubcomponent;
import com.coinbase.android.buysell.SellConfirmationPresenterModule;
import com.coinbase.android.buysell.SellControllerSubcomponent;
import com.coinbase.android.buysell.SellPresenterModule;
import com.coinbase.android.dashboard.DashboardCurrencyControllerSubcomponent;
import com.coinbase.android.dashboard.DashboardCurrencyPresenterModule;
import com.coinbase.android.dashboard.DashboardMainControllerSubcomponent;
import com.coinbase.android.dashboard.DashboardMainPresenterModule;
import com.coinbase.android.dashboard.DashboardPriceChartListLayoutSubcomponent;
import com.coinbase.android.dashboard.DashboardPriceChartListPresenterModule;
import com.coinbase.android.dashboard.DashboardTabPeriodLayoutSubcomponent;
import com.coinbase.android.dashboard.DashboardTabPeriodPresenterModule;
import com.coinbase.android.deposits.FiatTransactionsControllerModule;
import com.coinbase.android.deposits.FiatTransactionsControllerSubcomponent;
import com.coinbase.android.deposits.fiat.DepositFiatConfirmationControllerSubcomponent;
import com.coinbase.android.deposits.fiat.DepositFiatConfirmationPresenterModule;
import com.coinbase.android.deposits.fiat.DepositFiatControllerSubcomponent;
import com.coinbase.android.deposits.fiat.DepositFiatPresenterModule;
import com.coinbase.android.deposits.fiat.WithdrawDepositSuccessControllerSubcomponent;
import com.coinbase.android.deposits.fiat.WithdrawDepositSuccessPresenterModule;
import com.coinbase.android.deposits.fiat.WithdrawFiatConfirmationControllerSubcomponent;
import com.coinbase.android.deposits.fiat.WithdrawFiatConfirmationPresenterModule;
import com.coinbase.android.deposits.fiat.WithdrawFiatControllerSubcomponent;
import com.coinbase.android.deposits.fiat.WithdrawFiatPresenterModule;
import com.coinbase.android.dialog.AlertDialogController;
import com.coinbase.android.dialog.ConfirmationDialogController;
import com.coinbase.android.gdpr.GdprIntroControllerSubcomponent;
import com.coinbase.android.gdpr.GdprIntroPresenterModule;
import com.coinbase.android.gdpr.GdprMarketingEmailControllerSubcomponent;
import com.coinbase.android.gdpr.GdprMarketingEmailPresenterModule;
import com.coinbase.android.gdpr.GdprPrivacyPolicyControllerSubcomponent;
import com.coinbase.android.gdpr.GdprPrivacyPolicyPresenterModule;
import com.coinbase.android.identityverification.IdentityVerificationActivity;
import com.coinbase.android.identityverification.IdentityVerificationController;
import com.coinbase.android.identityverification.InAppIdentityAcceptableDocumentsControllerSubcomponent;
import com.coinbase.android.identityverification.InAppIdentityAcceptableDocumentsPresenterModule;
import com.coinbase.android.identityverification.InAppIdentityDocumentScanController.RetakePhotoDialogController;
import com.coinbase.android.identityverification.InAppIdentityDocumentScanPresenterModule;
import com.coinbase.android.identityverification.JumioDocumentScanControllerSubcomponent;
import com.coinbase.android.identityverification.TakeDocumentPhotoControllerSubcomponent;
import com.coinbase.android.identityverification.TakeDocumentPhotoPresenterModule;
import com.coinbase.android.idology.IdologyAddressFormLayoutSubcomponent;
import com.coinbase.android.idology.IdologyAddressFormPresenterModule;
import com.coinbase.android.idology.IdologyFormLayoutSubcomponent;
import com.coinbase.android.idology.IdologyFormPresenterModule;
import com.coinbase.android.idology.IdologyNameFormLayoutSubcomponent;
import com.coinbase.android.idology.IdologyNameFormPresenterModule;
import com.coinbase.android.idology.IdologyQuestionsLayoutSubcomponent;
import com.coinbase.android.idology.IdologyQuestionsPresenterModule;
import com.coinbase.android.idology.IdologySourceOfFundsFormLayoutSubcomponent;
import com.coinbase.android.idology.IdologySourceOfFundsFormPresenterModule;
import com.coinbase.android.notifications.priceAlerts.PriceAlertsControllerSubcomponent;
import com.coinbase.android.notifications.priceAlerts.PriceAlertsPresenterModule;
import com.coinbase.android.paymentmethods.AddBankErrorControllerSubcomponent;
import com.coinbase.android.paymentmethods.AddBankErrorPresenterModule;
import com.coinbase.android.paymentmethods.AddBankPickerControllerSubcomponent;
import com.coinbase.android.paymentmethods.AddBankPickerPresenterModule;
import com.coinbase.android.paymentmethods.AddPlaidAccountControllerSubcomponent;
import com.coinbase.android.paymentmethods.AddPlaidAccountPresenterModule;
import com.coinbase.android.paymentmethods.ConnectedAccountsLayoutSubcomponent;
import com.coinbase.android.paymentmethods.ConnectedAccountsPresenterModule;
import com.coinbase.android.paymentmethods.PaymentMethodsControllerSubcomponent;
import com.coinbase.android.paymentmethods.PaymentMethodsPresenterModule;
import com.coinbase.android.paymentmethods.PlaidControllerSubcomponent;
import com.coinbase.android.paymentmethods.PlaidPresenterModule;
import com.coinbase.android.paymentmethods.card.CardFormControllerSubcomponent;
import com.coinbase.android.paymentmethods.card.CardFormPresenterModule;
import com.coinbase.android.paymentmethods.card.PaymentMethodVerificationController;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountItemLayout;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountsPickerControllerSubcomponent;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountsPickerPresenterModule;
import com.coinbase.android.phone.AddPhoneDialogController;
import com.coinbase.android.phone.PhoneNumberControllerSubcomponent;
import com.coinbase.android.phone.PhoneNumberPresenterModule;
import com.coinbase.android.pricechart.PriceChartLayoutSubcomponent;
import com.coinbase.android.pricechart.PriceChartPresenterModule;
import com.coinbase.android.quickstart.QuickstartModule;
import com.coinbase.android.settings.AccountSettingsControllerSubcomponent;
import com.coinbase.android.settings.AccountSettingsPresenterModule;
import com.coinbase.android.settings.GoToSettingsDialogController;
import com.coinbase.android.settings.gdpr.EmailSettingsControllerSubcomponent;
import com.coinbase.android.settings.gdpr.EmailSettingsPresenterModule;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestControllerSubcomponent;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestPresenterModule;
import com.coinbase.android.settings.gdpr.GdprRequestSentControllerSubcomponent;
import com.coinbase.android.settings.gdpr.GdprRequestSentPresenterModule;
import com.coinbase.android.settings.gdpr.PrivacyRightsControllerSubcomponent;
import com.coinbase.android.settings.gdpr.PrivacyRightsPresenterModule;
import com.coinbase.android.settings.idology.IdologyFailureControllerSubcomponent;
import com.coinbase.android.settings.idology.IdologyFailurePresenterModule;
import com.coinbase.android.settings.idology.IdologySettingsControllerSubcomponent;
import com.coinbase.android.settings.idology.IdologySettingsPresenterModule;
import com.coinbase.android.settings.idology.IdologySettingsQuestionsControllerSubcomponent;
import com.coinbase.android.settings.idology.IdologySettingsQuestionsPresenterModule;
import com.coinbase.android.settings.idology.IdologySuccessControllerSubcomponent;
import com.coinbase.android.settings.idology.IdologySuccessPresenterModule;
import com.coinbase.android.settings.tiers.InvestmentTiersRequirementsControllerSubcomponent;
import com.coinbase.android.settings.tiers.InvestmentTiersRequirementsPresenterModule;
import com.coinbase.android.settings.tiers.InvestmentTiersSettingsControllerSubcomponent;
import com.coinbase.android.settings.tiers.InvestmentTiersSettingsPresenterModule;
import com.coinbase.android.signin.AcceptTermsControllerSubcomponent;
import com.coinbase.android.signin.AcceptTermsPresenterModule;
import com.coinbase.android.signin.DeviceVerifyControllerSubcomponent;
import com.coinbase.android.signin.DeviceVerifyPresenterModule;
import com.coinbase.android.signin.EmailVerifyControllerSubcomponent;
import com.coinbase.android.signin.EmailVerifyPresenterModule;
import com.coinbase.android.signin.IntroActivity;
import com.coinbase.android.signin.LoginControllerSubcomponentModule;
import com.coinbase.android.signin.RegistrationControllerActivity;
import com.coinbase.android.signin.SignInPhoneNumberControllerSubcomponent;
import com.coinbase.android.signin.SignInPhoneNumberPresenterModule;
import com.coinbase.android.signin.SignInVerifyPhoneNumberControllerSubcomponent;
import com.coinbase.android.signin.SignInVerifyPhoneNumberPresenterModule;
import com.coinbase.android.signin.SignUpControllerSubcomponentModule;
import com.coinbase.android.signin.TwoFactorControllerSubcomponent;
import com.coinbase.android.signin.TwoFactorPresenterModule;
import com.coinbase.android.signin.state.StateIdologyAddressFormControllerSubcomponent;
import com.coinbase.android.signin.state.StateIdologyAddressFormPresenterModule;
import com.coinbase.android.signin.state.StateIdologyFailureControllerSubcomponent;
import com.coinbase.android.signin.state.StateIdologyFailurePresenterModule;
import com.coinbase.android.signin.state.StateIdologyNameFormControllerSubcomponent;
import com.coinbase.android.signin.state.StateIdologyNameFormPresenterModule;
import com.coinbase.android.signin.state.StateIdologyQuestionsControllerSubcomponent;
import com.coinbase.android.signin.state.StateIdologyRetryFormControllerSubcomponent;
import com.coinbase.android.signin.state.StateIdologyRetryFormPresenterModule;
import com.coinbase.android.signin.state.StateIdologySettingsQuestionsPresenterModule;
import com.coinbase.android.signin.state.StateIdologySourceOfFundsFormControllerSubcomponent;
import com.coinbase.android.signin.state.StateIdologySourceOfFundsFormPresenterModule;
import com.coinbase.android.signin.state.StateLockedControllerSubcomponent;
import com.coinbase.android.signin.state.StateLockedPresenterModule;
import com.coinbase.android.signin.state.StateSelectorControllerSubcomponent;
import com.coinbase.android.signin.state.StateSelectorPresenterModule;
import com.coinbase.android.signin.state.StateSuspendedControllerSubcomponent;
import com.coinbase.android.signin.state.StateSuspendedPresenterModule;
import com.coinbase.android.signin.state.UpfrontKycIdentityAcceptableDocumentsControllerSubcomponent;
import com.coinbase.android.signin.state.UpfrontKycIdentityAcceptableDocumentsPresenterModule;
import com.coinbase.android.signin.state.UpfrontKycIdentityDocumentScanControllerSubcomponent;
import com.coinbase.android.signin.state.UpfrontKycIdentityDocumentScanPresenterModule;
import com.coinbase.android.signin.state.UpfrontKycIdentityFailedControllerSubcomponent;
import com.coinbase.android.signin.state.UpfrontKycIdentityFailedPresenterModule;
import com.coinbase.android.signin.state.UpfrontKycIdentityProcessingControllerSubcomponent;
import com.coinbase.android.signin.state.UpfrontKycIdentityProcessingPresenterModule;
import com.coinbase.android.signin.state.UpfrontKycTakeDocumentPhotoControllerSubcomponent;
import com.coinbase.android.signin.state.UpfrontKycTakeDocumentPhotoPresenterModule;
import com.coinbase.android.transactions.TransactionDetailLayout;
import com.coinbase.android.transactions.TransactionListLayoutSubcomponent;
import com.coinbase.android.transactions.TransactionListPresenterModule;
import com.coinbase.android.transfers.ConfirmSendTransferControllerSubcomponent;
import com.coinbase.android.transfers.ConfirmSendTransferPresenterModule;
import com.coinbase.android.transfers.DelayedTransactionDialogControllerSubcomponent;
import com.coinbase.android.transfers.DelayedTransactionsControllerModule;
import com.coinbase.android.transfers.SendControllerSubcomponent;
import com.coinbase.android.transfers.SendPresenterModule;
import com.coinbase.android.transfers.TransferSendControllerModule;
import com.coinbase.android.transfers.TransferSendControllerSubcomponent;
import com.coinbase.android.transfers.TransferSendPresenterModule;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.CenteredAppBarLayout;
import com.coinbase.android.ui.CurrencyTabLayoutSubcomponent;
import com.coinbase.android.ui.CurrencyTabPresenterModule;
import com.coinbase.android.ui.DatePickerSpinnerLayout;
import com.coinbase.android.ui.DialogController;
import com.coinbase.android.ui.MainControllerPresenterModule;
import com.coinbase.android.ui.MainControllerSubcomponent;
import com.coinbase.android.ui.ModalControllerLifeCycle;
import com.coinbase.android.ui.MystiqueBottomNavigationLayoutSubcomponent;
import com.coinbase.android.ui.MystiqueBottomNavigationPresenterModule;
import com.coinbase.android.ui.NumericKeyboardLayout;
import com.coinbase.android.ui.PageControllerLifeCycle;
import com.coinbase.android.wbl.AvailableBalanceControllerSubcomponent;
import com.coinbase.android.wbl.AvailableBalancePresenterModule;
import com.coinbase.android.wbl.ExistingUserDialogControllerSubcomponent;
import com.coinbase.android.wbl.ExistingUserDialogPresenterModule;
import com.coinbase.android.wbl.WithdrawalBasedLimitsDialogControllerSubcomponent;
import com.coinbase.android.wbl.WithdrawalBasedLimitsDialogPresenterModule;

@ActivityScope
public interface MainActivitySubcomponent {
    AcceptTermsControllerSubcomponent addAcceptTermsControllerSubcomponent(AcceptTermsPresenterModule acceptTermsPresenterModule);

    AccountCryptoAddressLayoutSubcomponent addAccountAddressLayoutSubcomponent(AccountCryptoAddressPresenterModule accountCryptoAddressPresenterModule);

    AccountTransactionsControllerSubcomponent addAccountDetailsControllerSubcomponent(AccountTransactionsPresenterModule accountTransactionsPresenterModule);

    AccountMainControllerSubcomponent addAccountListControllerSubcomponent(AccountMainPresenterModule accountMainPresenterModule);

    AccountFilteredListLayoutSubcomponent addAccountListLayoutSubcomponent(AccountFilteredListPresenterModule accountFilteredListPresenterModule);

    AccountRefreshListLayoutSubcomponent addAccountRefreshListLayoutSubcomponent(AccountRefreshListPresenterModule accountRefreshListPresenterModule);

    AccountSettingsControllerSubcomponent addAccountSettingsControllerSubcomponent(AccountSettingsPresenterModule accountSettingsPresenterModule);

    AlertsListLayoutSubcomponent addAlertsListLayoutSubcomponent(AlertsListPresenterModule alertsListPresenterModule);

    AvailableBalanceControllerSubcomponent addAvailableBalanceControllerSubcomponent(AvailableBalancePresenterModule availableBalancePresenterModule);

    AddBankErrorControllerSubcomponent addBankErrorControllerSubcomponent(AddBankErrorPresenterModule addBankErrorPresenterModule);

    AddBankPickerControllerSubcomponent addBankPickerControllerSubcomponent(AddBankPickerPresenterModule addBankPickerPresenterModule);

    BuyConfirmationControllerSubcomponent addBuyConfirmationControllerSubcomponent(BuyConfirmationPresenterModule buyConfirmationPresenterModule);

    BuyControllerSubcomponent addBuyControllerSubcomponent(QuickstartModule quickstartModule, BuyPresenterModule buyPresenterModule);

    BuySellSuccessControllerSubcomponent addBuySellSuccessControllerSubcomponent(BuySellSuccessPresenterModule buySellSuccessPresenterModule);

    ConfirmSendTransferControllerSubcomponent addConfirmSendTransferControllerSubcomponent(ConfirmSendTransferPresenterModule confirmSendTransferPresenterModule);

    ConnectedAccountsLayoutSubcomponent addConnectedAccountsLayoutSubcomponent(ConnectedAccountsPresenterModule connectedAccountsPresenterModule);

    CurrencyTabLayoutSubcomponent addCurrencyTabLayoutSubcomponent(CurrencyTabPresenterModule currencyTabPresenterModule);

    DashboardCurrencyControllerSubcomponent addDashboardCurrencySubcomponent(DashboardCurrencyPresenterModule dashboardCurrencyPresenterModule);

    DashboardMainControllerSubcomponent addDashboardMainControllerSubcomponent(QuickstartModule quickstartModule, DashboardMainPresenterModule dashboardMainPresenterModule);

    DashboardPriceChartListLayoutSubcomponent addDashboardPriceChartListLayoutSubcomponent(DashboardPriceChartListPresenterModule dashboardPriceChartListPresenterModule);

    DashboardTabPeriodLayoutSubcomponent addDashboardTabPeriodLayoutSubcomponent(DashboardTabPeriodPresenterModule dashboardTabPeriodPresenterModule);

    DelayedTransactionDialogControllerSubcomponent addDelayedTransactionDialogControllerSubcomponent(DelayedTransactionsControllerModule delayedTransactionsControllerModule);

    DeviceVerifyControllerSubcomponent addDeviceVerifyControllerSubcomponent(DeviceVerifyPresenterModule deviceVerifyPresenterModule);

    EmailSettingsControllerSubcomponent addEmailSettingsControllerSubcomponent(EmailSettingsPresenterModule emailSettingsPresenterModule);

    EmailVerifyControllerSubcomponent addEmailVerifyControllerSubcomponent(EmailVerifyPresenterModule emailVerifyPresenterModule);

    ExistingUserDialogControllerSubcomponent addExistingUserDialogControllerSubcomponent(ExistingUserDialogPresenterModule existingUserDialogPresenterModule);

    GdprIntroControllerSubcomponent addGdprIntroControllerSubcomponent(GdprIntroPresenterModule gdprIntroPresenterModule);

    GdprMarketingEmailControllerSubcomponent addGdprMarketingEmailControllerSubcomponent(GdprMarketingEmailPresenterModule gdprMarketingEmailPresenterModule);

    GdprPrivacyPolicyControllerSubcomponent addGdprPrivacyPolicyControllerSubcomponent(GdprPrivacyPolicyPresenterModule gdprPrivacyPolicyPresenterModule);

    GdprRequestSentControllerSubcomponent addGdprRequestSentControllerSubcomponent(GdprRequestSentPresenterModule gdprRequestSentPresenterModule);

    IdologyAddressFormLayoutSubcomponent addIdologyAddressFormLayoutSubcomponent(IdologyAddressFormPresenterModule idologyAddressFormPresenterModule);

    IdologyFailureControllerSubcomponent addIdologyFailureControllerSubcomponent(IdologyFailurePresenterModule idologyFailurePresenterModule);

    IdologyFormLayoutSubcomponent addIdologyFormLayoutSubcomponent(IdologyFormPresenterModule idologyFormPresenterModule);

    IdologyNameFormLayoutSubcomponent addIdologyNameFormLayoutSubcomponent(IdologyNameFormPresenterModule idologyNameFormPresenterModule);

    IdologySettingsQuestionsControllerSubcomponent addIdologyQuestionsControllerSubcomponent(IdologySettingsQuestionsPresenterModule idologySettingsQuestionsPresenterModule);

    IdologyQuestionsLayoutSubcomponent addIdologyQuestionsLayoutSubcomponent(IdologyQuestionsPresenterModule idologyQuestionsPresenterModule);

    IdologySettingsControllerSubcomponent addIdologySettingsControllerSubcomponent(IdologySettingsPresenterModule idologySettingsPresenterModule);

    IdologySourceOfFundsFormLayoutSubcomponent addIdologySourceOfFundsFormLayoutSubcomponent(IdologySourceOfFundsFormPresenterModule idologySourceOfFundsFormPresenterModule);

    IdologySuccessControllerSubcomponent addIdologySuccessControllerSubcomponent(IdologySuccessPresenterModule idologySuccessPresenterModule);

    InAppIdentityAcceptableDocumentsControllerSubcomponent addInAppAcceptableDocumentsControllerSubcomponent(InAppIdentityAcceptableDocumentsPresenterModule inAppIdentityAcceptableDocumentsPresenterModule);

    JumioDocumentScanControllerSubcomponent addInAppIdentityDocumentScanControllerSubcomponent(InAppIdentityDocumentScanPresenterModule inAppIdentityDocumentScanPresenterModule);

    InvestmentTiersRequirementsControllerSubcomponent addInvestmentTiersRequirementsControllerSubcomponent(InvestmentTiersRequirementsPresenterModule investmentTiersRequirementsPresenterModule);

    InvestmentTiersSettingsControllerSubcomponent addInvestmentTiersSettingsControllerSubcomponent(InvestmentTiersSettingsPresenterModule investmentTiersSettingsPresenterModule);

    LinkedAccountsPickerControllerSubcomponent addLinkedAccountsPickerControllerSubcomponent(LinkedAccountsPickerPresenterModule linkedAccountsPickerPresenterModule);

    LoginControllerSubcomponent addLoginControllerSubcomponent(LoginControllerSubcomponentModule loginControllerSubcomponentModule);

    PhoneNumberControllerSubcomponent addPhoneNumberControllerSubcomponent(PhoneNumberPresenterModule phoneNumberPresenterModule);

    AddPlaidAccountControllerSubcomponent addPlaidAccountControllerSubcomponent(AddPlaidAccountPresenterModule addPlaidAccountPresenterModule);

    PlaidControllerSubcomponent addPlaidControllerSubcomponent(PlaidPresenterModule plaidPresenterModule);

    PriceChartLayoutSubcomponent addPriceChartLayoutSubcomponent(PriceChartPresenterModule priceChartPresenterModule);

    PrivacyRightsControllerSubcomponent addPrivacyRightsControllerSubcomponent(PrivacyRightsPresenterModule privacyRightsPresenterModule);

    GdprPrivacyRequestControllerSubcomponent addRequestDataControllerSubcomponent(GdprPrivacyRequestPresenterModule gdprPrivacyRequestPresenterModule);

    SellConfirmationControllerSubcomponent addSellConfirmationControllerSubcomponent(SellConfirmationPresenterModule sellConfirmationPresenterModule);

    SellControllerSubcomponent addSellControllerSubcomponent(QuickstartModule quickstartModule, SellPresenterModule sellPresenterModule);

    SendControllerSubcomponent addSendControllerSubcomponent(SendPresenterModule sendPresenterModule);

    SignInPhoneNumberControllerSubcomponent addSignInPhoneNumberControllerSubcomponent(SignInPhoneNumberPresenterModule signInPhoneNumberPresenterModule);

    SignInVerifyPhoneNumberControllerSubcomponent addSignInVerifyPhoneNumberControllerSubcomponent(SignInVerifyPhoneNumberPresenterModule signInVerifyPhoneNumberPresenterModule);

    SignUpControllerSubcomponent addSignUpControllerSubcomponent(SignUpControllerSubcomponentModule signUpControllerSubcomponentModule);

    StateIdologyAddressFormControllerSubcomponent addStateIdologyAddressFormControllerSubcomponent(StateIdologyAddressFormPresenterModule stateIdologyAddressFormPresenterModule);

    StateIdologyFailureControllerSubcomponent addStateIdologyFailureControllerSubcomponent(StateIdologyFailurePresenterModule stateIdologyFailurePresenterModule);

    StateIdologyNameFormControllerSubcomponent addStateIdologyNameFormControllerSubcomponent(StateIdologyNameFormPresenterModule stateIdologyNameFormPresenterModule);

    StateIdologyQuestionsControllerSubcomponent addStateIdologyQuestionsControllerSubcomponent(StateIdologySettingsQuestionsPresenterModule stateIdologySettingsQuestionsPresenterModule);

    StateIdologyRetryFormControllerSubcomponent addStateIdologyRetryFormControllerSubcomponent(StateIdologyRetryFormPresenterModule stateIdologyRetryFormPresenterModule);

    StateIdologySourceOfFundsFormControllerSubcomponent addStateIdologySourceOfFundsFormControllerSubcomponent(StateIdologySourceOfFundsFormPresenterModule stateIdologySourceOfFundsFormPresenterModule);

    StateLockedControllerSubcomponent addStateLockedControllerSubcomponent(StateLockedPresenterModule stateLockedPresenterModule);

    StateSelectorControllerSubcomponent addStateSelectorControllerSubcomponent(StateSelectorPresenterModule stateSelectorPresenterModule);

    StateSuspendedControllerSubcomponent addStateSuspendedControllerSubcomponent(StateSuspendedPresenterModule stateSuspendedPresenterModule);

    TakeDocumentPhotoControllerSubcomponent addTakeDocumentPhotoControllerSubcomponent(TakeDocumentPhotoPresenterModule takeDocumentPhotoPresenterModule);

    TransactionListLayoutSubcomponent addTransactionListLayoutSubcomponent(TransactionListPresenterModule transactionListPresenterModule);

    TransferSendControllerSubcomponent addTransferSendControllerSubcomponent(TransferSendPresenterModule transferSendPresenterModule, TransferSendControllerModule transferSendControllerModule);

    TwoFactorControllerSubcomponent addTwoFactorControllerSubcomponent(TwoFactorPresenterModule twoFactorPresenterModule);

    UpfrontKycIdentityAcceptableDocumentsControllerSubcomponent addUpfrontKycIdentityAcceptableDocumentsControllerSubcomponent(UpfrontKycIdentityAcceptableDocumentsPresenterModule upfrontKycIdentityAcceptableDocumentsPresenterModule);

    UpfrontKycIdentityDocumentScanControllerSubcomponent addUpfrontKycIdentityDocumentScanControllerSubcomponent(UpfrontKycIdentityDocumentScanPresenterModule upfrontKycIdentityDocumentScanPresenterModule);

    UpfrontKycIdentityFailedControllerSubcomponent addUpfrontKycIdentityFailedControllerSubcomponent(UpfrontKycIdentityFailedPresenterModule upfrontKycIdentityFailedPresenterModule);

    UpfrontKycIdentityProcessingControllerSubcomponent addUpfrontKycIdentityProcessingControllerSubcomponent(UpfrontKycIdentityProcessingPresenterModule upfrontKycIdentityProcessingPresenterModule);

    UpfrontKycTakeDocumentPhotoControllerSubcomponent addUpfrontKycTakeDocumentPhotoControllerSubcomponent(UpfrontKycTakeDocumentPhotoPresenterModule upfrontKycTakeDocumentPhotoPresenterModule);

    WithdrawDepositSuccessControllerSubcomponent addWithdrawDepositSuccessControllerSubcomponent(WithdrawDepositSuccessPresenterModule withdrawDepositSuccessPresenterModule);

    WithdrawalBasedLimitsDialogControllerSubcomponent addWithdrawalBasedLimitsDialogControllerSubcomponent(WithdrawalBasedLimitsDialogPresenterModule withdrawalBasedLimitsDialogPresenterModule);

    CardFormControllerSubcomponent cardFormControllerSubcomponent(CardFormPresenterModule cardFormPresenterModule);

    DepositFiatConfirmationControllerSubcomponent depositFiatConfirmationControllerSubcomponent(DepositFiatConfirmationPresenterModule depositFiatConfirmationPresenterModule);

    DepositFiatControllerSubcomponent depositFiatControllerSubcomponent(QuickstartModule quickstartModule, DepositFiatPresenterModule depositFiatPresenterModule);

    FiatTransactionsControllerSubcomponent fiatTransactionsControllerSubcomponent(QuickstartModule quickstartModule, FiatTransactionsControllerModule fiatTransactionsControllerModule);

    void inject(QuickBuyListLayout quickBuyListLayout);

    void inject(AlertDialogController alertDialogController);

    void inject(ConfirmationDialogController confirmationDialogController);

    void inject(IdentityVerificationActivity identityVerificationActivity);

    void inject(IdentityVerificationController identityVerificationController);

    void inject(RetakePhotoDialogController retakePhotoDialogController);

    void inject(PaymentMethodVerificationController paymentMethodVerificationController);

    void inject(LinkedAccountItemLayout linkedAccountItemLayout);

    void inject(AddPhoneDialogController addPhoneDialogController);

    void inject(GoToSettingsDialogController goToSettingsDialogController);

    void inject(IntroActivity introActivity);

    void inject(RegistrationControllerActivity registrationControllerActivity);

    void inject(TransactionDetailLayout transactionDetailLayout);

    void inject(ActionBarController actionBarController);

    void inject(CenteredAppBarLayout centeredAppBarLayout);

    void inject(DatePickerSpinnerLayout datePickerSpinnerLayout);

    void inject(DialogController dialogController);

    void inject(ModalControllerLifeCycle modalControllerLifeCycle);

    void inject(NumericKeyboardLayout numericKeyboardLayout);

    void inject(PageControllerLifeCycle pageControllerLifeCycle);

    MainControllerSubcomponent mainControllerSubcomponent(MainControllerPresenterModule mainControllerPresenterModule);

    MystiqueBottomNavigationLayoutSubcomponent mystiqueBottomNavigationLayoutSubcomponent(MystiqueBottomNavigationPresenterModule mystiqueBottomNavigationPresenterModule);

    PaymentMethodsControllerSubcomponent paymentMethodsControllerSubcomponent(PaymentMethodsPresenterModule paymentMethodsPresenterModule);

    PriceAlertsControllerSubcomponent priceAlertsControllerSubcomponent(PriceAlertsPresenterModule priceAlertsPresenterModule);

    WithdrawFiatConfirmationControllerSubcomponent withdrawFiatConfirmationControllerSubcomponent(WithdrawFiatConfirmationPresenterModule withdrawFiatConfirmationPresenterModule);

    WithdrawFiatControllerSubcomponent withdrawFiatControllerSubcomponent(QuickstartModule quickstartModule, WithdrawFiatPresenterModule withdrawFiatPresenterModule);
}
