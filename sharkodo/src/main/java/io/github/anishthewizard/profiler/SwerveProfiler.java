package io.github.anishthewizard.profiler;

import io.github.anishthewizard.pose.Twist2D;
import io.github.anishthewizard.sharkodo.Exceptions;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class SwerveProfiler implements Profiler {

    private final Twist2D chassisPose;
    private final Supplier<double[][]> swerveGetPose;
    private final Supplier<double[]> getMotionVector;
    private final DoubleSupplier getHeading;

    private final double ticksPerInch;

    public SwerveProfiler(Supplier<double[][]> swerveGetPose, DoubleSupplier getHeading, Supplier<double[]> motionVectors, double[] pose, double ticksPerInch) {
        this(swerveGetPose, getHeading, motionVectors, ticksPerInch);
        chassisPose.setTwist(pose);
    }

    public SwerveProfiler(Supplier<double[][]> swerveGetPose, DoubleSupplier getHeading, Supplier<double[]> motionVectors, double ticksPerInch) {
        this.swerveGetPose = swerveGetPose;
        this.getMotionVector = motionVectors;
        this.getHeading = getHeading;
        this.ticksPerInch = ticksPerInch;
        chassisPose = new Twist2D(0, 0, 0, 0, 0);
    }

    @Override
    public Twist2D poll() throws Exceptions.PoseTypeMismatch {
        double[][] deltaPose = swerveGetPose.get();
        double deltaGyro = getHeading.getAsDouble();
        double[] motionVector = getMotionVector.get();
        double deltaX = 0, deltaY = 0;

        for(double[] moduleVec : deltaPose) {
            deltaX += (1.0/deltaPose.length) * (moduleVec[0] * Math.cos(moduleVec[1]));
            deltaY += (1.0/deltaPose.length) * (moduleVec[0] * Math.sin(moduleVec[1]));
        }
        Twist2D delta = new Twist2D(deltaX, deltaY, deltaGyro, motionVector[0], motionVector[1]);
        chassisPose.add(delta);

        return chassisPose.createFactoredTwist(1/ticksPerInch);
    }

    @Override
    public void reset() {
        chassisPose.setTwist(new double[]{0, 0, 0, 0, 0});
    }

    @Override
    public void reset(double[] pose) {
        chassisPose.setTwist(pose);
    }


}