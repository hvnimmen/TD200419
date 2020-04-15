package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {

    private static final int TILE_SIZE = 40;
    private static final int W = 800;
    private static final int H = 600;

    private static final int X_TILES = W / TILE_SIZE;
    private static final int Y_TILES = H / TILE_SIZE;

    private Scene scene;

    private Parent createContent() {

        Pane root = new Pane();

        root.setPrefSize(W, H);

        Tile tile1 = new Tile(1, 1);
        Tile tile2 = new Tile(2, 3);
        root.getChildren().addAll(tile1, tile2);

        return root;

    }

    private class Tile extends StackPane {
        private int x, y;

        private Rectangle border = new Rectangle(TILE_SIZE, TILE_SIZE);

        public Tile(int x, int y){
            this.x = x;
            this.y = y;

            border.setFill(Color.WHITE);

            getChildren().add(border);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);
        }
    }

    public void start(Stage window) throws Exception {
        window.setTitle("Tower Defense");
        Scene scene = new Scene(createContent(), Color.BLACK);
        window.setScene(scene);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
