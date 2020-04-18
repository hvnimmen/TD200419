package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static Game.Game.*;

public class Tile extends StackPane {

    private int x, y;
    private TileType type;

    private Image image;
    private ImageView iv;

    public Tile(int x, int y, TileType type){
        this.x = x;
        this.y = y;
        this.type = type;

        this.image = new Image(type.fileName);
    }

    public void Draw(GraphicsContext gc) {
        gc.drawImage(image, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}