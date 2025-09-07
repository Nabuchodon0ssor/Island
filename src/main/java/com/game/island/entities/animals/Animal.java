package com.game.island.entities.animals;
import com.game.island.config.OrganismConfig;
import com.game.island.entities.Organism;
import com.game.island.simulation.Cell;

import java.util.List;


public abstract class Animal extends Organism {
    protected double currentWeight;

    public Animal(Cell cell, OrganismConfig CONFIG) {
        super(cell,CONFIG);
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

    public double getFoodCapacity() {
        double stomachCapacity = CONFIG.getMaxFood();
        double weightCapacity = getMaxWeight() - getCurrentWeight();
        return Math.min(stomachCapacity, weightCapacity);
    }

    /*1*/@Override
    public void move() {
    }

    /*2*/@Override
        public abstract void eat();

    /*3*/@Override
    public void reproduce() {
        List<Organism> animals = currentCell.getOrganismsByType(this.getClass());

        if (animals.size() >= 2 && animals.size() < getMaxAmount()) {
            double chance = getReproduceChance();
            if (Math.random() < chance) {
                    Animal child = createChild(currentCell);
                    currentCell.addOrganism(child);
            }
        }
    }

    public Animal createChild(Cell cell) {
        try {
            return this.getClass()
                    .getConstructor(Cell.class)
                    .newInstance(cell);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create child for " + this.getClass().getSimpleName(), e);
        }
    }

    /*4*/
    public void loseWeight() {
        setCurrentWeight(getCurrentWeight() - CONFIG.getMaxFood() * 0.1);
        if (getCurrentWeight() < 0) {
            setCurrentWeight(0);
        }
    }
}
