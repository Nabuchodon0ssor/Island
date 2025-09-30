package com.game.island.simulation;

import com.game.island.entities.Plant;
import com.game.island.entities.animals.Animal;
import com.game.island.entities.animals.herbivores.*;
import com.game.island.entities.animals.predators.*;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Random;

public class PopulationInitializer {
    private final Island island;
    private final Random random = new Random();

    public PopulationInitializer(Island island) {
        this.island = island;
    }

    public void populate() {
        populatePlants();
        populateAnimals();
    }

    private void populatePlants() {
        for (Cell cell : island.getAllCells()) {
            int plants = 100;
            for (int i = 0; i < plants; i++) {
                cell.addOrganism(new Plant(cell));
            }
        }
    }

    private void populateAnimals() {
        List<Class<? extends Animal>> animalTypes = List.of(
                Horse.class, Wolf.class, Deer.class, Rabbit.class,
                Bear.class, Boa.class, Fox.class, Eagle.class,
                Goat.class, Sheep.class, Duck.class, Buffalo.class,
                Boar.class, Mouse.class, Caterpillar.class
        );

        for (Class<? extends Animal> type : animalTypes) {
            try {
                Animal temp = type.getConstructor(Cell.class).newInstance((Cell) null);
                int maxPerCell = temp.getMaxAmount();
                int totalOnMap = (int) (maxPerCell * 0.2 * island.getAllCells().size());

                Constructor<? extends Animal> constructor = type.getConstructor(Cell.class);

                for (int i = 0; i < totalOnMap; i++) {
                    Cell cell = getRandomCell(island, random);
                    Animal animal = constructor.newInstance(cell);
                    cell.addOrganism(animal);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Cell getRandomCell(Island island, Random random) {
        List<Cell> allCells = island.getAllCells();
        return allCells.get(random.nextInt(allCells.size()));
    }
}
