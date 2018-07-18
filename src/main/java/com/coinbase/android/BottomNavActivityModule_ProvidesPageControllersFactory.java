package com.coinbase.android;

import android.util.Pair;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;
import rx.functions.Func0;

public final class BottomNavActivityModule_ProvidesPageControllersFactory implements Factory<List<Func0<ActionBarController>>> {
    private final Provider<List<Pair<Type, Func0<ActionBarController>>>> controllerPairsProvider;
    private final BottomNavActivityModule module;

    public BottomNavActivityModule_ProvidesPageControllersFactory(BottomNavActivityModule module, Provider<List<Pair<Type, Func0<ActionBarController>>>> controllerPairsProvider) {
        this.module = module;
        this.controllerPairsProvider = controllerPairsProvider;
    }

    public List<Func0<ActionBarController>> get() {
        return provideInstance(this.module, this.controllerPairsProvider);
    }

    public static List<Func0<ActionBarController>> provideInstance(BottomNavActivityModule module, Provider<List<Pair<Type, Func0<ActionBarController>>>> controllerPairsProvider) {
        return proxyProvidesPageControllers(module, (List) controllerPairsProvider.get());
    }

    public static BottomNavActivityModule_ProvidesPageControllersFactory create(BottomNavActivityModule module, Provider<List<Pair<Type, Func0<ActionBarController>>>> controllerPairsProvider) {
        return new BottomNavActivityModule_ProvidesPageControllersFactory(module, controllerPairsProvider);
    }

    public static List<Func0<ActionBarController>> proxyProvidesPageControllers(BottomNavActivityModule instance, List<Pair<Type, Func0<ActionBarController>>> controllerPairs) {
        return (List) Preconditions.checkNotNull(instance.providesPageControllers(controllerPairs), "Cannot return null from a non-@Nullable @Provides method");
    }
}
