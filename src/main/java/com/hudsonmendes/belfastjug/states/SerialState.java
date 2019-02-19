package com.hudsonmendes.belfastjug.states;

import static com.hudsonmendes.belfastjug.Benchmarks.COLUMNS;
import static com.hudsonmendes.belfastjug.Benchmarks.ROWS;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.hudsonmendes.belfastjug.utils.DataUtils;

@State(Scope.Benchmark)
public class SerialState {

    private final double[][] numbers;
    private final double checkSum;

    public SerialState() {
        numbers = DataUtils.createArray(ROWS, COLUMNS);
        checkSum = DataUtils.checkSum(getNumbers());
    }

    public double[][] getNumbers() {
        return numbers;
    }

    public double getCheckSum() {
        return checkSum;
    }

}
