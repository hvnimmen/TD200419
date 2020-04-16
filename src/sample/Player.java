package sample;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import static sample.Game.*;

public class Player {

    private TileGrid grid;
    private TileType[] types;
    private int index;

    public Player(TileGrid grid) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.index = 0;
    }

    public void SetTile(double x, double y) {
        int newX = (int)Math.floor(x/TILE_SIZE);
        int newY = (int)Math.floor(y/TILE_SIZE);
        grid.SetTile(newX, newY, types[index]);
    }

    public TileGrid getGrid() {
        return grid;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }

    public void SetIndex(int index) {
        this.index = index;
    }
}
