package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse("f b r l f f r r f f f f f f f f".split(" "));
        IWorldMap map = new GrassField(10);
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