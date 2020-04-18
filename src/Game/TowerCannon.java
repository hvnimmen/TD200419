package Game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static Game.Game.*;
import static Game.Clock.*;

public class TowerCannon extends StackPane implements Entity{

    private float x, y, timeSinceLastShot, coolDown, angle, offset, displayX, displayY;
    private int damage, range;
    private ImageView iv;
    private Tile startTile;
    private Image image;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;
    private Enemy target;
    private boolean locked;

    public TowerCannon(Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.displayX = x * TILE_SIZE;
        this.displayY = y * TILE_SIZE;
        this.damage = damage;
        this.range = range + 1;
        this.coolDown = 2000;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<Projectile>();
        this.enemies = enemies;
        this.locked = false;
//        this.angle = calculateAngle();

        this.image = new Image("file:bow.png");

        this.iv = new ImageView(image);
    }

    private Enemy acquireTarget() {
        Enemy closestEnemy = null;
        for (Enemy e : enemies){
            System.out.println(getDistance(e));
            double closestRange = 5;
            if (isInRange(e) && getDistance(e) < closestRange) {
                closestEnemy = e;
                closestRange = getDistance(e);
            }
        }
        if (closestEnemy != null){
            locked = true;
        }

        return closestEnemy;
    }

    private boolean isInRange(Enemy e) {
        return getDistance(e) < range;
    }

    private double getDistance(Enemy e) {
        double xDistance = Math.abs(x - e.getX());
        double yDistance = Math.abs(y - e.getY());
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    private float calculateAngle() {
        double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(tempAngle) + 45;
    }

    private void shoot(){
        timeSinceLastShot = 0;
//        projectiles.add(new FreezeArrow("file:arrow.png", target, x, y, 15, 10));
    }

    public void updateEnemyList(ArrayList<Enemy> newList){
        enemies = newList;
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

    public float getDisplayX() {
        return displayX;
    }

    public float getDisplayY() {
        return displayY;
    }

    public void update(GraphicsContext gc){
        if(target == null || !target.isAlive() || !isInRange(target)){
            locked = false;
        }

        if(locked){
            timeSinceLastShot += Delta();
            if (timeSinceLastShot > coolDown)
                shoot();
            for (Projectile p : projectiles){
                p.update(gc);
            }

            angle = calculateAngle();
            iv.setRotate(angle);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            this.image = iv.snapshot(params, null);
            offset = (float) ((this.image.getWidth() - TILE_SIZE) * 0.5);

        } else {
            target = acquireTarget();
        }
        draw(gc);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, (x * TILE_SIZE) - offset, (y * TILE_SIZE) - offset);
    }


}
