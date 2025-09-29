package com.game.island.entities;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.interfaces.Reproducible;
import com.game.island.simulation.Cell;
import com.game.island.util.ConfigLoader;


public class Plant extends Organism implements Reproducible {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("/config/organisms/plant.yaml", OrganismConfig.class);

    public Plant(Cell cell) {
        super(cell, CONFIG);
    }

    @Override
    public void reproduce() {
        double chance = getReproduceChance();
        long count = currentCell.getOrganismsByType(Plant.class).size();
        if (count < getMaxAmount() && Math.random() < chance) {
            Plant child = new Plant(currentCell);
            currentCell.addOrganism(child);
        }
    }

    @Override
    public boolean canReproduceWith(Organism other) {
        return other instanceof Plant;
    }
}
