package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static sample.Clock.Delta;
import static sample.Boot.TILE_SIZE;

public class Projectile {

    private float x, y, speed;
    private int damage, size = 20;
    private Image image;
    private ImageView iv;

    public Projectile(float x, float y, float speed, int damage){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.image = new Image("file:bullet.png");
    }

    public void update(GraphicsContext gc){
        x += Delta() * speed;
        y += Delta() * speed;
        draw(gc);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, (x + 0.5) * TILE_SIZE - 0.5 * size, (y + 0.5) * TILE_SIZE - 0.5 * size, size, size);
    }
}
