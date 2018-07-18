package rx;

public final class Notification<T> {
    private static final Notification<Void> ON_COMPLETED = new Notification(Kind.OnCompleted, null, null);
    private final Kind kind;
    private final Throwable throwable;
    private final T value;

    public enum Kind {
        OnNext,
        OnError,
        OnCompleted
    }

    private Notification(Kind kind, T value, Throwable e) {
        this.value = value;
        this.throwable = e;
        this.kind = kind;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public T getValue() {
        return this.value;
    }

    public boolean hasValue() {
        return isOnNext() && this.value != null;
    }

    public boolean hasThrowable() {
        return isOnError() && this.throwable != null;
    }

    public Kind getKind() {
        return this.kind;
    }

    public boolean isOnError() {
        return getKind() == Kind.OnError;
    }

    public boolean isOnNext() {
        return getKind() == Kind.OnNext;
    }

    public String toString() {
        StringBuilder str = new StringBuilder(64).append('[').append(super.toString()).append(' ').append(getKind());
        if (hasValue()) {
            str.append(' ').append(getValue());
        }
        if (hasThrowable()) {
            str.append(' ').append(getThrowable().getMessage());
        }
        str.append(']');
        return str.toString();
    }

    public int hashCode() {
        int hash = getKind().hashCode();
        if (hasValue()) {
            hash = (hash * 31) + getValue().hashCode();
        }
        if (hasThrowable()) {
            return (hash * 31) + getThrowable().hashCode();
        }
        return hash;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Notification<?> notification = (Notification) obj;
        if (notification.getKind() != getKind() || ((this.value != notification.value && (this.value == null || !this.value.equals(notification.value))) || (this.throwable != notification.throwable && (this.throwable == null || !this.throwable.equals(notification.throwable))))) {
            z = false;
        }
        return z;
    }
}
