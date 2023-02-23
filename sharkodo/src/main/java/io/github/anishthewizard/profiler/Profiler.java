package io.github.anishthewizard.profiler;

import io.github.anishthewizard.sharkodo.Exceptions;
import io.github.anishthewizard.pose.Pose;

public interface Profiler {
    public Pose poll() throws Exceptions.PoseTypeMismatch;
    public void reset();
    public void reset(double[] p);
}
