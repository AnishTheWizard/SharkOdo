package org.anishthewizard.pose;

import org.anishthewizard.sharkodo.Exceptions;

public interface Pose {

    public double[] getPose();

    public Pose getTransformation(Pose p) throws Exceptions.PoseTypeMismatch;

    public void transformTo(Pose p) throws Exceptions.PoseTypeMismatch;

    public void add(Pose p) throws Exceptions.PoseTypeMismatch;

    public String toString();

}
