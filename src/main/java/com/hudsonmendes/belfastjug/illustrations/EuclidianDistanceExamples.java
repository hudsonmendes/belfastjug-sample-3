package com.hudsonmendes.belfastjug.illustrations;

public class EuclidianDistanceExamples {

    public double[] euclidianDist(
            final double[] a,
            final double[] b) {

        assert a.length == b.length;

        final double[] r = new double[a.length];
        for (int i = 0; i < a.length; i++)
            r[i] = Math.sqrt(
                Math.pow(a[i], 2) +
                Math.pow(b[i], 2));

        return r;
    }

}
