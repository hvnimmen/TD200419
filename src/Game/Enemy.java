package Game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static Game.Clock.*;
import static Game.Game.*;

public class Enemy implements Entity{

    private int displayX, displayY, angle;
    private float x, y, speed, health, maxHealth;
    private boolean first, alive;
    private EnemyType type;
    private ImageView iv;
    private Tile startTile;
    private TileGrid grid;
    private Image image, healthBackground, healthForeground, healthBorder;
    private Tile endTile = new Tile(0, 0, TileType.Dirt);
    private boolean hugsLeft;

    private int[] dir;

    public Enemy(EnemyType type, Tile startTile, TileGrid grid){

        this.type = type;
        this.image = type.image;
        this.speed = type.speed;
        this.health = type.health;
        this.maxHealth = type.health;

        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();

        this.displayX = (int)(x * TILE_SIZE);
        this.displayY = (int)(y * TILE_SIZE);

        this.grid = grid;

        this.first = true;
        this.alive = true;

        //randomizer
        this.hugsLeft = (Math.random() > 0.5);

        this.healthBackground = new Image("file:health-bg.png");
        this.healthForeground = new Image("file:health-fg.png");
        this.healthBorder = new Image("file:health-border.png");

        this.dir = new int[2];
        this.dir[0] = 1;
        this.dir[1] = 0;

        if (dir[0] == 0) {
            if (dir[1] == 1){
                angle = 180;
            } else {
                angle = 0;
            }
        }
        if (dir[1] == 0) {
            if (dir[0] == 1){
                angle = 90;
            } else {
                angle = 270;
            }
        }

        ImageView imageView = new ImageView(image);
        imageView.setRotate(angle);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.image = imageView.snapshot(params, null);
    }

    public void draw(GraphicsContext gc) {

        //enemy texture
        gc.drawImage(image, displayX, displayY, TILE_SIZE, TILE_SIZE);

        //health bar texture
        float hpPercentage = health / maxHealth;
        gc.drawImage(healthBackground, displayX, displayY, TILE_SIZE, TILE_SIZE/8);
        gc.drawImage(healthForeground, displayX, displayY, TILE_SIZE*hpPercentage, TILE_SIZE/8);
        gc.drawImage(healthBorder, displayX, displayY, TILE_SIZE, TILE_SIZE/8);
    }

    public void update(GraphicsContext gc) {
        //Check if it's the first time this class is updated, if so do nothing
        if (first) {
            first = false;
        } else {
            if (canGoForward()){
                x += Delta() * 0.001 * dir[0] * speed; //delta is in milliseconds
                y += Delta() * 0.001 * dir[1] * speed;
            } else {
                x = (int)(x+0.5);
                y = (int)(y+0.5);
                rotate();
            }
            displayX = (int)(x * TILE_SIZE);
            displayY = (int)(y * TILE_SIZE);
        }
        draw(gc);
    }

    //pathfinding method
    private void rotate() {
        //inverted coordinates on this referential
        if (hugsLeft) {
            dir = new int[]{dir[1], -dir[0]};
            angle = 270;
            if (!canGoForward()){
                dir = new int[]{-dir[0], -dir[1]};
                angle = 90;
            }
        } else {
            dir = new int[]{-dir[1], dir[0]};
            angle = 90;
            if (!canGoForward()){
                dir = new int[]{-dir[0], -dir[1]};
                angle = 270;
            }
        }

        ImageView imageView = new ImageView(image);
        imageView.setRotate(angle);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.image = imageView.snapshot(params, null);

        if (!canGoForward()){
            getToEnd();
        }
    }

    //run when dead end thus end of path
    public void getToEnd(){
        Player.changeHP(-1);
        die();
    }

    public boolean canGoForward() {
        return (withinBounds() && isPath());
    }

    //check if next tile is still in window/map
    public boolean withinBounds() {
        double nextX = x + Delta() * 0.001 * dir[0] * speed;
        double nextY = y + Delta() * 0.001 * dir[1] * speed;
        return (0 <= nextX && nextX < grid.xTiles-1 && 0 <= nextY && nextY < grid.yTiles-1);
    }

    //check if next tile is path
    public boolean isPath() {
        double nextX = x + Delta() * 0.001 * dir[0] * speed;
        double nextY = y + Delta() * 0.001 * dir[1] * speed;
        if (dir[0] == 1)
            nextX += 1;
        else if (dir[1] == 1)
            nextY += 1;
        Tile currentTile = grid.getTile((int)x, (int)y);
        Tile nextTile = grid.getTile((int)nextX, (int)nextY);
        return (currentTile.getType() == nextTile.getType());
    }

    //enemy takes damage from external source
    public void damage(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
            Player.changeGold(5);
        }
    }

    //kills the enemy
    private void die() {
        alive = false;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
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

    public TileGrid getGrid() {
        return grid;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getDisplayX() {
        return displayX;
    }

    public void setDisplayX(int displayX) {
        this.displayX = displayX;
    }

    public float getDisplayY() {
        return displayY;
    }

    public void setDisplayY(int displayY) {
        this.displayY = displayY;
    }
}
