package com.game.island;


import com.game.island.entities.Plant;
import com.game.island.entities.animals.Herbivore;
import com.game.island.entities.animals.Predator;
import com.game.island.entities.animals.herbivores.Horse;
import com.game.island.entities.animals.predators.Wolf;
import com.game.island.simulation.Cell;
import com.game.island.simulation.Island;
import com.game.island.simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {

        Island island = new Island(5, 5);
        SimulationEngine engine = new SimulationEngine(island,0);


        for (Cell cell : island.getAllCells()) {
            int plants = 10 ;
            for (int i = 0; i < plants; i++) {
                cell.addOrganism(new Plant(cell));
            }
        }


        Cell randomCell = island.getCell(0, 0);
        randomCell.addOrganism(new Horse(randomCell));
        randomCell = island.getCell(4, 4);
        randomCell.addOrganism(new Horse(randomCell));
        randomCell = island.getCell(1, 2);
        randomCell.addOrganism(new Horse(randomCell));
        randomCell = island.getCell(3, 4);
        randomCell.addOrganism(new Horse(randomCell));

        randomCell = island.getCell(2, 2);
        randomCell.addOrganism(new Wolf(randomCell));
        randomCell = island.getCell(0, 3);
        randomCell.addOrganism(new Wolf(randomCell));






        while (true) {
            engine.tick();
        }

    }
}
