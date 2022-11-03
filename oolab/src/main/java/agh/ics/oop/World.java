package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class World {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        OptionsParser optionsParser = new OptionsParser();
//        Animal animal = new Animal();
//        while (true) {
//            Arrays.stream(optionsParser.parse(scanner.nextLine()))
//                .forEach(cmd -> {
//                    animal.move(cmd);
//                    System.out.println(animal.toString());
//                });
//        }
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "b", "f", "b"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

    }

    private static void run(MoveDirection[] cmds) {
        System.out.println("Start");
        Arrays.stream(cmds).forEach(cmd -> System.out.println(switch (cmd) {
            case FORWARD -> "Zwierzak idzie do przodu";
            case RIGHT -> "Zwierzak skręca w prawo";
            case BACKWARD -> "Zwierzak idzie do tyłu";
            case LEFT -> "Zwierzak skręca w lewo";
        }));
        System.out.println("Stop");
    }
}