package com.game.island.entities.animals.herbivores;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.animals.Herbivore;
import com.game.island.entities.Organism;
import com.game.island.simulation.Cell;
import com.game.island.util.ConfigLoader;


public class Horse extends Herbivore {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("/config/organisms/animals/herbivores/Horse.yaml", OrganismConfig.class);

    public Horse(Cell cell) {
        super(cell, CONFIG);
    }

    @Override
    public boolean canReproduceWith(Organism other) {
        return false;
    }
}
