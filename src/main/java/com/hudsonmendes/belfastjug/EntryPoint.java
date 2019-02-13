package com.hudsonmendes.belfastjug;

import java.util.function.Supplier;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

public class EntryPoint {

    private static final int SIZE = 10000;

    public static void main(final String[] args) {

        final var a = DoubleFactory2D.dense.random(SIZE, 1);

        final var b = DoubleFactory2D.dense.random(1, SIZE);

        timed("Serial", () -> serialMultiplication(a, b));

        timed("Vectorial", () -> vectorialMultiplication(a, b));
    }

    private static void timed(
            final String tag,
            final Supplier<DoubleMatrix2D> multiplication) {
        final var tic = System.currentTimeMillis();
        final var result = multiplication.get();
        final var toc = System.currentTimeMillis();
        final var elapsed = toc - tic;
        final var checkSum = result.zSum();
        System.out.println(String.format("%s: %dms (result: %f)", tag, elapsed, checkSum));
    }

    private static DoubleMatrix2D serialMultiplication(final DoubleMatrix2D a, final DoubleMatrix2D b) {
        final DoubleMatrix2D result = DoubleFactory2D.dense.make(a.rows(), b.columns());
        for (var i = 0; i < a.rows(); i++)
            for (var j = 0; j < b.columns(); j++)
                for (int k = 0; k < a.columns(); k++) {
                    final var temp = result.get(i, j);
                    final var acum = temp + a.get(i, k) * b.get(k, j);
                    result.set(i, j, acum);
                }
        return result;
    }

    private static DoubleMatrix2D vectorialMultiplication(final DoubleMatrix2D a, final DoubleMatrix2D b) {
        return new Algebra().mult(a, b);
    }
}
