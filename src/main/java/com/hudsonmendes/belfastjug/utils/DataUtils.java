package com.hudsonmendes.belfastjug.utils;

import java.security.SecureRandom;

public class DataUtils {

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final double ZERO = 0;

    public static double zero() {
        return ZERO;
    }

    public static double[] generateArray(final int rows) {
        final double[] data = new double[rows];
        for (int i = 0; i < rows; i++)
            data[i] = RANDOM.nextFloat();
        return data;
    }

    public static double checkSum(final double[] data) {
        final int rows = data.length;
        double total = 0;
        for (int i = 0; i < rows; i++)
            total += data[i];
        return total;
    }

}
