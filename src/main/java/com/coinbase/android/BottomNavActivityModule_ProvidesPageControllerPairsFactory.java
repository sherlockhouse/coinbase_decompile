package com.coinbase.android;

import android.util.Pair;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import rx.functions.Func0;

public final class BottomNavActivityModule_ProvidesPageControllerPairsFactory implements Factory<List<Pair<Type, Func0<ActionBarController>>>> {
    private final BottomNavActivityModule module;

    public BottomNavActivityModule_ProvidesPageControllerPairsFactory(BottomNavActivityModule module) {
        this.module = module;
    }

    public List<Pair<Type, Func0<ActionBarController>>> get() {
        return provideInstance(this.module);
    }

    public static List<Pair<Type, Func0<ActionBarController>>> provideInstance(BottomNavActivityModule module) {
        return proxyProvidesPageControllerPairs(module);
    }

    public static BottomNavActivityModule_ProvidesPageControllerPairsFactory create(BottomNavActivityModule module) {
        return new BottomNavActivityModule_ProvidesPageControllerPairsFactory(module);
    }

    public static List<Pair<Type, Func0<ActionBarController>>> proxyProvidesPageControllerPairs(BottomNavActivityModule instance) {
        return (List) Preconditions.checkNotNull(instance.providesPageControllerPairs(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
