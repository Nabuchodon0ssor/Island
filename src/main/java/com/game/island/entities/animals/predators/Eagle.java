package com.game.island.entities.animals.predators;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.animals.Predator;
import com.game.island.simulation.Cell;
import com.game.island.util.ConfigLoader;

public class Eagle extends Predator {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("/config/organisms/animals/predators/Eagle.yaml", OrganismConfig.class);

    public Eagle(Cell cell) {
        super(cell, CONFIG);
    }
}
