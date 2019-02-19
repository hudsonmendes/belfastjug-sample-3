package com.hudsonmendes.belfastjug;

import static java.lang.System.gc;

import com.hudsonmendes.belfastjug.states.ColtState;
import com.hudsonmendes.belfastjug.states.ND4JState;
import com.hudsonmendes.belfastjug.states.SerialState;

public class OneOff {

    private static final int ROWS = 100000;
    private static final int COLUMNS = 500;

    public static void main(final String... args) {
        printTimed("serial", OneOff::serial);
        printTimed("colt", OneOff::colt);
        printTimed("nd4j", OneOff::nd4j);
    }

    private static void serial() {
        new Benchmarks().serial(new SerialState(ROWS, COLUMNS));
    }

    private static void colt() {
        new Benchmarks().colt(new ColtState(ROWS, COLUMNS));
    }

    private static void nd4j() {
        new Benchmarks().nd4j(new ND4JState(ROWS, COLUMNS));
    }

    private static void printTimed(final String tag, final Runnable callable) {
        gc();
        final long tic = System.currentTimeMillis();
        callable.run();
        final long toc = System.currentTimeMillis();
        final long elapsed = toc - tic;
        System.out.println(String.format("%s run in %dms", tag, elapsed));
        gc();
    }

}
