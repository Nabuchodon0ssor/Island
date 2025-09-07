package com.game.island.entities;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.interfaces.Eater;
import com.game.island.entities.interfaces.Movable;
import com.game.island.entities.interfaces.Reproducible;
import com.game.island.simulation.Cell;


public abstract class Organism implements Reproducible, Eater, Movable {
    protected final OrganismConfig CONFIG;
    protected Cell currentCell;
    protected volatile boolean alive = true;
    private static long counter = 0;
    protected final long id;

    public Organism(Cell cell, OrganismConfig CONFIG) {
        this.CONFIG = CONFIG;
        this.currentCell = cell;
        this.id = ++counter;
    }

    public Cell getCell() {
        return currentCell;
    }

    public double getMaxWeight() {return CONFIG.getMaxWeight();}
    public int getMaxAmount() { return CONFIG.getMaxAmount(); }
    public long getId() {
        return id;
    }
    public double getReproduceChance(){return CONFIG.getReproduceChance();}


    /*1*/@Override
    public void move() {
        // default, plants don't move
    }

    /*2*/@Override
    public void eat() {
        // default, plants don't eat
    }

    /*3*/@Override
    public abstract void reproduce();

    /*4*/
    public void loseWeight() {
        // default, plants don't lose weight (here)
    }

    @Override
    public abstract boolean canReproduceWith(Organism other);


    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
        if (currentCell != null) {
            currentCell.removeOrganism(this);
        }
    }

    @Override
    public String toString() {
        return CONFIG.getName();
    }
}
