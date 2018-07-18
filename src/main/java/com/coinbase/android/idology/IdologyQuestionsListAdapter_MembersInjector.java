package com.coinbase.android.idology;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class IdologyQuestionsListAdapter_MembersInjector implements MembersInjector<IdologyQuestionsListAdapter> {
    private final Provider<IdologyAnswerSelectedConnector> mIdologyAnswerSelectedConnectorProvider;

    public IdologyQuestionsListAdapter_MembersInjector(Provider<IdologyAnswerSelectedConnector> mIdologyAnswerSelectedConnectorProvider) {
        this.mIdologyAnswerSelectedConnectorProvider = mIdologyAnswerSelectedConnectorProvider;
    }

    public static MembersInjector<IdologyQuestionsListAdapter> create(Provider<IdologyAnswerSelectedConnector> mIdologyAnswerSelectedConnectorProvider) {
        return new IdologyQuestionsListAdapter_MembersInjector(mIdologyAnswerSelectedConnectorProvider);
    }

    public void injectMembers(IdologyQuestionsListAdapter instance) {
        injectMIdologyAnswerSelectedConnector(instance, (IdologyAnswerSelectedConnector) this.mIdologyAnswerSelectedConnectorProvider.get());
    }

    public static void injectMIdologyAnswerSelectedConnector(IdologyQuestionsListAdapter instance, IdologyAnswerSelectedConnector mIdologyAnswerSelectedConnector) {
        instance.mIdologyAnswerSelectedConnector = mIdologyAnswerSelectedConnector;
    }
}
