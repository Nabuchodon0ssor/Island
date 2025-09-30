package com.game.island;

import com.game.island.simulation.Island;
import com.game.island.simulation.PopulationInitializer;
import com.game.island.simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {

        Island island = new Island(5, 5);
        SimulationEngine engine = new SimulationEngine(island,0);

        PopulationInitializer initializer = new PopulationInitializer(island);
        initializer.populate();

        while (true) {
            engine.tick();
        }
    }
}
