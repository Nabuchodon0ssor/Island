package com.game.island.entities.animals.predators;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.Organism;
import com.game.island.entities.animals.Predator;
import com.game.island.util.ConfigLoader;


public class Wolf extends Predator {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("config/animals/predators/wolf.yaml");

    public Wolf(int x, int y) {
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
