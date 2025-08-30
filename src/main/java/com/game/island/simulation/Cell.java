package com.game.island.simulation;

import com.game.island.entities.Organism;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cell {
    private final int x;
    private final int y;

    private final Map<Class<? extends Organism>, List<Organism>> organisms = new ConcurrentHashMap<>();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void addOrganism(Organism organism) {
        Class<? extends Organism> type = organism.getClass();
        organisms.computeIfAbsent(type, k -> new ArrayList<>()).add(organism);
    }

    public synchronized void removeOrganism(Organism organism) {
        Class<? extends Organism> type = organism.getClass();
        List<Organism> list = organisms.get(type);
        if (list != null) {
            list.remove(organism);
            if (list.isEmpty()) {
                organisms.remove(type);
            }
        }
    }

    public List<Organism> getAllOrganisms() {
        List<Organism> result = new ArrayList<>();
        for (List<Organism> list : organisms.values()) {
            synchronized (list) { // защита на случай, если другой поток меняет этот список
                result.addAll(list);
            }
        }
        return result;
    }

    public List<Organism> getOrganismsByType(Class<? extends Organism> type) {
        return Collections.unmodifiableList(organisms.getOrDefault(type, Collections.emptyList()));
    }

    public boolean hasOrganismType(Class<? extends Organism> type) {
        return organisms.containsKey(type);
    }
}
