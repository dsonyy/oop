package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) throws IllegalArgumentException {
        return Arrays.stream(args).map(arg -> switch (arg) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "l", "left" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(arg + " is not a valid argument!");
        }).filter(Objects::nonNull).toArray(MoveDirection[]::new);
    }

    public static MoveDirection[] parse(String args) throws IllegalArgumentException {
        return parse(args.split("\\s+"));
    }
}
