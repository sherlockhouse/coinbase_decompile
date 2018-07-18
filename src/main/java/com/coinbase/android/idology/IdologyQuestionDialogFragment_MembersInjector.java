package com.coinbase.android.idology;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class IdologyQuestionDialogFragment_MembersInjector implements MembersInjector<IdologyQuestionDialogFragment> {
    private final Provider<IdologyAnswerSelectedConnector> mIdologyAnswerSelectedConnectorProvider;

    public IdologyQuestionDialogFragment_MembersInjector(Provider<IdologyAnswerSelectedConnector> mIdologyAnswerSelectedConnectorProvider) {
        this.mIdologyAnswerSelectedConnectorProvider = mIdologyAnswerSelectedConnectorProvider;
    }

    public static MembersInjector<IdologyQuestionDialogFragment> create(Provider<IdologyAnswerSelectedConnector> mIdologyAnswerSelectedConnectorProvider) {
        return new IdologyQuestionDialogFragment_MembersInjector(mIdologyAnswerSelectedConnectorProvider);
    }

    public void injectMembers(IdologyQuestionDialogFragment instance) {
        injectMIdologyAnswerSelectedConnector(instance, (IdologyAnswerSelectedConnector) this.mIdologyAnswerSelectedConnectorProvider.get());
    }

    public static void injectMIdologyAnswerSelectedConnector(IdologyQuestionDialogFragment instance, IdologyAnswerSelectedConnector mIdologyAnswerSelectedConnector) {
        instance.mIdologyAnswerSelectedConnector = mIdologyAnswerSelectedConnector;
    }
}
