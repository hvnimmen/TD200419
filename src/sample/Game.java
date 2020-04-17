package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static sample.Boot.*;
import static sample.Clock.Delta;

public class Game {

    private TileGrid grid;
    private Wave wave;
    private WaveManager waveManager;
    private Player player;

    private GraphicsContext gc;
    private Stage window;

    public Game(int[][] map, Stage stage) {

        grid = new TileGrid(map);
        waveManager = new WaveManager(new Enemy(grid.GetTile(0, 10), 1, grid, (Math.random() > 0.5)),
                2, 3);
        player = new Player(grid, waveManager);

        window = stage;

        showWindow();

    }

    public void showWindow() {

        VBox root = new VBox(); //maybe try with pane
        Canvas c = new Canvas(W, H);
        gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        root.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            int newX = (int)Math.floor(event.getX()/TILE_SIZE);
            int newY = (int)Math.floor(event.getY()/TILE_SIZE);
            if (player.isEditor())
                player.SetTile(newX, newY);
            else
                player.addTower(newX, newY);

        });

        Scene scene = new Scene(root, W, H);

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            switch (key.getCode()) {
                case G:
                    player.SetIndex(0);
                    break;
                case W:
                    player.SetIndex(1);
                    break;
                case D:
                    player.SetIndex(2);
                    break;
                case T:
                    player.setEditor(false);
                    break;
                case F:
                    Clock.changeMultiplier(0.2f);
                    break;
                case B:
                    Clock.changeMultiplier(-0.2f);
                    break;

            }
        });

        window.setScene(scene);
        window.setTitle("Tower Defense");
        window.show();

    }

    public void update() {

        grid.Draw(gc);
        waveManager.update(gc);
        player.update(gc);

    }

}
