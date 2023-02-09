package org.anishthewizard.pose;

import org.anishthewizard.sharkodo.Exceptions;

public class Twist2D implements Pose {

    private double x, y, theta;

    public Twist2D(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    @Override
    public double[] getPose() {
        return new double[]{x, y, theta};
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
        return new Twist2D(vector.getX() - getX(),
                vector.getY() - getY(),
                vector.getTheta() - getTheta());
    }

    @Override
    public void transformTo(Pose p) throws Exceptions.PoseTypeMismatch {
        if(p instanceof Twist2D) {
            Twist2D delta = (Twist2D) getTransformation((Twist2D) p);
            x += delta.getX();
            y += delta.getY();
            theta += delta.getTheta();
        }
        else {
            throw new Exceptions.PoseTypeMismatch("Expected to get pose type Twist2D");
        }
    }

    @Override
    public void add(Pose p) throws Exceptions.PoseTypeMismatch {
        if(p instanceof Twist2D) {
            Twist2D addend = (Twist2D) p;
            x+=addend.getX();
            y+=addend.getY();
            theta+=addend.getTheta();
        }
        else {
            throw new Exceptions.PoseTypeMismatch("Expected to get pose type Twist2D");
        }

    }

    public Twist2D createFactoredTwist(double factor) {
        return new Twist2D(x*factor, y*factor, theta);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTheta() {
        return theta;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setTwist(double[] pose) {
        this.x = pose[0];
        this.y = pose[1];
        this.theta = pose[2];
    }

    public void setTwist(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }
}
