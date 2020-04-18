package Game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static Game.Clock.Delta;
import static Game.Boot.TILE_SIZE;

public class Projectile {

    private float x, y, speed, xVelocity, yVelocity, angle, offset;
    private double displayX, displayY;
    private int damage, width, height, size = 32;
    private Image image;
    private ImageView iv;
    private Enemy target;
    private boolean hasCollided;

    public Projectile(Enemy target,float x, float y, float speed, int damage){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.image = new Image("file:arrow.png");
        this.hasCollided = false;

        this.angle = calculateAngle();
        this.iv = new ImageView(image);
        this.iv.setPreserveRatio(true);
        this.iv.setFitHeight(size);
        this.iv.setRotate(angle);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.image = iv.snapshot(params, null);

        this.width = (int)this.image.getWidth();
        this.height = (int)this.image.getHeight();

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
        if (!hasCollided) {
            x += Delta() * 0.001 * xVelocity * speed;
            y += Delta() * 0.001 * yVelocity * speed;

            this.displayX = (x + 0.5) * TILE_SIZE - 0.5 * width;
            this.displayY = (y + 0.5) * TILE_SIZE - 0.5 * height;

            draw(gc);
            projectileHitTarget();
        }
    }

    private void projectileHitTarget() {
        if (target.getDisplayX() < displayX + width && displayX < target.getDisplayX() + TILE_SIZE &&
                target.getDisplayY()  < displayY + height && displayY < target.getDisplayY() + TILE_SIZE) {
            hasCollided = true;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, displayX, displayY);
    }
}
