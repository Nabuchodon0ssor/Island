package com.game.island.simulation;

import java.util.ArrayList;
import java.util.List;

public class Island {
    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final List<Cell> allCells;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        this.allCells = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = new Cell(this, x, y);
                grid[y][x] = cell;
                allCells.add(cell);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return grid[y][x];
    }

    public List<Cell> getNeighbors(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        List<Cell> neighbors = new ArrayList<>();

        int[][] deltas = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1, 0},           {1, 0},
                {-1, 1},  {0, 1},  {1, 1}
        };

        for (int[] d : deltas) {
            Cell neighbor = getCell(x + d[0], y + d[1]);
            if (neighbor != null) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public List<Cell> getAllCells() {
        return allCells;
    }
}
