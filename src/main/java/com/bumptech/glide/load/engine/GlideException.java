package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GlideException extends Exception {
    private static final StackTraceElement[] EMPTY_ELEMENTS = new StackTraceElement[0];
    private final List<Exception> causes;
    private Class<?> dataClass;
    private DataSource dataSource;
    private Key key;

    private static final class IndentedAppendable implements Appendable {
        private final Appendable appendable;
        private boolean printedNewLine = true;

        IndentedAppendable(Appendable appendable) {
            this.appendable = appendable;
        }

        public Appendable append(char c) throws IOException {
            boolean z = false;
            if (this.printedNewLine) {
                this.printedNewLine = false;
                this.appendable.append("  ");
            }
            if (c == '\n') {
                z = true;
            }
            this.printedNewLine = z;
            this.appendable.append(c);
            return this;
        }

        public Appendable append(CharSequence charSequence) throws IOException {
            charSequence = safeSequence(charSequence);
            return append(charSequence, 0, charSequence.length());
        }

        public Appendable append(CharSequence charSequence, int start, int end) throws IOException {
            boolean z = false;
            charSequence = safeSequence(charSequence);
            if (this.printedNewLine) {
                this.printedNewLine = false;
                this.appendable.append("  ");
            }
            if (charSequence.length() > 0 && charSequence.charAt(end - 1) == '\n') {
                z = true;
            }
            this.printedNewLine = z;
            this.appendable.append(charSequence, start, end);
            return this;
        }

        private CharSequence safeSequence(CharSequence sequence) {
            if (sequence == null) {
                return "";
            }
            return sequence;
        }
    }

    public GlideException(String message) {
        this(message, Collections.emptyList());
    }

    public GlideException(String detailMessage, Exception cause) {
        this(detailMessage, Collections.singletonList(cause));
    }

    public GlideException(String detailMessage, List<Exception> causes) {
        super(detailMessage);
        setStackTrace(EMPTY_ELEMENTS);
        this.causes = causes;
    }

    void setLoggingDetails(Key key, DataSource dataSource) {
        setLoggingDetails(key, dataSource, null);
    }

    void setLoggingDetails(Key key, DataSource dataSource, Class<?> dataClass) {
        this.key = key;
        this.dataSource = dataSource;
        this.dataClass = dataClass;
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    public List<Exception> getCauses() {
        return this.causes;
    }

    public List<Exception> getRootCauses() {
        List<Exception> rootCauses = new ArrayList();
        addRootCauses(this, rootCauses);
        return rootCauses;
    }

    public void logRootCauses(String tag) {
        Log.e(tag, getClass() + ": " + getMessage());
        List<Exception> causes = getRootCauses();
        int size = causes.size();
        for (int i = 0; i < size; i++) {
            Log.i(tag, "Root cause (" + (i + 1) + " of " + size + ")", (Throwable) causes.get(i));
        }
    }

    private void addRootCauses(Exception exception, List<Exception> rootCauses) {
        if (exception instanceof GlideException) {
            for (Exception e : ((GlideException) exception).getCauses()) {
                addRootCauses(e, rootCauses);
            }
            return;
        }
        rootCauses.add(exception);
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream err) {
        printStackTrace((Appendable) err);
    }

    public void printStackTrace(PrintWriter err) {
        printStackTrace((Appendable) err);
    }

    private void printStackTrace(Appendable appendable) {
        appendExceptionMessage(this, appendable);
        appendCauses(getCauses(), new IndentedAppendable(appendable));
    }

    public String getMessage() {
        return super.getMessage() + (this.dataClass != null ? ", " + this.dataClass : "") + (this.dataSource != null ? ", " + this.dataSource : "") + (this.key != null ? ", " + this.key : "");
    }

    private static void appendExceptionMessage(Exception e, Appendable appendable) {
        try {
            appendable.append(e.getClass().toString()).append(": ").append(e.getMessage()).append('\n');
        } catch (IOException e2) {
            throw new RuntimeException(e);
        }
    }

    private static void appendCauses(List<Exception> causes, Appendable appendable) {
        try {
            appendCausesWrapped(causes, appendable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendCausesWrapped(List<Exception> causes, Appendable appendable) throws IOException {
        int size = causes.size();
        for (int i = 0; i < size; i++) {
            appendable.append("Cause (").append(String.valueOf(i + 1)).append(" of ").append(String.valueOf(size)).append("): ");
            Exception cause = (Exception) causes.get(i);
            if (cause instanceof GlideException) {
                ((GlideException) cause).printStackTrace(appendable);
            } else {
                appendExceptionMessage(cause, appendable);
            }
        }
    }
}
