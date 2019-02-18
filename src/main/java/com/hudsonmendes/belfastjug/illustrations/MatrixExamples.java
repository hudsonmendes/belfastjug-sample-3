package com.hudsonmendes.belfastjug.illustrations;

public class MatrixExamples {

    public double[][] logs(
            final double[][] matrix) {

        final int rows = matrix.length;
        final int columns = matrix[0].length;

        final double[][] r = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                r[i][j] = Math.log(matrix[i][j]);

        return r;
    }

}
