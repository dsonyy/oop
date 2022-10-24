package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;

    public String toString() {
        return position.toString() + ", " + orientation.toString();
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
        if (newPosition.precedes(new Vector2d(4, 4))
            && newPosition.follows(new Vector2d(0 ,0))) {
            return newPosition;
        }
        return position;
    }
}
