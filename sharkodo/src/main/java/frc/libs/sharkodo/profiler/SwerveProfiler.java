package frc.libs.sharkodo.profiler;

import frc.libs.sharkodo.pose.Twist2D;
import frc.libs.sharkodo.sharkodo.Exceptions;

import java.util.ArrayList;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class SwerveProfiler implements Profiler {

    private final Twist2D chassisPose;
    private final ArrayList<Twist2D>
    private final Supplier<double[][]> swerveGetPose;
    private final DoubleSupplier getHeading;

    private final double ticksPerInch;
    private final double delay;

    public SwerveProfiler(Supplier<double[][]> swerveGetPose, DoubleSupplier getHeading, double[] pose, double ticksPerInch, double delay) {
        this(swerveGetPose, getHeading, ticksPerInch, delay);
        chassisPose.setTwist(pose);
    }

    public SwerveProfiler(Supplier<double[][]> swerveGetPose, DoubleSupplier getHeading, double ticksPerInch, double delay) {
        this.swerveGetPose = swerveGetPose;
        this.getHeading = getHeading;
        this.ticksPerInch = ticksPerInch;
        this.delay = delay * 1000;
        chassisPose = new Twist2D(0, 0, 0, 0);
    }

    @Override
    public Twist2D poll() throws Exceptions.PoseTypeMismatch {
        double[][] pose = swerveGetPose.get();
        double deltaGyro = getHeading.getAsDouble();
        double deltaX = 0, deltaY = 0;

        for(double[] moduleVec : deltaPose) {
            deltaX += (1.0/deltaPose.length) * (moduleVec[0] * Math.cos(moduleVec[1]));
            deltaY += (1.0/deltaPose.length) * (moduleVec[0] * Math.sin(moduleVec[1]));
        }
        Twist2D delta = new Twist2D(deltaX, deltaY, deltaGyro, 0);
        double v = Math.hypot(deltaX, deltaY)/delay;
        chassisPose.add(delta);
        chassisPose.setV(v);

        return chassisPose.createFactoredTwist(1/ticksPerInch);
    }

    @Override
    public void reset() {
        chassisPose.setTwist(new double[]{0, 0, 0, 0});
    }

    @Override
    public void reset(double[] pose) {
        chassisPose.setTwist(pose);
    }



}