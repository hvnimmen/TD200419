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
    private Tile startTile;

    public Enemy(Tile startTile, double speed, EnemyType type){
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.startTile = startTile;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }
}
