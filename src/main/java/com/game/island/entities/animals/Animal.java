package com.game.island.entities.animals;
import com.game.island.config.OrganismConfig;
import com.game.island.entities.Organism;


public abstract class Animal extends Organism {
    protected double currentWeight;

    public Animal(int x, int y, OrganismConfig CONFIG) {
        super(x,y,CONFIG);
        this.currentWeight = CONFIG.getMaxWeight() * 0.7;
    }

    public boolean isAlive() {
        return currentWeight > 0;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }
    public void setCurrentWeight(double currentWeight) {
        double max = CONFIG.getMaxWeight();
        this.currentWeight = Math.max(0.0, Math.min(currentWeight, max));
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
