package com.game.island.entities.animals.herbivores;

import com.game.island.config.AnimalConfig;
import com.game.island.entities.Organism;
import com.game.island.entities.animals.Animal;
import com.game.island.util.ConfigLoader;


public class Horse extends Animal {
    private static final AnimalConfig config =
            ConfigLoader.load("config/animals/herbivores/horse.yaml");

    public Horse(int x, int y) {
        super(x, y, config.getMaxWeight(), config.getMaxWeight(), config.getMaxSpeed(), config.getMaxFood());
    }

    @Override
    public String toString() {
        return config.getIcon();
    }

    @Override
    public void reproduce() {

    }

    @Override
    public boolean canReproduceWith(Organism other) {
        return false;
    }
}
