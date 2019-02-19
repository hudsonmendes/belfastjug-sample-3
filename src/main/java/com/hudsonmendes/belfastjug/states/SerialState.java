package com.hudsonmendes.belfastjug.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.hudsonmendes.belfastjug.utils.DataUtils;

@State(Scope.Group)
public class SerialState {

    private final double[][] numbers;
    private final double checkSum;

    public SerialState(final int rows, final int columns) {
        numbers = DataUtils.createArray(rows, columns);
        checkSum = DataUtils.checkSum(getNumbers());
    }

    public double[][] getNumbers() {
        return numbers;
    }

    public double getCheckSum() {
        return checkSum;
    }

}
