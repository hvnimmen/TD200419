package Game;

import javafx.scene.canvas.GraphicsContext;

import static Game.Game.*;

public class TileGrid {

    public Tile[][] map;
    int xTiles, yTiles;

    public TileGrid() {
        xTiles = X_TILES;
        yTiles = Y_TILES;
        map = new Tile[X_TILES][Y_TILES];
        for (int i = 0; i < xTiles; i++){
            for (int j = 0; j < yTiles; j++){
                if(i == j-1 || i == j || i == j+1){
                    map[i][j] = new Tile(i, j, TileType.Dirt);
                } else {
                    map[i][j] = new Tile(i, j, TileType.Grass);
                }
            }
        }
    }

    public TileGrid(int[][] newMap){
        xTiles = newMap[0].length;
        yTiles = newMap.length;
        map = new Tile[xTiles][yTiles];
        for (int i = 0; i < xTiles; i++){
            for (int j = 0; j < yTiles; j++){
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

    public void draw(GraphicsContext gc) {
        for (int i = 0; i < xTiles; i++) {
            for (int j = 0; j < yTiles; j++) {
                map[i][j].Draw(gc);
            }
        }
    }

    public void setTile(int x, int y, TileType type){
        map[x][y] = new Tile(x, y, type);
    }

    public Tile getTile(int x, int y) {
        if (x < xTiles && y < yTiles && x > -1 && y > -1)
            return map[x][y];
        else
            return new Tile(0, 0, TileType.NULL);
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public int getxTiles() {
        return xTiles;
    }

    public void setxTiles(int xTiles) {
        this.xTiles = xTiles;
    }

    public int getyTiles() {
        return yTiles;
    }

    public void setyTiles(int yTiles) {
        this.yTiles = yTiles;
    }
}
