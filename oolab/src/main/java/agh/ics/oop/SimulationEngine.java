package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationEngine implements IEngine {
    private final IWorldMap map;
    private final Map<Vector2d, Animal> animals;
    private final List<MoveDirection> directions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.map = map;
        this.directions = List.of(directions);
        this.animals = new HashMap<>();
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            animal.addObserver(map);
            animals.put(animal.getPosition(), animal);
            map.place(animal);
        }
    }

    public Animal getAnimal(int i) {
        return animals.get(i);
    }

    @Override
    public void run() {
        for (int i = 0; i < directions.size(); i++) {
            animals.get(i % animals.size()).move(directions.get(i));
            System.out.println(map.toString());
        }
    }
}