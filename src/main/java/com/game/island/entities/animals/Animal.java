package com.game.island.entities.animals;
import com.game.island.entities.Organism;


public abstract class Animal extends Organism {

    protected double maxWeight;
    protected int speed;
    protected double foodNeeded;

    public Animal(int x, int y, double weight, double maxWeight, int speed, double foodNeeded) {
        super(x, y, weight);
        this.maxWeight = maxWeight;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
    }
}
