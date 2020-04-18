package Game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static Game.Game.*;
import static Game.Leveller.*;

public class Editor {

    private int index;
    private TileGrid grid;
    private TileType[] types;

    private VBox root;
    private GraphicsContext gc;
    private Stage window;
    private Canvas c;
    private Scene scene;

    public Editor() {
        this.index = 0;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.grid = loadMap("map");

        window = new Stage();

        showWindow();
    }

    //Builds the editor window
    public void showWindow() {

        root = new VBox(); //maybe try with pane
        c = new Canvas(W, H);
        gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        root.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            int newX = (int)Math.floor(event.getX()/TILE_SIZE);
            int newY = (int)Math.floor(event.getY()/TILE_SIZE);
            setTile(newX, newY);
        });

        root.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            if (0 < event.getX() && event.getX() < W && 0 < event.getY() && event.getY() < H) {
                int newX = (int) Math.floor(event.getX() / TILE_SIZE);
                int newY = (int) Math.floor(event.getY() / TILE_SIZE);
                setTile(newX, newY);
            }
        });

        Scene scene = new Scene(root, W, H);

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            switch (key.getCode()) {
                case G:
                    setIndex(0);
                    break;
                case D:
                    setIndex(1);
                    break;
                case W:
                    setIndex(2);
                    break;
                case S:
                    saveMap("map", grid);
                    break;
            }
        });

        window.setScene(scene);
        window.setTitle("Tower Defense");
        window.show();
    }

    private void setTile(int x, int y) {
        grid.setTile(x, y, types[index]);
    }

    //Allows editor to change which TileType is selected
    public void setIndex(int index) {
        this.index = index;
    }

    public void update(){
        grid.draw(gc);
    }
}
