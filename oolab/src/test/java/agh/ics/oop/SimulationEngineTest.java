package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationEngineTest {
    @Test
    public void animalsWalkingTest() {
        SimulationEngine engine = initEngine(
                new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f","f","f"},
                new Vector2d[]{ new Vector2d(2,2), new Vector2d(3,4) });
        assertEquals(engine.getAnimal(0).getPosition(), new Vector2d(2, 2));
        assertEquals(engine.getAnimal(1).getPosition(), new Vector2d(3, 4));
        engine.run();
        assertEquals(engine.getAnimal(0).getPosition(), new Vector2d(2, 3));
        assertEquals(engine.getAnimal(1).getPosition(), new Vector2d(3, 4));

        engine = initEngine(
                new String[]{},
                new Vector2d[]{ new Vector2d(0,1), new Vector2d(9,4) });
        assertEquals(engine.getAnimal(0).getPosition(), new Vector2d(0, 1));
        assertEquals(engine.getAnimal(1).getPosition(), new Vector2d(9, 4));
        engine.run();

        engine = initEngine(
                new String[]{"f", "b", "r", "l", "f", "b", "r", "l", "f", "b", "r", "l", "f", "b", "r", "l"},
                new Vector2d[]{ new Vector2d(0,1), new Vector2d(0,2), new Vector2d(0,3), new Vector2d(0,4)});
        assertEquals(engine.getAnimal(0).getPosition(), new Vector2d(0, 1));
        assertEquals(engine.getAnimal(1).getPosition(), new Vector2d(0, 2));
        assertEquals(engine.getAnimal(2).getPosition(), new Vector2d(0, 3));
        assertEquals(engine.getAnimal(3).getPosition(), new Vector2d(0, 4));
        engine.run();
    }

    private SimulationEngine initEngine(String[] directions, Vector2d[] positions) {
        SimulationEngine engine = new SimulationEngine(
                new OptionsParser().parse(new String[]{"f"}),
                new RectangularMap(10, 5),
                positions);
        return engine;
    }
}
