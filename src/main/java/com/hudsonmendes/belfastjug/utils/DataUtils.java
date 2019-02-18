package com.hudsonmendes.belfastjug.utils;

import java.security.SecureRandom;

public class DataUtils {

    private static final SecureRandom RANDOM = new SecureRandom();

    public static double[] generateArray(final int rows) {
        final double[] data = new double[rows];
        for (int i = 0; i < rows; i++)
            data[i] = RANDOM.nextFloat();
        return data;
    }

    public static double[][] generateMatrix(final int rows, final int columns) {
        final double[][] data = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (final int j = 0; j < columns; i++)
                data[i][j] = RANDOM.nextDouble();
        return data;
    }

    public static double checkSum(final double[] data) {
        final int rows = data.length;
        double total = 0;
        for (int i = 0; i < rows; i++)
            total += data[i];
        return total;
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
