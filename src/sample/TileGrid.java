package sample;

import static sample.Game.*;

public class TileGrid {

    public Tile[][] map;

    public TileGrid() {
        map = new Tile[X_TILES][Y_TILES];
        for (int i = 0; i < X_TILES; i++){
            for (int j = 0; j < Y_TILES; j++){
                if(i == j-1 || i == j || i == j+1){
                    map[i][j] = new Tile(i, j, TileType.Dirt);
                } else {
                    map[i][j] = new Tile(i, j, TileType.Grass);
                }
            }
        }
    }

    public TileGrid(int[][] newMap){
        map = new Tile[X_TILES][Y_TILES];
        for (int i = 0; i < X_TILES; i++){
            for (int j = 0; j < Y_TILES; j++){
                switch(newMap[j][i]){
                    case 0:
                        map[i][j] = new Tile(i, j, TileType.Grass);
                        break;
                    case 1:
                        map[i][j] = new Tile(i, j, TileType.Dirt);
                        break;
                    case 2:
                        map[i][j] = new Tile(i, j, TileType.Water);
                        break;
                }
            }
        }
    }

    public void SetTile(int x, int y, TileType type){
        map[x][y] = new Tile(x, y, type);
    }

    public Tile GetTile(int x, int y) {
        return map[x][y];
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }
}
