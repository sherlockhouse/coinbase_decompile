package rx.plugins;

import rx.exceptions.Exceptions;

public abstract class RxJavaErrorHandler {
    @Deprecated
    public void handleError(Throwable e) {
    }

    public final String handleOnNextValueRendering(Object item) {
        try {
            return render(item);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return item.getClass().getName() + ".errorRendering";
        } catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            return item.getClass().getName() + ".errorRendering";
        }
    }

    protected String render(Object item) throws InterruptedException {
        return null;
    }
}
