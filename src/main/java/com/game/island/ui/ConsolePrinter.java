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

    private static final int CELL_WIDTH = 12; // фиксированная ширина ячейки
    private static final int MAX_TYPES_PER_CELL = 3; // максимум видов для отображения

    public static void printIsland(Island island) {
        clearConsole();

        List<Cell> cells = island.getAllCells();

        StringBuilder sb = new StringBuilder();
        int width = island.getWidth();

        for (int i = 0; i < cells.size(); i++) {
            String cellStr = formatCell(cells.get(i));
            sb.append(String.format("[%-" + CELL_WIDTH + "s]", cellStr)); // выравнивание
            if ((i + 1) % width == 0) sb.append("\n");
        }

        System.out.println(sb);
        printStatistic(island);
    }

    private static String formatCell(Cell cell) {
        if (cell.getAllOrganisms().isEmpty()) {
            return " ";
        }

        Map<String, Long> grouped = cell.getAllOrganisms().stream()
                .collect(Collectors.groupingBy(Organism::getIcon, Collectors.counting()));

        return grouped.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // сначала самые многочисленные
                .limit(MAX_TYPES_PER_CELL)
                .map(e -> e.getKey() + "x" + e.getValue())
                .collect(Collectors.joining(" "));
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

        System.out.println("===== 📊 СТАТИСТИКА =====");
        System.out.printf("🌿 Растений: %d | 🐎 Травоядных: %d | 🐺 Хищников: %d%n",
                plants, herbivores, predators);
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

