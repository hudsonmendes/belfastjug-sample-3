package com.hudsonmendes.belfastjug;

import java.util.function.Supplier;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

public class EntryPoint {

    private static final int SIZE = 10000;

    public static void main(final String[] args) {

        final DoubleMatrix2D a = DoubleFactory2D.dense.random(SIZE, 1);

        final DoubleMatrix2D b = DoubleFactory2D.dense.random(1, SIZE);

        timed("Serial", () -> serialMultiplication(a, b));

        timed("Vectorial", () -> vectorialMultiplication(a, b));
    }

    private static void timed(
            final String tag,
            final Supplier<DoubleMatrix2D> multiplication) {
        final long tic = System.currentTimeMillis();
        final DoubleMatrix2D result = multiplication.get();
        final long toc = System.currentTimeMillis();
        final long elapsed = toc - tic;
        final double checkSum = result.zSum();
        System.out.println(String.format("%s: %dms (result: %f)", tag, elapsed, checkSum));
    }

    private static DoubleMatrix2D serialMultiplication(final DoubleMatrix2D a, final DoubleMatrix2D b) {
        final DoubleMatrix2D result = DoubleFactory2D.dense.make(a.rows(), b.columns());
        for (int i = 0; i < a.rows(); i++)
            for (int j = 0; j < b.columns(); j++)
                for (int k = 0; k < a.columns(); k++) {
                    final double temp = result.get(i, j);
                    final double acum = temp + a.get(i, k) * b.get(k, j);
                    result.set(i, j, acum);
                }
        return result;
    }

    private static DoubleMatrix2D vectorialMultiplication(final DoubleMatrix2D a, final DoubleMatrix2D b) {
        return new Algebra().mult(a, b);
    }
}
