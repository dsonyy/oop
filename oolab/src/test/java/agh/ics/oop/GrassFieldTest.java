package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrassFieldTest {
    @Test
    public void test_bounds() {
        GrassField map = new GrassField(10);
        Animal animals[] = {new Animal(map, new Vector2d(0, 0)),
                      new Animal(map, new Vector2d(2, 2))};
        map.place(animals[0]);
        map.place(animals[1]);

        animals[0].move(MoveDirection.RIGHT);
        animals[1].move(MoveDirection.LEFT);
        animals[0].move(MoveDirection.FORWARD);
        animals[1].move(MoveDirection.BACKWARD);
        animals[0].move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 0), animals[0].getPosition());
        assertEquals(new Vector2d(3, 2), animals[1].getPosition());
    }
}