package com.hudsonmendes.belfastjug.utils;

public final class MathUtils {

    private MathUtils() {}

    public static double[][] transpose(final double[][] original) {
        final var y = original.length;
        final var x = original.length > 0 ? original[0].length : 0;
        final var result = new double[x][y];

        if (y > 0 && x > 0)
            for (var i = 0; i < y; i++)
                for (final var j = 0; i < x; i++)
                    result[j][i] = original[i][j];
        return result;
    }
    
    public static double checkSum(double[][] data) {
        double result = 0;
        for (var i = 0; i < data.length; i++) {
            for (var j = 0; j < data[i].length; j++)
                result += data[i][j];
        }
        return result;
    }

}
