package rx.exceptions;

import java.io.PrintStream;

final class CompositeException$WrappedPrintStream extends CompositeException$PrintStreamOrWriter {
    private final PrintStream printStream;

    CompositeException$WrappedPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    Object lock() {
        return this.printStream;
    }

    void println(Object o) {
        this.printStream.println(o);
    }
}
