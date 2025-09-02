package com.game.island.simulation;


import com.game.island.entities.Organism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimulationEngine {
    private final Island island;
    private final ExecutorService executor;

    public SimulationEngine(Island island, int threadCount) {
        this.island = island;
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void tick() {
        List<Future<?>> futures = new ArrayList<>();
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Cell cell = island.getCell(x, y);

                List<Organism> organisms = cell.getAllOrganisms();


                for (Organism organism : organisms) {
                    futures.add(executor.submit(() -> {
                        organism.act(cell);
                    }));
                }
            }
        }
        for (Future<?> future : futures) {
            try {
                future.get();
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
