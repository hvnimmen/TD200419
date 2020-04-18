package Game;

import javafx.scene.canvas.GraphicsContext;

public interface Entity {

    float getX();
    float getY();

    float getDisplayX();
    float getDisplayY();

    void setX(float x);
    void setY(float y);

    void update(GraphicsContext gc);
    void draw(GraphicsContext gc);
}
