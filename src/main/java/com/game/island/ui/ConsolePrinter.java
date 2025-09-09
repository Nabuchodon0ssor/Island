package com.game.island.ui;

import com.game.island.entities.Organism;
import com.game.island.simulation.Cell;
import com.game.island.simulation.Island;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsolePrinter {

    public static void printIsland(Island island) {
        int width = island.getWidth();
        int height = island.getHeight();

        Map<String, Long> totalCount = new HashMap<>(); // для статистики

        for (int y = 0; y < height; y++) {
            int maxLines = 1;
            List<List<String>> rowCells = new ArrayList<>();

            for (int x = 0; x < width; x++) {
                Cell cell = island.getCell(x, y);
                Map<String, Long> grouped = cell.getAllOrganisms().stream()
                        .collect(Collectors.groupingBy(Organism::getIcon, Collectors.counting()));


                grouped.forEach((k,v) -> totalCount.merge(k,v,Long::sum));

                List<String> lines;
                if (grouped.isEmpty()) {
                    lines = List.of(".");
                } else {
                    lines = grouped.entrySet().stream()
                            .map(e -> e.getKey() + "x" + e.getValue())
                            .toList();
                }

                maxLines = Math.max(maxLines, lines.size());
                rowCells.add(lines);
            }


            for (int line = 0; line < maxLines; line++) {
                for (List<String> cellLines : rowCells) {
                    if (line < cellLines.size()) {
                        System.out.printf("%-8s", cellLines.get(line));
                    } else {
                        System.out.printf("%-8s", "");
                    }
                }
                System.out.println();
            }
        }

        System.out.println("=".repeat(width * 8));


        System.out.println("=== Statistics ===");
        totalCount.forEach((icon, count) -> System.out.println(icon + ": " + count));
        System.out.println();
    }
}
