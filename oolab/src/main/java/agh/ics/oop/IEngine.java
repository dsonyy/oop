package agh.ics.oop;

/**
 * The interface responsible for managing the moves of the animals.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IEngine {
    void run();

    void update();

    Animal getAnimal(int i);
}