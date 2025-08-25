package com.game.island.entities;

public interface Reproducible {
    void reproduce();
    boolean canReproduceWith(Organism other);
}
