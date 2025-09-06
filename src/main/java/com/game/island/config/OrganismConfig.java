package com.game.island.config;

import java.util.HashMap;
import java.util.Map;

public class OrganismConfig {
    private String name;
    private String icon;
    private double maxWeight;
    private int maxAmount;
    private int maxSpeed;
    private double maxFood;
    private Map<String, Integer> diet = new HashMap<>();

    public Map<String, Integer> getDiet() {
        return diet;
    }

    public String getName() {
        return name;
    }
    public String getIcon() {
        return icon;
    }
    public double getMaxWeight() {
        return maxWeight;
    }
    public int getMaxAmount() {
        return maxAmount;
    }
    public int getMaxSpeed() {
        return maxSpeed;
    }
    public double getMaxFood() {
        return maxFood;
    }
}
