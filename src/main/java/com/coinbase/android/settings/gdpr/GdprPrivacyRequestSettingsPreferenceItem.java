package com.coinbase.android.settings.gdpr;

import android.content.Context;
import com.coinbase.android.R;
import com.coinbase.android.settings.SettingsPreferenceItem;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.settings.SettingsPreferenceItemType;
import java.util.Locale;

public abstract class GdprPrivacyRequestSettingsPreferenceItem implements SettingsPreferenceItem {

    public static class ActivityLogItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public ActivityLogItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.ACTIVITY_LOG;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class AllItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public AllItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.ALL;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class ConnectedApplicationsItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public ConnectedApplicationsItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.CONNECTED_APPLICATIONS;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class ContactInformationItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public ContactInformationItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.CONTACT_INFORMATION;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class FinancialInformation extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public FinancialInformation(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.FINANCIAL_INFORMATION;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class JobRelatedInformation extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public JobRelatedInformation(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.JOB_RELATED_INFORMATION;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class MarketingAndCommunicationsItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public MarketingAndCommunicationsItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.MARKETING_AND_COMMUNICATIONS_DATA;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class OnlineBehaviorItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public OnlineBehaviorItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.ONLINE_BEHAVIORS;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class ReferralsItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public ReferralsItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.REFERRALS;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    enum RequestSettingType {
        SELECT_REQUESTED_DATA(R.string.gdpr_select_requested_data),
        CONTACT_INFORMATION(R.string.gdpr_contact_information),
        JOB_RELATED_INFORMATION(R.string.gdpr_job_related_information),
        FINANCIAL_INFORMATION(R.string.gdpr_financial_information),
        ACTIVITY_LOG(R.string.gdpr_activity_log),
        ONLINE_BEHAVIORS(R.string.gdpr_online_behaviors),
        SURVEY_RESPONSES(R.string.gdpr_survey_responses),
        TRANSACTION_HISTORY(R.string.gdpr_transaction_history),
        REFERRALS(R.string.gdpr_referrals),
        CONNECTED_APPLICATIONS(R.string.gdpr_connected_application),
        MARKETING_AND_COMMUNICATIONS_DATA(R.string.gdpr_marketing_and_communications_data),
        MERCHANT_ORDERS(R.string.gdpr_merchant_orders),
        TRADING_DATA(R.string.gdpr_trading_data),
        SEND_REQUEST(R.string.gdpr_send_request),
        ALL(R.string.gdpr_all);
        
        private final int mResourceId;

        private RequestSettingType(int resourceId) {
            this.mResourceId = resourceId;
        }

        public String toString() {
            return name().replaceAll("_", " ").toLowerCase(Locale.US);
        }
    }

    public static class SelectedRequestedDataItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private Context mContext;

        public SelectedRequestedDataItem(Context context) {
            this.mContext = context;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.SELECT_REQUESTED_DATA;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.HEADER;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return false;
        }

        public boolean isSwitchOn() {
            return false;
        }

        public void onClick() {
        }
    }

    public static class SurveyResponsesItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public SurveyResponsesItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.SURVEY_RESPONSES;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class TradingDataItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public TradingDataItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.TRADING_DATA;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class TransactionHistoryItem extends GdprPrivacyRequestSettingsPreferenceItem {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private boolean mIsSwitchOn;

        public TransactionHistoryItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return getDisplayName(this.mContext);
        }

        public RequestSettingType getSettingType() {
            return RequestSettingType.TRANSACTION_HISTORY;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public int getDisplayNameTextColor() {
            return R.color.primary_mystique_text_color;
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mIsSwitchOn;
        }

        public void onClick() {
            this.mIsSwitchOn = !this.mIsSwitchOn;
            this.mClickedConnector.get().onNext(this);
        }
    }

    public abstract RequestSettingType getSettingType();

    public String getDisplayValue() {
        return null;
    }

    public String getDisplayDescription() {
        return null;
    }

    public boolean isNavigationEnabled() {
        return false;
    }

    protected String getDisplayName(Context context) {
        return context.getString(getSettingType().mResourceId);
    }
}
