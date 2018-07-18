package com.bumptech.glide.request;

public interface Request {
    void begin();

    void clear();

    boolean isCancelled();

    boolean isComplete();

    boolean isResourceSet();

    boolean isRunning();

    void pause();

    void recycle();
}
