package Game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static Game.Boot.*;

public class Game {

    private TileGrid grid;
    private Wave wave;
    private WaveManager waveManager;
    private Player player;

    private VBox root;
    private GraphicsContext gc;
    private Stage window;

    public Game(int[][] map) {

        grid = new TileGrid(map);
        waveManager = new WaveManager(new Enemy(grid.GetTile(0, 10), 1, grid, (Math.random() > 0.5), 50),
                2, 3);
        player = new Player(grid, waveManager);

        window = new Stage();

        showWindow();

    }

    public void showWindow() {

        root = new VBox(); //maybe try with pane
        Canvas c = new Canvas(W, H);
        gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        root.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            int newX = (int)Math.floor(event.getX()/TILE_SIZE);
            int newY = (int)Math.floor(event.getY()/TILE_SIZE);
            player.addTower(newX, newY);
        });

        root.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
            int newDisplayX = (int)Math.floor(event.getX()/TILE_SIZE)*TILE_SIZE;
            int newDisplayY = (int)Math.floor(event.getY()/TILE_SIZE)*TILE_SIZE;
            gc.drawImage(new Image("file:crosshair.png"), newDisplayX, newDisplayY);
            System.out.println("displaying crosshairs");
        });

        Scene scene = new Scene(root, W, H);

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            switch (key.getCode()) {
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
