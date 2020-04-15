package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {

    private int x, y;
    private static final int TILE_SIZE = 40;

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