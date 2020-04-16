package sample;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import static sample.Game.*;

public class Player {

    private TileGrid grid;

    public Player(TileGrid grid) {
        this.grid = grid;
    }

    public void SetTile(double x, double y) {
        int newX = (int)Math.floor(x/TILE_SIZE);
        int newY = (int)Math.floor(y/TILE_SIZE);
        System.out.println("Tile " + newX + ' ' + newY + " before setTile " + grid.GetTile(newX, newY).getType());
        grid.SetTile(newX, newY, TileType.Water);
        System.out.println("Tile " + newX + ' ' + newY + " after setTile " + grid.GetTile(newX, newY).getType());
    }

    public TileGrid getGrid() {
        return grid;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }
}
