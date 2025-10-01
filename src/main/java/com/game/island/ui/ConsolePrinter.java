package com.game.island.ui;

import com.game.island.entities.Organism;
import com.game.island.entities.Plant;
import com.game.island.entities.animals.Herbivore;
import com.game.island.entities.animals.Predator;
import com.game.island.simulation.Cell;
import com.game.island.simulation.Island;

import java.util.*;
import java.util.stream.Collectors;

public class ConsolePrinter {

    private static final int CELL_COLUMNS = 3;
    private static final int CELL_ROWS = 5;
    private static int tickCounter = 0;

    public static void printIsland(Island island) {
        clearConsole();
        tickCounter++;
        System.out.println("=== Tick #" + tickCounter + " ===\n");

        List<Cell> cells = island.getAllCells();
        int width = island.getWidth();

        List<List<String>> renderedCells = cells.stream()
                .map(ConsolePrinter::renderCell)
                .toList();


        int cellHeight = renderedCells.get(0).size();

        for (int row = 0; row < cells.size() / width; row++) {
            for (int line = 0; line < cellHeight; line++) {
                for (int col = 0; col < width; col++) {
                    int idx = row * width + col;
                    System.out.print(renderedCells.get(idx).get(line) + " ");
                }
                System.out.println();
            }
        }

        printStatistic(island);
    }

    private static List<String> renderCell(Cell cell) {
        Map<String, Long> grouped = cell.getAllOrganisms().stream()
                .collect(Collectors.groupingBy(Organism::getIcon, Collectors.counting()));

        List<String> entries = grouped.entrySet().stream()
                .map(e -> e.getKey() + "x" + e.getValue())
                .toList();


        List<String> lines = new ArrayList<>();
        for (int i = 0; i < entries.size(); i += CELL_COLUMNS) {
            lines.add(
                    entries.subList(i, Math.min(i + CELL_COLUMNS, entries.size()))
                            .stream()
                            .collect(Collectors.joining(" "))
            );
        }

        while (lines.size() < CELL_ROWS) {
            lines.add("");
        }

        if (lines.size() > CELL_ROWS) {
            lines = lines.subList(0, CELL_ROWS);
            int lastIdx = CELL_ROWS - 1;
            lines.set(lastIdx, "...");
        }

        int maxWidth = lines.stream().mapToInt(String::length).max().orElse(1);

        List<String> box = new ArrayList<>();
        String top = "┌" + "─".repeat(maxWidth + 2) + "┐";
        String bottom = "└" + "─".repeat(maxWidth + 2) + "┘";
        box.add(top);

        for (String line : lines) {
            String padded = String.format("%-" + maxWidth + "s", line);
            box.add("│ " + padded + " │");
        }

        box.add(bottom);
        return box;
    }

    private static void printStatistic(Island island) {
        int plants = 0;
        int herbivores = 0;
        int predators = 0;

        for (Cell[] row : island.getGrid()) {
            for (Cell cell : row) {
                plants += cell.getOrganismsByType(Plant.class).size();
                herbivores += cell.getOrganismsByType(Herbivore.class).size();
                predators += cell.getOrganismsByType(Predator.class).size();
            }
        }

        System.out.println();
        System.out.printf("🌿 Plants: %d   🐑 Herbivores: %d   🐺 Predators: %d%n",
                plants, herbivores, predators);
    }

    private static void clearConsole() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }
}

