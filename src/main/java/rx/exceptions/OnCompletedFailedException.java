package rx.exceptions;

public final class OnCompletedFailedException extends RuntimeException {
    public OnCompletedFailedException(String message, Throwable throwable) {
        if (throwable == null) {
            throwable = new NullPointerException();
        }
        super(message, throwable);
    }
}
