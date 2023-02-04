package com.habbal.lifeapi.lifegame;

import java.util.HashSet;
import java.util.Optional;

public class Grid {

    private HashSet<Node> aliveNodes = new HashSet<>();

    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    private final int seedXLength;
    private final int seedYLength;

    public Grid(int[][] seed) {
        maxX = seed.length;
        maxY = seed[0].length;

        seedXLength = maxX;
        seedYLength = maxY;

        for (int i = 0; i < seed.length; i++) {
            for (int j = 0; j < seed[i].length; j++) {
                if(seed[i][j] == 1) {
                    aliveNodes.add(new Node(i,j));
                }
            }
        }
    }

    public int[][] getGridAsArray() {
        int[][] gridAsArray = new int[maxX][maxY];
        for (int i = 0; i < seedXLength; i++) {
            for (int j = 0; j < seedYLength; j++) {
                gridAsArray[i][j] = getNodeForCoordination(i,j).isPresent() ? 1 : 0;
            }
        }

        return gridAsArray;
    }

    public void printGrid() {
        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                boolean nodeIsAlive = getNodeForCoordination(i, j).isPresent();

                if(nodeIsAlive) {
                    System.out.print("█ ");
                }
                else {
                    System.out.print("░ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void tick() {
        HashSet<Node> newAliveNodes = new HashSet<>();

        for (int i = minX-1; i < maxX+1; i++) {
            for (int j = minY-1; j < maxY+1; j++) {
                long aliveNeighbourNodesCount = getAliveNeighbourNodesCount(i, j);
                Optional<Node> node = getNodeForCoordination(i, j);
                if(node.isPresent()) {
                    if(aliveNeighbourNodesCount >= 2 && aliveNeighbourNodesCount <= 3) {
                        newAliveNodes.add(node.get());
                    }
                }
                else {
                    if(aliveNeighbourNodesCount == 3) {
                        newAliveNodes.add(new Node(i,j));
                    }
                }
            }
        }

        this.aliveNodes = newAliveNodes;
        reevaluateGridSize();
    }

    private void reevaluateGridSize() {
        int minX = 0;
        int minY = 0;
        int maxX = this.maxX;
        int maxY = this.maxY;

        for(var node : aliveNodes) {
            int nodeX = node.x();
            int nodeY = node.y();

            if(nodeX-1 < minX) {
                minX = nodeX-1;
            }
            if(nodeX+1 > maxX) {
                maxX = nodeX +1;
            }

            if(nodeY-1 < minY) {
                minY = nodeY-1;
            }
            if(nodeY+1 > maxY) {
                maxY = nodeY +1;
            }
        }

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    private long getAliveNeighbourNodesCount(int x, int y) {
        return aliveNodes.stream().filter(node -> {
            int xSubtractionValue = node.x() - x;
            int ySubtractionValue = node.y() - y;

            boolean isSameNode = xSubtractionValue == 0 && ySubtractionValue == 0;
            boolean isNeighbouringNode = (xSubtractionValue <= 1 && xSubtractionValue >= -1)
                                        && (ySubtractionValue <= 1 && ySubtractionValue >= -1);
            if (isSameNode) {
                return false;
            }

            return isNeighbouringNode;
        })
                .count();
    }

    private Optional<Node> getNodeForCoordination(int x, int y) {
        return aliveNodes.stream().filter(node -> node.x() == x && node.y() == y).findFirst();
    }
}