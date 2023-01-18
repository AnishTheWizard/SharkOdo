package pose;

public record Pose2D(double x, double y) implements Pose {
    @Override
    public double[] getPose() {
        return new double[0];
    }
}
