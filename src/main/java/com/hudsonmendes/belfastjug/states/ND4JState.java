package com.hudsonmendes.belfastjug.states;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.hudsonmendes.belfastjug.utils.DataUtils;

@State(Scope.Group)
public class ND4JState {

    private final INDArray vector;
    private final double checkSum;

    public ND4JState(final int rows, final int columns) {
        vector = DataUtils.createVector(rows, columns);
        checkSum = DataUtils.checkSum(getVector().toDoubleMatrix());
    }

    public INDArray getVector() {
        return vector;
    }

    public double getCheckSum() {
        return checkSum;
    }

}
