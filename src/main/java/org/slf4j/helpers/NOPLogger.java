package org.slf4j.helpers;

public class NOPLogger extends MarkerIgnoringBase {
    public static final NOPLogger NOP_LOGGER = new NOPLogger();

    protected NOPLogger() {
    }

    public String getName() {
        return "NOP";
    }

    public final void error(String msg) {
    }

    public final void error(String msg, Throwable t) {
    }
}
