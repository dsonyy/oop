package agh.ics.oop;

public class Animal {
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2, 2);
    }

    public Animal() {
        this(new RectangularMap(5, 5));
    }

    public String toString() {
        return this.orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> position = validatePosition(orientation.toUnitVector());
            case BACKWARD -> position = validatePosition(orientation.toUnitVector().opposite());
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
        return position;
    }
}
