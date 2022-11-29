package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractEntity implements IMapElement{
    private Vector2d position;
    private MapDirection orientation;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection orientation) {
        this.map = map;
        this.orientation = orientation;
        this.position = initialPosition;
        this.observers = new ArrayList<>();
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.observers = new ArrayList<>();
    }

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;
    }

    public String toString() {
        return this.orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) throws IllegalArgumentException {
        Vector2d oldPosition = position;
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                position = validatePosition(orientation.toUnitVector());
                positionChanged(oldPosition, position);
            }
            case BACKWARD -> {
                position = validatePosition(orientation.toUnitVector().opposite());
                positionChanged(oldPosition, position);
            }
            default -> throw new IllegalArgumentException(direction + " is not a valid direction!");
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    private Vector2d validatePosition(Vector2d step) {
        Vector2d newPosition = position.add(step);
        if (map.canMoveTo(newPosition)) {
            return newPosition;
        }

        Object object = map.objectAt(newPosition);
        if (object instanceof Grass grass && map instanceof GrassField grass_field) {
            grass_field.replace(grass);
            this.position = newPosition;
        }

        return position;
    }

    @Override
    public String getImagePath() {
        return switch (this.getOrientation()) {
            case NORTH -> "src/main/resources/up.png";
            case EAST -> "src/main/resources/right.png";
            case SOUTH -> "src/main/resources/down.png";
            case WEST -> "src/main/resources/left.png";
        };
    }
}
