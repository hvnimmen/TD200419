package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static sample.Boot.*;
import static sample.Clock.Delta;

public class Game {

    private TileGrid grid;
    private Wave wave;
    private Player player;

    private GraphicsContext gc;
    private Stage window;

    private float test;

    //Temp variables;
    private TowerCannon tower;

    public Game(int[][] map, Stage stage) {

        grid = new TileGrid(map);
        wave = new Wave(4, new Enemy(grid.GetTile(0, 10), 1, grid, (Math.random() > 0.5)));
        player = new Player(grid);

        tower = new TowerCannon(grid.GetTile(1, 1), 10);
        window = stage;

        showWindow();

    }

    public void showWindow() {

        VBox root = new VBox(); //maybe try with pane
        Canvas c = new Canvas(W, H);
        gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        Scene scene = new Scene(root, W, H);

        window.setScene(scene);
        window.setTitle("Tower Defense");
        window.show();

    }

    public void update() {
//        test += Delta();
//        System.out.println("game time " + test);

        grid.Draw(gc);
        tower.update(gc);
        wave.Update(gc);
    }

}
