package Game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static Game.Clock.Delta;
import static Game.Game.TILE_SIZE;

public abstract class Tower implements Entity{

    private int damage, range;
    private float x, y, displayX, displayY, cooldown, timeSinceLastShot, offset;
    private TowerType towerType;
    private ArrayList<Projectile> projectiles;
    private CopyOnWriteArrayList<Enemy> enemies;
    private boolean locked;
    private Image image;
    private ImageView imageView;
    private Enemy target;

    public Tower(TowerType towerType, Tile startTile, int damage, int range, CopyOnWriteArrayList<Enemy> enemies){
        this.x = startTile.getX();
        this.y = startTile.getY();

        this.displayX = x * TILE_SIZE;
        this.displayY = y * TILE_SIZE;

        this.towerType = towerType;
        this.damage = damage;
        this.range = range + 1;

        this.cooldown = 2 * 1000;
        this.timeSinceLastShot = 0;

        this.projectiles = new ArrayList<Projectile>();
        this.enemies = enemies;
        this.locked = false;

        this.image = new Image(towerType.baseName, (double)TILE_SIZE, (double)TILE_SIZE, false, false);
        this.imageView = new ImageView(image);
    }

    private Enemy acquireTarget() {
        Enemy closestEnemy = null;
        double closestRange = 5;
        for (Enemy e : enemies){
            if (isInRange(e) && getDistance(e) < closestRange && e.isAlive()) {
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

    public void shoot(){
        timeSinceLastShot = 0;
        projectiles.add(new FreezeArrow(towerType.projectileName, target, x, y, 15, 10));
    }

    public void updateEnemyList(CopyOnWriteArrayList<Enemy> newList){
        enemies = newList;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDisplayX() {
        return displayX;
    }

    public float getDisplayY() {
        return displayY;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public float getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    public void setTimeSinceLastShot(float timeSinceLastShot) {
        this.timeSinceLastShot = timeSinceLastShot;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public void setTowerType(TowerType towerType) {
        this.towerType = towerType;
    }

    public void update(GraphicsContext gc){
//        System.out.println(locked);
//        System.out.println("target is " + target);
//        if (target != null) {
//            System.out.println("target is alive " + target.isAlive());
//            System.out.println("target is in range " + isInRange(target));
//        }
        if(target == null || !target.isAlive() || !isInRange(target)){
            locked = false;
        }
        if (!locked) {
            target = acquireTarget();
        } else if (timeSinceLastShot > cooldown) {
            shoot();
        }

        timeSinceLastShot += Delta();

        for (Projectile p : projectiles){
            p.update(gc);
        }

        if (locked) {

            float angle = calculateAngle();
            imageView.setRotate(angle);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            this.image = imageView.snapshot(params, null);
            offset = (float) ((this.image.getWidth() - TILE_SIZE) * 0.5);

        }

        draw(gc);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, (x * TILE_SIZE) - offset, (y * TILE_SIZE) - offset);
    }
}