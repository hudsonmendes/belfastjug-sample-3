package com.hudsonmendes.belfastjug.data;

import java.security.SecureRandom;

public class VectorDataGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private final VectorShape shape;

    public VectorDataGenerator(final VectorShape shape) {
        this.shape = shape;
    }

    public double[][] generate() {
        final var result = new double[shape.getY()][shape.getX()];
        for (int i = 0; i < shape.getY(); i++)
            for (int j = 0; j < shape.getX(); j++)
                result[i][j] = RANDOM.nextDouble();
        return result;
    }

    public double[] zeros() {
        final var result = new double[shape.getY()];
        for (int i = 0; i < shape.getY(); i++)
            result[i] = 0;
        return result;
    }

}
