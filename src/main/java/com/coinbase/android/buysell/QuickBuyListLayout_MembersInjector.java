package com.coinbase.android.buysell;

import com.coinbase.android.utils.MoneyFormatterUtil;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class QuickBuyListLayout_MembersInjector implements MembersInjector<QuickBuyListLayout> {
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;
    private final Provider<QuickBuyConnector> mQuickBuyConnectorProvider;

    public QuickBuyListLayout_MembersInjector(Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<QuickBuyConnector> mQuickBuyConnectorProvider) {
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
        this.mQuickBuyConnectorProvider = mQuickBuyConnectorProvider;
    }

    public static MembersInjector<QuickBuyListLayout> create(Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<QuickBuyConnector> mQuickBuyConnectorProvider) {
        return new QuickBuyListLayout_MembersInjector(mMoneyFormatterUtilProvider, mQuickBuyConnectorProvider);
    }

    public void injectMembers(QuickBuyListLayout instance) {
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
        injectMQuickBuyConnector(instance, (QuickBuyConnector) this.mQuickBuyConnectorProvider.get());
    }

    public static void injectMMoneyFormatterUtil(QuickBuyListLayout instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }

    public static void injectMQuickBuyConnector(QuickBuyListLayout instance, QuickBuyConnector mQuickBuyConnector) {
        instance.mQuickBuyConnector = mQuickBuyConnector;
    }
}
