package Game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static Game.Boot.*;
import static Game.Boot.TILE_SIZE;
import static Game.Leveller.*;

public class Editor {

    private int index;
    private TileGrid grid;
    private TileType[] types;
    private GraphicsContext gc;

    public Editor() {
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.grid = loadMap("map");
//        this.grid = new TileGrid();

        VBox root = new VBox(); //maybe try with pane
        Canvas c = new Canvas(W, H);
        gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        root.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            int newX = (int)Math.floor(event.getX()/TILE_SIZE);
            int newY = (int)Math.floor(event.getY()/TILE_SIZE);
            SetTile(newX, newY);
        });

        root.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            int newX = (int)Math.floor(event.getX()/TILE_SIZE);
            int newY = (int)Math.floor(event.getY()/TILE_SIZE);
            SetTile(newX, newY);
        });

        Scene scene = new Scene(root, W, H);

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            switch (key.getCode()) {
                case G:
                    SetIndex(0);
                    break;
                case D:
                    SetIndex(1);
                    break;
                case W:
                    SetIndex(2);
                    break;
                case S:
                    saveMap("map", grid);
                    break;
            }
        });

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tower Defense");
        stage.show();
    }

    private void SetTile(int x, int y) {
        grid.SetTile(x, y, types[index]);
    }

    public void SetIndex(int index) {
        this.index = index;
    }

    public void update(){
        grid.Draw(gc);
    }
}
