package agh.ics.oop;

public class Grass extends AbstractEntity implements IMapElement {
    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/grass.png";
    }
}