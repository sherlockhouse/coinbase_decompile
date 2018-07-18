package com.coinbase.android;

import android.os.Bundle;
import android.util.Pair;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ModalBottomNavigationItem.Type;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import rx.functions.Func1;

public final class BottomNavActivityModule_ProvidesModalControllerFuncsFactory implements Factory<List<Pair<Type, Func1<Bundle, ActionBarController>>>> {
    private final BottomNavActivityModule module;

    public BottomNavActivityModule_ProvidesModalControllerFuncsFactory(BottomNavActivityModule module) {
        this.module = module;
    }

    public List<Pair<Type, Func1<Bundle, ActionBarController>>> get() {
        return provideInstance(this.module);
    }

    public static List<Pair<Type, Func1<Bundle, ActionBarController>>> provideInstance(BottomNavActivityModule module) {
        return proxyProvidesModalControllerFuncs(module);
    }

    public static BottomNavActivityModule_ProvidesModalControllerFuncsFactory create(BottomNavActivityModule module) {
        return new BottomNavActivityModule_ProvidesModalControllerFuncsFactory(module);
    }

    public static List<Pair<Type, Func1<Bundle, ActionBarController>>> proxyProvidesModalControllerFuncs(BottomNavActivityModule instance) {
        return (List) Preconditions.checkNotNull(instance.providesModalControllerFuncs(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
