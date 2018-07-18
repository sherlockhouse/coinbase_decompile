package rx;

public abstract class Scheduler {
    public abstract Worker createWorker();

    public long now() {
        return System.currentTimeMillis();
    }
}
