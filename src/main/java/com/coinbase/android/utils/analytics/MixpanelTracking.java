package com.coinbase.android.utils.analytics;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.amplitude.api.Amplitude;
import com.coinbase.android.AnalyticsUtils;
import com.coinbase.android.BuildConfig;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.android.utils.CryptoUri;
import com.coinbase.v2.models.user.SplitTest;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MixpanelTracking {
    public static final String ADD_BANK_ACCOUNT_PASSED_THROUGH_TO_PLAID = "add_bank_account_passed_through_to_plaid";
    public static final String ADD_BANK_ACCOUNT_PLAID_TAPPED_START_OVER = "add_bank_account_plaid_tapped_start_over";
    public static final String ADD_BANK_ACCOUNT_PLAID_TAPPED_USE_CDV = "add_bank_account_plaid_tapped_use_cdv";
    public static final String ADD_BANK_ACCOUNT_PLAID_VIEWED_ERROR = "add_bank_account_plaid_viewed_error";
    public static final String ADD_BANK_ACCOUNT_TAPPED_CDV = "add_bank_account_tapped_cdv";
    public static final String ADD_BANK_ACCOUNT_TAPPED_PLAID = "add_bank_account_tapped_plaid";
    public static final String ADD_BANK_ACCOUNT_VIEWED = "add_bank_account_viewed";
    private static final String AMPLITUDE_TOKEN = "132e62b5953ce8d568137d5887b6b7ab";
    public static final String EVENT_3DS_BUY_COMPLETED = "Event - 3DS buy completed";
    public static final String EVENT_3DS_LOAD_COMPLETED = "three_ds_load_completed";
    public static final String EVENT_3DS_LOAD_FAILED = "three_ds_load_failed";
    public static final String EVENT_3DS_VERIFICATION_COMPLETED = "three_ds_verification_completed";
    public static final String EVENT_3DS_VERIFICATION_DISMISSED = "three_ds_verification_dismissed";
    public static final String EVENT_ACCEPT_TOS = "Event - Accept Terms of Service";
    public static final String EVENT_ACCOUNTS_TAPPED_ACCOUNT = "accounts_tapped_account";
    public static final String EVENT_ACCOUNTS_VIEWED = "accounts_viewed";
    public static final String EVENT_ACCOUNT_TAPPED_BUY_BUTTON = "account_tapped_buy_button";
    public static final String EVENT_ACCOUNT_TAPPED_RECEIVE_BUTTON = "account_tapped_receive";
    public static final String EVENT_ACCOUNT_TAPPED_SELL_BUTTON = "account_tapped_sell_button";
    public static final String EVENT_ACCOUNT_TAPPED_SEND_BUTTON = "account_tapped_send";
    public static final String EVENT_ADD_BANK_ACCOUNT_FROM_SETTINGS = "Event - Add bank account from settings";
    public static final String EVENT_ADD_BANK_ACCOUNT_FROM_TRANSACTIONS = "Event - Add bank account from transactions";
    public static final String EVENT_ADD_DEBIT_CARD_FROM_SETTINGS = "Event - Add debit card from settings";
    public static final String EVENT_ADD_LINKED_ACCOUNTS_TAPPED_BANK = "add_linked_accounts_tapped_bank";
    public static final String EVENT_ADD_LINKED_ACCOUNTS_TAPPED_CARD = "add_linked_accounts_tapped_card";
    public static final String EVENT_ADD_LINKED_ACCOUNTS_VIEWED = "add_linked_accounts_viewed";
    public static final String EVENT_ADD_NEW_ACCOUNT_INITIATED = "Event - Add new account initiated";
    public static final String EVENT_ADD_PAYMENT_METHOD_FROM_TRANSACTIONS = "Event - Add payment method from transactions";
    public static final String EVENT_AMOUNT_ENTERED = "Event - Amount entered";
    public static final String EVENT_APP_INVITE_COMPLETED = "Event - App invite completed";
    public static final String EVENT_APP_INVITE_FAILED = "Event - App invite failed";
    public static final String EVENT_APP_INVITE_INITIATED = "Event - App invite initiated";
    public static final String EVENT_APP_OPEN = "Event - app open";
    public static final String EVENT_APP_RATE_TAPPED = "app_rate_tapped";
    public static final String EVENT_APP_RATE_VIEWED = "app_rate_viewed";
    public static final String EVENT_AVAILABLE_BALANCE_TAPPED_CLOSE = "available_balance_tapped_close";
    public static final String EVENT_AVAILABLE_BALANCE_VIEWED = "available_balance_viewed";
    public static final String EVENT_BALANCE_WIDGET_ENABLED = "balance_widget_enabled";
    public static final String EVENT_BANK_ACCOUNT_FIRST_PURCHASE_NO_THANKS = "Event - Bank account first purchase no thanks";
    public static final String EVENT_BANK_ACCOUNT_FIRST_PURCHASE_SCREEN_SHOWN = "Event - Bank account first purchase screen shown";
    public static final String EVENT_BANK_ACCOUNT_IS_NOT_ON_THE_LIST = "Event - Bank account is not on the list";
    public static final String EVENT_BANK_ACCOUNT_SELECTED = "Event - Bank account selected";
    public static final String EVENT_BUY_BUTTON_CLICK = "Event - Buy";
    public static final String EVENT_BUY_CONFIRMATION_CLICK = "Event - Confirm buy";
    public static final String EVENT_BUY_SIDEBAR_CLICK = "Event - Navigation drawer buy";
    public static final String EVENT_BUY_TAPPED_CONFIRM = "buy_tapped_confirm_button";
    public static final String EVENT_BUY_TAPPED_PAYMENT_METHOD = "buy_tapped_select_payment_method";
    public static final String EVENT_BUY_TAPPED_PREVIEW = "buy_tapped_preview";
    public static final String EVENT_BUY_TAPPED_SUGGESTED_AMOUNT = "buy_tapped_suggested_amount";
    public static final String EVENT_BUY_TAPPED_SWITCH = "buy_tapped_switch";
    public static final String EVENT_BUY_VIEWED = "buy_viewed";
    public static final String EVENT_BUY_VIEWED_ERROR = "buy_viewed_error";
    public static final String EVENT_BUY_VIEWED_UPDATED_AMOUNT = "buy_viewed_updated_amount";
    public static final String EVENT_CHART_TAPPED_BUY_BUTTON = "chart_tapped_buy_button";
    public static final String EVENT_CHART_TAPPED_SELL_BUTTON = "chart_tapped_sell_button";
    public static final String EVENT_CHART_VIEWED = "chart_viewed";
    public static final String EVENT_CREATE_ACCOUNT_BUTTON_CLICK = "Event - Sign up page button click";
    public static final String EVENT_DASHBOARD_TAPPED_CHART = "dashboard_tapped_chart";
    public static final String EVENT_DASHBOARD_TAPPED_FIRST_BUY = "dashboard_tapped_first_buy";
    public static final String EVENT_DASHBOARD_TAPPED_PORTFOLIO = "dashboard_tapped_portfolio";
    public static final String EVENT_DASHBOARD_VIEWED = "dashboard_viewed";
    public static final String EVENT_DEPOSIT_ICON_CLICK = "Event - deposit icon";
    public static final String EVENT_DEPOSIT_TAPPED_CONFIRM = "deposit_tapped_confirm";
    public static final String EVENT_DEPOSIT_TAPPED_PREVIEW = "deposit_tapped_preview";
    public static final String EVENT_DEPOSIT_VIEWED_ERROR = "deposit_viewed_error";
    public static final String EVENT_DEVICE_VERIFIED = "Event - Device verified";
    public static final String EVENT_EDIT_PHONE_NUMBER = "Event - Edit phone number";
    public static final String EVENT_EMAIL_VERIFICATION = "Event - Email verified";
    public static final String EVENT_IDENTITY_TAPPED_MANUAL_ENTRY = "verify_identity_tapped_manual_entry";
    public static final String EVENT_INSTALL_REFERRER = "Event - Install Source";
    public static final String EVENT_INVESTMENT_TIERS_CURRENT_TIER_LEVEL_PROPERTY = "tier_level";
    public static final String EVENT_INVESTMENT_TIERS_TAPPED_COMPLETE_ACCOUNT = "investment_tiers_tapped_complete_account";
    public static final String EVENT_INVESTMENT_TIERS_TAPPED_FROM_SETTINGS = "investment_tiers_tapped_from_settings";
    public static final String EVENT_INVESTMENT_TIERS_TAPPED_INCREASE_LIMITS = "investment_tiers_tapped_increase_limits";
    public static final String EVENT_INVESTMENT_TIERS_VIEWED = "investment_tiers_viewed";
    public static final String EVENT_INVESTMENT_TIERS_VIEWED_SUCCESS = "investment_tiers_viewed_success ";
    public static final String EVENT_JUMIO_COUNTRY_SELECT = "Event - Jumio country select";
    public static final String EVENT_JUMIO_DOC_TYPE_SELECT = "Event - Jumio document type select";
    public static final String EVENT_JUMIO_START_FROM_PAYMENT_METHOD = "Event - Jumio start from payment method";
    public static final String EVENT_JUMIO_START_FROM_QUICKSTART = "Event - Jumio start from quick start";
    public static final String EVENT_JUMIO_START_FROM_SETTINGS = "Event - Jumio start from settings";
    public static final String EVENT_JUMIO_START_FROM_WEB = "Event - Jumio start from web";
    public static final String EVENT_JUMIO_START_VERIFICATION = "Event - Jumio start verification";
    public static final String EVENT_JUMIO_SUBMIT = "Event - Jumio submit";
    public static final String EVENT_JUMIO_SUBMIT_FAILURE = "Event - Jumio submit failure";
    public static final String EVENT_JUMIO_SUBMIT_SUCCESS = "Event - Jumio submit success";
    public static final String EVENT_JUMIO_TAKE_BACK_IMAGE = "Event - Jumio take back image";
    public static final String EVENT_JUMIO_TAKE_FACE_MATCH = "Event - Jumio take face match";
    public static final String EVENT_JUMIO_TAKE_FRONT_IMAGE = "Event - Jumio take front image";
    public static final String EVENT_LINKED_ACCOUNTS_NO_ACCOUNT_TAPPED_CLOSE = "linked_accounts_no_account_tapped_close";
    public static final String EVENT_LINKED_ACCOUNTS_NO_ACCOUNT_TAPPED_GO_TO_SETTINGS = "linked_accounts_no_account_tapped_go_to_settings";
    public static final String EVENT_LINKED_ACCOUNTS_NO_ACCOUNT_VIEWED = "linked_accounts_no_account_viewed";
    public static final String EVENT_LINKED_ACCOUNTS_VIEWED = "linked_accounts_viewed";
    public static final String EVENT_LINKED_ACCOUNT_NUM_PROPERTY = "num_accounts";
    public static final String EVENT_LINKED_ACCOUNT_REMOVAL_CONFIRMATION_TAPPED_CANCEL = "linked_account_removal_confirmation_tapped_cancel";
    public static final String EVENT_LINKED_ACCOUNT_REMOVAL_CONFIRMATION_TAPPED_REMOVE = "linked_account_removal_confirmation_tapped_remove";
    public static final String EVENT_LINKED_ACCOUNT_TAPPED_REMOVE = "linked_account_tapped_remove";
    public static final String EVENT_LINKED_ACCOUNT_TAPPED_VERIFY = "linked_account_tapped_verify";
    public static final String EVENT_LINKED_ACCOUNT_TYPE_PROPERTY = "type";
    public static final String EVENT_LOG_IN_BUTTON_CLICK = "Event - Sign-in page button click";
    public static final String EVENT_MANAGE_ACCOUNTS = "Event - Manage accounts";
    public static final String EVENT_MANAGE_EMAIL_SETTINGS = "Event - Manage email settings";
    public static final String EVENT_MANAGE_PHONE_NUMBERS_SETTINGS = "Event - Manage phone numbers settings";
    public static final String EVENT_MANAGE_PRIVACY_SETTINGS = "Event - Manage privacy options";
    public static final String EVENT_M_CDV_INITIATED = "Event - M cdv initiated";
    public static final String EVENT_M_CDV_SCREEN = "Event - M cdv screen";
    public static final String EVENT_M_CDV_SHOWN_ON_TRANSACTION_LIST = "Event - M cdv shown on transaction list";
    public static final String EVENT_M_ENTER_CREDENTIALS_SCREEN = "Event - M enter credentials screen";
    public static final String EVENT_M_ENTER_NAME_SCREEN = "Event - M enter name screen";
    public static final String EVENT_M_IAV_MFA_REQUESTED = "Event - M iav mfa requested";
    public static final String EVENT_M_SUCCESSFUL = "Event - M successful";
    public static final String EVENT_NAVIGATION_BAR_TAPPED_BUY_BUTTON = "navigation_bar_tapped_buy_button";
    public static final String EVENT_NEW_ACCOUNT_CREATED = "Event - New account created";
    public static final String EVENT_ONBOARDING_STEP_SKIPPED = "onboarding_step_skipped_missing";
    public static final String EVENT_PAYMENT_CARD_SETUP = "Event - Payment card setup";
    public static final String EVENT_PAYMENT_METHODS_FROM_NAVIGATION_BAR = "Event - Payment methods from navigation bar";
    public static final String EVENT_PAYMENT_METHODS_FROM_SETTINGS = "Event - Payment methods from settings";
    public static final String EVENT_PERMISSION_CHECK_FAILED = "Event - Permission Check Failed";
    public static final String EVENT_PHONE_NUMBER_CODE_ENTERED = "Event - Phone number code entered";
    public static final String EVENT_PHONE_NUMBER_VERIFIED_AUTOMATICALLY = "Event - Phone number verified automatically";
    public static final String EVENT_PHONE_NUMBER_VERIFIED_MANUALLY = "Event - Phone number verified manually";
    public static final String EVENT_PHONE_VERIFICATION_BUTTON_CLICKED = "Event - Phone verification button clicked";
    public static final String EVENT_PLAID_LINK = "plaid_link";
    public static final String EVENT_PRICE_ALERT_CREATED = "Event - Price alert created";
    public static final String EVENT_PRICE_ALERT_DELETED = "Event - Price alert deleted";
    public static final String EVENT_PRICE_ALERT_VIEWED = "price_alert_viewed";
    public static final String EVENT_PRICE_WIDGET_ENABLED = "price_widget_enabled";
    public static final String EVENT_PROCESS_PAYMENT_CARD = "Event - Process payment card";
    public static final String EVENT_P_CREDENTIALS_SCREEN = "Event - P credentials screen";
    public static final String EVENT_P_MFA_REQUESTED = "Event - P mfa requested";
    public static final String EVENT_P_PIN_SCREEN = "Event - P pin screen";
    public static final String EVENT_P_SUCCESSFUL = "Event - P successful";
    public static final String EVENT_RECEIVE_ACCOUNT_SETUP_REQUIRED_TAPPED_CLOSE = "receive_account_setup_required_tapped_close";
    public static final String EVENT_RECEIVE_ACCOUNT_SETUP_REQUIRED_TAPPED_CONFIRMED = "receive_account_setup_required_tapped_verify_your_account";
    public static final String EVENT_RECEIVE_ACCOUNT_SETUP_REQUIRED_VIEWED = "receive_account_setup_required_viewed";
    public static final String EVENT_RECEIVE_TAPPED_COPY_ADDRESS = "receive_tapped_copy_address";
    public static final String EVENT_RECEIVE_VIEWED = "receive_viewed";
    public static final String EVENT_REJECT_ADDING_PHONE = "Event - Reject Adding Phone Number";
    public static final String EVENT_REJECT_IDENTITY_VERIFICATION = "Event - Reject Adding Identity Verification";
    public static final String EVENT_REJECT_TOS = "Event - Reject Terms of Service";
    public static final String EVENT_REQUEST_ICON_CLICK = "Event - request icon";
    public static final String EVENT_REQUEST_REQUESTED = "Event - Request requested";
    public static final String EVENT_SCAN_QR = "Event - Scan QR";
    public static final String EVENT_SELECT_STATE_TAPPED_CONTINUE = "select_state_tapped_continue";
    public static final String EVENT_SELECT_STATE_TAPPED_STATE = "select_state_tapped_state";
    public static final String EVENT_SELECT_STATE_TAPPED_STATE_PROPERTY = "state";
    public static final String EVENT_SELECT_STATE_VIEWED = "select_state_viewed";
    public static final String EVENT_SELL_BUTTON_CLICK = "Event - Sell";
    public static final String EVENT_SELL_CONFIRMATION_CLICK = "Event - Confirm sell";
    public static final String EVENT_SELL_EXCEEDED_TAPPED_DISMISS = "sell_exceeded_limit_tapped_dismiss";
    public static final String EVENT_SELL_EXCEEDED_TAPPED_LEARN_MORE = "sell_exceeded_limit_tapped_learn_more";
    public static final String EVENT_SELL_SIDEBAR_CLICK = "Event - Navigation drawer sell";
    public static final String EVENT_SELL_TAPPED_AMOUNT = "sell_tapped_amount";
    public static final String EVENT_SELL_TAPPED_AVAILABLE_BALANCE_DETAIL = "sell_tapped_available_balance_detail";
    public static final String EVENT_SELL_TAPPED_CONFIRM = "sell_tapped_confirm_button";
    public static final String EVENT_SELL_TAPPED_PAYMENT_METHOD = "sell_tapped_select_payment_method";
    public static final String EVENT_SELL_TAPPED_PREVIEW = "sell_tapped_preview";
    public static final String EVENT_SELL_TAPPED_SELL_HALF = "sell_tapped_sell_half";
    public static final String EVENT_SELL_TAPPED_SELL_MAX = "sell_tapped_sell_max";
    public static final String EVENT_SELL_TAPPED_SELL_QUARTER = "sell_tapped_sell_quarter";
    public static final String EVENT_SELL_TAPPED_SWITCH = "sell_tapped_switch";
    public static final String EVENT_SELL_VIEWED = "sell_viewed";
    public static final String EVENT_SELL_VIEWED_ERROR = "sell_viewed_error";
    public static final String EVENT_SELL_VIEWED_LIMIT_EXCEEDED_ERROR = "sell_exceeded_limit_viewed";
    public static final String EVENT_SEND_ACCOUNT_SETUP_REQUIRED_TAPPED_CLOSE = "send_account_setup_required_tapped_close";
    public static final String EVENT_SEND_ACCOUNT_SETUP_REQUIRED_TAPPED_CONFIRMED = "send_account_setup_required_tapped_verify_your_account";
    public static final String EVENT_SEND_ACCOUNT_SETUP_REQUIRED_VIEWED = "send_account_setup_required_viewed";
    public static final String EVENT_SEND_EXCEEDED_TAPPED_DISMISS = "send_exceeded_limit_tapped_dismiss";
    public static final String EVENT_SEND_EXCEEDED_TAPPED_LEARN_MORE = "send_exceeded_limit_tapped_learn_more";
    public static final String EVENT_SEND_ICON_CLICK = "Event - send icon";
    public static final String EVENT_SEND_REQUESTED = "Event - Sent requested";
    public static final String EVENT_SEND_REQUESTED_TYPE_CRYPTO = "Property: Type: crypto";
    public static final String EVENT_SEND_REQUESTED_TYPE_FIAT = "Property: Type: fiat";
    public static final String EVENT_SEND_REQUEST_INITIATED = "Event - Send request initiated";
    public static final String EVENT_SEND_SUCCESS = "send_success";
    public static final String EVENT_SEND_TAPPED_AVAILABLE_BALANCE_DETAIL = "send_tapped_available_balance_detail";
    public static final String EVENT_SEND_TAPPED_CONFIRM = "send_tapped_confirm";
    public static final String EVENT_SEND_TAPPED_CONTACT = "send_tapped_contact";
    public static final String EVENT_SEND_TAPPED_CONTINUE = "send_tapped_continue";
    public static final String EVENT_SEND_TAPPED_NOTE = "send_tapped_note";
    public static final String EVENT_SEND_TAPPED_QR_CODE = "send_tapped_qr_code";
    public static final String EVENT_SEND_TAPPED_SEND_HALF = "send_tapped_use_half";
    public static final String EVENT_SEND_TAPPED_SEND_MAX = "send_tapped_use_max";
    public static final String EVENT_SEND_TAPPED_SEND_QUARTER = "send_tapped_use_quarter";
    public static final String EVENT_SEND_TAPPED_SWITCHED_CURRENCY = "send_tapped_switch_currency";
    public static final String EVENT_SEND_TAPPED_TO = "send_tapped_to";
    public static final String EVENT_SEND_VIEWED = "send_viewed";
    public static final String EVENT_SEND_VIEWED_AMOUNT_ERROR = "send_viewed_amount_error";
    public static final String EVENT_SEND_VIEWED_CONTACTS = "send_viewed_contacts";
    public static final String EVENT_SEND_VIEWED_LIMIT_EXCEEDED_ERROR = "send_exceeded_limit_viewed";
    public static final String EVENT_SEND_VIEWED_RECENT = "send_viewed_recent";
    public static final String EVENT_SEND_WITHOUT_CONFIRM = "Event - Send without confirm";
    public static final String EVENT_SETTINGS_TAPPED_ADD_LINKED_ACCOUNT = "settings_tapped_add_linked_account";
    public static final String EVENT_SETTINGS_TAPPED_LINKED_ACCOUNT = "settings_tapped_linked_account";
    public static final String EVENT_SETTINGS_VIEWED = "settings_viewed";
    public static final String EVENT_SETTINGS_VIEWED_LINKED_ACCOUNTS = "settings_viewed_linked_accounts";
    public static final String EVENT_SHARE = "Event - Share Coinbase";
    public static final String EVENT_SIGN_IN_BUTTON_CLICK = "Event - Sign-in button click";
    public static final String EVENT_SIGN_IN_PHONE_NUMBER_TWO_FACTOR_RESENT = "Event - Sign In Two Factor resent";
    public static final String EVENT_SIGN_IN_PHONE_NUMBER_VERIFIED_MANUALLY = "Event - Sign In Phone number verified manually";
    public static final String EVENT_SIGN_OUT = "Event - Sign out";
    public static final String EVENT_SIGN_UP_BUTTON_CLICK = "Event - Sign up button click";
    public static final String EVENT_SPLIT_TEST_EXPOSED = "split_test_exposed";
    public static final String EVENT_SUCCESSFUL_TRANSFER = "Event - Successful transfer";
    public static final String EVENT_TRANSACTION_PAGE_SHOWN = "Event - Transactions dashboard shown";
    public static final String EVENT_TRANSFER_BITCOIN = "Event - Transfer Bitcoin";
    public static final String EVENT_TWO_STEP_VERIFICATION = "Event - Two Step confirm click";
    public static final String EVENT_VERIFY_DEVICE_TAPPED_EXIT_LOGIN_FLOW = "verify_device_tapped_exit_login_flow";
    public static final String EVENT_VERIFY_EMAIL_TAPPED_CANCEL = "verify_email_tapped_cancel";
    public static final String EVENT_VERIFY_EMAIL_TAPPED_OPEN_EMAIL_APP = "verify_email_tapped_open_email_app";
    public static final String EVENT_VERIFY_EMAIL_VIEWED = "verify_email_viewed";
    public static final String EVENT_VERIFY_IDENTITY_ADDRESS_VIEWED = "verify_identity_address_viewed";
    public static final String EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY = "context";
    public static final String EVENT_VERIFY_IDENTITY_ERROR_MESSAGE_PROPERTY = "error_message";
    public static final String EVENT_VERIFY_IDENTITY_ERROR_PROPERTY = "error";
    public static final String EVENT_VERIFY_IDENTITY_ERROR_TYPE_PROPERTY = "error_type";
    public static final String EVENT_VERIFY_IDENTITY_NAME_VIEWED = "verify_identity_name_viewed";
    public static final String EVENT_VERIFY_IDENTITY_QUESTIONS_TAPPED_SUBMIT = "verify_identity_questions_tapped_submit";
    public static final String EVENT_VERIFY_IDENTITY_QUESTIONS_VIEWED = "verify_identity_questions_viewed";
    public static final String EVENT_VERIFY_IDENTITY_QUESTIONS_VIEWED_ERROR = "verify_identity_questions_viewed_error";
    public static final String EVENT_VERIFY_IDENTITY_QUESTIONS_VIEWED_SUCCESS = "verify_identity_questions_viewed_success";
    public static final String EVENT_VERIFY_IDENTITY_QUESTIONS_VIEWED_TIMEOUT_ERROR = "verify_identity_questions_viewed_timeout_error";
    public static final String EVENT_VERIFY_IDENTITY_SOURCE_OF_FUNDS_VIEWED = "verify_identity_source_of_funds_viewed";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_ADDRESS_LINE1 = "verify_identity_tapped_street_address_line_1";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_ADDRESS_LINE2 = "verify_identity_tapped_street_address_line_2";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_ADDRESS_SEARCH_RESULT = "verify_identity_tapped_address_search_result";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_CITY_TOWN = "verify_identity_tapped_city_town";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_DOB = "verify_identity_tapped_dob";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_EMPLOYER = "verify_identity_tapped_employer";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_FIRST_NAME = "verify_identity_tapped_first_name";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_JOB_TITLE = "verify_identity_tapped_job_title";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_LAST_NAME = "verify_identity_tapped_last_name";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_RESUBMIT = "verify_identity_tapped_resubmit";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_SOURCE_OF_FUNDS = "verify_identity_tapped_source_of_funds";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_SSN = "verify_identity_tapped_ssn";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_STATE = "verify_identity_tapped_state";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_SUBMIT = "verify_identify_tapped_submit";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_SUBMIT_ADDRESS = "verify_identity_tapped_submit_address";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_SUBMIT_PERSONAL_DETAIL = "verify_identity_tapped_submit_personal_detail";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_USE_FOR_COINBASE = "verify_identity_tapped_use_for_coinbase";
    public static final String EVENT_VERIFY_IDENTITY_TAPPED_ZIP = "verify_identity_tapped_zip";
    public static final String EVENT_VERIFY_IDENTITY_VIEWED = "verify_identity_viewed";
    public static final String EVENT_VERIFY_IDENTITY_VIEWED_ERROR = "verify_identity_viewed_error";
    public static final String EVENT_VERIFY_IDENTITY_VIEWED_FAILURE = "verify_identity_viewed_failure";
    public static final String EVENT_VERIFY_IDENTITY_VIEWED_SUCCESS = "verify_identity_viewed_success";
    public static final String EVENT_VERIFY_PAYMENT_CARD = "Event - Verify payment card";
    public static final String EVENT_VERIFY_PHONE_FROM_SETTINGS = "Event - Verify phone from settings";
    public static final String EVENT_VERIFY_PHONE_FROM_TRANSACTIONS = "Event - Verify phone from transactions";
    public static final String EVENT_VERIFY_PHONE_TAPPED_BACK = "verify_phone_tapped_back";
    public static final String EVENT_VERIFY_PHONE_TAPPED_CHOOSE_COUNTRY = "verify_phone_tapped_choose_country";
    public static final String EVENT_VERIFY_PHONE_TAPPED_CONFIRM = "verify_phone_tapped_confirm";
    public static final String EVENT_VERIFY_PHONE_TAPPED_CONFIRM_FAILURE = "verify_phone_tapped_confirm_failure";
    public static final String EVENT_VERIFY_PHONE_TAPPED_CONFIRM_SUCCESS = "verify_phone_tapped_confirm_success";
    public static final String EVENT_VERIFY_PHONE_TAPPED_CONTINUE = "verify_phone_tapped_continue";
    public static final String EVENT_VERIFY_PHONE_TAPPED_CONTINUE_FAILURE = "verify_phone_tapped_continue_failure";
    public static final String EVENT_VERIFY_PHONE_TAPPED_CONTINUE_SUCCESS = "verify_phone_tapped_continue_success";
    public static final String EVENT_VERIFY_PHONE_TAPPED_COUNTRY = "verify_phone_tapped_country";
    public static final String EVENT_VERIFY_PHONE_TAPPED_COUNTRY_PROPERTY = "country";
    public static final String EVENT_VERIFY_PHONE_TAPPED_PHONE_NUMBER = "verify_phone_tapped_phone_number";
    public static final String EVENT_VERIFY_PHONE_TAPPED_RESEND_CODE = "verify_phone_tapped_resend_code";
    public static final String EVENT_VERIFY_PHONE_TAPPED_RESEND_CODE_FAILURE = "verify_phone_tapped_resend_code_failure";
    public static final String EVENT_VERIFY_PHONE_TAPPED_RESEND_CODE_SUCCESS = "verify_phone_tapped_resend_code_success";
    public static final String EVENT_VERIFY_PHONE_VIEWED = "verify_phone_viewed";
    public static final String EVENT_WBL_EXISTING_USER_ACCOUNT_SETUP_TAPPED_COMPLETE_YOUR_PROFILE = "account_setup_tapped_complete_your_profile";
    public static final String EVENT_WBL_EXISTING_USER_ACCOUNT_SETUP_TAPPED_LEARN_MORE = "account_setup_tapped_learn_more";
    public static final String EVENT_WBL_EXISTING_USER_ACCOUNT_SETUP_VIEWED = "account_setup_viewed";
    public static final String EVENT_WITHDRAW_EXCEEDED_TAPPED_DISMISS = "withdrawal_exceeded_limit_tapped_dismiss";
    public static final String EVENT_WITHDRAW_EXCEEDED_TAPPED_LEARN_MORE = "withdrawal_exceeded_limit_tapped_learn_more";
    public static final String EVENT_WITHDRAW_ICON_CLICK = "Event - withdraw icon";
    public static final String EVENT_WITHDRAW_TAPPED_AVAILABLE_BALANCE_DETAIL = "withdraw_tapped_available_balance_detail";
    public static final String EVENT_WITHDRAW_TAPPED_CONFIRM = "withdraw_tapped_confirm";
    public static final String EVENT_WITHDRAW_TAPPED_PREVIEW = "withdraw_tapped_preview";
    public static final String EVENT_WITHDRAW_VIEWED_ERROR = "withdraw_viewed_error";
    public static final String EVENT_WITHDRAW_VIEWED_LIMIT_EXCEEDED_ERROR = "withdrawal_exceeded_limit_viewed";
    private static final String MIXPANEL_LOGTAG = "Coinbase tracking";
    private static final String MIXPANEL_TOKEN = "1b0a4bbfd833d99794cc4ed3de93f7fb";
    private static final String PREVIOUSLY_TRACKED_EVENTS = "previously_tracked_events";
    public static final String PROPERTY_APP_RATE_BUTTON_TAP = "app_rate_button_tap";
    public static final String PROPERTY_APP_RATE_CALLING_VIEW = "app_rate_calling_view";
    public static final String PROPERTY_APP_VERSION_CODE = "app_version_code";
    public static final String PROPERTY_BANK_NAME = "Property: Bank Name: ";
    public static final String PROPERTY_BUYSELL_ERROR = "error";
    public static final String PROPERTY_BUYSELL_PREVIOUS_SCREEN = "previous_screen";
    public static final String PROPERTY_BUYSELL_TYPE = "type";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_INSTALL_SOURCE = "Property - Source";
    public static final String PROPERTY_JUMIO_DOC_TYPE = "id_type";
    public static final String PROPERTY_MFA_TYPE = "Property: MFA type: ";
    public static final String PROPERTY_NUM_CONTACTS = "num_contacts";
    public static final String PROPERTY_NUM_RECENT = "num_recent";
    public static final String PROPERTY_PAYMENT_METHOD_TYPE = "Property - Payment Method Type";
    public static final String PROPERTY_REASON = "reason";
    public static final String PROPERTY_SEND_ERROR_TYPE = "error_type";
    public static final String PROPERTY_SPLIT_TEST_EXPOSED_GROUP = "group";
    public static final String PROPERTY_SPLIT_TEST_EXPOSED_TEST = "test";
    public static final String PROPERTY_VALUE_BUY_SELL_SUCCESS_VIEW = "buy_sell_success_view";
    public static final String PROPERTY_VALUE_SEND_ERROR_TYPE_CLIENT = "client";
    public static final String PROPERTY_VALUE_SEND_ERROR_TYPE_SERVER = "server";
    public static final String PROPERTY_VALUE_TRANSFER_MADE_VIEW = "transfer_made_view";
    public static final String PROPERTY_WITHDRAWDEPOSIT_ERROR = "error";
    public static final String SETTINGS_TAPPED_COMPLETE_ACCOUNT = "settings_tapped_complete_account";
    public static final String SETTINGS_TAPPED_INCREASE_LIMIT = "settings_tapped_increase_limit";
    public static final String SETTINGS_TAPPED_PERSONAL_INFORMATION = "settings_tapped_personal_information";
    private static final String SPLIT_TEST_GROUPS_PROPERTY = "split_test_groups";
    public static final String VALUE_BUYSELL_TYPE_CRYPTO = "crypto";
    public static final String VALUE_BUYSELL_TYPE_NATIVE = "native";
    public static final String VERIFY_BANK_ACCOUNT_TAPPED_AMOUNT_1 = "verify_bank_account_tapped_amount_1";
    public static final String VERIFY_BANK_ACCOUNT_TAPPED_AMOUNT_2 = "verify_bank_account_tapped_amount_2";
    public static final String VERIFY_BANK_ACCOUNT_TAPPED_ISSUES = "verify_bank_account_tapped_issues";
    public static final String VERIFY_BANK_ACCOUNT_TAPPED_SUBMIT = "verify_bank_account_tapped_submit";
    public static final String VERIFY_BANK_ACCOUNT_VIEWED = "verify_bank_account_viewed";
    public static final String VERIFY_CARD_TAPPED_AMOUNT_1 = "verify_card_tapped_amount_1";
    public static final String VERIFY_CARD_TAPPED_AMOUNT_2 = "verify_card_tapped_amount_2";
    public static final String VERIFY_CARD_TAPPED_ISSUES = "verify_card_tapped_issues";
    public static final String VERIFY_CARD_TAPPED_SUBMIT = "verify_card_tapped_submit";
    public static final String VERIFY_CARD_VIEWED = "verify_card_viewed";
    private static MixpanelAPI mixpanel;
    private static volatile MixpanelTracking sInstance = null;
    private AnalyticsUtils mAnalyticsUtils;
    private final Map<String, Long> mExposureMap = new HashMap();
    private final Logger mLogger = LoggerFactory.getLogger(MixpanelTracking.class);
    private JSONArray mTrackedSplitTests = new JSONArray();
    private SharedPreferences sharedPrefs;

    private MixpanelTracking() {
    }

    public static MixpanelTracking getInstance() {
        if (sInstance == null) {
            synchronized (MixpanelTracking.class) {
                if (sInstance == null) {
                    sInstance = new MixpanelTracking();
                }
            }
        }
        return sInstance;
    }

    public static void logTransactionEvent(QuickstartItem item) {
        switch (item) {
            case ADD_PAYMENT_METHOD:
                getInstance().trackEvent(EVENT_ADD_PAYMENT_METHOD_FROM_TRANSACTIONS, new String[0]);
                return;
            case VERIFY_PHONE_NUMBER:
                getInstance().trackEvent(EVENT_VERIFY_PHONE_FROM_TRANSACTIONS, new String[0]);
                return;
            case VERIFY_IDENTITY:
                getInstance().trackEvent(EVENT_JUMIO_START_FROM_QUICKSTART, new String[0]);
                return;
            default:
                return;
        }
    }

    public void initializeMixpanel(Application app) {
        mixpanel = MixpanelAPI.getInstance(app, MIXPANEL_TOKEN);
        this.sharedPrefs = PreferenceManager.getDefaultSharedPreferences(app);
        this.mAnalyticsUtils = new AnalyticsUtils(app, this.sharedPrefs);
        Amplitude.getInstance().initialize(app, AMPLITUDE_TOKEN).enableForegroundTracking(app);
    }

    public void setId(String id) {
        mixpanel.identify(id);
        Amplitude.getInstance().setUserId(id);
    }

    public void initializeTrackedTests(Map<String, String> trackedSplitTests) {
        synchronized (this) {
            this.mTrackedSplitTests = new JSONArray();
            for (String key : trackedSplitTests.keySet()) {
                this.mTrackedSplitTests.put(key + " - " + ((String) trackedSplitTests.get(key)));
            }
        }
    }

    public void trackEvent(Event event) {
        trackEvent(event, null);
    }

    public void trackEvent(Event event, Map<Property, ?> properties) {
        Map stringifiedProperties = new HashMap();
        if (properties != null) {
            for (Entry<Property, ?> propertyEntry : properties.entrySet()) {
                stringifiedProperties.put(getPropertyName((Property) propertyEntry.getKey()), propertyEntry.getValue());
            }
        }
        trackEvent(getEventName(event), stringifiedProperties);
    }

    @Deprecated
    public void trackEvent(String eventName, Map<String, ?> properties) {
        JSONObject prop = new JSONObject();
        if (eventName.isEmpty()) {
            Log.e(MIXPANEL_LOGTAG, "Empty Event");
            return;
        }
        if (properties != null) {
            for (Entry<String, ?> entry : properties.entrySet()) {
                try {
                    if (!(((String) entry.getKey()).isEmpty() || (((entry.getValue() instanceof String) && TextUtils.isEmpty((String) entry.getValue())) || entry.getValue() == null))) {
                        prop.put((String) entry.getKey(), entry.getValue());
                    }
                } catch (JSONException e) {
                }
            }
        }
        this.mAnalyticsUtils.logEvent(eventName, prop);
        prop = addSplitTestTracking(addVersionCode(prop));
        Amplitude.getInstance().setUserProperties(addSplitTestTracking(addVersionCode(new JSONObject())));
        Amplitude.getInstance().logEvent(eventName, prop);
        mixpanel.track(eventName, prop);
        mixpanel.flush();
    }

    @Deprecated
    public void trackEvent(String events, String... properties) {
        Map params = new HashMap();
        if (properties != null) {
            if (properties.length % 2 != 0) {
                Log.e(MIXPANEL_LOGTAG, "Properties missing values");
            } else {
                int i = 0;
                while (i < properties.length) {
                    Object obj = properties[i];
                    i++;
                    params.put(obj, properties[i]);
                    i++;
                }
            }
        }
        trackEvent(events, params);
    }

    public void trackExposureEventIfNeeded(SplitTest splitTest) {
        if (!TextUtils.isEmpty(splitTest.getGroup()) && !TextUtils.isEmpty(splitTest.getTest())) {
            String key = splitTest.getTest() + CryptoUri.URI_SCHEME_DELIMETER + splitTest.getGroup();
            long sessionId = Amplitude.getInstance().getSessionId();
            if (!this.mExposureMap.containsKey(key) || sessionId != ((Long) this.mExposureMap.get(key)).longValue()) {
                this.mExposureMap.put(key, Long.valueOf(sessionId));
                Amplitude.getInstance().setUserProperties(addSplitTestTracking(new JSONObject()));
                trackEvent(EVENT_SPLIT_TEST_EXPOSED, PROPERTY_SPLIT_TEST_EXPOSED_TEST, splitTest.getTest(), PROPERTY_SPLIT_TEST_EXPOSED_GROUP, splitTest.getGroup());
            }
        }
    }

    public void trackEventOnce(Event event) {
        trackEventOnce(event, null);
    }

    public void trackEventOnce(Event event, Map<String, ?> properties) {
        trackEventOnce(getEventName(event), (Map) properties);
    }

    public void trackEventOnce(String eventName) {
        trackEventOnce(eventName, null);
    }

    public void trackEventOnce(String eventName, Map<String, ?> properties) {
        Set<String> previouslyTrackedEvents = this.sharedPrefs.getStringSet(PREVIOUSLY_TRACKED_EVENTS, new HashSet());
        if (eventName.equals(EVENT_BALANCE_WIDGET_ENABLED) || eventName.equals(EVENT_PRICE_WIDGET_ENABLED)) {
            if (this.sharedPrefs.contains(eventName)) {
                return;
            }
        } else if (previouslyTrackedEvents.contains(eventName)) {
            return;
        }
        trackEvent(eventName, (Map) properties);
        previouslyTrackedEvents.add(eventName);
        this.sharedPrefs.edit().putStringSet(PREVIOUSLY_TRACKED_EVENTS, previouslyTrackedEvents).apply();
    }

    private JSONObject addVersionCode(JSONObject prop) {
        try {
            prop.put(PROPERTY_APP_VERSION_CODE, String.valueOf(BuildConfig.VERSION_CODE));
        } catch (JSONException e) {
            this.mLogger.error("Couldn't add version code to mixpanel properties", e);
        }
        return prop;
    }

    private JSONObject addSplitTestTracking(JSONObject prop) {
        synchronized (this) {
            if (this.mTrackedSplitTests.length() > 0) {
                try {
                    prop.put(SPLIT_TEST_GROUPS_PROPERTY, this.mTrackedSplitTests);
                } catch (JSONException e) {
                    this.mLogger.error("Couldn't add tracked splittests array to mixpanel properties", e);
                }
            }
        }
        return prop;
    }

    private String getEventName(Event event) {
        return event.name().toLowerCase();
    }

    private String getPropertyName(Property property) {
        return property.name().toLowerCase();
    }
}
