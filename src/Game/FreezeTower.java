package Game;

import java.util.ArrayList;

public class FreezeTower extends Tower{

    public FreezeTower(Tile startTile, int damage, int range, ArrayList<Enemy> enemies){
        super(TowerType.Freeze, startTile, damage, range, enemies);
    }

    @Override
    public void shoot(){
        setTimeSinceLastShot(0);
        getProjectiles().add(new FreezeArrow(getTowerType().projectileName, getTarget(), getX(), getY(), 15, 100));
    }

}
