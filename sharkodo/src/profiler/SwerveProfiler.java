package profiler;

import pose.Twist2D;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class SwerveProfiler implements Profiler {

    Twist2D chassisPose;
    Supplier<double[][]> swerveGetPose;
    DoubleSupplier getHeading;


    public SwerveProfiler(Supplier<double[][]> swerveGetPose, DoubleSupplier getHeading) {
        this.swerveGetPose = swerveGetPose;
        this.getHeading = getHeading;
        try {
            poll();
        } catch (Exceptions.PoseTypeMismatch e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Twist2D poll() throws Exceptions.PoseTypeMismatch {
        double[][] deltaPose = swerveGetPose.get();
        double deltaGyro = getHeading.getAsDouble();
        double deltaX = 0, deltaY = 0;

        for(double[] moduleVec : deltaPose) {
            deltaX += (1.0/deltaPose.length) * (moduleVec[0] * Math.cos(moduleVec[1]));
            deltaY += (1.0/deltaPose.length) * (moduleVec[0] * Math.sin(moduleVec[1]));
        }
        Twist2D delta = new Twist2D(deltaX, deltaY, deltaGyro);
        chassisPose.transformTo(delta);

        return chassisPose;
    }
}