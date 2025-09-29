package com.game.island.entities.animals;

import com.game.island.config.OrganismConfig;
import com.game.island.entities.Organism;
import com.game.island.simulation.Cell;
import java.util.Random;
import java.util.List;

public abstract class Predator extends Animal {
    private final Random random = new Random();

    public Predator(Cell cell, OrganismConfig CONFIG) {
        super(cell, CONFIG);
    }

    @Override
    public void eat() {
        if (!isAlive()) return;

        List<Organism> organisms = currentCell.getAllOrganisms();
        double needed = getFoodCapacity();

        for (Organism o : organisms) {
            if (needed <= 0) break;
            if (o == null) continue;
            if (!o.isAlive()) continue;
            if (o == this) continue;

            String preyName = o.getClass().getSimpleName();
            Integer chance = CONFIG.getDiet().get(preyName);

            if (chance != null && random.nextInt(100) < chance) {
                o.die();

                double bite = Math.min(o.getMaxWeight(), needed);
                setCurrentWeight(getCurrentWeight() + bite);
                needed -= bite;
            }
        }
    }


    @Override
    protected boolean isCellSuitable(Cell cell) {
        return cell.getAllOrganisms().stream()
                .anyMatch(o -> o instanceof Herbivore);
    }

}
