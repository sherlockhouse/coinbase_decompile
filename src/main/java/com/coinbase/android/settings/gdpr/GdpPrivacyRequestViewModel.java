package com.coinbase.android.settings.gdpr;

import android.content.Context;
import com.coinbase.android.R;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.ActivityLogItem;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.AllItem;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.ConnectedApplicationsItem;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.ContactInformationItem;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.FinancialInformation;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.JobRelatedInformation;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.MarketingAndCommunicationsItem;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.OnlineBehaviorItem;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.ReferralsItem;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.SelectedRequestedDataItem;
import com.coinbase.android.settings.gdpr.GdprPrivacyRequestSettingsPreferenceItem.SurveyResponsesItem;
import java.util.ArrayList;
import java.util.List;

abstract class GdpPrivacyRequestViewModel {

    public enum RequestActionType {
        ACCESS("access"),
        DELETION("deletion"),
        EXPORT("export"),
        RESTRICT("restrict"),
        CORRECTION("correction");
        
        private final String mAction;

        private RequestActionType(String action) {
            this.mAction = action;
        }

        public String getAction() {
            return this.mAction;
        }

        public static RequestActionType from(String action) {
            for (RequestActionType type : values()) {
                if (type.getAction().equals(action)) {
                    return type;
                }
            }
            return null;
        }
    }

    static class RequestCorrectionViewModel implements PrivacyRequestViewModel {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        RequestCorrectionViewModel(Context context, SettingsPreferenceItemClickedConnector itemClickedConnector) {
            this.mContext = context;
            this.mClickedConnector = itemClickedConnector;
        }

        public RequestActionType getActionType() {
            return RequestActionType.CORRECTION;
        }

        public String getTitle() {
            return this.mContext.getString(R.string.gdpr_request_correction);
        }

        public String getLegalHeader() {
            return this.mContext.getString(R.string.gdpr_request_correction_legal_header);
        }

        public List<GdprPrivacyRequestSettingsPreferenceItem> getOptions() {
            List<GdprPrivacyRequestSettingsPreferenceItem> itemList = new ArrayList();
            itemList.add(new SelectedRequestedDataItem(this.mContext));
            itemList.add(new ContactInformationItem(this.mContext, this.mClickedConnector));
            itemList.add(new JobRelatedInformation(this.mContext, this.mClickedConnector));
            itemList.add(new FinancialInformation(this.mContext, this.mClickedConnector));
            itemList.add(new SurveyResponsesItem(this.mContext, this.mClickedConnector));
            itemList.add(new ReferralsItem(this.mContext, this.mClickedConnector));
            itemList.add(new ConnectedApplicationsItem(this.mContext, this.mClickedConnector));
            return itemList;
        }

        public boolean isTerminalPage() {
            return true;
        }

        public GdprSettingsEvent getAnalyticsEvent() {
            return GdprSettingsEvent.PRIVACY_OPTIONS_TAPPED_REQUEST_CORRECTION;
        }
    }

    static class RequestDataViewModel implements PrivacyRequestViewModel {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        RequestDataViewModel(Context context, SettingsPreferenceItemClickedConnector itemClickedConnector) {
            this.mContext = context;
            this.mClickedConnector = itemClickedConnector;
        }

        public RequestActionType getActionType() {
            return RequestActionType.ACCESS;
        }

        public String getTitle() {
            return this.mContext.getString(R.string.gdpr_request_data);
        }

        public String getLegalHeader() {
            return this.mContext.getString(R.string.gdpr_redirect_to_web);
        }

        public List<GdprPrivacyRequestSettingsPreferenceItem> getOptions() {
            return new ArrayList();
        }

        public boolean isTerminalPage() {
            return getOptions().size() > 0;
        }

        public GdprSettingsEvent getAnalyticsEvent() {
            return GdprSettingsEvent.PRIVACY_OPTIONS_TAPPED_REQUEST_DATA;
        }
    }

    static class RequestDeletionViewModel implements PrivacyRequestViewModel {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        RequestDeletionViewModel(Context context, SettingsPreferenceItemClickedConnector itemClickedConnector) {
            this.mContext = context;
            this.mClickedConnector = itemClickedConnector;
        }

        public RequestActionType getActionType() {
            return RequestActionType.DELETION;
        }

        public String getTitle() {
            return this.mContext.getString(R.string.gdpr_request_deletion);
        }

        public String getLegalHeader() {
            return this.mContext.getString(R.string.gdpr_request_deletion_legal_header);
        }

        public List<GdprPrivacyRequestSettingsPreferenceItem> getOptions() {
            List<GdprPrivacyRequestSettingsPreferenceItem> itemList = new ArrayList();
            itemList.add(new SelectedRequestedDataItem(this.mContext));
            itemList.add(new SurveyResponsesItem(this.mContext, this.mClickedConnector));
            itemList.add(new ReferralsItem(this.mContext, this.mClickedConnector));
            itemList.add(new ConnectedApplicationsItem(this.mContext, this.mClickedConnector));
            itemList.add(new MarketingAndCommunicationsItem(this.mContext, this.mClickedConnector));
            itemList.add(new AllItem(this.mContext, this.mClickedConnector));
            return itemList;
        }

        public boolean isTerminalPage() {
            return true;
        }

        public GdprSettingsEvent getAnalyticsEvent() {
            return GdprSettingsEvent.PRIVACY_OPTIONS_TAPPED_REQUEST_DELETION;
        }
    }

    static class RequestExportViewModel implements PrivacyRequestViewModel {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        RequestExportViewModel(Context context, SettingsPreferenceItemClickedConnector itemClickedConnector) {
            this.mContext = context;
            this.mClickedConnector = itemClickedConnector;
        }

        public RequestActionType getActionType() {
            return RequestActionType.EXPORT;
        }

        public String getTitle() {
            return this.mContext.getString(R.string.gdpr_request_export);
        }

        public String getLegalHeader() {
            return this.mContext.getString(R.string.gdpr_redirect_to_web);
        }

        public List<GdprPrivacyRequestSettingsPreferenceItem> getOptions() {
            return new ArrayList();
        }

        public boolean isTerminalPage() {
            return getOptions().size() > 0;
        }

        public GdprSettingsEvent getAnalyticsEvent() {
            return GdprSettingsEvent.PRIVACY_OPTIONS_TAPPED_REQUEST_EXPORT;
        }
    }

    static class RequestRestrictionProcessingViewModel implements PrivacyRequestViewModel {
        private SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        RequestRestrictionProcessingViewModel(Context context, SettingsPreferenceItemClickedConnector itemClickedConnector) {
            this.mContext = context;
            this.mClickedConnector = itemClickedConnector;
        }

        public RequestActionType getActionType() {
            return RequestActionType.RESTRICT;
        }

        public String getTitle() {
            return this.mContext.getString(R.string.gdpr_request_restriction_toolbar_header);
        }

        public String getLegalHeader() {
            return this.mContext.getString(R.string.gdpr_request_restriction_processing_legal_header);
        }

        public List<GdprPrivacyRequestSettingsPreferenceItem> getOptions() {
            List<GdprPrivacyRequestSettingsPreferenceItem> itemList = new ArrayList();
            itemList.add(new SelectedRequestedDataItem(this.mContext));
            itemList.add(new ContactInformationItem(this.mContext, this.mClickedConnector));
            itemList.add(new JobRelatedInformation(this.mContext, this.mClickedConnector));
            itemList.add(new FinancialInformation(this.mContext, this.mClickedConnector));
            itemList.add(new ActivityLogItem(this.mContext, this.mClickedConnector));
            itemList.add(new OnlineBehaviorItem(this.mContext, this.mClickedConnector));
            itemList.add(new SurveyResponsesItem(this.mContext, this.mClickedConnector));
            itemList.add(new ReferralsItem(this.mContext, this.mClickedConnector));
            itemList.add(new ConnectedApplicationsItem(this.mContext, this.mClickedConnector));
            return itemList;
        }

        public boolean isTerminalPage() {
            return true;
        }

        public GdprSettingsEvent getAnalyticsEvent() {
            return GdprSettingsEvent.PRIVACY_OPTIONS_TAPPED_REQUEST_RESTRICTION_OF_PROCESSING;
        }
    }

    GdpPrivacyRequestViewModel() {
    }
}
