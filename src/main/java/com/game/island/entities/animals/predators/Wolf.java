package com.game.island.entities.animals.predators;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.Organism;
import com.game.island.entities.animals.Predator;
import com.game.island.simulation.Cell;
import com.game.island.util.ConfigLoader;


public class Wolf extends Predator {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("/config/organisms/animals/predators/wolf.yaml", OrganismConfig.class);

    public Wolf(Cell cell) {
        super(cell, CONFIG);
    }


    @Override
    public boolean canReproduceWith(Organism other) {
        return false;
    }
}
