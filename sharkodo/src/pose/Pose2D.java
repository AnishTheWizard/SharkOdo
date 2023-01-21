package pose;


public record Pose2D(double x, double y) implements Pose {
    @Override
    public double[] getPose() {
        return new double[0];
    }

    @Override
    public Pose getTransformation(Pose p) throws Exceptions.PoseTypeMismatch {
        return null;
    }

    @Override
    public void transformTo(Pose p) {

    }
}
