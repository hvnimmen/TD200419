package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Player {

    private TileGrid grid;
    private TileType[] types;
    private int index;
    private WaveManager waveManager;
    private ArrayList<TowerCannon> towerList;
    private boolean editor;

    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.index = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<TowerCannon>();
        this.editor = false;
    }

    public void update(GraphicsContext gc){
        for (TowerCannon t : towerList){
            t.update(gc);
        }
    }

    public void SetTile(int x, int y) {
        grid.SetTile(x, y, types[index]);
    }

    public void addTower(int x, int y){
        System.out.println("Tower added at coordinates" + x + ' ' + y);
        towerList.add(new TowerCannon(grid.GetTile(x, y), 10, waveManager.getCurrentWave().getEnemyList()));
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

    public boolean isEditor() {
        return this.editor;
    }

    public void setEditor(boolean b) {
        this.editor = b;
    }
}
