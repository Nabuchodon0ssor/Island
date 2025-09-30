package com.game.island.entities.animals.herbivores;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.animals.Herbivore;
import com.game.island.simulation.Cell;
import com.game.island.util.ConfigLoader;

public class Deer extends Herbivore {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("/config/organisms/animals/herbivores/Deer.yaml", OrganismConfig.class);

    public Deer(Cell cell) {
        super(cell, CONFIG);
    }
}
