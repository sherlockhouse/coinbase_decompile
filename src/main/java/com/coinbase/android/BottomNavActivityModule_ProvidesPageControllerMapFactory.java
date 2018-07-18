package com.coinbase.android;

import android.util.Pair;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import rx.functions.Func0;

public final class BottomNavActivityModule_ProvidesPageControllerMapFactory implements Factory<Map<Type, Integer>> {
    private final Provider<List<Pair<Type, Func0<ActionBarController>>>> controllersProvider;
    private final BottomNavActivityModule module;

    public BottomNavActivityModule_ProvidesPageControllerMapFactory(BottomNavActivityModule module, Provider<List<Pair<Type, Func0<ActionBarController>>>> controllersProvider) {
        this.module = module;
        this.controllersProvider = controllersProvider;
    }

    public Map<Type, Integer> get() {
        return provideInstance(this.module, this.controllersProvider);
    }

    public static Map<Type, Integer> provideInstance(BottomNavActivityModule module, Provider<List<Pair<Type, Func0<ActionBarController>>>> controllersProvider) {
        return proxyProvidesPageControllerMap(module, (List) controllersProvider.get());
    }

    public static BottomNavActivityModule_ProvidesPageControllerMapFactory create(BottomNavActivityModule module, Provider<List<Pair<Type, Func0<ActionBarController>>>> controllersProvider) {
        return new BottomNavActivityModule_ProvidesPageControllerMapFactory(module, controllersProvider);
    }

    public static Map<Type, Integer> proxyProvidesPageControllerMap(BottomNavActivityModule instance, List<Pair<Type, Func0<ActionBarController>>> controllers) {
        return (Map) Preconditions.checkNotNull(instance.providesPageControllerMap(controllers), "Cannot return null from a non-@Nullable @Provides method");
    }
}
