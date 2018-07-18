package dagger.internal;

import javax.inject.Provider;

public final class DoubleCheck<T> implements Provider<T> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DoubleCheck.class.desiredAssertionStatus());
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private DoubleCheck(Provider<T> provider) {
        if ($assertionsDisabled || provider != null) {
            this.provider = provider;
            return;
        }
        throw new AssertionError();
    }

    public T get() {
        Object result = this.instance;
        if (result == UNINITIALIZED) {
            synchronized (this) {
                result = this.instance;
                if (result == UNINITIALIZED) {
                    result = this.provider.get();
                    this.instance = reentrantCheck(this.instance, result);
                    this.provider = null;
                }
            }
        }
        return result;
    }

    public static Object reentrantCheck(Object currentInstance, Object newInstance) {
        boolean isReentrant = (currentInstance == UNINITIALIZED || (currentInstance instanceof MemoizedSentinel)) ? false : true;
        if (!isReentrant || currentInstance == newInstance) {
            return newInstance;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + currentInstance + " & " + newInstance + ". This is likely due to a circular dependency.");
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P delegate) {
        Preconditions.checkNotNull(delegate);
        return delegate instanceof DoubleCheck ? delegate : new DoubleCheck(delegate);
    }
}
