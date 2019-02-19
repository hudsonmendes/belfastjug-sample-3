package com.hudsonmendes.belfastjug.states;

import static com.hudsonmendes.belfastjug.Benchmarks.COLUMNS;
import static com.hudsonmendes.belfastjug.Benchmarks.ROWS;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.hudsonmendes.belfastjug.utils.DataUtils;

@State(Scope.Benchmark)
public class ND4JState {

    private final INDArray vector;
    private final double checkSum;

    public ND4JState() {
        vector = DataUtils.createVector(ROWS, COLUMNS);
        checkSum = DataUtils.checkSum(getVector().toDoubleMatrix());
    }

    public INDArray getVector() {
        return vector;
    }

    public double getCheckSum() {
        return checkSum;
    }

}
