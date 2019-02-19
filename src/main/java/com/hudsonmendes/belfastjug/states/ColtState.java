package com.hudsonmendes.belfastjug.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.hudsonmendes.belfastjug.utils.DataUtils;

import cern.colt.matrix.impl.DenseDoubleMatrix2D;

@State(Scope.Group)
public class ColtState {

    private final DenseDoubleMatrix2D matrix;
    private final double checkSum;

    public ColtState(final int rows, final int columns) {
        matrix = DataUtils.createMatrix(rows, columns);
        checkSum = DataUtils.checkSum(matrix.toArray());
    }

    public DenseDoubleMatrix2D getMatrix() {
        return matrix;
    }

    public double getCheckSum() {
        return checkSum;
    }

}
