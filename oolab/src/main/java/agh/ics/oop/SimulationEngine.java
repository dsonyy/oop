package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationEngine implements IEngine {
    private final IWorldMap map;
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private int state;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.map = map;
        this.directions = List.of(directions);
        this.animals = new ArrayList<>();
        this.state = 0;
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            animals.add(animal);
            map.place(animal);
        }
    }

    @Override
    public Animal getAnimal(int i) {
        return animals.get(i);
    }

    @Override
    public void run() {
        for (int i = 0; i < directions.size(); i++) {
            update();
        }
    }

    public void update() {
        if (state >= directions.size())
            return;
        animals.get(state % animals.size()).move(directions.get(state));
        state++;
    }
}