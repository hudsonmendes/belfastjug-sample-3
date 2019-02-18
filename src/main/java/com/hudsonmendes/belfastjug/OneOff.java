package com.hudsonmendes.belfastjug;

public class OneOff {

    public static void main(final String... args) {
        final Benchmarks b = new Benchmarks();
        b.serial();
        b.colt();
        b.nd4j();
    }

}
