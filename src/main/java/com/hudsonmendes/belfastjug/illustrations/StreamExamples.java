package com.hudsonmendes.belfastjug.illustrations;

import java.util.Arrays;

public class StreamExamples {

    public double streamSum(
            final double[] examples) {
        return Arrays
            .stream(examples)
            .sum();
    }

    public double forSum(
            final double[] examples) {
        double total = 0;
        for (final double example : examples)
            total += example;
        return total;
    }

}
