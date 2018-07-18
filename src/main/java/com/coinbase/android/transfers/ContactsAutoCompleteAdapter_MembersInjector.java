package com.coinbase.android.transfers;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ContactsAutoCompleteAdapter_MembersInjector implements MembersInjector<ContactsAutoCompleteAdapter> {
    private final Provider<LoginManager> mLoginManagerProvider;

    public ContactsAutoCompleteAdapter_MembersInjector(Provider<LoginManager> mLoginManagerProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<ContactsAutoCompleteAdapter> create(Provider<LoginManager> mLoginManagerProvider) {
        return new ContactsAutoCompleteAdapter_MembersInjector(mLoginManagerProvider);
    }

    public void injectMembers(ContactsAutoCompleteAdapter instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMLoginManager(ContactsAutoCompleteAdapter instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
