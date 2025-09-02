package com.game.island.entities;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.interfaces.Reproducible;
import com.game.island.simulation.Cell;

public abstract class Organism implements Reproducible {
    protected final OrganismConfig CONFIG;


    protected int x, y;
    private static long counter = 0;
    protected final long id;

    public Organism(int x, int y, OrganismConfig CONFIG) {
        this.CONFIG = CONFIG;
        this.x = x;
        this.y = y;

        this.id = ++counter;
    }

    @Override
    public String toString() {
        return CONFIG.getName();
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

    public abstract void act(Cell currentCell);

    public long getId() {
        return id;
    }
}
