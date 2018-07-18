package org.slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.event.SubstituteLoggingEvent;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.helpers.SubstituteLogger;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

public final class LoggerFactory {
    private static final String[] API_COMPATIBILITY_LIST = new String[]{"1.6", "1.7"};
    static boolean DETECT_LOGGER_NAME_MISMATCH = Util.safeGetBooleanSystemProperty("slf4j.detectLoggerNameMismatch");
    static volatile int INITIALIZATION_STATE = 0;
    static final NOPLoggerFactory NOP_FALLBACK_FACTORY = new NOPLoggerFactory();
    private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
    static final SubstituteLoggerFactory SUBST_FACTORY = new SubstituteLoggerFactory();

    private LoggerFactory() {
    }

    private static final void performInitialization() {
        bind();
        if (INITIALIZATION_STATE == 3) {
            versionSanityCheck();
        }
    }

    private static boolean messageContainsOrgSlf4jImplStaticLoggerBinder(String msg) {
        if (msg == null) {
            return false;
        }
        if (msg.contains("org/slf4j/impl/StaticLoggerBinder")) {
            return true;
        }
        if (msg.contains("org.slf4j.impl.StaticLoggerBinder")) {
            return true;
        }
        return false;
    }

    private static final void bind() {
        Set<URL> staticLoggerBinderPathSet = null;
        try {
            if (!isAndroid()) {
                staticLoggerBinderPathSet = findPossibleStaticLoggerBinderPathSet();
                reportMultipleBindingAmbiguity(staticLoggerBinderPathSet);
            }
            StaticLoggerBinder.getSingleton();
            INITIALIZATION_STATE = 3;
            reportActualBinding(staticLoggerBinderPathSet);
            fixSubstituteLoggers();
            replayEvents();
            SUBST_FACTORY.clear();
        } catch (NoClassDefFoundError ncde) {
            if (messageContainsOrgSlf4jImplStaticLoggerBinder(ncde.getMessage())) {
                INITIALIZATION_STATE = 4;
                Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                Util.report("Defaulting to no-operation (NOP) logger implementation");
                Util.report("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
                return;
            }
            failedBinding(ncde);
            throw ncde;
        } catch (NoSuchMethodError nsme) {
            String msg = nsme.getMessage();
            if (msg != null && msg.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
                INITIALIZATION_STATE = 2;
                Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
                Util.report("Your binding is version 1.5.5 or earlier.");
                Util.report("Upgrade your binding to version 1.6.x.");
            }
            throw nsme;
        } catch (Exception e) {
            failedBinding(e);
            throw new IllegalStateException("Unexpected initialization failure", e);
        }
    }

    private static void fixSubstituteLoggers() {
        synchronized (SUBST_FACTORY) {
            SUBST_FACTORY.postInitialization();
            for (SubstituteLogger substLogger : SUBST_FACTORY.getLoggers()) {
                substLogger.setDelegate(getLogger(substLogger.getName()));
            }
        }
    }

    static void failedBinding(Throwable t) {
        INITIALIZATION_STATE = 2;
        Util.report("Failed to instantiate SLF4J LoggerFactory", t);
    }

    private static void replayEvents() {
        LinkedBlockingQueue<SubstituteLoggingEvent> queue = SUBST_FACTORY.getEventQueue();
        int queueSize = queue.size();
        int count = 0;
        List<SubstituteLoggingEvent> eventList = new ArrayList(128);
        while (queue.drainTo(eventList, 128) != 0) {
            for (SubstituteLoggingEvent event : eventList) {
                replaySingleEvent(event);
                int count2 = count + 1;
                if (count == 0) {
                    emitReplayOrSubstituionWarning(event, queueSize);
                }
                count = count2;
            }
            eventList.clear();
        }
    }

    private static void emitReplayOrSubstituionWarning(SubstituteLoggingEvent event, int queueSize) {
        if (event.getLogger().isDelegateEventAware()) {
            emitReplayWarning(queueSize);
        } else if (!event.getLogger().isDelegateNOP()) {
            emitSubstitutionWarning();
        }
    }

    private static void replaySingleEvent(SubstituteLoggingEvent event) {
        if (event != null) {
            SubstituteLogger substLogger = event.getLogger();
            String loggerName = substLogger.getName();
            if (substLogger.isDelegateNull()) {
                throw new IllegalStateException("Delegate logger cannot be null at this state.");
            } else if (!substLogger.isDelegateNOP()) {
                if (substLogger.isDelegateEventAware()) {
                    substLogger.log(event);
                } else {
                    Util.report(loggerName);
                }
            }
        }
    }

    private static void emitSubstitutionWarning() {
        Util.report("The following set of substitute loggers may have been accessed");
        Util.report("during the initialization phase. Logging calls during this");
        Util.report("phase were not honored. However, subsequent logging calls to these");
        Util.report("loggers will work as normally expected.");
        Util.report("See also http://www.slf4j.org/codes.html#substituteLogger");
    }

    private static void emitReplayWarning(int eventCount) {
        Util.report("A number (" + eventCount + ") of logging calls during the initialization phase have been intercepted and are");
        Util.report("now being replayed. These are subject to the filtering rules of the underlying logging system.");
        Util.report("See also http://www.slf4j.org/codes.html#replay");
    }

    private static final void versionSanityCheck() {
        try {
            String requested = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean match = false;
            for (String aAPI_COMPATIBILITY_LIST : API_COMPATIBILITY_LIST) {
                if (requested.startsWith(aAPI_COMPATIBILITY_LIST)) {
                    match = true;
                }
            }
            if (!match) {
                Util.report("The requested version " + requested + " by your slf4j binding is not compatible with " + Arrays.asList(API_COMPATIBILITY_LIST).toString());
                Util.report("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
            }
        } catch (NoSuchFieldError e) {
        } catch (Throwable e2) {
            Util.report("Unexpected problem occured during version sanity check", e2);
        }
    }

    static Set<URL> findPossibleStaticLoggerBinderPathSet() {
        Set<URL> staticLoggerBinderPathSet = new LinkedHashSet();
        try {
            Enumeration<URL> paths;
            ClassLoader loggerFactoryClassLoader = LoggerFactory.class.getClassLoader();
            if (loggerFactoryClassLoader == null) {
                paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
            } else {
                paths = loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
            }
            while (paths.hasMoreElements()) {
                staticLoggerBinderPathSet.add((URL) paths.nextElement());
            }
        } catch (IOException ioe) {
            Util.report("Error getting resources from path", ioe);
        }
        return staticLoggerBinderPathSet;
    }

    private static boolean isAmbiguousStaticLoggerBinderPathSet(Set<URL> binderPathSet) {
        return binderPathSet.size() > 1;
    }

    private static void reportMultipleBindingAmbiguity(Set<URL> binderPathSet) {
        if (isAmbiguousStaticLoggerBinderPathSet(binderPathSet)) {
            Util.report("Class path contains multiple SLF4J bindings.");
            for (URL path : binderPathSet) {
                Util.report("Found binding in [" + path + "]");
            }
            Util.report("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    private static boolean isAndroid() {
        String vendor = Util.safeGetSystemProperty("java.vendor.url");
        if (vendor == null) {
            return false;
        }
        return vendor.toLowerCase().contains("android");
    }

    private static void reportActualBinding(Set<URL> binderPathSet) {
        if (binderPathSet != null && isAmbiguousStaticLoggerBinderPathSet(binderPathSet)) {
            Util.report("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
        }
    }

    public static Logger getLogger(String name) {
        return getILoggerFactory().getLogger(name);
    }

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = getLogger(clazz.getName());
        if (DETECT_LOGGER_NAME_MISMATCH) {
            Class<?> autoComputedCallingClass = Util.getCallingClass();
            if (autoComputedCallingClass != null && nonMatchingClasses(clazz, autoComputedCallingClass)) {
                Util.report(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", new Object[]{logger.getName(), autoComputedCallingClass.getName()}));
                Util.report("See http://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
            }
        }
        return logger;
    }

    private static boolean nonMatchingClasses(Class<?> clazz, Class<?> autoComputedCallingClass) {
        return !autoComputedCallingClass.isAssignableFrom(clazz);
    }

    public static ILoggerFactory getILoggerFactory() {
        if (INITIALIZATION_STATE == 0) {
            synchronized (LoggerFactory.class) {
                if (INITIALIZATION_STATE == 0) {
                    INITIALIZATION_STATE = 1;
                    performInitialization();
                }
            }
        }
        switch (INITIALIZATION_STATE) {
            case 1:
                return SUBST_FACTORY;
            case 2:
                throw new IllegalStateException("org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
            case 3:
                return StaticLoggerBinder.getSingleton().getLoggerFactory();
            case 4:
                return NOP_FALLBACK_FACTORY;
            default:
                throw new IllegalStateException("Unreachable code");
        }
    }
}
