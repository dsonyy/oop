package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationEngine implements IEngine {
    private final GrassField map;
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private int state;
    private final App app;
    private final long delay = 400;

    public SimulationEngine(MoveDirection[] directions, GrassField map, Vector2d[] positions, App app, MapDirection orientation) {
        this.map = map;
        this.directions = List.of(directions);
        this.animals = new ArrayList<>();
        this.state = 0;
        this.app = app;
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position, orientation);
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
        Platform.runLater(() -> {
            this.app.renderMap(this.map);
        });
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < this.directions.size(); i++) {
            Animal currentAnimal = this.animals.get(i % this.animals.size());
            currentAnimal.move(this.directions.get(i));
            Platform.runLater(() -> {
                this.app.renderMap(this.map);
            });
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

//    @Override
//    public void run() {
//        for (int i = 0; i < directions.size(); i++) {
//            update();
//        }
//    }
//
//    public void update() {
//        if (state >= directions.size())
//            return;
//        animals.get(state % animals.size()).move(directions.get(state));
//        state++;
//    }
}