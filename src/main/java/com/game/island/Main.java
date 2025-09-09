package com.game.island;


import com.game.island.entities.Plant;
import com.game.island.entities.animals.herbivores.Horse;
import com.game.island.entities.animals.predators.Wolf;
import com.game.island.simulation.Cell;
import com.game.island.simulation.Island;
import com.game.island.simulation.SimulationEngine;
import com.game.island.ui.ConsolePrinter;

public class Main {
    public static void main(String[] args) {
        // Создаём остров 5x5 клеток
        Island island = new Island(5, 5);

        // Размещаем растения
        for (Cell cell : island.getAllCells()) {
            int plants = 10;
            for (int i = 0; i < plants; i++) {
                cell.addOrganism(new Plant(cell));
            }
        }

        // Размещаем травоядных
        Cell randomCell = island.getCell(0, 0);
        randomCell.addOrganism(new Horse(randomCell));

        randomCell = island.getCell(2, 2);
        randomCell.addOrganism(new Wolf(randomCell));

        // Размещаем хищников
        randomCell = island.getCell(4, 4);
        randomCell.addOrganism(new Horse(randomCell));


        SimulationEngine engine = new SimulationEngine(island,0);


        System.out.println("=== Initial state ===");
        ConsolePrinter.printIsland(island);


        for (int tick = 1; tick <= 5; tick++) {
            System.out.println("=== Tick " + tick + " ===");
            engine.tick();
            ConsolePrinter.printIsland(island);
        }

        engine.stop();
    }
}
