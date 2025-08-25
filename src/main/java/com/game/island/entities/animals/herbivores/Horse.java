package com.game.island.entities.animals.herbivores;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.animals.Herbivore;
import com.game.island.entities.Organism;
import com.game.island.util.ConfigLoader;


public class Horse extends Herbivore {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("config/animals/herbivores/horse.yaml");

    public Horse(int x, int y) {
        super(x, y, CONFIG);
    }


    @Override
    public void reproduce() {

    }

    @Override
    public boolean canReproduceWith(Organism other) {
        return false;
    }
}
