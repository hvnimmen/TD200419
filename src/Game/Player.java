package Game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Player {

    private int index;
    private TileGrid grid;
    private TowerType[] types;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private static int hp = 0, gold = 0;
    private Tower tempTower;
    private boolean holdingTower;

    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.index = 0;
        this.types = new TowerType[3];
        this.types[0] = TowerType.Archer;
        this.types[1] = TowerType.Freeze;
        this.types[2] = TowerType.Flaming;
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
            return true;
        }
        return false;
    }

    public static void changeHP(int change) {
        hp += change;
    }

    public void update(GraphicsContext gc){

//        if (holdingTower) {
//            tempTower.setX();
//            tempTower.setY();
//            tempTower.draw(gc);
//        }

        for (Tower t : towerList){
            t.update(gc);
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }
    }

    public void addTower(int x, int y){
        switch(types[index]){
            case Archer:
                if (changeGold(-10))
                    towerList.add(new ArcherTower(TowerType.Archer, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                break;
            case Freeze:
                if (changeGold(-30))
                    towerList.add(new FreezeTower(TowerType.Freeze, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                break;
            case Flaming:
                if (changeGold(-20))
                    towerList.add(new FlamingTower(TowerType.Flaming, grid.getTile(x, y), waveManager.getCurrentWave().getEnemyList()));
                break;
        }
    }

    public void switchTower() {
        this.index++;
        if (index >= 3)
            index = 0;
        System.out.println(types[index]);
    }

    public TileGrid getGrid() {
        return grid;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }

    public void pickTower(Tower t){
        tempTower = t;
        holdingTower = true;
    }
}
