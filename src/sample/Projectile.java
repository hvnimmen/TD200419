package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static sample.Clock.Delta;
import static sample.Game.TILE_SIZE;

public class Projectile {

    private float x, y, speed;
    private int damage;
    private Image image;

    public Projectile(float x, float y, float speed, int damage){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.image = new Image("file:bullet.png");
    }

    public void update(GraphicsContext gc){
        x += Delta() * speed;
        draw(gc);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y, 20, 20);
    }
}
