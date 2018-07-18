package rx.exceptions;

public class OnErrorFailedException extends RuntimeException {
    public OnErrorFailedException(String message, Throwable e) {
        if (e == null) {
            e = new NullPointerException();
        }
        super(message, e);
    }

    public OnErrorFailedException(Throwable e) {
        String message = e != null ? e.getMessage() : null;
        if (e == null) {
            e = new NullPointerException();
        }
        super(message, e);
    }
}
