package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class World {
    public static void main(String[] args) {
        System.out.println("Program wystartował");
        run(convert(args));
        System.out.println("Program zakończył działanie");
    }

    private static Direction[] convert(String[] args) {
        return Arrays.stream(args).map(arg -> switch (arg) {
            case "f" -> Direction.FORWARD;
            case "r" -> Direction.RIGHT;
            case "b" -> Direction.BACKWARD;
            case "l" -> Direction.LEFT;
            default -> null;
        }).filter(Objects::nonNull).toArray(Direction[]::new);
    }

    private static void run(Direction[] cmds) {
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