package org.slf4j.helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.event.EventRecodingLogger;
import org.slf4j.event.LoggingEvent;
import org.slf4j.event.SubstituteLoggingEvent;

public class SubstituteLogger implements Logger {
    private volatile Logger _delegate;
    private final boolean createdPostInitialization;
    private Boolean delegateEventAware;
    private Queue<SubstituteLoggingEvent> eventQueue;
    private EventRecodingLogger eventRecodingLogger;
    private Method logMethodCache;
    private final String name;

    public SubstituteLogger(String name, Queue<SubstituteLoggingEvent> eventQueue, boolean createdPostInitialization) {
        this.name = name;
        this.eventQueue = eventQueue;
        this.createdPostInitialization = createdPostInitialization;
    }

    public String getName() {
        return this.name;
    }

    public void error(String msg) {
        delegate().error(msg);
    }

    public void error(String msg, Throwable t) {
        delegate().error(msg, t);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.name.equals(((SubstituteLogger) o).name)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    Logger delegate() {
        if (this._delegate != null) {
            return this._delegate;
        }
        if (this.createdPostInitialization) {
            return NOPLogger.NOP_LOGGER;
        }
        return getEventRecordingLogger();
    }

    private Logger getEventRecordingLogger() {
        if (this.eventRecodingLogger == null) {
            this.eventRecodingLogger = new EventRecodingLogger(this, this.eventQueue);
        }
        return this.eventRecodingLogger;
    }

    public void setDelegate(Logger delegate) {
        this._delegate = delegate;
    }

    public boolean isDelegateEventAware() {
        if (this.delegateEventAware != null) {
            return this.delegateEventAware.booleanValue();
        }
        try {
            this.logMethodCache = this._delegate.getClass().getMethod("log", new Class[]{LoggingEvent.class});
            this.delegateEventAware = Boolean.TRUE;
        } catch (NoSuchMethodException e) {
            this.delegateEventAware = Boolean.FALSE;
        }
        return this.delegateEventAware.booleanValue();
    }

    public void log(LoggingEvent event) {
        if (isDelegateEventAware()) {
            try {
                this.logMethodCache.invoke(this._delegate, new Object[]{event});
            } catch (IllegalAccessException e) {
            } catch (IllegalArgumentException e2) {
            } catch (InvocationTargetException e3) {
            }
        }
    }

    public boolean isDelegateNull() {
        return this._delegate == null;
    }

    public boolean isDelegateNOP() {
        return this._delegate instanceof NOPLogger;
    }
}
