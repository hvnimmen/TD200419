package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Boot extends Application {

    protected static final int TILE_SIZE = 64;

    protected static final int X_TILES = 20;
    protected static final int Y_TILES = 12;

    protected static final int W = TILE_SIZE * X_TILES;
    protected static final int H = TILE_SIZE * Y_TILES;

    private static int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };

/*    public void start(Stage window) {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {

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
        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            if (key.getCode() == KeyCode.G)
                player.SetIndex(0);
            if (key.getCode() == KeyCode.D)
                player.SetIndex(1);
            if (key.getCode() == KeyCode.W)
                player.SetIndex(2);
        });

    }*/

    public void start(Stage window){

        Game game = new Game(map, window);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Clock.Update();

                game.update();
            }
        };

        gameLoop.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
