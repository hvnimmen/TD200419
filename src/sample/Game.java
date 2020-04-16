package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;

public class Game extends Application {

    protected static final int TILE_SIZE = 40;

    protected static final int X_TILES = 24;
    protected static final int Y_TILES = 16;

    protected static final int W = TILE_SIZE * X_TILES;
    protected static final int H = TILE_SIZE * Y_TILES;

    private Scene scene;
    private Pane root, waveLayer;

    private Parent createContent() {

        root = new Pane();
        root.setPrefSize(W, H);

        int[][] map = {
                {0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,2,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,1,0,0,0,1,1,1,1,1,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };

        TileGrid grid = new TileGrid(map);

//        Draw(grid);

        Enemy e = new Enemy(grid.GetTile(2, 2), 0.006, EnemyType.Zombie);
        Wave wave = new Wave(10000, e);
        Player player = new Player(grid);

//        root.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
//            player.SetTile(event.getX(), event.getY());
//        });

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Clock.Update();

                for (int i = 0; i < X_TILES; i++){
                    for (int j = 0; j < Y_TILES; j++){
                        if (!root.getChildren().contains(grid.getMap()[i][j]))
                            root.getChildren().add(grid.getMap()[i][j]);
                    }
                }

                for(Enemy i : wave.getEnemyList()){
                    if(!root.getChildren().contains(i)){
                        root.getChildren().add(i);
                    }
                }

                wave.Update();

                root.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
                    player.SetTile(event.getX(), event.getY());
                });
            }
        };

        gameLoop.start();

        return root;

    }

    public void start(Stage window) throws Exception {
        window.setTitle("Tower Defense");
        Scene scene = new Scene(createContent(), Color.BLACK);
        window.setScene(scene);
        window.show();

    }

//    private void Draw(TileGrid tileGrid){
//        Tile[][] map = tileGrid.getMap();
//        for (int i = 0; i < X_TILES; i++){
//            for (int j = 0; j < Y_TILES; j++){
//                root.getChildren().add(map[i][j]);
//            }
//        }
//    }

/*    public void start(Stage window) throws Exception {
        window.setTitle("Tower Defense");
        root = new Pane();
        root.setPrefSize(W, H);

        int[][] map = {
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,2,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,2,2,2,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };

        TileGrid grid = new TileGrid(map);
        Draw(grid.map);

        scene = new Scene(root, W, H);

        window.setScene(scene);
        window.show();

        Enemy e = new Enemy(grid.GetTile(2, 2), 0.002, EnemyType.Zombie);
        root.getChildren().add(e);

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                Clock.Update();
                e.Move();
                e.Update();
            }

        };

        gameLoop.start();

    }*/

    public static void main(String[] args) {
        launch(args);
    }
}
