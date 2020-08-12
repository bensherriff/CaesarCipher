package com.bensherriff.caesar.app;

public class Guess<Integer, Double> {

    private final int key;
    private final double certainty;

    public Guess(Integer key, Double certainty) {
        this.key = (int) key;
        this.certainty = (double) certainty;

    }

    public int key() {
        return this.key;
    }

    public double certainty() {
        return this.certainty;
    }

    public double certaintyPercent() {
        return this.certainty * 100;
    }

    public Guess<Integer, Double> compareTo(Guess<Integer, Double> guess) {
        if (this.certainty >= guess.certainty) {
            return this;
        } else {
            return guess;
        }
    }
}
