package com.firebase.jobdispatcher;

public interface Driver {
    JobValidator getValidator();

    boolean isAvailable();

    int schedule(Job job);
}
