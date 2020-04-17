package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;

import static sample.Boot.*;

public class Game {

    private TileGrid grid;
    private Player player;
    private Wave wave;
    private GraphicsContext gc;

    //Temp variables;
    TowerCannon tower;

    public Game(int[][] map) {
        grid = new TileGrid(map);
        player = new Player(grid);
        wave = new Wave(15, new Enemy(grid.GetTile(0, 10), 0.0001, grid, (Math.random() > 0.5)));

        tower = new TowerCannon(grid.GetTile(14, 13), 10);

        VBox root = new VBox();
        Canvas c = new Canvas(W, H);
        gc = c.getGraphicsContext2D();
        root.getChildren().add(c);
    }

    public void update() {
        grid.Draw(gc);
        wave.Update(gc);
    }

}
