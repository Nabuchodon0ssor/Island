package com.game.island.simulation;


import com.game.island.entities.Organism;
import com.game.island.ui.ConsolePrinter;

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
        int threads = (threadCount > 0)
                ? threadCount
                : Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void tick() {
        runPhase(Organism::move);
        runPhase(Organism::eat);
        runPhase(Organism::reproduce);
        runPhase(Organism::loseWeight);

        ConsolePrinter.printIsland(island);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void runPhase(Consumer<Organism> action) {
        List<Future<?>> futures = new ArrayList<>();

        for (Cell cell : island.getAllCells()) {
            for (Organism o : cell.getAllOrganisms()) {
                futures.add(executor.submit(() -> action.accept(o)));
            }
        }

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
