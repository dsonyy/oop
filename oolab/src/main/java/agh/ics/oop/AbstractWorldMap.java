package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, IEntity> entities = new HashMap<>();

    public abstract Vector2d lowerLeft();

    public abstract Vector2d upperRight();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft()) && position.precedes(upperRight()) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            entities.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) == null)
            return false;
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return entities.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(lowerLeft(), upperRight());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IEntity entity = entities.get(oldPosition);
        entities.remove(oldPosition);
        entities.put(newPosition, entity);
    }
}