package com.coinbase.android.idology;

import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;

public final class IdologyFormErrorMatcher_Factory implements Factory<IdologyFormErrorMatcher> {
    private final Provider<List<String>> handledFieldsProvider;

    public IdologyFormErrorMatcher_Factory(Provider<List<String>> handledFieldsProvider) {
        this.handledFieldsProvider = handledFieldsProvider;
    }

    public IdologyFormErrorMatcher get() {
        return provideInstance(this.handledFieldsProvider);
    }

    public static IdologyFormErrorMatcher provideInstance(Provider<List<String>> handledFieldsProvider) {
        return new IdologyFormErrorMatcher((List) handledFieldsProvider.get());
    }

    public static IdologyFormErrorMatcher_Factory create(Provider<List<String>> handledFieldsProvider) {
        return new IdologyFormErrorMatcher_Factory(handledFieldsProvider);
    }

    public static IdologyFormErrorMatcher newIdologyFormErrorMatcher(List<String> handledFields) {
        return new IdologyFormErrorMatcher(handledFields);
    }
}
