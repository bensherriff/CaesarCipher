package com.bensherriff.caesar.app;

public class Timer {

    private long startTime;
    private long stopTime;

    public Timer() {
        startTime = 0;
        stopTime = 0;
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        stopTime = System.nanoTime();
    }

    public void reset() {
        startTime = 0;
        stopTime = 0;
    }

    public String output() {
        return "Duration: " + duration() + " milliseconds";
    }

    private double duration() {
        long time = (stopTime - startTime);
        startTime = 0;
        stopTime = 0;
        return (double) time / (long) 1000000;
    }
}
