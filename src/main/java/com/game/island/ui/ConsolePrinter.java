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

    private static final int CELL_COLUMNS = 3;  // сколько организмов в строке клетки
    private static final int CELL_ROWS = 5;     // сколько строк внутри клетки

    public static void printIsland(Island island) {
        clearConsole();


        List<Cell> cells = island.getAllCells();
        int width = island.getWidth();

        // Преобразуем каждую клетку в список строк (рамка + содержимое)
        List<List<String>> renderedCells = cells.stream()
                .map(ConsolePrinter::renderCell)
                .toList();

        // Каждая клетка = N строк, печатаем "построчно"
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

        // Статистика
        printStatistic(island);
    }

    private static List<String> renderCell(Cell cell) {
        Map<String, Long> grouped = cell.getAllOrganisms().stream()
                .collect(Collectors.groupingBy(Organism::getIcon, Collectors.counting()));

        List<String> entries = grouped.entrySet().stream()
                .map(e -> e.getKey() + "x" + e.getValue())
                .toList();

        // Разбиваем на строки по CELL_COLUMNS элементов
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < entries.size(); i += CELL_COLUMNS) {
            lines.add(
                    entries.subList(i, Math.min(i + CELL_COLUMNS, entries.size()))
                            .stream()
                            .collect(Collectors.joining(" "))
            );
        }

        // если строк меньше, чем CELL_ROWS → добавляем пустые
        while (lines.size() < CELL_ROWS) {
            lines.add("");
        }

        // обрезаем лишние строки (если организмов слишком много)
        if (lines.size() > CELL_ROWS) {
            lines = lines.subList(0, CELL_ROWS);
            int lastIdx = CELL_ROWS - 1;
            lines.set(lastIdx, "..."); // знак, что не всё влезло
        }

        int maxWidth = lines.stream().mapToInt(String::length).max().orElse(1);

        // Добавляем рамку
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
        System.out.printf("🌿 Растений: %d   🐑 Травоядных: %d   🐺 Хищников: %d%n",
                plants, herbivores, predators);
    }

    private static void clearConsole() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }
}

