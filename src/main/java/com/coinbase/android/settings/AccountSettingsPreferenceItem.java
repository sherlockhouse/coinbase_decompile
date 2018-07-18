package com.coinbase.android.settings;

import android.app.Activity;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.paymentmethods.ConnectedAccountsLayout;

public abstract class AccountSettingsPreferenceItem implements SettingsPreferenceItem {
    private String mDisplayValue = "";
    protected AccountSettingsPresenter mPresenter;

    static class AboutItem extends AccountSettingsPreferenceItem {
        AboutItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.menu_about);
        }

        public String getDisplayValue() {
            return this.mPresenter.getFormattedAppVersion();
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.INFO;
        }

        public void onClick() {
        }
    }

    static class AccountHeader extends AccountSettingsPreferenceItem {
        AccountHeader(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.account_header);
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.HEADER;
        }

        public void onClick() {
        }
    }

    static class AppHeader extends AccountSettingsPreferenceItem {
        AppHeader(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.app_header);
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.HEADER;
        }

        public void onClick() {
        }
    }

    static class ConnectedAccountsHeader extends AccountSettingsPreferenceItem {
        ConnectedAccountsHeader(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.linked_accounts);
        }

        public String getDisplayValue() {
            return null;
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.HEADER;
        }

        public void onClick() {
        }
    }

    static class ConnectedAccountsView extends AccountSettingsPreferenceItem {
        private ConnectedAccountsLayout view;

        ConnectedAccountsView(AccountSettingsPresenter presenter, Activity activity) {
            super(presenter);
            this.view = new ConnectedAccountsLayout(activity);
        }

        public String getDisplayName() {
            return null;
        }

        public String getDisplayValue() {
            return null;
        }

        public void onClick() {
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.CONNECTED_ACCOUNTS;
        }

        ConnectedAccountsLayout getView() {
            return this.view;
        }
    }

    static class EmailItem extends AccountSettingsPreferenceItem {
        EmailItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.account_email);
        }

        public String getDisplayValue() {
            return getCachedValue(Constants.KEY_ACCOUNT_EMAIL);
        }

        public void onClick() {
        }
    }

    static class EmailSettingsItem extends AccountSettingsPreferenceItem {
        EmailSettingsItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.gdpr_email_settings);
        }

        public void onClick() {
            this.mPresenter.onEmailSettingsItemClicked();
        }
    }

    static class IdentityDocument extends AccountSettingsPreferenceItem {
        IdentityDocument(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.account_settings_identity_document);
        }

        public void onClick() {
            this.mPresenter.onIdentityDocumentItemClicked();
        }
    }

    static class InviteItem extends AccountSettingsPreferenceItem {
        InviteItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.invite_friends);
        }

        public void onClick() {
            this.mPresenter.onInviteItemClicked();
        }
    }

    static class NameItem extends AccountSettingsPreferenceItem {
        NameItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.account_name);
        }

        public String getDisplayValue() {
            return getCachedValue(Constants.KEY_ACCOUNT_FULL_NAME);
        }

        public void onClick() {
            this.mPresenter.onNameItemClicked(getDisplayValue());
        }
    }

    static class NativeCurrencyItem extends AccountSettingsPreferenceItem {
        NativeCurrencyItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.account_native_currency);
        }

        public String getDisplayValue() {
            return getCachedValue(Constants.KEY_ACCOUNT_NATIVE_CURRENCY);
        }

        public void onClick() {
            this.mPresenter.onNativeCurrencyItemClicked(getDisplayValue());
        }
    }

    static class PaymentMethodsItem extends AccountSettingsPreferenceItem {
        PaymentMethodsItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.linked_accounts);
        }

        public void onClick() {
            this.mPresenter.onPaymentMethodsItemClicked();
        }
    }

    static class PersonalInformation extends AccountSettingsPreferenceItem {
        PersonalInformation(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.account_settings_personal_information);
        }

        public void onClick() {
            this.mPresenter.onPersonalInformationItemClicked();
        }
    }

    static class PhoneNumberItem extends AccountSettingsPreferenceItem {
        PhoneNumberItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.account_phone_numbers);
        }

        public void onClick() {
            this.mPresenter.onPhoneNumberItemClicked();
        }
    }

    static class PinItem extends AccountSettingsPreferenceItem {
        PinItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.account_android_pin);
        }

        public boolean showSwitch() {
            return true;
        }

        public boolean isSwitchOn() {
            return this.mPresenter.isPinEnabled();
        }

        public void onClick() {
            this.mPresenter.onPinItemClicked(isSwitchOn());
        }

        public boolean isNavigationEnabled() {
            return false;
        }
    }

    static class PrivacyItem extends AccountSettingsPreferenceItem {
        PrivacyItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.gdpr_privacy);
        }

        public void onClick() {
            this.mPresenter.onPrivacyItemClicked();
        }
    }

    static class ShareItem extends AccountSettingsPreferenceItem {
        ShareItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.menu_share_coinbase);
        }

        public void onClick() {
            this.mPresenter.onShareItemClicked();
        }
    }

    static class SignOutItem extends AccountSettingsPreferenceItem {
        SignOutItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.menu_sign_out);
        }

        public void onClick() {
            this.mPresenter.onSignOutItemClicked();
        }

        public int getDisplayNameTextColor() {
            return R.color.red_text_color;
        }

        public boolean isNavigationEnabled() {
            return false;
        }
    }

    static class SupportItem extends AccountSettingsPreferenceItem {
        SupportItem(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.menu_support);
        }

        public void onClick() {
            this.mPresenter.onSupportItemClicked();
        }

        public int getDisplayNameTextColor() {
            return R.color.link_text_color;
        }

        public boolean isNavigationEnabled() {
            return false;
        }
    }

    static class TiersItem extends AccountSettingsPreferenceItem {
        TiersItem(AccountSettingsPresenter presenter, String tiersLevelName) {
            super(presenter);
            setDisplayValue(tiersLevelName);
        }

        public void onClick() {
            this.mPresenter.onTiersItemClicked();
        }

        public String getDisplayName() {
            return getDisplayName(R.string.investment_limit);
        }
    }

    static class VerificationHeader extends AccountSettingsPreferenceItem {
        VerificationHeader(AccountSettingsPresenter presenter) {
            super(presenter);
        }

        public String getDisplayName() {
            return getDisplayName(R.string.account_settings_verification_header);
        }

        public SettingsPreferenceItemType getType() {
            return SettingsPreferenceItemType.HEADER;
        }

        public void onClick() {
        }
    }

    public abstract String getDisplayName();

    public abstract void onClick();

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public String getDisplayDescription() {
        return null;
    }

    void setDisplayValue(String value) {
        this.mDisplayValue = value;
    }

    public SettingsPreferenceItemType getType() {
        return SettingsPreferenceItemType.ITEM;
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

    AccountSettingsPreferenceItem(AccountSettingsPresenter presenter) {
        this.mPresenter = presenter;
    }

    String getCachedValue(String preferenceKey) {
        return this.mPresenter.getCachedValue(preferenceKey, null);
    }

    String getDisplayName(int resourceId) {
        return this.mPresenter.getString(resourceId);
    }

    public int getDisplayNameTextColor() {
        return R.color.primary_mystique_text_color;
    }
}
