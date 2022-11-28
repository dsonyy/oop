package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final String title = "Window";

    private GridPane createGridPane(AbstractWorldMap map, IEngine engine) {
        GridPane grid_pane = new GridPane();
        grid_pane.setGridLinesVisible(true);
        grid_pane.setMinWidth(WIDTH);
        grid_pane.setMinHeight(HEIGHT);
        grid_pane.setAlignment(Pos.CENTER);
        Label label = new Label("X/Y");
        GridPane.setHalignment(label, HPos.CENTER);
        grid_pane.add(label, 0, 0);
        grid_pane.getColumnConstraints().add(new ColumnConstraints(30));
        grid_pane.getRowConstraints().add(new RowConstraints(30));
        Vector2d lower_left = map.lowerLeft();
        Vector2d upper_right = map.upperRight();
        for (int i = 1; i <= upper_right.x - lower_left.x + 1; i++) {
            label = new Label(Integer.toString(lower_left.x + i - 1));
            grid_pane.getColumnConstraints().add(new ColumnConstraints(30));
            GridPane.setHalignment(label, HPos.CENTER);
            grid_pane.add(label, i, 0);
        }

        for (int i = 1; i <= upper_right.y - lower_left.y + 1; i++) {
            label = new Label(Integer.toString(upper_right.y - i + 1));
            grid_pane.getRowConstraints().add(new RowConstraints(30));
            GridPane.setHalignment(label, HPos.CENTER);
            grid_pane.add(label, 0, i);
        }

        for (int i = lower_left.x; i <= upper_right.x; i++) {
            for (int j = lower_left.y; j <= upper_right.y; j++) {
                int screen_x = i - lower_left.x + 1;
                int screen_y = upper_right.y - j + 1;
                if (map.isOccupied(new Vector2d(i, j))) {
                    label = new Label("#");
                    GridPane.setHalignment(label, HPos.CENTER);
                    grid_pane.add(label, screen_x, screen_y);
                }
            }
        }

        for (int i = 0; i < 2; i++) {
            Animal animal = engine.getAnimal(i);
            label = new Label(String.valueOf(i));
            GridPane.setHalignment(label, HPos.CENTER);
            int screen_x = animal.getPosition().x - lower_left.x + 1;
            int screen_y = upper_right.y - animal.getPosition().y + 1;
            grid_pane.add(label, screen_x, screen_y);
        }

        return grid_pane;
    }

    @Override
    public void start(Stage primary_stage) {
        String[] args = this.getParameters().getRaw().toArray(String[]::new);
        MoveDirection[] directions = OptionsParser.parse(args);
        Vector2d[] positions = { new Vector2d(3, -1), new Vector2d(1, 2) };
        GrassField map = new GrassField(10);
        IEngine engine = new SimulationEngine(directions, map, positions);

        GridPane pane = createGridPane(map, engine);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primary_stage.setTitle(title);
        primary_stage.setScene(scene);
        primary_stage.show();
    }
}