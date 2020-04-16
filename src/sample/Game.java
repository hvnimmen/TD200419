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
            {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,1,1,0,0,1,0,0,0,0,0,1,0,0,0,0},
            {0,0,0,2,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
            {0,0,0,2,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
            {0,0,0,2,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,1,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };

    public void start(Stage window) {

        VBox root = new VBox();
        Canvas c = new Canvas(W, H);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        TileGrid grid = new TileGrid(map);

        new AnimationTimer() {
            @Override
            public void handle(long l) {

            }
        }.start();

        Scene scene = new Scene(root, W, H);

        window.setScene(scene);
        window.setTitle("Tower Defense");
        window.show();

    }

    /*private Scene scene;
    private Pane root;

    public void start(Stage window) throws Exception {
        window.setTitle("Tower Defense");

        root = new Pane();
        root.setPrefSize(W, H);

        int[][] map = {
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,1,1,0,0,1,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
                {0,0,0,2,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
                {0,0,0,2,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };

        TileGrid grid = new TileGrid(map);

        Enemy e = new Enemy(grid.GetTile(10, 7), 0.0003, EnemyType.Zombie, grid);
        Wave wave = new Wave(10000, e);
        Player player = new Player(grid);
        TowerCannon tower = new TowerCannon(grid.GetTile(12, 9), 10);

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

                if (!root.getChildren().contains(tower)){
                    root.getChildren().add(tower);
                }

                for(Enemy i : wave.getEnemyList()){
                    if(!root.getChildren().contains(i)){
                        root.getChildren().add(i);
                    }
                    if(!i.isAlive()){
                        root.getChildren().remove(i);
                    }
                }

                wave.Update();

                root.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
                    int newX = (int)Math.floor(event.getX()/TILE_SIZE);
                    int newY = (int)Math.floor(event.getY()/TILE_SIZE);
                    root.getChildren().remove(grid.getMap()[newX][newY]);
                    player.SetTile(newX, newY);
                });

                root.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    int newX = (int)Math.floor(event.getX()/TILE_SIZE);
                    int newY = (int)Math.floor(event.getY()/TILE_SIZE);
                    root.getChildren().remove(grid.getMap()[newX][newY]);
                    player.SetTile(newX, newY);
                });
            }
        };

        gameLoop.start();

        scene = new Scene(root, Color.BLACK);

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            if (key.getCode() == KeyCode.G)
                player.SetIndex(0);
            if (key.getCode() == KeyCode.D)
                player.SetIndex(1);
            if (key.getCode() == KeyCode.W)
                player.SetIndex(2);
        });

        window.setScene(scene);
        window.show();

    }*/

    public static void main(String[] args) {
        launch(args);
    }
}
