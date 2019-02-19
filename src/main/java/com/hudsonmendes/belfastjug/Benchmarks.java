package com.hudsonmendes.belfastjug;

import static com.hudsonmendes.belfastjug.utils.DataUtils.tolerance;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import com.hudsonmendes.belfastjug.states.ColtState;
import com.hudsonmendes.belfastjug.states.ND4JState;
import com.hudsonmendes.belfastjug.states.SerialState;

public class Benchmarks {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void serial(final SerialState state) {
        double total = 0;
        for (final double[] row : state.getNumbers())
            for (final double element : row)
                total += element;
        assertEquals(
            state.getCheckSum(),
            total,
            tolerance());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void colt(final ColtState state) {
        assertEquals(
            state.getCheckSum(),
            state.getMatrix().zSum(),
            tolerance());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void nd4j(final ND4JState state) {
        assertEquals(
            state.getCheckSum(),
            state.getVector().sumNumber().doubleValue(),
            tolerance());
    }
}
