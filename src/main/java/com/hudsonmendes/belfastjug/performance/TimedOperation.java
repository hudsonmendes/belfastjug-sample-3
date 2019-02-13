package com.hudsonmendes.belfastjug.performance;

public class TimedOperation<T> {

    private final long elapsedMillis;
    private final T result;
    private final double checkSum;

    public TimedOperation(
            final long elapsedMillis,
            final T result,
            final double checkSum) {
        this.elapsedMillis = elapsedMillis;
        this.result = result;
        this.checkSum = checkSum;
    }

    public long getElapsedMillis() {
        return elapsedMillis;
    }

    public T getResult() {
        return result;
    }

    public double getCheckSum() {
        return checkSum;
    }

}
