package pose;

public record Twist2D(double x, double y, double theta) implements Pose {



    public Twist2D getTransformation(Twist2D vector) {
        return new Twist2D(vector.x() - x(),
                vector.y() - y(),
                vector.theta() - theta());
    }

    @Override
    public double[] getPose() {
        return new double[0];
    }
}
