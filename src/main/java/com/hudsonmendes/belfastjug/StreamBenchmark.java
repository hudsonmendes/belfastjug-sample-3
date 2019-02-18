package com.hudsonmendes.belfastjug;

import static com.hudsonmendes.belfastjug.utils.DataUtils.checkSum;
import static com.hudsonmendes.belfastjug.utils.DataUtils.generateArray;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;

public class StreamBenchmark {

    private static final double[] SAMPLE = generateArray(100000);
    private static final double expected = checkSum(SAMPLE);
    private static final double ACCEPTABLE_DELTA = 0;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void serial() {
        double total = 0;
        for (final double element : SAMPLE)
            total += element;
        assertEquals(
            expected,
            total,
            ACCEPTABLE_DELTA);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void colt() {
        final DoubleMatrix1D vector = new DenseDoubleMatrix1D(SAMPLE);
        assertEquals(
            expected,
            vector.zSum(),
            ACCEPTABLE_DELTA);
    }

}
