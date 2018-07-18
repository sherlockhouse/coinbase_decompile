package com.coinbase.android;

import com.coinbase.android.settings.AccountSettingsPreferenceItem;
import com.coinbase.android.settings.AccountSettingsPresenter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import rx.functions.Func1;

public final class NoOpDebugModule_ProvidesAccountSettingsPreferenceItemListFactory implements Factory<List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>>> {
    private final NoOpDebugModule module;

    public NoOpDebugModule_ProvidesAccountSettingsPreferenceItemListFactory(NoOpDebugModule module) {
        this.module = module;
    }

    public List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>> get() {
        return provideInstance(this.module);
    }

    public static List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>> provideInstance(NoOpDebugModule module) {
        return proxyProvidesAccountSettingsPreferenceItemList(module);
    }

    public static NoOpDebugModule_ProvidesAccountSettingsPreferenceItemListFactory create(NoOpDebugModule module) {
        return new NoOpDebugModule_ProvidesAccountSettingsPreferenceItemListFactory(module);
    }

    public static List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>> proxyProvidesAccountSettingsPreferenceItemList(NoOpDebugModule instance) {
        return (List) Preconditions.checkNotNull(instance.providesAccountSettingsPreferenceItemList(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
