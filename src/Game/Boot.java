package Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Boot extends Application {

    protected static final int TILE_SIZE = 64;

    protected static final int X_TILES = 20;
    protected static final int Y_TILES = 12;

    protected static final int W = TILE_SIZE * X_TILES;
    protected static final int H = TILE_SIZE * Y_TILES;

    public void start(Stage window){

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Clock.Update();

                StateManager.update();
            }
        };

        gameLoop.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
