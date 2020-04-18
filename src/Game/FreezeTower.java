package Game;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class FreezeTower extends Tower{

    public FreezeTower(Tile startTile, int damage, int range, CopyOnWriteArrayList<Enemy> enemies){
        super(TowerType.Freeze, startTile, damage, range, enemies);
    }

    @Override
    public void shoot(){
        setTimeSinceLastShot(0);
        getProjectiles().add(new FreezeArrow(getTowerType().projectileName, getTarget(), getX(), getY(), 15, 100));
    }

}
