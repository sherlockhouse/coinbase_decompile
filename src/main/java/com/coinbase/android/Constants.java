package com.coinbase.android;

public class Constants {
    public static final String ABBREVIATION = "abbreviation";
    public static final int ACTIVITY_RESULT_PARENT_SUCCESS_ROUTER = 9999;
    public static final long ANIM_DURATION = 250;
    public static final int AVATAR_IMAGE_RADIUS = 80;
    public static final String BILLING_ID = "BILLING_ID";
    public static final String BILLING_STRING = "BILLING_STRING";
    public static final String CLIENT_ID = "34183b03a3e1f0b74ee6aa8a6150e90125de2d6c1ee4ff7880c2b7e6e98b11f5";
    public static final String CLIENT_SECRET = "2c481f46f9dc046b4b9a67e630041b9906c023d139fbc77a47053328b9d3122d";
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final int DISPLAY_HEIGHT_PX_MIN = 1794;
    public static final String GOOGLE_NOW_CLIENT_ID = "242335961153-ili65kms0rs20m7a68jklh5ka7733plc.apps.googleusercontent.com";
    public static final String GOOGLE_PROJECT_ID = "242335961153";
    public static final String KEY_ACCOUNT_ACCESS_TOKEN = "account_access_token";
    public static final String KEY_ACCOUNT_APP_USAGE = "account_app_usage";
    public static final String KEY_ACCOUNT_EMAIL = "account_email";
    public static final String KEY_ACCOUNT_ENABLE_TIPPING = "account_enable_tipping";
    public static final String KEY_ACCOUNT_FIRST_LAUNCH = "account_first_launch";
    public static final String KEY_ACCOUNT_FULL_NAME = "account_full_name";
    public static final String KEY_ACCOUNT_HAS_ETH_ACCOUNT = "account_has_eth_account";
    public static final String KEY_ACCOUNT_NATIVE_CURRENCY = "account_native_currency";
    public static final String KEY_ACCOUNT_NEEDS_USER_AGREEMENT = "account_needs_user_agreement";
    public static final String KEY_ACCOUNT_PIN = "account_pin";
    public static final String KEY_ACCOUNT_PIN_VIEW_ALLOWED = "account_pin_view_allowed";
    public static final String KEY_ACCOUNT_POS_BTC_AMT = "account_pos_btc_amt";
    public static final String KEY_ACCOUNT_POS_NOTES = "account_pos_notes";
    public static final String KEY_ACCOUNT_RATE_NOTICE_STATE = "account_rate_notice_state";
    public static final String KEY_ACCOUNT_REFRESH_TOKEN = "account_refresh_token";
    public static final String KEY_ACCOUNT_SALT = "account_salt";
    public static final String KEY_ACCOUNT_SHOW_BALANCE = "account_show_balance";
    public static final String KEY_ACCOUNT_TIME_ZONE = "account_time_zone";
    public static final String KEY_ACCOUNT_TOKEN_EXPIRES_AT = "account_token_expires_at";
    public static final String KEY_ACCOUNT_TRANSFER_CURRENCY_BTC = "account_transfer_currency_btc";
    public static final String KEY_ACCOUNT_VALID = "account_valid";
    public static final String KEY_ACCOUNT_VALID_DESC = "account_valid_desc";
    public static final String KEY_ACTIVE_ACCOUNT_ID = "active_account_id";
    public static final String KEY_ACTIVE_ACCOUNT_NAME = "active_account_name";
    public static final String KEY_FEATURE_FLAGS = "user_feature_flags";
    public static final String KEY_HAS_USER_ENTERED_PIN = "has_user_entered_pin";
    public static final String KEY_INCORRECT_PIN_TRIES = "incoorect_pin_tries";
    public static final String KEY_LAST_CHOSEN_CURRENCY = "last_chosen_currency";
    public static final String KEY_LOGIN_CODE_WAITING_FOR_DV = "login_code_waiting_for_dv";
    public static final String KEY_LOGIN_CSRF_TOKEN = "login_csrf_token";
    public static final String KEY_PHONE_VERIFIED = "phone_verified";
    public static final String KEY_PIN_ENABLED_APP_OPEN = "pin_enabled_app_open";
    public static final String KEY_PIN_ENABLED_SEND_MONEY = "pin_enabled_send_money";
    public static final String KEY_QUICKSTART_ITEM_CANCELLED = "quickstart_item_cancelled_%s";
    public static final String KEY_QUICKSTART_ITEM_SHOW = "quickstart_item_show_%s";
    public static final String KEY_RATE_APP_THRESHOLD = "KEY_RATE_APP_THRESHOLD";
    public static final String KEY_REFERRAL = "referral";
    public static final String KEY_SHOW_JUMIO_FLOW = "show_jumio_flow";
    public static final String KEY_USER_COUNTRY_CODE = "user_country_code";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_IN_EU = "user_is_in_europe";
    public static final String KEY_WIDGET_ACCOUNT = "widget_%d_account";
    public static final String KEY_WIDGET_ACCOUNT_TYPE = "widget_%d_account_type";
    public static final String KEY_WIDGET_CURRENCY = "widget_%d_currency";
    public static final String LAUNCH_MESSAGE_HASHCODE = "LAUNCH_MESSAGE_HASHCODE";
    public static final String NAME = "name";
    public static final String NOTIFICATION_BUY_FRAGMENT = "NOTIFICATION_BUY_FRAGMENT";
    public static final String NOTIFICATION_CHART_FRAGMENT = "NOTIFICATION_CHART_FRAGMENT";
    public static final String NOTIFICATION_PRICE_ALERTS_CHANNEL = "NOTIFICATION_PRICE_ALERTS_CHANNEL";
    public static final String NOTIFICATION_SELL_FRAGMENT = "NOTIFICATION_SELL_FRAGMENT";
    public static final String NOTIFICATION_SET = "NOTIFICATION_SET_V2";
    public static final String NOTIFICATION_SET_LEGACY = "NOTIFICATION_SET";
    public static final long OVERLAY_ANIM_DURATION = 100;
    public static final String PARENT_SUCCESS_ROUTER = "parent_success_router";
    public static final String PLAY_STORE_URI = "https://play.google.com/store/apps/details?id=com.coinbase.android";
    public static final int PRICE_ALERT_NOT_TRIGGERED = -1;
    public static final int RATE_APP_THRESHOLD = 3;
    public static final short RETRY_DURATION = (short) 500;
    public static final String SERVER_OUTAGE_MESSAGE_SHOWN = "SERVER_OUTAGE_MESSAGE_SHOWN";
    public static final String TENJIN_API_KEY = "A1W77ZEG4UFVTGD2DGEEBMBZ4EEHESRV";
    public static final long TOKEN_EXPIRY_PERIOD = 7200000;

    public enum RateNoticeState {
        NOTICE_NOT_YET_SHOWN,
        SHOULD_SHOW_NOTICE,
        NOTICE_DISMISSED
    }
}
