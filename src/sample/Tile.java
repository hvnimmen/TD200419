package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane {

    private static final int TILE_SIZE = 40;

    private int x, y;
    private TileType type;

    private Image image;
    private ImageView iv;

    public Tile(int x, int y, TileType type){
        this.x = x;
        this.y = y;
        this.type = type;

//        add this if image isn't 40x40
//        iv.setPreserveRatio(true);
//        iv.setFitHeight(TILE_SIZE);

        image = new Image(type.fileName);
        iv = new ImageView(image);
        getChildren().add(iv);

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}