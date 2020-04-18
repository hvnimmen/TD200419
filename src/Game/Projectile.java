package Game;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static Game.Clock.Delta;
import static Game.Game.TILE_SIZE;

public abstract class Projectile implements Entity {

    private float x, y, displayX, displayY, speed, xVelocity, yVelocity, angle;
    private int damage, width, height, size = TILE_SIZE/2;
    private Image image;
    private ImageView iv;
    private Enemy target;
    private boolean hasCollided;

    public Projectile(String fileName, Enemy target,float x, float y, float speed, int damage){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.image = new Image(fileName);
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

            this.displayX = (x + 0.5f) * TILE_SIZE - 0.5f * width;
            this.displayY = (y + 0.5f) * TILE_SIZE - 0.5f * height;

            if (projectileHitTarget()){
                damage();
            }
            draw(gc);
        }
    }

    public boolean projectileHitTarget() {
        return (target.getDisplayX() < displayX + width && displayX < target.getDisplayX() + TILE_SIZE &&
                target.getDisplayY()  < displayY + height && displayY < target.getDisplayY() + TILE_SIZE);
    }

    public void damage(){
        target.damage(damage);
        hasCollided = true;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, displayX, displayY);
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

    public void setDisplayX(float displayX) {
        this.displayX = displayX;
    }

    public float getDisplayY() {
        return displayY;
    }

    public void setDisplayY(float displayY) {
        this.displayY = displayY;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }
}
