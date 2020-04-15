package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

        for (int i = 0; i < X_TILES; i++){
            for (int j = 0; j < Y_TILES; j++){
                Tile tile = new Tile(i, j, TileType.Grass);
                if(i == j-1 || i == j || i == j+1){
                    tile = new Tile(i, j, TileType.Dirt);
                }
                root.getChildren().add(tile);
            }
        }

//        Tile tile1 = new Tile(0, 0, "grass");
//        Tile tile2 = new Tile(1, 0, "dirt");
//        root.getChildren().addAll(tile1, tile2);

        return root;

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
