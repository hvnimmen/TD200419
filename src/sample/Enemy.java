package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static sample.Clock.*;
import static sample.Game.TILE_SIZE;

public class Enemy extends StackPane {

    private int health;
    private float x, y;
    private double speed;
    private boolean first = true;
    private EnemyType type;
    private ImageView iv;

    public Enemy(Tile startTile, double speed, EnemyType type){
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.speed = speed;
        this.type = type;

        this.iv = new ImageView(new Image(type.fileName));

        getChildren().add(iv);

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);
    }

    public void Move() {
        if (first)
            first = false;
        else
            x += (Delta() * speed) / TILE_SIZE;
    }

    public void Update() {
        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);
    }

}
