package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Enemy extends StackPane {

    private int x, y, health;
    private float speed;
    private EnemyType type;

    private Tile startTile;

    private Image image;
    private ImageView iv;

    public Enemy(Tile startTile, float speed, EnemyType type){
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.speed = speed;
        this.type = type;

        image = new Image(type.fileName);
        iv = new ImageView(image);
        getChildren().add(iv);

        setTranslateX(x * Game.TILE_SIZE);
        setTranslateY(y * Game.TILE_SIZE);
    }

}
