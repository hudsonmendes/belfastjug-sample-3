package com.hudsonmendes.belfastjug.operations;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

public class VectorialBatchMath implements BatchMath {
    private static final Algebra ALGEBRA = new Algebra();

    @Override
    public double[][] sumAll(final double[][] a, final double[] b) {
        assert a.length == b.length;

        final var matrixA = new DenseDoubleMatrix2D(a);
        final var matrixB = new DenseDoubleMatrix1D(b);

        final var result = DoubleFactory2D.dense.make(matrixA.rows(), matrixA.columns());
        for (int i = 0; i < result.rows(); i++)
            for (int j = 0; j < result.columns(); j++) {
                final var aItem = matrixA.getQuick(i, j);
                final var bItem = matrixB.get(i);
                result.setQuick(i, j, aItem + bItem);
            }

        return result.toArray();
    }

    @Override
    public double[][] dotAll(final double[][] a, final double[][] b) {
        assert a.length > 0;
        assert b.length > 0;
        assert a.length == b[0].length;
        assert a[0].length == b[1].length;

        final var matrixA = new DenseDoubleMatrix2D(a);
        final var matrixB = new DenseDoubleMatrix2D(b);
        final var matrixBTranspose = ALGEBRA.transpose(matrixB);
        return ALGEBRA.mult(matrixA, matrixBTranspose).toArray();
    }

}
