package com.game.island.entities.animals;
import com.game.island.config.OrganismConfig;
import com.game.island.entities.Organism;


public abstract class Animal extends Organism {

    public Animal(int x, int y, OrganismConfig CONFIG) {
        super(x,y,CONFIG);
    }

    @Override
    public String toString() {
        return CONFIG.getName();
    }

    public int getMaxSpeed() { return CONFIG.getMaxSpeed(); }
    public double getMaxFood() { return CONFIG.getMaxFood(); }

    public void eat(double foodWeight) {
        setCurrentWeight(getCurrentWeight() + foodWeight);
    }

    public void loseWeight() {
        setCurrentWeight(getCurrentWeight() - CONFIG.getMaxFood() * 0.1);
    }
}
