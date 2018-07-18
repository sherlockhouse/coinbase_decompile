package org.slf4j.event;

import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;

public class EventRecodingLogger implements Logger {
    Queue<SubstituteLoggingEvent> eventQueue;
    SubstituteLogger logger;
    String name;

    public EventRecodingLogger(SubstituteLogger logger, Queue<SubstituteLoggingEvent> eventQueue) {
        this.logger = logger;
        this.name = logger.getName();
        this.eventQueue = eventQueue;
    }

    public String getName() {
        return this.name;
    }

    private void recordEvent(Level level, String msg, Object[] args, Throwable throwable) {
        recordEvent(level, null, msg, args, throwable);
    }

    private void recordEvent(Level level, Marker marker, String msg, Object[] args, Throwable throwable) {
        SubstituteLoggingEvent loggingEvent = new SubstituteLoggingEvent();
        loggingEvent.setTimeStamp(System.currentTimeMillis());
        loggingEvent.setLevel(level);
        loggingEvent.setLogger(this.logger);
        loggingEvent.setLoggerName(this.name);
        loggingEvent.setMarker(marker);
        loggingEvent.setMessage(msg);
        loggingEvent.setArgumentArray(args);
        loggingEvent.setThrowable(throwable);
        loggingEvent.setThreadName(Thread.currentThread().getName());
        this.eventQueue.add(loggingEvent);
    }

    public void error(String msg) {
        recordEvent(Level.ERROR, msg, null, null);
    }

    public void error(String msg, Throwable t) {
        recordEvent(Level.ERROR, msg, null, t);
    }
}
