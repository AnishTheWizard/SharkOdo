package io.github.anishthewizard.sharkodo;
import io.github.anishthewizard.pose.Pose;
import io.github.anishthewizard.profiler.Profiler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SharkOdo {

    private final Profiler profiler;

    private final int delay;

    private final ScheduledExecutorService service;

    private Pose pose;

    private final Object threadLock = new Object();

    public SharkOdo(Profiler profiler, int delayMs) {
        this.profiler = profiler;
        delay = delayMs;
        service = Executors.newSingleThreadScheduledExecutor();

        updatePose();
    }

    public void startOdometryThread() {
        synchronized(threadLock) {
            service.scheduleAtFixedRate(this::updatePose, 0, delay, TimeUnit.MILLISECONDS);
        }
    }

    public void exitOdometryThread() {
        synchronized(threadLock) {
            service.shutdown();
        }
    }

    private void updatePose() {
        synchronized(threadLock) {
            try {
                pose = profiler.poll();
            } catch (Exceptions.PoseTypeMismatch e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Pose getPose() {
        synchronized (threadLock) {
            return pose;
        }
    }

    public void resetPose() {
        synchronized(threadLock) {
            profiler.reset();
        }
    }

    public void resetPoseTo(double[] pose) {
        synchronized(threadLock) {
            profiler.reset(pose);
        }
    }

    public double[] getPoseAsArray() {
        synchronized(threadLock) {
            return pose.getPose();
        }
    }
}
