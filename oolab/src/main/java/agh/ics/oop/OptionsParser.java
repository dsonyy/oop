package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        return Arrays.stream(args).map(arg -> switch (arg) {
            case "f" -> MoveDirection.FORWARD;
            case "r" -> MoveDirection.RIGHT;
            case "b" -> MoveDirection.BACKWARD;
            case "l" -> MoveDirection.LEFT;
            default -> null;
        }).filter(Objects::nonNull).toArray(MoveDirection[]::new);
    }

    public static MoveDirection[] parse(String args) {
        return parse(args.split("\\s+"));
    }
}
