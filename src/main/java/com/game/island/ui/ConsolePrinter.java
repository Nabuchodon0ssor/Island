package com.game.island.ui;

import com.game.island.entities.Organism;
import com.game.island.simulation.Cell;
import com.game.island.simulation.Island;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsolePrinter {

    public static void printIsland(Island island) {
        List<Cell> cells = island.getAllCells();


        List<String> cellStrings = new ArrayList<>();
        int maxWidth = 0;

        for (Cell cell : cells) {
            String cellStr = formatCell(cell);
            cellStrings.add(cellStr);
            maxWidth = Math.max(maxWidth, cellStr.length());
        }


        StringBuilder sb = new StringBuilder();
        int width = island.getWidth();
        for (int i = 0; i < cellStrings.size(); i++) {
            String padded = padRight(cellStrings.get(i), maxWidth);
            sb.append("[").append(padded).append("]");
            if ((i + 1) % width == 0) sb.append("\n");
        }


        clearConsole();
        System.out.println(sb);
    }

    private static String formatCell(Cell cell) {
        if (cell.getAllOrganisms().isEmpty()) {
            return " "; // пустая клетка
        }
        return cell.getAllOrganisms().stream()
                .collect(Collectors.groupingBy(Organism::getIcon, Collectors.counting()))
                .entrySet().stream()
                .map(e -> e.getKey() + "x" + e.getValue())
                .collect(Collectors.joining(" "));
    }

    private static String padRight(String s, int width) {
        return String.format("%-" + width + "s", s);
    }

    private static void clearConsole() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }
}
