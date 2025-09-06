package com.game.island.entities.animals;
import com.game.island.entities.Organism;
import com.game.island.entities.Plant;
import com.game.island.simulation.Cell;
import com.game.island.config.OrganismConfig;

import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(Cell cell, OrganismConfig CONFIG) {
        super(cell, CONFIG);
    }

    @Override
    public void eat() {
        if (!isAlive()) return;

        List<Organism> plants = currentCell.getOrganismsByType(Plant.class);
        double needed = getFoodCapacity();

        for (Organism o : plants) {
            if (needed <= 0) break;
            if (!o.isAlive()) continue;

            Plant plant = (Plant) o;
            plant.die(); // убрать из клетки и пометить "мертвым"

            double bite = Math.min(plant.getMaxWeight(), needed);
            setCurrentWeight(getCurrentWeight() + bite);
            needed -= bite;
        }
    }

}
