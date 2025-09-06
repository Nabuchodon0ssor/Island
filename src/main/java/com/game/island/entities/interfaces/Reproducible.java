package com.game.island.entities.interfaces;
import com.game.island.entities.Organism;

public interface Reproducible {
    void reproduce();
    boolean canReproduceWith(Organism other);
}
