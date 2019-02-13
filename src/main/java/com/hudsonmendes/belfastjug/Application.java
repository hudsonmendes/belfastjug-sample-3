package com.hudsonmendes.belfastjug;

import com.hudsonmendes.belfastjug.data.VectorDataGenerator;
import com.hudsonmendes.belfastjug.data.VectorShape;
import com.hudsonmendes.belfastjug.operations.SerialBatchMath;
import com.hudsonmendes.belfastjug.operations.VectorialBatchMath;
import com.hudsonmendes.belfastjug.performance.TimedExecutor;
import com.hudsonmendes.belfastjug.performance.TimedOperation;
import com.hudsonmendes.belfastjug.utils.MathUtils;

public class Application {

    private static final VectorShape SHAPE_ONE_BY_ONE_MILLION = VectorShape.builder()
        .withY(100)
        .withX(1)
        .build();

    private static final VectorDataGenerator VECTOR_DATA_GENERATOR = new VectorDataGenerator(SHAPE_ONE_BY_ONE_MILLION);

    public static void main(final String[] args) {

        final var x = VECTOR_DATA_GENERATOR.generate();
        final var w = VECTOR_DATA_GENERATOR.generate();
        final var b = VECTOR_DATA_GENERATOR.zeros();

        final var timedZ1 = new TimedExecutor<>(() -> serialLinearActivation(x, w, b)).executeAndTime(MathUtils::checkSum);
        printStatsOf(timedZ1, "Serial");

        final var timedZ2 = new TimedExecutor<>(() -> vectorialLinearActivation(x, w, b)).executeAndTime(MathUtils::checkSum);
        printStatsOf(timedZ2, "Vectorial");
    }

    private static double[][] serialLinearActivation(final double[][] x, final double[][] w, final double[] b) {
        final var serialMath = new SerialBatchMath();
        return serialMath.sumAll(serialMath.dotAll(x, w), b);
    }

    private static double[][] vectorialLinearActivation(final double[][] x, final double[][] w, final double[] b) {
        final var vectorialMath = new VectorialBatchMath();
        return vectorialMath.sumAll(vectorialMath.dotAll(x, w), b);
    }

    private static void printStatsOf(final TimedOperation<double[][]> timedZ, final String tag) {
        System.out.println(String.format("%s: %dms", tag, timedZ.getElapsedMillis()));
    }
}
