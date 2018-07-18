package org.slf4j.helpers;

import java.io.Serializable;
import org.slf4j.Logger;

abstract class NamedLoggerBase implements Serializable, Logger {
    protected String name;

    NamedLoggerBase() {
    }

    public String getName() {
        return this.name;
    }
}
