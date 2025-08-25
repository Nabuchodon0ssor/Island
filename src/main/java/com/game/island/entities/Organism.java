package com.game.island.entities;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.Reproducible;

public abstract class Organism implements Reproducible {
    protected final OrganismConfig CONFIG;

    protected double currentWeight;
    protected int x, y;
    private static long counter = 0;
    protected final long id;

    public Organism(int x, int y, OrganismConfig CONFIG) {
        this.CONFIG = CONFIG;
        this.x = x;
        this.y = y;
        this.currentWeight = CONFIG.getMaxWeight() * 0.7;
        this.id = ++counter;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public double getMaxWeight() {return CONFIG.getMaxWeight();}
    public int getMaxAmount() { return CONFIG.getMaxAmount(); }

    public double getCurrentWeight() {
        return currentWeight;
    }
    public void setCurrentWeight(double currentWeight) {
        double max = CONFIG.getMaxWeight();
        this.currentWeight = Math.max(0.0, Math.min(currentWeight, max));
    }

    public long getId() {
        return id;
    }
}
