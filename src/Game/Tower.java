package Game;

import javafx.scene.canvas.GraphicsContext;

public class Tower implements Entity{

    float x, y, displayX, displayY;

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

    public void update(GraphicsContext gc) {

    }

    public void draw(GraphicsContext gc) {

    }
}
