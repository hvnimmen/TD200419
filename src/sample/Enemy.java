package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static sample.Clock.*;
import static sample.Boot.TILE_SIZE;

public class Enemy extends StackPane {

    private int health, currentCheckpoint;
    private double x, y, speed;
    private boolean first = true, alive = true;
    private EnemyType type;
    private ImageView iv;
    private Tile startTile;
    private TileGrid grid;
    private Image image;
    private Tile endTile = new Tile(0, 0, TileType.Dirt);
    private boolean hugsLeft;

//    private ArrayList<Checkpoint> checkpoints;
    private int[] dir;

//    public Enemy(Tile startTile, double speed, EnemyType type, TileGrid grid, boolean hugsLeft){
    public Enemy(Tile startTile, double speed, TileGrid grid, boolean hugsLeft){
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.startTile = startTile;
        this.speed = speed;
        this.type = type;
        this.grid = grid;
//        this.image = new Image(type.fileName);
        this.hugsLeft = hugsLeft;
        if(!hugsLeft){
            this.speed *= 1.5;
            this.image = new Image("file:spider-face.png");
        } else {
            this.image = new Image("file:zombie-face.png");
        }

//        this.checkpoints = new ArrayList<>();
        this.dir = new int[2];
        this.dir[0] = 1;
        this.dir[1] = 0;

        /*directions = FindNextD(startTile);
        this.currentCheckpoint = 0;
        PopulateCheckpointList();*/
    }

    public void Draw(GraphicsContext gc) {
        gc.drawImage(image, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public void Update() {
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
        }
    }

    private void rotate() {
        //inverted coordinates on this referential
        if (hugsLeft) {
            dir = new int[]{dir[1], -dir[0]};
        } else {
            dir = new int[]{-dir[1], dir[0]};
        }
        if (!canGoForward()){
            dir = new int[]{-dir[0], -dir[1]};
        }
        if (!canGoForward()){
            Die();
        }
    }

    public boolean canGoForward() {
        return (withinBounds() && isPath());
    }

    public boolean withinBounds() {
        double nextX = x + Delta() * 0.001 * dir[0] * speed;
        double nextY = y + Delta() * 0.001 * dir[1] * speed;
        return (0 <= nextX && nextX < grid.xTiles-1 && 0 <= nextY && nextY < grid.yTiles-1);
    }

    public boolean isPath() {
        double nextX = x + Delta() * 0.001 * dir[0] * speed;
        double nextY = y + Delta() * 0.001 * dir[1] * speed;
        if (dir[0] == 1)
            nextX += 1;
        else if (dir[1] == 1)
            nextY += 1;
        Tile currentTile = grid.GetTile((int)x, (int)y);
        Tile nextTile = grid.GetTile((int)nextX, (int)nextY);
        return (currentTile.getType() == nextTile.getType());
    }

    private void Die() {
        alive = false;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
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
}
