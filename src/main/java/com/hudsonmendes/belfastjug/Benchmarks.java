package com.hudsonmendes.belfastjug;

import static com.hudsonmendes.belfastjug.utils.Data.sample;
import static com.hudsonmendes.belfastjug.utils.DataUtils.zero;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.nd4j.linalg.api.buffer.DataBuffer.Type;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import com.hudsonmendes.belfastjug.utils.Data;

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;

public class Benchmarks {

    private static final Data SAMPLE = sample();

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void serial() {
        double total = 0;
        for (final double element : SAMPLE.getNumbers())
            total += element;
        assertEquals(
            SAMPLE.checkSum(),
            total,
            zero());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void colt() {
        final DoubleMatrix1D vector = new DenseDoubleMatrix1D(SAMPLE.getNumbers());
        assertEquals(
            SAMPLE.checkSum(),
            vector.zSum(),
            zero());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void nd4j() {
        Nd4j.setDataType(Type.DOUBLE);
        final INDArray vector = Nd4j.create(SAMPLE.getNumbers());
        assertEquals(
            SAMPLE.checkSum(),
            vector.sumNumber().doubleValue(),
            zero());
    }
}