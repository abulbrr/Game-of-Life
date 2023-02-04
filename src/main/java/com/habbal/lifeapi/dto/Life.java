package com.habbal.lifeapi.dto;

public class Life {
    private final int iterationsCount;
    private final int[][] seed;

    public Life(int iterationsCount, int[][] seed) {
        this.iterationsCount = iterationsCount;
        this.seed = seed;
    }

    public int getIterationsCount() {
        return iterationsCount;
    }

    public int[][] getSeed() {
        return seed;
    }
}
