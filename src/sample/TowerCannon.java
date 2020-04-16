package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static sample.Game.*;

public class TowerCannon extends StackPane {

    private float x, y;
    private int damage;
    private ImageView iv;
    private Tile startTile;

    public TowerCannon(Tile startTile, int damage) {
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.damage = damage;

        iv = new ImageView(new Image("file:cannon-base.png"));
        iv.setPreserveRatio(true);
        iv.setFitHeight(40);
        getChildren().add(iv);

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);
    }

    public void update(){

    }



}
