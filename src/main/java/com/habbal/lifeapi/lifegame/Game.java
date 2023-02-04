package com.habbal.lifeapi.lifegame;

import com.habbal.lifeapi.dto.Life;

public class Game {
    private final int iterationCount;
    private final Grid grid;
    public Game(Life life) {
        iterationCount = life.getIterationsCount();
        grid = new Grid(life.getSeed());
    }

    public int[][] play() {
        System.out.println("Printing the initial state:");
        grid.printGrid();

        for (int i = 0; i < iterationCount; i++) {
            grid.tick();
            grid.printGrid();
        }

        return grid.getGridAsArray();
    }
}
