package com.game.island.entities.animals;
import com.game.island.entities.Organism;
import com.game.island.entities.Plant;
import com.game.island.simulation.Cell;
import com.game.island.config.OrganismConfig;

import java.util.Iterator;
import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(Cell cell, OrganismConfig CONFIG) {
        super(cell, CONFIG);
    }

    @Override
    public void eat() {
        if (!isAlive()) return;


        double needed = getFoodCapacity();

        synchronized (currentCell) {
            List<Organism> plants = currentCell.getOrganismsByType(Plant.class);

            Iterator<Organism> it = plants.iterator();
            while (it.hasNext() && needed > 0) {
                Organism o = it.next();
                if (!o.isAlive()) continue;

                Plant plant = (Plant) o;
                plant.die();

                double bite = Math.min(plant.getMaxWeight(), needed);
                setCurrentWeight(getCurrentWeight() + bite);
                needed -= bite;
            }
        }
    }

    @Override
    protected boolean isCellSuitable(Cell cell) {
        boolean food = cell.getAllOrganisms().stream()
                .anyMatch(o -> o instanceof Plant);

        boolean safe = cell.getAllOrganisms().stream()
                .noneMatch(o -> o instanceof Predator);

        return food && safe;
    }

}
