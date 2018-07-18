package com.firebase.jobdispatcher;

import android.support.v4.util.SimpleArrayMap;

public abstract class SimpleJobService extends JobService {
    private final SimpleArrayMap<JobParameters, Object> runningJobs = new SimpleArrayMap();
}
