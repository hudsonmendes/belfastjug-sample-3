package com.hudsonmendes.belfastjug;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(final String[] args) {
        System.out.println(new App().getGreeting());
    }
}
