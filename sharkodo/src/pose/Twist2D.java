package pose;

public class Twist2D implements Pose {

    private double x, y, theta;

    public Twist2D(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    @Override
    public double[] getPose() {
        return new double[0];
    }

    @Override
    public Pose getTransformation(Pose p) throws Exceptions.PoseTypeMismatch {
        Twist2D vector;
        if(p instanceof Twist2D) {
            vector = (Twist2D) p;
        }
        else {
            throw new Exceptions.PoseTypeMismatch("Expected to get pose type Twist2D");
        }
        return new Twist2D(vector.x() - x(),
                vector.y() - y(),
                vector.theta() - theta());
    }

    @Override
    public void transformTo(Pose p) throws Exceptions.PoseTypeMismatch {
        if(p instanceof Twist2D) {
            Twist2D newPose = (Twist2D) p;
            Twist2D delta = (Twist2D) getTransformation(newPose);
            x += delta.x();
            y += delta.y();
            theta += delta.theta();
        }
        else {
            throw new Exceptions.PoseTypeMismatch("Expected to get pose type Twist2D");
        }
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double theta() {
        return theta;
    }
}
