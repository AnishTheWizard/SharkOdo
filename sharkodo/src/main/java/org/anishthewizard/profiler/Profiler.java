package org.anishthewizard.profiler;

import org.anishthewizard.pose.Pose;
import org.anishthewizard.sharkodo.Exceptions;

public interface Profiler {
    public Pose poll() throws Exceptions.PoseTypeMismatch;
    public void reset();
    public void reset(double[] p);
}
