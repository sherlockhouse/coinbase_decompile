package org.slf4j.helpers;

import org.slf4j.Logger;

public abstract class MarkerIgnoringBase extends NamedLoggerBase implements Logger {
    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    public String toString() {
        return getClass().getName() + "(" + getName() + ")";
    }
}
