package rx.exceptions;

import java.io.PrintWriter;

final class CompositeException$WrappedPrintWriter extends CompositeException$PrintStreamOrWriter {
    private final PrintWriter printWriter;

    CompositeException$WrappedPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    Object lock() {
        return this.printWriter;
    }

    void println(Object o) {
        this.printWriter.println(o);
    }
}
