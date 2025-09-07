package com.game.island.entities;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.interfaces.Reproducible;
import com.game.island.simulation.Cell;
import com.game.island.util.ConfigLoader;

import java.util.List;

public class Plant extends Organism implements Reproducible {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("/config/organisms/plant.yaml", OrganismConfig.class);

    public Plant(Cell cell) {
        super(cell, CONFIG);
    }

    @Override
    public void reproduce() {
        List<Organism> plants = currentCell.getOrganismsByType(Plant.class);
        if (plants.size() < getMaxAmount()) {
            if (Math.random() < 0.1) {
                Plant child = new Plant(currentCell);
                currentCell.addOrganism(child);
            }
        }
    }

    @Override
    public boolean canReproduceWith(Organism other) {
        return other instanceof Plant;
    }
}
