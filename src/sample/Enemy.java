package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Random;

import static sample.Clock.*;
import static sample.Game.TILE_SIZE;

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

    public Enemy(Tile startTile, double speed, EnemyType type, TileGrid grid, boolean hugsLeft){
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.startTile = startTile;
        this.speed = speed;
        this.type = type;
        this.grid = grid;
        this.image = new Image(type.fileName);
        this.hugsLeft = hugsLeft;
        if(hugsLeft){
            System.out.println("hugs left");
        } else {
            System.out.println("hugs right");
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
                x += Delta() * dir[0] * speed;
                y += Delta() * dir[1] * speed;
            } else {
                x = (int)(x+0.5);
                y = (int)(y+0.5);
                rotate();
            }
        }
    }

    private void rotate() {
        if (hugsLeft) {
            dir = new int[]{dir[1], -dir[0]};
            System.out.println("turns left");
        } else {
            dir = new int[]{-dir[1], dir[0]};
            System.out.println("turns right");
        }
    }

    public boolean canGoForward() {
        return (withinBounds() && isPath());
    }

    public boolean withinBounds() {
        double nextX = x + Delta() * dir[0] * speed;
        double nextY = y + Delta() * dir[1] * speed;
        return (0 <= nextX && nextX < grid.xTiles-1 && 0 <= nextY && nextY < grid.yTiles-1);
    }

    public boolean isPath() {
        double nextX = x + Delta() * dir[0] * speed;
        double nextY = y + Delta() * dir[1] * speed;
        if (dir[0] == 1)
            nextX += 1;
        else if (dir[1] == 1)
            nextY += 1;
        Tile currentTile = grid.GetTile((int)x, (int)y);
        Tile nextTile = grid.GetTile((int)nextX, (int)nextY);
        return (currentTile.getType() == nextTile.getType());
    }

    /*public void Update() {
        if (first)
            first = false;
        else {
            if (CheckpointReached()) {
                if (currentCheckpoint + 1 == checkpoints.size()) {
                    System.out.println("bang bang");
                    Die();
                } else {
                    currentCheckpoint++;
                }
            } else {
                x += Delta() * checkpoints.get(currentCheckpoint).getxDirection() * speed;
                y += Delta() * checkpoints.get(currentCheckpoint).getyDirection() * speed;
            }
        }
    }

    private boolean CheckpointReached() {
        boolean reached = false;
        Tile t = checkpoints.get(currentCheckpoint).getTile();

        // check if position reached tile within variance of 3 (arbitrary)
        // gives some allowance or leeway to account for the speed and deltas
        double allowance = 200 * speed;
        if (x > t.getX() - allowance &&
                x < t.getX() + allowance &&
                y > t.getY() - allowance &&
                y < t.getY() + allowance ) {

            reached = true;
            x = t.getX();
            y = t.getY();
        }

        return reached;
    }

    private void PopulateCheckpointList(){
        checkpoints.add(FindNextC(startTile, directions = FindNextD(startTile)));

        int counter = 0;
        boolean cont = true;
        while (cont) {
            int[] currentD = FindNextD(checkpoints.get(counter).getTile());
            //check if a next direction/checkpoint exists, end after 20 checkpoints
            if (currentD[0] == 2 || counter == 20)
                cont = false;
            else
                checkpoints.add(FindNextC(checkpoints.get(counter).getTile(), directions =
                        FindNextD(checkpoints.get(counter).getTile())));
            counter++;
        }
    }

    private Checkpoint FindNextC(Tile s, int[] dir) {
        Tile next = null;
        Checkpoint c = null;

        //Boolean to decide if next checkpoint is found
        boolean found = false;

        //Integer to increment each loop
        int counter = 1;

        while (!found) {
            if (s.getX() + dir[0] * counter == grid.getxTiles() ||
                    s.getY() + dir[1] * counter == grid.getyTiles() ||
                    s.getType() != grid.GetTile(s.getX() + dir[0] * counter, s.getY() + dir[1] * counter).getType()) {
                found = true;
                //Move counter back 1 to find tile before next tileType
                counter -= 1;
                next = grid.GetTile(s.getX() + dir[0] * counter, s.getY() + dir[1] * counter);
            }

            counter++;
        }
        c = new Checkpoint(next, dir[0], dir[1]);
        return c;
    }

    private int[] FindNextD(Tile s) {
        int[] dir = new int[2];
        Tile u = grid.GetTile(s.getX(), s.getY() - 1);
        Tile r = grid.GetTile(s.getX() + 1, s.getY());
        Tile d = grid.GetTile(s.getX(), s.getY() + 1);
        Tile l = grid.GetTile(s.getX() - 1, s.getY());

        if(s.getType() == u.getType() && directions[1] != 1) {
            dir[0] = 0;
            dir[1] = -1;
        } else if (s.getType() == r.getType() && directions[0] != -1) {
            dir[0] = 1;
            dir[1] = 0;
        } else if (s.getType() == d.getType() && directions[1] != -1) {
            dir[0] = 0;
            dir[1] = 1;
        } else if (s.getType() == l.getType() && directions[0] != 1) {
            dir[0] = -1;
            dir[1] = 0;
        } else {
            dir[0] = 2;
            dir[1] = 2;
        }

        return dir;
    }*/

    private void Die() {
        System.out.println("just died");
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
