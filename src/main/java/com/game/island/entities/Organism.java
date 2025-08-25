package com.game.island.entities;

public abstract class Organism implements Reproducible {
    protected double weight;
    protected int x, y; // координаты на карте
    private static long counter = 0;
    protected final long id;

    public Organism(int x, int y, double weight) {
        this.weight = weight;
        this.x = x;
        this.y = y;
        this.id = ++counter;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public double getWeight() {
        return weight;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getId() {
        return id;
    }
}
