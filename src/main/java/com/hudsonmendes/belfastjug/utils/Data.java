package com.hudsonmendes.belfastjug.utils;

import static com.hudsonmendes.belfastjug.utils.DataUtils.generateArray;

public final class Data {
    private static final Data SINGLETON = new Data(generateArray(100000));

    public static Data sample() {
        return SINGLETON;
    }

    private final double[] numbers;
    private final double checkSum;

    public Data(final double[] sample) {
        numbers = sample;
        checkSum = checkSum(sample);
    }

    private static double checkSum(final double[] data) {
        final int rows = data.length;
        double total = 0;
        for (int i = 0; i < rows; i++)
            total += data[i];
        return total;
    }

    public double[] getNumbers() {
        return numbers;
    }

    public double checkSum() {
        return checkSum;
    }
}
