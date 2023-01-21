package profiler;

import pose.Pose;
import pose.Twist2D;

public interface Profiler {
    public Twist2D poll() throws Exceptions.PoseTypeMismatch;
}
