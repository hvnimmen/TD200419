package Game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Player {

    private TileGrid grid;
    private TileType[] types;
    private int index;
    private WaveManager waveManager;
    private ArrayList<TowerCannon> towerList;

    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.index = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<TowerCannon>();
    }

    public void update(GraphicsContext gc){
        for (TowerCannon t : towerList){
            t.update(gc);
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }
    }

    public void addTower(int x, int y){
        towerList.add(new TowerCannon(grid.GetTile(x, y), 10,1, waveManager.getCurrentWave().getEnemyList()));
    }

    public TileGrid getGrid() {
        return grid;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }
}
