package agh.ics.oop;

import java.util.*;

public class GrassField
        extends AbstractWorldMap {
    private final int grass_count;
    private final Random random = new Random();

    public GrassField(int grass_count) {
        super();
        this.grass_count = grass_count;

        random.setSeed(42);
        for (int i = 0; i < this.grass_count; i++) {
            spawnRandomGrass();
        }
    }

    private void spawnRandomGrass() {
        int x, y;
        do {
            x = random.nextInt((int) Math.sqrt(10 * grass_count));
            y = random.nextInt((int) Math.sqrt(10 * grass_count));
        } while (isOccupied(new Vector2d(x, y)));
        Grass grass = new Grass(new Vector2d(x, y));
        this.entities.put(grass.getPosition(), grass);
        this.map_boundary.addPosition(grass.getPosition());
    }

    @Override
    public Vector2d upperRight() {
        return this.map_boundary.getUpperRight();
    }

    @Override
    public Vector2d lowerLeft() {
        return this.map_boundary.getLowerLeft();
    }

    public void replace(Grass grass) {
        entities.remove(grass);
        spawnRandomGrass();
    }

    public Set<Vector2d> getEntities() {
        return entities.keySet();
    }
}
