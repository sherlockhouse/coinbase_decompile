package com.coinbase.android;

import android.os.Bundle;
import android.util.Pair;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ModalBottomNavigationItem.Type;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import rx.functions.Func1;

public final class BottomNavActivityModule_ProvidesModalControllerMapFactory implements Factory<Map<Type, Func1<Bundle, ActionBarController>>> {
    private final Provider<List<Pair<Type, Func1<Bundle, ActionBarController>>>> listProvider;
    private final BottomNavActivityModule module;

    public BottomNavActivityModule_ProvidesModalControllerMapFactory(BottomNavActivityModule module, Provider<List<Pair<Type, Func1<Bundle, ActionBarController>>>> listProvider) {
        this.module = module;
        this.listProvider = listProvider;
    }

    public Map<Type, Func1<Bundle, ActionBarController>> get() {
        return provideInstance(this.module, this.listProvider);
    }

    public static Map<Type, Func1<Bundle, ActionBarController>> provideInstance(BottomNavActivityModule module, Provider<List<Pair<Type, Func1<Bundle, ActionBarController>>>> listProvider) {
        return proxyProvidesModalControllerMap(module, (List) listProvider.get());
    }

    public static BottomNavActivityModule_ProvidesModalControllerMapFactory create(BottomNavActivityModule module, Provider<List<Pair<Type, Func1<Bundle, ActionBarController>>>> listProvider) {
        return new BottomNavActivityModule_ProvidesModalControllerMapFactory(module, listProvider);
    }

    public static Map<Type, Func1<Bundle, ActionBarController>> proxyProvidesModalControllerMap(BottomNavActivityModule instance, List<Pair<Type, Func1<Bundle, ActionBarController>>> list) {
        return (Map) Preconditions.checkNotNull(instance.providesModalControllerMap(list), "Cannot return null from a non-@Nullable @Provides method");
    }
}
