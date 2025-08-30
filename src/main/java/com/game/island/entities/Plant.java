package com.game.island.entities;

import com.game.island.config.OrganismConfig;
import com.game.island.util.ConfigLoader;

public class Plant extends Organism {
    private static final OrganismConfig CONFIG =
            ConfigLoader.load("/config/organisms/plant.yaml", OrganismConfig.class);

    public Plant(int x, int y) {
        super(x, y, CONFIG);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public boolean canReproduceWith(Organism other) {
        return other instanceof Plant;
    }
}
