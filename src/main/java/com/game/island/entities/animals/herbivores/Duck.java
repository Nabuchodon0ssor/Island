package com.game.island.entities.animals.herbivores;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.animals.Herbivore;
import com.game.island.simulation.Cell;
import com.game.island.util.ConfigLoader;

public class Duck extends Herbivore {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("/config/organisms/animals/herbivores/Duck.yaml", OrganismConfig.class);

    public Duck(Cell cell) {
        super(cell, CONFIG);
    }
}
