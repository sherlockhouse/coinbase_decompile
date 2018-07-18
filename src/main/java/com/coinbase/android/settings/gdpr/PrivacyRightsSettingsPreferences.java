package com.coinbase.android.settings.gdpr;

import android.content.Context;
import com.coinbase.android.R;
import com.coinbase.android.settings.SettingsPreferenceItem;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.settings.SettingsPreferenceItemType;

public abstract class PrivacyRightsSettingsPreferences implements SettingsPreferenceItem {

    public static class ExercisePrivacyRightsHeader extends PrivacyRightsSettingsPreferences {
        private Context mContext;

        public ExercisePrivacyRightsHeader(Context context) {
            this.mContext = context;
        }

        public String getDisplayName() {
            return this.mContext.getString(R.string.gdpr_privacy_options);
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.HEADER;
        }

        public void onClick() {
        }
    }

    public static class RequestCorrectionItem extends PrivacyRightsSettingsPreferences {
        SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        public RequestCorrectionItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return this.mContext.getString(R.string.gdpr_request_correction);
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public void onClick() {
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class RequestDataItem extends PrivacyRightsSettingsPreferences {
        SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        public RequestDataItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return this.mContext.getString(R.string.gdpr_request_data);
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public void onClick() {
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class RequestDeletion extends PrivacyRightsSettingsPreferences {
        SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        public RequestDeletion(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return this.mContext.getString(R.string.gdpr_request_deletion);
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public void onClick() {
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class RequestExport extends PrivacyRightsSettingsPreferences {
        SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        public RequestExport(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return this.mContext.getString(R.string.gdpr_request_export);
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public void onClick() {
            this.mClickedConnector.get().onNext(this);
        }
    }

    public static class RequestRestrictionItem extends PrivacyRightsSettingsPreferences {
        SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;

        public RequestRestrictionItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector) {
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return this.mContext.getString(R.string.gdpr_request_restriction);
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.ITEM;
        }

        public void onClick() {
            this.mClickedConnector.get().onNext(this);
        }
    }

    public String getDisplayValue() {
        return null;
    }

    public String getDisplayDescription() {
        return null;
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

    public boolean isNavigationEnabled() {
        return true;
    }
}
