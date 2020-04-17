package sample;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static sample.Boot.*;
import static sample.Clock.*;

public class TowerCannon extends StackPane {

    private float x, y, timeSinceLastShot, coolDown;
    private int damage;
    private ImageView iv;
    private Tile startTile;
    private Image baseImage, headImage;
    private ArrayList<Projectile> projectiles;

    public TowerCannon(Tile startTile, int damage) {
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.damage = damage;
        this.coolDown = 30000;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<Projectile>();

        this.baseImage = new Image("file:cannon-base.png");
        this.headImage = new Image("file:cannon-head.png");

        this.iv = new ImageView(headImage);
        this.iv.setRotate(-90);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.headImage = iv.snapshot(params, null);
    }

    private void shoot(){
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(x, y, (float)0.0001, 10));
    }

    public void update(GraphicsContext gc){
        timeSinceLastShot += Delta();
        if (timeSinceLastShot > coolDown)
            shoot();
        for (Projectile p : projectiles){
            p.update(gc);
        }

        draw(gc);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(baseImage, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        gc.drawImage(headImage, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }


}
