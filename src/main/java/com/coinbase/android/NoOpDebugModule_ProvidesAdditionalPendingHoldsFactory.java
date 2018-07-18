package com.coinbase.android;

import com.coinbase.api.internal.models.wbl.PendingHold;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import rx.functions.Func0;

public final class NoOpDebugModule_ProvidesAdditionalPendingHoldsFactory implements Factory<Func0<List<PendingHold>>> {
    private final NoOpDebugModule module;

    public NoOpDebugModule_ProvidesAdditionalPendingHoldsFactory(NoOpDebugModule module) {
        this.module = module;
    }

    public Func0<List<PendingHold>> get() {
        return provideInstance(this.module);
    }

    public static Func0<List<PendingHold>> provideInstance(NoOpDebugModule module) {
        return proxyProvidesAdditionalPendingHolds(module);
    }

    public static NoOpDebugModule_ProvidesAdditionalPendingHoldsFactory create(NoOpDebugModule module) {
        return new NoOpDebugModule_ProvidesAdditionalPendingHoldsFactory(module);
    }

    public static Func0<List<PendingHold>> proxyProvidesAdditionalPendingHolds(NoOpDebugModule instance) {
        return (Func0) Preconditions.checkNotNull(instance.providesAdditionalPendingHolds(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
