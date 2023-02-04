package com.habbal.lifeapi.lifegame;

import com.habbal.lifeapi.dto.Life;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void PlayReturnsCorrectValueForBlinker() {
        Life life = new Life(1, new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}});

        Game game = new Game(life);
        int[][] result = game.play();

        int[][] expectedResult = new int[][] {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}};

        assertTrue(Arrays.deepEquals(expectedResult, result));
    }


    @Test
    public void PlayReturnsCorrectValueForGliderOutOfScope() {
        Life life = new Life(2, new int[][]{
                {0, 1, 0},
                {1, 1, 0},
                {1, 0, 1}});

        Game game = new Game(life);
        int[][] result = game.play();

        int[][] expectedResult = new int[][] {
                {1, 1, 0},
                {1, 0, 0},
                {0, 1, 0}};

        assertTrue(Arrays.deepEquals(expectedResult, result));
    }

    @Test
    public void aliveCellWithLessThanTwoNeighboursDies() {
        Life life = new Life(1, new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 0, 0}});

        Game game = new Game(life);
        int[][] result = game.play();

        int[][] expectedResult = new int[][] {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};

        assertTrue(Arrays.deepEquals(expectedResult, result));
    }

    @Test
    public void aliveCellWithTwoOrThreeNeighboursStaysAlive() {
        Life life = new Life(1, new int[][]{
                {0, 1, 1},
                {0, 1, 1},
                {0, 0, 0}});

        Game game = new Game(life);
        int[][] result = game.play();

        int[][] expectedResult = new int[][] {
                {0, 1, 1},
                {0, 1, 1},
                {0, 0, 0}};

        assertTrue(Arrays.deepEquals(expectedResult, result));
    }

    @Test
    public void aliveCellWithMoreThanThreeNeighboursDies() {
        Life life = new Life(1, new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}});

        Game game = new Game(life);
        int[][] result = game.play();

        int[][] expectedResult = new int[][] {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}};

        assertTrue(Arrays.deepEquals(expectedResult, result));
    }

    @Test
    public void deadCellWithThreeNeighboursBecomesAlive() {
        Life life = new Life(1, new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}});

        Game game = new Game(life);
        int[][] result = game.play();

        int[][] expectedResult = new int[][] {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}};

        assertTrue(Arrays.deepEquals(expectedResult, result));
    }
}