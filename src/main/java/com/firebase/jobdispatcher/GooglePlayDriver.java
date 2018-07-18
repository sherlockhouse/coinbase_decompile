package com.firebase.jobdispatcher;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public final class GooglePlayDriver implements Driver {
    private final boolean available = true;
    private final Context context;
    private final PendingIntent token;
    private final JobValidator validator;
    private final GooglePlayJobWriter writer;

    public GooglePlayDriver(Context context) {
        this.context = context;
        this.token = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        this.writer = new GooglePlayJobWriter();
        this.validator = new DefaultJobValidator(context);
    }

    public boolean isAvailable() {
        return true;
    }

    public int schedule(Job job) {
        this.context.sendBroadcast(createScheduleRequest(job));
        GooglePlayReceiver.onSchedule(job);
        return 0;
    }

    public JobValidator getValidator() {
        return this.validator;
    }

    private Intent createScheduleRequest(JobParameters job) {
        Intent scheduleReq = createSchedulerIntent("SCHEDULE_TASK");
        scheduleReq.putExtras(this.writer.writeToBundle(job, scheduleReq.getExtras()));
        return scheduleReq;
    }

    private Intent createSchedulerIntent(String schedulerAction) {
        Intent scheduleReq = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        scheduleReq.setPackage("com.google.android.gms");
        scheduleReq.putExtra("scheduler_action", schedulerAction);
        scheduleReq.putExtra("app", this.token);
        scheduleReq.putExtra("source", 8);
        scheduleReq.putExtra("source_version", 1);
        return scheduleReq;
    }
}
