package sample;

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

    public void SetTile(int x, int y) {
        grid.SetTile(x, y, types[index]);
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
