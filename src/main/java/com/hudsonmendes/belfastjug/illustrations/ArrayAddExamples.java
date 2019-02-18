package com.hudsonmendes.belfastjug.illustrations;

public class ArrayAddExamples {

    public double[] addTwoArrays(
            final double[] a,
            final double[] b) {
        assert a.length == b.length;

        final double[] ret = new double[a.length];

        for (int i = 0; i < a.length; i++)
            ret[i] = a[i] + b[i];

        return ret;
    }

}
