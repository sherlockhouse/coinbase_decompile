package com.coinbase.android.settings.gdpr;

import android.content.Context;
import com.coinbase.android.R;
import com.coinbase.android.settings.SettingsPreferenceItem;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.settings.SettingsPreferenceItemType;
import com.coinbase.api.internal.ApiConstants;

public abstract class EmailSettingsPreferenceItem implements SettingsPreferenceItem {

    public static class MarketingEmailSettingsItem extends EmailSettingsPreferenceItem {
        SettingsPreferenceItemClickedConnector mClickedConnector;
        private Context mContext;
        private EmailSettingsPresenter mPresenter;

        public MarketingEmailSettingsItem(Context context, SettingsPreferenceItemClickedConnector clickedConnector, EmailSettingsPresenter emailSettingsPresenter) {
            this.mPresenter = emailSettingsPresenter;
            this.mContext = context;
            this.mClickedConnector = clickedConnector;
        }

        public String getDisplayName() {
            return this.mContext.getString(R.string.marketing_email_title);
        }

        public String getDisplayValue() {
            return null;
        }

        public String getDisplayDescription() {
            return this.mContext.getString(R.string.marketing_email_paragraph);
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
            return this.mPresenter.isToggled(ApiConstants.MARKETING);
        }

        public boolean isNavigationEnabled() {
            return false;
        }

        public void onClick() {
            this.mClickedConnector.get().onNext(this);
        }
    }
}
