import pose.Pose;
import pose.Twist2D;
import profiler.Profiler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SharkOdo {

    private Profiler profiler;

    private final int delay;

    private ScheduledExecutorService service;

    private Pose pose;

    public SharkOdo(Profiler profiler, int delayMs) {
        this.profiler = profiler;
        delay = delayMs;
        service = Executors.newSingleThreadScheduledExecutor();
    }

    public void startOdometryThread() {
        service.scheduleAtFixedRate(this::updatePose, 0, delay, TimeUnit.MILLISECONDS);
    }

    public void updatePose() {

    }

}
