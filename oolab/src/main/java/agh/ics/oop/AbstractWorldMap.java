package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, AbstractEntity> entities = new HashMap<>();
    protected final MapBoundary map_boundary = new MapBoundary();

    public abstract Vector2d lowerLeft();

    public abstract Vector2d upperRight();

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (this.isOccupied(position)) {
            return (objectAt(position) instanceof Grass);
        }
        return true;
    }

    @Override
    public boolean place(AbstractEntity entity) {
        if (!isOccupied(entity.getPosition())) {
            entities.put(entity.getPosition(), entity);
            entity.addPositionChangeObserver(this);
            map_boundary.addPosition(entity.getPosition());
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
        AbstractEntity entity = entities.get(oldPosition);
        entities.remove(oldPosition);
        entities.put(newPosition, entity);
    }
}