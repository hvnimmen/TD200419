package Game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Player {

    private TileGrid grid;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private static int hp = 0, gold = 0;
    private Tower tempTower;
    private boolean holdingTower;

    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<Tower>();
        this.holdingTower = false;
        this.tempTower = null;
    }

    public void setup(){
        gold = 100;
        hp = 100;
    }

    public static boolean changeGold(int change) {
        if (gold + change >= 0) {
            gold += change;
            System.out.println(gold + " gold remaining");
            return true;
        }
        return false;
    }

    public static void changeHP(int change) {
        hp += change;
    }

    public void update(GraphicsContext gc){

        for (Tower t : towerList){
            t.update(gc);
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }
    }

    public void addTower(int x, int y){
        switch(tempTower.getTowerType()){
            case Archer:
                if (changeGold(tempTower.getCost()))
                    towerList.add(new ArcherTower(TowerType.Archer, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                break;
            case Freeze:
                if (changeGold(tempTower.getCost()))
                    towerList.add(new FreezeTower(TowerType.Freeze, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                break;
            case Flaming:
                if (changeGold(tempTower.getCost()))
                    towerList.add(new FlamingTower(TowerType.Flaming, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                break;
        }
        holdingTower = false;
        tempTower = null;
    }

    public TileGrid getGrid() {
        return grid;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }

    public void selectTower(Tower t){
        tempTower = t;
        holdingTower = true;
    }

    public boolean isHoldingTower() {
        return holdingTower;
    }

    public void setHoldingTower(boolean b) {
        this.holdingTower = b;
    }

    public Tower getTempTower() {
        return tempTower;
    }
}
