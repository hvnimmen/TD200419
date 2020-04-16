package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

    protected static final int TILE_SIZE = 40;

    protected static final int X_TILES = 24;
    protected static final int Y_TILES = 16;

    protected static final int W = TILE_SIZE * X_TILES;
    protected static final int H = TILE_SIZE * Y_TILES;

    private static int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };

    public void start(Stage window) {

        VBox root = new VBox(); //maybe try with pane
        Canvas c = new Canvas(W, H);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        TileGrid grid = new TileGrid(map);

//        Enemy e = new Enemy(grid.GetTile(0, 14), 0.0006, EnemyType.Zombie, grid, (Math.random() > 0.5));
        Enemy e = new Enemy(grid.GetTile(0, 14), 0.0001, grid, (Math.random() > 0.5));
        Wave wave = new Wave(10000, e);

        Player player = new Player(grid);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Clock.Update();

                grid.Draw(gc);
//                e.Update();
//                e.Draw(gc);
                wave.Update(gc);

                root.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
                    int newX = (int)Math.floor(event.getX()/TILE_SIZE);
                    int newY = (int)Math.floor(event.getY()/TILE_SIZE);
                    player.SetTile(newX, newY);
                });

                root.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
                    int newX = (int)Math.floor(event.getX()/TILE_SIZE);
                    int newY = (int)Math.floor(event.getY()/TILE_SIZE);
                    player.SetTile(newX, newY);
                });

            }
        };

        gameLoop.start();

        Scene scene = new Scene(root, W, H);

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            if (key.getCode() == KeyCode.G)
                player.SetIndex(0);
            if (key.getCode() == KeyCode.D)
                player.SetIndex(1);
            if (key.getCode() == KeyCode.W)
                player.SetIndex(2);
        });

        window.setScene(scene);
        window.setTitle("Tower Defense");
        window.show();

    }

    /*

        TowerCannon tower = new TowerCannon(grid.GetTile(12, 9), 10);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (!root.getChildren().contains(tower)){
                    root.getChildren().add(tower);
                }

                for(Enemy i : wave.getEnemyList()){
                    if(!i.isAlive()){
                        root.getChildren().remove(i);
                    }
                }
            }
        };

    }*/

    public static void main(String[] args) {
        launch(args);
    }
}
