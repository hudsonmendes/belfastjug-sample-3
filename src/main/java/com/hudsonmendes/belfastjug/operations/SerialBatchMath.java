package com.hudsonmendes.belfastjug.operations;

import static com.hudsonmendes.belfastjug.utils.MathUtils.transpose;

public class SerialBatchMath implements BatchMath {

    @Override
    public double[][] sumAll(final double[][] a, final double[] b) {
        assert a.length == b.length;
        final var result = new double[a.length][a[0].length];
        for (var i = 0; i < a.length; i++)
            for (var j = 0; j < a[0].length; j++)
                result[i][j] = a[i][j] + b[i];
        return result;
    }

    @Override
    public double[][] dotAll(final double[][] a, double[][] b) {
        b = transpose(b);
        assert a.length > 0;
        assert b.length > 0;
        assert a.length == b[0].length;
        assert a[0].length == b[1].length;
        final var result = new double[a.length][a[0].length];
        for (var i = 0; i < a.length; i++)
            for (var j = 0; j < a[0].length; j++)
                result[i][j] = a[i][j] * b[j][i];
        return result;
    }

}
