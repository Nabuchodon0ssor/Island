package com.game.island.entities.animals;

import com.game.island.config.OrganismConfig;

public abstract class Predator extends Animal {
    public Predator(int x, int y, OrganismConfig CONFIG) {
        super(x, y, CONFIG);
    }
}
