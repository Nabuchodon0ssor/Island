package com.game.island.entities.animals;

import com.game.island.config.OrganismConfig;

public abstract class Herbivore extends Animal {
    public Herbivore(int x, int y, OrganismConfig CONFIG) {
        super(x, y, CONFIG);
    }
}
