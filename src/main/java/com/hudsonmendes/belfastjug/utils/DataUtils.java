package com.hudsonmendes.belfastjug.utils;

import static java.lang.Math.pow;

import java.security.SecureRandom;

import org.nd4j.linalg.api.buffer.DataBuffer.Type;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import cern.colt.matrix.impl.DenseDoubleMatrix2D;

public class DataUtils {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final double TOLERANCE = pow(10, -6);

    public static double tolerance() {
        return TOLERANCE;
    }

    public static INDArray createVector(final int rows, final int columns) {
        Nd4j.setDataType(Type.DOUBLE);
        return Nd4j.randn(rows, columns);
    }

    public static DenseDoubleMatrix2D createMatrix(final int rows, final int columns) {
        final DenseDoubleMatrix2D matrix = new DenseDoubleMatrix2D(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                matrix.setQuick(i, j, RANDOM.nextGaussian());
        return matrix;
    }

    public static double[][] createArray(final int rows, final int columns) {
        final double[][] array = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                array[i][j] = RANDOM.nextGaussian();
        return array;
    }

    public static double checkSum(final double[][] data) {
        final int rows = data.length;
        final int columns = data[0].length;
        double total = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                total += data[i][j];
        return total;
    }

}
