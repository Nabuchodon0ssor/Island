package com.game.island.simulation;

public class Island {
    private final int width;
    private final int height;
    private final Cell[][] cells;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }
}
