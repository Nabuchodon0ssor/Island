package com.game.island.simulation;


import com.game.island.entities.Organism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SimulationEngine {
    private final Island island;
    private final ExecutorService executor;

    public SimulationEngine(Island island, int threadCount) {
        this.island = island;
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void tick() {
        // 1. Фаза движения
        runPhase(organism -> organism.move());

        // 2. Фаза питания
        runPhase(organism -> organism.eat());

        // 3. Фаза размножения
        runPhase(organism -> organism.reproduce());

        // 4. Фаза потери веса / смерти
        runPhase(organism -> organism.loseWeight());
    }

    // Вспомогательный метод
    private void runPhase(Consumer<Organism> action) {
        List<Future<?>> futures = new ArrayList<>();

        for (Cell cell : island.getAllCells()) {
            for (Organism o : cell.getAllOrganisms()) {
                futures.add(executor.submit(() -> action.accept(o)));
            }
        }

        // Ждём завершения всех задач этой фазы
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Tick interrupted");
            } catch (ExecutionException e) {
                System.err.println("Error in the organism: " + e.getCause());
                e.getCause().printStackTrace();
            }
        }
    }


    public void stop() {
        executor.shutdown();
        //executor.awaitTermination(30, TimeUnit.SECONDS);
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
