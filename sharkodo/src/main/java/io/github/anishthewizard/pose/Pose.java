package io.github.anishthewizard.pose;

import io.github.anishthewizard.sharkodo.Exceptions;

public interface Pose {

    public double[] getPose();

    public Pose getTransformation(Pose p) throws Exceptions.PoseTypeMismatch;

    public void add(Pose p) throws Exceptions.PoseTypeMismatch;

    public String toString();

}
