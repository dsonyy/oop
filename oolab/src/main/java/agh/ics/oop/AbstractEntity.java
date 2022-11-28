package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntity implements IEntity {
    protected List<IPositionChangeObserver> observers = new ArrayList<>();

    protected void positionChanged(Vector2d new_position, Vector2d old_position) {
        observers.forEach(observer -> observer.positionChanged(new_position, old_position));
    }

    public void addPositionChangeObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    protected void removePositionChangeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }
}