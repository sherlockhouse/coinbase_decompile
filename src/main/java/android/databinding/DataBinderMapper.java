package android.databinding;

import android.view.View;
import com.coinbase.android.R;
import com.coinbase.android.databinding.AcceptableDocItemBinding;
import com.coinbase.android.databinding.ActivityCardBuyVerifyBinding;
import com.coinbase.android.databinding.ActivityCreatePriceAlertBinding;
import com.coinbase.android.databinding.ActivityIntroBinding;
import com.coinbase.android.databinding.ActivityLoginBinding;
import com.coinbase.android.databinding.ActivityMainContentBinding;
import com.coinbase.android.databinding.ActivityModalMystiqueBinding;
import com.coinbase.android.databinding.ActivityPinpromptBinding;
import com.coinbase.android.databinding.ActivitySignupBinding;
import com.coinbase.android.databinding.AddPlaidAccountControllerBindingImpl;
import com.coinbase.android.databinding.AddPlaidAccountControllerBindingLargeLandImpl;
import com.coinbase.android.databinding.AddPlaidAccountControllerContentBinding;
import com.coinbase.android.databinding.BankCdvAmountsFormBinding;
import com.coinbase.android.databinding.BottomItemBinding;
import com.coinbase.android.databinding.BottomItemModalBinding;
import com.coinbase.android.databinding.CardCdvAmountsFormBinding;
import com.coinbase.android.databinding.CardFormControllerBinding;
import com.coinbase.android.databinding.ControllerAcceptTermsBinding;
import com.coinbase.android.databinding.ControllerAcceptableDocumentsBindingImpl;
import com.coinbase.android.databinding.ControllerAcceptableDocumentsBindingLargeLandImpl;
import com.coinbase.android.databinding.ControllerAcceptableDocumentsContentBinding;
import com.coinbase.android.databinding.ControllerAddBankErrorBinding;
import com.coinbase.android.databinding.ControllerAvailableBalanceBinding;
import com.coinbase.android.databinding.ControllerBankPickerBinding;
import com.coinbase.android.databinding.ControllerBuyBinding;
import com.coinbase.android.databinding.ControllerBuysellConfirmationBinding;
import com.coinbase.android.databinding.ControllerBuysellSuccessBinding;
import com.coinbase.android.databinding.ControllerDepositFiatBinding;
import com.coinbase.android.databinding.ControllerDeviceVerifyBinding;
import com.coinbase.android.databinding.ControllerDialogBinding;
import com.coinbase.android.databinding.ControllerDialogConfirmSendBinding;
import com.coinbase.android.databinding.ControllerEmailVerifyBinding;
import com.coinbase.android.databinding.ControllerFiatConfirmationBinding;
import com.coinbase.android.databinding.ControllerGdprIntroBinding;
import com.coinbase.android.databinding.ControllerGdprMarketingEmailPromptBinding;
import com.coinbase.android.databinding.ControllerGdprPrivacyPolicyBinding;
import com.coinbase.android.databinding.ControllerGdprRequestSentBinding;
import com.coinbase.android.databinding.ControllerIdentityDocumentScanBindingImpl;
import com.coinbase.android.databinding.ControllerIdentityDocumentScanBindingLargeLandImpl;
import com.coinbase.android.databinding.ControllerIdentityVerificationBindingImpl;
import com.coinbase.android.databinding.ControllerIdentityVerificationBindingLargeLandImpl;
import com.coinbase.android.databinding.ControllerIdentityVerificationContentBinding;
import com.coinbase.android.databinding.ControllerIdologyFailureBinding;
import com.coinbase.android.databinding.ControllerIdologySettingsBinding;
import com.coinbase.android.databinding.ControllerIdologySettingsQuestionsBinding;
import com.coinbase.android.databinding.ControllerIdologyStateFailureBinding;
import com.coinbase.android.databinding.ControllerIdologySuccessBinding;
import com.coinbase.android.databinding.ControllerInvestmentTiersRequirementsBinding;
import com.coinbase.android.databinding.ControllerInvestmentTiersSettingsBinding;
import com.coinbase.android.databinding.ControllerJumioCompleteBinding;
import com.coinbase.android.databinding.ControllerJumioDocumentScanContentBinding;
import com.coinbase.android.databinding.ControllerLinkedAccountsPickerBinding;
import com.coinbase.android.databinding.ControllerPlaidBinding;
import com.coinbase.android.databinding.ControllerPrivacyRightsBinding;
import com.coinbase.android.databinding.ControllerRequestDataBinding;
import com.coinbase.android.databinding.ControllerSellBinding;
import com.coinbase.android.databinding.ControllerSendBinding;
import com.coinbase.android.databinding.ControllerStateIdologyAddressFormBinding;
import com.coinbase.android.databinding.ControllerStateIdologyNameFormBinding;
import com.coinbase.android.databinding.ControllerStateIdologyQuestionsBinding;
import com.coinbase.android.databinding.ControllerStateIdologyRetryFormBinding;
import com.coinbase.android.databinding.ControllerStateIdologySourceOfFundsFormBinding;
import com.coinbase.android.databinding.ControllerStateLockedBinding;
import com.coinbase.android.databinding.ControllerStateSelectorBinding;
import com.coinbase.android.databinding.ControllerStateSuspendedBinding;
import com.coinbase.android.databinding.ControllerTakeDocumentPhotoBinding;
import com.coinbase.android.databinding.ControllerTakeDocumentPhotoContentBinding;
import com.coinbase.android.databinding.ControllerTransferSendBinding;
import com.coinbase.android.databinding.ControllerTwoFactorBinding;
import com.coinbase.android.databinding.ControllerUpfrontKycAcceptableDocumentsContentBinding;
import com.coinbase.android.databinding.ControllerUpfrontKycIdentityAcceptableDocumentsBinding;
import com.coinbase.android.databinding.ControllerUpfrontKycIdentityDocumentScanBinding;
import com.coinbase.android.databinding.ControllerUpfrontKycIdentityFailedBinding;
import com.coinbase.android.databinding.ControllerUpfrontKycIdvProcessingBinding;
import com.coinbase.android.databinding.ControllerUpfrontKycTakeDocumentPhotoBinding;
import com.coinbase.android.databinding.ControllerWithdrawFiatBinding;
import com.coinbase.android.databinding.ControllerWithdrawdepositSuccessBinding;
import com.coinbase.android.databinding.DepositWithdrawLimitBinding;
import com.coinbase.android.databinding.DialogAddPhoneBinding;
import com.coinbase.android.databinding.DialogAlertBinding;
import com.coinbase.android.databinding.DialogConfirmBuysellBinding;
import com.coinbase.android.databinding.DialogConfirmationBinding;
import com.coinbase.android.databinding.DialogExistingUserBinding;
import com.coinbase.android.databinding.DialogGotoSettingsBinding;
import com.coinbase.android.databinding.DialogLaunchMessageBinding;
import com.coinbase.android.databinding.DialogVerifyPhoneBinding;
import com.coinbase.android.databinding.DialogWithdrawalBasedLimitsErrorBinding;
import com.coinbase.android.databinding.DropdownItemContactSuggestionBinding;
import com.coinbase.android.databinding.FragmentAccountMainBinding;
import com.coinbase.android.databinding.FragmentAccountSettingsBinding;
import com.coinbase.android.databinding.FragmentAccountTransactionsBinding;
import com.coinbase.android.databinding.FragmentAddBillingAddressBinding;
import com.coinbase.android.databinding.FragmentDashboardBinding;
import com.coinbase.android.databinding.FragmentDashboardCurrencyBinding;
import com.coinbase.android.databinding.FragmentDepositBinding;
import com.coinbase.android.databinding.FragmentIavLoginBindingImpl;
import com.coinbase.android.databinding.FragmentIavLoginBindingLargeLandImpl;
import com.coinbase.android.databinding.FragmentIavLoginContentBinding;
import com.coinbase.android.databinding.FragmentPhoneNumberBinding;
import com.coinbase.android.databinding.FragmentPinSettingsDialogBinding;
import com.coinbase.android.databinding.FragmentPlaidAccountLoginBindingImpl;
import com.coinbase.android.databinding.FragmentPlaidAccountLoginBindingLargeLandImpl;
import com.coinbase.android.databinding.FragmentPlaidAccountLoginContentBinding;
import com.coinbase.android.databinding.FragmentPriceAlertsBinding;
import com.coinbase.android.databinding.FragmentPricechartBinding;
import com.coinbase.android.databinding.FragmentSepaDepositWithdrawBinding;
import com.coinbase.android.databinding.FragmentTextDialogBinding;
import com.coinbase.android.databinding.FragmentTransferTypeBinding;
import com.coinbase.android.databinding.GridItemBankBinding;
import com.coinbase.android.databinding.KeypadBinding;
import com.coinbase.android.databinding.LayoutAccountAddressBinding;
import com.coinbase.android.databinding.LayoutAccountListBinding;
import com.coinbase.android.databinding.LayoutAccountRefreshListBinding;
import com.coinbase.android.databinding.LayoutAlertsListBinding;
import com.coinbase.android.databinding.LayoutAvailableBalanceAppBarBinding;
import com.coinbase.android.databinding.LayoutAvailableBalanceBinding;
import com.coinbase.android.databinding.LayoutCenteredAppBarBinding;
import com.coinbase.android.databinding.LayoutConnectedAccountListBinding;
import com.coinbase.android.databinding.LayoutCurrencyTabBinding;
import com.coinbase.android.databinding.LayoutDashboardPricechartListBinding;
import com.coinbase.android.databinding.LayoutDashboardTabPeriodBinding;
import com.coinbase.android.databinding.LayoutEnableSendReceiveBinding;
import com.coinbase.android.databinding.LayoutIdologyAddressFormBinding;
import com.coinbase.android.databinding.LayoutIdologyDatePickerBinding;
import com.coinbase.android.databinding.LayoutIdologyFormBinding;
import com.coinbase.android.databinding.LayoutIdologyNameFormBinding;
import com.coinbase.android.databinding.LayoutIdologyQuestionsBinding;
import com.coinbase.android.databinding.LayoutIdologySourceOfFundsFormBinding;
import com.coinbase.android.databinding.LayoutLinkedAccountItemBinding;
import com.coinbase.android.databinding.LayoutNumericKeyboardBinding;
import com.coinbase.android.databinding.LayoutPriceChartBinding;
import com.coinbase.android.databinding.LayoutQuickBuyListBinding;
import com.coinbase.android.databinding.LayoutTransactionDetailBinding;
import com.coinbase.android.databinding.LayoutTransactionsListBinding;
import com.coinbase.android.databinding.LayoutTransferSendBinding;
import com.coinbase.android.databinding.LimitAndFeatureItemBinding;
import com.coinbase.android.databinding.ListItemAccountBinding;
import com.coinbase.android.databinding.ListItemAlertBinding;
import com.coinbase.android.databinding.ListItemAvailablePaymentMethodBinding;
import com.coinbase.android.databinding.ListItemBillingAddressBinding;
import com.coinbase.android.databinding.ListItemBuysellFiatBinding;
import com.coinbase.android.databinding.ListItemBuysellPaymentMethodBinding;
import com.coinbase.android.databinding.ListItemBuysellPaymentMethodHeaderBinding;
import com.coinbase.android.databinding.ListItemConfirmationDetailBinding;
import com.coinbase.android.databinding.ListItemConfirmationFeeBinding;
import com.coinbase.android.databinding.ListItemConfirmationPaymentMethodBinding;
import com.coinbase.android.databinding.ListItemConfirmationTotalBinding;
import com.coinbase.android.databinding.ListItemConnectedAccountBinding;
import com.coinbase.android.databinding.ListItemConnectedAccountFooterBinding;
import com.coinbase.android.databinding.ListItemContactSuggestionBinding;
import com.coinbase.android.databinding.ListItemCreatePriceAlertBinding;
import com.coinbase.android.databinding.ListItemDashboardPricechartBinding;
import com.coinbase.android.databinding.ListItemIdologyQuestionBinding;
import com.coinbase.android.databinding.ListItemJumioProfileBinding;
import com.coinbase.android.databinding.ListItemMfaBinding;
import com.coinbase.android.databinding.ListItemPaymentMethodBinding;
import com.coinbase.android.databinding.ListItemPaymentMethodHeaderBinding;
import com.coinbase.android.databinding.ListItemPhoneNumberBinding;
import com.coinbase.android.databinding.ListItemPlaidMfaBinding;
import com.coinbase.android.databinding.ListItemPriceAlertBinding;
import com.coinbase.android.databinding.ListItemQuickBuyBinding;
import com.coinbase.android.databinding.ListItemQuickstartBinding;
import com.coinbase.android.databinding.ListItemSelectionBinding;
import com.coinbase.android.databinding.ListItemSepaDepositInfoBinding;
import com.coinbase.android.databinding.ListItemSettingsBinding;
import com.coinbase.android.databinding.ListItemSettingsConnectedAccountsBinding;
import com.coinbase.android.databinding.ListItemSettingsHeaderBinding;
import com.coinbase.android.databinding.ListItemSettingsInfoBinding;
import com.coinbase.android.databinding.ListItemTransactionBinding;
import com.coinbase.android.databinding.ListItemTransactionDetailBinding;
import com.coinbase.android.databinding.ListItemTransactionFooterBinding;
import com.coinbase.android.databinding.MystiqueBottomNavigationControllerBinding;
import com.coinbase.android.databinding.MystiqueBottomNavigationLayoutBinding;
import com.coinbase.android.databinding.MystiqueListSelectorLayoutBinding;
import com.coinbase.android.databinding.NumberKeyboardBinding;
import com.coinbase.android.databinding.OverlayBuysellDepositBinding;
import com.coinbase.android.databinding.PaymentMethodControllerBinding;
import com.coinbase.android.databinding.PaymentMethodVerificationControllerBinding;
import com.coinbase.android.databinding.PendingHoldBinding;
import com.coinbase.android.databinding.PlaidLoginAllDoneBinding;
import com.coinbase.android.databinding.RequirementItemBinding;
import com.coinbase.android.databinding.SigninPhoneNumberControllerBinding;
import com.coinbase.android.databinding.SigninVerifyPhoneNumberControllerBinding;
import com.coinbase.android.databinding.SpinnerDropdownDepositBinding;
import com.coinbase.android.databinding.SpinnerOptionDepositBinding;
import com.coinbase.android.databinding.TierRequirementItemBinding;
import com.coinbase.android.databinding.TiersItemBinding;
import com.coinbase.android.databinding.UpfrontKycAcceptableDocItemBinding;
import com.coinbase.android.databinding.UpfrontKycTakeDocumentPhotoHintItemBinding;
import com.coinbase.android.databinding.UpfrontKycVerifyDocumentPhotoHintItemBinding;

class DataBinderMapper {
    static final int TARGET_MIN_SDK = 19;

    public ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View view, int layoutId) {
        Object tag;
        switch (layoutId) {
            case R.layout.acceptable_doc_item:
                return AcceptableDocItemBinding.bind(view, bindingComponent);
            case R.layout.activity_card_buy_verify:
                return ActivityCardBuyVerifyBinding.bind(view, bindingComponent);
            case R.layout.activity_create_price_alert:
                return ActivityCreatePriceAlertBinding.bind(view, bindingComponent);
            case R.layout.activity_intro:
                return ActivityIntroBinding.bind(view, bindingComponent);
            case R.layout.activity_login:
                return ActivityLoginBinding.bind(view, bindingComponent);
            case R.layout.activity_main_content:
                return ActivityMainContentBinding.bind(view, bindingComponent);
            case R.layout.activity_modal_mystique:
                return ActivityModalMystiqueBinding.bind(view, bindingComponent);
            case R.layout.activity_pinprompt:
                return ActivityPinpromptBinding.bind(view, bindingComponent);
            case R.layout.activity_signup:
                return ActivitySignupBinding.bind(view, bindingComponent);
            case R.layout.add_plaid_account_controller:
                tag = view.getTag();
                if (tag == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout/add_plaid_account_controller_0".equals(tag)) {
                    return new AddPlaidAccountControllerBindingImpl(bindingComponent, view);
                } else {
                    if ("layout-large-land/add_plaid_account_controller_0".equals(tag)) {
                        return new AddPlaidAccountControllerBindingLargeLandImpl(bindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for add_plaid_account_controller is invalid. Received: " + tag);
                }
            case R.layout.add_plaid_account_controller_content:
                return AddPlaidAccountControllerContentBinding.bind(view, bindingComponent);
            case R.layout.bank_cdv_amounts_form:
                return BankCdvAmountsFormBinding.bind(view, bindingComponent);
            case R.layout.bottom_item:
                return BottomItemBinding.bind(view, bindingComponent);
            case R.layout.bottom_item_modal:
                return BottomItemModalBinding.bind(view, bindingComponent);
            case R.layout.card_cdv_amounts_form:
                return CardCdvAmountsFormBinding.bind(view, bindingComponent);
            case R.layout.card_form_controller:
                return CardFormControllerBinding.bind(view, bindingComponent);
            case R.layout.controller_accept_terms:
                return ControllerAcceptTermsBinding.bind(view, bindingComponent);
            case R.layout.controller_acceptable_documents:
                tag = view.getTag();
                if (tag == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout-large-land/controller_acceptable_documents_0".equals(tag)) {
                    return new ControllerAcceptableDocumentsBindingLargeLandImpl(bindingComponent, view);
                } else {
                    if ("layout/controller_acceptable_documents_0".equals(tag)) {
                        return new ControllerAcceptableDocumentsBindingImpl(bindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for controller_acceptable_documents is invalid. Received: " + tag);
                }
            case R.layout.controller_acceptable_documents_content:
                return ControllerAcceptableDocumentsContentBinding.bind(view, bindingComponent);
            case R.layout.controller_add_bank_error:
                return ControllerAddBankErrorBinding.bind(view, bindingComponent);
            case R.layout.controller_available_balance:
                return ControllerAvailableBalanceBinding.bind(view, bindingComponent);
            case R.layout.controller_bank_picker:
                return ControllerBankPickerBinding.bind(view, bindingComponent);
            case R.layout.controller_buy:
                return ControllerBuyBinding.bind(view, bindingComponent);
            case R.layout.controller_buysell_confirmation:
                return ControllerBuysellConfirmationBinding.bind(view, bindingComponent);
            case R.layout.controller_buysell_success:
                return ControllerBuysellSuccessBinding.bind(view, bindingComponent);
            case R.layout.controller_deposit_fiat:
                return ControllerDepositFiatBinding.bind(view, bindingComponent);
            case R.layout.controller_device_verify:
                return ControllerDeviceVerifyBinding.bind(view, bindingComponent);
            case R.layout.controller_dialog:
                return ControllerDialogBinding.bind(view, bindingComponent);
            case R.layout.controller_dialog_confirm_send:
                return ControllerDialogConfirmSendBinding.bind(view, bindingComponent);
            case R.layout.controller_email_verify:
                return ControllerEmailVerifyBinding.bind(view, bindingComponent);
            case R.layout.controller_fiat_confirmation:
                return ControllerFiatConfirmationBinding.bind(view, bindingComponent);
            case R.layout.controller_gdpr_intro:
                return ControllerGdprIntroBinding.bind(view, bindingComponent);
            case R.layout.controller_gdpr_marketing_email_prompt:
                return ControllerGdprMarketingEmailPromptBinding.bind(view, bindingComponent);
            case R.layout.controller_gdpr_privacy_policy:
                return ControllerGdprPrivacyPolicyBinding.bind(view, bindingComponent);
            case R.layout.controller_gdpr_request_sent:
                return ControllerGdprRequestSentBinding.bind(view, bindingComponent);
            case R.layout.controller_identity_document_scan:
                tag = view.getTag();
                if (tag == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout/controller_identity_document_scan_0".equals(tag)) {
                    return new ControllerIdentityDocumentScanBindingImpl(bindingComponent, view);
                } else {
                    if ("layout-large-land/controller_identity_document_scan_0".equals(tag)) {
                        return new ControllerIdentityDocumentScanBindingLargeLandImpl(bindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for controller_identity_document_scan is invalid. Received: " + tag);
                }
            case R.layout.controller_identity_verification:
                tag = view.getTag();
                if (tag == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout-large-land/controller_identity_verification_0".equals(tag)) {
                    return new ControllerIdentityVerificationBindingLargeLandImpl(bindingComponent, view);
                } else {
                    if ("layout/controller_identity_verification_0".equals(tag)) {
                        return new ControllerIdentityVerificationBindingImpl(bindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for controller_identity_verification is invalid. Received: " + tag);
                }
            case R.layout.controller_identity_verification_content:
                return ControllerIdentityVerificationContentBinding.bind(view, bindingComponent);
            case R.layout.controller_idology_failure:
                return ControllerIdologyFailureBinding.bind(view, bindingComponent);
            case R.layout.controller_idology_settings:
                return ControllerIdologySettingsBinding.bind(view, bindingComponent);
            case R.layout.controller_idology_settings_questions:
                return ControllerIdologySettingsQuestionsBinding.bind(view, bindingComponent);
            case R.layout.controller_idology_state_failure:
                return ControllerIdologyStateFailureBinding.bind(view, bindingComponent);
            case R.layout.controller_idology_success:
                return ControllerIdologySuccessBinding.bind(view, bindingComponent);
            case R.layout.controller_investment_tiers_requirements:
                return ControllerInvestmentTiersRequirementsBinding.bind(view, bindingComponent);
            case R.layout.controller_investment_tiers_settings:
                return ControllerInvestmentTiersSettingsBinding.bind(view, bindingComponent);
            case R.layout.controller_jumio_complete:
                return ControllerJumioCompleteBinding.bind(view, bindingComponent);
            case R.layout.controller_jumio_document_scan_content:
                return ControllerJumioDocumentScanContentBinding.bind(view, bindingComponent);
            case R.layout.controller_linked_accounts_picker:
                return ControllerLinkedAccountsPickerBinding.bind(view, bindingComponent);
            case R.layout.controller_plaid:
                return ControllerPlaidBinding.bind(view, bindingComponent);
            case R.layout.controller_privacy_rights:
                return ControllerPrivacyRightsBinding.bind(view, bindingComponent);
            case R.layout.controller_request_data:
                return ControllerRequestDataBinding.bind(view, bindingComponent);
            case R.layout.controller_sell:
                return ControllerSellBinding.bind(view, bindingComponent);
            case R.layout.controller_send:
                return ControllerSendBinding.bind(view, bindingComponent);
            case R.layout.controller_state_idology_address_form:
                return ControllerStateIdologyAddressFormBinding.bind(view, bindingComponent);
            case R.layout.controller_state_idology_name_form:
                return ControllerStateIdologyNameFormBinding.bind(view, bindingComponent);
            case R.layout.controller_state_idology_questions:
                return ControllerStateIdologyQuestionsBinding.bind(view, bindingComponent);
            case R.layout.controller_state_idology_retry_form:
                return ControllerStateIdologyRetryFormBinding.bind(view, bindingComponent);
            case R.layout.controller_state_idology_source_of_funds_form:
                return ControllerStateIdologySourceOfFundsFormBinding.bind(view, bindingComponent);
            case R.layout.controller_state_locked:
                return ControllerStateLockedBinding.bind(view, bindingComponent);
            case R.layout.controller_state_selector:
                return ControllerStateSelectorBinding.bind(view, bindingComponent);
            case R.layout.controller_state_suspended:
                return ControllerStateSuspendedBinding.bind(view, bindingComponent);
            case R.layout.controller_take_document_photo:
                return ControllerTakeDocumentPhotoBinding.bind(view, bindingComponent);
            case R.layout.controller_take_document_photo_content:
                return ControllerTakeDocumentPhotoContentBinding.bind(view, bindingComponent);
            case R.layout.controller_transfer_send:
                return ControllerTransferSendBinding.bind(view, bindingComponent);
            case R.layout.controller_two_factor:
                return ControllerTwoFactorBinding.bind(view, bindingComponent);
            case R.layout.controller_upfront_kyc_acceptable_documents_content:
                return ControllerUpfrontKycAcceptableDocumentsContentBinding.bind(view, bindingComponent);
            case R.layout.controller_upfront_kyc_identity_acceptable_documents:
                return ControllerUpfrontKycIdentityAcceptableDocumentsBinding.bind(view, bindingComponent);
            case R.layout.controller_upfront_kyc_identity_document_scan:
                return ControllerUpfrontKycIdentityDocumentScanBinding.bind(view, bindingComponent);
            case R.layout.controller_upfront_kyc_identity_failed:
                return ControllerUpfrontKycIdentityFailedBinding.bind(view, bindingComponent);
            case R.layout.controller_upfront_kyc_idv_processing:
                return ControllerUpfrontKycIdvProcessingBinding.bind(view, bindingComponent);
            case R.layout.controller_upfront_kyc_take_document_photo:
                return ControllerUpfrontKycTakeDocumentPhotoBinding.bind(view, bindingComponent);
            case R.layout.controller_withdraw_fiat:
                return ControllerWithdrawFiatBinding.bind(view, bindingComponent);
            case R.layout.controller_withdrawdeposit_success:
                return ControllerWithdrawdepositSuccessBinding.bind(view, bindingComponent);
            case R.layout.deposit_withdraw_limit:
                return DepositWithdrawLimitBinding.bind(view, bindingComponent);
            case R.layout.dialog_add_phone:
                return DialogAddPhoneBinding.bind(view, bindingComponent);
            case R.layout.dialog_alert:
                return DialogAlertBinding.bind(view, bindingComponent);
            case R.layout.dialog_confirm_buysell:
                return DialogConfirmBuysellBinding.bind(view, bindingComponent);
            case R.layout.dialog_confirmation:
                return DialogConfirmationBinding.bind(view, bindingComponent);
            case R.layout.dialog_existing_user:
                return DialogExistingUserBinding.bind(view, bindingComponent);
            case R.layout.dialog_goto_settings:
                return DialogGotoSettingsBinding.bind(view, bindingComponent);
            case R.layout.dialog_launch_message:
                return DialogLaunchMessageBinding.bind(view, bindingComponent);
            case R.layout.dialog_verify_phone:
                return DialogVerifyPhoneBinding.bind(view, bindingComponent);
            case R.layout.dialog_withdrawal_based_limits_error:
                return DialogWithdrawalBasedLimitsErrorBinding.bind(view, bindingComponent);
            case R.layout.dropdown_item_contact_suggestion:
                return DropdownItemContactSuggestionBinding.bind(view, bindingComponent);
            case R.layout.fragment_account_main:
                return FragmentAccountMainBinding.bind(view, bindingComponent);
            case R.layout.fragment_account_settings:
                return FragmentAccountSettingsBinding.bind(view, bindingComponent);
            case R.layout.fragment_account_transactions:
                return FragmentAccountTransactionsBinding.bind(view, bindingComponent);
            case R.layout.fragment_add_billing_address:
                return FragmentAddBillingAddressBinding.bind(view, bindingComponent);
            case R.layout.fragment_dashboard:
                return FragmentDashboardBinding.bind(view, bindingComponent);
            case R.layout.fragment_dashboard_currency:
                return FragmentDashboardCurrencyBinding.bind(view, bindingComponent);
            case R.layout.fragment_deposit:
                return FragmentDepositBinding.bind(view, bindingComponent);
            case R.layout.fragment_iav_login:
                tag = view.getTag();
                if (tag == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout/fragment_iav_login_0".equals(tag)) {
                    return new FragmentIavLoginBindingImpl(bindingComponent, view);
                } else {
                    if ("layout-large-land/fragment_iav_login_0".equals(tag)) {
                        return new FragmentIavLoginBindingLargeLandImpl(bindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_iav_login is invalid. Received: " + tag);
                }
            case R.layout.fragment_iav_login_content:
                return FragmentIavLoginContentBinding.bind(view, bindingComponent);
            case R.layout.fragment_phone_number:
                return FragmentPhoneNumberBinding.bind(view, bindingComponent);
            case R.layout.fragment_pin_settings_dialog:
                return FragmentPinSettingsDialogBinding.bind(view, bindingComponent);
            case R.layout.fragment_plaid_account_login:
                tag = view.getTag();
                if (tag == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout/fragment_plaid_account_login_0".equals(tag)) {
                    return new FragmentPlaidAccountLoginBindingImpl(bindingComponent, view);
                } else {
                    if ("layout-large-land/fragment_plaid_account_login_0".equals(tag)) {
                        return new FragmentPlaidAccountLoginBindingLargeLandImpl(bindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_plaid_account_login is invalid. Received: " + tag);
                }
            case R.layout.fragment_plaid_account_login_content:
                return FragmentPlaidAccountLoginContentBinding.bind(view, bindingComponent);
            case R.layout.fragment_price_alerts:
                return FragmentPriceAlertsBinding.bind(view, bindingComponent);
            case R.layout.fragment_pricechart:
                return FragmentPricechartBinding.bind(view, bindingComponent);
            case R.layout.fragment_sepa_deposit_withdraw:
                return FragmentSepaDepositWithdrawBinding.bind(view, bindingComponent);
            case R.layout.fragment_text_dialog:
                return FragmentTextDialogBinding.bind(view, bindingComponent);
            case R.layout.fragment_transfer_type:
                return FragmentTransferTypeBinding.bind(view, bindingComponent);
            case R.layout.grid_item_bank:
                return GridItemBankBinding.bind(view, bindingComponent);
            case R.layout.keypad:
                return KeypadBinding.bind(view, bindingComponent);
            case R.layout.layout_account_address:
                return LayoutAccountAddressBinding.bind(view, bindingComponent);
            case R.layout.layout_account_list:
                return LayoutAccountListBinding.bind(view, bindingComponent);
            case R.layout.layout_account_refresh_list:
                return LayoutAccountRefreshListBinding.bind(view, bindingComponent);
            case R.layout.layout_alerts_list:
                return LayoutAlertsListBinding.bind(view, bindingComponent);
            case R.layout.layout_available_balance:
                return LayoutAvailableBalanceBinding.bind(view, bindingComponent);
            case R.layout.layout_available_balance_app_bar:
                return LayoutAvailableBalanceAppBarBinding.bind(view, bindingComponent);
            case R.layout.layout_centered_app_bar:
                return LayoutCenteredAppBarBinding.bind(view, bindingComponent);
            case R.layout.layout_connected_account_list:
                return LayoutConnectedAccountListBinding.bind(view, bindingComponent);
            case R.layout.layout_currency_tab:
                return LayoutCurrencyTabBinding.bind(view, bindingComponent);
            case R.layout.layout_dashboard_pricechart_list:
                return LayoutDashboardPricechartListBinding.bind(view, bindingComponent);
            case R.layout.layout_dashboard_tab_period:
                return LayoutDashboardTabPeriodBinding.bind(view, bindingComponent);
            case R.layout.layout_enable_send_receive:
                return LayoutEnableSendReceiveBinding.bind(view, bindingComponent);
            case R.layout.layout_idology_address_form:
                return LayoutIdologyAddressFormBinding.bind(view, bindingComponent);
            case R.layout.layout_idology_date_picker:
                return LayoutIdologyDatePickerBinding.bind(view, bindingComponent);
            case R.layout.layout_idology_form:
                return LayoutIdologyFormBinding.bind(view, bindingComponent);
            case R.layout.layout_idology_name_form:
                return LayoutIdologyNameFormBinding.bind(view, bindingComponent);
            case R.layout.layout_idology_questions:
                return LayoutIdologyQuestionsBinding.bind(view, bindingComponent);
            case R.layout.layout_idology_source_of_funds_form:
                return LayoutIdologySourceOfFundsFormBinding.bind(view, bindingComponent);
            case R.layout.layout_linked_account_item:
                return LayoutLinkedAccountItemBinding.bind(view, bindingComponent);
            case R.layout.layout_numeric_keyboard:
                return LayoutNumericKeyboardBinding.bind(view, bindingComponent);
            case R.layout.layout_price_chart:
                return LayoutPriceChartBinding.bind(view, bindingComponent);
            case R.layout.layout_quick_buy_list:
                return LayoutQuickBuyListBinding.bind(view, bindingComponent);
            case R.layout.layout_transaction_detail:
                return LayoutTransactionDetailBinding.bind(view, bindingComponent);
            case R.layout.layout_transactions_list:
                return LayoutTransactionsListBinding.bind(view, bindingComponent);
            case R.layout.layout_transfer_send:
                return LayoutTransferSendBinding.bind(view, bindingComponent);
            case R.layout.limit_and_feature_item:
                return LimitAndFeatureItemBinding.bind(view, bindingComponent);
            case R.layout.list_item_account:
                return ListItemAccountBinding.bind(view, bindingComponent);
            case R.layout.list_item_alert:
                return ListItemAlertBinding.bind(view, bindingComponent);
            case R.layout.list_item_available_payment_method:
                return ListItemAvailablePaymentMethodBinding.bind(view, bindingComponent);
            case R.layout.list_item_billing_address:
                return ListItemBillingAddressBinding.bind(view, bindingComponent);
            case R.layout.list_item_buysell_fiat:
                return ListItemBuysellFiatBinding.bind(view, bindingComponent);
            case R.layout.list_item_buysell_payment_method:
                return ListItemBuysellPaymentMethodBinding.bind(view, bindingComponent);
            case R.layout.list_item_buysell_payment_method_header:
                return ListItemBuysellPaymentMethodHeaderBinding.bind(view, bindingComponent);
            case R.layout.list_item_confirmation_detail:
                return ListItemConfirmationDetailBinding.bind(view, bindingComponent);
            case R.layout.list_item_confirmation_fee:
                return ListItemConfirmationFeeBinding.bind(view, bindingComponent);
            case R.layout.list_item_confirmation_payment_method:
                return ListItemConfirmationPaymentMethodBinding.bind(view, bindingComponent);
            case R.layout.list_item_confirmation_total:
                return ListItemConfirmationTotalBinding.bind(view, bindingComponent);
            case R.layout.list_item_connected_account:
                return ListItemConnectedAccountBinding.bind(view, bindingComponent);
            case R.layout.list_item_connected_account_footer:
                return ListItemConnectedAccountFooterBinding.bind(view, bindingComponent);
            case R.layout.list_item_contact_suggestion:
                return ListItemContactSuggestionBinding.bind(view, bindingComponent);
            case R.layout.list_item_create_price_alert:
                return ListItemCreatePriceAlertBinding.bind(view, bindingComponent);
            case R.layout.list_item_dashboard_pricechart:
                return ListItemDashboardPricechartBinding.bind(view, bindingComponent);
            case R.layout.list_item_idology_question:
                return ListItemIdologyQuestionBinding.bind(view, bindingComponent);
            case R.layout.list_item_jumio_profile:
                return ListItemJumioProfileBinding.bind(view, bindingComponent);
            case R.layout.list_item_mfa:
                return ListItemMfaBinding.bind(view, bindingComponent);
            case R.layout.list_item_payment_method:
                return ListItemPaymentMethodBinding.bind(view, bindingComponent);
            case R.layout.list_item_payment_method_header:
                return ListItemPaymentMethodHeaderBinding.bind(view, bindingComponent);
            case R.layout.list_item_phone_number:
                return ListItemPhoneNumberBinding.bind(view, bindingComponent);
            case R.layout.list_item_plaid_mfa:
                return ListItemPlaidMfaBinding.bind(view, bindingComponent);
            case R.layout.list_item_price_alert:
                return ListItemPriceAlertBinding.bind(view, bindingComponent);
            case R.layout.list_item_quick_buy:
                return ListItemQuickBuyBinding.bind(view, bindingComponent);
            case R.layout.list_item_quickstart:
                return ListItemQuickstartBinding.bind(view, bindingComponent);
            case R.layout.list_item_selection:
                return ListItemSelectionBinding.bind(view, bindingComponent);
            case R.layout.list_item_sepa_deposit_info:
                return ListItemSepaDepositInfoBinding.bind(view, bindingComponent);
            case R.layout.list_item_settings:
                return ListItemSettingsBinding.bind(view, bindingComponent);
            case R.layout.list_item_settings_connected_accounts:
                return ListItemSettingsConnectedAccountsBinding.bind(view, bindingComponent);
            case R.layout.list_item_settings_header:
                return ListItemSettingsHeaderBinding.bind(view, bindingComponent);
            case R.layout.list_item_settings_info:
                return ListItemSettingsInfoBinding.bind(view, bindingComponent);
            case R.layout.list_item_transaction:
                return ListItemTransactionBinding.bind(view, bindingComponent);
            case R.layout.list_item_transaction_detail:
                return ListItemTransactionDetailBinding.bind(view, bindingComponent);
            case R.layout.list_item_transaction_footer:
                return ListItemTransactionFooterBinding.bind(view, bindingComponent);
            case R.layout.mystique_bottom_navigation_controller:
                return MystiqueBottomNavigationControllerBinding.bind(view, bindingComponent);
            case R.layout.mystique_bottom_navigation_layout:
                return MystiqueBottomNavigationLayoutBinding.bind(view, bindingComponent);
            case R.layout.mystique_list_selector_layout:
                return MystiqueListSelectorLayoutBinding.bind(view, bindingComponent);
            case R.layout.number_keyboard:
                return NumberKeyboardBinding.bind(view, bindingComponent);
            case R.layout.overlay_buysell_deposit:
                return OverlayBuysellDepositBinding.bind(view, bindingComponent);
            case R.layout.payment_method_controller:
                return PaymentMethodControllerBinding.bind(view, bindingComponent);
            case R.layout.payment_method_verification_controller:
                return PaymentMethodVerificationControllerBinding.bind(view, bindingComponent);
            case R.layout.pending_hold:
                return PendingHoldBinding.bind(view, bindingComponent);
            case R.layout.plaid_login_all_done:
                return PlaidLoginAllDoneBinding.bind(view, bindingComponent);
            case R.layout.requirement_item:
                return RequirementItemBinding.bind(view, bindingComponent);
            case R.layout.signin_phone_number_controller:
                return SigninPhoneNumberControllerBinding.bind(view, bindingComponent);
            case R.layout.signin_verify_phone_number_controller:
                return SigninVerifyPhoneNumberControllerBinding.bind(view, bindingComponent);
            case R.layout.spinner_dropdown_deposit:
                return SpinnerDropdownDepositBinding.bind(view, bindingComponent);
            case R.layout.spinner_option_deposit:
                return SpinnerOptionDepositBinding.bind(view, bindingComponent);
            case R.layout.tier_requirement_item:
                return TierRequirementItemBinding.bind(view, bindingComponent);
            case R.layout.tiers_item:
                return TiersItemBinding.bind(view, bindingComponent);
            case R.layout.upfront_kyc_acceptable_doc_item:
                return UpfrontKycAcceptableDocItemBinding.bind(view, bindingComponent);
            case R.layout.upfront_kyc_take_document_photo_hint_item:
                return UpfrontKycTakeDocumentPhotoHintItemBinding.bind(view, bindingComponent);
            case R.layout.upfront_kyc_verify_document_photo_hint_item:
                return UpfrontKycVerifyDocumentPhotoHintItemBinding.bind(view, bindingComponent);
            default:
                return null;
        }
    }

    ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View[] views, int layoutId) {
        return null;
    }

    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        switch (tag.hashCode()) {
            case -2134857085:
                if (tag.equals("layout/upfront_kyc_take_document_photo_hint_item_0")) {
                    return R.layout.upfront_kyc_take_document_photo_hint_item;
                }
                return 0;
            case -2085003985:
                if (tag.equals("layout/controller_buysell_success_0")) {
                    return R.layout.controller_buysell_success;
                }
                return 0;
            case -2084797587:
                if (tag.equals("layout/list_item_selection_0")) {
                    return R.layout.list_item_selection;
                }
                return 0;
            case -2023610915:
                if (tag.equals("layout/pending_hold_0")) {
                    return R.layout.pending_hold;
                }
                return 0;
            case -1996920391:
                return tag.equals("layout-large-land/controller_identity_verification_0") ? R.layout.controller_identity_verification : 0;
            case -1990745723:
                return tag.equals("layout-large-land/controller_identity_document_scan_0") ? R.layout.controller_identity_document_scan : 0;
            case -1980188328:
                if (tag.equals("layout/dialog_confirmation_0")) {
                    return R.layout.dialog_confirmation;
                }
                return 0;
            case -1975025051:
                if (tag.equals("layout/list_item_confirmation_payment_method_0")) {
                    return R.layout.list_item_confirmation_payment_method;
                }
                return 0;
            case -1972629032:
                if (tag.equals("layout/dialog_confirm_buysell_0")) {
                    return R.layout.dialog_confirm_buysell;
                }
                return 0;
            case -1921928921:
                if (tag.equals("layout/activity_card_buy_verify_0")) {
                    return R.layout.activity_card_buy_verify;
                }
                return 0;
            case -1910958234:
                if (tag.equals("layout/fragment_account_main_0")) {
                    return R.layout.fragment_account_main;
                }
                return 0;
            case -1805811968:
                if (tag.equals("layout/controller_state_suspended_0")) {
                    return R.layout.controller_state_suspended;
                }
                return 0;
            case -1795760875:
                if (tag.equals("layout/list_item_quick_buy_0")) {
                    return R.layout.list_item_quick_buy;
                }
                return 0;
            case -1781617431:
                if (tag.equals("layout/number_keyboard_0")) {
                    return R.layout.number_keyboard;
                }
                return 0;
            case -1734789181:
                if (tag.equals("layout/acceptable_doc_item_0")) {
                    return R.layout.acceptable_doc_item;
                }
                return 0;
            case -1647913384:
                if (tag.equals("layout/layout_idology_questions_0")) {
                    return R.layout.layout_idology_questions;
                }
                return 0;
            case -1626340949:
                if (tag.equals("layout/list_item_settings_info_0")) {
                    return R.layout.list_item_settings_info;
                }
                return 0;
            case -1561877111:
                if (tag.equals("layout/layout_enable_send_receive_0")) {
                    return R.layout.layout_enable_send_receive;
                }
                return 0;
            case -1558282341:
                if (tag.equals("layout/fragment_dashboard_0")) {
                    return R.layout.fragment_dashboard;
                }
                return 0;
            case -1532303825:
                if (tag.equals("layout/activity_main_content_0")) {
                    return R.layout.activity_main_content;
                }
                return 0;
            case -1531855414:
                if (tag.equals("layout/keypad_0")) {
                    return R.layout.keypad;
                }
                return 0;
            case -1523443509:
                return tag.equals("layout-large-land/add_plaid_account_controller_0") ? R.layout.add_plaid_account_controller : 0;
            case -1520359213:
                if (tag.equals("layout/list_item_transaction_detail_0")) {
                    return R.layout.list_item_transaction_detail;
                }
                return 0;
            case -1515855207:
                if (tag.equals("layout/controller_buy_0")) {
                    return R.layout.controller_buy;
                }
                return 0;
            case -1515343490:
                if (tag.equals("layout/dialog_existing_user_0")) {
                    return R.layout.dialog_existing_user;
                }
                return 0;
            case -1500718312:
                if (tag.equals("layout/tier_requirement_item_0")) {
                    return R.layout.tier_requirement_item;
                }
                return 0;
            case -1484947029:
                if (tag.equals("layout/controller_withdrawdeposit_success_0")) {
                    return R.layout.controller_withdrawdeposit_success;
                }
                return 0;
            case -1482266925:
                if (tag.equals("layout/controller_plaid_0")) {
                    return R.layout.controller_plaid;
                }
                return 0;
            case -1469393087:
                if (tag.equals("layout/list_item_dashboard_pricechart_0")) {
                    return R.layout.list_item_dashboard_pricechart;
                }
                return 0;
            case -1426047513:
                if (tag.equals("layout/mystique_bottom_navigation_layout_0")) {
                    return R.layout.mystique_bottom_navigation_layout;
                }
                return 0;
            case -1376776555:
                if (tag.equals("layout/fragment_transfer_type_0")) {
                    return R.layout.fragment_transfer_type;
                }
                return 0;
            case -1309245447:
                if (tag.equals("layout/controller_available_balance_0")) {
                    return R.layout.controller_available_balance;
                }
                return 0;
            case -1303663655:
                if (tag.equals("layout/mystique_bottom_navigation_controller_0")) {
                    return R.layout.mystique_bottom_navigation_controller;
                }
                return 0;
            case -1295539633:
                if (tag.equals("layout/fragment_iav_login_0")) {
                    return R.layout.fragment_iav_login;
                }
                return 0;
            case -1288105157:
                if (tag.equals("layout/list_item_payment_method_0")) {
                    return R.layout.list_item_payment_method;
                }
                return 0;
            case -1286312802:
                if (tag.equals("layout/layout_dashboard_pricechart_list_0")) {
                    return R.layout.layout_dashboard_pricechart_list;
                }
                return 0;
            case -1218278242:
                if (tag.equals("layout/controller_withdraw_fiat_0")) {
                    return R.layout.controller_withdraw_fiat;
                }
                return 0;
            case -1211336892:
                if (tag.equals("layout/controller_bank_picker_0")) {
                    return R.layout.controller_bank_picker;
                }
                return 0;
            case -1188315876:
                if (tag.equals("layout/controller_state_selector_0")) {
                    return R.layout.controller_state_selector;
                }
                return 0;
            case -1186519038:
                if (tag.equals("layout/fragment_account_transactions_0")) {
                    return R.layout.fragment_account_transactions;
                }
                return 0;
            case -1185760237:
                if (tag.equals("layout/activity_modal_mystique_0")) {
                    return R.layout.activity_modal_mystique;
                }
                return 0;
            case -1163255953:
                if (tag.equals("layout/controller_transfer_send_0")) {
                    return R.layout.controller_transfer_send;
                }
                return 0;
            case -1161424293:
                if (tag.equals("layout/controller_gdpr_intro_0")) {
                    return R.layout.controller_gdpr_intro;
                }
                return 0;
            case -1107835224:
                if (tag.equals("layout/upfront_kyc_acceptable_doc_item_0")) {
                    return R.layout.upfront_kyc_acceptable_doc_item;
                }
                return 0;
            case -1090913133:
                if (tag.equals("layout/layout_transaction_detail_0")) {
                    return R.layout.layout_transaction_detail;
                }
                return 0;
            case -1081305684:
                if (tag.equals("layout/controller_state_idology_source_of_funds_form_0")) {
                    return R.layout.controller_state_idology_source_of_funds_form;
                }
                return 0;
            case -1080040017:
                if (tag.equals("layout/mystique_list_selector_layout_0")) {
                    return R.layout.mystique_list_selector_layout;
                }
                return 0;
            case -1077893845:
                if (tag.equals("layout/layout_centered_app_bar_0")) {
                    return R.layout.layout_centered_app_bar;
                }
                return 0;
            case -1070521349:
                if (tag.equals("layout/controller_gdpr_request_sent_0")) {
                    return R.layout.controller_gdpr_request_sent;
                }
                return 0;
            case -1009034651:
                if (tag.equals("layout/grid_item_bank_0")) {
                    return R.layout.grid_item_bank;
                }
                return 0;
            case -1005357328:
                if (tag.equals("layout/layout_idology_source_of_funds_form_0")) {
                    return R.layout.layout_idology_source_of_funds_form;
                }
                return 0;
            case -997104278:
                if (tag.equals("layout/controller_upfront_kyc_identity_document_scan_0")) {
                    return R.layout.controller_upfront_kyc_identity_document_scan;
                }
                return 0;
            case -991363702:
                if (tag.equals("layout/list_item_plaid_mfa_0")) {
                    return R.layout.list_item_plaid_mfa;
                }
                return 0;
            case -988102778:
                if (tag.equals("layout/controller_deposit_fiat_0")) {
                    return R.layout.controller_deposit_fiat;
                }
                return 0;
            case -949839820:
                if (tag.equals("layout/controller_upfront_kyc_identity_acceptable_documents_0")) {
                    return R.layout.controller_upfront_kyc_identity_acceptable_documents;
                }
                return 0;
            case -934337664:
                if (tag.equals("layout/controller_idology_success_0")) {
                    return R.layout.controller_idology_success;
                }
                return 0;
            case -932554704:
                if (tag.equals("layout/spinner_option_deposit_0")) {
                    return R.layout.spinner_option_deposit;
                }
                return 0;
            case -907976389:
                if (tag.equals("layout/list_item_phone_number_0")) {
                    return R.layout.list_item_phone_number;
                }
                return 0;
            case -900820778:
                if (tag.equals("layout/controller_upfront_kyc_identity_failed_0")) {
                    return R.layout.controller_upfront_kyc_identity_failed;
                }
                return 0;
            case -888203365:
                if (tag.equals("layout/dialog_alert_0")) {
                    return R.layout.dialog_alert;
                }
                return 0;
            case -863868711:
                if (tag.equals("layout/list_item_settings_connected_accounts_0")) {
                    return R.layout.list_item_settings_connected_accounts;
                }
                return 0;
            case -861953859:
                if (tag.equals("layout/controller_investment_tiers_requirements_0")) {
                    return R.layout.controller_investment_tiers_requirements;
                }
                return 0;
            case -852910388:
                if (tag.equals("layout/list_item_sepa_deposit_info_0")) {
                    return R.layout.list_item_sepa_deposit_info;
                }
                return 0;
            case -839347292:
                if (tag.equals("layout/list_item_connected_account_footer_0")) {
                    return R.layout.list_item_connected_account_footer;
                }
                return 0;
            case -839024438:
                if (tag.equals("layout/list_item_settings_header_0")) {
                    return R.layout.list_item_settings_header;
                }
                return 0;
            case -803925545:
                if (tag.equals("layout/controller_dialog_0")) {
                    return R.layout.controller_dialog;
                }
                return 0;
            case -750670965:
                if (tag.equals("layout/controller_email_verify_0")) {
                    return R.layout.controller_email_verify;
                }
                return 0;
            case -736232412:
                if (tag.equals("layout/controller_acceptable_documents_content_0")) {
                    return R.layout.controller_acceptable_documents_content;
                }
                return 0;
            case -718341741:
                if (tag.equals("layout/list_item_payment_method_header_0")) {
                    return R.layout.list_item_payment_method_header;
                }
                return 0;
            case -685721894:
                if (tag.equals("layout/layout_numeric_keyboard_0")) {
                    return R.layout.layout_numeric_keyboard;
                }
                return 0;
            case -684412609:
                if (tag.equals("layout/activity_pinprompt_0")) {
                    return R.layout.activity_pinprompt;
                }
                return 0;
            case -644198649:
                if (tag.equals("layout/layout_available_balance_0")) {
                    return R.layout.layout_available_balance;
                }
                return 0;
            case -627900611:
                if (tag.equals("layout/list_item_alert_0")) {
                    return R.layout.list_item_alert;
                }
                return 0;
            case -595817468:
                if (tag.equals("layout/signin_verify_phone_number_controller_0")) {
                    return R.layout.signin_verify_phone_number_controller;
                }
                return 0;
            case -556434215:
                if (tag.equals("layout/controller_take_document_photo_0")) {
                    return R.layout.controller_take_document_photo;
                }
                return 0;
            case -513574626:
                if (tag.equals("layout/limit_and_feature_item_0")) {
                    return R.layout.limit_and_feature_item;
                }
                return 0;
            case -500383106:
                if (tag.equals("layout/list_item_buysell_fiat_0")) {
                    return R.layout.list_item_buysell_fiat;
                }
                return 0;
            case -474702252:
                if (tag.equals("layout/activity_signup_0")) {
                    return R.layout.activity_signup;
                }
                return 0;
            case -457193953:
                if (tag.equals("layout/activity_create_price_alert_0")) {
                    return R.layout.activity_create_price_alert;
                }
                return 0;
            case -455246898:
                if (tag.equals("layout/fragment_sepa_deposit_withdraw_0")) {
                    return R.layout.fragment_sepa_deposit_withdraw;
                }
                return 0;
            case -396437861:
                if (tag.equals("layout/layout_idology_form_0")) {
                    return R.layout.layout_idology_form;
                }
                return 0;
            case -391703503:
                if (tag.equals("layout/controller_fiat_confirmation_0")) {
                    return R.layout.controller_fiat_confirmation;
                }
                return 0;
            case -373799805:
                if (tag.equals("layout/layout_account_address_0")) {
                    return R.layout.layout_account_address;
                }
                return 0;
            case -353390459:
                if (tag.equals("layout/requirement_item_0")) {
                    return R.layout.requirement_item;
                }
                return 0;
            case -308700425:
                if (tag.equals("layout/layout_dashboard_tab_period_0")) {
                    return R.layout.layout_dashboard_tab_period;
                }
                return 0;
            case -302356366:
                if (tag.equals("layout/controller_add_bank_error_0")) {
                    return R.layout.controller_add_bank_error;
                }
                return 0;
            case -278089214:
                if (tag.equals("layout/controller_state_idology_address_form_0")) {
                    return R.layout.controller_state_idology_address_form;
                }
                return 0;
            case -270155608:
                if (tag.equals("layout/fragment_price_alerts_0")) {
                    return R.layout.fragment_price_alerts;
                }
                return 0;
            case -240772888:
                if (tag.equals("layout/layout_currency_tab_0")) {
                    return R.layout.layout_currency_tab;
                }
                return 0;
            case -237232145:
                if (tag.equals("layout/activity_login_0")) {
                    return R.layout.activity_login;
                }
                return 0;
            case -214499715:
                if (tag.equals("layout/list_item_idology_question_0")) {
                    return R.layout.list_item_idology_question;
                }
                return 0;
            case -164055863:
                if (tag.equals("layout/list_item_mfa_0")) {
                    return R.layout.list_item_mfa;
                }
                return 0;
            case -153461424:
                if (tag.equals("layout/fragment_account_settings_0")) {
                    return R.layout.fragment_account_settings;
                }
                return 0;
            case -152139442:
                if (tag.equals("layout/controller_state_idology_retry_form_0")) {
                    return R.layout.controller_state_idology_retry_form;
                }
                return 0;
            case -125174992:
                if (tag.equals("layout/controller_linked_accounts_picker_0")) {
                    return R.layout.controller_linked_accounts_picker;
                }
                return 0;
            case -92668079:
                if (tag.equals("layout/upfront_kyc_verify_document_photo_hint_item_0")) {
                    return R.layout.upfront_kyc_verify_document_photo_hint_item;
                }
                return 0;
            case -90569397:
                if (tag.equals("layout/controller_buysell_confirmation_0")) {
                    return R.layout.controller_buysell_confirmation;
                }
                return 0;
            case -23778829:
                if (tag.equals("layout/list_item_jumio_profile_0")) {
                    return R.layout.list_item_jumio_profile;
                }
                return 0;
            case -321722:
                if (tag.equals("layout/layout_idology_address_form_0")) {
                    return R.layout.layout_idology_address_form;
                }
                return 0;
            case 55507116:
                if (tag.equals("layout/layout_linked_account_item_0")) {
                    return R.layout.layout_linked_account_item;
                }
                return 0;
            case 77212362:
                if (tag.equals("layout/layout_quick_buy_list_0")) {
                    return R.layout.layout_quick_buy_list;
                }
                return 0;
            case 113558028:
                if (tag.equals("layout/spinner_dropdown_deposit_0")) {
                    return R.layout.spinner_dropdown_deposit;
                }
                return 0;
            case 126400830:
                if (tag.equals("layout/controller_upfront_kyc_take_document_photo_0")) {
                    return R.layout.controller_upfront_kyc_take_document_photo;
                }
                return 0;
            case 166093244:
                if (tag.equals("layout/list_item_confirmation_detail_0")) {
                    return R.layout.list_item_confirmation_detail;
                }
                return 0;
            case 178943869:
                if (tag.equals("layout/controller_privacy_rights_0")) {
                    return R.layout.controller_privacy_rights;
                }
                return 0;
            case 184568575:
                if (tag.equals("layout/controller_jumio_complete_0")) {
                    return R.layout.controller_jumio_complete;
                }
                return 0;
            case 198212420:
                if (tag.equals("layout/list_item_contact_suggestion_0")) {
                    return R.layout.list_item_contact_suggestion;
                }
                return 0;
            case 207576591:
                return tag.equals("layout/controller_identity_verification_0") ? R.layout.controller_identity_verification : 0;
            case 209031967:
                if (tag.equals("layout/fragment_plaid_account_login_0")) {
                    return R.layout.fragment_plaid_account_login;
                }
                return 0;
            case 253735145:
                if (tag.equals("layout/controller_request_data_0")) {
                    return R.layout.controller_request_data;
                }
                return 0;
            case 260761249:
                if (tag.equals("layout/fragment_text_dialog_0")) {
                    return R.layout.fragment_text_dialog;
                }
                return 0;
            case 269430097:
                if (tag.equals("layout/controller_dialog_confirm_send_0")) {
                    return R.layout.controller_dialog_confirm_send;
                }
                return 0;
            case 270928225:
                if (tag.equals("layout/bank_cdv_amounts_form_0")) {
                    return R.layout.bank_cdv_amounts_form;
                }
                return 0;
            case 313917191:
                if (tag.equals("layout/controller_state_idology_name_form_0")) {
                    return R.layout.controller_state_idology_name_form;
                }
                return 0;
            case 331454925:
                if (tag.equals("layout/payment_method_controller_0")) {
                    return R.layout.payment_method_controller;
                }
                return 0;
            case 332554540:
                if (tag.equals("layout/list_item_buysell_payment_method_header_0")) {
                    return R.layout.list_item_buysell_payment_method_header;
                }
                return 0;
            case 386828919:
                if (tag.equals("layout/dropdown_item_contact_suggestion_0")) {
                    return R.layout.dropdown_item_contact_suggestion;
                }
                return 0;
            case 392347569:
                if (tag.equals("layout/layout_account_list_0")) {
                    return R.layout.layout_account_list;
                }
                return 0;
            case 412820285:
                if (tag.equals("layout/deposit_withdraw_limit_0")) {
                    return R.layout.deposit_withdraw_limit;
                }
                return 0;
            case 498430139:
                if (tag.equals("layout/list_item_confirmation_total_0")) {
                    return R.layout.list_item_confirmation_total;
                }
                return 0;
            case 514970596:
                if (tag.equals("layout/list_item_create_price_alert_0")) {
                    return R.layout.list_item_create_price_alert;
                }
                return 0;
            case 531623489:
                if (tag.equals("layout/fragment_pin_settings_dialog_0")) {
                    return R.layout.fragment_pin_settings_dialog;
                }
                return 0;
            case 539123313:
                if (tag.equals("layout/payment_method_verification_controller_0")) {
                    return R.layout.payment_method_verification_controller;
                }
                return 0;
            case 562626425:
                if (tag.equals("layout-large-land/fragment_iav_login_0")) {
                    return R.layout.fragment_iav_login;
                }
                return 0;
            case 576928217:
                if (tag.equals("layout/fragment_plaid_account_login_content_0")) {
                    return R.layout.fragment_plaid_account_login_content;
                }
                return 0;
            case 638261513:
                if (tag.equals("layout/fragment_iav_login_content_0")) {
                    return R.layout.fragment_iav_login_content;
                }
                return 0;
            case 650980571:
                if (tag.equals("layout/add_plaid_account_controller_content_0")) {
                    return R.layout.add_plaid_account_controller_content;
                }
                return 0;
            case 679659923:
                if (tag.equals("layout/controller_take_document_photo_content_0")) {
                    return R.layout.controller_take_document_photo_content;
                }
                return 0;
            case 688592073:
                if (tag.equals("layout/controller_identity_verification_content_0")) {
                    return R.layout.controller_identity_verification_content;
                }
                return 0;
            case 724674785:
                if (tag.equals("layout/controller_sell_0")) {
                    return R.layout.controller_sell;
                }
                return 0;
            case 724726679:
                if (tag.equals("layout/controller_send_0")) {
                    return R.layout.controller_send;
                }
                return 0;
            case 727532918:
                if (tag.equals("layout/dialog_withdrawal_based_limits_error_0")) {
                    return R.layout.dialog_withdrawal_based_limits_error;
                }
                return 0;
            case 733003027:
                if (tag.equals("layout/controller_gdpr_marketing_email_prompt_0")) {
                    return R.layout.controller_gdpr_marketing_email_prompt;
                }
                return 0;
            case 741424664:
                if (tag.equals("layout/list_item_connected_account_0")) {
                    return R.layout.list_item_connected_account;
                }
                return 0;
            case 754906782:
                if (tag.equals("layout/dialog_launch_message_0")) {
                    return R.layout.dialog_launch_message;
                }
                return 0;
            case 773385239:
                if (tag.equals("layout/controller_jumio_document_scan_content_0")) {
                    return R.layout.controller_jumio_document_scan_content;
                }
                return 0;
            case 775068949:
                if (tag.equals("layout/controller_device_verify_0")) {
                    return R.layout.controller_device_verify;
                }
                return 0;
            case 800681726:
                if (tag.equals("layout/dialog_goto_settings_0")) {
                    return R.layout.dialog_goto_settings;
                }
                return 0;
            case 845510945:
                if (tag.equals("layout/bottom_item_modal_0")) {
                    return R.layout.bottom_item_modal;
                }
                return 0;
            case 890115522:
                if (tag.equals("layout/list_item_buysell_payment_method_0")) {
                    return R.layout.list_item_buysell_payment_method;
                }
                return 0;
            case 927898637:
                if (tag.equals("layout/card_cdv_amounts_form_0")) {
                    return R.layout.card_cdv_amounts_form;
                }
                return 0;
            case 952427802:
                if (tag.equals("layout/signin_phone_number_controller_0")) {
                    return R.layout.signin_phone_number_controller;
                }
                return 0;
            case 984412657:
                if (tag.equals("layout/list_item_available_payment_method_0")) {
                    return R.layout.list_item_available_payment_method;
                }
                return 0;
            case 1080820681:
                if (tag.equals("layout-large-land/fragment_plaid_account_login_0")) {
                    return R.layout.fragment_plaid_account_login;
                }
                return 0;
            case 1168518116:
                if (tag.equals("layout/list_item_settings_0")) {
                    return R.layout.list_item_settings;
                }
                return 0;
            case 1169247125:
                if (tag.equals("layout/layout_account_refresh_list_0")) {
                    return R.layout.layout_account_refresh_list;
                }
                return 0;
            case 1172026768:
                if (tag.equals("layout/controller_investment_tiers_settings_0")) {
                    return R.layout.controller_investment_tiers_settings;
                }
                return 0;
            case 1199760503:
                if (tag.equals("layout/fragment_dashboard_currency_0")) {
                    return R.layout.fragment_dashboard_currency;
                }
                return 0;
            case 1208828057:
                if (tag.equals("layout/fragment_add_billing_address_0")) {
                    return R.layout.fragment_add_billing_address;
                }
                return 0;
            case 1231760638:
                if (tag.equals("layout/card_form_controller_0")) {
                    return R.layout.card_form_controller;
                }
                return 0;
            case 1232859606:
                if (tag.equals("layout/list_item_quickstart_0")) {
                    return R.layout.list_item_quickstart;
                }
                return 0;
            case 1261967434:
                if (tag.equals("layout/layout_idology_date_picker_0")) {
                    return R.layout.layout_idology_date_picker;
                }
                return 0;
            case 1264289423:
                if (tag.equals("layout/dialog_add_phone_0")) {
                    return R.layout.dialog_add_phone;
                }
                return 0;
            case 1330484508:
                if (tag.equals("layout/controller_state_idology_questions_0")) {
                    return R.layout.controller_state_idology_questions;
                }
                return 0;
            case 1340802153:
                if (tag.equals("layout/layout_transactions_list_0")) {
                    return R.layout.layout_transactions_list;
                }
                return 0;
            case 1378869810:
                if (tag.equals("layout/activity_intro_0")) {
                    return R.layout.activity_intro;
                }
                return 0;
            case 1388572092:
                if (tag.equals("layout/controller_gdpr_privacy_policy_0")) {
                    return R.layout.controller_gdpr_privacy_policy;
                }
                return 0;
            case 1400815751:
                if (tag.equals("layout/controller_state_locked_0")) {
                    return R.layout.controller_state_locked;
                }
                return 0;
            case 1402391985:
                if (tag.equals("layout/controller_two_factor_0")) {
                    return R.layout.controller_two_factor;
                }
                return 0;
            case 1436758247:
                if (tag.equals("layout/layout_alerts_list_0")) {
                    return R.layout.layout_alerts_list;
                }
                return 0;
            case 1474448144:
                if (tag.equals("layout/fragment_pricechart_0")) {
                    return R.layout.fragment_pricechart;
                }
                return 0;
            case 1548214623:
                if (tag.equals("layout/controller_upfront_kyc_acceptable_documents_content_0")) {
                    return R.layout.controller_upfront_kyc_acceptable_documents_content;
                }
                return 0;
            case 1553706487:
                if (tag.equals("layout/tiers_item_0")) {
                    return R.layout.tiers_item;
                }
                return 0;
            case 1580312448:
                return tag.equals("layout-large-land/controller_acceptable_documents_0") ? R.layout.controller_acceptable_documents : 0;
            case 1607794591:
                if (tag.equals("layout/controller_accept_terms_0")) {
                    return R.layout.controller_accept_terms;
                }
                return 0;
            case 1614090749:
                if (tag.equals("layout/list_item_confirmation_fee_0")) {
                    return R.layout.list_item_confirmation_fee;
                }
                return 0;
            case 1630486595:
                if (tag.equals("layout/layout_idology_name_form_0")) {
                    return R.layout.layout_idology_name_form;
                }
                return 0;
            case 1640182695:
                if (tag.equals("layout/layout_connected_account_list_0")) {
                    return R.layout.layout_connected_account_list;
                }
                return 0;
            case 1640933003:
                if (tag.equals("layout/dialog_verify_phone_0")) {
                    return R.layout.dialog_verify_phone;
                }
                return 0;
            case 1644880469:
                if (tag.equals("layout/fragment_phone_number_0")) {
                    return R.layout.fragment_phone_number;
                }
                return 0;
            case 1667779647:
                if (tag.equals("layout/list_item_transaction_0")) {
                    return R.layout.list_item_transaction;
                }
                return 0;
            case 1694388118:
                if (tag.equals("layout/controller_idology_settings_questions_0")) {
                    return R.layout.controller_idology_settings_questions;
                }
                return 0;
            case 1709887828:
                if (tag.equals("layout/overlay_buysell_deposit_0")) {
                    return R.layout.overlay_buysell_deposit;
                }
                return 0;
            case 1789972586:
                return tag.equals("layout/controller_acceptable_documents_0") ? R.layout.controller_acceptable_documents : 0;
            case 1807767357:
                if (tag.equals("layout/layout_available_balance_app_bar_0")) {
                    return R.layout.layout_available_balance_app_bar;
                }
                return 0;
            case 1813021646:
                if (tag.equals("layout/list_item_account_0")) {
                    return R.layout.list_item_account;
                }
                return 0;
            case 1830031368:
                if (tag.equals("layout/controller_idology_settings_0")) {
                    return R.layout.controller_idology_settings;
                }
                return 0;
            case 1848597063:
                if (tag.equals("layout/list_item_price_alert_0")) {
                    return R.layout.list_item_price_alert;
                }
                return 0;
            case 1889137249:
                if (tag.equals("layout/controller_upfront_kyc_idv_processing_0")) {
                    return R.layout.controller_upfront_kyc_idv_processing;
                }
                return 0;
            case 1893715783:
                if (tag.equals("layout/controller_idology_failure_0")) {
                    return R.layout.controller_idology_failure;
                }
                return 0;
            case 1899735073:
                return tag.equals("layout/add_plaid_account_controller_0") ? R.layout.add_plaid_account_controller : 0;
            case 1908123005:
                if (tag.equals("layout/layout_transfer_send_0")) {
                    return R.layout.layout_transfer_send;
                }
                return 0;
            case 1921664715:
                if (tag.equals("layout/plaid_login_all_done_0")) {
                    return R.layout.plaid_login_all_done;
                }
                return 0;
            case 1924151279:
                return tag.equals("layout/controller_identity_document_scan_0") ? R.layout.controller_identity_document_scan : 0;
            case 1990954769:
                if (tag.equals("layout/list_item_billing_address_0")) {
                    return R.layout.list_item_billing_address;
                }
                return 0;
            case 2062496793:
                if (tag.equals("layout/controller_idology_state_failure_0")) {
                    return R.layout.controller_idology_state_failure;
                }
                return 0;
            case 2081548403:
                if (tag.equals("layout/bottom_item_0")) {
                    return R.layout.bottom_item;
                }
                return 0;
            case 2124651421:
                if (tag.equals("layout/list_item_transaction_footer_0")) {
                    return R.layout.list_item_transaction_footer;
                }
                return 0;
            case 2127523493:
                if (tag.equals("layout/fragment_deposit_0")) {
                    return R.layout.fragment_deposit;
                }
                return 0;
            case 2138698889:
                if (tag.equals("layout/layout_price_chart_0")) {
                    return R.layout.layout_price_chart;
                }
                return 0;
            default:
                return 0;
        }
    }
}
