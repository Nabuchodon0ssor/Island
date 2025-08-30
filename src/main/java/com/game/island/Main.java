package com.game.island;

import com.game.island.entities.Organism;
import com.game.island.entities.Plant;
import com.game.island.entities.animals.Animal;
import com.game.island.entities.animals.herbivores.Horse;
import com.game.island.entities.animals.predators.Wolf;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Organism> list = new ArrayList<>();
        list.add(new Horse(1,1));
        list.add(new Wolf(2,2));
        list.add(new Plant(4,5));
        list.add(new Plant(4,6));
        list.add(new Plant(2,7));

        for (Organism organism : list){
            System.out.println(organism.toString());
            System.out.println(organism.getX());
            System.out.println(organism.getY());
            System.out.println(organism.getId());
            System.out.println(organism.getMaxWeight());
            System.out.println(organism.getMaxAmount());


            if(organism instanceof Animal) {
                Animal animal = (Animal) organism;
                System.out.println(animal.getMaxSpeed());
                System.out.println(animal.getMaxFood());
                System.out.println(animal.getCurrentWeight());
            }

        }
    }
}
