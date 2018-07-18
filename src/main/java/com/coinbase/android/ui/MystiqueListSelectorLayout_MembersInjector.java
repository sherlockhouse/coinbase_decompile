package com.coinbase.android.ui;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class MystiqueListSelectorLayout_MembersInjector implements MembersInjector<MystiqueListSelectorLayout> {
    private final Provider<MystiqueListButtonConnector> mListButtonConnectorProvider;
    private final Provider<MystiqueListSelectorConnector> mListSelectorConnectorProvider;

    public MystiqueListSelectorLayout_MembersInjector(Provider<MystiqueListSelectorConnector> mListSelectorConnectorProvider, Provider<MystiqueListButtonConnector> mListButtonConnectorProvider) {
        this.mListSelectorConnectorProvider = mListSelectorConnectorProvider;
        this.mListButtonConnectorProvider = mListButtonConnectorProvider;
    }

    public static MembersInjector<MystiqueListSelectorLayout> create(Provider<MystiqueListSelectorConnector> mListSelectorConnectorProvider, Provider<MystiqueListButtonConnector> mListButtonConnectorProvider) {
        return new MystiqueListSelectorLayout_MembersInjector(mListSelectorConnectorProvider, mListButtonConnectorProvider);
    }

    public void injectMembers(MystiqueListSelectorLayout instance) {
        injectMListSelectorConnector(instance, (MystiqueListSelectorConnector) this.mListSelectorConnectorProvider.get());
        injectMListButtonConnector(instance, (MystiqueListButtonConnector) this.mListButtonConnectorProvider.get());
    }

    public static void injectMListSelectorConnector(MystiqueListSelectorLayout instance, MystiqueListSelectorConnector mListSelectorConnector) {
        instance.mListSelectorConnector = mListSelectorConnector;
    }

    public static void injectMListButtonConnector(MystiqueListSelectorLayout instance, MystiqueListButtonConnector mListButtonConnector) {
        instance.mListButtonConnector = mListButtonConnector;
    }
}
