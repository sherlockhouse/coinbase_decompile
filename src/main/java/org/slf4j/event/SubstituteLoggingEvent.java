package org.slf4j.event;

import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;

public class SubstituteLoggingEvent implements LoggingEvent {
    Object[] argArray;
    Level level;
    SubstituteLogger logger;
    String loggerName;
    Marker marker;
    String message;
    String threadName;
    Throwable throwable;
    long timeStamp;

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public SubstituteLogger getLogger() {
        return this.logger;
    }

    public void setLogger(SubstituteLogger logger) {
        this.logger = logger;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setArgumentArray(Object[] argArray) {
        this.argArray = argArray;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
