package Game;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArcherTower extends Tower{

    public ArcherTower(Tile startTile, int damage, int range, CopyOnWriteArrayList<Enemy> enemies){
        super(TowerType.Archer, startTile, damage, range, enemies);
    }

    @Override
    public void shoot(){
        setTimeSinceLastShot(0);
        getProjectiles().add(new Arrow(getTowerType().projectileName, getTarget(), getX(), getY(), 15, 10));
    }

}
