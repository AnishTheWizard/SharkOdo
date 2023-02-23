package io.github.anishthewizard.profiler;

import io.github.anishthewizard.pose.Twist2D;

public class TankProfiler implements Profiler {
    @Override
    public Twist2D poll() {
        return null;
    }

    @Override
    public void reset() {}

    @Override
    public void reset(double[] p) {}
}
