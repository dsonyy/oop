package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Set;

public class App extends Application {
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final String title = "Window";
    private MapDirection orientation = MapDirection.NORTH;
    private GridPane grid_pane;

    private GridPane getGridPane(GrassField map) {
        GridPane grid_pane = new GridPane();
        grid_pane.setGridLinesVisible(true);
        grid_pane.setMinWidth(WIDTH);
        grid_pane.setMinHeight(HEIGHT);
        grid_pane.setAlignment(Pos.CENTER);
        Label label = new Label("X\\Y");
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

        Set<Vector2d> entities = map.getEntities();
        for (Vector2d position : entities) {
            GuiElementBox vBox = new GuiElementBox((IMapElement) map.objectAt(position));
            label = new Label();
            GridPane.setHalignment(label, HPos.CENTER);
            int screen_x = position.x - lower_left.x + 1;
            int screen_y = upper_right.y - position.y + 1;
            grid_pane.add(vBox.vBox, screen_x, screen_y, 1, 1);
        }

        return grid_pane;
    }

    @Override
    public void start(Stage primary_stage) {
        TextField textField = new TextField();

        grid_pane = new GridPane(); // getGridPane(new GrassField(10));
        VBox vBox = new VBox(
                grid_pane,
                textField,
                getStartButton(textField),
                getDirectionButton());

        Scene scene = new Scene(vBox, WIDTH, HEIGHT + 100);
        primary_stage.setTitle(title);
        primary_stage.setScene(scene);
        primary_stage.show();
    }

    public Button getDirectionButton() {
        Button directionButton = new Button(orientation.toString());
        directionButton.setOnAction((action) -> {
            this.orientation = this.orientation.next();
            directionButton.setText(this.orientation.toString());
        });
        return directionButton;
    }

    public void renderMap(GrassField newMap) {
//        grid_pane.setGridLinesVisible(false);
//        grid_pane.getColumnConstraints().clear();
//        grid_pane.getRowConstraints().clear();
        grid_pane.getChildren().clear();
//        grid_pane.setGridLinesVisible(true);
        grid_pane = getGridPane(newMap);
    }

    public Button getStartButton(TextField textField) {
        Button startButton = new Button("Start");
        startButton.setOnAction((action) -> {
            String text = textField.getText();
            MoveDirection[] directions = OptionsParser.parse(text.split(" "));
            Vector2d[] positions = {new Vector2d(1, 3), new Vector2d(2, -1)};
            GrassField map = new GrassField(10);
            IEngine engine = new SimulationEngine(directions, map, positions, this, orientation);
            Thread engineThread = new Thread(engine::run);
            engineThread.start();
        });
        return startButton;
    }
}