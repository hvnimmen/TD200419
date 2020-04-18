package Game;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class FlamingTower extends Tower{

    public FlamingTower(Tile startTile, int damage, int range, CopyOnWriteArrayList<Enemy> enemies){
        super(TowerType.Flaming, startTile, damage, range, enemies);
    }

    @Override
    public void shoot(){
        setTimeSinceLastShot(0);
        getProjectiles().add(new FlamingArrow(getTowerType().projectileName, getTarget(), getX(), getY(), 15, 10));
    }

}