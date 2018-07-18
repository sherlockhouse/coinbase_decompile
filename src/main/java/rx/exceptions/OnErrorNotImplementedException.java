package rx.exceptions;

public class OnErrorNotImplementedException extends RuntimeException {
    public OnErrorNotImplementedException(String message, Throwable e) {
        if (e == null) {
            e = new NullPointerException();
        }
        super(message, e);
    }

    public OnErrorNotImplementedException(Throwable e) {
        String message = e != null ? e.getMessage() : null;
        if (e == null) {
            e = new NullPointerException();
        }
        super(message, e);
    }
}
