package com.crashlytics.android.core;

import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.AppMeasurementEventLogger;
import com.crashlytics.android.answers.EventLogger;
import com.crashlytics.android.core.LogFileManager.DirectoryProvider;
import com.crashlytics.android.core.internal.models.SessionEventData;
import com.google.android.gms.measurement.AppMeasurement;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStore;
import io.fabric.sdk.android.services.settings.PromptSettingsData;
import io.fabric.sdk.android.services.settings.SessionSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

class CrashlyticsController {
    private static final String[] INITIAL_SESSION_PART_TAGS = new String[]{"SessionUser", "SessionApp", "SessionOS", "SessionDevice"};
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file1, File file2) {
            return file2.getName().compareTo(file1.getName());
        }
    };
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    static final FilenameFilter SESSION_BEGIN_FILE_FILTER = new FileNameContainsFilter("BeginSession") {
        public boolean accept(File dir, String filename) {
            return super.accept(dir, filename) && filename.endsWith(".cls");
        }
    };
    static final FileFilter SESSION_DIRECTORY_FILTER = new FileFilter() {
        public boolean accept(File file) {
            return file.isDirectory() && file.getName().length() == 35;
        }
    };
    static final FilenameFilter SESSION_FILE_FILTER = new FilenameFilter() {
        public boolean accept(File dir, String filename) {
            return filename.length() == ".cls".length() + 35 && filename.endsWith(".cls");
        }
    };
    private static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file1, File file2) {
            return file1.getName().compareTo(file2.getName());
        }
    };
    private final AppData appData;
    private final CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    private final CrashlyticsCore crashlyticsCore;
    private final DevicePowerStateListener devicePowerStateListener;
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final FileStore fileStore;
    private final EventLogger firebaseAnalytics;
    private final boolean firebaseCrashlyticsEnabled;
    private final HandlingExceptionCheck handlingExceptionCheck;
    private final HttpRequestFactory httpRequestFactory;
    private final IdManager idManager;
    private final LogFileDirectoryProvider logFileDirectoryProvider;
    private final LogFileManager logFileManager;
    private final PreferenceManager preferenceManager;
    private final ReportFilesProvider reportFilesProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
    private final String unityVersion;

    private interface CodedOutputStreamWriteAction {
        void writeTo(CodedOutputStream codedOutputStream) throws Exception;
    }

    private interface FileOutputStreamWriteAction {
        void writeTo(FileOutputStream fileOutputStream) throws Exception;
    }

    static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String s) {
            this.string = s;
        }

        public boolean accept(File dir, String filename) {
            return filename.contains(this.string) && !filename.endsWith(".cls_temp");
        }
    }

    private static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        public boolean accept(File file, String fileName) {
            return !CrashlyticsController.SESSION_FILE_FILTER.accept(file, fileName) && CrashlyticsController.SESSION_FILE_PATTERN.matcher(fileName).matches();
        }
    }

    static class InvalidPartFileFilter implements FilenameFilter {
        InvalidPartFileFilter() {
        }

        public boolean accept(File file, String fileName) {
            return ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(file, fileName) || fileName.contains("SessionMissingBinaryImages");
        }
    }

    private static final class LogFileDirectoryProvider implements DirectoryProvider {
        private final FileStore rootFileStore;

        public LogFileDirectoryProvider(FileStore rootFileStore) {
            this.rootFileStore = rootFileStore;
        }

        public File getLogFileDir() {
            File logFileDir = new File(this.rootFileStore.getFilesDir(), "log-files");
            if (!logFileDir.exists()) {
                logFileDir.mkdirs();
            }
            return logFileDir;
        }
    }

    private static final class PrivacyDialogCheck implements SendCheck {
        private final Kit kit;
        private final PreferenceManager preferenceManager;
        private final PromptSettingsData promptData;

        public PrivacyDialogCheck(Kit kit, PreferenceManager preferenceManager, PromptSettingsData promptData) {
            this.kit = kit;
            this.preferenceManager = preferenceManager;
            this.promptData = promptData;
        }

        public boolean canSendReports() {
            Activity activity = this.kit.getFabric().getCurrentActivity();
            if (activity == null || activity.isFinishing()) {
                return true;
            }
            final CrashPromptDialog dialog = CrashPromptDialog.create(activity, this.promptData, new AlwaysSendCallback() {
                public void sendUserReportsWithoutPrompting(boolean send) {
                    PrivacyDialogCheck.this.preferenceManager.setShouldAlwaysSendReports(send);
                }
            });
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    dialog.show();
                }
            });
            Fabric.getLogger().d("CrashlyticsCore", "Waiting for user opt-in.");
            dialog.await();
            return dialog.getOptIn();
        }
    }

    private final class ReportUploaderFilesProvider implements ReportFilesProvider {
        private ReportUploaderFilesProvider() {
        }

        public File[] getCompleteSessionFiles() {
            return CrashlyticsController.this.listCompleteSessionFiles();
        }

        public File[] getInvalidSessionFiles() {
            return CrashlyticsController.this.getInvalidFilesDir().listFiles();
        }
    }

    private final class ReportUploaderHandlingExceptionCheck implements HandlingExceptionCheck {
        private ReportUploaderHandlingExceptionCheck() {
        }

        public boolean isHandlingException() {
            return CrashlyticsController.this.isHandlingException();
        }
    }

    private static final class SendReportRunnable implements Runnable {
        private final Context context;
        private final Report report;
        private final ReportUploader reportUploader;

        public SendReportRunnable(Context context, Report report, ReportUploader reportUploader) {
            this.context = context;
            this.report = report;
            this.reportUploader = reportUploader;
        }

        public void run() {
            if (CommonUtils.canTryConnection(this.context)) {
                Fabric.getLogger().d("CrashlyticsCore", "Attempting to send crash report at time of crash...");
                this.reportUploader.forceUpload(this.report);
            }
        }
    }

    static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String sessionId) {
            this.sessionId = sessionId;
        }

        public boolean accept(File file, String fileName) {
            if (fileName.equals(this.sessionId + ".cls") || !fileName.contains(this.sessionId) || fileName.endsWith(".cls_temp")) {
                return false;
            }
            return true;
        }
    }

    CrashlyticsController(CrashlyticsCore crashlyticsCore, CrashlyticsBackgroundWorker backgroundWorker, HttpRequestFactory httpRequestFactory, IdManager idManager, PreferenceManager preferenceManager, FileStore fileStore, AppData appData, UnityVersionProvider unityVersionProvider, boolean firebaseCrashlyticsEnabled) {
        this.crashlyticsCore = crashlyticsCore;
        this.backgroundWorker = backgroundWorker;
        this.httpRequestFactory = httpRequestFactory;
        this.idManager = idManager;
        this.preferenceManager = preferenceManager;
        this.fileStore = fileStore;
        this.appData = appData;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        this.firebaseCrashlyticsEnabled = firebaseCrashlyticsEnabled;
        Context context = crashlyticsCore.getContext();
        this.logFileDirectoryProvider = new LogFileDirectoryProvider(fileStore);
        this.logFileManager = new LogFileManager(context, this.logFileDirectoryProvider);
        this.reportFilesProvider = new ReportUploaderFilesProvider();
        this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck();
        this.devicePowerStateListener = new DevicePowerStateListener(context);
        this.stackTraceTrimmingStrategy = new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10));
        this.firebaseAnalytics = AppMeasurementEventLogger.getEventLogger(context);
    }

    void enableExceptionHandling(UncaughtExceptionHandler defaultHandler) {
        openSession();
        this.crashHandler = new CrashlyticsUncaughtExceptionHandler(new CrashListener() {
            public void onUncaughtException(Thread thread, Throwable ex) {
                CrashlyticsController.this.handleUncaughtException(thread, ex);
            }
        }, defaultHandler);
        Thread.setDefaultUncaughtExceptionHandler(this.crashHandler);
    }

    synchronized void handleUncaughtException(final Thread thread, final Throwable ex) {
        Fabric.getLogger().d("CrashlyticsCore", "Crashlytics is handling uncaught exception \"" + ex + "\" from thread " + thread.getName());
        this.devicePowerStateListener.dispose();
        final Date time = new Date();
        this.backgroundWorker.submitAndWait(new Callable<Void>() {
            public Void call() throws Exception {
                SessionSettingsData sessionSettings;
                CrashlyticsController.this.crashlyticsCore.createCrashMarker();
                CrashlyticsController.this.writeFatal(time, thread, ex);
                SettingsData settingsData = Settings.getInstance().awaitSettingsData();
                if (settingsData != null) {
                    sessionSettings = settingsData.sessionData;
                } else {
                    sessionSettings = null;
                }
                CrashlyticsController.this.doCloseSessions(sessionSettings);
                CrashlyticsController.this.doOpenSession();
                if (sessionSettings != null) {
                    CrashlyticsController.this.trimSessionFiles(sessionSettings.maxCompleteSessionsCount);
                }
                if (!CrashlyticsController.this.shouldPromptUserBeforeSendingCrashReports(settingsData)) {
                    CrashlyticsController.this.sendSessionReports(settingsData);
                }
                return null;
            }
        });
    }

    void submitAllReports(float delay, SettingsData settingsData) {
        if (settingsData == null) {
            Fabric.getLogger().w("CrashlyticsCore", "Could not send reports. Settings are not available.");
            return;
        }
        new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(settingsData.appData.reportsUrl), this.reportFilesProvider, this.handlingExceptionCheck).uploadReports(delay, shouldPromptUserBeforeSendingCrashReports(settingsData) ? new PrivacyDialogCheck(this.crashlyticsCore, this.preferenceManager, settingsData.promptData) : new AlwaysSendCheck());
    }

    void writeNonFatalException(final Thread thread, final Throwable ex) {
        final Date now = new Date();
        this.backgroundWorker.submit(new Runnable() {
            public void run() {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.doWriteNonFatal(now, thread, ex);
                }
            }
        });
    }

    void cacheUserData(final String userId, final String userName, final String userEmail) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeUserData(CrashlyticsController.this.getCurrentSessionId(), new UserMetaData(userId, userName, userEmail));
                return null;
            }
        });
    }

    void cacheKeyData(final Map<String, String> keyData) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeKeyData(CrashlyticsController.this.getCurrentSessionId(), keyData);
                return null;
            }
        });
    }

    void openSession() {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                CrashlyticsController.this.doOpenSession();
                return null;
            }
        });
    }

    private String getCurrentSessionId() {
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        return sessionBeginFiles.length > 0 ? getSessionIdFromSessionFile(sessionBeginFiles[0]) : null;
    }

    private String getPreviousSessionId() {
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        return sessionBeginFiles.length > 1 ? getSessionIdFromSessionFile(sessionBeginFiles[1]) : null;
    }

    static String getSessionIdFromSessionFile(File sessionFile) {
        return sessionFile.getName().substring(0, 35);
    }

    boolean finalizeSessions(final SessionSettingsData sessionSettingsData) {
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                if (CrashlyticsController.this.isHandlingException()) {
                    Fabric.getLogger().d("CrashlyticsCore", "Skipping session finalization because a crash has already occurred.");
                    return Boolean.FALSE;
                }
                Fabric.getLogger().d("CrashlyticsCore", "Finalizing previously open sessions.");
                CrashlyticsController.this.doCloseSessions(sessionSettingsData, true);
                Fabric.getLogger().d("CrashlyticsCore", "Closed all previously open sessions");
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    private void doOpenSession() throws Exception {
        Date startedAt = new Date();
        String sessionIdentifier = new CLSUUID(this.idManager).toString();
        Fabric.getLogger().d("CrashlyticsCore", "Opening a new session with ID " + sessionIdentifier);
        writeBeginSession(sessionIdentifier, startedAt);
        writeSessionApp(sessionIdentifier);
        writeSessionOS(sessionIdentifier);
        writeSessionDevice(sessionIdentifier);
        this.logFileManager.setCurrentSession(sessionIdentifier);
    }

    void doCloseSessions(SessionSettingsData sessionSettingsData) throws Exception {
        doCloseSessions(sessionSettingsData, false);
    }

    private void doCloseSessions(SessionSettingsData sessionSettingsData, boolean excludeCurrent) throws Exception {
        int offset = excludeCurrent ? 1 : 0;
        trimOpenSessions(offset + 8);
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        if (sessionBeginFiles.length <= offset) {
            Fabric.getLogger().d("CrashlyticsCore", "No open sessions to be closed.");
            return;
        }
        writeSessionUser(getSessionIdFromSessionFile(sessionBeginFiles[offset]));
        if (sessionSettingsData == null) {
            Fabric.getLogger().d("CrashlyticsCore", "Unable to close session. Settings are not loaded.");
        } else {
            closeOpenSessions(sessionBeginFiles, offset, sessionSettingsData.maxCustomExceptionEvents);
        }
    }

    private void closeOpenSessions(File[] sessionBeginFiles, int beginIndex, int maxLoggedExceptionsCount) {
        Fabric.getLogger().d("CrashlyticsCore", "Closing open sessions.");
        for (int i = beginIndex; i < sessionBeginFiles.length; i++) {
            File sessionBeginFile = sessionBeginFiles[i];
            String sessionIdentifier = getSessionIdFromSessionFile(sessionBeginFile);
            Fabric.getLogger().d("CrashlyticsCore", "Closing session: " + sessionIdentifier);
            writeSessionPartsToSessionFile(sessionBeginFile, sessionIdentifier, maxLoggedExceptionsCount);
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream fos) {
        if (fos != null) {
            try {
                fos.closeInProgressStream();
            } catch (IOException ex) {
                Fabric.getLogger().e("CrashlyticsCore", "Error closing session file stream in the presence of an exception", ex);
            }
        }
    }

    private void deleteSessionPartFilesFor(String sessionId) {
        for (File file : listSessionPartFilesFor(sessionId)) {
            file.delete();
        }
    }

    private File[] listSessionPartFilesFor(String sessionId) {
        return listFilesMatching(new SessionPartFileFilter(sessionId));
    }

    File[] listCompleteSessionFiles() {
        List<File> completeSessionFiles = new LinkedList();
        Collections.addAll(completeSessionFiles, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(completeSessionFiles, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(completeSessionFiles, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
        return (File[]) completeSessionFiles.toArray(new File[completeSessionFiles.size()]);
    }

    File[] listSessionBeginFiles() {
        return listFilesMatching(SESSION_BEGIN_FILE_FILTER);
    }

    private File[] listSortedSessionBeginFiles() {
        File[] sessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(sessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return sessionBeginFiles;
    }

    private File[] listFilesMatching(FilenameFilter filter) {
        return listFilesMatching(getFilesDir(), filter);
    }

    private File[] listFilesMatching(File directory, FilenameFilter filter) {
        return ensureFileArrayNotNull(directory.listFiles(filter));
    }

    private File[] listFiles(File directory) {
        return ensureFileArrayNotNull(directory.listFiles());
    }

    private File[] ensureFileArrayNotNull(File[] files) {
        return files == null ? new File[0] : files;
    }

    private void trimSessionEventFiles(String sessionId, int limit) {
        Utils.capFileCount(getFilesDir(), new FileNameContainsFilter(sessionId + "SessionEvent"), limit, SMALLEST_FILE_NAME_FIRST);
    }

    void trimSessionFiles(int maxCompleteSessionsCount) {
        int remaining = maxCompleteSessionsCount;
        remaining -= Utils.capFileCount(getFatalSessionFilesDir(), remaining, SMALLEST_FILE_NAME_FIRST);
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, remaining - Utils.capFileCount(getNonFatalSessionFilesDir(), remaining, SMALLEST_FILE_NAME_FIRST), SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int maxOpenSessionCount) {
        Set<String> sessionIdsToKeep = new HashSet();
        File[] beginSessionFiles = listSortedSessionBeginFiles();
        int count = Math.min(maxOpenSessionCount, beginSessionFiles.length);
        for (int i = 0; i < count; i++) {
            sessionIdsToKeep.add(getSessionIdFromSessionFile(beginSessionFiles[i]));
        }
        this.logFileManager.discardOldLogFiles(sessionIdsToKeep);
        retainSessions(listFilesMatching(new AnySessionPartFileFilter()), sessionIdsToKeep);
    }

    private void retainSessions(File[] files, Set<String> sessionIdsToKeep) {
        for (File sessionPartFile : files) {
            String fileName = sessionPartFile.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(fileName);
            if (!matcher.matches()) {
                Fabric.getLogger().d("CrashlyticsCore", "Deleting unknown file: " + fileName);
                sessionPartFile.delete();
            } else if (!sessionIdsToKeep.contains(matcher.group(1))) {
                Fabric.getLogger().d("CrashlyticsCore", "Trimming session file: " + fileName);
                sessionPartFile.delete();
            }
        }
    }

    private File[] getTrimmedNonFatalFiles(String sessionId, File[] nonFatalFiles, int maxLoggedExceptionsCount) {
        if (nonFatalFiles.length <= maxLoggedExceptionsCount) {
            return nonFatalFiles;
        }
        Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(maxLoggedExceptionsCount)}));
        trimSessionEventFiles(sessionId, maxLoggedExceptionsCount);
        return listFilesMatching(new FileNameContainsFilter(sessionId + "SessionEvent"));
    }

    void cleanInvalidTempFiles() {
        this.backgroundWorker.submit(new Runnable() {
            public void run() {
                CrashlyticsController.this.doCleanInvalidTempFiles(CrashlyticsController.this.listFilesMatching(new InvalidPartFileFilter()));
            }
        });
    }

    void doCleanInvalidTempFiles(File[] invalidFiles) {
        int length;
        int i = 0;
        final Set<String> invalidSessionIds = new HashSet();
        for (File invalidFile : invalidFiles) {
            Fabric.getLogger().d("CrashlyticsCore", "Found invalid session part file: " + invalidFile);
            invalidSessionIds.add(getSessionIdFromSessionFile(invalidFile));
        }
        if (!invalidSessionIds.isEmpty()) {
            File invalidFilesDir = getInvalidFilesDir();
            if (!invalidFilesDir.exists()) {
                invalidFilesDir.mkdir();
            }
            File[] listFilesMatching = listFilesMatching(new FilenameFilter() {
                public boolean accept(File dir, String filename) {
                    if (filename.length() < 35) {
                        return false;
                    }
                    return invalidSessionIds.contains(filename.substring(0, 35));
                }
            });
            length = listFilesMatching.length;
            while (i < length) {
                File sessionFile = listFilesMatching[i];
                Fabric.getLogger().d("CrashlyticsCore", "Moving session file: " + sessionFile);
                if (!sessionFile.renameTo(new File(invalidFilesDir, sessionFile.getName()))) {
                    Fabric.getLogger().d("CrashlyticsCore", "Could not move session file. Deleting " + sessionFile);
                    sessionFile.delete();
                }
                i++;
            }
            trimInvalidSessionFiles();
        }
    }

    private void trimInvalidSessionFiles() {
        File invalidFilesDir = getInvalidFilesDir();
        if (invalidFilesDir.exists()) {
            File[] oldInvalidFiles = listFilesMatching(invalidFilesDir, new InvalidPartFileFilter());
            Arrays.sort(oldInvalidFiles, Collections.reverseOrder());
            Set<String> sessionIdsToKeep = new HashSet();
            for (int i = 0; i < oldInvalidFiles.length && sessionIdsToKeep.size() < 4; i++) {
                sessionIdsToKeep.add(getSessionIdFromSessionFile(oldInvalidFiles[i]));
            }
            retainSessions(listFiles(invalidFilesDir), sessionIdsToKeep);
        }
    }

    private void writeFatal(Date time, Thread thread, Throwable ex) {
        Exception e;
        Throwable th;
        ClsFileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            String currentSessionId = getCurrentSessionId();
            if (currentSessionId == null) {
                Fabric.getLogger().e("CrashlyticsCore", "Tried to write a fatal exception while no session was open.", null);
                CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
                return;
            }
            recordFatalExceptionAnswersEvent(currentSessionId, ex.getClass().getName());
            recordFatalFirebaseEvent(time.getTime());
            ClsFileOutputStream fos2 = new ClsFileOutputStream(getFilesDir(), currentSessionId + "SessionCrash");
            try {
                cos = CodedOutputStream.newInstance(fos2);
                writeSessionEvent(cos, time, thread, ex, AppMeasurement.CRASH_ORIGIN, true);
                CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(fos2, "Failed to close fatal exception file output stream.");
                fos = fos2;
            } catch (Exception e2) {
                e = e2;
                fos = fos2;
                try {
                    Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the fatal exception logger", e);
                    CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fos2;
                CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the fatal exception logger", e);
            CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
        }
    }

    void writeExternalCrashEvent(final SessionEventData crashEventData) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.doWriteExternalCrashEvent(crashEventData);
                }
                return null;
            }
        });
    }

    private void doWriteExternalCrashEvent(SessionEventData crashEventData) throws IOException {
        Exception e;
        Throwable th;
        ClsFileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            String previousSessionId = getPreviousSessionId();
            if (previousSessionId == null) {
                Fabric.getLogger().e("CrashlyticsCore", "Tried to write a native crash while no session was open.", null);
                CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
                return;
            }
            recordFatalExceptionAnswersEvent(previousSessionId, String.format(Locale.US, "<native-crash [%s (%s)]>", new Object[]{crashEventData.signal.code, crashEventData.signal.name}));
            boolean hasBinaryImages = crashEventData.binaryImages != null && crashEventData.binaryImages.length > 0;
            ClsFileOutputStream fos2 = new ClsFileOutputStream(getFilesDir(), previousSessionId + (hasBinaryImages ? "SessionCrash" : "SessionMissingBinaryImages"));
            try {
                cos = CodedOutputStream.newInstance(fos2);
                NativeCrashWriter.writeNativeCrash(crashEventData, new LogFileManager(this.crashlyticsCore.getContext(), this.logFileDirectoryProvider, previousSessionId), new MetaDataStore(getFilesDir()).readKeyData(previousSessionId), cos);
                CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(fos2, "Failed to close fatal exception file output stream.");
                fos = fos2;
            } catch (Exception e2) {
                e = e2;
                fos = fos2;
                try {
                    Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the native crash logger", e);
                    CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fos2;
                CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the native crash logger", e);
            CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
        }
    }

    private void doWriteNonFatal(Date time, Thread thread, Throwable ex) {
        Exception e;
        Throwable th;
        String currentSessionId = getCurrentSessionId();
        if (currentSessionId == null) {
            Fabric.getLogger().e("CrashlyticsCore", "Tried to write a non-fatal exception while no session was open.", null);
            return;
        }
        recordLoggedExceptionAnswersEvent(currentSessionId, ex.getClass().getName());
        ClsFileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            Fabric.getLogger().d("CrashlyticsCore", "Crashlytics is logging non-fatal exception \"" + ex + "\" from thread " + thread.getName());
            ClsFileOutputStream fos2 = new ClsFileOutputStream(getFilesDir(), currentSessionId + "SessionEvent" + CommonUtils.padWithZerosToMaxIntWidth(this.eventCounter.getAndIncrement()));
            try {
                cos = CodedOutputStream.newInstance(fos2);
                writeSessionEvent(cos, time, thread, ex, "error", false);
                CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
                CommonUtils.closeOrLog(fos2, "Failed to close non-fatal file output stream.");
                fos = fos2;
            } catch (Exception e2) {
                e = e2;
                fos = fos2;
                try {
                    Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the non-fatal exception logger", e);
                    CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(fos, "Failed to close non-fatal file output stream.");
                    trimSessionEventFiles(currentSessionId, 64);
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(fos, "Failed to close non-fatal file output stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fos2;
                CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
                CommonUtils.closeOrLog(fos, "Failed to close non-fatal file output stream.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the non-fatal exception logger", e);
            CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
            CommonUtils.closeOrLog(fos, "Failed to close non-fatal file output stream.");
            trimSessionEventFiles(currentSessionId, 64);
        }
        try {
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Exception e4) {
            Fabric.getLogger().e("CrashlyticsCore", "An error occurred when trimming non-fatal files.", e4);
        }
    }

    private void writeSessionPartFile(String sessionId, String tag, CodedOutputStreamWriteAction writeAction) throws Exception {
        Throwable th;
        FileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            FileOutputStream fos2 = new ClsFileOutputStream(getFilesDir(), sessionId + tag);
            try {
                cos = CodedOutputStream.newInstance(fos2);
                writeAction.writeTo(cos);
                CommonUtils.flushOrLog(cos, "Failed to flush to session " + tag + " file.");
                CommonUtils.closeOrLog(fos2, "Failed to close session " + tag + " file.");
            } catch (Throwable th2) {
                th = th2;
                fos = fos2;
                CommonUtils.flushOrLog(cos, "Failed to flush to session " + tag + " file.");
                CommonUtils.closeOrLog(fos, "Failed to close session " + tag + " file.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.flushOrLog(cos, "Failed to flush to session " + tag + " file.");
            CommonUtils.closeOrLog(fos, "Failed to close session " + tag + " file.");
            throw th;
        }
    }

    private void writeFile(String sessionId, String tag, FileOutputStreamWriteAction writeAction) throws Exception {
        Throwable th;
        FileOutputStream fos = null;
        try {
            FileOutputStream fos2 = new FileOutputStream(new File(getFilesDir(), sessionId + tag));
            try {
                writeAction.writeTo(fos2);
                CommonUtils.closeOrLog(fos2, "Failed to close " + tag + " file.");
            } catch (Throwable th2) {
                th = th2;
                fos = fos2;
                CommonUtils.closeOrLog(fos, "Failed to close " + tag + " file.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.closeOrLog(fos, "Failed to close " + tag + " file.");
            throw th;
        }
    }

    private void writeBeginSession(String sessionId, Date startedAt) throws Exception {
        final String generator = String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{this.crashlyticsCore.getVersion()});
        final long startedAtSeconds = startedAt.getTime() / 1000;
        final String str = sessionId;
        writeSessionPartFile(sessionId, "BeginSession", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeBeginSession(arg, str, generator, startedAtSeconds);
            }
        });
        str = sessionId;
        writeFile(sessionId, "BeginSession.json", new FileOutputStreamWriteAction() {
            public void writeTo(FileOutputStream arg) throws Exception {
                arg.write(new JSONObject(new HashMap<String, Object>() {
                    {
                        put("session_id", str);
                        put("generator", generator);
                        put("started_at_seconds", Long.valueOf(startedAtSeconds));
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionApp(String sessionId) throws Exception {
        final String appIdentifier = this.idManager.getAppIdentifier();
        final String versionCode = this.appData.versionCode;
        final String versionName = this.appData.versionName;
        final String installUuid = this.idManager.getAppInstallIdentifier();
        final int deliveryMechanism = DeliveryMechanism.determineFrom(this.appData.installerPackageName).getId();
        writeSessionPartFile(sessionId, "SessionApp", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionApp(arg, appIdentifier, CrashlyticsController.this.appData.apiKey, versionCode, versionName, installUuid, deliveryMechanism, CrashlyticsController.this.unityVersion);
            }
        });
        writeFile(sessionId, "SessionApp.json", new FileOutputStreamWriteAction() {
            public void writeTo(FileOutputStream arg) throws Exception {
                arg.write(new JSONObject(new HashMap<String, Object>() {
                    {
                        put("app_identifier", appIdentifier);
                        put("api_key", CrashlyticsController.this.appData.apiKey);
                        put("version_code", versionCode);
                        put("version_name", versionName);
                        put("install_uuid", installUuid);
                        put("delivery_mechanism", Integer.valueOf(deliveryMechanism));
                        put("unity_version", TextUtils.isEmpty(CrashlyticsController.this.unityVersion) ? "" : CrashlyticsController.this.unityVersion);
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionOS(String sessionId) throws Exception {
        final boolean isRooted = CommonUtils.isRooted(this.crashlyticsCore.getContext());
        writeSessionPartFile(sessionId, "SessionOS", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionOS(arg, VERSION.RELEASE, VERSION.CODENAME, isRooted);
            }
        });
        writeFile(sessionId, "SessionOS.json", new FileOutputStreamWriteAction() {
            public void writeTo(FileOutputStream arg) throws Exception {
                arg.write(new JSONObject(new HashMap<String, Object>() {
                    {
                        put("version", VERSION.RELEASE);
                        put("build_version", VERSION.CODENAME);
                        put("is_rooted", Boolean.valueOf(isRooted));
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionDevice(String sessionId) throws Exception {
        Context context = this.crashlyticsCore.getContext();
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        final int arch = CommonUtils.getCpuArchitectureInt();
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final long totalRam = CommonUtils.getTotalRamInBytes();
        final long diskSpace = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        final boolean isEmulator = CommonUtils.isEmulator(context);
        final Map<DeviceIdentifierType, String> ids = this.idManager.getDeviceIdentifiers();
        final int state = CommonUtils.getDeviceState(context);
        writeSessionPartFile(sessionId, "SessionDevice", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionDevice(arg, arch, Build.MODEL, availableProcessors, totalRam, diskSpace, isEmulator, ids, state, Build.MANUFACTURER, Build.PRODUCT);
            }
        });
        writeFile(sessionId, "SessionDevice.json", new FileOutputStreamWriteAction() {
            public void writeTo(FileOutputStream arg) throws Exception {
                arg.write(new JSONObject(new HashMap<String, Object>() {
                    {
                        put("arch", Integer.valueOf(arch));
                        put("build_model", Build.MODEL);
                        put("available_processors", Integer.valueOf(availableProcessors));
                        put("total_ram", Long.valueOf(totalRam));
                        put("disk_space", Long.valueOf(diskSpace));
                        put("is_emulator", Boolean.valueOf(isEmulator));
                        put("ids", ids);
                        put("state", Integer.valueOf(state));
                        put("build_manufacturer", Build.MANUFACTURER);
                        put("build_product", Build.PRODUCT);
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionUser(String sessionId) throws Exception {
        final UserMetaData userMetaData = getUserMetaData(sessionId);
        writeSessionPartFile(sessionId, "SessionUser", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionUser(arg, userMetaData.id, userMetaData.name, userMetaData.email);
            }
        });
    }

    private void writeSessionEvent(CodedOutputStream cos, Date time, Thread thread, Throwable ex, String eventType, boolean includeAllThreads) throws Exception {
        Thread[] threads;
        Map<String, String> attributes;
        TrimmedThrowableData trimmedEx = new TrimmedThrowableData(ex, this.stackTraceTrimmingStrategy);
        Context context = this.crashlyticsCore.getContext();
        long eventTime = time.getTime() / 1000;
        Float batteryLevel = CommonUtils.getBatteryLevel(context);
        int batteryVelocity = CommonUtils.getBatteryVelocity(context, this.devicePowerStateListener.isPowerConnected());
        boolean proximityEnabled = CommonUtils.getProximitySensorEnabled(context);
        int orientation = context.getResources().getConfiguration().orientation;
        long usedRamBytes = CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(context);
        long diskUsedBytes = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        RunningAppProcessInfo runningAppProcessInfo = CommonUtils.getAppProcessInfo(context.getPackageName(), context);
        List<StackTraceElement[]> stacks = new LinkedList();
        StackTraceElement[] exceptionStack = trimmedEx.stacktrace;
        String buildId = this.appData.buildId;
        String appIdentifier = this.idManager.getAppIdentifier();
        if (includeAllThreads) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            threads = new Thread[allStackTraces.size()];
            int i = 0;
            for (Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                threads[i] = (Thread) entry.getKey();
                stacks.add(this.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[]) entry.getValue()));
                i++;
            }
        } else {
            threads = new Thread[0];
        }
        if (CommonUtils.getBooleanResourceValue(context, "com.crashlytics.CollectCustomKeys", true)) {
            attributes = this.crashlyticsCore.getAttributes();
            if (attributes != null && attributes.size() > 1) {
                attributes = new TreeMap(attributes);
            }
        } else {
            attributes = new TreeMap();
        }
        SessionProtobufHelper.writeSessionEvent(cos, eventTime, eventType, trimmedEx, thread, exceptionStack, threads, stacks, attributes, this.logFileManager, runningAppProcessInfo, orientation, appIdentifier, buildId, batteryLevel, batteryVelocity, proximityEnabled, usedRamBytes, diskUsedBytes);
    }

    private void writeSessionPartsToSessionFile(File sessionBeginFile, String sessionId, int maxLoggedExceptionsCount) {
        Fabric.getLogger().d("CrashlyticsCore", "Collecting session parts for ID " + sessionId);
        File[] fatalFiles = listFilesMatching(new FileNameContainsFilter(sessionId + "SessionCrash"));
        boolean hasFatal = fatalFiles != null && fatalFiles.length > 0;
        Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{sessionId, Boolean.valueOf(hasFatal)}));
        File[] nonFatalFiles = listFilesMatching(new FileNameContainsFilter(sessionId + "SessionEvent"));
        boolean hasNonFatal = nonFatalFiles != null && nonFatalFiles.length > 0;
        Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{sessionId, Boolean.valueOf(hasNonFatal)}));
        if (hasFatal || hasNonFatal) {
            synthesizeSessionFile(sessionBeginFile, sessionId, getTrimmedNonFatalFiles(sessionId, nonFatalFiles, maxLoggedExceptionsCount), hasFatal ? fatalFiles[0] : null);
        } else {
            Fabric.getLogger().d("CrashlyticsCore", "No events present for session ID " + sessionId);
        }
        Fabric.getLogger().d("CrashlyticsCore", "Removing session part files for ID " + sessionId);
        deleteSessionPartFilesFor(sessionId);
    }

    private void synthesizeSessionFile(File sessionBeginFile, String sessionId, File[] nonFatalFiles, File fatalFile) {
        Exception e;
        Throwable th;
        boolean hasFatal = fatalFile != null;
        File outputDir = hasFatal ? getFatalSessionFilesDir() : getNonFatalSessionFilesDir();
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        ClsFileOutputStream fos = null;
        try {
            ClsFileOutputStream fos2 = new ClsFileOutputStream(outputDir, sessionId);
            try {
                CodedOutputStream cos = CodedOutputStream.newInstance(fos2);
                Fabric.getLogger().d("CrashlyticsCore", "Collecting SessionStart data for session ID " + sessionId);
                writeToCosFromFile(cos, sessionBeginFile);
                cos.writeUInt64(4, new Date().getTime() / 1000);
                cos.writeBool(5, hasFatal);
                cos.writeUInt32(11, 1);
                cos.writeEnum(12, 3);
                writeInitialPartsTo(cos, sessionId);
                writeNonFatalEventsTo(cos, nonFatalFiles, sessionId);
                if (hasFatal) {
                    writeToCosFromFile(cos, fatalFile);
                }
                CommonUtils.flushOrLog(cos, "Error flushing session file stream");
                if (null != null) {
                    closeWithoutRenamingOrLog(fos2);
                    fos = fos2;
                    return;
                }
                CommonUtils.closeOrLog(fos2, "Failed to close CLS file");
                fos = fos2;
            } catch (Exception e2) {
                e = e2;
                fos = fos2;
                try {
                    Fabric.getLogger().e("CrashlyticsCore", "Failed to write session file for session ID: " + sessionId, e);
                    CommonUtils.flushOrLog(null, "Error flushing session file stream");
                    if (true) {
                        closeWithoutRenamingOrLog(fos);
                    } else {
                        CommonUtils.closeOrLog(fos, "Failed to close CLS file");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.flushOrLog(null, "Error flushing session file stream");
                    if (null == null) {
                        CommonUtils.closeOrLog(fos, "Failed to close CLS file");
                    } else {
                        closeWithoutRenamingOrLog(fos);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fos2;
                CommonUtils.flushOrLog(null, "Error flushing session file stream");
                if (null == null) {
                    closeWithoutRenamingOrLog(fos);
                } else {
                    CommonUtils.closeOrLog(fos, "Failed to close CLS file");
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().e("CrashlyticsCore", "Failed to write session file for session ID: " + sessionId, e);
            CommonUtils.flushOrLog(null, "Error flushing session file stream");
            if (true) {
                closeWithoutRenamingOrLog(fos);
            } else {
                CommonUtils.closeOrLog(fos, "Failed to close CLS file");
            }
        }
    }

    private static void writeNonFatalEventsTo(CodedOutputStream cos, File[] nonFatalFiles, String sessionId) {
        Arrays.sort(nonFatalFiles, CommonUtils.FILE_MODIFIED_COMPARATOR);
        for (File nonFatalFile : nonFatalFiles) {
            try {
                Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{sessionId, nonFatalFile.getName()}));
                writeToCosFromFile(cos, nonFatalFile);
            } catch (Exception e) {
                Fabric.getLogger().e("CrashlyticsCore", "Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream cos, String sessionId) throws IOException {
        for (String tag : INITIAL_SESSION_PART_TAGS) {
            File[] sessionPartFiles = listFilesMatching(new FileNameContainsFilter(sessionId + tag + ".cls"));
            if (sessionPartFiles.length == 0) {
                Fabric.getLogger().e("CrashlyticsCore", "Can't find " + tag + " data for session ID " + sessionId, null);
            } else {
                Fabric.getLogger().d("CrashlyticsCore", "Collecting " + tag + " data for session ID " + sessionId);
                writeToCosFromFile(cos, sessionPartFiles[0]);
            }
        }
    }

    private static void writeToCosFromFile(CodedOutputStream cos, File file) throws IOException {
        Throwable th;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                FileInputStream fis2 = new FileInputStream(file);
                try {
                    copyToCodedOutputStream(fis2, cos, (int) file.length());
                    CommonUtils.closeOrLog(fis2, "Failed to close file input stream.");
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    fis = fis2;
                    CommonUtils.closeOrLog(fis, "Failed to close file input stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                CommonUtils.closeOrLog(fis, "Failed to close file input stream.");
                throw th;
            }
        }
        Fabric.getLogger().e("CrashlyticsCore", "Tried to include a file that doesn't exist: " + file.getName(), null);
    }

    private static void copyToCodedOutputStream(InputStream inStream, CodedOutputStream cos, int bufferLength) throws IOException {
        byte[] buffer = new byte[bufferLength];
        int offset = 0;
        while (offset < buffer.length) {
            int numRead = inStream.read(buffer, offset, buffer.length - offset);
            if (numRead < 0) {
                break;
            }
            offset += numRead;
        }
        cos.writeRawBytes(buffer);
    }

    private UserMetaData getUserMetaData(String sessionId) {
        if (isHandlingException()) {
            return new UserMetaData(this.crashlyticsCore.getUserIdentifier(), this.crashlyticsCore.getUserName(), this.crashlyticsCore.getUserEmail());
        }
        return new MetaDataStore(getFilesDir()).readUserData(sessionId);
    }

    boolean isHandlingException() {
        return this.crashHandler != null && this.crashHandler.isHandlingException();
    }

    File getFilesDir() {
        return this.fileStore.getFilesDir();
    }

    File getFatalSessionFilesDir() {
        return new File(getFilesDir(), "fatal-sessions");
    }

    File getNonFatalSessionFilesDir() {
        return new File(getFilesDir(), "nonfatal-sessions");
    }

    File getInvalidFilesDir() {
        return new File(getFilesDir(), "invalidClsFiles");
    }

    private boolean shouldPromptUserBeforeSendingCrashReports(SettingsData settingsData) {
        if (settingsData == null || !settingsData.featuresData.promptEnabled || this.preferenceManager.shouldAlwaysSendReports()) {
            return false;
        }
        return true;
    }

    private CreateReportSpiCall getCreateReportSpiCall(String reportsUrl) {
        return new DefaultCreateReportSpiCall(this.crashlyticsCore, CommonUtils.getStringsFileValue(this.crashlyticsCore.getContext(), "com.crashlytics.ApiEndpoint"), reportsUrl, this.httpRequestFactory);
    }

    private void sendSessionReports(SettingsData settingsData) {
        if (settingsData == null) {
            Fabric.getLogger().w("CrashlyticsCore", "Cannot send reports. Settings are unavailable.");
            return;
        }
        Context context = this.crashlyticsCore.getContext();
        ReportUploader reportUploader = new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(settingsData.appData.reportsUrl), this.reportFilesProvider, this.handlingExceptionCheck);
        for (File finishedSessionFile : listCompleteSessionFiles()) {
            this.backgroundWorker.submit(new SendReportRunnable(context, new SessionReport(finishedSessionFile, SEND_AT_CRASHTIME_HEADER), reportUploader));
        }
    }

    private static void recordLoggedExceptionAnswersEvent(String sessionId, String exceptionName) {
        Answers answers = (Answers) Fabric.getKit(Answers.class);
        if (answers == null) {
            Fabric.getLogger().d("CrashlyticsCore", "Answers is not available");
        } else {
            answers.onException(new LoggedException(sessionId, exceptionName));
        }
    }

    private static void recordFatalExceptionAnswersEvent(String sessionId, String exceptionName) {
        Answers answers = (Answers) Fabric.getKit(Answers.class);
        if (answers == null) {
            Fabric.getLogger().d("CrashlyticsCore", "Answers is not available");
        } else {
            answers.onException(new FatalException(sessionId, exceptionName));
        }
    }

    private void recordFatalFirebaseEvent(long timestamp) {
        if (firebaseCrashExists()) {
            Fabric.getLogger().d("CrashlyticsCore", "Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
        } else if (!this.firebaseCrashlyticsEnabled) {
        } else {
            if (this.firebaseAnalytics != null) {
                Fabric.getLogger().d("CrashlyticsCore", "Logging Crashlytics event to Firebase");
                Bundle params = new Bundle();
                params.putInt("_r", 1);
                params.putInt("fatal", 1);
                params.putLong("timestamp", timestamp);
                this.firebaseAnalytics.logEvent("clx", "_ae", params);
                return;
            }
            Fabric.getLogger().d("CrashlyticsCore", "Skipping logging Crashlytics event to Firebase, no Firebase Analytics");
        }
    }

    private boolean firebaseCrashExists() {
        try {
            Class clazz = Class.forName("com.google.firebase.crash.FirebaseCrash");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
