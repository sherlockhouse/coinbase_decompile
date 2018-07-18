package dagger.internal;

public final class InstanceFactory<T> implements Factory<T> {
    private static final InstanceFactory<Object> NULL_INSTANCE_FACTORY = new InstanceFactory(null);
    private final T instance;

    public static <T> Factory<T> create(T instance) {
        return new InstanceFactory(Preconditions.checkNotNull(instance, "instance cannot be null"));
    }

    private InstanceFactory(T instance) {
        this.instance = instance;
    }

    public T get() {
        return this.instance;
    }
}
