package sample;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static sample.Clock.Delta;
import static sample.Boot.TILE_SIZE;

public class Projectile {

    private float x, y, speed, xVelocity, yVelocity, angle, offset;
    private int damage, size = 32;
    private Image image;
    private ImageView iv;
    private Enemy target;

    public Projectile(Enemy target,float x, float y, float speed, int damage){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.image = new Image("file:arrow.png");

        this.angle = calculateAngle();
        this.iv = new ImageView(image);
        this.iv.setRotate(angle);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.image = iv.snapshot(params, null);
        offset = (float) ((this.image.getWidth() - TILE_SIZE) * 0.5);

        calculateDirection();
    }

    private float calculateAngle() {
        double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(tempAngle) + 45;
    }

    private void calculateDirection() {
        float totalAllowedMovement = 1.0f;
        float xDistance = (float) (target.getX() - x);
        float yDistance = (float) (target.getY() - y);

        float totalDistance = Math.abs(xDistance) + Math.abs(yDistance);
        float xMovementPercent = xDistance / totalDistance;
        float yMovementPercent = yDistance / totalDistance;

        xVelocity = xMovementPercent;
        yVelocity = yMovementPercent;

    }

    public void update(GraphicsContext gc){
        x += Delta() * 0.001 * xVelocity * speed;
        y += Delta() * 0.001 * yVelocity * speed;
        draw(gc);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, (x + 0.5) * TILE_SIZE - 0.5 * size - offset, (y + 0.5) * TILE_SIZE - 0.5 * size, size, size);
    }
}
