package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    private Animal animal = new Animal(new RectangularMap(5,5), new Vector2d(2, 2));

    @Test
    public void positioningTest() {
        assertMove(new Vector2d(2, 2), MapDirection.NORTH);
        animal.move(MoveDirection.FORWARD);
        assertMove(new Vector2d(2, 3), MapDirection.NORTH);
        animal.move(MoveDirection.FORWARD);
        assertMove(new Vector2d(2, 4), MapDirection.NORTH);
        animal.move(MoveDirection.FORWARD);
        assertMove(new Vector2d(2, 4), MapDirection.NORTH);

        animal.move(MoveDirection.RIGHT);
        assertMove(new Vector2d(2, 4), MapDirection.EAST);
        animal.move(MoveDirection.RIGHT);
        assertMove(new Vector2d(2, 4), MapDirection.SOUTH);
        animal.move(MoveDirection.RIGHT);
        assertMove(new Vector2d(2, 4), MapDirection.WEST);
        animal.move(MoveDirection.RIGHT);

        assertMove(new Vector2d(2, 4), MapDirection.NORTH);
        animal.move(MoveDirection.LEFT);
        assertMove(new Vector2d(2, 4), MapDirection.WEST);
        animal.move(MoveDirection.LEFT);
        assertMove(new Vector2d(2, 4), MapDirection.SOUTH);
        animal.move(MoveDirection.LEFT);
        assertMove(new Vector2d(2, 4), MapDirection.EAST);

        animal.move(MoveDirection.BACKWARD);
        assertMove(new Vector2d(1, 4), MapDirection.EAST);
        animal.move(MoveDirection.BACKWARD);
        assertMove(new Vector2d(0, 4), MapDirection.EAST);
        animal.move(MoveDirection.BACKWARD);
        assertMove(new Vector2d(0, 4), MapDirection.EAST);

        animal.move(MoveDirection.FORWARD);
        assertMove(new Vector2d(1, 4), MapDirection.EAST);
        animal.move(MoveDirection.FORWARD);
        assertMove(new Vector2d(2, 4), MapDirection.EAST);
        animal.move(MoveDirection.FORWARD);
        assertMove(new Vector2d(3, 4), MapDirection.EAST);
        animal.move(MoveDirection.FORWARD);
        assertMove(new Vector2d(4, 4), MapDirection.EAST);
        animal.move(MoveDirection.FORWARD);
        assertMove(new Vector2d(4, 4), MapDirection.EAST);
    }

    private void assertMove(Vector2d position, MapDirection orientation) {
        assertEquals(animal.getPosition(), position);
        assertEquals(animal.getOrientation(), orientation);
    }

}
