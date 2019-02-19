package com.hudsonmendes.belfastjug.states;

import static com.hudsonmendes.belfastjug.Benchmarks.COLUMNS;
import static com.hudsonmendes.belfastjug.Benchmarks.ROWS;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.hudsonmendes.belfastjug.utils.DataUtils;

import cern.colt.matrix.impl.DenseDoubleMatrix2D;

@State(Scope.Benchmark)
public class ColtState {

    private final DenseDoubleMatrix2D matrix;
    private final double checkSum;

    public ColtState() {
        matrix = DataUtils.createMatrix(ROWS, COLUMNS);
        checkSum = DataUtils.checkSum(matrix.toArray());
    }

    public DenseDoubleMatrix2D getMatrix() {
        return matrix;
    }

    public double getCheckSum() {
        return checkSum;
    }

}
