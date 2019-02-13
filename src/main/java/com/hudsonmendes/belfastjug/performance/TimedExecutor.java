package com.hudsonmendes.belfastjug.performance;

import java.util.function.Function;
import java.util.function.Supplier;

public class TimedExecutor<T> {
    
    private final Supplier<T> operation;

    public TimedExecutor(final Supplier<T> operation) {
        this.operation = operation;
    }

    public TimedOperation<T> executeAndTime(Function<T, Double> checkSum) {
        final var tic = System.currentTimeMillis();
        final var result = operation.get();
        final var toc = System.currentTimeMillis();
        return new TimedOperation<>(toc - tic, result, checkSum.apply(result));
    }

}
