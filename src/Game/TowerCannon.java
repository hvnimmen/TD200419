package Game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static Game.Boot.*;
import static Game.Clock.*;

public class TowerCannon extends StackPane {

    private float x, y, timeSinceLastShot, coolDown, angle, offset;
    private int damage;
    private ImageView iv;
    private Tile startTile;
    private Image image;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;
    private Enemy target;

    public TowerCannon(Tile startTile, int damage, ArrayList<Enemy> enemies) {
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.damage = damage;
        this.coolDown = 2000;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<Projectile>();
        this.enemies = enemies;
        this.target = acquireTarget();
        this.angle = calculateAngle();

        this.image = new Image("file:bow.png");

        this.iv = new ImageView(image);
    }

    private Enemy acquireTarget() {
        return enemies.get(0);
    }

    private float calculateAngle() {
        double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(tempAngle) + 45;
    }

    private void shoot(){
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(target, x, y, 15, 10));
    }

    public void update(GraphicsContext gc){
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
        draw(gc);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, (x * TILE_SIZE) - offset, (y * TILE_SIZE) - offset);
    }


}
